package net.sashakyotoz.variousworld.world.treegrowers;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.sashakyotoz.variousworld.init.VariousWorldFeatures;
import org.jetbrains.annotations.Nullable;

public class SculkTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource source, boolean p_222911_) {
        return VariousWorldFeatures.SCULK_TREE;
    }
}
