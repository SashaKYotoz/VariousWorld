package net.sashakyotoz.variousworld.common;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import net.sashakyotoz.variousworld.platform.VWPlatform;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class GemsmithingHelper {
    public static final ResourceLocation GEM_MODIFIER_ID = VariousWorld.createVWLocation("gemsmithing");
    private static final String LEGACY_MODIFIER_PREFIX = "tool.modify_attribute";

    public enum Category {
        TOOL,
        ARMOR,
        RANGED
    }

    private GemsmithingHelper() {
    }

    @Nullable
    public static Category categoryOf(Item item) {
        if (item instanceof ProjectileWeaponItem)
            return Category.RANGED;
        if (item instanceof ArmorItem armor)
            return armor.getType() == ArmorItem.Type.BODY ? null : Category.ARMOR;
        if (item instanceof TieredItem || item instanceof MaceItem)
            return Category.TOOL;
        return null;
    }

    public static boolean isGemmable(Item item) {
        return categoryOf(item) != null;
    }

    public static boolean isGemmable(ItemStack stack) {
        return !stack.isEmpty() && isGemmable(stack.getItem());
    }

    public static boolean supports(ModConfigController.GemsmithingSetting setting, Category category) {
        return switch (category) {
            case TOOL -> true;
            case ARMOR -> setting.armor_attribute() != null && setting.armor_modify_value() != 0.0;
            case RANGED -> setting.arrow_damage() > 0.0;
        };
    }

    @Nullable
    public static ModConfigController.GemsmithingSetting settingFor(ItemStack gem) {
        List<ModConfigController.GemsmithingSetting> settings = ModConfigController.CRYSTALING_CONFIG_VALUES;
        if (settings == null || gem.isEmpty())
            return null;
        for (ModConfigController.GemsmithingSetting setting : settings) {
            if (gem.is(setting.item().build()))
                return setting;
        }
        return null;
    }

    public static boolean canApply(ItemStack tool, ItemStack gem) {
        Category category = categoryOf(tool.getItem());
        ModConfigController.GemsmithingSetting setting = settingFor(gem);
        return category != null && setting != null && supports(setting, category);
    }

    public static ItemStack applyGem(ItemStack tool, ItemStack gem) {
        Category category = categoryOf(tool.getItem());
        ModConfigController.GemsmithingSetting setting = settingFor(gem);
        if (category == null || setting == null || !supports(setting, category))
            return tool;

        ItemStack result = tool.copyWithCount(1);
        ItemStack supplyCrystal = new ItemStack(VWItems.SUPPLY_CRYSTAL.get());
        supplyCrystal.set(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(),
                new SupplyCrystalData(gem.copyWithCount(1), OnActionsTrigger.getToolName(result)));
        result.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(supplyCrystal, setting.durability()));
        switch (category) {
            case TOOL -> setModifiers(result, withGemModifier(effectiveModifiers(result), setting.attribute(),
                    setting.modify_value(), EquipmentSlotGroup.MAINHAND, setting.prefix()));
            case ARMOR -> setModifiers(result, withGemModifier(effectiveModifiers(result), setting.armor_attribute(),
                    setting.armor_modify_value(), EquipmentSlotGroup.bySlot(((ArmorItem) result.getItem()).getEquipmentSlot()), setting.prefix()));
            case RANGED -> {
            }
        }
        return result;
    }

    public static ItemStack removeGem(ItemStack tool) {
        ItemStack result = tool.copy();
        result.set(VWMiscRegistries.CRYSTAL_DATA.get(), OnActionsTrigger.defaultCrystalData());
        ItemAttributeModifiers current = result.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        List<ItemAttributeModifiers.Entry> kept = current.modifiers().stream()
                .filter(entry -> !isGemModifier(entry.modifier().id()))
                .toList();
        if (kept.size() != current.modifiers().size())
            setModifiers(result, new ItemAttributeModifiers(kept, current.showInTooltip()));
        else if (hasInstalledGem(tool) && categoryOf(tool.getItem()) != Category.RANGED)
            result.set(DataComponents.ATTRIBUTE_MODIFIERS, tool.getItem().components()
                    .getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY));
        return result;
    }

    private static boolean hasInstalledGem(ItemStack stack) {
        CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
        if (data == null)
            return false;
        SupplyCrystalData supply = data.crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get());
        return supply != null && !supply.crystalStack().isEmpty();
    }

    @Nullable
    public static ItemStack activeGem(ItemStack stack) {
        CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
        if (data == null || data.crystalDurability() <= 0)
            return null;
        SupplyCrystalData supply = data.crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get());
        if (supply == null || supply.crystalStack().isEmpty())
            return null;
        return supply.crystalStack();
    }

    public static double arrowDamageBonus(ItemStack weapon) {
        if (categoryOf(weapon.getItem()) != Category.RANGED)
            return 0.0;
        ItemStack gem = activeGem(weapon);
        if (gem == null)
            return 0.0;
        ModConfigController.GemsmithingSetting setting = settingFor(gem);
        return setting == null ? 0.0 : Math.max(0.0, setting.arrow_damage());
    }

    private static ItemAttributeModifiers effectiveModifiers(ItemStack stack) {
        ItemAttributeModifiers current = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        if (!current.modifiers().isEmpty())
            return current;
        return VWPlatform.INSTANCE.getDefaultAttributeModifiers(stack);
    }

    private static void setModifiers(ItemStack stack, ItemAttributeModifiers modifiers) {
        ItemAttributeModifiers prototype = stack.getItem().components().getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
        ItemAttributeModifiers defaults = VWPlatform.INSTANCE.getDefaultAttributeModifiers(stack);
        if (modifiers.modifiers().equals(prototype.modifiers()) || (prototype.modifiers().isEmpty() && modifiers.modifiers().equals(defaults.modifiers())))
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, prototype);
        else
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
    }

    private static ItemAttributeModifiers withGemModifier(ItemAttributeModifiers modifiers, @Nullable Attribute attribute, double value,
                                                          EquipmentSlotGroup slot, String prefix) {
        List<ItemAttributeModifiers.Entry> entries = new ArrayList<>();
        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            if (!isGemModifier(entry.modifier().id()))
                entries.add(entry);
        }
        if (attribute == null || BuiltInRegistries.ATTRIBUTE.getKey(attribute) == null) {
            VariousWorld.LOGGER.warn("Gemsmithing setting '{}' has an unknown attribute, only the crystal was applied", prefix);
            return new ItemAttributeModifiers(entries, modifiers.showInTooltip());
        }
        Holder<Attribute> holder = BuiltInRegistries.ATTRIBUTE.wrapAsHolder(attribute);
        entries.add(new ItemAttributeModifiers.Entry(holder,
                new AttributeModifier(GEM_MODIFIER_ID, value, AttributeModifier.Operation.ADD_VALUE), slot));
        return new ItemAttributeModifiers(entries, modifiers.showInTooltip());
    }

    private static boolean isGemModifier(ResourceLocation id) {
        return id.equals(GEM_MODIFIER_ID) || isLegacyModifier(id);
    }

    private static boolean isLegacyModifier(ResourceLocation id) {
        return id.getNamespace().equals(VariousWorld.MOD_ID) && id.getPath().startsWith(LEGACY_MODIFIER_PREFIX);
    }
}