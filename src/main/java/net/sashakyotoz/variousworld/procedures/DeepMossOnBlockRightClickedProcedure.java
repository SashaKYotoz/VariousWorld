package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;

public class DeepMossOnBlockRightClickedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        double sx;
        double sy;
        double sz;
        sx = -3;
        for (int index0 = 0; index0 < 6; index0++) {
            sy = -3;
            for (int index1 = 0; index1 < 6; index1++) {
                sz = -3;
                for (int index2 = 0; index2 < 6; index2++) {
                    if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.STONE || (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.DEEPSLATE) {
                        if (RandomSource.create().nextBoolean()) {
                            world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), VariousWorldBlocks.DEEP_MOSS.get().defaultBlockState(), 3);
                            if (Math.random() < 0.125 && !world.getBlockState(BlockPos.containing(x + sx, y + sy + 1, z + sz)).canOcclude())
                                world.setBlock(BlockPos.containing(x + sx, y + sy + 1, z + sz), VariousWorldBlocks.DEEP_MOSS.get().defaultBlockState(), 3);
                        }
                    }
                    sz = sz + 1;
                }
                sy = sy + 1;
            }
            sx = sx + 1;
        }
    }
}
