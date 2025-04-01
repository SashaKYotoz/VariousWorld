package net.sashakyotoz.variousworld.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.world.effect.MobEffect;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.common.effects.ChainedOfChainMobEffect;
import net.sashakyotoz.variousworld.common.effects.AmethystSpikesMobEffect;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWMiscRegistries {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, VariousWorld.MOD_ID);
    public static final DeferredHolder<MobEffect, AmethystSpikesMobEffect> AMETHYST_SPIKES = EFFECTS.register("amethyst_spikes", AmethystSpikesMobEffect::new);
    public static final DeferredHolder<MobEffect, ChainedOfChainMobEffect> CHAINED_OF_CHAIN = EFFECTS.register("chained_of_chain", ChainedOfChainMobEffect::new);

//	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHA, VariousWorld.MODID);
//	public static final RegistryObject<Enchantment> WITHERED = ENCHANTMENTS.register("withered", WitheredEnchantment::new);
//	public static final RegistryObject<Enchantment> JUMPER = ENCHANTMENTS.register("jumper", JumperEnchantment::new);

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, VariousWorld.MOD_ID);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PEACEFUL_PARTICLE = PARTICLES.register("peaceful_particle", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WANDERING_SPIRIT_PROJECTILE_PARTICLE = PARTICLES.register("wandering_spirit_ability_shoot_particle", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LORD_SHOOT_PARTICLE = PARTICLES.register("lord_shoot_particle", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MAGMA_FIREFLIES = PARTICLES.register("magma_fireflies", () -> new SimpleParticleType(false));

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
        PARTICLES.register(bus);
    }
}