package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;

public class ModSaplingBlock extends SaplingBlock {
    public ModSaplingBlock(AbstractTreeGrower grower, Properties properties) {
        super(grower,properties);
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 50;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 30;
    }
    @Override
    public boolean mayPlaceOn(BlockState groundState, BlockGetter getter, BlockPos pos) {
        return groundState.is(Blocks.MYCELIUM) || groundState.is(Blocks.PODZOL) || groundState.is(VariousWorldBlocks.SCULK_GRASS.get()) || groundState.is(VariousWorldBlocks.CRYSTALIC_GRASS.get())
                || groundState.is(VariousWorldBlocks.SHINY_GRASS.get()) || groundState.is(Blocks.GRASS_BLOCK) || groundState.is(VariousWorldBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(VariousWorldBlocks.DEEP_MOSS.get())
                || groundState.is(VariousWorldBlocks.FLOWER_DEEP_MOSS.get()) || groundState.is(Blocks.MOSS_BLOCK);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = worldIn.getBlockState(blockpos);
        return this.mayPlaceOn(groundState, worldIn, blockpos);
    }
}
