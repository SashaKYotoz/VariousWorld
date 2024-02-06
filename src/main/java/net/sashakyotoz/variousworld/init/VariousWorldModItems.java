
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.VariousWorldMod;
import net.sashakyotoz.variousworld.item.*;

public class VariousWorldModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, VariousWorldMod.MODID);
	public static final RegistryObject<Item> CRYSTAL_SWORD = REGISTRY.register("crystal_sword", () -> new CrystalSwordItem());
	public static final RegistryObject<Item> THUNDERBOLT_HAMMER = REGISTRY.register("thunderbolt_hammer", () -> new ThunderboltHammerItem());
	public static final RegistryObject<Item> CRYSTALIC_SLIMEBALL_SWORD = REGISTRY.register("crystalic_slimeball_sword", () -> new CrystalicSlimeballSwordItem());
	public static final RegistryObject<Item> CRYSTALIC_SLIMEBALL_PICKAXE = REGISTRY.register("crystalic_slimeball_pickaxe", () -> new CrystalicSlimeballPickaxeItem());
	public static final RegistryObject<Item> DARKNIUM_SWORD = REGISTRY.register("darknium_sword", () -> new DarkniumSwordItem());
	public static final RegistryObject<Item> DARKNIUM_PICKAXE = REGISTRY.register("darknium_pickaxe", DarkniumPickaxeItem::new);
	public static final RegistryObject<Item> DARKNIUM_AXE = REGISTRY.register("darknium_axe", () -> new DarkniumAxeItem());
	public static final RegistryObject<Item> DARKNIUM_SHOVEL = REGISTRY.register("darknium_shovel", () -> new DarkniumShovelItem());
	public static final RegistryObject<Item> DARKNIUM_HOE = REGISTRY.register("darknium_hoe", () -> new DarkniumHoeItem());
	public static final RegistryObject<Item> LORD_SWORD = REGISTRY.register("lord_sword", () -> new LordSwordItem());
	public static final RegistryObject<Item> LORD_PICKAXE = REGISTRY.register("lord_pickaxe", () -> new LordPickaxeItem());
	public static final RegistryObject<Item> LORD_AXE = REGISTRY.register("lord_axe", () -> new LordAxeItem());
	public static final RegistryObject<Item> LORD_SHOVEL = REGISTRY.register("lord_shovel", () -> new LordShovelItem());
	public static final RegistryObject<Item> LORD_HOE = REGISTRY.register("lord_hoe", () -> new LordHoeItem());
	public static final RegistryObject<Item> SCULK_SCYTHE = REGISTRY.register("sculk_scythe", () -> new SculkScytheItem());
	public static final RegistryObject<Item> NECROMANCER_WAND = REGISTRY.register("necromancer_wand", () -> new NecromancerWandItem());
	public static final RegistryObject<Item> CRYSTALIC_BOW = REGISTRY.register("crystalic_bow", () -> new CrystalicBowItem());
	public static final RegistryObject<Item> LORD_OF_FURIES_CROSSBOW = REGISTRY.register("lord_of_furies_crossbow", () -> new LordOfFuriesCrossbowItem());
	public static final RegistryObject<Item> CRYSTALIC_STRENGTH = REGISTRY.register("crystalic_strength", () -> new CrystalicStrengthItem());
	public static final RegistryObject<Item> CRYSTAL_GEM = REGISTRY.register("crystal_gem", () -> new CrystalGemItem());
	public static final RegistryObject<Item> MULTIPLE_ENDER_PEARL_ITEM = REGISTRY.register("multiple_ender_pearl_item", () -> new MultipleEnderPearlItem());
	public static final RegistryObject<Item> TOTEM_OF_DARK_SPIRIT = REGISTRY.register("totem_of_dark_spirit", () -> new TotemOfDarkSpiritItem());
	public static final RegistryObject<Item> EXPLORER_NECKLACE = REGISTRY.register("explorer_necklace", () -> new ExplorerNecklaceItem());
	public static final RegistryObject<Item> STRENGH_AMULET = REGISTRY.register("strengh_amulet", () -> new Item(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> REGENERATION_GEM = REGISTRY.register("regeneration_gem", () -> new Item(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> AMETHYST_RING = REGISTRY.register("amethyst_ring", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> CRYSTAL_ARMOR_HELMET = REGISTRY.register("crystal_armor_helmet", () -> new CrystalArmorItem.Helmet());
	public static final RegistryObject<Item> CRYSTAL_ARMOR_CHESTPLATE = REGISTRY.register("crystal_armor_chestplate", () -> new CrystalArmorItem.Chestplate());
	public static final RegistryObject<Item> CRYSTAL_ARMOR_LEGGINGS = REGISTRY.register("crystal_armor_leggings", () -> new CrystalArmorItem.Leggings());
	public static final RegistryObject<Item> CRYSTAL_ARMOR_BOOTS = REGISTRY.register("crystal_armor_boots", () -> new CrystalArmorItem.Boots());
	public static final RegistryObject<Item> ANGEL_HELMET = REGISTRY.register("angel_helmet", () -> new AngelItem.Helmet());
	public static final RegistryObject<Item> ANGEL_CHESTPLATE = REGISTRY.register("angel_chestplate", () -> new AngelItem.Chestplate());
	public static final RegistryObject<Item> ANGEL_LEGGINGS = REGISTRY.register("angel_leggings", () -> new AngelItem.Leggings());
	public static final RegistryObject<Item> ANGEL_BOOTS = REGISTRY.register("angel_boots", () -> new AngelItem.Boots());
	public static final RegistryObject<Item> SCULK_ARMOR_HELMET = REGISTRY.register("sculk_armor_helmet", () -> new SculkArmorItem.Helmet());
	public static final RegistryObject<Item> SCULK_ARMOR_CHESTPLATE = REGISTRY.register("sculk_armor_chestplate", () -> new SculkArmorItem.Chestplate());
	public static final RegistryObject<Item> SCULK_ARMOR_LEGGINGS = REGISTRY.register("sculk_armor_leggings", () -> new SculkArmorItem.Leggings());
	public static final RegistryObject<Item> SCULK_ARMOR_BOOTS = REGISTRY.register("sculk_armor_boots", () -> new SculkArmorItem.Boots());
	public static final RegistryObject<Item> DARKNIUM_ARMOR_HELMET = REGISTRY.register("darknium_armor_helmet", () -> new DarkniumArmorItem.Helmet());
	public static final RegistryObject<Item> DARKNIUM_ARMOR_CHESTPLATE = REGISTRY.register("darknium_armor_chestplate", () -> new DarkniumArmorItem.Chestplate());
	public static final RegistryObject<Item> DARKNIUM_ARMOR_LEGGINGS = REGISTRY.register("darknium_armor_leggings", () -> new DarkniumArmorItem.Leggings());
	public static final RegistryObject<Item> DARKNIUM_ARMOR_BOOTS = REGISTRY.register("darknium_armor_boots", () -> new DarkniumArmorItem.Boots());
	public static final RegistryObject<Item> FURY_HELMET = REGISTRY.register("fury_helmet", () -> new FuryItem.Helmet());
	public static final RegistryObject<Item> FURY_CHESTPLATE = REGISTRY.register("fury_chestplate", () -> new FuryItem.Chestplate());
	public static final RegistryObject<Item> FURY_LEGGINGS = REGISTRY.register("fury_leggings", () -> new FuryItem.Leggings());
	public static final RegistryObject<Item> FURY_BOOTS = REGISTRY.register("fury_boots", () -> new FuryItem.Boots());
	public static final RegistryObject<Item> LORD_FURY_HELMET = REGISTRY.register("lord_fury_helmet", () -> new LordFuryItem.Helmet());
	public static final RegistryObject<Item> LORD_FURY_CHESTPLATE = REGISTRY.register("lord_fury_chestplate", () -> new LordFuryItem.Chestplate());
	public static final RegistryObject<Item> LORD_FURY_LEGGINGS = REGISTRY.register("lord_fury_leggings", () -> new LordFuryItem.Leggings());
	public static final RegistryObject<Item> LORD_FURY_BOOTS = REGISTRY.register("lord_fury_boots", () -> new LordFuryItem.Boots());
	public static final RegistryObject<Item> SLIME_ARMOR_HELMET = REGISTRY.register("slime_armor_helmet", () -> new SlimeArmorItem.Helmet());
	public static final RegistryObject<Item> SLIME_ARMOR_CHESTPLATE = REGISTRY.register("slime_armor_chestplate", () -> new SlimeArmorItem.Chestplate());
	public static final RegistryObject<Item> SLIME_ARMOR_LEGGINGS = REGISTRY.register("slime_armor_leggings", () -> new SlimeArmorItem.Leggings());
	public static final RegistryObject<Item> SLIME_ARMOR_BOOTS = REGISTRY.register("slime_armor_boots", () -> new SlimeArmorItem.Boots());
	public static final RegistryObject<Item> CRYSTAL_CLUSTER = block(VariousWorldModBlocks.CRYSTAL_CLUSTER);
	public static final RegistryObject<Item> SMALL_CRYSTAL_CLUSTER = block(VariousWorldModBlocks.SMALL_CRYSTAL_CLUSTER);
	public static final RegistryObject<Item> KUNZITE_COLOURFUL_CRYSTAL = block(VariousWorldModBlocks.KUNZITE_COLOURFUL_CRYSTAL);
	public static final RegistryObject<Item> BUDDING_KUNZITE_COLOURFUL_CRYSTAL = block(VariousWorldModBlocks.BUDDING_KUNZITE_COLOURFUL_CRYSTAL);
	public static final RegistryObject<Item> CRYSTAL_LEAVES = block(VariousWorldModBlocks.CRYSTAL_LEAVES);
	public static final RegistryObject<Item> CRYSTALIC_OAK_WOOD = block(VariousWorldModBlocks.CRYSTALIC_OAK_WOOD);
	public static final RegistryObject<Item> CRYSTALIC_OAK_LOG = block(VariousWorldModBlocks.CRYSTALIC_OAK_LOG);
	public static final RegistryObject<Item> CRYSTALIC_OAK_PLANKS = block(VariousWorldModBlocks.CRYSTALIC_OAK_PLANKS);
	public static final RegistryObject<Item> CRYSTALIC_OAK_STAIRS = block(VariousWorldModBlocks.CRYSTALIC_OAK_STAIRS);
	public static final RegistryObject<Item> CRYSTALIC_OAK_SLAB = block(VariousWorldModBlocks.CRYSTALIC_OAK_SLAB);
	public static final RegistryObject<Item> CRYSTALIC_OAK_FENCE = block(VariousWorldModBlocks.CRYSTALIC_OAK_FENCE);
	public static final RegistryObject<Item> CRYSTALIC_OAK_PLANKS_TRAPDOOR = block(VariousWorldModBlocks.CRYSTALIC_OAK_PLANKS_TRAPDOOR);
	public static final RegistryObject<Item> CRYSTALIC_OAK_DOOR = doubleBlock(VariousWorldModBlocks.CRYSTALIC_OAK_DOOR);
	public static final RegistryObject<Item> SAKURA_LEAVES = block(VariousWorldModBlocks.SAKURA_LEAVES);
	public static final RegistryObject<Item> SAKURA_LOG = block(VariousWorldModBlocks.SAKURA_LOG);
	public static final RegistryObject<Item> SAKURA_WOOD = block(VariousWorldModBlocks.SAKURA_WOOD);
	public static final RegistryObject<Item> SAKURA_PLANKS = block(VariousWorldModBlocks.SAKURA_PLANKS);
	public static final RegistryObject<Item> SAKURA_STAIRS = block(VariousWorldModBlocks.SAKURA_STAIRS);
	public static final RegistryObject<Item> SAKURA_SLAB = block(VariousWorldModBlocks.SAKURA_SLAB);
	public static final RegistryObject<Item> SAKURA_FENCE = block(VariousWorldModBlocks.SAKURA_FENCE);
	public static final RegistryObject<Item> SAKURA_PLANKS_TRAPDOOR = block(VariousWorldModBlocks.SAKURA_PLANKS_TRAPDOOR);
	public static final RegistryObject<Item> SAKURA_DOOR = doubleBlock(VariousWorldModBlocks.SAKURA_DOOR);
	public static final RegistryObject<Item> SCULK_LEAVES = block(VariousWorldModBlocks.SCULK_LEAVES);
	public static final RegistryObject<Item> SCULK_LOG = block(VariousWorldModBlocks.SCULK_LOG);
	public static final RegistryObject<Item> SCULK_WOOD = block(VariousWorldModBlocks.SCULK_WOOD);
	public static final RegistryObject<Item> SCULK_PLANKS = block(VariousWorldModBlocks.SCULK_PLANKS);
	public static final RegistryObject<Item> SCULK_STAIRS = block(VariousWorldModBlocks.SCULK_STAIRS);
	public static final RegistryObject<Item> SCULK_SLAB = block(VariousWorldModBlocks.SCULK_SLAB);
	public static final RegistryObject<Item> SCULK_FENCE = block(VariousWorldModBlocks.SCULK_FENCE);
	public static final RegistryObject<Item> SCULK_PLANKS_TRAPDOOR = block(VariousWorldModBlocks.SCULK_PLANKS_TRAPDOOR);
	public static final RegistryObject<Item> SCULK_DOOR = doubleBlock(VariousWorldModBlocks.SCULK_DOOR);
	public static final RegistryObject<Item> GNEISS = block(VariousWorldModBlocks.GNEISS);
	public static final RegistryObject<Item> COBBLED_GNEISS = block(VariousWorldModBlocks.COBBLED_GNEISS);
	public static final RegistryObject<Item> GNEISS_BRICKS = block(VariousWorldModBlocks.GNEISS_BRICKS);
	public static final RegistryObject<Item> GNEISS_BRICKS_STAIRS = block(VariousWorldModBlocks.GNEISS_BRICKS_STAIRS);
	public static final RegistryObject<Item> GNEISS_BRICKS_SLAB = block(VariousWorldModBlocks.GNEISS_BRICKS_SLAB);
	public static final RegistryObject<Item> GNEISS_BRICKS_WALL = block(VariousWorldModBlocks.GNEISS_BRICKS_WALL);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA = block(VariousWorldModBlocks.BLACKLY_STONY_MAGMA);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS = block(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS_STAIRS = block(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS_STAIRS);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS_SLAB = block(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS_SLAB);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS_WALL = block(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS_WALL);
	public static final RegistryObject<Item> ENDER_BRICKS = block(VariousWorldModBlocks.ENDER_BRICKS);
	public static final RegistryObject<Item> ENDER_BRICKS_STAIRS = block(VariousWorldModBlocks.ENDER_BRICKS_STAIRS);
	public static final RegistryObject<Item> ENDER_BRICKS_SLAB = block(VariousWorldModBlocks.ENDER_BRICKS_SLAB);
	public static final RegistryObject<Item> ENDER_BRICKS_WALL = block(VariousWorldModBlocks.ENDER_BRICKS_WALL);
	public static final RegistryObject<Item> SCULK_BRICKS = block(VariousWorldModBlocks.SCULK_BRICKS);
	public static final RegistryObject<Item> SCULK_BRICK_STAIRS = block(VariousWorldModBlocks.SCULK_BRICK_STAIRS);
	public static final RegistryObject<Item> SCULK_BRIKS_SLAB = block(VariousWorldModBlocks.SCULK_BRIKS_SLAB);
	public static final RegistryObject<Item> SCULK_BRICKS_FENCE = block(VariousWorldModBlocks.SCULK_BRICKS_FENCE);
	public static final RegistryObject<Item> ROSE_QUARTZ = block(VariousWorldModBlocks.ROSE_QUARTZ);
	public static final RegistryObject<Item> ROSE_QUARTZ_SLAB = block(VariousWorldModBlocks.ROSE_QUARTZ_SLAB);
	public static final RegistryObject<Item> ROSE_QUARTZ_STAIRS = block(VariousWorldModBlocks.ROSE_QUARTZ_STAIRS);
	public static final RegistryObject<Item> CRYSTALIC_GRASS = block(VariousWorldModBlocks.CRYSTALIC_GRASS);
	public static final RegistryObject<Item> SHINY_GRASS = block(VariousWorldModBlocks.SHINY_GRASS);
	public static final RegistryObject<Item> SCULK_GRASS = block(VariousWorldModBlocks.SCULK_GRASS);
	public static final RegistryObject<Item> SCULK_MAGMA = block(VariousWorldModBlocks.SCULK_MAGMA);
	public static final RegistryObject<Item> SCULK_MOSS_BLOCK = block(VariousWorldModBlocks.SCULK_MOSS_BLOCK);
	public static final RegistryObject<Item> DEEP_MOSS = block(VariousWorldModBlocks.DEEP_MOSS);
	public static final RegistryObject<Item> CRYSTALIC_SLIME_BLOCK = block(VariousWorldModBlocks.CRYSTALIC_SLIME_BLOCK);
	public static final RegistryObject<Item> LORD_FURY_HEAD = block(VariousWorldModBlocks.LORD_FURY_HEAD);
	public static final RegistryObject<Item> ARTIFACTTABLE = block(VariousWorldModBlocks.ARTIFACTTABLE);
	public static final RegistryObject<Item> ARMOR_STATION_BLOCK = block(VariousWorldModBlocks.ARMOR_STATION_BLOCK);
	public static final RegistryObject<Item> DISENCHANT_TABLE = block(VariousWorldModBlocks.DISENCHANT_TABLE);
	public static final RegistryObject<Item> MYCOLOCYFAROGRAPH = block(VariousWorldModBlocks.MYCOLOCYFAROGRAPH);
	public static final RegistryObject<Item> CRYSTALSHARD = REGISTRY.register("crystalshard", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> DARKSHARD = REGISTRY.register("darkshard", () -> new Item(new Item.Properties().stacksTo(4).fireResistant().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> CRYSTAL_BLOCK = block(VariousWorldModBlocks.CRYSTAL_BLOCK);
	public static final RegistryObject<Item> CRYSTAL_OF_CHANGED_BLOCK = block(VariousWorldModBlocks.CRYSTAL_OF_CHANGED_BLOCK);
	public static final RegistryObject<Item> SCULK_GEM_ORE = block(VariousWorldModBlocks.SCULK_GEM_ORE);
	public static final RegistryObject<Item> DEEPSLATE_SCULK_GEM_ORE = block(VariousWorldModBlocks.DEEPSLATE_SCULK_GEM_ORE);
	public static final RegistryObject<Item> SCULK_GEM_BLOCK = block(VariousWorldModBlocks.SCULK_GEM_BLOCK);
	public static final RegistryObject<Item> RAW_SCULK_GEM = REGISTRY.register("raw_sculk_gem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> SCULK_GEM = REGISTRY.register("sculk_gem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SCULK_SHARD = REGISTRY.register("sculk_shard", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> DARKNIUM_ORE = block(VariousWorldModBlocks.DARKNIUM_ORE);
	public static final RegistryObject<Item> DEEPSLATE_DARKNIUM_ORE = block(VariousWorldModBlocks.DEEPSLATE_DARKNIUM_ORE);
	public static final RegistryObject<Item> DARKNIUM_BLOCK = block(VariousWorldModBlocks.DARKNIUM_BLOCK);
	public static final RegistryObject<Item> DARKNIUM_INGOT = REGISTRY.register("darknium_ingot", () -> new DarkniumIngotItem());
	public static final RegistryObject<Item> RAW_DARKNIUM_INGOT = REGISTRY.register("raw_darknium_ingot", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> FURY_INGOT = REGISTRY.register("fury_ingot", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> FURY_SCALES = REGISTRY.register("fury_scales", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> LORD_FURY_SCALES_BLOCK = block(VariousWorldModBlocks.LORD_FURY_SCALES_BLOCK);
	public static final RegistryObject<Item> LORD_FURY_SCALES_DUST = REGISTRY.register("lord_fury_scales_dust", ()-> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> LORD_FURY_SHARD = REGISTRY.register("lord_fury_shard", LordFuryShardItem::new);
	public static final RegistryObject<Item> SLIME_CRYSTALIC = REGISTRY.register("slime_crystalic", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> GLOW_PURPLE_DYE = REGISTRY.register("glow_purple_dye",()-> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> CRYSTALIC_STICK = REGISTRY.register("crystalic_stick",()-> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> MAGIC_VINES = block(VariousWorldModBlocks.MAGIC_VINES);
	public static final RegistryObject<Item> SMALL_SCULK_BUSH = block(VariousWorldModBlocks.SMALL_SCULK_BUSH);
	public static final RegistryObject<Item> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = block(VariousWorldModBlocks.UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT);
	public static final RegistryObject<Item> SCULK_GROWTHS = block(VariousWorldModBlocks.SCULK_GROWTHS);
	public static final RegistryObject<Item> PURPLE_SAFFRON = block(VariousWorldModBlocks.PURPLE_SAFFRON);
	public static final RegistryObject<Item> SHINY_PLUMERIA = block(VariousWorldModBlocks.SHINY_PLUMERIA);
	public static final RegistryObject<Item> ANTHURIUM_SPROUTED_OF_MAGMA = block(VariousWorldModBlocks.ANTHURIUM_SPROUTED_OF_MAGMA);
	public static final RegistryObject<Item> BIG_CRYSHROOM_BLOCK = block(VariousWorldModBlocks.BIG_CRYSHROOM_BLOCK);
	public static final RegistryObject<Item> SCULK_SAPLING = block(VariousWorldModBlocks.SCULK_SAPLING);
	public static final RegistryObject<Item> CRYSTALIC_OAK_SAPLING = block(VariousWorldModBlocks.CRYSTALIC_OAK_SAPLING);
	public static final RegistryObject<Item> MAGNOLIA_SAPLING = block(VariousWorldModBlocks.MAGNOLIA_SAPLING);
	public static final RegistryObject<Item> SHINY_SAPLING = block(VariousWorldModBlocks.SHINY_SAPLING);
	public static final RegistryObject<Item> SAKURA_FENCE_GATE = block(VariousWorldModBlocks.SAKURA_FENCE_GATE);
	public static final RegistryObject<Item> SAKURA_PRESSURE_PLATE = block(VariousWorldModBlocks.SAKURA_PRESSURE_PLATE);
	public static final RegistryObject<Item> SAKURA_BUTTON = block(VariousWorldModBlocks.SAKURA_BUTTON);
	public static final RegistryObject<Item> SCULK_FENCE_GATE = block(VariousWorldModBlocks.SCULK_FENCE_GATE);
	public static final RegistryObject<Item> SCULK_PRESSURE_PLATE = block(VariousWorldModBlocks.SCULK_PRESSURE_PLATE);
	public static final RegistryObject<Item> SCULK_BUTTON = block(VariousWorldModBlocks.SCULK_BUTTON);
	public static final RegistryObject<Item> CRYSTALIC_OAK_FENCE_GATE = block(VariousWorldModBlocks.CRYSTALIC_OAK_FENCE_GATE);
	public static final RegistryObject<Item> CRYSTALIC_OAK_PRESSURE_PLATE = block(VariousWorldModBlocks.CRYSTALIC_OAK_PRESSURE_PLATE);
	public static final RegistryObject<Item> CRYSTALIC_OAK_BUTTON = block(VariousWorldModBlocks.CRYSTALIC_OAK_BUTTON);
	public static final RegistryObject<Item> DARK_ZOMBIE_SPAWN_EGG = REGISTRY.register("dark_zombie_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.ZOMBIE_OF_VARIOUS_BIOMES, -12425662, -13057, new Item.Properties()));
	public static final RegistryObject<Item> SCULK_SKELETON_SPAWN_EGG = REGISTRY.register("sculk_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.SCULK_SKELETON, -12866376, -16777216, new Item.Properties()));
	public static final RegistryObject<Item> DARK_FURY_SPAWN_EGG = REGISTRY.register("dark_fury_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.DARK_FURY, -13421773, -3355393, new Item.Properties()));
	public static final RegistryObject<Item> CRYSTALIC_SLIME_SPAWN_EGG = REGISTRY.register("crystalic_slime_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.CRYSTALIC_SLIME, -6693377, -3407617, new Item.Properties()));
	public static final RegistryObject<Item> SPIRITOF_PEACEFUL_WASTELAND_SPAWN_EGG = REGISTRY.register("spiritof_peaceful_wasteland_spawn_egg",
			() -> new ForgeSpawnEggItem(VariousWorldModEntities.SPIRITOF_PEACEFUL_WASTELAND, -886377, -1751902, new Item.Properties()));
	public static final RegistryObject<Item> SPIRITOF_DEEP_CAVERN_SPAWN_EGG = REGISTRY.register("spiritof_deep_cavern_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.SPIRITOF_DEEP_CAVERN, -11645362, -12290176, new Item.Properties()));
	public static final RegistryObject<Item> ARMOREDSKELETON_SPAWN_EGG = REGISTRY.register("armoredskeleton_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.ARMOREDSKELETON, -14935012, -16751002, new Item.Properties()));
	public static final RegistryObject<Item> WANDERING_SPIRIT_SUMMONED_OF_SCULKS_SPAWN_EGG = REGISTRY.register("wandering_spirit_summoned_of_sculks_spawn_egg",
			() -> new ForgeSpawnEggItem(VariousWorldModEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS, -16777216, -8388620, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_OF_STONY_MAGMA_SPAWN_EGG = REGISTRY.register("zombie_of_stony_magma_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.ZOMBIE_OF_STONY_MAGMA, -26317, -13421773, new Item.Properties()));
	public static final RegistryObject<Item> ZANY_VILER_WITCH_SPAWN_EGG = REGISTRY.register("zany_viler_witch_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.ZANY_VILER_WITCH, -10092442, -6710785, new Item.Properties()));
	public static final RegistryObject<Item> MYCENA_FROM_CAVERN_OF_DEEP_FOOD = REGISTRY.register("mycena_from_cavern_of_deep_food", MycenaFromCavernOfDeepItem::new);
	public static final RegistryObject<Item> MYCENA_FROM_CAVERN_OF_DEEP = REGISTRY.register("mycena_from_cavern_of_deep", MycenaFromCavernOfDeepItem::new);
	public static final RegistryObject<Item> MYCENA_FROM_CAVERN_OF_DEEP_BLOCK =block(VariousWorldModBlocks.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK);
	public static final RegistryObject<Item> CRYSHROOM = REGISTRY.register("cryshroom", CryshroomItem::new);
	public static final RegistryObject<Item> SCULKBERRY = REGISTRY.register("sculkberry", SculkberryItem::new);
	public static final RegistryObject<Item> SCULK_FRUIT = REGISTRY.register("sculk_fruit", SculkFruitItem::new);
	public static final RegistryObject<Item> BRANCH_WITH_DRAGON_EYE_FRUIT = REGISTRY.register("branch_with_dragon_eye_fruit", () -> new BranchWithDragonEyeFruitItem());
	public static final RegistryObject<Item> POTION_OF_DRAGON_EYE_EFFECT = REGISTRY.register("potion_of_dragon_eye_effect", () -> new PotionOfDragonEyeEffectItem());
	public static final RegistryObject<Item> QUARTZ_STAND = block(VariousWorldModBlocks.QUARTZ_STAND);
	public static final RegistryObject<Item> DARK_SPIRIT_SPAWN_EGG = REGISTRY.register("dark_spirit_spawn_egg", () -> new ForgeSpawnEggItem(VariousWorldModEntities.DARK_SPIRIT, -16724737, -16777216, new Item.Properties()));
	public static final RegistryObject<Item> AIR_STAND = block(VariousWorldModBlocks.AIR_STAND);
	public static final RegistryObject<Item> LORD_FURY_STAND = block(VariousWorldModBlocks.LORD_FURY_STAND);
	public static final RegistryObject<Item> SCULK_NECROMANCER_BLOCK_SPAWNER = block(VariousWorldModBlocks.SCULK_NECROMANCER_BLOCK_SPAWNER);
	public static final RegistryObject<Item> SCULK_BUSH = doubleBlock(VariousWorldModBlocks.SCULK_BUSH);
	public static final RegistryObject<Item> SCULK_BUSH_WITHOUT_BERRY = doubleBlock(VariousWorldModBlocks.SCULK_BUSH_WITHOUT_BERRY);
	public static final RegistryObject<Item> UNDERGROUND_SCULK_FRUIT_BUSH = block(VariousWorldModBlocks.UNDERGROUND_SCULK_FRUIT_BUSH);
	public static final RegistryObject<Item> FLOWER_DEEP_MOSS = block(VariousWorldModBlocks.FLOWER_DEEP_MOSS);
	public static final RegistryObject<Item> MUSHROOM_SPAWNER = block(VariousWorldModBlocks.MUSHROOM_SPAWNER);
	public static final RegistryObject<Item> SUPER_VISION_CHARM = REGISTRY.register("super_vision_charm", () -> new SuperVisionCharmItem());
	public static final RegistryObject<Item> SHINY_VALLEY_DECOR_GENERATOR = block(VariousWorldModBlocks.SHINY_VALLEY_DECOR_GENERATOR);

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}
