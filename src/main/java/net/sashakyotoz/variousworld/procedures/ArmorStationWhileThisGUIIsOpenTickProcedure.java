package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;

import java.util.function.Supplier;
import java.util.Map;

public class ArmorStationWhileThisGUIIsOpenTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if(entity instanceof ServerPlayer player && player.containerMenu instanceof Supplier supplier){
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.FEATHER
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.SCULK_ARMOR_HELMET.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY).getItem() == Items.NETHER_STAR) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.ANGEL_HELMET.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.FEATHER
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.SCULK_ARMOR_CHESTPLATE.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY).getItem() == Items.NETHER_STAR) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.ANGEL_CHESTPLATE.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier current && current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.FEATHER
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.SCULK_ARMOR_LEGGINGS.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY).getItem() == Items.NETHER_STAR) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.ANGEL_LEGGINGS.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.FEATHER
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.SCULK_ARMOR_BOOTS.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY).getItem() == Items.NETHER_STAR) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.ANGEL_BOOTS.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.PHANTOM_MEMBRANE
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.DARKNIUM_ARMOR_HELMET.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.FURY_INGOT.get()) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.FURY_HELMET.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.PHANTOM_MEMBRANE
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.DARKNIUM_ARMOR_CHESTPLATE.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.FURY_INGOT.get()) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.FURY_CHESTPLATE.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.PHANTOM_MEMBRANE
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.DARKNIUM_ARMOR_LEGGINGS.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.FURY_INGOT.get()) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.FURY_LEGGINGS.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
			if ((supplier.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Items.PHANTOM_MEMBRANE
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.DARKNIUM_ARMOR_BOOTS.get()
					&& (supplier.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY)
					.getItem() == VariousWorldModItems.FURY_INGOT.get()) {
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack _setstack = new ItemStack(VariousWorldModItems.FURY_BOOTS.get());
					_setstack.setCount(1);
					((Slot) _slots.get(3)).set(_setstack);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(0)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(1)).remove(1);
					player.containerMenu.broadcastChanges();
				}
				if (player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					((Slot) _slots.get(2)).remove(1);
					player.containerMenu.broadcastChanges();
				}
			}
		}
	}
}
