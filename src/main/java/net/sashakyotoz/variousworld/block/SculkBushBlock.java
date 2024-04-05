
package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.PlantType;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

import java.util.Collections;
import java.util.List;

public class SculkBushBlock extends DoublePlantBlock {
	public SculkBushBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 3).noCollission());
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
		return groundState.is(VariousWorldModBlocks.SCULK_GRASS.get());
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
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, player, hand, hit);
		BlockState state = VariousWorldModBlocks.SCULK_BUSH_WITHOUT_BERRY.get().defaultBlockState();
		world.setBlock(pos, state, 3);
		player.spawnAtLocation(new ItemStack(VariousWorldModItems.SCULKBERRY.get()));
		return InteractionResult.SUCCESS;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldModItems.SCULK_FRUIT.get());
		return Collections.singletonList(itemStack);
	}
}
