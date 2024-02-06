package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;

import javax.annotation.Nullable;
import java.util.Objects;

@Mod.EventBusSubscriber
public class AmethystSpikesOnEffectActiveTickProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}
	private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(VariousWorldModMobEffects.AMETHYST_SPIKES.get())) {
				sourceentity.hurt(sourceentity.damageSources().generic(),(Objects.requireNonNull(livingEntity.getEffect(VariousWorldModMobEffects.AMETHYST_SPIKES.get())).getAmplifier() + 1));
		}
	}
}
