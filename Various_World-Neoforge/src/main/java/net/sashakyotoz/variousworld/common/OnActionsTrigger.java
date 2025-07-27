package net.sashakyotoz.variousworld.common;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
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
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@EventBusSubscriber
public class OnActionsTrigger {
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
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

    public static ItemStack returnStackWithGem(ItemStack stack, ItemStack gem) {
        ItemStack supplyGemStack = VWItems.SUPPLY_CRYSTAL.toStack();
        String toolName = getToolName(stack);
        if (stack.getItem() instanceof TieredItem && stack.has(VWMiscRegistries.CRYSTAL_DATA.get())) {
            for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                if (gem.getItem().equals(setting.item().build())) {
                    supplyGemStack.set(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(), new SupplyCrystalData(gem, toolName));
                    stack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(supplyGemStack, setting.durability()));
                    break;
                }
            }
        }
        return stack;
    }

    public static String getToolName(ItemStack stack) {
        String toolName;
        switch (stack.getItem()) {
            case TieredItem item when item instanceof SwordItem -> toolName = "sword";
            case TieredItem item when item instanceof PickaxeItem -> toolName = "pickaxe";
            case TieredItem item when item instanceof AxeItem -> toolName = "axe";
            case TieredItem item when item instanceof HoeItem -> toolName = "hoe";
            case TieredItem item when item instanceof ShovelItem -> toolName = "shovel";
            default -> toolName = "";
        }
        return toolName;
    }

    public static void spawnParticle(ParticleOptions type, Level level, double x, double y, double z, float modifier) {
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0)
                level.addParticle(type, x + 0.5, y, z + 0.5, Math.cos(i) * 0.25d * modifier, 0.2d, Math.sin(i) * 0.25d * modifier);
        }
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
                                Climate.Parameter.point(0f), Climate.Parameter.span(-0.64f, 0.28f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.CRYSTALLINE_FOREST)));
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.35f, 0.7f), Climate.Parameter.span(-0.25f, 0.35f), Climate.Parameter.span(0.1f, 0.5f), Climate.Parameter.span(-0.125f, 0.35f),
                                Climate.Parameter.point(1f), Climate.Parameter.span(-0.64f, 0.28f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.CRYSTALLINE_FOREST)));
                    }
                    if (ModConfigController.MOD_CONFIG_VALUES != null && ModConfigController.MOD_CONFIG_VALUES.do_blue_jacaranda_meadow()) {
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.45f, 0.9f), Climate.Parameter.span(-0.125f, 0.65f), Climate.Parameter.span(0.2f, 0.7f), Climate.Parameter.span(-0.25f, 0.5f),
                                Climate.Parameter.point(0f), Climate.Parameter.span(-0.56f, 0.46f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.BLUE_JACARANDA_MEADOW)));
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.45f, 0.9f), Climate.Parameter.span(-0.125f, 0.65f), Climate.Parameter.span(0.2f, 0.7f), Climate.Parameter.span(-0.25f, 0.5f),
                                Climate.Parameter.point(1f), Climate.Parameter.span(-0.56f, 0.46f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.BLUE_JACARANDA_MEADOW)));
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

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        if (event.getType().equals(VWVillagers.GEMSMITHER.value())) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.AMETHYST_SHARD, 4),
                    new ItemStack(Items.EMERALD), 10, 3, 0.1f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(VWItems.CRYSTALLINE_SLIME_BALL.get(), 2),
                    new ItemStack(Items.EMERALD), 10, 6, 0.1f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 4),
                    new ItemStack(VWItems.SODALITE_SHARD.get()), 10, 5, 0.05f));
            event.getTrades().get(2)
                    .add(new BasicItemListing(new ItemStack(Items.EMERALD, 8), new ItemStack(Items.IRON_AXE),
                            returnStackWithGem(Items.IRON_AXE.getDefaultInstance(), Items.AMETHYST_SHARD.getDefaultInstance()), 1, 8, 0.25f));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(Items.AMETHYST_SHARD, 2), 10, 5, 0.05f));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(VWItems.CRYSTALLINE_SLIME_BALL.get()), new ItemStack(Items.EMERALD, 1), new ItemStack(Items.SLIME_BALL, 2), 10, 10, 0.25f));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 11), new ItemStack(Items.DIAMOND_PICKAXE),
                    returnStackWithGem(Items.DIAMOND_PICKAXE.getDefaultInstance(), VWItems.SODALITE_SHARD.toStack()), 1, 3, 0.15f));
        }
        if (event.getType().equals(VWVillagers.ARTIFACTOLOGIST.value())) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 8), new ItemStack(VWItems.CRYSTALLINE_STRENGTH.get()), 8, 5, 0.1f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.REDSTONE, 2), 8, 5, 0.1f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.SUGAR, 2), 8, 5, 0.1f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.GLOWSTONE, 2), 8, 5, 0.1f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.EMERALD), 8, 5, 0.1f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.GLOWSTONE, 3), new ItemStack(Items.EMERALD), 8, 5, 0.1f));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(VWItems.CRYSTALLINE_STRENGTH.get()), new ItemStack(Items.ECHO_SHARD), new ItemStack(Items.RECOVERY_COMPASS), 2, 5, 0.1f));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 16), new ItemStack(VWBlocks.ARTIFACT_TABLE.get()), 1, 5, 0.1f));
        }
    }
}