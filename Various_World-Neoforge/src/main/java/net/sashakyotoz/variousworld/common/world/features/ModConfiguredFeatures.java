package net.sashakyotoz.variousworld.common.world.features;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.world.features.trees.ExtraBranchedTrunkPlacer;
import net.sashakyotoz.variousworld.common.world.features.trees.FancyHangingFoliagePlacer;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWFeatures;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTALIC_TREE = registerKey("crystalic_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_CRYSTALIC_TREE = registerKey("small_crystalic_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_JACARANDA_TREE = registerKey("blue_jacaranda_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_PETALS_PATCH = registerKey("jacaranda_petals_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SODALITE_WART_PATCH = registerKey("sodalite_wart_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SODALITE_GEODE = registerKey("sodalite_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RECLAIMITE_POINTED_DRIPSTONE = registerKey("reclaimite_pointed_dripstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOW_LICHEN_VEIN = registerKey("glow_lichen_vein");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBWEB_BLOCKS_CHAIN = registerKey("cobweb_blocks_chain");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

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

        register(context, BLUE_JACARANDA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(VWBlocks.BLUE_JACARANDA_LOG.get()),
                new ExtraBranchedTrunkPlacer(6, 4, 3),
                BlockStateProvider.simple(VWBlocks.BLUE_JACARANDA_LEAVES.get()),
                new FancyHangingFoliagePlacer(UniformInt.of(2, 4), UniformInt.of(0, 1), UniformInt.of(4, 6), 0.25f),
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
                new GeodeCrackSettings(0.8, 2.0, 2), 0.35, 0.075,
                true, UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2), -16, 16, 0.05, 1));
        register(context, RECLAIMITE_POINTED_DRIPSTONE, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(VWFeatures.RECLAIMITE_POINTED_DRIPSTONE_FEATURE.get(),
                new PointedDripstoneConfiguration(0.1F, 0.8F, 0.6F, 0.6F),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 16),
                RandomOffsetPlacement.vertical(ConstantInt.of(1))), PlacementUtils.inlinePlaced(VWFeatures.RECLAIMITE_POINTED_DRIPSTONE_FEATURE.get(),
                new PointedDripstoneConfiguration(0.1F, 0.8F, 0.6F, 0.6F),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 16),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1))))));
        register(context, COBWEB_BLOCKS_CHAIN, VWFeatures.BLOCKS_CHAIN_FEATURE.get(), new BlockStateConfiguration(Blocks.COBWEB.defaultBlockState()));

        register(context, GLOW_LICHEN_VEIN, Feature.MULTIFACE_GROWTH, new MultifaceGrowthConfiguration((MultifaceBlock) Blocks.GLOW_LICHEN, 24, true, true, true, 1.0F, HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE, Blocks.DRIPSTONE_BLOCK, Blocks.CALCITE, Blocks.TUFF, Blocks.DEEPSLATE)));

        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                builder.add(
                        VWBlocks.BLUE_JACARANDA_PETALS.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, i).setValue(PinkPetalsBlock.FACING, direction), 1
                );
            }
        }
        register(
                context,
                JACARANDA_PETALS_PATCH,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        72, 5, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder)))
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, VariousWorld.createVWLocation(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}