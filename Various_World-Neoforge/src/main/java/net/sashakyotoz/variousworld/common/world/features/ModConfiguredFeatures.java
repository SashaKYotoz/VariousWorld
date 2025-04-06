package net.sashakyotoz.variousworld.common.world.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.world.features.trees.FancyHangingFoliagePlacer;
import net.sashakyotoz.variousworld.init.VWBlocks;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTALIC_TREE = registerKey("crystalic_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_CRYSTALIC_TREE = registerKey("small_crystalic_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SODALITE_WART_PATCH = registerKey("sodalite_wart_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SODALITE_GEODE = registerKey("sodalite_geode");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        register(context, CRYSTALIC_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(VWBlocks.CRYSTALIC_OAK_LOG.get()),
                new ForkingTrunkPlacer(4, 3, 3),
                BlockStateProvider.simple(VWBlocks.CRYSTALIC_OAK_LEAVES.get()),
                new FancyHangingFoliagePlacer(UniformInt.of(2, 4), UniformInt.of(0, 1), UniformInt.of(4, 6), 0.25f),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(2))
        ).build());
        register(context, SMALL_CRYSTALIC_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(VWBlocks.CRYSTALIC_OAK_LOG.get()),
                new StraightTrunkPlacer(2, 3, 4),
                BlockStateProvider.simple(VWBlocks.CRYSTALIC_OAK_LEAVES.get()),
                new FancyHangingFoliagePlacer(UniformInt.of(2, 4), UniformInt.of(0, 1), UniformInt.of(4, 5), 0.25f),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(2))
        ).build());

        register(context, SODALITE_WART_PATCH, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(VWBlocks.SODALITE_WART.get()))));

        register(context, SODALITE_GEODE, Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR), BlockStateProvider.simple(VWBlocks.SODALITE_BLOCK.get()),
                        BlockStateProvider.simple(VWBlocks.BUDDING_SODALITE.get()), BlockStateProvider.simple(Blocks.CALCITE),
                        BlockStateProvider.simple(Blocks.TUFF), List.of(VWBlocks.SMALL_SODALITE_BUD.get().defaultBlockState(),
                        VWBlocks.MEDIUM_SODALITE_BUD.get().defaultBlockState(), VWBlocks.MEDIUM_SODALITE_BUD.get().defaultBlockState(),
                        VWBlocks.SODALITE_CLUSTER.get().defaultBlockState()), BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                new GeodeCrackSettings(0.95, 2.0, 2), 0.35, 0.075,
                true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05, 1));

//        List<OreConfiguration.TargetBlockState> overworldBismuthOres = List.of(
//                OreConfiguration.target(stoneReplaceables, ModBlocks.BISMUTH_ORE.get().defaultBlockState()),
//                OreConfiguration.target(deepslateReplaceables, ModBlocks.BISMUTH_DEEPSLATE_ORE.get().defaultBlockState()));
//
//        register(context, OVERWORLD_BISMUTH_ORE_KEY, Feature.ORE, new OreConfiguration(overworldBismuthOres, 9));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, VariousWorld.createVWLocation(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}