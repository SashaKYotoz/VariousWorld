package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class ShinyValleyDecorGeneratorProcedure {
    public static boolean execute(LevelAccessor world, double x, double y, double z) {
        double sx;
        double sy;
        double sz;
        if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR && world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
            sx = -4;
            for (int index0 = 0; index0 < 8; index0++) {
                sy = -2;
                for (int index1 = 0; index1 < 4; index1++) {
                    sz = -4;
                    for (int index2 = 0; index2 < 8; index2++) {
                        if ((world.getBlockState(BlockPos.containing(x + sx, y + sy + 1, z + sz))).getBlock() == Blocks.AIR && (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.SHINY_GRASS.get()
                                && ((world.getBlockState(BlockPos.containing(x + sx + 1, y + sy, z + sz))).getBlock() == Blocks.WATER || world.getBlockState(BlockPos.containing(x + sx + 1, y + sy, z + sz)).canOcclude())
                                && ((world.getBlockState(BlockPos.containing((x + sx) - 1, y + sy, z + sz))).getBlock() == Blocks.WATER || world.getBlockState(BlockPos.containing((x + sx) - 1, y + sy, z + sz)).canOcclude())
                                && ((world.getBlockState(BlockPos.containing(x + sx, y + sy, (z + sz) - 1))).getBlock() == Blocks.WATER || world.getBlockState(BlockPos.containing(x + sx, y + sy, (z + sz) - 1)).canOcclude())
                                && world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz + 1)).canOcclude()) {
                            {
                                BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
                                BlockState _bs = Blocks.WATER.defaultBlockState();
                                world.setBlock(_bp, _bs, 3);
                            }
                            if (Math.random() < 0.25) {
                                BlockPos _bp = BlockPos.containing(x + sx, (y + sy) - 1, z + sz);
                                BlockState _bs = Blocks.WATER.defaultBlockState();
                                world.setBlock(_bp, _bs, 3);
                            }
                        } else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.SHINY_VALLEY_DECOR_GENERATOR.get()) {
                            BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
                            BlockState _bs = VariousWorldModBlocks.SHINY_GRASS.get().defaultBlockState();
                            world.setBlock(_bp, _bs, 3);
                        }
                        sz = sz + Mth.nextDouble(RandomSource.create(), 1, 2);
                    }
                    sy = sy + 1;
                }
                sx = sx + 1;
            }
        }
        return true;
    }
}
