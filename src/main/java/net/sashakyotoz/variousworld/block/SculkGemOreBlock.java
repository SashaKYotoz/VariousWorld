
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class SculkGemOreBlock extends Block {
	public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

	public SculkGemOreBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.SCULK_SENSOR).strength(6f, 8.705505632961241f).lightLevel(s -> s.getValue(SculkGemOreBlock.CLICKED) ? 15 : 0).requiresCorrectToolForDrops());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(CLICKED);
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
			return tieredItem.getTier().getLevel() >= 3;
		return false;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldModItems.RAW_SCULK_GEM.get());
		return Collections.singletonList(itemStack);
	}
}
