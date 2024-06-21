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
public class VariousWorldTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VariousWorld.MODID);
    public static final RegistryObject<CreativeModeTab> VARIOUS_WORLD_TAB = CREATIVE_MODE_TABS.register("various_world_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(VariousWorldItems.CRYSTAL_SWORD.get()))
                    .title(Component.translatable("creativetab.various_world_tab")).displayItems((pParameters, tabData) -> {
                        tabData.accept(VariousWorldItems.CRYSTAL_SWORD.get());
                        tabData.accept(VariousWorldItems.THUNDERBOLT_HAMMER.get());
                        tabData.accept(VariousWorldItems.CRYSTALIC_SLIMEBALL_SWORD.get());
                        tabData.accept(VariousWorldItems.CRYSTALIC_SLIMEBALL_PICKAXE.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_SWORD.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_PICKAXE.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_AXE.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_SHOVEL.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_HOE.get());
                        tabData.accept(VariousWorldItems.LORD_SWORD.get());
                        tabData.accept(VariousWorldItems.LORD_PICKAXE.get());
                        tabData.accept(VariousWorldItems.LORD_AXE.get());
                        tabData.accept(VariousWorldItems.LORD_SHOVEL.get());
                        tabData.accept(VariousWorldItems.LORD_HOE.get());
                        tabData.accept(VariousWorldItems.SCULK_SCYTHE.get());
                        tabData.accept(VariousWorldItems.NECROMANCER_WAND.get());
                        tabData.accept(VariousWorldItems.CRYSTALIC_BOW.get());
                        tabData.accept(VariousWorldItems.LORD_OF_FURIES_CROSSBOW.get());
                        tabData.accept(VariousWorldItems.CRYSTALIC_STRENGTH.get());
                        tabData.accept(VariousWorldItems.CRYSTAL_GEM.get());
                        tabData.accept(VariousWorldItems.LORD_FURY_SHARD.get());
                        tabData.accept(VariousWorldItems.MULTIPLE_ENDER_PEARL_ITEM.get());
                        tabData.accept(VariousWorldItems.TOTEM_OF_DARK_SPIRIT.get());
                        tabData.accept(VariousWorldItems.EXPLORER_NECKLACE.get());
                        tabData.accept(VariousWorldItems.STRENGH_AMULET.get());
                        tabData.accept(VariousWorldItems.REGENERATION_GEM.get());
                        tabData.accept(VariousWorldItems.AMETHYST_RING.get());
                        tabData.accept(VariousWorldItems.CRYSTAL_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldItems.CRYSTAL_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldItems.CRYSTAL_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldItems.CRYSTAL_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldItems.ANGEL_HELMET.get());
                        tabData.accept(VariousWorldItems.ANGEL_CHESTPLATE.get());
                        tabData.accept(VariousWorldItems.ANGEL_LEGGINGS.get());
                        tabData.accept(VariousWorldItems.ANGEL_BOOTS.get());
                        tabData.accept(VariousWorldItems.SCULK_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldItems.SCULK_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldItems.SCULK_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldItems.SCULK_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldItems.DARKNIUM_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldItems.FURY_HELMET.get());
                        tabData.accept(VariousWorldItems.FURY_CHESTPLATE.get());
                        tabData.accept(VariousWorldItems.FURY_LEGGINGS.get());
                        tabData.accept(VariousWorldItems.FURY_BOOTS.get());
                        tabData.accept(VariousWorldItems.LORD_FURY_HELMET.get());
                        tabData.accept(VariousWorldItems.LORD_FURY_CHESTPLATE.get());
                        tabData.accept(VariousWorldItems.LORD_FURY_LEGGINGS.get());
                        tabData.accept(VariousWorldItems.LORD_FURY_BOOTS.get());
                        tabData.accept(VariousWorldItems.SLIME_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldItems.SLIME_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldItems.SLIME_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldItems.SLIME_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldBlocks.CRYSTAL_CLUSTER.get().asItem());
                        tabData.accept(VariousWorldBlocks.SMALL_CRYSTAL_CLUSTER.get().asItem());
                        tabData.accept(VariousWorldBlocks.KUNZITE_COLOURFUL_CRYSTAL.get().asItem());
                        tabData.accept(VariousWorldBlocks.BUDDING_KUNZITE_COLOURFUL_CRYSTAL.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTAL_LEAVES.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_WOOD.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_LOG.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_PLANKS.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_FENCE.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_FENCE_GATE.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_DOOR.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_PRESSURE_PLATE.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_BUTTON.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_LEAVES.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_LOG.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_WOOD.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_PLANKS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_FENCE.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_FENCE_GATE.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_DOOR.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_PRESSURE_PLATE.get().asItem());
                        tabData.accept(VariousWorldBlocks.SAKURA_BUTTON.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_LEAVES.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_LOG.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_WOOD.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_PLANKS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_FENCE.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_FENCE_GATE.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_DOOR.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_PRESSURE_PLATE.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_BUTTON.get().asItem());
                        tabData.accept(VariousWorldBlocks.GNEISS.get().asItem());
                        tabData.accept(VariousWorldBlocks.COBBLED_GNEISS.get().asItem());
                        tabData.accept(VariousWorldBlocks.GNEISS_BRICKS.get().asItem());
                        tabData.accept(VariousWorldBlocks.GNEISS_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.GNEISS_BRICKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.GNEISS_BRICKS_WALL.get().asItem());
                        tabData.accept(VariousWorldBlocks.BLACKLY_STONY_MAGMA.get().asItem());
                        tabData.accept(VariousWorldBlocks.BLACKLY_STONY_MAGMA_BRICKS.get().asItem());
                        tabData.accept(VariousWorldBlocks.BLACKLY_STONY_MAGMA_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.BLACKLY_STONY_MAGMA_BRICKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.BLACKLY_STONY_MAGMA_BRICKS_WALL.get().asItem());
                        tabData.accept(VariousWorldBlocks.ENDER_BRICKS.get().asItem());
                        tabData.accept(VariousWorldBlocks.ENDER_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.ENDER_BRICKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.ENDER_BRICKS_WALL.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_BRICKS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_BRICK_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_BRICKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_BRICKS_WALL.get().asItem());
                        tabData.accept(VariousWorldBlocks.ROSE_QUARTZ.get().asItem());
                        tabData.accept(VariousWorldBlocks.ROSE_QUARTZ_SLAB.get().asItem());
                        tabData.accept(VariousWorldBlocks.ROSE_QUARTZ_STAIRS.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_GRASS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SHINY_GRASS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_GRASS.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_MAGMA.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_MOSS_BLOCK.get().asItem());
                        tabData.accept(VariousWorldBlocks.DEEP_MOSS.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTALIC_SLIME_BLOCK.get().asItem());
                        tabData.accept(VariousWorldBlocks.LORD_FURY_HEAD.get().asItem());
                        tabData.accept(VariousWorldBlocks.ARTIFACTTABLE.get().asItem());
                        tabData.accept(VariousWorldBlocks.ARMOR_STATION_BLOCK.get().asItem());
                        tabData.accept(VariousWorldBlocks.DISENCHANT_TABLE.get().asItem());
                        tabData.accept(VariousWorldBlocks.MYCOLOCYFAROGRAPH.get().asItem());
                        tabData.accept(VariousWorldItems.CRYSTALSHARD.get());
                        tabData.accept(VariousWorldItems.DARKSHARD.get());
                        tabData.accept(VariousWorldBlocks.CRYSTAL_BLOCK.get().asItem());
                        tabData.accept(VariousWorldBlocks.CRYSTAL_OF_CHARGED_BLOCK.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_GEM_ORE.get().asItem());
                        tabData.accept(VariousWorldBlocks.DEEPSLATE_SCULK_GEM_ORE.get().asItem());
                        tabData.accept(VariousWorldBlocks.SCULK_GEM_BLOCK.get().asItem());
                        tabData.accept(VariousWorldItems.RAW_SCULK_GEM.get());
                        tabData.accept(VariousWorldItems.SCULK_GEM.get());
                        tabData.accept(VariousWorldItems.SCULK_SHARD.get());
                        tabData.accept(VariousWorldBlocks.DARKNIUM_ORE.get().asItem());
                        tabData.accept(VariousWorldBlocks.DEEPSLATE_DARKNIUM_ORE.get().asItem());
                        tabData.accept(VariousWorldBlocks.DARKNIUM_BLOCK.get().asItem());
                        tabData.accept(VariousWorldItems.DARKNIUM_INGOT.get());
                        tabData.accept(VariousWorldItems.RAW_DARKNIUM_INGOT.get());
                        tabData.accept(VariousWorldItems.FURY_INGOT.get());
                        tabData.accept(VariousWorldItems.FURY_SCALES.get());
                        tabData.accept(VariousWorldBlocks.LORD_FURY_SCALES_BLOCK.get().asItem());
                        tabData.accept(VariousWorldItems.LORD_FURY_SCALE.get());
                        tabData.accept(VariousWorldItems.SLIME_CRYSTALIC.get());
                        tabData.accept(VariousWorldItems.GLOW_PURPLE_DYE.get());
                        tabData.accept(VariousWorldItems.CRYSTALIC_STICK.get());
                    }).build());

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            tabData.accept(VariousWorldItems.DARK_ZOMBIE_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.SCULK_SKELETON_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.DARK_FURY_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.CRYSTALIC_SLIME_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.SPIRITOF_PEACEFUL_WASTELAND_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.SPIRITOF_DEEP_CAVERN_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.ARMOREDSKELETON_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.WANDERING_SPIRIT_OF_SCULKS_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.ZOMBIE_OF_STONY_MAGMA_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.ZANY_VILER_WITCH_SPAWN_EGG.get());
            tabData.accept(VariousWorldItems.DROMOPHANT_SPAWN_EGG.get());
        }

        if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            tabData.accept(VariousWorldBlocks.MAGIC_VINES.get().asItem());
            tabData.accept(VariousWorldBlocks.SMALL_SCULK_BUSH.get().asItem());
            tabData.accept(VariousWorldBlocks.UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT.get().asItem());
            tabData.accept(VariousWorldBlocks.SCULK_GROWTHS.get().asItem());
            tabData.accept(VariousWorldBlocks.PURPLE_SAFFRON.get().asItem());
            tabData.accept(VariousWorldBlocks.SHINY_PLUMERIA.get().asItem());
            tabData.accept(VariousWorldBlocks.ANTHURIUM_SPROUTED_OF_MAGMA.get().asItem());
            tabData.accept(VariousWorldBlocks.BIG_CRYSHROOM_BLOCK.get().asItem());
            tabData.accept(VariousWorldItems.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK.get());
            tabData.accept(VariousWorldBlocks.SCULK_SAPLING.get().asItem());
            tabData.accept(VariousWorldBlocks.CRYSTALIC_OAK_SAPLING.get().asItem());
            tabData.accept(VariousWorldBlocks.MAGNOLIA_SAPLING.get().asItem());
            tabData.accept(VariousWorldBlocks.SHINY_SAPLING.get().asItem());
        }
        if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            tabData.accept(VariousWorldItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get());
            tabData.accept(VariousWorldItems.CRYSHROOM.get());
            tabData.accept(VariousWorldItems.SCULKBERRY.get());
            tabData.accept(VariousWorldItems.SCULK_FRUIT.get());
            tabData.accept(VariousWorldItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get());
            tabData.accept(VariousWorldItems.POTION_OF_DRAGON_EYE_EFFECT.get());
        }
        if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            tabData.accept(VariousWorldItems.CRYSTALIC_OAK_SIGN.get());
            tabData.accept(VariousWorldItems.SAKURA_SIGN.get());
            tabData.accept(VariousWorldItems.SCULK_SIGN.get());
        }
        if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            tabData.accept(VariousWorldItems.CRYSTALIC_OAK_BOAT);
            tabData.accept(VariousWorldItems.CRYSTALIC_OAK_CHEST_BOAT);
            tabData.accept(VariousWorldItems.MAGNOLIA_BOAT);
            tabData.accept(VariousWorldItems.MAGNOLIA_CHEST_BOAT);
            tabData.accept(VariousWorldItems.SCULK_BOAT);
            tabData.accept(VariousWorldItems.SCULK_CHEST_BOAT);
        }
    }
}