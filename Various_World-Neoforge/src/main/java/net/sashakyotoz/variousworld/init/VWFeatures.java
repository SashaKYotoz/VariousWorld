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
import net.sashakyotoz.variousworld.common.world.features.trees.FancyHangingFoliagePlacer;

public class VWFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, VariousWorld.MOD_ID);
//    public static final ResourceKey<ConfiguredFeature<?,?>> SCULK_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation("various_world","sculk_valley_tree"));
//    public static final ResourceKey<ConfiguredFeature<?,?>> MAGNOLIA_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation("various_world","peaceful_wasteland_tree"));
//    public static final ResourceKey<ConfiguredFeature<?,?>> SHINY_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation("various_world","shiny_valley_tree"));
//    public static final RegistryObject<Feature<?>> SCULK_GEM_ORE = REGISTRY.register("sculk_gem_ore", SculkGemOreFeature::new);
//    public static final RegistryObject<Feature<?>> DEEPSLATE_SCULK_GEM_ORE = REGISTRY.register("deepslate_sculk_gem_ore", DeepslateSculkGemOreFeature::new);
//    public static final RegistryObject<Feature<?>> DEEPSLATE_DARKNIUM_ORE = REGISTRY.register("deepslate_darknium_ore", DeepslateDarkniumOreFeature::new);
//    public static final RegistryObject<Feature<?>> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = REGISTRY.register("underground_sculk_bush_without_fruit", UndergroundSculkBushWithoutFruitFeature::new);
//    public static final RegistryObject<Feature<?>> SMALL_WATER_WELL = REGISTRY.register("small_water_well", SmallWaterWellFeature::new);
//    public static final RegistryObject<Feature<?>> SMALL_WITCHER_HOUSE = REGISTRY.register("small_witcher_house", SmallWitcherHouseFeature::new);
//    public static final RegistryObject<Feature<?>> TREE_HOUSE = REGISTRY.register("tree_house", TreeHouseFeature::new);
//    public static final RegistryObject<Feature<?>> DESERT_WELL = REGISTRY.register("desertwell", DesertWellFeature::new);
//    public static final RegistryObject<Feature<?>> FLOWER_DEEP_MOSS = REGISTRY.register("flower_deep_moss", FlowerDeepMossFeature::new);
//    public static final RegistryObject<Feature<?>> SHINY_VALLEY_DECOR_GENERATOR = REGISTRY.register("shiny_valley_decor_generator", ShinyValleyDecorGeneratorFeature::new);
//    public static final RegistryObject<Feature<?>> TAIGA_TOWER = REGISTRY.register("taiga_tower", TaigaTowerFeature::new);

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_REGISTRY = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, VariousWorld.MOD_ID);
    public static final DeferredHolder<FoliagePlacerType<?>,FoliagePlacerType<?>> FANCY_HANGING_FOLIAGE_PLACER = FOLIAGE_PLACER_REGISTRY.register("fancy_hanging_foliage_placer",()-> new FoliagePlacerType<>(FancyHangingFoliagePlacer.CODEC));
    public static DeferredRegister<TrunkPlacerType<?>> TRUNK_TYPE_REGISTRY = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, VariousWorld.MOD_ID);

    //    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<?>> PREAM_TRUNK_PLACER = TRUNK_TYPE_REGISTRY.register("pream_trunk_placer",
//            () -> new TrunkPlacerType<>(PreamTrunkPlacer.CODEC));
    public static void register(IEventBus bus) {
        FEATURES.register(bus);
        FOLIAGE_PLACER_REGISTRY.register(bus);
        TRUNK_TYPE_REGISTRY.register(bus);
    }
}