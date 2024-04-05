package net.sashakyotoz.variousworld.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicReference;

public class DisenchantTableUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
				return _retval.get();
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 0)).isEnchanted() && (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
				return _retval.get();
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 1)).getItem() == Items.LAPIS_LAZULI && (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
				return _retval.get();
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 1)).getCount() >= 3 && (new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
				return _retval.get();
			}
		}.getItemStack(world, BlockPos.containing(x, y, z), 2)).getItem() == Items.BOOK) {
			{
				BlockEntity blockEntity = world.getBlockEntity(BlockPos.containing(x, y, z));
				if (blockEntity != null) {
					final int slotid = 1;
					blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							ItemStack stack = capability.getStackInSlot(slotid).copy();
							stack.shrink(3);
							((IItemHandlerModifiable) capability).setStackInSlot(slotid, stack);
						}
					});
				}
			}
			{
				BlockEntity blockEntity = world.getBlockEntity(BlockPos.containing(x, y, z));
				if (blockEntity != null) {
					final int slotid = 0;
					blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							ItemStack stack = capability.getStackInSlot(slotid).copy();
							stack.shrink(1);
							((IItemHandlerModifiable) capability).setStackInSlot(slotid, stack);
						}
					});
				}
			}
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
				if (_ent != null) {
					final int slotid = 2;
					final ItemStack stack = (EnchantmentHelper.enchantItem(RandomSource.create(), new ItemStack(Items.BOOK), 50, true));
					stack.setCount((new Object() {
						public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
							return _retval.get();
						}
					}.getItemStack(world, BlockPos.containing(x, y, z), 2)).getCount());
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable)
							((IItemHandlerModifiable) capability).setStackInSlot(slotid, stack);
					});
				}
			}
		}
	}
}
