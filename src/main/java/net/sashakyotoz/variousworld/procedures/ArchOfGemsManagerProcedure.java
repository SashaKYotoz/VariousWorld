package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;

import java.util.function.Supplier;
import java.util.Map;

public class ArchOfGemsManagerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof ServerPlayer _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == VariousWorldModItems.STRENGH_AMULET
				.get() && entity instanceof Player player) {
			if (!player.level().isClientSide())
				player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 2));
		}
		if ((entity instanceof ServerPlayer _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY).getItem() == VariousWorldModItems.REGENERATION_GEM
				.get() && entity instanceof Player player) {
			if (!player.level().isClientSide())
				player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 4));
		}
		if ((entity instanceof ServerPlayer _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(3)).getItem() : ItemStack.EMPTY)
				.getItem() == VariousWorldModItems.EXPLORER_NECKLACE.get() && entity instanceof Player player) {
			if (!player.level().isClientSide())
				player.addEffect(new MobEffectInstance(MobEffects.JUMP, 300, 2));
			ExplorerNecklaceItemInHandTickProcedure.execute(world, x, y, z, entity);
		}
		if ((entity instanceof ServerPlayer _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(2)).getItem() : ItemStack.EMPTY).getItem() == VariousWorldModItems.AMETHYST_RING
				.get() && entity instanceof Player player) {
			if (!player.level().isClientSide())
				player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 2));
		}
	}
}
