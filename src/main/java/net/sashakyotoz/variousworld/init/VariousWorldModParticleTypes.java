package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.sashakyotoz.variousworld.VariousWorldMod;

public class VariousWorldModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, VariousWorldMod.MODID);
	public static final RegistryObject<SimpleParticleType> PEACEFUL_PARTICLE = REGISTRY.register("peaceful_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> WANDERING_SPIRIT_PROJECTILE_PARTICLE = REGISTRY.register("wandering_spirit_ability_shoot_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LORD_SHOOT_PARTICLE = REGISTRY.register("lord_shoot_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> MAGMA_FIREFLIES = REGISTRY.register("magma_fireflies", () -> new SimpleParticleType(false));
}
