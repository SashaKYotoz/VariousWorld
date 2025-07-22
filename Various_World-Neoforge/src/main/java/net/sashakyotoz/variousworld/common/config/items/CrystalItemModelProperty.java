package net.sashakyotoz.variousworld.common.config.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.config.ConfiguredData;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.jetbrains.annotations.Nullable;

public class CrystalItemModelProperty implements SelectItemModelProperty<Crystal> {
    public static final SelectItemModelProperty.Type<CrystalItemModelProperty, Crystal> TYPE = SelectItemModelProperty.Type.create(
            MapCodec.unit(new CrystalItemModelProperty()), Crystal.CODEC
    );

    @Override
    public @Nullable Crystal get(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i1, ItemDisplayContext itemDisplayContext) {
        if (ModConfigController.CRYSTALING_CONFIG_VALUES != null && itemStack.has(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get())) {
            for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                    SupplyCrystalData data1 = itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get());
                    if (data1.crystalStack().getItem().getDescriptionId().contains(setting.prefix()))
                        return new Crystal("%s_%s".formatted(setting.prefix(), data1.toolType()));
            }
        }
        return new Crystal("air");
    }

    @Override
    public Codec<Crystal> valueCodec() {
        return Crystal.CODEC;
    }

    @Override
    public Type<? extends SelectItemModelProperty<Crystal>, Crystal> type() {
        return TYPE;
    }
}