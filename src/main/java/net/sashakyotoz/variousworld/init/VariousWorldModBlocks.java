
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, VariousWorldMod.MODID);
	public static final RegistryObject<Block> CRYSTAL_CLUSTER = REGISTRY.register("crystal_cluster", () -> new CrystalClusterBlock());
	public static final RegistryObject<Block> SMALL_CRYSTAL_CLUSTER = REGISTRY.register("small_crystal_cluster", () -> new SmallCrystalClusterBlock());
	public static final RegistryObject<Block> KUNZITE_COLOURFUL_CRYSTAL = REGISTRY.register("kunzite_colourful_crystal", () -> new KunziteColourfulCrystalBlock());
	public static final RegistryObject<Block> BUDDING_KUNZITE_COLOURFUL_CRYSTAL = REGISTRY.register("budding_kunzite_colourful_crystal", () -> new BuddingKunziteColourfulCrystalBlock());
	public static final RegistryObject<Block> CRYSTAL_LEAVES = REGISTRY.register("crystal_leaves", () -> new CrystalLeavesBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_WOOD = REGISTRY.register("crystalic_oak_wood", () -> new Crystalic_OakWoodBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_LOG = REGISTRY.register("crystalic_oak_log", () -> new Crystalic_OakLogBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_PLANKS = REGISTRY.register("crystalic_oak_planks", () -> new Crystalic_OakPlanksBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_STAIRS = REGISTRY.register("crystalic_oak_stairs", () -> new Crystalic_OakStairsBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_SLAB = REGISTRY.register("crystalic_oak_slab", () -> new Crystalic_OakSlabBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_FENCE = REGISTRY.register("crystalic_oak_fence", () -> new Crystalic_OakFenceBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_PLANKS_TRAPDOOR = REGISTRY.register("crystalic_oak_planks_trapdoor", () -> new CrystalicOakPlanksTrapdoorBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_DOOR = REGISTRY.register("crystalic_oak_door", () -> new CrystalicOakDoorBlock());
	public static final RegistryObject<Block> SAKURA_LEAVES = REGISTRY.register("sakura_leaves", () -> new SakuraLeavesBlock());
	public static final RegistryObject<Block> SAKURA_LOG = REGISTRY.register("sakura_log", () -> new SakuraLogBlock());
	public static final RegistryObject<Block> SAKURA_WOOD = REGISTRY.register("sakura_wood", () -> new SakuraWoodBlock());
	public static final RegistryObject<Block> SAKURA_PLANKS = REGISTRY.register("sakura_planks", () -> new SakuraPlanksBlock());
	public static final RegistryObject<Block> SAKURA_STAIRS = REGISTRY.register("sakura_stairs", () -> new SakuraStairsBlock());
	public static final RegistryObject<Block> SAKURA_SLAB = REGISTRY.register("sakura_slab", () -> new SakuraSlabBlock());
	public static final RegistryObject<Block> SAKURA_FENCE = REGISTRY.register("sakura_fence", () -> new SakuraFenceBlock());
	public static final RegistryObject<Block> SAKURA_PLANKS_TRAPDOOR = REGISTRY.register("sakura_planks_trapdoor", () -> new SakuraPlanksTrapdoorBlock());
	public static final RegistryObject<Block> SAKURA_DOOR = REGISTRY.register("sakura_door", () -> new SakuraDoorBlock());
	public static final RegistryObject<Block> SCULK_LEAVES = REGISTRY.register("sculk_leaves", () -> new SculkLeavesBlock());
	public static final RegistryObject<Block> SCULK_LOG = REGISTRY.register("sculk_log", () -> new SculkLogBlock());
	public static final RegistryObject<Block> SCULK_WOOD = REGISTRY.register("sculk_wood", () -> new SculkWoodBlock());
	public static final RegistryObject<Block> SCULK_PLANKS = REGISTRY.register("sculk_planks", () -> new SculkPlanksBlock());
	public static final RegistryObject<Block> SCULK_STAIRS = REGISTRY.register("sculk_stairs", () -> new SculkStairsBlock());
	public static final RegistryObject<Block> SCULK_SLAB = REGISTRY.register("sculk_slab", () -> new SculkSlabBlock());
	public static final RegistryObject<Block> SCULK_FENCE = REGISTRY.register("sculk_fence", () -> new SculkFenceBlock());
	public static final RegistryObject<Block> SCULK_PLANKS_TRAPDOOR = REGISTRY.register("sculk_planks_trapdoor", () -> new SculkPlanksTrapdoorBlock());
	public static final RegistryObject<Block> SCULK_DOOR = REGISTRY.register("sculk_door", () -> new SculkDoorBlock());
	public static final RegistryObject<Block> GNEISS = REGISTRY.register("gneiss", () -> new GneissBlock());
	public static final RegistryObject<Block> COBBLED_GNEISS = REGISTRY.register("cobbled_gneiss", () -> new CobbledGneissBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS = REGISTRY.register("gneiss_bricks", () -> new GneissBricksBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS_STAIRS = REGISTRY.register("gneiss_bricks_stairs", () -> new GneissBricksStairsBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS_SLAB = REGISTRY.register("gneiss_bricks_slab", () -> new GneissBricksSlabBlock());
	public static final RegistryObject<Block> GNEISS_BRICKS_WALL = REGISTRY.register("gneiss_bricks_wall", () -> new GneissBricksWallBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA = REGISTRY.register("blackly_stony_magma", () -> new BlacklyStonyMagmaBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS = REGISTRY.register("blackly_stony_magma_bricks", () -> new BlacklyStonyMagmaBricksBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_STAIRS = REGISTRY.register("blackly_stony_magma_bricks_stairs", () -> new BlacklyStonyMagmaBricksStairsBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_SLAB = REGISTRY.register("blackly_stony_magma_bricks_slab", () -> new BlacklyStonyMagmaBricksSlabBlock());
	public static final RegistryObject<Block> BLACKLY_STONY_MAGMA_BRICKS_WALL = REGISTRY.register("blackly_stony_magma_bricks_wall", () -> new BlacklyStonyMagmaBricksWallBlock());
	public static final RegistryObject<Block> ENDER_BRICKS = REGISTRY.register("ender_bricks", EnderBricksBlock::new);
	public static final RegistryObject<Block> ENDER_BRICKS_STAIRS = REGISTRY.register("ender_bricks_stairs", () -> new EnderBricksStairsBlock());
	public static final RegistryObject<Block> ENDER_BRICKS_SLAB = REGISTRY.register("ender_bricks_slab", () -> new EnderBricksSlabBlock());
	public static final RegistryObject<Block> ENDER_BRICKS_WALL = REGISTRY.register("ender_bricks_wall", () -> new EnderBricksWallBlock());
	public static final RegistryObject<Block> SCULK_BRICKS = REGISTRY.register("sculk_bricks", () -> new SculkBricksBlock());
	public static final RegistryObject<Block> SCULK_BRICK_STAIRS = REGISTRY.register("sculk_brick_stairs", () -> new SculkBrickStairsBlock());
	public static final RegistryObject<Block> SCULK_BRIKS_SLAB = REGISTRY.register("sculk_briks_slab", () -> new SculkBricksSlabBlock());
	public static final RegistryObject<Block> SCULK_BRICKS_FENCE = REGISTRY.register("sculk_bricks_fence", () -> new SculkBricksFenceBlock());
	public static final RegistryObject<Block> ROSE_QUARTZ = REGISTRY.register("rose_quartz", () -> new RoseQuartzBlock());
	public static final RegistryObject<Block> ROSE_QUARTZ_SLAB = REGISTRY.register("rose_quartz_slab", () -> new RoseQuartzSlabBlock());
	public static final RegistryObject<Block> ROSE_QUARTZ_STAIRS = REGISTRY.register("rose_quartz_stairs", () -> new RoseQuartzStairsBlock());
	public static final RegistryObject<Block> CRYSTALIC_GRASS = REGISTRY.register("crystalic_grass", () -> new CrystalicGrassBlock());
	public static final RegistryObject<Block> SHINY_GRASS = REGISTRY.register("shiny_grass", () -> new ShinyGrassBlock());
	public static final RegistryObject<Block> SCULK_GRASS = REGISTRY.register("sculk_grass", () -> new SculkGrassBlock());
	public static final RegistryObject<Block> SCULK_MAGMA = REGISTRY.register("sculk_magma", () -> new SculkMagmaBlock());
	public static final RegistryObject<Block> SCULK_MOSS_BLOCK = REGISTRY.register("sculk_moss_block", () -> new SculkMossBlockBlock());
	public static final RegistryObject<Block> DEEP_MOSS = REGISTRY.register("deep_moss", DeepMossBlock::new);
	public static final RegistryObject<Block> CRYSTALIC_SLIME_BLOCK = REGISTRY.register("crystalic_slime_block", () -> new CrystalicSlimeBlockBlock());
	public static final RegistryObject<Block> LORD_FURY_HEAD = REGISTRY.register("lord_fury_head", () -> new LordFuryHeadBlock());
	public static final RegistryObject<Block> ARTIFACTTABLE = REGISTRY.register("artifacttable", () -> new ArtifactTableBlock());
	public static final RegistryObject<Block> ARMOR_STATION_BLOCK = REGISTRY.register("armor_station_block", () -> new ArmorStationBlockBlock());
	public static final RegistryObject<Block> DISENCHANT_TABLE = REGISTRY.register("disenchant_table", () -> new DisenchantTableBlock());
	public static final RegistryObject<Block> MYCOLOCYFAROGRAPH = REGISTRY.register("mycolocyfarograph", () -> new MycolocyfarographBlock());
	public static final RegistryObject<Block> CRYSTAL_BLOCK = REGISTRY.register("crystal_block", () -> new CrystalBlockBlock());
	public static final RegistryObject<Block> CRYSTAL_OF_CHANGED_BLOCK = REGISTRY.register("crystal_of_changed_block", () -> new CrystalOfChangedBlockBlock());
	public static final RegistryObject<Block> SCULK_GEM_ORE = REGISTRY.register("sculk_gem_ore", () -> new Sculk_GemOreBlock());
	public static final RegistryObject<Block> DEEPSLATE_SCULK_GEM_ORE = REGISTRY.register("deepslate_sculk_gem_ore", () -> new DeepslateSculkGemOreBlock());
	public static final RegistryObject<Block> SCULK_GEM_BLOCK = REGISTRY.register("sculk_gem_block", () -> new Sculk_GemBlockBlock());
	public static final RegistryObject<Block> DARKNIUM_ORE = REGISTRY.register("darknium_ore", () -> new DarkniumOreBlock());
	public static final RegistryObject<Block> DEEPSLATE_DARKNIUM_ORE = REGISTRY.register("deepslate_darknium_ore", () -> new DeepslateDarkniumOreBlock());
	public static final RegistryObject<Block> DARKNIUM_BLOCK = REGISTRY.register("darknium_block", () -> new DarkniumBlockBlock());
	public static final RegistryObject<Block> LORD_FURY_SCALES_BLOCK = REGISTRY.register("lord_fury_scales_block", () -> new Lord_Fury_ScalesBlockBlock());
	public static final RegistryObject<Block> MAGIC_VINES = REGISTRY.register("magic_vines", () -> new MagicVinesBlock());
	public static final RegistryObject<Block> FLOWER_VINE_FROM_CAVERNOF_DEEP = REGISTRY.register("flower_vine_from_cavernof_deep", () -> new FlowerVineFromCavernofDeepBlock());
	public static final RegistryObject<Block> SMALL_SCULK_BUSH = REGISTRY.register("small_sculk_bush", () -> new SmallSculkBushBlock());
	public static final RegistryObject<Block> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = REGISTRY.register("underground_sculk_bush_without_fruit", () -> new UndergroundSculkBushWithoutFruitBlock());
	public static final RegistryObject<Block> SCULK_GROWTHS = REGISTRY.register("sculk_growths", () -> new SculkGrowthsBlock());
	public static final RegistryObject<Block> PURPLE_SAFFRON = REGISTRY.register("purple_saffron", () -> new PurpleSaffronBlock());
	public static final RegistryObject<Block> SHINY_PLUMERIA = REGISTRY.register("shiny_plumeria", () -> new ShinyPlumeriaBlock());
	public static final RegistryObject<Block> ANTHURIUM_SPROUTED_OF_MAGMA = REGISTRY.register("anthurium_sprouted_of_magma", () -> new AnthuriumSproutedOfMagmaBlock());
	public static final RegistryObject<Block> BIG_CRYSHROOM_BLOCK = REGISTRY.register("big_cryshroom_block", () -> new BigCryshroomBlock());
	public static final RegistryObject<Block> MYCENA_FROM_CAVERN_OF_DEEP_BLOCK = REGISTRY.register("mycena_from_cavern_of_deep_block", () -> new MycenaFromCavernOfDeepBlock());
	public static final RegistryObject<Block> SCULK_SAPLING = REGISTRY.register("sculk_sapling", () -> new SculkSaplingBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_SAPLING = REGISTRY.register("crystalic_oak_sapling", () -> new CrystalicOakSaplingBlock());
	public static final RegistryObject<Block> MAGNOLIA_SAPLING = REGISTRY.register("magnolia_sapling", () -> new MagnoliaSaplingBlock());
	public static final RegistryObject<Block> SAKURA_FENCE_GATE = REGISTRY.register("sakura_fence_gate", () -> new SakuraFenceGateBlock());
	public static final RegistryObject<Block> SAKURA_PRESSURE_PLATE = REGISTRY.register("sakura_pressure_plate", () -> new SakuraPressurePlateBlock());
	public static final RegistryObject<Block> SAKURA_BUTTON = REGISTRY.register("sakura_button", () -> new SakuraButtonBlock());
	public static final RegistryObject<Block> SCULK_FENCE_GATE = REGISTRY.register("sculk_fence_gate", () -> new SculkFenceGateBlock());
	public static final RegistryObject<Block> SCULK_PRESSURE_PLATE = REGISTRY.register("sculk_pressure_plate", () -> new SculkPressurePlateBlock());
	public static final RegistryObject<Block> SCULK_BUTTON = REGISTRY.register("sculk_button", () -> new SculkButtonBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_FENCE_GATE = REGISTRY.register("crystalic_oak_fence_gate", () -> new Crystalic_OakFenceGateBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_PRESSURE_PLATE = REGISTRY.register("crystalic_oak_pressure_plate", () -> new Crystalic_OakPressurePlateBlock());
	public static final RegistryObject<Block> CRYSTALIC_OAK_BUTTON = REGISTRY.register("crystalic_oak_button", () -> new Crystalic_OakButtonBlock());
	public static final RegistryObject<Block> QUARTZ_STAND = REGISTRY.register("quartz_stand", () -> new QuartzStandBlock());
	public static final RegistryObject<Block> AIR_STAND = REGISTRY.register("air_stand", () -> new AirStandBlock());
	public static final RegistryObject<Block> LORD_FURY_STAND = REGISTRY.register("lord_fury_stand", () -> new LordFuryStandBlock());
	public static final RegistryObject<Block> SCULK_NECROMANCER_BLOCK_SPAWNER = REGISTRY.register("sculk_necromancer_block_spawner", () -> new SculkNecromancerBlockSpawnerBlock());
	public static final RegistryObject<Block> SCULK_BUSH = REGISTRY.register("sculk_bush", () -> new SculkBushBlock());
	public static final RegistryObject<Block> SCULK_BUSH_WITHOUT_BERRY = REGISTRY.register("sculk_bush_without_berry", SculkBushWithoutBerryBlock::new);
	public static final RegistryObject<Block> UNDERGROUND_SCULK_FRUIT_BUSH = REGISTRY.register("underground_sculk_fruit_bush", UndergroundSculkFruitBushBlock::new);
	public static final RegistryObject<Block> VINE_FROM_CAVERNOF_DEEP = REGISTRY.register("vine_from_cavernof_deep", VineFromCavernofDeepBlock::new);
	public static final RegistryObject<Block> MYCENA_FROM_CAVERN_OF_DEEP = REGISTRY.register("mycena_from_cavern_of_deep", MycenaFromCavernOfDeep::new);
	public static final RegistryObject<Block> FLOWER_DEEP_MOSS = REGISTRY.register("flower_deep_moss", FlowerDeepMossBlock::new);
	public static final RegistryObject<Block> MUSHROOM_SPAWNER = REGISTRY.register("mushroom_spawner", MushroomSpawnerBlock::new);
	public static final RegistryObject<Block> SHINY_VALLEY_DECOR_GENERATOR = REGISTRY.register("shiny_valley_decor_generator",()-> new Block(BlockBehaviour.Properties.copy(Blocks.CLAY).sound(SoundType.WOOL).strength(10f)));
	public static final RegistryObject<Block> CRYSHROOM_PLANT = REGISTRY.register("cryshroom_plant", CryshroomPlantBlock::new);
	public static final RegistryObject<Block> SHINY_SAPLING = REGISTRY.register("shiny_sapling",() -> new SaplingBlock(new ShinyTreeGrower(), BlockBehaviour.Properties.of().sound(SoundType.GRASS).instabreak().lightLevel(s -> 3).noCollission()){
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
			CrystalBlockBlock.blockColorLoad(event);
			SmallSculkBushBlock.blockColorLoad(event);
			AnthuriumSproutedOfMagmaBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			AnthuriumSproutedOfMagmaBlock.itemColorLoad(event);
		}
	}
}
