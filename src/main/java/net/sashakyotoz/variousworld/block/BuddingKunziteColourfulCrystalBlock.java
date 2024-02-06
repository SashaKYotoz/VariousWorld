
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class BuddingKunziteColourfulCrystalBlock extends AmethystBlock {
	public static final int GROWTH_CHANCE = 10;
	private static final Direction[] DIRECTIONS = Direction.values();

	public BuddingKunziteColourfulCrystalBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).randomTicks().sound(SoundType.AMETHYST).strength(2.25f, 2.5f).requiresCorrectToolForDrops()
				.friction(0.4f).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	public void randomTick(BlockState p_220898_, ServerLevel p_220899_, BlockPos p_220900_, RandomSource p_220901_) {
		if (p_220901_.nextInt(4) == 0) {
			Direction direction = DIRECTIONS[p_220901_.nextInt(DIRECTIONS.length)];
			BlockPos blockpos = p_220900_.relative(direction);
			BlockState blockstate = p_220899_.getBlockState(blockpos);
			Block block = null;
			if (canClusterGrowAtState(blockstate)) {
				block = VariousWorldModBlocks.SMALL_CRYSTAL_CLUSTER.get();
			} else if (blockstate.is(VariousWorldModBlocks.SMALL_CRYSTAL_CLUSTER.get())
					&& blockstate.getValue(CrystalClusterBlock.FACING) == direction) {
				block = VariousWorldModBlocks.CRYSTAL_CLUSTER.get();
			}
			if (block != null) {
				BlockState blockstate1 = block.defaultBlockState().setValue(CrystalClusterBlock.FACING, direction)
						.setValue(CrystalClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
				p_220899_.setBlockAndUpdate(blockpos, blockstate1);
			}
		}
	}

	public static boolean canClusterGrowAtState(BlockState p_152735_) {
		return p_152735_.isAir() || p_152735_.is(Blocks.WATER) && p_152735_.getFluidState().getAmount() == 8;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
			return tieredItem.getTier().getLevel() >= 1;
		return false;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldModBlocks.BUDDING_KUNZITE_COLOURFUL_CRYSTAL.get());
		return Collections.singletonList(itemStack);
	}
}
