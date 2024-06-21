package net.sashakyotoz.variousworld.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.block.*;
import net.sashakyotoz.variousworld.block.signs.ModStandingSignBlock;
import net.sashakyotoz.variousworld.block.signs.ModWallSignBlock;
import net.sashakyotoz.variousworld.block.signs.ModWoodType;
import net.sashakyotoz.variousworld.world.treegrowers.CrystalicOakTreeGrower;
import net.sashakyotoz.variousworld.world.treegrowers.MagnoliaTreeGrower;
import net.sashakyotoz.variousworld.world.treegrowers.SculkTreeGrower;
import net.sashakyotoz.variousworld.world.treegrowers.ShinyTreeGrower;

public class VariousWorldBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VariousWorld.MODID);
	public static final RegistryObject<Block> CRYSTAL_CLUSTER = BLOCKS.register("crystal_cluster", () -> new CrystalClusterBlock());
	public static final RegistryObject<Block> SMALL_CRYSTAL_CLUSTER = BLOCKS.register("small_crystal_cluster", () -> new SmallCrystalClusterBlock());
	public static final RegistryObject<Block> KUNZITE_COLOURFUL_CRYSTAL = BLOCKS.register("kunzite_colourful_crystal", () -> new KunziteColourfulCrystalBlock());
	public static final RegistryObject<Block> BUDDING_KUNZITE_COLOURFUL_CRYSTAL = BLOCKS.register("budding_kunzite_colourful_crystal", () -> new BuddingKunziteColourfulCrystalBlock());
	public static final RegistryObject<Block> CRYSTAL_LEAVES = BLOCKS.register("crystal_leaves", () -> new CrystalLeavesBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_WOOD = BLOCKS.register("crystalic_oak_wood", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 3f)));
	public static final RegistryObject<Block> CRYSTALIC_OAK_PLANKS = BLOCKS.register("crystalic_oak_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4f)));
	public static final RegistryObject<Block> CRYSTALIC_OAK_LOG = BLOCKS.register("crystalic_oak_log", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(CRYSTALIC_OAK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
	public static final RegistryObject<Block> CRYSTALIC_OAK_STAIRS = BLOCKS.register("crystalic_oak_stairs", () -> new CrystalicOakStairsBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_SLAB = BLOCKS.register("crystalic_oak_slab", () -> new CrystalicOakSlabBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_FENCE = BLOCKS.register("crystalic_oak_fence", () -> new CrystalicOakFenceBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_PLANKS_TRAPDOOR = BLOCKS.register("crystalic_oak_planks_trapdoor", () -> new TrapdoorLikeBlock(BlockBehaviour.Properties.copy(CRYSTALIC_OAK_PLANKS.get()).sound(SoundType.WOOD).strength(2f, 7f).noOcclusion().dynamicShape()));
	public static final RegistryObject<Block> CRYSTALIC_OAK_DOOR = BLOCKS.register("crystalic_oak_door", () -> new CrystalicOakDoorBlock());
	public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register("sakura_leaves", () -> new SakuraLeavesBlock());
	public static final RegistryObject<Block> SAKURA_PLANKS = BLOCKS.register("sakura_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f)));
	public static final RegistryObject<Block> SAKURA_LOG = BLOCKS.register("sakura_log", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.7f)));
	public static final RegistryObject<Block> SAKURA_WOOD = BLOCKS.register("sakura_wood", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.5f)));
	public static final RegistryObject<Block> SAKURA_STAIRS = BLOCKS.register("sakura_stairs", () -> new SakuraStairsBlock());
	public static final RegistryObject<Block> SAKURA_SLAB = BLOCKS.register("sakura_slab", () -> new SakuraSlabBlock());
	public static final RegistryObject<Block> SAKURA_FENCE = BLOCKS.register("sakura_fence", () -> new SakuraFenceBlock());
	public static final RegistryObject<Block> SAKURA_PLANKS_TRAPDOOR = BLOCKS.register("sakura_planks_trapdoor", () -> new TrapdoorLikeBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 4f).noOcclusion().dynamicShape()));
	public static final RegistryObject<Block> SAKURA_DOOR = BLOCKS.register("sakura_door", () -> new SakuraDoorBlock());
	public static final RegistryObject<Block> SCULK_LEAVES = BLOCKS.register("sculk_leaves", () -> new SculkLeavesBlock());
	public static final RegistryObject<Block> SCULK_PLANKS = BLOCKS.register("sculk_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_LOG = BLOCKS.register("sculk_log", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).lightLevel(s -> 3).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_WOOD = BLOCKS.register("sculk_wood", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_STAIRS = BLOCKS.register("sculk_stairs", () -> new SculkStairsBlock());
	public static final RegistryObject<Block> SCULK_SLAB = BLOCKS.register("sculk_slab", () -> new SculkSlabBlock());
	public static final RegistryObject<Block> SCULK_FENCE = BLOCKS.register("sculk_fence", () -> new SculkFenceBlock());
	public static final RegistryObject<Block> SCULK_PLANKS_TRAPDOOR = BLOCKS.register("sculk_planks_trapdoor", () -> new TrapdoorLikeBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.SCULK_VEIN).strength(3f, 4f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).noOcclusion().dynamicShape()));
	public static final RegistryObject<Block> SCULK_DOOR = BLOCKS.register("sculk_door", () -> new SculkDoorBlock());
	private static final BlockBehaviour.Properties GNEISS_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.POLISHED_DEEPSLATE).strength(4f).lightLevel(s -> 4).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true);
	public static final RegistryObject<Block> GNEISS = BLOCKS.register("gneiss", () -> new Block(GNEISS_PROPERTIES));
	public static final RegistryObject<Block> COBBLED_GNEISS = BLOCKS.register("cobbled_gneiss", () -> new Block(GNEISS_PROPERTIES));
	public static final RegistryObject<Block> GNEISS_BRICKS = BLOCKS.register("gneiss_bricks", () -> new RotatedPillarBlock(GNEISS_PROPERTIES));
	public static final RegistryObject<Block> GNEISS_BRICKS_STAIRS = BLOCKS.register("gneiss_bricks_stairs", () -> new StairBlock(()-> GNEISS.get().defaultBlockState(),GNEISS_PROPERTIES));
	public static final RegistryObject<Block> GNEISS_BRICKS_SLAB = BLOCKS.register("gneiss_bricks_slab", () -> new SlabBlock(GNEISS_PROPERTIES));
	public static final RegistryObject<Block> GNEISS_BRICKS_WALL = BLOCKS.register("gneiss_bricks_wall", () -> new WallBlock(GNEISS_PROPERTIES));
	private static final BlockBehaviour.Properties BLACKLY_MAGMA_PROPERTIES =BlockBehaviour.Properties.of().sound(VariousWorldSounds.STONY_MAGMA).strength(5f).requiresCorrectToolForDrops();
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA = BLOCKS.register("blackly_stony_magma", () -> new ModFacingableBlock(BLACKLY_MAGMA_PROPERTIES));
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS = BLOCKS.register("blackly_stony_magma_bricks", () -> new ModFacingableBlock(BLACKLY_MAGMA_PROPERTIES));
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_STAIRS = BLOCKS.register("blackly_stony_magma_bricks_stairs", () -> new StairBlock(()-> BLACKLY_STONY_MAGMA_BRICKS.get().defaultBlockState(),BLACKLY_MAGMA_PROPERTIES));
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_SLAB = BLOCKS.register("blackly_stony_magma_bricks_slab", () -> new SlabBlock(BLACKLY_MAGMA_PROPERTIES));
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_WALL = BLOCKS.register("blackly_stony_magma_bricks_wall", () -> new WallBlock(BLACKLY_MAGMA_PROPERTIES));
	private static final BlockBehaviour.Properties ENDER_BRICKS_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops().friction(0.5f);
	public static final RegistryObject<Block> ENDER_BRICKS = BLOCKS.register("ender_bricks", ()->new ModFacingableBlock(ENDER_BRICKS_PROPERTIES));
	public static final RegistryObject<Block> ENDER_BRICKS_STAIRS = BLOCKS.register("ender_bricks_stairs", () -> new StairBlock(()->ENDER_BRICKS.get().defaultBlockState(),ENDER_BRICKS_PROPERTIES));
	public static final RegistryObject<Block> ENDER_BRICKS_SLAB = BLOCKS.register("ender_bricks_slab", () -> new SlabBlock(ENDER_BRICKS_PROPERTIES));
	public static final RegistryObject<Block> ENDER_BRICKS_WALL = BLOCKS.register("ender_bricks_wall", () -> new WallBlock(ENDER_BRICKS_PROPERTIES));
	private static final BlockBehaviour.Properties SCULK_BRICKS_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.SCULK_CATALYST).strength(2.5f, 7.5f).lightLevel(s -> 3).requiresCorrectToolForDrops();
	public static final RegistryObject<Block> SCULK_BRICKS = BLOCKS.register("sculk_bricks", () -> new ModFacingableBlock(SCULK_BRICKS_PROPERTIES));
	public static final RegistryObject<Block> SCULK_BRICK_STAIRS = BLOCKS.register("sculk_brick_stairs", () -> new StairBlock(()->SCULK_BRICKS.get().defaultBlockState(),SCULK_BRICKS_PROPERTIES));
	public static final RegistryObject<Block> SCULK_BRICKS_SLAB = BLOCKS.register("sculk_bricks_slab", () -> new SlabBlock(SCULK_BRICKS_PROPERTIES));
	public static final RegistryObject<Block> SCULK_BRICKS_WALL = BLOCKS.register("sculk_bricks_fence", () -> new WallBlock(SCULK_BRICKS_PROPERTIES));
	public static final RegistryObject<Block> ROSE_QUARTZ = BLOCKS.register("rose_quartz", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(2.5f).lightLevel(s -> 3).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ROSE_QUARTZ_SLAB = BLOCKS.register("rose_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(2.5f).lightLevel(s -> 3).requiresCorrectToolForDrops().noOcclusion().dynamicShape()));
	public static final RegistryObject<Block> ROSE_QUARTZ_STAIRS = BLOCKS.register("rose_quartz_stairs", () -> new StairBlock(ROSE_QUARTZ.get()::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(2.5f).lightLevel(s -> 3).requiresCorrectToolForDrops().dynamicShape()));
	public static final RegistryObject<Block> CRYSTALIC_GRASS = BLOCKS.register("crystalic_grass", () -> new GrassLikeBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).sound(SoundType.ROOTED_DIRT).strength(2f, 5f).lightLevel(s -> 3)));
	public static final RegistryObject<Block> SHINY_GRASS = BLOCKS.register("shiny_grass", () -> new GrassLikeBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.WET_GRASS).strength(1f, 5f).lightLevel(s -> 8).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_GRASS = BLOCKS.register("sculk_grass", () -> new GrassLikeBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK_VEIN).strength(1f, 5f).lightLevel(s -> 3).friction(0.5f).speedFactor(0.8f)));
	public static final RegistryObject<Block> SCULK_MAGMA = BLOCKS.register("sculk_magma", SculkMagmaBlock::new);
	public static final RegistryObject<Block> SCULK_MOSS_BLOCK = BLOCKS.register("sculk_moss_block", SculkMossBlock::new);
	public static final RegistryObject<Block> DEEP_MOSS = BLOCKS.register("deep_moss", DeepMossBlock::new);
	public static final RegistryObject<Block> CRYSTALIC_SLIME_BLOCK = BLOCKS.register("crystalic_slime_block", CrystalicSlimeBlock::new);
	public static final RegistryObject<Block> LORD_FURY_HEAD = BLOCKS.register("lord_fury_head", LordFuryHeadBlock::new);
	public static final RegistryObject<Block> ARTIFACTTABLE = BLOCKS.register("artifacttable", ArtifactTableBlock::new);
	public static final RegistryObject<Block> ARMOR_STATION_BLOCK = BLOCKS.register("armor_station_block", ArmorStationBlock::new);
	public static final RegistryObject<Block> DISENCHANT_TABLE = BLOCKS.register("disenchant_table", DisenchantTableBlock::new);
	public static final RegistryObject<Block> MYCOLOCYFAROGRAPH = BLOCKS.register("mycolocyfarograph", MycolocyfarographBlock::new);
	public static final RegistryObject<Block> CRYSTAL_BLOCK = BLOCKS.register("crystal_block", ()->new CrystalLikeBlock(false));
	public static final RegistryObject<Block> CRYSTAL_OF_CHARGED_BLOCK = BLOCKS.register("crystal_of_changed_block", ()->new CrystalLikeBlock(true));
	public static final RegistryObject<Block> SCULK_GEM_ORE = BLOCKS.register("sculk_gem_ore", () -> new ModOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.SCULK_SENSOR).strength(5f).requiresCorrectToolForDrops(), UniformInt.of(1,4)));
	public static final RegistryObject<Block> DEEPSLATE_SCULK_GEM_ORE = BLOCKS.register("deepslate_sculk_gem_ore", () -> new ModOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.SCULK_SENSOR).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(2,4)));
	public static final RegistryObject<Block> SCULK_GEM_BLOCK = BLOCKS.register("sculk_gem_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(7.5f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DARKNIUM_ORE = BLOCKS.register("darknium_ore", () -> new ModOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(7.5f).requiresCorrectToolForDrops(),UniformInt.of(0,3)));
	public static final RegistryObject<Block> DEEPSLATE_DARKNIUM_ORE = BLOCKS.register("deepslate_darknium_ore", () -> new ModOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(8.5f).requiresCorrectToolForDrops(),UniformInt.of(1,3)));
	public static final RegistryObject<Block> DARKNIUM_BLOCK = BLOCKS.register("darknium_block", () -> new ModFacingableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LORD_FURY_SCALES_BLOCK = BLOCKS.register("lord_fury_scales_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(7.5f).lightLevel(s -> 2).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MAGIC_VINES = BLOCKS.register("magic_vines", MagicVinesBlock::new);
	public static final RegistryObject<Block> FLOWER_VINE_FROM_CAVERNOF_DEEP = BLOCKS.register("flower_vine_from_cavernof_deep", FlowerVineFromCavernOfDeepBlock::new);
	public static final RegistryObject<Block> SMALL_SCULK_BUSH = BLOCKS.register("small_sculk_bush", SmallSculkBushBlock::new);
	public static final RegistryObject<Block> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = BLOCKS.register("underground_sculk_bush_without_fruit", UndergroundSculkBushWithoutFruitBlock::new);
	public static final RegistryObject<Block> SCULK_GROWTHS = BLOCKS.register("sculk_growths", SculkGrowthsBlock::new);
	public static final RegistryObject<Block> PURPLE_SAFFRON = BLOCKS.register("purple_saffron", PurpleSaffronBlock::new);
	public static final RegistryObject<Block> SHINY_PLUMERIA = BLOCKS.register("shiny_plumeria", ShinyPlumeriaBlock::new);
	public static final RegistryObject<Block> ANTHURIUM_SPROUTED_OF_MAGMA = BLOCKS.register("anthurium_sprouted_of_magma", AnthuriumSproutedOfMagmaBlock::new);
	public static final RegistryObject<Block> BIG_CRYSHROOM_BLOCK = BLOCKS.register("big_cryshroom_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK).sound(SoundType.SHROOMLIGHT).strength(1.5f, 7f).lightLevel(s -> 8)));
	public static final RegistryObject<Block> MYCENA_FROM_CAVERN_OF_DEEP_BLOCK = BLOCKS.register("mycena_from_cavern_of_deep_block", MycenaFromCavernOfDeepBlock::new);
	public static final RegistryObject<Block> SCULK_SAPLING = BLOCKS.register("sculk_sapling", () -> new ModSaplingBlock(new SculkTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().lightLevel(s -> 3).noCollission()));
	public static final RegistryObject<Block> CRYSTALIC_OAK_SAPLING = BLOCKS.register("crystalic_oak_sapling", () -> new ModSaplingBlock(new CrystalicOakTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().lightLevel(s -> 5).noCollission()));
	public static final RegistryObject<Block> MAGNOLIA_SAPLING = BLOCKS.register("magnolia_sapling", () -> new ModSaplingBlock(new MagnoliaTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().noCollission()));
	public static final RegistryObject<Block> SHINY_SAPLING = BLOCKS.register("shiny_sapling",() -> new ModSaplingBlock(new ShinyTreeGrower(), BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak().lightLevel(s -> 3).noCollission()));
	public static final RegistryObject<Block> SAKURA_FENCE_GATE = BLOCKS.register("sakura_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape(), WoodType.OAK));
	public static final RegistryObject<Block> SAKURA_PRESSURE_PLATE = BLOCKS.register("sakura_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape().ignitedByLava(), BlockSetType.OAK));
	public static final RegistryObject<Block> SAKURA_BUTTON = BLOCKS.register("sakura_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape(), BlockSetType.OAK, 30, true));
	public static final RegistryObject<Block> SAKURA_SIGN = BLOCKS.register("sakura_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodType.MAGNOLIA));
	public static final RegistryObject<Block> SAKURA_WALL_SIGN = BLOCKS.register("sakura_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).lootFrom(SAKURA_SIGN), ModWoodType.MAGNOLIA));
	public static final RegistryObject<Block> SCULK_FENCE_GATE = BLOCKS.register("sculk_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 3.5f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape(), WoodType.OAK));
	public static final RegistryObject<Block> SCULK_PRESSURE_PLATE = BLOCKS.register("sculk_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
			BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4.5f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape(), BlockSetType.OAK));
	public static final RegistryObject<Block> SCULK_BUTTON = BLOCKS.register("sculk_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4.5f).lightLevel(s -> 1).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape(),
			BlockSetType.OAK, 30, true));
	public static final RegistryObject<Block> SCULK_SIGN = BLOCKS.register("sculk_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodType.SCULK));
	public static final RegistryObject<Block> SCULK_WALL_SIGN = BLOCKS.register("sculk_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).lootFrom(SCULK_SIGN), ModWoodType.SCULK));
	public static final RegistryObject<Block> CRYSTALIC_OAK_FENCE_GATE = BLOCKS.register("crystalic_oak_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).dynamicShape(), WoodType.OAK));
	public static final RegistryObject<Block> CRYSTALIC_OAK_PRESSURE_PLATE = BLOCKS.register("crystalic_oak_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).dynamicShape(), BlockSetType.OAK));
	public static final RegistryObject<Block> CRYSTALIC_OAK_BUTTON = BLOCKS.register("crystalic_oak_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).requiresCorrectToolForDrops().dynamicShape(), BlockSetType.OAK, 30, true));
	public static final RegistryObject<Block> CRYSTALIC_OAK_SIGN = BLOCKS.register("crystalic_oak_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodType.CRYSTALIC_OAK));
	public static final RegistryObject<Block> CRYSTALIC_OAK_WALL_SIGN = BLOCKS.register("crystalic_oak_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).lootFrom(CRYSTALIC_OAK_SIGN), ModWoodType.CRYSTALIC_OAK));
	public static final RegistryObject<Block> QUARTZ_STAND = BLOCKS.register("quartz_stand", () -> new StandBlock(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.AMETHYST).strength(-1, 3600000).lightLevel(s -> 1).noOcclusion().hasPostProcess((bs, br, bp) -> true)
			.emissiveRendering((bs, br, bp) -> true).noLootTable()));
	public static final RegistryObject<Block> AIR_STAND = BLOCKS.register("air_stand", () -> new StandBlock(BlockBehaviour.Properties.copy(Blocks.BUBBLE_COLUMN).sound(SoundType.BONE_BLOCK).strength(-1, 3600000).lightLevel(s -> 10).noOcclusion().noLootTable()));
	public static final RegistryObject<Block> LORD_FURY_STAND = BLOCKS.register("lord_fury_stand", () -> new StandBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.METAL).strength(-1, 3600000).lightLevel(s -> 3).noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).noLootTable()));
	public static final RegistryObject<Block> SCULK_NECROMANCER_BLOCK_SPAWNER = BLOCKS.register("sculk_necromancer_block_spawner", () -> new StandBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.METAL).strength(-1, 3600000).lightLevel(s -> 4).noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).noLootTable()));
	public static final RegistryObject<Block> SCULK_BUSH = BLOCKS.register("sculk_bush", SculkBushBlock::new);
	public static final RegistryObject<Block> SCULK_BUSH_WITHOUT_BERRY = BLOCKS.register("sculk_bush_without_berry", SculkBushWithoutBerryBlock::new);
	public static final RegistryObject<Block> UNDERGROUND_SCULK_FRUIT_BUSH = BLOCKS.register("underground_sculk_fruit_bush", UndergroundSculkFruitBushBlock::new);
	public static final RegistryObject<Block> VINE_FROM_CAVERNOF_DEEP = BLOCKS.register("vine_from_cavernof_deep", VineFromCavernofDeepBlock::new);
	public static final RegistryObject<Block> MYCENA_FROM_CAVERN_OF_DEEP = BLOCKS.register("mycena_from_cavern_of_deep", MycenaFromCavernOfDeep::new);
	public static final RegistryObject<Block> FLOWER_DEEP_MOSS = BLOCKS.register("flower_deep_moss", FlowerDeepMossBlock::new);
	public static final RegistryObject<Block> MUSHROOM_SPAWNER = BLOCKS.register("mushroom_spawner", MushroomSpawnerBlock::new);
	public static final RegistryObject<Block> CRYSHROOM_PLANT = BLOCKS.register("cryshroom_plant", CryshroomPlantBlock::new);

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			CrystalLikeBlock.blockColorLoad(event);
			SmallSculkBushBlock.blockColorLoad(event);
			AnthuriumSproutedOfMagmaBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			AnthuriumSproutedOfMagmaBlock.itemColorLoad(event);
		}
	}
}
