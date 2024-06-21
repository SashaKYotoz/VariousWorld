
package net.sashakyotoz.variousworld.block;

import net.minecraft.world.level.LevelAccessor;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldItems;
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
		super(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).sound(SoundType.GRASS).instabreak().randomTicks().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 3).noCollission());
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
		return groundState.is(VariousWorldBlocks.SCULK_GRASS.get());
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
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
	private void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.0125) {
			BlockPos blockPos = BlockPos.containing(x, y, z);
			BlockState state = VariousWorldBlocks.SCULK_BUSH.get().defaultBlockState();
			world.setBlock(blockPos, state, 3);
		}
	}
	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		SculkBushEntityCollidesWithPlantProcedure.execute(entity);
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldItems.SCULK_FRUIT.get());
		return Collections.singletonList(itemStack);
	}
}
