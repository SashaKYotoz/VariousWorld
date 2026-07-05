package net.sashakyotoz.variousworld.common;

import com.google.common.base.Predicate;
import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
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

    public static boolean isMovingOnLand(LivingEntity entity) {
        return entity.onGround() && entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
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

    public static ArrayList<BlockPos> findBlocksInRegion(Level level, BlockPos min, BlockPos max,
                                                         Predicate<BlockState> matcher, int maxResults) {
        ArrayList<BlockPos> found = new ArrayList<>();
        final int minX = Math.min(min.getX(), max.getX());
        final int maxX = Math.max(min.getX(), max.getX());
        final int columnMinY = Math.max(-63, Math.min(min.getY(), max.getY()));
        final int maxY = Math.max(min.getY(), max.getY());
        final int minZ = Math.min(min.getZ(), max.getZ());
        final int maxZ = Math.max(min.getZ(), max.getZ());
        BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                int topY = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
                int columnMaxY = Math.min(maxY, topY);
                if (columnMinY > columnMaxY) continue;
                for (int y = columnMaxY; y >= columnMinY; y--) {
                    mpos.set(x, y, z);
                    BlockState state = level.getBlockState(mpos);
                    if (matcher.test(state)) {
                        found.add(mpos.immutable());
                        if (maxResults > 0 && found.size() >= maxResults) return found;
                    }
                }
            }
        }
        return found;
    }

    public static BlockPos findNearest(List<BlockPos> candidates, LivingEntity entity, Level level) {
        if (candidates.isEmpty()) return null;
        double bestDistSq = Double.POSITIVE_INFINITY;
        BlockPos best = null;
        final double ex = entity.getX();
        final double ey = entity.getY();
        final double ez = entity.getZ();
        for (BlockPos pos : candidates) {
            double dx = pos.getX() + 0.5 - ex;
            double dy = pos.getY() + 0.5 - ey;
            double dz = pos.getZ() + 0.5 - ez;
            double distSq = dx * dx + dy * dy + dz * dz;
            if (distSq >= bestDistSq) continue;
            if (level.getFluidState(pos).isSource() && level.getFluidState(pos).is(Fluids.LAVA)) continue;
            if (!level.isAreaLoaded(pos, 1)) continue;
            bestDistSq = distSq;
            best = pos;
        }

        return best;
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
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        BlockState state = event.getLevel().getBlockState(event.getPos());
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        if (BlockUtils.isReclamited(state) && stack.getItem() instanceof PickaxeItem) {
            if (level.isClientSide()) {
                level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, VWBlocks.RECLAIMITE_CRYSTAL.get().defaultBlockState()), pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
                level.playLocalSound(pos, SoundEvents.MEDIUM_AMETHYST_BUD_BREAK, SoundSource.BLOCKS, 1, 0.4f, true);
            }
            level.setBlockAndUpdate(pos, state.setValue(BlockUtils.RECLAMITE_SHARDED, false));
            event.getEntity().drop(VWItems.RECLAIMITE_SHARD.toStack(), false);
        }
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
                    if (ModConfigController.MOD_CONFIG_VALUES != null && ModConfigController.MOD_CONFIG_VALUES.do_reclamite_caves()) {
                        parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(-1f, 1f), Climate.Parameter.span(0.5f, 1f), Climate.Parameter.span(-0.5f, 0.5f), Climate.Parameter.span(0f, 0.75f),
                                Climate.Parameter.span(0.6f, 1.1f), Climate.Parameter.span(-1f, 1f), 0), biomeRegistry.getHolderOrThrow(VWBiomes.RECLAIMITE_CAVES)));
                    }
                    chunkGenerator.biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(parameters));
                    chunkGenerator.featuresPerStep = Suppliers
                            .memoize(() -> FeatureSorter.buildFeaturesPerStep(List.copyOf(chunkGenerator.biomeSource.possibleBiomes()), biome -> chunkGenerator.generationSettingsGetter.apply(biome).features(), true));
                }
                if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
                    NoiseGeneratorSettings noiseGeneratorSettings = noiseGenerator.settings.value();
                    SurfaceRules.RuleSource currentRuleSource = noiseGeneratorSettings.surfaceRule();
                    if (currentRuleSource instanceof SurfaceRules.SequenceRuleSource(
                            List<SurfaceRules.RuleSource> sequence
                    )) {
                        List<SurfaceRules.RuleSource> surfaceRules = new ArrayList<>(sequence);
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