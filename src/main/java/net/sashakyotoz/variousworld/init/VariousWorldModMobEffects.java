
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.sashakyotoz.variousworld.potion.DragonEyeMobEffect;
import net.sashakyotoz.variousworld.potion.ChainedOfChainMobEffect;
import net.sashakyotoz.variousworld.potion.AmethystSpikesMobEffect;
import net.sashakyotoz.variousworld.VariousWorldMod;

public class VariousWorldModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, VariousWorldMod.MODID);
	public static final RegistryObject<MobEffect> AMETHYST_SPIKES = REGISTRY.register("amethyst_spikes", AmethystSpikesMobEffect::new);
	public static final RegistryObject<MobEffect> DRAGON_EYE = REGISTRY.register("dragon_eye", DragonEyeMobEffect::new);
	public static final RegistryObject<MobEffect> CHAINED_OF_CHAIN = REGISTRY.register("chained_of_chain", ChainedOfChainMobEffect::new);
}
