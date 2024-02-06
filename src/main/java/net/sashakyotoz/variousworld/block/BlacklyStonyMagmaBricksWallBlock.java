
package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldModSounds;

import java.util.Collections;
import java.util.List;

public class BlacklyStonyMagmaBricksWallBlock extends WallBlock {
	public BlacklyStonyMagmaBricksWallBlock() {
		super(BlockBehaviour.Properties.of().sound(VariousWorldModSounds.STONY_MAGMA)
				.strength(5f, 10f).requiresCorrectToolForDrops().friction(0.55f).dynamicShape());
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
			return tieredItem.getTier().getLevel() >= 1;
		return false;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS_WALL.get());
		return Collections.singletonList(itemStack);
	}
}
