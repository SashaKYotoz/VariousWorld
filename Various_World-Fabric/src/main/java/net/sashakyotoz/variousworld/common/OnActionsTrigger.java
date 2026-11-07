package net.sashakyotoz.variousworld.common;

import com.google.common.base.Predicate;
import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
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
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.init.VWBiomes;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import net.sashakyotoz.variousworld.mixin.accessor.ChunkGeneratorAccessor;
import net.sashakyotoz.variousworld.mixin.accessor.MultiNoiseBiomeSourceAccessor;
import net.sashakyotoz.variousworld.mixin.accessor.NoiseBasedChunkGeneratorAccessor;

import java.util.ArrayList;
import java.util.List;

public class OnActionsTrigger {

    public static boolean isMovingOnLand(LivingEntity entity) {
        return entity.onGround() && entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    public static void returnDefaultStack(ItemStack stack, LivingEntity entity) {
        ItemStack reverted = GemsmithingHelper.removeGem(stack);
        for (EquipmentSlot value : EquipmentSlot.values()) {
            if (entity.getItemBySlot(value) == stack)
                entity.setItemSlot(value, reverted);
        }
    }

    public static void wearCrystal(ItemStack stack, LivingEntity user) {
        if (user.level().isClientSide())
            return;
        CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
        if (data == null || !data.crystalStack().has(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get())
                || data.crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()).crystalStack().isEmpty())
            return;
        if (data.crystalDurability() > 0) {
            stack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(data.crystalStack(), data.crystalDurability() - 1));
            return;
        }
        Level level = user.level();
        if (!level.isClientSide()) {
            level.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.AMETHYST_CLUSTER_BREAK, user.getSoundSource(), 1.0F, 1.2F);
            ((ServerLevel) level).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, data.crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()).crystalStack()),
                    user.getX(), user.getEyeY() - 0.4, user.getZ(), 8, 0.2, 0.2, 0.2, 0.05);
        }
        returnDefaultStack(stack, user);
    }

    public static ItemStack returnStackWithGem(ItemStack stack, ItemStack gem) {
        return GemsmithingHelper.applyGem(stack, gem);
    }

    public static String getToolName(ItemStack stack) {
        Item item = stack.getItem();
        return switch (item) {
            case SwordItem ignored -> "sword";
            case PickaxeItem ignored -> "pickaxe";
            case AxeItem ignored -> "axe";
            case HoeItem ignored -> "hoe";
            case ShovelItem ignored -> "shovel";
            case MaceItem ignored -> "mace";
            case CrossbowItem ignored -> "crossbow";
            case BowItem ignored -> "bow";
            case ArmorItem armor -> switch (armor.getType()) {
                case HELMET -> "helmet";
                case CHESTPLATE -> "chestplate";
                case LEGGINGS -> "leggings";
                case BOOTS -> "boots";
                default -> "";
            };
            default -> "";
        };
    }

    public static void wearCrystalOf(ItemStack stack, LivingEntity user, GemsmithingHelper.Category category) {
        if (!stack.isEmpty() && GemsmithingHelper.categoryOf(stack.getItem()) == category)
            wearCrystal(stack, user);
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

    public static CrystalData defaultCrystalData() {
        return new CrystalData(new ItemStack(VWItems.SUPPLY_CRYSTAL.get()), 0);
    }

    public static void onUseBlock(Player player, Level level, BlockPos pos, ItemStack stack) {
        BlockState state = level.getBlockState(pos);
        if (BlockUtils.isReclamited(state) && stack.getItem() instanceof PickaxeItem) {
            if (level.isClientSide()) {
                level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, VWBlocks.RECLAIMITE_CRYSTAL.get().defaultBlockState()), pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
                level.playLocalSound(pos, SoundEvents.MEDIUM_AMETHYST_BUD_BREAK, SoundSource.BLOCKS, 1, 0.4f, true);
            }
            level.setBlockAndUpdate(pos, state.setValue(BlockUtils.RECLAMITE_SHARDED, false));
            player.drop(new ItemStack(VWItems.RECLAIMITE_SHARD.get()), true, false);
        }
    }

    public static void injectOverworldBiomes(MinecraftServer server) {
        Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        Registry<LevelStem> levelStemTypeRegistry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        Registry<Biome> biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);
        for (LevelStem levelStem : levelStemTypeRegistry.stream().toList()) {
            DimensionType dimensionType = levelStem.type().value();
            if (dimensionType != dimensionTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD))
                continue;
            ChunkGenerator chunkGenerator = levelStem.generator();
            if (chunkGenerator.getBiomeSource() instanceof MultiNoiseBiomeSource noiseSource) {
                List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(((MultiNoiseBiomeSourceAccessor) noiseSource).variousworld$parameters().values());
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
                ChunkGeneratorAccessor accessor = (ChunkGeneratorAccessor) chunkGenerator;
                BiomeSource biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(parameters));
                accessor.variousworld$setBiomeSource(biomeSource);
                accessor.variousworld$setFeaturesPerStep(Suppliers.memoize(() -> FeatureSorter.buildFeaturesPerStep(List.copyOf(biomeSource.possibleBiomes()),
                        biome -> accessor.variousworld$getGenerationSettingsGetter().apply(biome).features(), true)));
            }
            if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
                NoiseGeneratorSettings settings = noiseGenerator.generatorSettings().value();
                SurfaceRules.RuleSource surfaceRule = SurfaceRules.sequence(
                        VWBiomes.preliminarySurfaceRule(VWBiomes.CRYSTALLINE_FOREST, VWBlocks.CRYSTALIC_GRASS_BLOCK.get().defaultBlockState(),
                                VWBlocks.DIRT_WITH_CRYSTALS.get().defaultBlockState(), VWBlocks.DIRT_WITH_CRYSTALS.get().defaultBlockState()),
                        VWBiomes.preliminarySurfaceRule(VWBiomes.BLUE_JACARANDA_MEADOW, Blocks.GRASS_BLOCK.defaultBlockState(),
                                Blocks.COARSE_DIRT.defaultBlockState(), Blocks.CLAY.defaultBlockState()),
                        settings.surfaceRule());
                NoiseGeneratorSettings moddedSettings = new NoiseGeneratorSettings(settings.noiseSettings(), settings.defaultBlock(), settings.defaultFluid(),
                        settings.noiseRouter(), surfaceRule, settings.spawnTarget(), settings.seaLevel(),
                        settings.disableMobGeneration(), settings.aquifersEnabled(), settings.oreVeinsEnabled(), settings.useLegacyRandomSource());
                ((NoiseBasedChunkGeneratorAccessor) (Object) noiseGenerator).variousworld$setSettings(Holder.direct(moddedSettings));
            }
        }
    }
}