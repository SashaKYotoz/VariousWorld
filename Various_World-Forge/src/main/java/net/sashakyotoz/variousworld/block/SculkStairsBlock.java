
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class SculkStairsBlock extends StairBlock {
	public SculkStairsBlock() {
		super(Blocks.AIR::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4.5f, 3f).lightLevel(s -> 1).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape());
	}

	@Override
	public float getExplosionResistance() {
		return 3f;
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 8;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VWBlocks.SCULK_STAIRS.get());
		return Collections.singletonList(itemStack);
	}
}
