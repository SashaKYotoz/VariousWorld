package net.sashakyotoz.variousworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.sashakyotoz.variousworld.init.VWBlocks;

public class BuddingSodaliteBlock extends BuddingAmethystBlock {
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingSodaliteBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate))
                block = VWBlocks.SMALL_SODALITE_BUD.get();
            else {
                if (blockstate.hasProperty(AmethystClusterBlock.FACING) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                    switch (blockstate.getBlock()) {
                        case Block block1 when block1 == VWBlocks.SMALL_SODALITE_BUD.get() ->
                                block = VWBlocks.MEDIUM_SODALITE_BUD.get();
                        case Block block1 when block1 == VWBlocks.MEDIUM_SODALITE_BUD.get() ->
                                block = VWBlocks.SODALITE_CLUSTER.get();
                        default -> block = blockstate.getBlock();
                    }
                }
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, blockstate.getFluidState().is(Fluids.WATER));
                level.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }
}