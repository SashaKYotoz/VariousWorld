package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;

public class CryshroomFoodEatenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (Math.random() < 0.5) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(VariousWorldModMobEffects.AMETHYST_SPIKES.get(), 100, 1, true, false));
		} else if (Math.random() < 0.25) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 2, true, false));
		}
	}
}
