package net.sashakyotoz.variousworld.common.items.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

public record CrystalData(ItemStack crystalStack, int crystalDurability) {
    public static final Codec<CrystalData> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        ItemStack.CODEC.fieldOf("crystal_stack").forGetter(CrystalData::crystalStack),
                        Codec.INT.fieldOf("crystal_durability").forGetter(CrystalData::crystalDurability)
                ).apply(instance, CrystalData::new)
        );
    }
}