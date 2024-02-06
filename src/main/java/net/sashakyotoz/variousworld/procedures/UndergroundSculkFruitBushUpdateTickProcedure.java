package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class UndergroundSculkFruitBushUpdateTickProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
            if (Math.random() < 0.05) {
                BlockPos _bp = BlockPos.containing(x, y, z);
                BlockState _bs = VariousWorldModBlocks.UNDERGROUND_SCULK_FRUIT_BUSH.get().defaultBlockState();
                world.setBlock(_bp, _bs, 3);
            }
        }
    }
}
