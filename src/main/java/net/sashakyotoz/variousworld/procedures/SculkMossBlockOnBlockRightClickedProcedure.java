package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class SculkMossBlockOnBlockRightClickedProcedure {
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
                    if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.STONE) {
                        if (Math.random() < 0.5) {
                            BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
                            BlockState _bs = VariousWorldModBlocks.SCULK_MOSS_BLOCK.get().defaultBlockState();
                            world.setBlock(_bp, _bs, 3);
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
