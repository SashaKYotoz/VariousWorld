
package net.sashakyotoz.variousworld.client.menus;

import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.procedures.DisenchantTableUpdateTickProcedure;
import net.sashakyotoz.variousworld.network.DisenchantTableGUISlotMessage;
import net.sashakyotoz.variousworld.init.VWMenus;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class DisenchantTableGUIMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level level;
	public final Player player;
	public int x, y, z;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;

	public DisenchantTableGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(VWMenus.DISENCHANT_TABLE_GUI.get(), id);
		this.player = inv.player;
		this.level = inv.player.level();
		this.internal = new ItemStackHandler(3);
		BlockPos pos = null;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
		if (pos != null) {
			if (extraData.readableBytes() == 1) {
				byte hand = extraData.readByte();
				ItemStack itemstack;
				if (hand == 0)
					itemstack = this.player.getMainHandItem();
				else
					itemstack = this.player.getOffhandItem();
				itemstack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					this.internal = capability;
					this.bound = true;
				});
			} else if (extraData.readableBytes() > 1) {
				extraData.readByte();
				Entity entity = level.getEntity(extraData.readVarInt());
				if (entity != null)
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
			} else {
				BlockEntity ent = player != null ? level.getBlockEntity(pos) : null;
				if (ent != null) {
					ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
				}
			}
		}
		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 12, 35) {
			@Override
			public void setChanged() {
				super.setChanged();
				slotChanged(0, 0, 0);
			}
		}));
		this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 84, 53) {
			@Override
			public void setChanged() {
				super.setChanged();
				slotChanged(1, 0, 0);
			}

			@Override
			public boolean mayPlace(ItemStack stack) {
				return Items.LAPIS_LAZULI == stack.getItem();
			}
		}));
		this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 156, 35) {
		}));
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 5 + 8 + sj * 18, 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 5 + 8 + si * 18, 142));
		DisenchantTableUpdateTickProcedure.execute(level, x, y, z);
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 3) {
				if (!this.moveItemStackTo(itemstack1, 3, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
				if (index < 3 + 27) {
					if (!this.moveItemStackTo(itemstack1, 3 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 3, 3 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack stack, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (stack.isStackable()) {
			while (!stack.isEmpty()) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameTags(stack, itemstack)) {
					int j = itemstack.getCount() + stack.getCount();
					int maxSize = Math.min(slot.getMaxStackSize(), stack.getMaxStackSize());
					if (j <= maxSize) {
						stack.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < maxSize) {
						stack.shrink(maxSize - itemstack.getCount());
						itemstack.setCount(maxSize);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		if (!stack.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (true) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(stack)) {
					if (stack.getCount() > slot1.getMaxStackSize()) {
						slot1.setByPlayer(stack.split(slot1.getMaxStackSize()));
					} else {
						slot1.setByPlayer(stack.split(stack.getCount()));
					}
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
				}
			}
		}
	}

	private void slotChanged(int slotid, int ctype, int meta) {
		if (this.level != null && this.level.isClientSide()) {
			VariousWorld.PACKET_HANDLER.sendToServer(new DisenchantTableGUISlotMessage(slotid, x, y, z, ctype, meta));
			DisenchantTableGUISlotMessage.handleSlotAction(player, slotid, ctype, meta, x, y, z);
		}
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}
}