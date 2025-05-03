package net.sashakyotoz.variousworld.common.world.features;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWEntities;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> SODALITE_GEODE_MODIFIER = registerKey("sodalite_geode_modifier");
    public static final ResourceKey<BiomeModifier> WANDERING_ZOMBIE_MODIFIER = registerKey("wandering_zombie_modifier");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(SODALITE_GEODE_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SODALITE_GEODE)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));
        context.register(WANDERING_ZOMBIE_MODIFIER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.STRONGHOLD_BIASED_TO),
                List.of(new MobSpawnSettings.SpawnerData(VWEntities.WANDERING_ZOMBIE.get(), 5, 1, 2))
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, VariousWorld.createVWLocation(name));
    }
}
