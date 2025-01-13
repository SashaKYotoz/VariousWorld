
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class PurpleSaffronBlock extends FlowerBlock {
	public PurpleSaffronBlock() {
		super(() -> MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).strength(0f, 1f).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 3).noCollission());
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 50;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}


	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(Blocks.GRASS_BLOCK) || groundState.is(Blocks.DIRT_PATH) || groundState.is(Blocks.MYCELIUM) || groundState.is(Blocks.DIRT) || groundState.is(Blocks.COARSE_DIRT) || groundState.is(Blocks.PODZOL)
				|| groundState.is(Blocks.ROOTED_DIRT) || groundState.is(Blocks.MOSS_BLOCK) || groundState.is(VWBlocks.SCULK_GRASS.get()) || groundState.is(VWBlocks.SHINY_GRASS.get())
				|| groundState.is(VWBlocks.CRYSTALIC_GRASS.get());
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		return this.mayPlaceOn(groundState, worldIn, blockpos);
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VWBlocks.PURPLE_SAFFRON.get());
		return Collections.singletonList(itemStack);
	}
}
