package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.world.features.trees.ExtraBranchedTrunkPlacer;
import net.sashakyotoz.variousworld.common.world.features.trees.FancyHangingFoliagePlacer;

public class VWFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, VariousWorld.MOD_ID);

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_REGISTRY = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, VariousWorld.MOD_ID);
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<?>> FANCY_HANGING_FOLIAGE_PLACER = FOLIAGE_PLACER_REGISTRY.register("fancy_hanging_foliage_placer", () -> new FoliagePlacerType<>(FancyHangingFoliagePlacer.CODEC));
    public static DeferredRegister<TrunkPlacerType<?>> TRUNK_TYPE_REGISTRY = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, VariousWorld.MOD_ID);
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<?>> EXTRA_BRANCHED_TRUNK_PLACER = TRUNK_TYPE_REGISTRY.register("extra_branched_trunk_placer", () -> new TrunkPlacerType<>(ExtraBranchedTrunkPlacer.CODEC));

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
        FOLIAGE_PLACER_REGISTRY.register(bus);
        TRUNK_TYPE_REGISTRY.register(bus);
    }
}