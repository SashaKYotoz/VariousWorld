
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.sashakyotoz.variousworld.client.particle.WanderingSpiritAbilityShootParticleParticle;
import net.sashakyotoz.variousworld.client.particle.SculkVibrationParticle;
import net.sashakyotoz.variousworld.client.particle.PeacefulParticleParticle;
import net.sashakyotoz.variousworld.client.particle.MagmaFirefliesParticle;
import net.sashakyotoz.variousworld.client.particle.LordShootParticleParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VariousWorldModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(VariousWorldModParticleTypes.SCULK_VIBRATION.get(), SculkVibrationParticle::provider);
		event.registerSpriteSet(VariousWorldModParticleTypes.PEACEFUL_PARTICLE.get(), PeacefulParticleParticle::provider);
		event.registerSpriteSet(VariousWorldModParticleTypes.WANDERING_SPIRIT_ABILITY_SHOOT_PARTICLE.get(), WanderingSpiritAbilityShootParticleParticle::provider);
		event.registerSpriteSet(VariousWorldModParticleTypes.LORD_SHOOT_PARTICLE.get(), LordShootParticleParticle::provider);
		event.registerSpriteSet(VariousWorldModParticleTypes.MAGMA_FIREFLIES.get(), MagmaFirefliesParticle::provider);
	}
}
