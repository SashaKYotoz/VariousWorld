package net.sashakyotoz.variousworld.init;

import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.PointedDripstoneConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.sashakyotoz.variousworld.common.world.features.custom.BlocksChainFeature;
import net.sashakyotoz.variousworld.common.world.features.custom.ReclaimitePointedDripstoneFeature;
import net.sashakyotoz.variousworld.common.world.features.trees.ExtraBranchedTrunkPlacer;
import net.sashakyotoz.variousworld.common.world.features.trees.FancyHangingFoliagePlacer;
import net.sashakyotoz.variousworld.init.holder.VWHolders;

public class VWFeatures {
    public static void init() {
    }

    public static final VWHolders.FeatureHolder<ReclaimitePointedDripstoneFeature> RECLAIMITE_POINTED_DRIPSTONE_FEATURE = VWRegistryHelper.feature(
            "reclaimite_pointed_dripstone", () -> new ReclaimitePointedDripstoneFeature(PointedDripstoneConfiguration.CODEC));
    public static final VWHolders.FeatureHolder<BlocksChainFeature> BLOCKS_CHAIN_FEATURE = VWRegistryHelper.feature(
            "blocks_chain", () -> new BlocksChainFeature(BlockStateConfiguration.CODEC));

    public static final VWHolders.FoliagePlacerTypeHolder FANCY_HANGING_FOLIAGE_PLACER = VWRegistryHelper.foliagePlacer(
            "fancy_hanging_foliage_placer", () -> new FoliagePlacerType<>(FancyHangingFoliagePlacer.CODEC));
    public static final VWHolders.TrunkPlacerTypeHolder EXTRA_BRANCHED_TRUNK_PLACER = VWRegistryHelper.trunkPlacer(
            "extra_branched_trunk_placer", () -> new TrunkPlacerType<>(ExtraBranchedTrunkPlacer.CODEC));
}