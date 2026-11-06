package net.sashakyotoz.variousworld.common;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import java.util.ArrayList;
import java.util.List;

public final class GemsmithingHelper {
    public static final ResourceLocation GEM_MODIFIER_ID = VariousWorld.createVWLocation("gemsmithing");
    private static final String LEGACY_MODIFIER_PREFIX = "tool.modify_attribute";

    private GemsmithingHelper() {
    }

    public static ItemStack applyGem(ItemStack tool, ItemStack gem) {
        List<ModConfigController.GemsmithingSetting> settings = ModConfigController.CRYSTALING_CONFIG_VALUES;
        if (!(tool.getItem() instanceof TieredItem) || settings == null || gem.isEmpty())
            return tool;

        for (ModConfigController.GemsmithingSetting setting : settings) {
            if (!gem.is(setting.item().build()))
                continue;

            ItemStack result = tool.copyWithCount(1);
            ItemStack supplyCrystal = new ItemStack(VWItems.SUPPLY_CRYSTAL.get());
            supplyCrystal.set(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(),
                    new SupplyCrystalData(gem.copyWithCount(1), OnActionsTrigger.getToolName(result)));
            result.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(supplyCrystal, setting.durability()));
            result.set(DataComponents.ATTRIBUTE_MODIFIERS, withGemModifier(currentModifiers(result), setting));
            return result;
        }
        return tool;
    }

    public static ItemStack removeGem(ItemStack tool) {
        ItemStack result = tool.copy();
        result.set(VWMiscRegistries.CRYSTAL_DATA.get(), OnActionsTrigger.defaultCrystalData());
        ItemAttributeModifiers defaults = tool.getItem().components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (defaults != null)
            result.set(DataComponents.ATTRIBUTE_MODIFIERS, defaults);
        else
            result.remove(DataComponents.ATTRIBUTE_MODIFIERS);
        return result;
    }

    private static ItemAttributeModifiers currentModifiers(ItemStack stack) {
        ItemAttributeModifiers defaults = stack.getItem().components().get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (defaults != null)
            return defaults;
        return stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
    }

    private static ItemAttributeModifiers withGemModifier(ItemAttributeModifiers modifiers, ModConfigController.GemsmithingSetting setting) {
        Attribute attribute = setting.attribute();
        if (attribute == null || BuiltInRegistries.ATTRIBUTE.getKey(attribute) == null) {
            VariousWorld.LOGGER.warn("Gemsmithing setting '{}' has an unknown attribute, only the crystal was applied", setting.prefix());
            return modifiers;
        }
        Holder<Attribute> holder = BuiltInRegistries.ATTRIBUTE.wrapAsHolder(attribute);

        List<ItemAttributeModifiers.Entry> entries = new ArrayList<>();
        boolean merged = false;
        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            if (isGemModifier(entry.modifier().id()))
                continue;
            if (!merged && canMerge(entry, holder)) {
                AttributeModifier modifier = entry.modifier();
                entries.add(new ItemAttributeModifiers.Entry(entry.attribute(),
                        new AttributeModifier(modifier.id(), modifier.amount() + setting.modify_value(), modifier.operation()),
                        entry.slot()));
                merged = true;
            } else {
                entries.add(entry);
            }
        }
        if (!merged)
            entries.add(new ItemAttributeModifiers.Entry(holder,
                    new AttributeModifier(GEM_MODIFIER_ID, setting.modify_value(), AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND));
        return new ItemAttributeModifiers(entries, modifiers.showInTooltip());
    }

    private static boolean canMerge(ItemAttributeModifiers.Entry entry, Holder<Attribute> attribute) {
        return entry.attribute().is(attribute)
                && entry.modifier().operation() == AttributeModifier.Operation.ADD_VALUE
                && (entry.slot() == EquipmentSlotGroup.MAINHAND || entry.slot() == EquipmentSlotGroup.HAND || entry.slot() == EquipmentSlotGroup.ANY);
    }

    private static boolean isGemModifier(ResourceLocation id) {
        return id.equals(GEM_MODIFIER_ID) || isLegacyModifier(id);
    }

    private static boolean isLegacyModifier(ResourceLocation id) {
        return id.getNamespace().equals(VariousWorld.MOD_ID) && id.getPath().startsWith(LEGACY_MODIFIER_PREFIX);
    }
}