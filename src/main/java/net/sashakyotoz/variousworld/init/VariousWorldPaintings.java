package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.entity.decoration.PaintingVariant;

import net.sashakyotoz.variousworld.VariousWorld;

public class VariousWorldPaintings {
	public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, VariousWorld.MODID);
	public static final RegistryObject<PaintingVariant> SHINY_VALLEY_ART = PAINTINGS.register("shiny_valley_art", () -> new PaintingVariant(64, 32));
	public static final RegistryObject<PaintingVariant> CAVERN_OF_DEEP_ART = PAINTINGS.register("cavern_of_deep_art", () -> new PaintingVariant(32, 16));
}
