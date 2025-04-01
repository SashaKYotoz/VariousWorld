package net.sashakyotoz.variousworld.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.sashakyotoz.variousworld.VariousWorld;

@EventBusSubscriber
public class VWBiomes {
	public static final ResourceKey<Biome> CRYSTALIC_FOREST = ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"crystalicforest"));
	public static final ResourceKey<Biome> PEACEFUL_WASTELAND = ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"peaceful_wasteland"));
	public static final ResourceKey<Biome> SCULK_VALLEY = ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"sculk_valley"));
	public static final ResourceKey<Biome> SHINY_VALLEY = ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"shiny_valley"));
	public static final ResourceKey<Biome> CAVERN_OF_DEEP = ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"cavernof_deep"));
	public static final ResourceKey<Biome> CAVERNS_OF_MAGMA_GROWTH = ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"caverns_of_magma_growth"));
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
//				if (chunkGenerator.getBiomeSource() instanceof MultiNoiseBiomeSource noiseSource) {
//					List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(noiseSource.parameters().values());
//					if (VariousWorldConfig.GENERATE_CRYSTALIC_FOREST.get()){
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.25f, 0.8f), Climate.Parameter.span(-0.25f, 0.5f), Climate.Parameter.span(-0.015f, 0.65f), Climate.Parameter.span(-0.2225f, 0.515f),
//								Climate.Parameter.point(0f), Climate.Parameter.span(-0.75f, 0.35f), 0), biomeRegistry.getHolderOrThrow(CRYSTALIC_FOREST)));
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.25f, 0.8f), Climate.Parameter.span(-0.25f, 0.5f), Climate.Parameter.span(-0.015f, 0.65f), Climate.Parameter.span(-0.2225f, 0.515f),
//								Climate.Parameter.point(1f), Climate.Parameter.span(-0.75f, 0.35f), 0), biomeRegistry.getHolderOrThrow(CRYSTALIC_FOREST)));
//					}
//					if (VariousWorldConfig.GENERATE_PEACEFUL_WASTELAND.get()){
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.25f, 0.75f), Climate.Parameter.span(-0.1f, 0.775f), Climate.Parameter.span(-0.05f, 0.85f), Climate.Parameter.span(-0.125f, 0.65f),
//								Climate.Parameter.point(0f), Climate.Parameter.span(-0.8f, 0.15f), 0), biomeRegistry.getHolderOrThrow(PEACEFUL_WASTELAND)));
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.25f, 0.75f), Climate.Parameter.span(-0.1f, 0.775f), Climate.Parameter.span(-0.05f, 0.85f), Climate.Parameter.span(-0.125f, 0.65f),
//								Climate.Parameter.point(1f), Climate.Parameter.span(-0.8f, 0.15f), 0), biomeRegistry.getHolderOrThrow(PEACEFUL_WASTELAND)));
//					}
//					if (VariousWorldConfig.GENERATE_SCULK_VALLEY.get()){
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(-0.55f, 0.45f), Climate.Parameter.span(-0.45f, 0.45f), Climate.Parameter.span(0.28f, 1.14f), Climate.Parameter.span(-0.25f, 0.35f),
//								Climate.Parameter.point(0f), Climate.Parameter.span(-0.1f, 0.75f), 0), biomeRegistry.getHolderOrThrow(SCULK_VALLEY)));
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(-0.55f, 0.45f), Climate.Parameter.span(-0.45f, 0.45f), Climate.Parameter.span(0.28f, 1.14f), Climate.Parameter.span(-0.25f, 0.35f),
//								Climate.Parameter.point(1f), Climate.Parameter.span(-0.1f, 0.75f), 0), biomeRegistry.getHolderOrThrow(SCULK_VALLEY)));
//					}
//					if (VariousWorldConfig.GENERATE_SHINY_VALLEY.get()){
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.35f, 0.95f), Climate.Parameter.span(0.35f, 0.95f), Climate.Parameter.span(-0.15f, 0.6f), Climate.Parameter.span(0.4f, 1f),
//								Climate.Parameter.point(0f), Climate.Parameter.span(-0.9f, 0.4f), 0), biomeRegistry.getHolderOrThrow(SHINY_VALLEY)));
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.35f, 0.95f), Climate.Parameter.span(0.35f, 0.95f), Climate.Parameter.span(-0.15f, 0.6f), Climate.Parameter.span(0.4f, 1f),
//								Climate.Parameter.point(1f), Climate.Parameter.span(-0.9f, 0.4f), 0), biomeRegistry.getHolderOrThrow(SHINY_VALLEY)));
//					}
//					if (VariousWorldConfig.GENERATE_CAVERN_OF_DEEP.get()){
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(-0.75f, -0.25f), Climate.Parameter.span(-0.85f, -0.15f), Climate.Parameter.span(0.575f, 0.95f), Climate.Parameter.span(-0.5f, 0.375f),
//								Climate.Parameter.span(0.5f, 1.1f), Climate.Parameter.span(0.25f, 0.8f), 0), biomeRegistry.getHolderOrThrow(CAVERN_OF_DEEP)));
//					}
//					if (VariousWorldConfig.GENERATE_CAVERNS_OF_MAGMA_GROWTH.get()){
//						parameters.add(new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(0.35f, 1f), Climate.Parameter.span(-0.15f, 1.05f), Climate.Parameter.span(0.25f, 0.85f), Climate.Parameter.span(0.35f, 1f),
//								Climate.Parameter.span(0.3f, 0.9f), Climate.Parameter.span(0.25f, 0.75f), 0), biomeRegistry.getHolderOrThrow(CAVERNS_OF_MAGMA_GROWTH)));
//					}
//					chunkGenerator.biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(parameters));
//					chunkGenerator.featuresPerStep = Suppliers
//							.memoize(() -> FeatureSorter.buildFeaturesPerStep(List.copyOf(chunkGenerator.biomeSource.possibleBiomes()), biome -> chunkGenerator.generationSettingsGetter.apply(biome).features(), true));
//				}
				if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
//					NoiseGeneratorSettings noiseGeneratorSettings = noiseGenerator.settings.value();
//					SurfaceRules.RuleSource currentRuleSource = noiseGeneratorSettings.surfaceRule();
//					if (currentRuleSource instanceof SurfaceRules.SequenceRuleSource sequenceRuleSource) {
//						List<SurfaceRules.RuleSource> surfaceRules = new ArrayList<>(sequenceRuleSource.sequence());
//						surfaceRules.add(1, anySurfaceRule(CAVERN_OF_DEEP, VWBlocks.DEEP_MOSS.get().defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState(),
//								Blocks.BLACKSTONE.defaultBlockState()));
//						surfaceRules.add(1, anySurfaceRule(CAVERNS_OF_MAGMA_GROWTH, VWBlocks.BLACKLY_STONY_MAGMA.get().defaultBlockState(),
//								VWBlocks.BLACKLY_STONY_MAGMA.get().defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState()));
//						surfaceRules.add(1, preliminarySurfaceRule(CRYSTALIC_FOREST, VWBlocks.CRYSTALIC_GRASS.get().defaultBlockState(),
//								Blocks.STONE.defaultBlockState(), Blocks.STONE.defaultBlockState()));
//						surfaceRules.add(1, preliminarySurfaceRule(PEACEFUL_WASTELAND, Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState(),
//								Blocks.SAND.defaultBlockState()));
//						surfaceRules.add(1, preliminarySurfaceRule(SCULK_VALLEY, VWBlocks.SCULK_GRASS.get().defaultBlockState(),
//								Blocks.SCULK.defaultBlockState(), Blocks.STONE.defaultBlockState()));
//						surfaceRules.add(1, preliminarySurfaceRule(SHINY_VALLEY, VWBlocks.SHINY_GRASS.get().defaultBlockState(),
//								VWBlocks.GNEISS.get().defaultBlockState(), VWBlocks.GNEISS.get().defaultBlockState()));
//						NoiseGeneratorSettings moddedNoiseGeneratorSettings = new NoiseGeneratorSettings(noiseGeneratorSettings.noiseSettings(), noiseGeneratorSettings.defaultBlock(), noiseGeneratorSettings.defaultFluid(),
//								noiseGeneratorSettings.noiseRouter(), SurfaceRules.sequence(surfaceRules.toArray(SurfaceRules.RuleSource[]::new)), noiseGeneratorSettings.spawnTarget(), noiseGeneratorSettings.seaLevel(),
//								noiseGeneratorSettings.disableMobGeneration(), noiseGeneratorSettings.aquifersEnabled(), noiseGeneratorSettings.oreVeinsEnabled(),noiseGeneratorSettings.useLegacyRandomSource());
//						noiseGenerator.settings = new Holder.Direct<>(moddedNoiseGeneratorSettings);
//					}
				}
			}
		}
	}
	private static SurfaceRules.RuleSource preliminarySurfaceRule(ResourceKey<Biome> biomeKey, BlockState groundBlock, BlockState undergroundBlock, BlockState underwaterBlock) {
		return SurfaceRules.ifTrue(SurfaceRules.isBiome(biomeKey),
				SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
						SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), SurfaceRules.state(groundBlock)), SurfaceRules.state(underwaterBlock))),
								SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(undergroundBlock)))));
	}

	private static SurfaceRules.RuleSource anySurfaceRule(ResourceKey<Biome> biomeKey, BlockState groundBlock, BlockState undergroundBlock, BlockState underwaterBlock) {
		return SurfaceRules.ifTrue(SurfaceRules.isBiome(biomeKey),
				SurfaceRules.sequence(
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
								SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), SurfaceRules.state(groundBlock)), SurfaceRules.state(underwaterBlock))),
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(undergroundBlock))));
	}
}