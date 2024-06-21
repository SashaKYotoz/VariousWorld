
package net.sashakyotoz.variousworld.world.features.ores;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class FlowerDeepMossFeature extends OreFeature {

    public FlowerDeepMossFeature() {
        super(OreConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        if (level.getBlockState(pos.above()).isAir()) {
            BlockPos above = pos.above();
            BlockState state = VariousWorldModBlocks.MYCENA_FROM_CAVERN_OF_DEEP.get().defaultBlockState();
            level.setBlock(above, state, 3);
        }
        if (level.getBlockState(pos.below()).isAir()) {
            BlockPos below = pos.below();
            BlockState state = VariousWorldModBlocks.FLOWER_VINE_FROM_CAVERNOF_DEEP.get().defaultBlockState();
            level.setBlock(below, state, 3);
        }
        BlockState state = VariousWorldModBlocks.DEEP_MOSS.get().defaultBlockState();
        level.setBlock(pos, state, 3);
        return true;
    }
}
