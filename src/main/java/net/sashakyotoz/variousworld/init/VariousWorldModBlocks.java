package net.sashakyotoz.variousworld.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.VariousWorldMod;
import net.sashakyotoz.variousworld.block.*;
import net.sashakyotoz.variousworld.world.treegrowers.ShinyTreeGrower;

import java.util.Collections;
import java.util.List;

public class VariousWorldModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VariousWorldMod.MODID);
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
	public static final RegistryObject<Block> CRYSTALIC_OAK_PLANKS_TRAPDOOR = BLOCKS.register("crystalic_oak_planks_trapdoor", () -> new TrapDoorLikeBlock(BlockBehaviour.Properties.copy(CRYSTALIC_OAK_PLANKS.get()).sound(SoundType.WOOD).strength(2f, 7f).noOcclusion().dynamicShape()));
	public static final RegistryObject<Block> CRYSTALIC_OAK_DOOR = BLOCKS.register("crystalic_oak_door", () -> new CrystalicOakDoorBlock());
	public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register("sakura_leaves", () -> new SakuraLeavesBlock());
	public static final RegistryObject<Block> SAKURA_PLANKS = BLOCKS.register("sakura_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f)));
	public static final RegistryObject<Block> SAKURA_LOG = BLOCKS.register("sakura_log", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.7f)));
	public static final RegistryObject<Block> SAKURA_WOOD = BLOCKS.register("sakura_wood", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.5f)));
	public static final RegistryObject<Block> SAKURA_STAIRS = BLOCKS.register("sakura_stairs", () -> new SakuraStairsBlock());
	public static final RegistryObject<Block> SAKURA_SLAB = BLOCKS.register("sakura_slab", () -> new SakuraSlabBlock());
	public static final RegistryObject<Block> SAKURA_FENCE = BLOCKS.register("sakura_fence", () -> new SakuraFenceBlock());
	public static final RegistryObject<Block> SAKURA_PLANKS_TRAPDOOR = BLOCKS.register("sakura_planks_trapdoor", () -> new TrapDoorLikeBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 4f).noOcclusion().dynamicShape()));
	public static final RegistryObject<Block> SAKURA_DOOR = BLOCKS.register("sakura_door", () -> new SakuraDoorBlock());
	public static final RegistryObject<Block> SCULK_LEAVES = BLOCKS.register("sculk_leaves", () -> new SculkLeavesBlock());
	public static final RegistryObject<Block> SCULK_PLANKS = BLOCKS.register("sculk_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_LOG = BLOCKS.register("sculk_log", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).lightLevel(s -> 3).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_WOOD = BLOCKS.register("sculk_wood", () -> new LogLikeBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 2.75f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_STAIRS = BLOCKS.register("sculk_stairs", () -> new SculkStairsBlock());
	public static final RegistryObject<Block> SCULK_SLAB = BLOCKS.register("sculk_slab", () -> new SculkSlabBlock());
	public static final RegistryObject<Block> SCULK_FENCE = BLOCKS.register("sculk_fence", () -> new SculkFenceBlock());
	public static final RegistryObject<Block> SCULK_PLANKS_TRAPDOOR = BLOCKS.register("sculk_planks_trapdoor", () -> new TrapDoorLikeBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.SCULK_VEIN).strength(3f, 4f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).noOcclusion().dynamicShape()));
	public static final RegistryObject<Block> SCULK_DOOR = BLOCKS.register("sculk_door", () -> new SculkDoorBlock());
	public static final RegistryObject<Block> GNEISS = BLOCKS.register("gneiss", () -> new GneissBlock());
	public static final RegistryObject<Block> COBBLED_GNEISS = BLOCKS.register("cobbled_gneiss", () -> new CobbledGneissBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS = BLOCKS.register("gneiss_bricks", () -> new GneissBricksBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS_STAIRS = BLOCKS.register("gneiss_bricks_stairs", () -> new GneissBricksStairsBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS_SLAB = BLOCKS.register("gneiss_bricks_slab", () -> new GneissBricksSlabBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS_WALL = BLOCKS.register("gneiss_bricks_wall", () -> new GneissBricksWallBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA = BLOCKS.register("blackly_stony_magma", () -> new BlacklyStonyMagmaBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS = BLOCKS.register("blackly_stony_magma_bricks", () -> new BlacklyStonyMagmaBricksBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_STAIRS = BLOCKS.register("blackly_stony_magma_bricks_stairs", () -> new BlacklyStonyMagmaBricksStairsBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_SLAB = BLOCKS.register("blackly_stony_magma_bricks_slab", () -> new BlacklyStonyMagmaBricksSlabBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_WALL = BLOCKS.register("blackly_stony_magma_bricks_wall", () -> new BlacklyStonyMagmaBricksWallBlock());
	public static final RegistryObject<Block> ENDER_BRICKS = BLOCKS.register("ender_bricks", EnderBricksBlock::new);
	public static final RegistryObject<Block> ENDER_BRICKS_STAIRS = BLOCKS.register("ender_bricks_stairs", () -> new EnderBricksStairsBlock());
	public static final RegistryObject<Block> ENDER_BRICKS_SLAB = BLOCKS.register("ender_bricks_slab", () -> new EnderBricksSlabBlock());
	public static final RegistryObject<Block> ENDER_BRICKS_WALL = BLOCKS.register("ender_bricks_wall", () -> new EnderBricksWallBlock());
	public static final RegistryObject<Block> SCULK_BRICKS = BLOCKS.register("sculk_bricks", () -> new SculkBricksBlock());
	public static final RegistryObject<Block> SCULK_BRICK_STAIRS = BLOCKS.register("sculk_brick_stairs", () -> new SculkBrickStairsBlock());
	public static final RegistryObject<Block> SCULK_BRIKS_SLAB = BLOCKS.register("sculk_briks_slab", () -> new SculkBricksSlabBlock());
	public static final RegistryObject<Block> SCULK_BRICKS_FENCE = BLOCKS.register("sculk_bricks_fence", () -> new SculkBricksFenceBlock());
	public static final RegistryObject<Block> ROSE_QUARTZ = BLOCKS.register("rose_quartz", () -> new RoseQuartzBlock());
	public static final RegistryObject<Block> ROSE_QUARTZ_SLAB = BLOCKS.register("rose_quartz_slab", () -> new RoseQuartzSlabBlock());
	public static final RegistryObject<Block> ROSE_QUARTZ_STAIRS = BLOCKS.register("rose_quartz_stairs", () -> new RoseQuartzStairsBlock());
	public static final RegistryObject<Block> CRYSTALIC_GRASS = BLOCKS.register("crystalic_grass", () -> new GrassLikeBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT).sound(SoundType.ROOTED_DIRT).strength(2f, 5f).lightLevel(s -> 3)));
	public static final RegistryObject<Block> SHINY_GRASS = BLOCKS.register("shiny_grass", () -> new GrassLikeBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.WET_GRASS).strength(1f, 5f).lightLevel(s -> 8).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)));
	public static final RegistryObject<Block> SCULK_GRASS = BLOCKS.register("sculk_grass", () -> new GrassLikeBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.SCULK_VEIN).strength(1f, 5f).lightLevel(s -> 3).friction(0.5f).speedFactor(0.8f)));
	public static final RegistryObject<Block> SCULK_MAGMA = BLOCKS.register("sculk_magma", () -> new SculkMagmaBlock());
	public static final RegistryObject<Block> SCULK_MOSS_BLOCK = BLOCKS.register("sculk_moss_block", () -> new SculkMossBlock());
	public static final RegistryObject<Block> DEEP_MOSS = BLOCKS.register("deep_moss", DeepMossBlock::new);
	public static final RegistryObject<Block> CRYSTALIC_SLIME_BLOCK = BLOCKS.register("crystalic_slime_block", () -> new CrystalicSlimeBlock());
	public static final RegistryObject<Block> LORD_FURY_HEAD = BLOCKS.register("lord_fury_head", () -> new LordFuryHeadBlock());
	public static final RegistryObject<Block> ARTIFACTTABLE = BLOCKS.register("artifacttable", ArtifactTableBlock::new);
	public static final RegistryObject<Block> ARMOR_STATION_BLOCK = BLOCKS.register("armor_station_block", ArmorStationBlock::new);
	public static final RegistryObject<Block> DISENCHANT_TABLE = BLOCKS.register("disenchant_table", DisenchantTableBlock::new);
	public static final RegistryObject<Block> MYCOLOCYFAROGRAPH = BLOCKS.register("mycolocyfarograph", MycolocyfarographBlock::new);
	public static final RegistryObject<Block> CRYSTAL_BLOCK = BLOCKS.register("crystal_block", () -> new CrystalBlock());
	public static final RegistryObject<Block> CRYSTAL_OF_CHANGED_BLOCK = BLOCKS.register("crystal_of_changed_block", () -> new CrystalOfChangedBlockBlock());
	public static final RegistryObject<Block> SCULK_GEM_ORE = BLOCKS.register("sculk_gem_ore", () -> new SculkGemOreBlock());
	public static final RegistryObject<Block> DEEPSLATE_SCULK_GEM_ORE = BLOCKS.register("deepslate_sculk_gem_ore", () -> new DeepslateSculkGemOreBlock());
	public static final RegistryObject<Block> SCULK_GEM_BLOCK = BLOCKS.register("sculk_gem_block", () -> new SculkGemBlock());
	public static final RegistryObject<Block> DARKNIUM_ORE = BLOCKS.register("darknium_ore", () -> new DarkniumOreBlock());
	public static final RegistryObject<Block> DEEPSLATE_DARKNIUM_ORE = BLOCKS.register("deepslate_darknium_ore", () -> new DeepslateDarkniumOreBlock());
	public static final RegistryObject<Block> DARKNIUM_BLOCK = BLOCKS.register("darknium_block", () -> new DarkniumBlockBlock());
	public static final RegistryObject<Block> LORD_FURY_SCALES_BLOCK = BLOCKS.register("lord_fury_scales_block", () -> new LordFuryScalesBlockBlock());
	public static final RegistryObject<Block> MAGIC_VINES = BLOCKS.register("magic_vines", () -> new MagicVinesBlock());
	public static final RegistryObject<Block> FLOWER_VINE_FROM_CAVERNOF_DEEP = BLOCKS.register("flower_vine_from_cavernof_deep", () -> new FlowerVineFromCavernOfDeepBlock());
	public static final RegistryObject<Block> SMALL_SCULK_BUSH = BLOCKS.register("small_sculk_bush", () -> new SmallSculkBushBlock());
	public static final RegistryObject<Block> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = BLOCKS.register("underground_sculk_bush_without_fruit", () -> new UndergroundSculkBushWithoutFruitBlock());
	public static final RegistryObject<Block> SCULK_GROWTHS = BLOCKS.register("sculk_growths", () -> new SculkGrowthsBlock());
	public static final RegistryObject<Block> PURPLE_SAFFRON = BLOCKS.register("purple_saffron", () -> new PurpleSaffronBlock());
	public static final RegistryObject<Block> SHINY_PLUMERIA = BLOCKS.register("shiny_plumeria", () -> new ShinyPlumeriaBlock());
	public static final RegistryObject<Block> ANTHURIUM_SPROUTED_OF_MAGMA = BLOCKS.register("anthurium_sprouted_of_magma", () -> new AnthuriumSproutedOfMagmaBlock());
	public static final RegistryObject<Block> BIG_CRYSHROOM_BLOCK = BLOCKS.register("big_cryshroom_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK).sound(SoundType.SHROOMLIGHT).strength(1.5f, 7f).lightLevel(s -> 8)));
	public static final RegistryObject<Block> MYCENA_FROM_CAVERN_OF_DEEP_BLOCK = BLOCKS.register("mycena_from_cavern_of_deep_block", () -> new MycenaFromCavernOfDeepBlock());
	public static final RegistryObject<Block> SCULK_SAPLING = BLOCKS.register("sculk_sapling", () -> new SculkSaplingBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_SAPLING = BLOCKS.register("crystalic_oak_sapling", () -> new CrystalicOakSaplingBlock());
	public static final RegistryObject<Block> MAGNOLIA_SAPLING = BLOCKS.register("magnolia_sapling", () -> new MagnoliaSaplingBlock());
	public static final RegistryObject<Block> SAKURA_FENCE_GATE = BLOCKS.register("sakura_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(SAKURA_PLANKS.get()).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape(), WoodType.OAK));
	public static final RegistryObject<Block> SAKURA_PRESSURE_PLATE = BLOCKS.register("sakura_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.5f, 4f).dynamicShape().ignitedByLava(), BlockSetType.OAK));
	public static final RegistryObject<Block> SAKURA_BUTTON = BLOCKS.register("sakura_button", () -> new SakuraButtonBlock());
	public static final RegistryObject<Block> SCULK_FENCE_GATE = BLOCKS.register("sculk_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(SCULK_PLANKS.get()).sound(SoundType.WOOD).strength(3f, 3.5f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape(), WoodType.OAK));
	public static final RegistryObject<Block> SCULK_PRESSURE_PLATE = BLOCKS.register("sculk_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
			BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(3f, 4.5f).lightLevel(s -> 2).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).dynamicShape(), BlockSetType.OAK));
	public static final RegistryObject<Block> SCULK_BUTTON = BLOCKS.register("sculk_button", () -> new SculkButtonBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_FENCE_GATE = BLOCKS.register("crystalic_oak_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).dynamicShape(), WoodType.OAK));
	public static final RegistryObject<Block> CRYSTALIC_OAK_PRESSURE_PLATE = BLOCKS.register("crystalic_oak_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(4f, 5f).dynamicShape(), BlockSetType.OAK));
	public static final RegistryObject<Block> CRYSTALIC_OAK_BUTTON = BLOCKS.register("crystalic_oak_button", () -> new CrystalicOakButtonBlock());
	public static final RegistryObject<Block> QUARTZ_STAND = BLOCKS.register("quartz_stand", () -> new QuartzStandBlock());
	public static final RegistryObject<Block> AIR_STAND = BLOCKS.register("air_stand", () -> new AirStandBlock());
	public static final RegistryObject<Block> LORD_FURY_STAND = BLOCKS.register("lord_fury_stand", () -> new LordFuryStandBlock());
	public static final RegistryObject<Block> SCULK_NECROMANCER_BLOCK_SPAWNER = BLOCKS.register("sculk_necromancer_block_spawner", () -> new SculkNecromancerBlockSpawnerBlock());
	public static final RegistryObject<Block> SCULK_BUSH = BLOCKS.register("sculk_bush", () -> new SculkBushBlock());
	public static final RegistryObject<Block> SCULK_BUSH_WITHOUT_BERRY = BLOCKS.register("sculk_bush_without_berry", SculkBushWithoutBerryBlock::new);
	public static final RegistryObject<Block> UNDERGROUND_SCULK_FRUIT_BUSH = BLOCKS.register("underground_sculk_fruit_bush", UndergroundSculkFruitBushBlock::new);
	public static final RegistryObject<Block> VINE_FROM_CAVERNOF_DEEP = BLOCKS.register("vine_from_cavernof_deep", VineFromCavernofDeepBlock::new);
	public static final RegistryObject<Block> MYCENA_FROM_CAVERN_OF_DEEP = BLOCKS.register("mycena_from_cavern_of_deep", MycenaFromCavernOfDeep::new);
	public static final RegistryObject<Block> FLOWER_DEEP_MOSS = BLOCKS.register("flower_deep_moss", FlowerDeepMossBlock::new);
	public static final RegistryObject<Block> MUSHROOM_SPAWNER = BLOCKS.register("mushroom_spawner", MushroomSpawnerBlock::new);
	public static final RegistryObject<Block> CRYSHROOM_PLANT = BLOCKS.register("cryshroom_plant", CryshroomPlantBlock::new);
	public static final RegistryObject<Block> SHINY_SAPLING = BLOCKS.register("shiny_sapling",() -> new SaplingBlock(new ShinyTreeGrower(), BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak().lightLevel(s -> 3).noCollission()){
		@Override
		public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
			ItemStack itemStack = new ItemStack(VariousWorldModBlocks.SHINY_SAPLING.get());
			return Collections.singletonList(itemStack);
		}
		@Override
		public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
			return groundState.is(VariousWorldModBlocks.CRYSTALIC_GRASS.get()) || groundState.is(VariousWorldModBlocks.SHINY_GRASS.get()) || groundState.is(Blocks.GRASS_BLOCK) || groundState.is(Blocks.PODZOL) || groundState.is(Blocks.MOSS_BLOCK)
					|| groundState.is(VariousWorldModBlocks.DEEP_MOSS.get()) || groundState.is(VariousWorldModBlocks.FLOWER_DEEP_MOSS.get());
		}
	});

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			CrystalBlock.blockColorLoad(event);
			SmallSculkBushBlock.blockColorLoad(event);
			AnthuriumSproutedOfMagmaBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			AnthuriumSproutedOfMagmaBlock.itemColorLoad(event);
		}
	}
}
