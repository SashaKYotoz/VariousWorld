package net.sashakyotoz.variousworld.common.world.features;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> CRYSTALIC_TREE = registerKey("crystalic_tree");
    public static final ResourceKey<PlacedFeature> SMALL_CRYSTALIC_TREE = registerKey("small_crystalic_tree");

    public static final ResourceKey<PlacedFeature> SODALITE_WART_PATCH = registerKey("sodalite_wart_patch");

    public static final ResourceKey<PlacedFeature> SODALITE_GEODE = registerKey("sodalite_geode");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, CRYSTALIC_TREE, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYSTALIC_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.25F, 2), VWBlocks.CRYSTALIC_OAK_SAPLING.get()));
        register(context, SMALL_CRYSTALIC_TREE, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYSTALIC_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.25F, 2), VWBlocks.CRYSTALIC_OAK_SAPLING.get()));
//        register(context, BISMUTH_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_BISMUTH_ORE_KEY),
//                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, SODALITE_WART_PATCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.SODALITE_WART_PATCH), List.of(
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        ));

        register(context, SODALITE_GEODE, configuredFeatures.getOrThrow(ModConfiguredFeatures.SODALITE_GEODE), List.of(
                RarityFilter.onAverageOnceEvery(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(12), VerticalAnchor.absolute(48)),
                BiomeFilter.biome()
        ));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, VariousWorld.createVWLocation(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
