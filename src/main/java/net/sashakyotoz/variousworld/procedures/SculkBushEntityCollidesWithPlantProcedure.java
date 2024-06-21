package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.sashakyotoz.variousworld.entity.WanderingSpiritOfSculksEntity;
import net.sashakyotoz.variousworld.init.VariousWorldItems;

public class SculkBushEntityCollidesWithPlantProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if(entity instanceof LivingEntity livingEntity){
			if (!(entity instanceof Animal || entity instanceof WanderingSpiritOfSculksEntity
					|| entity instanceof Player player && player.getItemBySlot(EquipmentSlot.LEGS).is(VariousWorldItems.SCULK_ARMOR_LEGGINGS.get()))) {
				livingEntity.hurt(livingEntity.damageSources().sweetBerryBush(), 1);
			}
		}
	}
}
