package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VWBlocks;

public class UndergroundSculkFruitBushUpdateTickProcedure {
    public static void execute(LevelAccessor world, BlockPos pos) {
        if (world.getBlockState(pos.above()).isAir()) {
            if (Math.random() < 0.05) {
                BlockState state = VWBlocks.UNDERGROUND_SCULK_FRUIT_BUSH.get().defaultBlockState();
                world.setBlock(pos, state, 3);
            }
        }
    }
}