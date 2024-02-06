
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
        WorldGenLevel world = context.level();
        int x = context.origin().getX();
        int y = context.origin().getY();
        int z = context.origin().getZ();
        if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).isAir()) {
            BlockPos _bp = BlockPos.containing(x, y + 1, z);
            BlockState _bs = VariousWorldModBlocks.MYCENA_FROM_CAVERN_OF_DEEP.get().defaultBlockState();
            world.setBlock(_bp, _bs, 3);
        }
        if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.AIR) {
            BlockPos _bp = BlockPos.containing(x, y - 1, z);
            BlockState _bs = VariousWorldModBlocks.FLOWER_VINE_FROM_CAVERNOF_DEEP.get().defaultBlockState();
            world.setBlock(_bp, _bs, 3);
        }
        BlockPos _bp = BlockPos.containing(x, y, z);
        BlockState _bs = VariousWorldModBlocks.DEEP_MOSS.get().defaultBlockState();
        world.setBlock(_bp, _bs, 3);
        return true;
    }
}
