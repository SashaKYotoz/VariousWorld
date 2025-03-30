package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VWBlocks;

public class ModSaplingBlock extends SaplingBlock {
    public ModSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower,properties);
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
        return groundState.is(Blocks.MYCELIUM) || groundState.is(Blocks.PODZOL) || groundState.is(VWBlocks.SCULK_GRASS.get()) || groundState.is(VWBlocks.CRYSTALIC_GRASS.get())
                || groundState.is(VWBlocks.SHINY_GRASS.get()) || groundState.is(Blocks.GRASS_BLOCK) || groundState.is(VWBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(VWBlocks.DEEP_MOSS.get())
                || groundState.is(VWBlocks.FLOWER_DEEP_MOSS.get()) || groundState.is(Blocks.MOSS_BLOCK);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = worldIn.getBlockState(blockpos);
        return this.mayPlaceOn(groundState, worldIn, blockpos);
    }
}
