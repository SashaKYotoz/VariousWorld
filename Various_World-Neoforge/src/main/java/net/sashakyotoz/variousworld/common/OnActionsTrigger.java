package net.sashakyotoz.variousworld.common;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.init.VWBiomes;
import net.sashakyotoz.variousworld.init.VWBlocks;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@EventBusSubscriber
public class OnActionsTrigger {
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }

    @SubscribeEvent
    public static void tick(ServerTickEvent.Post event) {
        List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getKey().run());
        workQueue.removeAll(actions);
    }

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        MinecraftServer server = event.getServer();
        Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        Registry<LevelStem> levelStemTypeRegistry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        Registry<Biome> biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);
        for (LevelStem levelStem : levelStemTypeRegistry.stream().toList()) {
            DimensionType dimensionType = levelStem.type().value();
            if (dimensionType == dimensionTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD)) {
                ChunkGenerator chunkGenerator = levelStem.generator();
                if (chunkGenerator.getBiomeSource() instanceof MultiNoiseBiomeSource noiseSource) {
                    List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(noiseSource.parameters().values());
                    if (ModConfigController.MOD_CONFIG_VALUES != null && ModConfigController.MOD_CONFIG_VALUES.do_crystalline_forest()) {
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.35f, 0.7f), Climate.Parameter.span(-0.25f, 0.35f), Climate.Parameter.span(0.1f, 0.5f), Climate.Parameter.span(-0.125f, 0.35f),
                                Climate.Parameter.point(0f), Climate.Parameter.span(-0.65f, 0.35f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.CRYSTALLINE_FOREST)));
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.35f, 0.7f), Climate.Parameter.span(-0.25f, 0.35f), Climate.Parameter.span(0.1f, 0.5f), Climate.Parameter.span(-0.125f, 0.35f),
                                Climate.Parameter.point(1f), Climate.Parameter.span(-0.65f, 0.35f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.CRYSTALLINE_FOREST)));
                    }
                    if (ModConfigController.MOD_CONFIG_VALUES != null && ModConfigController.MOD_CONFIG_VALUES.do_blue_jacaranda_meadow()) {
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.45f, 0.9f), Climate.Parameter.span(-0.125f, 0.65f), Climate.Parameter.span(0.2f, 0.7f), Climate.Parameter.span(-0.25f, 0.5f),
                                Climate.Parameter.point(0f), Climate.Parameter.span(-0.54f, 0.44f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.BLUE_JACARANDA_MEADOW)));
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.45f, 0.9f), Climate.Parameter.span(-0.125f, 0.65f), Climate.Parameter.span(0.2f, 0.7f), Climate.Parameter.span(-0.25f, 0.5f),
                                Climate.Parameter.point(1f), Climate.Parameter.span(-0.54f, 0.44f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.BLUE_JACARANDA_MEADOW)));
                    }
                    chunkGenerator.biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(parameters));
                    chunkGenerator.featuresPerStep = Suppliers
                            .memoize(() -> FeatureSorter.buildFeaturesPerStep(List.copyOf(chunkGenerator.biomeSource.possibleBiomes()), biome -> chunkGenerator.generationSettingsGetter.apply(biome).features(), true));
                }
                if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
                    NoiseGeneratorSettings noiseGeneratorSettings = noiseGenerator.settings.value();
                    SurfaceRules.RuleSource currentRuleSource = noiseGeneratorSettings.surfaceRule();
                    if (currentRuleSource instanceof SurfaceRules.SequenceRuleSource sequenceRuleSource) {
                        List<SurfaceRules.RuleSource> surfaceRules = new ArrayList<>(sequenceRuleSource.sequence());
                        surfaceRules.add(0, VWBiomes.preliminarySurfaceRule(VWBiomes.CRYSTALLINE_FOREST, VWBlocks.CRYSTALIC_GRASS_BLOCK.get().defaultBlockState(),
                                VWBlocks.DIRT_WITH_CRYSTALS.get().defaultBlockState(), VWBlocks.DIRT_WITH_CRYSTALS.get().defaultBlockState()));
                        surfaceRules.add(1, VWBiomes.preliminarySurfaceRule(VWBiomes.BLUE_JACARANDA_MEADOW, Blocks.GRASS_BLOCK.defaultBlockState(),
                                Blocks.COARSE_DIRT.defaultBlockState(), Blocks.CLAY.defaultBlockState()));
                        NoiseGeneratorSettings moddedNoiseGeneratorSettings = new NoiseGeneratorSettings(noiseGeneratorSettings.noiseSettings(), noiseGeneratorSettings.defaultBlock(), noiseGeneratorSettings.defaultFluid(),
                                noiseGeneratorSettings.noiseRouter(), SurfaceRules.sequence(surfaceRules.toArray(SurfaceRules.RuleSource[]::new)), noiseGeneratorSettings.spawnTarget(), noiseGeneratorSettings.seaLevel(),
                                noiseGeneratorSettings.disableMobGeneration(), noiseGeneratorSettings.aquifersEnabled(), noiseGeneratorSettings.oreVeinsEnabled(), noiseGeneratorSettings.useLegacyRandomSource());
                        noiseGenerator.settings = new Holder.Direct<>(moddedNoiseGeneratorSettings);
                    }
                }
            }
        }
    }

    public static void returnDefaultStack(ItemStack stack, LivingEntity entity) {
        ItemStack tmpStack = stack.getItem().getDefaultInstance();
        tmpStack.setCount(stack.getCount());
        tmpStack.setDamageValue(stack.getDamageValue());
        for (EquipmentSlot value : EquipmentSlot.values()) {
            if (entity.getItemBySlot(value).equals(stack))
                entity.setItemSlot(value, tmpStack);
        }
    }
}