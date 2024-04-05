package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.sashakyotoz.variousworld.init.VariousWorldModParticleTypes;

public class SculkScytheRangedItemProjectileHitsLivingEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		for (int i = 0; i < 360; i++) {
			if (i % 20 == 0) {
				world.addParticle(VariousWorldModParticleTypes.WANDERING_SPIRIT_ABILITY_SHOOT_PARTICLE.get(),
						x, y + 0.5, z,
						Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
			}
		}
		if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide()){
			if(livingEntity.getRandom().nextBoolean())
				livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1, false, false));
			else
				livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 1, false, false));
		}
	}
}
