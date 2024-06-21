
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class MycenaFromCavernOfDeepBlock extends Block {
	public MycenaFromCavernOfDeepBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK).sound(SoundType.FUNGUS).lightLevel(s -> 12).friction(0.5f).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldBlocks.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK.get());
		return Collections.singletonList(itemStack);
	}
}
