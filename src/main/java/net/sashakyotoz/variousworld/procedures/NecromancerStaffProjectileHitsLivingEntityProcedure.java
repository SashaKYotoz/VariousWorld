package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.sashakyotoz.variousworld.init.VWMiscRegistries;

public class NecromancerStaffProjectileHitsLivingEntityProcedure {
	public static void execute(Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity != sourceentity) {
			if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide())
				livingEntity.addEffect(new MobEffectInstance(VWMiscRegistries.CHAINED_OF_CHAIN.get(), 150, 0, true, false));
		}
	}
}
