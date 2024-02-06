package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;

public class NecromancerStaffProjectileHitsLivingEntityProcedure {
	public static void execute(Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!(entity == sourceentity)) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(VariousWorldModMobEffects.CHAINED_OF_CHAIN.get(), 150, 0, true, false));
		}
	}
}
