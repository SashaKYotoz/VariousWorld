
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.entity.decoration.PaintingVariant;

import net.sashakyotoz.variousworld.VariousWorldMod;

public class VariousWorldModPaintings {
	public static final DeferredRegister<PaintingVariant> REGISTRY = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, VariousWorldMod.MODID);
	public static final RegistryObject<PaintingVariant> SHINY_VALLEY_ART = REGISTRY.register("shiny_valley_art", () -> new PaintingVariant(64, 32));
	public static final RegistryObject<PaintingVariant> CAVERN_OF_DEEP_ART = REGISTRY.register("cavern_of_deep_art", () -> new PaintingVariant(32, 16));
}
