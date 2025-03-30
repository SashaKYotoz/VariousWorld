package net.sashakyotoz.variousworld.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.block.*;
import net.sashakyotoz.variousworld.block.signs.*;
import net.sashakyotoz.variousworld.world.treegrowers.CrystalicOakTreeGrower;
import net.sashakyotoz.variousworld.world.treegrowers.MagnoliaTreeGrower;
import net.sashakyotoz.variousworld.world.treegrowers.SculkTreeGrower;
import net.sashakyotoz.variousworld.world.treegrowers.ShinyTreeGrower;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.*;

public class VWBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VariousWorld.MODID);
    public static final DeferredBlock<CrystalClusterBlock> CRYSTAL_CLUSTER = registerBlock("crystal_cluster", () -> new CrystalClusterBlock());
    public static final DeferredBlock<SmallCrystalClusterBlock> SMALL_CRYSTAL_CLUSTER = registerBlock("small_crystal_cluster", () -> new SmallCrystalClusterBlock());
    public static final DeferredBlock<KunziteColourfulCrystalBlock> KUNZITE_COLOURFUL_CRYSTAL = registerBlock("kunzite_colourful_crystal", () -> new KunziteColourfulCrystalBlock());
    public static final DeferredBlock<BuddingKunziteColourfulCrystalBlock> BUDDING_KUNZITE_COLOURFUL_CRYSTAL = registerBlock("budding_kunzite_colourful_crystal", () -> new BuddingKunziteColourfulCrystalBlock());
    public static final DeferredBlock<CrystalLeavesBlock> CRYSTAL_LEAVES = registerBlock("crystal_leaves", () -> new CrystalLeavesBlock());
    public static final DeferredBlock<LogLikeBlock> CRYSTALIC_OAK_WOOD = registerBlock("crystalic_oak_wood", () -> new LogLikeBlock(Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 3f)));
    public static final DeferredBlock<Block> CRYSTALIC_OAK_PLANKS = registerBlock("crystalic_oak_planks", () -> new Block(Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4f)));
    public static final DeferredBlock<LogLikeBlock> CRYSTALIC_OAK_LOG = registerBlock("crystalic_oak_log", () -> new LogLikeBlock(Properties.ofFullCopy(CRYSTALIC_OAK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final DeferredBlock<CrystalicOakStairsBlock> CRYSTALIC_OAK_STAIRS = registerBlock("crystalic_oak_stairs", () -> new CrystalicOakStairsBlock());
    public static final DeferredBlock<CrystalicOakSlabBlock> CRYSTALIC_OAK_SLAB = registerBlock("crystalic_oak_slab", () -> new CrystalicOakSlabBlock());
    public static final DeferredBlock<CrystalicOakFenceBlock> CRYSTALIC_OAK_FENCE = registerBlock("crystalic_oak_fence", () -> new CrystalicOakFenceBlock());
    public static final DeferredBlock<TrapdoorLikeBlock> CRYSTALIC_OAK_PLANKS_TRAPDOOR = registerBlock("crystalic_oak_planks_trapdoor", () -> new TrapdoorLikeBlock(Properties.ofFullCopy(CRYSTALIC_OAK_PLANKS.get()).sound(SoundType.WOOD).strength(2f, 7f).noOcclusion().dynamicShape()));
    public static final RegistryObject<Block> CRYSTALIC_OAK_DOOR = registerDoubleBlockItem("crystalic_oak_door", () -> new CrystalicOakDoorBlock());
    public static final DeferredBlock<SakuraLeavesBlock> SAKURA_LEAVES = registerBlock("sakura_leaves", () -> new SakuraLeavesBlock());
    public static final DeferredBlock<Block> SAKURA_PLANKS = registerBlock("sakura_planks", () -> new Block(Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f)));
    public static final DeferredBlock<LogLikeBlock> SAKURA_LOG = registerBlock("sakura_log", () -> new LogLikeBlock(Properties.ofFullCopy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.7f)));
    public static final DeferredBlock<LogLikeBlock> SAKURA_WOOD = registerBlock("sakura_wood", () -> new LogLikeBlock(Properties.ofFullCopy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.5f)));
    public static final DeferredBlock<SakuraStairsBlock> SAKURA_STAIRS = registerBlock("sakura_stairs", () -> new SakuraStairsBlock());
    public static final DeferredBlock<SakuraSlabBlock> SAKURA_SLAB = registerBlock("sakura_slab", () -> new SakuraSlabBlock());
    public static final DeferredBlock<SakuraFenceBlock> SAKURA_FENCE = registerBlock("sakura_fence", () -> new SakuraFenceBlock());
    public static final DeferredBlock<TrapdoorLikeBlock> SAKURA_PLANKS_TRAPDOOR = registerBlock("sakura_planks_trapdoor", () -> new TrapdoorLikeBlock(Properties.ofFullCopy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 4f).noOcclusion().dynamicShape()));
    public static final DeferredItem<Item> SAKURA_DOOR = registerDoubleBlockItem("sakura_door", () -> new SakuraDoorBlock());
    public static final DeferredBlock<SculkLeavesBlock> SCULK_LEAVES = registerBlock("sculk_leaves", () -> new SculkLeavesBlock());
    public static final DeferredBlock<Block> SCULK_PLANKS = registerBlock("sculk_planks", () -> new Block(Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
    public static final DeferredBlock<LogLikeBlock> SCULK_LOG = registerBlock("sculk_log", () -> new LogLikeBlock(Properties.ofFullCopy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).lightLevel(s -> 3).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
    public static final DeferredBlock<LogLikeBlock> SCULK_WOOD = registerBlock("sculk_wood", () -> new LogLikeBlock(Properties.ofFullCopy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
    public static final DeferredBlock<SculkStairsBlock> SCULK_STAIRS = registerBlock("sculk_stairs", () -> new SculkStairsBlock());
    public static final DeferredBlock<SculkSlabBlock> SCULK_SLAB = registerBlock("sculk_slab", () -> new SculkSlabBlock());
    public static final DeferredBlock<SculkFenceBlock> SCULK_FENCE = registerBlock("sculk_fence", () -> new SculkFenceBlock());
    public static final DeferredBlock<TrapdoorLikeBlock> SCULK_PLANKS_TRAPDOOR = registerBlock("sculk_planks_trapdoor", () -> new TrapdoorLikeBlock(Properties.ofFullCopy(SCULK_PLANKS.get()).sound(SoundType.SCULK_VEIN).strength(3f, 4f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).noOcclusion().dynamicShape()));
    public static final RegistryObject<Block> SCULK_DOOR = registerDoubleBlockItem("sculk_door", () -> new SculkDoorBlock());
    private static final Properties GNEISS_PROPERTIES = Properties.ofFullCopy(Blocks.STONE).sound(SoundType.POLISHED_DEEPSLATE).strength(4f).lightLevel(s -> 4).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true);
    public static final DeferredBlock<Block> GNEISS = registerBlock("gneiss", () -> new Block(GNEISS_PROPERTIES));
    public static final DeferredBlock<Block> COBBLED_GNEISS = registerBlock("cobbled_gneiss", () -> new Block(GNEISS_PROPERTIES));
    public static final DeferredBlock<RotatedPillarBlock> GNEISS_BRICKS = registerBlock("gneiss_bricks", () -> new RotatedPillarBlock(GNEISS_PROPERTIES));
    public static final DeferredBlock<StairBlock> GNEISS_BRICKS_STAIRS = registerBlock("gneiss_bricks_stairs", () -> new StairBlock(GNEISS.get().defaultBlockState(), GNEISS_PROPERTIES));
    public static final DeferredBlock<SlabBlock> GNEISS_BRICKS_SLAB = registerBlock("gneiss_bricks_slab", () -> new SlabBlock(GNEISS_PROPERTIES));
    public static final DeferredBlock<WallBlock> GNEISS_BRICKS_WALL = registerBlock("gneiss_bricks_wall", () -> new WallBlock(GNEISS_PROPERTIES));
    private static final Properties BLACKLY_MAGMA_PROPERTIES = Properties.of().sound(VWSounds.STONY_MAGMA).strength(5f).requiresCorrectToolForDrops();
    public static final DeferredBlock<ModFacingableBlock> BLACKLY_STONY_MAGMA = registerBlock("blackly_stony_magma", () -> new ModFacingableBlock(BLACKLY_MAGMA_PROPERTIES));
    public static final DeferredBlock<ModFacingableBlock> BLACKLY_STONY_MAGMA_BRICKS = registerBlock("blackly_stony_magma_bricks", () -> new ModFacingableBlock(BLACKLY_MAGMA_PROPERTIES));
    public static final DeferredBlock<StairBlock> BLACKLY_STONY_MAGMA_BRICKS_STAIRS = registerBlock("blackly_stony_magma_bricks_stairs", () -> new StairBlock(BLACKLY_STONY_MAGMA_BRICKS.get().defaultBlockState(), BLACKLY_MAGMA_PROPERTIES));
    public static final DeferredBlock<SlabBlock> BLACKLY_STONY_MAGMA_BRICKS_SLAB = registerBlock("blackly_stony_magma_bricks_slab", () -> new SlabBlock(BLACKLY_MAGMA_PROPERTIES));
    public static final DeferredBlock<WallBlock> BLACKLY_STONY_MAGMA_BRICKS_WALL = registerBlock("blackly_stony_magma_bricks_wall", () -> new WallBlock(BLACKLY_MAGMA_PROPERTIES));
    private static final Properties ENDER_BRICKS_PROPERTIES = Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops().friction(0.5f);
    public static final DeferredBlock<ModFacingableBlock> ENDER_BRICKS = registerBlock("ender_bricks", () -> new ModFacingableBlock(ENDER_BRICKS_PROPERTIES));
    public static final DeferredBlock<StairBlock> ENDER_BRICKS_STAIRS = registerBlock("ender_bricks_stairs", () -> new StairBlock(ENDER_BRICKS.get().defaultBlockState(), ENDER_BRICKS_PROPERTIES));
    public static final DeferredBlock<SlabBlock> ENDER_BRICKS_SLAB = registerBlock("ender_bricks_slab", () -> new SlabBlock(ENDER_BRICKS_PROPERTIES));
    public static final DeferredBlock<WallBlock> ENDER_BRICKS_WALL = registerBlock("ender_bricks_wall", () -> new WallBlock(ENDER_BRICKS_PROPERTIES));
    private static final Properties SCULK_BRICKS_PROPERTIES = Properties.ofFullCopy(Blocks.STONE).sound(SoundType.SCULK_CATALYST).strength(2.5f, 7.5f).lightLevel(s -> 3).requiresCorrectToolForDrops();
    public static final DeferredBlock<ModFacingableBlock> SCULK_BRICKS = registerBlock("sculk_bricks", () -> new ModFacingableBlock(SCULK_BRICKS_PROPERTIES));
    public static final DeferredBlock<StairBlock> SCULK_BRICK_STAIRS = registerBlock("sculk_brick_stairs", () -> new StairBlock(SCULK_BRICKS.get().defaultBlockState(), SCULK_BRICKS_PROPERTIES));
    public static final DeferredBlock<SlabBlock> SCULK_BRICKS_SLAB = registerBlock("sculk_bricks_slab", () -> new SlabBlock(SCULK_BRICKS_PROPERTIES));
    public static final DeferredBlock<WallBlock> SCULK_BRICKS_WALL = registerBlock("sculk_bricks_fence", () -> new WallBlock(SCULK_BRICKS_PROPERTIES));
    public static final DeferredBlock<RotatedPillarBlock> ROSE_QUARTZ = registerBlock("rose_quartz", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(2.5f).lightLevel(s -> 3).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> ROSE_QUARTZ_SLAB = registerBlock("rose_quartz_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(2.5f).lightLevel(s -> 3).requiresCorrectToolForDrops().noOcclusion().dynamicShape()));
    public static final DeferredBlock<StairBlock> ROSE_QUARTZ_STAIRS = registerBlock("rose_quartz_stairs", () -> new StairBlock(ROSE_QUARTZ.get().defaultBlockState(), Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(2.5f).lightLevel(s -> 3).requiresCorrectToolForDrops().dynamicShape()));
    public static final DeferredBlock<GrassLikeBlock> CRYSTALIC_GRASS = registerBlock("crystalic_grass", () -> new GrassLikeBlock(Properties.ofFullCopy(Blocks.COARSE_DIRT).sound(SoundType.ROOTED_DIRT).strength(2f, 5f).lightLevel(s -> 3)));
    public static final DeferredBlock<GrassLikeBlock> SHINY_GRASS = registerBlock("shiny_grass", () -> new GrassLikeBlock(Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.WET_GRASS).strength(1f, 5f).lightLevel(s -> 8).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
    public static final DeferredBlock<GrassLikeBlock> SCULK_GRASS = registerBlock("sculk_grass", () -> new GrassLikeBlock(Properties.ofFullCopy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK_VEIN).strength(1f, 5f).lightLevel(s -> 3).friction(0.5f).speedFactor(0.8f)));
    public static final DeferredBlock<SculkMagmaBlock> SCULK_MAGMA = registerBlock("sculk_magma", SculkMagmaBlock::new);
    public static final DeferredBlock<SculkMossBlock> SCULK_MOSS_BLOCK = registerBlock("sculk_moss_block", SculkMossBlock::new);
    public static final DeferredBlock<DeepMossBlock> DEEP_MOSS = registerBlock("deep_moss", DeepMossBlock::new);
    public static final DeferredBlock<CrystalicSlimeBlock> CRYSTALIC_SLIME_BLOCK = registerBlock("crystalic_slime_block", CrystalicSlimeBlock::new);
    public static final DeferredBlock<LordFuryHeadBlock> LORD_FURY_HEAD = registerBlock("lord_fury_head", LordFuryHeadBlock::new);
    public static final DeferredBlock<ArtifactTableBlock> ARTIFACTTABLE = registerBlock("artifacttable", ArtifactTableBlock::new);
    public static final DeferredBlock<ArmorStationBlock> ARMOR_STATION_BLOCK = registerBlock("armor_station_block", ArmorStationBlock::new);
    public static final DeferredBlock<DisenchantTableBlock> DISENCHANT_TABLE = registerBlock("disenchant_table", DisenchantTableBlock::new);
    public static final DeferredBlock<MycolocyfarographBlock> MYCOLOCYFAROGRAPH = registerBlock("mycolocyfarograph", MycolocyfarographBlock::new);
    public static final DeferredBlock<CrystalLikeBlock> CRYSTAL_BLOCK = registerBlock("crystal_block", () -> new CrystalLikeBlock(false));
    public static final DeferredBlock<CrystalLikeBlock> CRYSTAL_OF_CHARGED_BLOCK = registerBlock("crystal_of_changed_block", () -> new CrystalLikeBlock(true));
    public static final DeferredBlock<ModOreBlock> SCULK_GEM_ORE = registerBlock("sculk_gem_ore", () -> new ModOreBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.SCULK_SENSOR).strength(5f).requiresCorrectToolForDrops(), UniformInt.of(1, 4)));
    public static final DeferredBlock<ModOreBlock> DEEPSLATE_SCULK_GEM_ORE = registerBlock("deepslate_sculk_gem_ore", () -> new ModOreBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.SCULK_SENSOR).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(2, 4)));
    public static final DeferredBlock<Block> SCULK_GEM_BLOCK = registerBlock("sculk_gem_block", () -> new Block(Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(7.5f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<ModOreBlock> DARKNIUM_ORE = registerBlock("darknium_ore", () -> new ModOreBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(7.5f).requiresCorrectToolForDrops(), UniformInt.of(0, 3)));
    public static final DeferredBlock<ModOreBlock> DEEPSLATE_DARKNIUM_ORE = registerBlock("deepslate_darknium_ore", () -> new ModOreBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(8.5f).requiresCorrectToolForDrops(), UniformInt.of(1, 3)));
    public static final DeferredBlock<ModFacingableBlock> DARKNIUM_BLOCK = registerBlock("darknium_block", () -> new ModFacingableBlock(Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<RotatedPillarBlock> LORD_FURY_SCALES_BLOCK = registerBlock("lord_fury_scales_block", () -> new RotatedPillarBlock(Properties.of().sound(SoundType.METAL).strength(7.5f).lightLevel(s -> 2).requiresCorrectToolForDrops()));
    public static final DeferredBlock<MagicVinesBlock> MAGIC_VINES = registerBlock("magic_vines", MagicVinesBlock::new);
    public static final DeferredBlock<FlowerVineFromCavernOfDeepBlock> FLOWER_VINE_FROM_CAVERNOF_DEEP = registerBlock("flower_vine_from_cavernof_deep", FlowerVineFromCavernOfDeepBlock::new);
    public static final DeferredBlock<SmallSculkBushBlock> SMALL_SCULK_BUSH = registerBlock("small_sculk_bush", SmallSculkBushBlock::new);
    public static final DeferredBlock<UndergroundSculkBushWithoutFruitBlock> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = registerBlock("underground_sculk_bush_without_fruit", UndergroundSculkBushWithoutFruitBlock::new);
    public static final DeferredBlock<SculkGrowthsBlock> SCULK_GROWTHS = registerBlock("sculk_growths", SculkGrowthsBlock::new);
    public static final DeferredBlock<PurpleSaffronBlock> PURPLE_SAFFRON = registerBlock("purple_saffron", PurpleSaffronBlock::new);
    public static final DeferredBlock<ShinyPlumeriaBlock> SHINY_PLUMERIA = registerBlock("shiny_plumeria", ShinyPlumeriaBlock::new);
    public static final DeferredBlock<AnthuriumSproutedOfMagmaBlock> ANTHURIUM_SPROUTED_OF_MAGMA = registerBlock("anthurium_sprouted_of_magma", AnthuriumSproutedOfMagmaBlock::new);
    public static final DeferredBlock<Block> BIG_CRYSHROOM_BLOCK = registerBlock("big_cryshroom_block", () -> new Block(Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK).sound(SoundType.SHROOMLIGHT).strength(1.5f, 7f).lightLevel(s -> 8)));
    public static final DeferredBlock<MycenaFromCavernOfDeepBlock> MYCENA_FROM_CAVERN_OF_DEEP_BLOCK = registerBlock("mycena_from_cavern_of_deep_block", MycenaFromCavernOfDeepBlock::new);
    public static final RegistryObject<Block> SCULK_SAPLING = registerBlock("sculk_sapling", () -> new ModSaplingBlock(new SculkTreeGrower(), Properties.ofFullCopy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().lightLevel(s -> 3).noCollission()));
    public static final RegistryObject<Block> CRYSTALIC_OAK_SAPLING = registerBlock("crystalic_oak_sapling", () -> new ModSaplingBlock(new CrystalicOakTreeGrower(), Properties.ofFullCopy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().lightLevel(s -> 5).noCollission()));
    public static final RegistryObject<Block> MAGNOLIA_SAPLING = registerBlock("magnolia_sapling", () -> new ModSaplingBlock(new MagnoliaTreeGrower(), Properties.ofFullCopy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().noCollission()));
    public static final RegistryObject<Block> SHINY_SAPLING = registerBlock("shiny_sapling", () -> new ModSaplingBlock(new ShinyTreeGrower(), Properties.of().sound(SoundType.GRASS).instabreak().lightLevel(s -> 3).noCollission()));
    public static final DeferredBlock<FenceGateBlock> SAKURA_FENCE_GATE = registerBlock("sakura_fence_gate", () -> new FenceGateBlock(WoodType.OAK, Properties.ofFullCopy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape()));
    public static final DeferredBlock<PressurePlateBlock> SAKURA_PRESSURE_PLATE = registerBlock("sakura_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape().ignitedByLava()));
    public static final DeferredBlock<ButtonBlock> SAKURA_BUTTON = registerBlock("sakura_button", () -> new ButtonBlock(BlockSetType.OAK, 30, Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape()));
    public static final DeferredBlock<ModStandingSignBlock> SAKURA_SIGN = registerBlock("sakura_sign", () -> new ModStandingSignBlock(Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodType.MAGNOLIA));
    public static final DeferredBlock<ModWallSignBlock> SAKURA_WALL_SIGN = registerBlock("sakura_wall_sign", () -> new ModWallSignBlock(Properties.ofFullCopy(Blocks.OAK_SIGN).lootFrom(SAKURA_SIGN), ModWoodType.MAGNOLIA));
    public static final DeferredBlock<ModHangingSignBlock> SAKURA_HANGING_SIGN = registerBlock("sakura_hanging_sign", () -> new ModHangingSignBlock(Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodType.MAGNOLIA));
    public static final DeferredBlock<ModHangingWallSignBlock> SAKURA_HANGING_WALL_SIGN = registerBlock("sakura_hanging_wall_sign", () -> new ModHangingWallSignBlock(Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).lootFrom(SAKURA_HANGING_SIGN), ModWoodType.MAGNOLIA));
    public static final DeferredBlock<FenceGateBlock> SCULK_FENCE_GATE = registerBlock("sculk_fence_gate", () -> new FenceGateBlock(WoodType.OAK, Properties.ofFullCopy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 3.5f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape()));
    public static final DeferredBlock<PressurePlateBlock> SCULK_PRESSURE_PLATE = registerBlock("sculk_pressure_plate", () -> new PressurePlateBlock(
            BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4.5f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape()));
    public static final DeferredBlock<ButtonBlock> SCULK_BUTTON = registerBlock("sculk_button", () -> new ButtonBlock(BlockSetType.OAK,
            30, Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4.5f).lightLevel(s -> 1).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape()));
    public static final DeferredBlock<ModStandingSignBlock> SCULK_SIGN = registerBlock("sculk_sign", () -> new ModStandingSignBlock(Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodType.SCULK));
    public static final DeferredBlock<ModWallSignBlock> SCULK_WALL_SIGN = registerBlock("sculk_wall_sign", () -> new ModWallSignBlock(Properties.ofFullCopy(Blocks.OAK_SIGN).lootFrom(SCULK_SIGN), ModWoodType.SCULK));
    public static final DeferredBlock<ModHangingSignBlock> SCULK_HANGING_SIGN = registerBlock("sculk_hanging_sign", () -> new ModHangingSignBlock(Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodType.SCULK));
    public static final DeferredBlock<ModHangingWallSignBlock> SCULK_HANGING_WALL_SIGN = registerBlock("sculk_hanging_wall_sign", () -> new ModHangingWallSignBlock(Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).lootFrom(SCULK_HANGING_SIGN), ModWoodType.SCULK));
    public static final DeferredBlock<FenceGateBlock> CRYSTALIC_OAK_FENCE_GATE = registerBlock("crystalic_oak_fence_gate", () -> new FenceGateBlock(WoodType.OAK, Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).dynamicShape()));
    public static final DeferredBlock<PressurePlateBlock> CRYSTALIC_OAK_PRESSURE_PLATE = registerBlock("crystalic_oak_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).dynamicShape()));
    public static final DeferredBlock<ButtonBlock> CRYSTALIC_OAK_BUTTON = registerBlock("crystalic_oak_button", () -> new ButtonBlock(BlockSetType.OAK, 30, Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).requiresCorrectToolForDrops().dynamicShape()));
    public static final DeferredBlock<ModStandingSignBlock> CRYSTALIC_OAK_SIGN = registerBlock("crystalic_oak_sign", () -> new ModStandingSignBlock(Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodType.CRYSTALIC_OAK));
    public static final DeferredBlock<ModWallSignBlock> CRYSTALIC_OAK_WALL_SIGN = registerBlock("crystalic_oak_wall_sign", () -> new ModWallSignBlock(Properties.ofFullCopy(Blocks.OAK_SIGN).lootFrom(CRYSTALIC_OAK_SIGN), ModWoodType.CRYSTALIC_OAK));
    public static final DeferredBlock<ModHangingSignBlock> CRYSTALIC_OAK_HANGING_SIGN = registerBlock("crystalic_oak_hanging_sign", () -> new ModHangingSignBlock(Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodType.CRYSTALIC_OAK));
    public static final DeferredBlock<ModHangingWallSignBlock> CRYSTALIC_OAK_HANGING_WALL_SIGN = registerBlock("crystalic_oak_hanging_wall_sign", () -> new ModHangingWallSignBlock(Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).lootFrom(CRYSTALIC_OAK_HANGING_SIGN), ModWoodType.CRYSTALIC_OAK));
    public static final DeferredBlock<StandBlock> QUARTZ_STAND = registerBlock("quartz_stand", () -> new StandBlock(Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).sound(SoundType.AMETHYST).strength(-1, 3600000).lightLevel(s -> 1).noOcclusion().hasPostProcess((bs, br, bp) -> true)
            .emissiveRendering((bs, br, bp) -> true).noLootTable()));
    public static final DeferredBlock<StandBlock> AIR_STAND = registerBlock("air_stand", () -> new StandBlock(Properties.ofFullCopy(Blocks.BUBBLE_COLUMN).sound(SoundType.BONE_BLOCK).strength(-1, 3600000).lightLevel(s -> 10).noOcclusion().noLootTable()));
    public static final DeferredBlock<StandBlock> LORD_FURY_STAND = registerBlock("lord_fury_stand", () -> new StandBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.METAL).strength(-1, 3600000).lightLevel(s -> 3).noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).noLootTable()));
    public static final DeferredBlock<StandBlock> SCULK_NECROMANCER_BLOCK_SPAWNER = registerBlock("sculk_necromancer_block_spawner", () -> new StandBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.METAL).strength(-1, 3600000).lightLevel(s -> 4).noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).noLootTable()));
    public static final DeferredBlock<SculkBushBlock> SCULK_BUSH = registerDoubleBlock("sculk_bush", SculkBushBlock::new);
    public static final DeferredBlock<SculkBushWithoutBerryBlock> SCULK_BUSH_WITHOUT_BERRY = registerDoubleBlock("sculk_bush_without_berry", SculkBushWithoutBerryBlock::new);
    public static final DeferredBlock<UndergroundSculkFruitBushBlock> UNDERGROUND_SCULK_FRUIT_BUSH = registerBlock("underground_sculk_fruit_bush", UndergroundSculkFruitBushBlock::new);
    public static final DeferredBlock<VineFromCavernOfDeepBlock> VINE_FROM_CAVERNOF_DEEP = registerBlock("vine_from_cavernof_deep", VineFromCavernOfDeepBlock::new);
    public static final DeferredBlock<MycenaFromCavernOfDeep> MYCENA_FROM_CAVERN_OF_DEEP = registerBlock("mycena_from_cavern_of_deep", MycenaFromCavernOfDeep::new);
    public static final DeferredBlock<FlowerDeepMossBlock> FLOWER_DEEP_MOSS = registerBlock("flower_deep_moss", FlowerDeepMossBlock::new);
    public static final DeferredBlock<MushroomSpawnerBlock> MUSHROOM_SPAWNER = registerBlock("mushroom_spawner", MushroomSpawnerBlock::new);
    public static final DeferredBlock<CryshroomPlantBlock> CRYSHROOM_PLANT = registerBlock("cryshroom_plant", CryshroomPlantBlock::new);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerDoubleBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerDoubleBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block) {
        return VWItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredItem<Item> registerDoubleBlockItem(String name, DeferredBlock<T> block) {
        return VWItems.ITEMS.register(name, () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }
}