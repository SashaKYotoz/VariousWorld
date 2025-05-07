package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VWItems;

import java.util.concurrent.atomic.AtomicReference;

public class ArtifactTableRandomTickProcedure {

	private static ItemStack getItemStack(LevelAccessor accessor, BlockPos pos, int slotid) {
		BlockEntity blockEntity = accessor.getBlockEntity(pos);
		if (blockEntity != null) {
			AtomicReference<ItemStack> itemStackRef = new AtomicReference<>(ItemStack.EMPTY);
			blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(handler -> {
				itemStackRef.set(handler.getStackInSlot(slotid).copy());
			});
			return itemStackRef.get();
		}
		return ItemStack.EMPTY;
	}

	private static boolean isItemStackMatching(LevelAccessor world, BlockPos pos, int slotid, Item targetItem) {
		return getItemStack(world, pos, slotid).getItem() == targetItem;
	}

	private static void updateBlockState(LevelAccessor world, BlockPos pos, int value) {
		BlockState blockState = world.getBlockState(pos);
		IntegerProperty property = (IntegerProperty) blockState.getBlock().getStateDefinition().getProperty("artifacts");
		if (property != null && property.getPossibleValues().contains(value)) {
			world.setBlock(pos, blockState.setValue(property, value), 3);
		}
	}

	public static void execute(LevelAccessor world, BlockPos blockPos) {
		Item airItem = ItemStack.EMPTY.getItem();
		boolean isSlot0Air = isItemStackMatching(world, blockPos, 0, airItem);
		boolean isSlot1Air = isItemStackMatching(world, blockPos, 1, airItem);
		boolean isSlot2Air = isItemStackMatching(world, blockPos, 2, airItem);
		boolean isSlot3Air = isItemStackMatching(world, blockPos, 3, airItem);

		boolean isSlot0Amulet = isItemStackMatching(world, blockPos, 0, VWItems.STRENGH_AMULET.get());
		boolean isSlot1Gem = isItemStackMatching(world, blockPos, 1, VWItems.REGENERATION_GEM.get());
		boolean isSlot2Ring = isItemStackMatching(world, blockPos, 2, VWItems.AMETHYST_RING.get());
		boolean isSlot3Necklace = isItemStackMatching(world, blockPos, 3, VWItems.EXPLORER_NECKLACE.get());

		if (isSlot0Air && isSlot1Air && isSlot2Air && isSlot3Air) {
			updateBlockState(world, blockPos, 0);
		} else if (isSlot0Amulet && isSlot1Gem && isSlot2Ring && isSlot3Necklace) {
			updateBlockState(world, blockPos, 5);
		} else if (isSlot0Air && isSlot1Air && isSlot2Ring && isSlot3Air) {
			updateBlockState(world, blockPos, 1);
		} else if (isSlot0Amulet && isSlot1Air && isSlot2Air && isSlot3Air) {
			updateBlockState(world, blockPos, 2);
		} else if (isSlot0Air && isSlot1Gem && isSlot2Air && isSlot3Air) {
			updateBlockState(world, blockPos, 3);
		} else if (isSlot0Air && isSlot1Air && isSlot2Air && isSlot3Necklace) {
			updateBlockState(world, blockPos, 4);
		}
	}
}