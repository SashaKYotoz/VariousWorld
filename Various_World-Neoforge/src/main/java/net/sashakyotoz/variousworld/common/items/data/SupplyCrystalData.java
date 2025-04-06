package net.sashakyotoz.variousworld.common.items.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

public record SupplyCrystalData(ItemStack crystalStack, String toolType) {
    public static final Codec<SupplyCrystalData> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        ItemStack.CODEC.fieldOf("crystal_stack").forGetter(SupplyCrystalData::crystalStack),
                        Codec.STRING.fieldOf("tool_type").forGetter(SupplyCrystalData::toolType)
                ).apply(instance, SupplyCrystalData::new)
        );
    }
}