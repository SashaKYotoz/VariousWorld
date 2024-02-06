package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.sashakyotoz.variousworld.entity.WanderingSpiritSummonedOfSculksEntity;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class SculkBushEntityCollidesWithPlantProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if(entity instanceof LivingEntity livingEntity){
			if (!(entity instanceof Animal || entity instanceof WanderingSpiritSummonedOfSculksEntity || entity instanceof ItemEntity
					|| entity instanceof Player player && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == VariousWorldModItems.SCULK_ARMOR_LEGGINGS.get())) {
				livingEntity.hurt(new DamageSource(livingEntity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)) {
					@Override
					public Component getLocalizedDeathMessage(LivingEntity livingEntity) {
						return Component.translatable("death.attack." + "Sculk Spikes");
					}
				}, 1);
			}
		}
	}
}
