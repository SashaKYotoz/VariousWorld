package net.sashakyotoz.variousworld.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.entity.boats.ModBoatEntity;
import net.sashakyotoz.variousworld.item.*;

public class VWItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VariousWorld.MODID);
	public static final RegistryObject<Item> CRYSTAL_SWORD = ITEMS.register("crystal_sword", CrystalSwordItem::new);
	public static final RegistryObject<Item> THUNDERBOLT_HAMMER = ITEMS.register("thunderbolt_hammer", ThunderboltHammerItem::new);
	public static final RegistryObject<Item> CRYSTALIC_SLIMEBALL_SWORD = ITEMS.register("crystalic_slimeball_sword", () -> new SwordItem(ModTiers.CRYSTALIC_SLIME, 3, -2.4f, new Item.Properties()));
	public static final RegistryObject<Item> CRYSTALIC_SLIMEBALL_PICKAXE = ITEMS.register("crystalic_slimeball_pickaxe", CrystalicSlimeballPickaxeItem::new);
	public static final RegistryObject<Item> DARKNIUM_SWORD = ITEMS.register("darknium_sword", DarkniumSwordItem::new);
	public static final RegistryObject<Item> DARKNIUM_PICKAXE = ITEMS.register("darknium_pickaxe",()-> new PickaxeItem(ModTiers.DARKNIUM,2, -2.8F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> DARKNIUM_AXE = ITEMS.register("darknium_axe", () -> new AxeItem(ModTiers.DARKNIUM,6F, -3.0F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> DARKNIUM_SHOVEL = ITEMS.register("darknium_shovel", () -> new ShovelItem(ModTiers.DARKNIUM,2.5F, -3.0F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> DARKNIUM_HOE = ITEMS.register("darknium_hoe", () -> new HoeItem(ModTiers.DARKNIUM,-5, 0.0F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> LORD_SWORD = ITEMS.register("lord_sword", LordSwordItem::new);
	public static final RegistryObject<Item> LORD_PICKAXE = ITEMS.register("lord_pickaxe", () -> new PickaxeItem(ModTiers.LORD_SCALE,2, -2.8F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> LORD_AXE = ITEMS.register("lord_axe", () -> new AxeItem(ModTiers.LORD_SCALE,6F, -3.0F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> LORD_SHOVEL = ITEMS.register("lord_shovel", () -> new ShovelItem(ModTiers.LORD_SCALE,2.5F, -3.0F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> LORD_HOE = ITEMS.register("lord_hoe", () -> new HoeItem(ModTiers.LORD_SCALE,-5, 0.0F, new Item.Properties().fireResistant()));
	public static final RegistryObject<Item> SCULK_SCYTHE = ITEMS.register("sculk_scythe", SculkScytheItem::new);
	public static final RegistryObject<Item> NECROMANCER_WAND = ITEMS.register("necromancer_wand", NecromancerWandItem::new);
	public static final RegistryObject<Item> CRYSTALIC_BOW = ITEMS.register("crystalic_bow", CrystalicBowItem::new);
	public static final RegistryObject<Item> LORD_OF_FURIES_CROSSBOW = ITEMS.register("lord_of_furies_crossbow", LordOfFuriesCrossbowItem::new);
	public static final RegistryObject<Item> CRYSTALIC_STRENGTH = ITEMS.register("crystalic_strength", () -> new WithDescriptionItem(new Item.Properties().stacksTo(16).rarity(Rarity.RARE), Component.translatable("tooltip.various_world.crystalic_strength.tooltip")));
	public static final RegistryObject<Item> CRYSTAL_GEM = ITEMS.register("crystal_gem", () -> new WithDescriptionItem(new Item.Properties().stacksTo(16).fireResistant().rarity(Rarity.EPIC),Component.translatable("tooltip.various_world.crystalic_gem.tooltip")));
	public static final RegistryObject<Item> MULTIPLE_ENDER_PEARL_ITEM = ITEMS.register("multiple_ender_pearl_item", MultipleEnderPearlItem::new);
	public static final RegistryObject<Item> TOTEM_OF_DARK_SPIRIT = ITEMS.register("totem_of_dark_spirit", TotemOfDarkSpiritItem::new);
	public static final RegistryObject<Item> EXPLORER_NECKLACE = ITEMS.register("explorer_necklace", ExplorerNecklaceItem::new);
	public static final RegistryObject<Item> STRENGH_AMULET = ITEMS.register("strengh_amulet", () -> new Item(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> REGENERATION_GEM = ITEMS.register("regeneration_gem", () -> new Item(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> AMETHYST_RING = ITEMS.register("amethyst_ring", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> CRYSTAL_ARMOR_HELMET = ITEMS.register("crystal_armor_helmet", CrystalArmorItem.Helmet::new);
	public static final RegistryObject<Item> CRYSTAL_ARMOR_CHESTPLATE = ITEMS.register("crystal_armor_chestplate", CrystalArmorItem.Chestplate::new);
	public static final RegistryObject<Item> CRYSTAL_ARMOR_LEGGINGS = ITEMS.register("crystal_armor_leggings", CrystalArmorItem.Leggings::new);
	public static final RegistryObject<Item> CRYSTAL_ARMOR_BOOTS = ITEMS.register("crystal_armor_boots", CrystalArmorItem.Boots::new);
	public static final RegistryObject<Item> ANGEL_HELMET = ITEMS.register("angel_helmet", AngelItem.Helmet::new);
	public static final RegistryObject<Item> ANGEL_CHESTPLATE = ITEMS.register("angel_chestplate", AngelItem.Chestplate::new);
	public static final RegistryObject<Item> ANGEL_LEGGINGS = ITEMS.register("angel_leggings", AngelItem.Leggings::new);
	public static final RegistryObject<Item> ANGEL_BOOTS = ITEMS.register("angel_boots", AngelItem.Boots::new);
	public static final RegistryObject<Item> SCULK_ARMOR_HELMET = ITEMS.register("sculk_armor_helmet", SculkArmorItem.Helmet::new);
	public static final RegistryObject<Item> SCULK_ARMOR_CHESTPLATE = ITEMS.register("sculk_armor_chestplate", SculkArmorItem.Chestplate::new);
	public static final RegistryObject<Item> SCULK_ARMOR_LEGGINGS = ITEMS.register("sculk_armor_leggings", SculkArmorItem.Leggings::new);
	public static final RegistryObject<Item> SCULK_ARMOR_BOOTS = ITEMS.register("sculk_armor_boots", SculkArmorItem.Boots::new);
	public static final RegistryObject<Item> DARKNIUM_ARMOR_HELMET = ITEMS.register("darknium_armor_helmet", DarkniumArmorItem.Helmet::new);
	public static final RegistryObject<Item> DARKNIUM_ARMOR_CHESTPLATE = ITEMS.register("darknium_armor_chestplate", DarkniumArmorItem.Chestplate::new);
	public static final RegistryObject<Item> DARKNIUM_ARMOR_LEGGINGS = ITEMS.register("darknium_armor_leggings", DarkniumArmorItem.Leggings::new);
	public static final RegistryObject<Item> DARKNIUM_ARMOR_BOOTS = ITEMS.register("darknium_armor_boots", DarkniumArmorItem.Boots::new);
	public static final RegistryObject<Item> FURY_HELMET = ITEMS.register("fury_helmet", FuryArmorItem.Helmet::new);
	public static final RegistryObject<Item> FURY_CHESTPLATE = ITEMS.register("fury_chestplate", FuryArmorItem.Chestplate::new);
	public static final RegistryObject<Item> FURY_LEGGINGS = ITEMS.register("fury_leggings", FuryArmorItem.Leggings::new);
	public static final RegistryObject<Item> FURY_BOOTS = ITEMS.register("fury_boots", FuryArmorItem.Boots::new);
	public static final RegistryObject<Item> LORD_FURY_HELMET = ITEMS.register("lord_fury_helmet", LordFuryItem.Helmet::new);
	public static final RegistryObject<Item> LORD_FURY_CHESTPLATE = ITEMS.register("lord_fury_chestplate", LordFuryItem.Chestplate::new);
	public static final RegistryObject<Item> LORD_FURY_LEGGINGS = ITEMS.register("lord_fury_leggings", LordFuryItem.Leggings::new);
	public static final RegistryObject<Item> LORD_FURY_BOOTS = ITEMS.register("lord_fury_boots", LordFuryItem.Boots::new);
	public static final RegistryObject<Item> SLIME_ARMOR_HELMET = ITEMS.register("slime_armor_helmet", SlimeArmorItem.Helmet::new);
	public static final RegistryObject<Item> SLIME_ARMOR_CHESTPLATE = ITEMS.register("slime_armor_chestplate", SlimeArmorItem.Chestplate::new);
	public static final RegistryObject<Item> SLIME_ARMOR_LEGGINGS = ITEMS.register("slime_armor_leggings", SlimeArmorItem.Leggings::new);
	public static final RegistryObject<Item> SLIME_ARMOR_BOOTS = ITEMS.register("slime_armor_boots", SlimeArmorItem.Boots::new);
	public static final RegistryObject<Item> CRYSTAL_CLUSTER = block(VWBlocks.CRYSTAL_CLUSTER);
	public static final RegistryObject<Item> SMALL_CRYSTAL_CLUSTER = block(VWBlocks.SMALL_CRYSTAL_CLUSTER);
	public static final RegistryObject<Item> KUNZITE_COLOURFUL_CRYSTAL = block(VWBlocks.KUNZITE_COLOURFUL_CRYSTAL);
	public static final RegistryObject<Item> BUDDING_KUNZITE_COLOURFUL_CRYSTAL = block(VWBlocks.BUDDING_KUNZITE_COLOURFUL_CRYSTAL);
	public static final RegistryObject<Item> CRYSTAL_LEAVES = block(VWBlocks.CRYSTAL_LEAVES);
	public static final RegistryObject<Item> CRYSTALIC_OAK_WOOD = block(VWBlocks.CRYSTALIC_OAK_WOOD);
	public static final RegistryObject<Item> CRYSTALIC_OAK_LOG = block(VWBlocks.CRYSTALIC_OAK_LOG);
	public static final RegistryObject<Item> CRYSTALIC_OAK_PLANKS = block(VWBlocks.CRYSTALIC_OAK_PLANKS);
	public static final RegistryObject<Item> CRYSTALIC_OAK_STAIRS = block(VWBlocks.CRYSTALIC_OAK_STAIRS);
	public static final RegistryObject<Item> CRYSTALIC_OAK_SLAB = block(VWBlocks.CRYSTALIC_OAK_SLAB);
	public static final RegistryObject<Item> CRYSTALIC_OAK_FENCE = block(VWBlocks.CRYSTALIC_OAK_FENCE);
	public static final RegistryObject<Item> CRYSTALIC_OAK_PLANKS_TRAPDOOR = block(VWBlocks.CRYSTALIC_OAK_PLANKS_TRAPDOOR);
	public static final RegistryObject<Item> CRYSTALIC_OAK_DOOR = doubleBlock(VWBlocks.CRYSTALIC_OAK_DOOR);
	public static final RegistryObject<Item> SAKURA_LEAVES = block(VWBlocks.SAKURA_LEAVES);
	public static final RegistryObject<Item> SAKURA_LOG = block(VWBlocks.SAKURA_LOG);
	public static final RegistryObject<Item> SAKURA_WOOD = block(VWBlocks.SAKURA_WOOD);
	public static final RegistryObject<Item> SAKURA_PLANKS = block(VWBlocks.SAKURA_PLANKS);
	public static final RegistryObject<Item> SAKURA_STAIRS = block(VWBlocks.SAKURA_STAIRS);
	public static final RegistryObject<Item> SAKURA_SLAB = block(VWBlocks.SAKURA_SLAB);
	public static final RegistryObject<Item> SAKURA_FENCE = block(VWBlocks.SAKURA_FENCE);
	public static final RegistryObject<Item> SAKURA_PLANKS_TRAPDOOR = block(VWBlocks.SAKURA_PLANKS_TRAPDOOR);
	public static final RegistryObject<Item> SAKURA_DOOR = doubleBlock(VWBlocks.SAKURA_DOOR);
	public static final RegistryObject<Item> SCULK_LEAVES = block(VWBlocks.SCULK_LEAVES);
	public static final RegistryObject<Item> SCULK_LOG = block(VWBlocks.SCULK_LOG);
	public static final RegistryObject<Item> SCULK_WOOD = block(VWBlocks.SCULK_WOOD);
	public static final RegistryObject<Item> SCULK_PLANKS = block(VWBlocks.SCULK_PLANKS);
	public static final RegistryObject<Item> SCULK_STAIRS = block(VWBlocks.SCULK_STAIRS);
	public static final RegistryObject<Item> SCULK_SLAB = block(VWBlocks.SCULK_SLAB);
	public static final RegistryObject<Item> SCULK_FENCE = block(VWBlocks.SCULK_FENCE);
	public static final RegistryObject<Item> SCULK_PLANKS_TRAPDOOR = block(VWBlocks.SCULK_PLANKS_TRAPDOOR);
	public static final RegistryObject<Item> SCULK_DOOR = doubleBlock(VWBlocks.SCULK_DOOR);
	public static final RegistryObject<Item> GNEISS = block(VWBlocks.GNEISS);
	public static final RegistryObject<Item> COBBLED_GNEISS = block(VWBlocks.COBBLED_GNEISS);
	public static final RegistryObject<Item> GNEISS_BRICKS = block(VWBlocks.GNEISS_BRICKS);
	public static final RegistryObject<Item> GNEISS_BRICKS_STAIRS = block(VWBlocks.GNEISS_BRICKS_STAIRS);
	public static final RegistryObject<Item> GNEISS_BRICKS_SLAB = block(VWBlocks.GNEISS_BRICKS_SLAB);
	public static final RegistryObject<Item> GNEISS_BRICKS_WALL = block(VWBlocks.GNEISS_BRICKS_WALL);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA = block(VWBlocks.BLACKLY_STONY_MAGMA);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS = block(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS_STAIRS = block(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS_STAIRS);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS_SLAB = block(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS_SLAB);
	public static final RegistryObject<Item> BLACKLY_STONY_MAGMA_BRICKS_WALL = block(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS_WALL);
	public static final RegistryObject<Item> ENDER_BRICKS = block(VWBlocks.ENDER_BRICKS);
	public static final RegistryObject<Item> ENDER_BRICKS_STAIRS = block(VWBlocks.ENDER_BRICKS_STAIRS);
	public static final RegistryObject<Item> ENDER_BRICKS_SLAB = block(VWBlocks.ENDER_BRICKS_SLAB);
	public static final RegistryObject<Item> ENDER_BRICKS_WALL = block(VWBlocks.ENDER_BRICKS_WALL);
	public static final RegistryObject<Item> SCULK_BRICKS = block(VWBlocks.SCULK_BRICKS);
	public static final RegistryObject<Item> SCULK_BRICK_STAIRS = block(VWBlocks.SCULK_BRICK_STAIRS);
	public static final RegistryObject<Item> SCULK_BRIKS_SLAB = block(VWBlocks.SCULK_BRICKS_SLAB);
	public static final RegistryObject<Item> SCULK_BRICKS_FENCE = block(VWBlocks.SCULK_BRICKS_WALL);
	public static final RegistryObject<Item> ROSE_QUARTZ = block(VWBlocks.ROSE_QUARTZ);
	public static final RegistryObject<Item> ROSE_QUARTZ_SLAB = block(VWBlocks.ROSE_QUARTZ_SLAB);
	public static final RegistryObject<Item> ROSE_QUARTZ_STAIRS = block(VWBlocks.ROSE_QUARTZ_STAIRS);
	public static final RegistryObject<Item> CRYSTALIC_GRASS = block(VWBlocks.CRYSTALIC_GRASS);
	public static final RegistryObject<Item> SHINY_GRASS = block(VWBlocks.SHINY_GRASS);
	public static final RegistryObject<Item> SCULK_GRASS = block(VWBlocks.SCULK_GRASS);
	public static final RegistryObject<Item> SCULK_MAGMA = block(VWBlocks.SCULK_MAGMA);
	public static final RegistryObject<Item> SCULK_MOSS_BLOCK = block(VWBlocks.SCULK_MOSS_BLOCK);
	public static final RegistryObject<Item> DEEP_MOSS = block(VWBlocks.DEEP_MOSS);
	public static final RegistryObject<Item> CRYSTALIC_SLIME_BLOCK = block(VWBlocks.CRYSTALIC_SLIME_BLOCK);
	public static final RegistryObject<Item> LORD_FURY_HEAD = block(VWBlocks.LORD_FURY_HEAD);
	public static final RegistryObject<Item> ARTIFACTTABLE = block(VWBlocks.ARTIFACTTABLE);
	public static final RegistryObject<Item> ARMOR_STATION_BLOCK = block(VWBlocks.ARMOR_STATION_BLOCK);
	public static final RegistryObject<Item> DISENCHANT_TABLE = block(VWBlocks.DISENCHANT_TABLE);
	public static final RegistryObject<Item> MYCOLOCYFAROGRAPH = block(VWBlocks.MYCOLOCYFAROGRAPH);
	public static final RegistryObject<Item> CRYSTAL_BLOCK = block(VWBlocks.CRYSTAL_BLOCK);
	public static final RegistryObject<Item> CRYSTAL_OF_CHANGED_BLOCK = block(VWBlocks.CRYSTAL_OF_CHARGED_BLOCK);
	public static final RegistryObject<Item> SCULK_GEM_ORE = block(VWBlocks.SCULK_GEM_ORE);
	public static final RegistryObject<Item> DEEPSLATE_SCULK_GEM_ORE = block(VWBlocks.DEEPSLATE_SCULK_GEM_ORE);
	public static final RegistryObject<Item> SCULK_GEM_BLOCK = block(VWBlocks.SCULK_GEM_BLOCK);
	public static final RegistryObject<Item> CRYSTALSHARD = ITEMS.register("crystalshard", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> DARKSHARD = ITEMS.register("darkshard", () -> new Item(new Item.Properties().stacksTo(16).fireResistant().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> DARKNIUM_ORE = block(VWBlocks.DARKNIUM_ORE);
	public static final RegistryObject<Item> DEEPSLATE_DARKNIUM_ORE = block(VWBlocks.DEEPSLATE_DARKNIUM_ORE);
	public static final RegistryObject<Item> DARKNIUM_BLOCK = block(VWBlocks.DARKNIUM_BLOCK);
	public static final RegistryObject<Item> RAW_SCULK_GEM = ITEMS.register("raw_sculk_gem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> SCULK_GEM = ITEMS.register("sculk_gem", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SCULK_SHARD = ITEMS.register("sculk_shard", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> LORD_FURY_SCALES_BLOCK = block(VWBlocks.LORD_FURY_SCALES_BLOCK);
	public static final RegistryObject<Item> DARKNIUM_INGOT = ITEMS.register("darknium_ingot", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> RAW_DARKNIUM_INGOT = ITEMS.register("raw_darknium_ingot", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> FURY_INGOT = ITEMS.register("fury_ingot", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> FURY_SCALES = ITEMS.register("fury_scales", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> MAGIC_VINES = block(VWBlocks.MAGIC_VINES);
	public static final RegistryObject<Item> SMALL_SCULK_BUSH = block(VWBlocks.SMALL_SCULK_BUSH);
	public static final RegistryObject<Item> UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT = block(VWBlocks.UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT);
	public static final RegistryObject<Item> SCULK_GROWTHS = block(VWBlocks.SCULK_GROWTHS);
	public static final RegistryObject<Item> PURPLE_SAFFRON = block(VWBlocks.PURPLE_SAFFRON);
	public static final RegistryObject<Item> SHINY_PLUMERIA = block(VWBlocks.SHINY_PLUMERIA);
	public static final RegistryObject<Item> ANTHURIUM_SPROUTED_OF_MAGMA = block(VWBlocks.ANTHURIUM_SPROUTED_OF_MAGMA);
	public static final RegistryObject<Item> BIG_CRYSHROOM_BLOCK = block(VWBlocks.BIG_CRYSHROOM_BLOCK);
	public static final RegistryObject<Item> SCULK_SAPLING = block(VWBlocks.SCULK_SAPLING);
	public static final RegistryObject<Item> CRYSTALIC_OAK_SAPLING = block(VWBlocks.CRYSTALIC_OAK_SAPLING);
	public static final RegistryObject<Item> MAGNOLIA_SAPLING = block(VWBlocks.MAGNOLIA_SAPLING);
	public static final RegistryObject<Item> SHINY_SAPLING = block(VWBlocks.SHINY_SAPLING);
	public static final RegistryObject<Item> SAKURA_FENCE_GATE = block(VWBlocks.SAKURA_FENCE_GATE);
	public static final RegistryObject<Item> SAKURA_PRESSURE_PLATE = block(VWBlocks.SAKURA_PRESSURE_PLATE);
	public static final RegistryObject<Item> SAKURA_BUTTON = block(VWBlocks.SAKURA_BUTTON);
	public static final RegistryObject<Item> SCULK_FENCE_GATE = block(VWBlocks.SCULK_FENCE_GATE);
	public static final RegistryObject<Item> SCULK_PRESSURE_PLATE = block(VWBlocks.SCULK_PRESSURE_PLATE);
	public static final RegistryObject<Item> SCULK_BUTTON = block(VWBlocks.SCULK_BUTTON);
	public static final RegistryObject<Item> CRYSTALIC_OAK_FENCE_GATE = block(VWBlocks.CRYSTALIC_OAK_FENCE_GATE);
	public static final RegistryObject<Item> CRYSTALIC_OAK_PRESSURE_PLATE = block(VWBlocks.CRYSTALIC_OAK_PRESSURE_PLATE);
	public static final RegistryObject<Item> CRYSTALIC_OAK_BUTTON = block(VWBlocks.CRYSTALIC_OAK_BUTTON);
	public static final RegistryObject<Item> QUARTZ_STAND = block(VWBlocks.QUARTZ_STAND);
	public static final RegistryObject<Item> DARK_SPIRIT_SPAWN_EGG = ITEMS.register("dark_spirit_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.DARK_SPIRIT, -16724737, -16777216, new Item.Properties()));
	public static final RegistryObject<Item> AIR_STAND = block(VWBlocks.AIR_STAND);
	public static final RegistryObject<Item> LORD_FURY_STAND = block(VWBlocks.LORD_FURY_STAND);
	public static final RegistryObject<Item> SCULK_NECROMANCER_BLOCK_SPAWNER = block(VWBlocks.SCULK_NECROMANCER_BLOCK_SPAWNER);
	public static final RegistryObject<Item> SCULK_BUSH = doubleBlock(VWBlocks.SCULK_BUSH);
	public static final RegistryObject<Item> SCULK_BUSH_WITHOUT_BERRY = doubleBlock(VWBlocks.SCULK_BUSH_WITHOUT_BERRY);
	public static final RegistryObject<Item> UNDERGROUND_SCULK_FRUIT_BUSH = block(VWBlocks.UNDERGROUND_SCULK_FRUIT_BUSH);
	public static final RegistryObject<Item> FLOWER_DEEP_MOSS = block(VWBlocks.FLOWER_DEEP_MOSS);
	public static final RegistryObject<Item> MUSHROOM_SPAWNER = block(VWBlocks.MUSHROOM_SPAWNER);
	public static final RegistryObject<Item> MYCENA_FROM_CAVERN_OF_DEEP_BLOCK =block(VWBlocks.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK);
	public static final RegistryObject<Item> LORD_FURY_SCALE = ITEMS.register("lord_fury_scales_dust", ()-> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> LORD_FURY_SHARD = ITEMS.register("lord_fury_shard",()-> new WithDescriptionItem(new Item.Properties().stacksTo(3).rarity(Rarity.RARE),Component.translatable("tooltip.various_world.lord_fury_shard.tooltip")));
	public static final RegistryObject<Item> SLIME_CRYSTALIC = ITEMS.register("slime_crystalic", () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> GLOW_PURPLE_DYE = ITEMS.register("glow_purple_dye",()-> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> CRYSTALIC_STICK = ITEMS.register("crystalic_stick",()-> new Item(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> DARK_ZOMBIE_SPAWN_EGG = ITEMS.register("dark_zombie_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.ZOMBIE_OF_VARIOUS_BIOMES, -12425662, -13057, new Item.Properties()));
	public static final RegistryObject<Item> SCULK_SKELETON_SPAWN_EGG = ITEMS.register("sculk_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.SCULK_SKELETON, -12866376, -16777216, new Item.Properties()));
	public static final RegistryObject<Item> DARK_FURY_SPAWN_EGG = ITEMS.register("dark_fury_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.DARK_FURY, -13421773, -3355393, new Item.Properties()));
	public static final RegistryObject<Item> CRYSTALIC_SLIME_SPAWN_EGG = ITEMS.register("crystalic_slime_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.CRYSTALIC_SLIME, -6693377, -3407617, new Item.Properties()));
	public static final RegistryObject<Item> SPIRITOF_PEACEFUL_WASTELAND_SPAWN_EGG = ITEMS.register("spiritof_peaceful_wasteland_spawn_egg",
			() -> new ForgeSpawnEggItem(VWEntities.SPIRITOF_PEACEFUL_WASTELAND, -886377, -1751902, new Item.Properties()));
	public static final RegistryObject<Item> SPIRITOF_DEEP_CAVERN_SPAWN_EGG = ITEMS.register("spiritof_deep_cavern_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.SPIRITOF_DEEP_CAVERN, -11645362, -12290176, new Item.Properties()));
	public static final RegistryObject<Item> ARMOREDSKELETON_SPAWN_EGG = ITEMS.register("armoredskeleton_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.ARMORED_SKELETON, -14935012, -16751002, new Item.Properties()));
	public static final RegistryObject<Item> DROMOPHANT_SPAWN_EGG = ITEMS.register("dromophant_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.DROMOPHANT, 16514043, 26415, new Item.Properties()));
	public static final RegistryObject<Item> WANDERING_SPIRIT_OF_SCULKS_SPAWN_EGG = ITEMS.register("wandering_spirit_summoned_of_sculks_spawn_egg",
			() -> new ForgeSpawnEggItem(VWEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS, -16777216, -8388620, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_OF_STONY_MAGMA_SPAWN_EGG = ITEMS.register("zombie_of_stony_magma_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.ZOMBIE_OF_STONY_MAGMA, -26317, -13421773, new Item.Properties()));
	public static final RegistryObject<Item> ZANY_VILER_WITCH_SPAWN_EGG = ITEMS.register("zany_viler_witch_spawn_egg", () -> new ForgeSpawnEggItem(VWEntities.ZANY_VILER_WITCH, -10092442, -6710785, new Item.Properties()));
	public static final RegistryObject<Item> MYCENA_FROM_CAVERN_OF_DEEP_FOOD = ITEMS.register("mycena_from_cavern_of_deep_food", MycenaFromCavernOfDeepItem::new);
	public static final RegistryObject<Item> MYCENA_FROM_CAVERN_OF_DEEP = ITEMS.register("mycena_from_cavern_of_deep", MycenaFromCavernOfDeepItem::new);
	public static final RegistryObject<Item> CRYSHROOM = ITEMS.register("cryshroom", CryshroomItem::new);
	public static final RegistryObject<Item> SCULKBERRY = ITEMS.register("sculkberry", SculkberryItem::new);
	public static final RegistryObject<Item> SCULK_FRUIT = ITEMS.register("sculk_fruit",()-> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(5).saturationMod(1.5f).build())));
	public static final RegistryObject<Item> BRANCH_WITH_DRAGON_EYE_FRUIT = ITEMS.register("branch_with_dragon_eye_fruit", () -> new ItemNameBlockItem(VWBlocks.FLOWER_VINE_FROM_CAVERNOF_DEEP.get(),new Item.Properties().stacksTo(64).food((new FoodProperties.Builder()).nutrition(3).saturationMod(3f).build())));
	public static final RegistryObject<Item> POTION_OF_DRAGON_EYE_EFFECT = ITEMS.register("potion_of_dragon_eye_effect", PotionOfDragonEyeEffectItem::new);
	public static final RegistryObject<Item> SUPER_VISION_CHARM = ITEMS.register("super_vision_charm", SuperVisionCharmItem::new);
	public static final RegistryObject<Item> CRYSTALIC_OAK_SIGN = ITEMS.register("crystalic_oak_sign",()->new SignItem(new Item.Properties(), VWBlocks.CRYSTALIC_OAK_SIGN.get(), VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get()));
	public static final RegistryObject<Item> SAKURA_SIGN = ITEMS.register("sakura_sign",()->new SignItem(new Item.Properties(), VWBlocks.SAKURA_SIGN.get(), VWBlocks.SAKURA_WALL_SIGN.get()));
	public static final RegistryObject<Item> SCULK_SIGN = ITEMS.register("sculk_sign",()->new SignItem(new Item.Properties(), VWBlocks.SCULK_SIGN.get(), VWBlocks.SCULK_WALL_SIGN.get()));
	public static final RegistryObject<Item> CRYSTALIC_OAK_HANGING_SIGN = ITEMS.register("crystalic_oak_hanging_sign",()->new HangingSignItem(VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(), VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get(),new Item.Properties()));
	public static final RegistryObject<Item> SAKURA_HANGING_SIGN = ITEMS.register("sakura_hanging_sign",()->new HangingSignItem(VWBlocks.SAKURA_HANGING_SIGN.get(), VWBlocks.SAKURA_HANGING_WALL_SIGN.get(),new Item.Properties()));
	public static final RegistryObject<Item> SCULK_HANGING_SIGN = ITEMS.register("sculk_hanging_sign",()->new HangingSignItem(VWBlocks.SCULK_HANGING_SIGN.get(), VWBlocks.SCULK_HANGING_WALL_SIGN.get(),new Item.Properties()));
	public static final RegistryObject<Item> CRYSTALIC_OAK_BOAT = ITEMS.register("crystalic_oak_boat",()->new ModBoatItem(false, ModBoatEntity.Type.CRYSTALIC_OAK,new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> CRYSTALIC_OAK_CHEST_BOAT = ITEMS.register("crystalic_oak_chest_boat",()->new ModBoatItem(true, ModBoatEntity.Type.CRYSTALIC_OAK,new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> MAGNOLIA_BOAT = ITEMS.register("magnolia_boat",()->new ModBoatItem(false, ModBoatEntity.Type.MAGNOLIA,new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> MAGNOLIA_CHEST_BOAT = ITEMS.register("magnolia_chest_boat",()->new ModBoatItem(true, ModBoatEntity.Type.MAGNOLIA,new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> SCULK_BOAT = ITEMS.register("sculk_boat",()->new ModBoatItem(false, ModBoatEntity.Type.SCULK,new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> SCULK_CHEST_BOAT = ITEMS.register("sculk_chest_boat",()->new ModBoatItem(true, ModBoatEntity.Type.SCULK,new Item.Properties().stacksTo(1)));

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block) {
		return ITEMS.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}