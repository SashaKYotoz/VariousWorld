package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.sashakyotoz.variousworld.init.VariousWorldModParticleTypes;

public class DarkniumSwordLivingEntityIsHitWithToolProcedure {
	public static void execute(LevelAccessor level, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof LivingEntity livingEntity && livingEntity.getMainHandItem().getOrCreateTag().getDouble("CustomModelData") == 1) {
			level.addParticle(VariousWorldModParticleTypes.SCULK_VIBRATION.get(), x, y, z, 0, 1.5, 0);
			if (Math.random() < 0.5) {
				if (entity instanceof LivingEntity entityToHit)
					entityToHit.hurt(new DamageSource(entityToHit.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)) {
						@Override
						public Component getLocalizedDeathMessage(LivingEntity living) {
							return Component.translatable("death.attack." + "Absorption by sculk");
						}
					}, 1);
				if (level instanceof ServerLevel serverLevel)
					serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, x, y, z, 1));
			}
		}
	}
}
