package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.world.features.ModBiomeModifiers;
import net.sashakyotoz.variousworld.common.world.features.ModConfiguredFeatures;
import net.sashakyotoz.variousworld.common.world.features.ModPlacedFeatures;
import net.sashakyotoz.variousworld.init.VWBiomes;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
//            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
//
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.BIOME, VWBiomes::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(VariousWorld.MOD_ID));
    }
}
