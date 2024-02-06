package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class DeepslateSculkGemOreAdditionalGenerationConditionProcedure {
    public static boolean execute(LevelAccessor world, double x, double y, double z) {
        double sx;
        double sy;
        double sz;
        sx = -5;
        for (int index0 = 0; index0 < 8; index0++) {
            sy = -5;
            for (int index1 = 0; index1 < 8; index1++) {
                sz = -5;
                for (int index2 = 0; index2 < 8; index2++) {
                    if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.DEEPSLATE_SCULK_GEM_ORE.get()) {
                        if (Math.random() < 0.5) {
                            BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
                            BlockState _bs = VariousWorldModBlocks.SCULK_MOSS_BLOCK.get().defaultBlockState();
                            world.setBlock(_bp, _bs, 3);
                        } else if (Math.random() < 0.25) {
                            BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
                            BlockState _bs = Blocks.AIR.defaultBlockState();
                            world.setBlock(_bp, _bs, 3);
                        } else if (Math.random() < 0.125) {
                            BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
                            BlockState _bs = VariousWorldModBlocks.SCULK_MAGMA.get().defaultBlockState();
                            world.setBlock(_bp, _bs, 3);
                        }
                    }
                    sz = sz + 1;
                }
                sy = sy + 1;
            }
            sx = sx + 1;
        }
        return true;
    }
}
