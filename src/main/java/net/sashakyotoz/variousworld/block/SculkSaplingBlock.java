
package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.world.treegrowers.SculkTreeGrower;

import java.util.Collections;
import java.util.List;

public class SculkSaplingBlock extends SaplingBlock implements BonemealableBlock {
	public SculkSaplingBlock() {
		super(new SculkTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().lightLevel(s -> 3).noCollission());
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
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(Blocks.MYCELIUM) || groundState.is(Blocks.PODZOL) || groundState.is(VariousWorldModBlocks.SCULK_GRASS.get()) || groundState.is(VariousWorldModBlocks.CRYSTALIC_GRASS.get())
				|| groundState.is(VariousWorldModBlocks.SHINY_GRASS.get()) || groundState.is(Blocks.GRASS_BLOCK) || groundState.is(VariousWorldModBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(VariousWorldModBlocks.DEEP_MOSS.get())
				|| groundState.is(VariousWorldModBlocks.FLOWER_DEEP_MOSS.get()) || groundState.is(Blocks.MOSS_BLOCK);
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		return this.mayPlaceOn(groundState, worldIn, blockpos);
	}


	@Override
	public boolean isValidBonemealTarget(LevelReader reader, BlockPos p_50898_, BlockState p_50899_, boolean p_50900_) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
		return (double)level.random.nextFloat() < 0.35D;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldModBlocks.SCULK_SAPLING.get());
		return Collections.singletonList(itemStack);
	}
}
