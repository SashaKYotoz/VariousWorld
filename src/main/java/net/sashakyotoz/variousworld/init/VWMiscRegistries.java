package net.sashakyotoz.variousworld.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.sashakyotoz.variousworld.effects.DragonEyeMobEffect;
import net.sashakyotoz.variousworld.effects.ChainedOfChainMobEffect;
import net.sashakyotoz.variousworld.effects.AmethystSpikesMobEffect;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.enchantment.JumperEnchantment;
import net.sashakyotoz.variousworld.enchantment.WitheredEnchantment;

public class VWMiscRegistries {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, VariousWorld.MODID);
	public static final RegistryObject<MobEffect> AMETHYST_SPIKES = EFFECTS.register("amethyst_spikes", AmethystSpikesMobEffect::new);
	public static final RegistryObject<MobEffect> DRAGON_EYE = EFFECTS.register("dragon_eye", DragonEyeMobEffect::new);
	public static final RegistryObject<MobEffect> CHAINED_OF_CHAIN = EFFECTS.register("chained_of_chain", ChainedOfChainMobEffect::new);

	public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, VariousWorld.MODID);
	public static final RegistryObject<PaintingVariant> SHINY_VALLEY_ART = PAINTINGS.register("shiny_valley_art", () -> new PaintingVariant(64, 32));
	public static final RegistryObject<PaintingVariant> CAVERN_OF_DEEP_ART = PAINTINGS.register("cavern_of_deep_art", () -> new PaintingVariant(32, 16));

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, VariousWorld.MODID);
	public static final RegistryObject<Enchantment> WITHERED = ENCHANTMENTS.register("withered", WitheredEnchantment::new);
	public static final RegistryObject<Enchantment> JUMPER = ENCHANTMENTS.register("jumper", JumperEnchantment::new);

	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, VariousWorld.MODID);
	public static final RegistryObject<SimpleParticleType> PEACEFUL_PARTICLE = PARTICLES.register("peaceful_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> WANDERING_SPIRIT_PROJECTILE_PARTICLE = PARTICLES.register("wandering_spirit_ability_shoot_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LORD_SHOOT_PARTICLE = PARTICLES.register("lord_shoot_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> MAGMA_FIREFLIES = PARTICLES.register("magma_fireflies", () -> new SimpleParticleType(false));
	public static void register(IEventBus bus) {
		EFFECTS.register(bus);
		PAINTINGS.register(bus);
		ENCHANTMENTS.register(bus);
		PARTICLES.register(bus);
	}
}