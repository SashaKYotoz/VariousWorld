package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;

public class SculkMossBlockOnBlockRightClickedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        double sx;
        double sy;
        double sz;
        sx = -3;
        for (int i = 0; i < 6; i++) {
            sy = -3;
            for (int j = 0; j < 6; j++) {
                sz = -3;
                for (int k = 0; k < 6; k++) {
                    if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.STONE) {
                        if (Math.random() < 0.5) {
                            BlockPos pos = BlockPos.containing(x + sx, y + sy, z + sz);
                            world.setBlock(pos, VariousWorldBlocks.SCULK_MOSS_BLOCK.get().defaultBlockState(), 3);
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