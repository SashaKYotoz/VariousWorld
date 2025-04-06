package net.sashakyotoz.variousworld.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.world.features.ModPlacedFeatures;

public class VWBiomes {
    public static final ResourceKey<Biome> CRYSTALLINE_FOREST = registerKey("crystalline_forest");

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(CRYSTALLINE_FOREST, crystallineForest(context));
    }

    public static Biome crystallineForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 2, 3, 5));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CRYSTALIC_TREE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SMALL_CRYSTALIC_TREE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SODALITE_WART_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.SODALITE_GEODE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.25f)
                .temperature(0.75f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(5013401)
                        .waterFogColor(5013401)
                        .skyColor(calculateSkyColor(0.9f))
                        .fogColor(5013401)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(Holder.direct(SoundEvents.AMETHYST_BLOCK_CHIME))).build())
                .build();
    }

    private static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    public static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, VariousWorld.createVWLocation(name));
    }

    public static SurfaceRules.RuleSource preliminarySurfaceRule(ResourceKey<Biome> biomeKey, BlockState groundBlock, BlockState undergroundBlock, BlockState underwaterBlock) {
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(biomeKey),
                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), SurfaceRules.state(groundBlock)), SurfaceRules.state(underwaterBlock))),
                                SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(undergroundBlock)))));
    }

    public static SurfaceRules.RuleSource anySurfaceRule(ResourceKey<Biome> biomeKey, BlockState groundBlock, BlockState undergroundBlock, BlockState underwaterBlock) {
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(biomeKey),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), SurfaceRules.state(groundBlock)), SurfaceRules.state(underwaterBlock))),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(undergroundBlock))));
    }
}