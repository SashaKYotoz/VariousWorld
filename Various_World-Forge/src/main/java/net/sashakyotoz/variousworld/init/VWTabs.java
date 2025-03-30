package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.VariousWorld;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VWTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VariousWorld.MODID);
    public static final RegistryObject<CreativeModeTab> VARIOUS_WORLD_TAB = CREATIVE_MODE_TABS.register("various_world_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(VWItems.CRYSTAL_SWORD.get()))
                    .title(Component.translatable("creativetab.various_world_tab")).displayItems((pParameters, tabData) -> {
                        tabData.accept(VWItems.CRYSTAL_SWORD.get());
                        tabData.accept(VWItems.THUNDERBOLT_HAMMER.get());
                        tabData.accept(VWItems.CRYSTALIC_SLIMEBALL_SWORD.get());
                        tabData.accept(VWItems.CRYSTALIC_SLIMEBALL_PICKAXE.get());
                        tabData.accept(VWItems.DARKNIUM_SWORD.get());
                        tabData.accept(VWItems.DARKNIUM_PICKAXE.get());
                        tabData.accept(VWItems.DARKNIUM_AXE.get());
                        tabData.accept(VWItems.DARKNIUM_SHOVEL.get());
                        tabData.accept(VWItems.DARKNIUM_HOE.get());
                        tabData.accept(VWItems.LORD_SWORD.get());
                        tabData.accept(VWItems.LORD_PICKAXE.get());
                        tabData.accept(VWItems.LORD_AXE.get());
                        tabData.accept(VWItems.LORD_SHOVEL.get());
                        tabData.accept(VWItems.LORD_HOE.get());
                        tabData.accept(VWItems.SCULK_SCYTHE.get());
                        tabData.accept(VWItems.NECROMANCER_WAND.get());
                        tabData.accept(VWItems.CRYSTALIC_BOW.get());
                        tabData.accept(VWItems.LORD_OF_FURIES_CROSSBOW.get());
                        tabData.accept(VWItems.CRYSTALIC_STRENGTH.get());
                        tabData.accept(VWItems.CRYSTAL_GEM.get());
                        tabData.accept(VWItems.LORD_FURY_SHARD.get());
                        tabData.accept(VWItems.MULTIPLE_ENDER_PEARL_ITEM.get());
                        tabData.accept(VWItems.TOTEM_OF_DARK_SPIRIT.get());
                        tabData.accept(VWItems.EXPLORER_NECKLACE.get());
                        tabData.accept(VWItems.STRENGH_AMULET.get());
                        tabData.accept(VWItems.REGENERATION_GEM.get());
                        tabData.accept(VWItems.AMETHYST_RING.get());
                        tabData.accept(VWItems.CRYSTAL_ARMOR_HELMET.get());
                        tabData.accept(VWItems.CRYSTAL_ARMOR_CHESTPLATE.get());
                        tabData.accept(VWItems.CRYSTAL_ARMOR_LEGGINGS.get());
                        tabData.accept(VWItems.CRYSTAL_ARMOR_BOOTS.get());
                        tabData.accept(VWItems.ANGEL_HELMET.get());
                        tabData.accept(VWItems.ANGEL_CHESTPLATE.get());
                        tabData.accept(VWItems.ANGEL_LEGGINGS.get());
                        tabData.accept(VWItems.ANGEL_BOOTS.get());
                        tabData.accept(VWItems.SCULK_ARMOR_HELMET.get());
                        tabData.accept(VWItems.SCULK_ARMOR_CHESTPLATE.get());
                        tabData.accept(VWItems.SCULK_ARMOR_LEGGINGS.get());
                        tabData.accept(VWItems.SCULK_ARMOR_BOOTS.get());
                        tabData.accept(VWItems.DARKNIUM_ARMOR_HELMET.get());
                        tabData.accept(VWItems.DARKNIUM_ARMOR_CHESTPLATE.get());
                        tabData.accept(VWItems.DARKNIUM_ARMOR_LEGGINGS.get());
                        tabData.accept(VWItems.DARKNIUM_ARMOR_BOOTS.get());
                        tabData.accept(VWItems.FURY_HELMET.get());
                        tabData.accept(VWItems.FURY_CHESTPLATE.get());
                        tabData.accept(VWItems.FURY_LEGGINGS.get());
                        tabData.accept(VWItems.FURY_BOOTS.get());
                        tabData.accept(VWItems.LORD_FURY_HELMET.get());
                        tabData.accept(VWItems.LORD_FURY_CHESTPLATE.get());
                        tabData.accept(VWItems.LORD_FURY_LEGGINGS.get());
                        tabData.accept(VWItems.LORD_FURY_BOOTS.get());
                        tabData.accept(VWItems.SLIME_ARMOR_HELMET.get());
                        tabData.accept(VWItems.SLIME_ARMOR_CHESTPLATE.get());
                        tabData.accept(VWItems.SLIME_ARMOR_LEGGINGS.get());
                        tabData.accept(VWItems.SLIME_ARMOR_BOOTS.get());
                        tabData.accept(VWBlocks.CRYSTAL_CLUSTER.get().asItem());
                        tabData.accept(VWBlocks.SMALL_CRYSTAL_CLUSTER.get().asItem());
                        tabData.accept(VWBlocks.KUNZITE_COLOURFUL_CRYSTAL.get().asItem());
                        tabData.accept(VWBlocks.BUDDING_KUNZITE_COLOURFUL_CRYSTAL.get().asItem());
                        tabData.accept(VWBlocks.CRYSTAL_LEAVES.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_WOOD.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_LOG.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_PLANKS.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_SLAB.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_FENCE.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_FENCE_GATE.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_DOOR.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_PRESSURE_PLATE.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_OAK_BUTTON.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_LEAVES.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_LOG.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_WOOD.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_PLANKS.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_SLAB.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_FENCE.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_FENCE_GATE.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_DOOR.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_PRESSURE_PLATE.get().asItem());
                        tabData.accept(VWBlocks.SAKURA_BUTTON.get().asItem());
                        tabData.accept(VWBlocks.SCULK_LEAVES.get().asItem());
                        tabData.accept(VWBlocks.SCULK_LOG.get().asItem());
                        tabData.accept(VWBlocks.SCULK_WOOD.get().asItem());
                        tabData.accept(VWBlocks.SCULK_PLANKS.get().asItem());
                        tabData.accept(VWBlocks.SCULK_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.SCULK_SLAB.get().asItem());
                        tabData.accept(VWBlocks.SCULK_FENCE.get().asItem());
                        tabData.accept(VWBlocks.SCULK_FENCE_GATE.get().asItem());
                        tabData.accept(VWBlocks.SCULK_DOOR.get().asItem());
                        tabData.accept(VWBlocks.SCULK_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VWBlocks.SCULK_PRESSURE_PLATE.get().asItem());
                        tabData.accept(VWBlocks.SCULK_BUTTON.get().asItem());
                        tabData.accept(VWBlocks.GNEISS.get().asItem());
                        tabData.accept(VWBlocks.COBBLED_GNEISS.get().asItem());
                        tabData.accept(VWBlocks.GNEISS_BRICKS.get().asItem());
                        tabData.accept(VWBlocks.GNEISS_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.GNEISS_BRICKS_SLAB.get().asItem());
                        tabData.accept(VWBlocks.GNEISS_BRICKS_WALL.get().asItem());
                        tabData.accept(VWBlocks.BLACKLY_STONY_MAGMA.get().asItem());
                        tabData.accept(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS.get().asItem());
                        tabData.accept(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS_SLAB.get().asItem());
                        tabData.accept(VWBlocks.BLACKLY_STONY_MAGMA_BRICKS_WALL.get().asItem());
                        tabData.accept(VWBlocks.ENDER_BRICKS.get().asItem());
                        tabData.accept(VWBlocks.ENDER_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.ENDER_BRICKS_SLAB.get().asItem());
                        tabData.accept(VWBlocks.ENDER_BRICKS_WALL.get().asItem());
                        tabData.accept(VWBlocks.SCULK_BRICKS.get().asItem());
                        tabData.accept(VWBlocks.SCULK_BRICK_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.SCULK_BRICKS_SLAB.get().asItem());
                        tabData.accept(VWBlocks.SCULK_BRICKS_WALL.get().asItem());
                        tabData.accept(VWBlocks.ROSE_QUARTZ.get().asItem());
                        tabData.accept(VWBlocks.ROSE_QUARTZ_SLAB.get().asItem());
                        tabData.accept(VWBlocks.ROSE_QUARTZ_STAIRS.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_GRASS.get().asItem());
                        tabData.accept(VWBlocks.SHINY_GRASS.get().asItem());
                        tabData.accept(VWBlocks.SCULK_GRASS.get().asItem());
                        tabData.accept(VWBlocks.SCULK_MAGMA.get().asItem());
                        tabData.accept(VWBlocks.SCULK_MOSS_BLOCK.get().asItem());
                        tabData.accept(VWBlocks.DEEP_MOSS.get().asItem());
                        tabData.accept(VWBlocks.CRYSTALIC_SLIME_BLOCK.get().asItem());
                        tabData.accept(VWBlocks.LORD_FURY_HEAD.get().asItem());
                        tabData.accept(VWBlocks.ARTIFACTTABLE.get().asItem());
                        tabData.accept(VWBlocks.ARMOR_STATION_BLOCK.get().asItem());
                        tabData.accept(VWBlocks.DISENCHANT_TABLE.get().asItem());
                        tabData.accept(VWBlocks.MYCOLOCYFAROGRAPH.get().asItem());
                        tabData.accept(VWItems.CRYSTALSHARD.get());
                        tabData.accept(VWItems.DARKSHARD.get());
                        tabData.accept(VWBlocks.CRYSTAL_BLOCK.get().asItem());
                        tabData.accept(VWBlocks.CRYSTAL_OF_CHARGED_BLOCK.get().asItem());
                        tabData.accept(VWBlocks.SCULK_GEM_ORE.get().asItem());
                        tabData.accept(VWBlocks.DEEPSLATE_SCULK_GEM_ORE.get().asItem());
                        tabData.accept(VWBlocks.SCULK_GEM_BLOCK.get().asItem());
                        tabData.accept(VWItems.RAW_SCULK_GEM.get());
                        tabData.accept(VWItems.SCULK_GEM.get());
                        tabData.accept(VWItems.SCULK_SHARD.get());
                        tabData.accept(VWBlocks.DARKNIUM_ORE.get().asItem());
                        tabData.accept(VWBlocks.DEEPSLATE_DARKNIUM_ORE.get().asItem());
                        tabData.accept(VWBlocks.DARKNIUM_BLOCK.get().asItem());
                        tabData.accept(VWItems.DARKNIUM_INGOT.get());
                        tabData.accept(VWItems.RAW_DARKNIUM_INGOT.get());
                        tabData.accept(VWItems.FURY_INGOT.get());
                        tabData.accept(VWItems.FURY_SCALES.get());
                        tabData.accept(VWBlocks.LORD_FURY_SCALES_BLOCK.get().asItem());
                        tabData.accept(VWItems.LORD_FURY_SCALE.get());
                        tabData.accept(VWItems.SLIME_CRYSTALIC.get());
                        tabData.accept(VWItems.GLOW_PURPLE_DYE.get());
                        tabData.accept(VWItems.CRYSTALIC_STICK.get());
                    }).build());

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            tabData.accept(VWItems.DARK_ZOMBIE_SPAWN_EGG.get());
            tabData.accept(VWItems.SCULK_SKELETON_SPAWN_EGG.get());
            tabData.accept(VWItems.DARK_FURY_SPAWN_EGG.get());
            tabData.accept(VWItems.CRYSTALIC_SLIME_SPAWN_EGG.get());
            tabData.accept(VWItems.SPIRITOF_PEACEFUL_WASTELAND_SPAWN_EGG.get());
            tabData.accept(VWItems.SPIRITOF_DEEP_CAVERN_SPAWN_EGG.get());
            tabData.accept(VWItems.ARMOREDSKELETON_SPAWN_EGG.get());
            tabData.accept(VWItems.WANDERING_SPIRIT_OF_SCULKS_SPAWN_EGG.get());
            tabData.accept(VWItems.ZOMBIE_OF_STONY_MAGMA_SPAWN_EGG.get());
            tabData.accept(VWItems.ZANY_VILER_WITCH_SPAWN_EGG.get());
            tabData.accept(VWItems.DROMOPHANT_SPAWN_EGG.get());
        }

        if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            tabData.accept(VWBlocks.MAGIC_VINES.get().asItem());
            tabData.accept(VWBlocks.SMALL_SCULK_BUSH.get().asItem());
            tabData.accept(VWBlocks.UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT.get().asItem());
            tabData.accept(VWBlocks.SCULK_GROWTHS.get().asItem());
            tabData.accept(VWBlocks.PURPLE_SAFFRON.get().asItem());
            tabData.accept(VWBlocks.SHINY_PLUMERIA.get().asItem());
            tabData.accept(VWBlocks.ANTHURIUM_SPROUTED_OF_MAGMA.get().asItem());
            tabData.accept(VWBlocks.BIG_CRYSHROOM_BLOCK.get().asItem());
            tabData.accept(VWItems.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK.get());
            tabData.accept(VWBlocks.SCULK_SAPLING.get().asItem());
            tabData.accept(VWBlocks.CRYSTALIC_OAK_SAPLING.get().asItem());
            tabData.accept(VWBlocks.MAGNOLIA_SAPLING.get().asItem());
            tabData.accept(VWBlocks.SHINY_SAPLING.get().asItem());
        }
        if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            tabData.accept(VWItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get());
            tabData.accept(VWItems.CRYSHROOM.get());
            tabData.accept(VWItems.SCULKBERRY.get());
            tabData.accept(VWItems.SCULK_FRUIT.get());
            tabData.accept(VWItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get());
            tabData.accept(VWItems.POTION_OF_DRAGON_EYE_EFFECT.get());
        }
        if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            tabData.accept(VWItems.CRYSTALIC_OAK_SIGN.get());
            tabData.accept(VWItems.CRYSTALIC_OAK_HANGING_SIGN.get());
            tabData.accept(VWItems.SAKURA_SIGN.get());
            tabData.accept(VWItems.SAKURA_HANGING_SIGN.get());
            tabData.accept(VWItems.SCULK_SIGN.get());
            tabData.accept(VWItems.SCULK_HANGING_SIGN.get());
        }
        if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            tabData.accept(VWItems.CRYSTALIC_OAK_BOAT);
            tabData.accept(VWItems.CRYSTALIC_OAK_CHEST_BOAT);
            tabData.accept(VWItems.MAGNOLIA_BOAT);
            tabData.accept(VWItems.MAGNOLIA_CHEST_BOAT);
            tabData.accept(VWItems.SCULK_BOAT);
            tabData.accept(VWItems.SCULK_CHEST_BOAT);
        }
    }
}