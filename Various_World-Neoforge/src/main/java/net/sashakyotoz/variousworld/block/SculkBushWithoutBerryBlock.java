
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.procedures.SculkBushEntityCollidesWithPlantProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.common.PlantType;

import java.util.Collections;
import java.util.List;

public class SculkBushWithoutBerryBlock extends DoublePlantBlock {
	public SculkBushWithoutBerryBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).sound(SoundType.GRASS).instabreak().randomTicks().noCollission());
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}

	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(VWBlocks.SCULK_GRASS.get()) || groundState.is(Blocks.SCULK);
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		if (blockstate.getValue(HALF) == DoubleBlockHalf.UPPER)
			return groundState.is(this) && groundState.getValue(HALF) == DoubleBlockHalf.LOWER;
		else
			return this.mayPlaceOn(groundState, worldIn, blockpos);
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.PLAINS;
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel level, BlockPos pos, RandomSource random) {
		super.tick(blockstate, level, pos, random);
		if (Math.random() < 0.0125 && level.getBlockState(pos.above()).isAir()) {
			BlockState state = VWBlocks.SCULK_BUSH.get().defaultBlockState();
			level.setBlock(pos, state, 3);
		}
	}
	@Override
	public void entityInside(BlockState blockstate, Level level, BlockPos pos, Entity entity) {
		SculkBushEntityCollidesWithPlantProcedure.execute(entity);
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VWItems.SCULK_FRUIT.get());
		return Collections.singletonList(itemStack);
	}
}
