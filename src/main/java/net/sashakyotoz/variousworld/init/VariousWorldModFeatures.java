
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.VariousWorldMod;
import net.sashakyotoz.variousworld.world.features.*;
import net.sashakyotoz.variousworld.world.features.ores.*;
import net.sashakyotoz.variousworld.world.features.plants.*;

@Mod.EventBusSubscriber
public class VariousWorldModFeatures {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, VariousWorldMod.MODID);
    public static final ResourceKey<ConfiguredFeature<?,?>> CRYSTALIC_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation("various_world","crystalicforest_tree"));
    public static final ResourceKey<ConfiguredFeature<?,?>> SCULK_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation("various_world","sculk_valley_tree"));
    public static final ResourceKey<ConfiguredFeature<?,?>> MAGNOLIA_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation("various_world","peaceful_wasteland_tree"));
    public static final ResourceKey<ConfiguredFeature<?,?>> SHINY_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation("various_world","shiny_valley_tree"));
    public static final RegistryObject<Feature<?>> BLACKLY_STONY_MAGMA = REGISTRY.register("blackly_stony_magma", BlacklyStonyMagmaFeature::new);
    public static final RegistryObject<Feature<?>> SCULK_GEM_ORE = REGISTRY.register("sculk_gem_ore", Sculk_GemOreFeature::new);
    public static final RegistryObject<Feature<?>> DEEPSLATE_SCULK_GEM_ORE = REGISTRY.register("deepslate_sculk_gem_ore", DeepslateSculkGemOreFeature::new);
    public static final RegistryObject<Feature<?>> DEEPSLATE_DARKNIUM_ORE = REGISTRY.register("deepslate_darknium_ore", DeepslateDarkniumOreFeature::new);
    public static final RegistryObject<Feature<?>> SMALL_SCULK_BUSH = REGISTRY.register("small_sculk_bush", SmallSculkBushFeature::new);
    public static final RegistryObject<Feature<?>> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = REGISTRY.register("underground_sculk_bush_without_fruit", UndergroundSculkBushWithoutFruitFeature::new);
    public static final RegistryObject<Feature<?>> PURPLE_SAFFRON = REGISTRY.register("purple_saffron", PurpleSaffronFeature::new);
    public static final RegistryObject<Feature<?>> SHINY_PLUMERIA = REGISTRY.register("shiny_plumeria", ShinyPlumeriaFeature::new);
    public static final RegistryObject<Feature<?>> ANTHURIUM_SPROUTED_OF_MAGMA = REGISTRY.register("anthurium_sprouted_of_magma", AnthuriumSproutedOfMagmaFeature::new);
    public static final RegistryObject<Feature<?>> SMALL_WATER_WELL = REGISTRY.register("small_water_well", SmallWaterWellFeature::new);
    public static final RegistryObject<Feature<?>> SNOWMAN_TOWER = REGISTRY.register("snowman_tower", SnowmanTowerFeature::new);
    public static final RegistryObject<Feature<?>> SMALL_WITCHER_HOUSE = REGISTRY.register("small_witcher_house", SmallWitcherHouseFeature::new);
    public static final RegistryObject<Feature<?>> TREE_HOUSE = REGISTRY.register("tree_house", TreeHouseFeature::new);
    public static final RegistryObject<Feature<?>> DESERTWELL = REGISTRY.register("desertwell", DesertwellFeature::new);
    public static final RegistryObject<Feature<?>> FLOWER_DEEP_MOSS = REGISTRY.register("flower_deep_moss", FlowerDeepMossFeature::new);
    public static final RegistryObject<Feature<?>> SHINY_VALLEY_DECOR_GENERATOR = REGISTRY.register("shiny_valley_decor_generator", ShinyValleyDecorGeneratorFeature::new);
    public static final RegistryObject<Feature<?>> TAIGA_TOWER = REGISTRY.register("taiga_tower", TaigaTowerFeature::new);
    public static final RegistryObject<Feature<?>> CAVERNS_OF_MAGMA_GROWTH_FEATURES = REGISTRY.register("caverns_of_magma_growth_features", CavernsOfMagmaGrowthFeaturesFeature::new);
    public static final RegistryObject<Feature<?>> CAVERNS_OF_MAGMA_FEATURE = REGISTRY.register("caverns_of_magma_feature", CavernsOfMagmaFeatureFeature::new);
}