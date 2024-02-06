
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
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
import net.sashakyotoz.variousworld.VariousWorldMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VariousWorldModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VariousWorldMod.MODID);
    public static final RegistryObject<CreativeModeTab> VARIOUS_WORLD_TAB = CREATIVE_MODE_TABS.register("various_world_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(VariousWorldModItems.CRYSTAL_SWORD.get()))
                    .title(Component.translatable("creativetab.various_world_tab")).displayItems((pParameters, tabData) -> {
                        tabData.accept(VariousWorldModItems.CRYSTAL_SWORD.get());
                        tabData.accept(VariousWorldModItems.THUNDERBOLT_HAMMER.get());
                        tabData.accept(VariousWorldModItems.CRYSTALIC_SLIMEBALL_SWORD.get());
                        tabData.accept(VariousWorldModItems.CRYSTALIC_SLIMEBALL_PICKAXE.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_SWORD.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_PICKAXE.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_AXE.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_SHOVEL.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_HOE.get());
                        tabData.accept(VariousWorldModItems.LORD_SWORD.get());
                        tabData.accept(VariousWorldModItems.LORD_PICKAXE.get());
                        tabData.accept(VariousWorldModItems.LORD_AXE.get());
                        tabData.accept(VariousWorldModItems.LORD_SHOVEL.get());
                        tabData.accept(VariousWorldModItems.LORD_HOE.get());
                        tabData.accept(VariousWorldModItems.SCULK_SCYTHE.get());
                        tabData.accept(VariousWorldModItems.NECROMANCER_WAND.get());
                        tabData.accept(VariousWorldModItems.CRYSTALIC_BOW.get());
                        tabData.accept(VariousWorldModItems.LORD_OF_FURIES_CROSSBOW.get());
                        tabData.accept(VariousWorldModItems.CRYSTALIC_STRENGTH.get());
                        tabData.accept(VariousWorldModItems.CRYSTAL_GEM.get());
                        tabData.accept(VariousWorldModItems.LORD_FURY_SHARD.get());
                        tabData.accept(VariousWorldModItems.MULTIPLE_ENDER_PEARL_ITEM.get());
                        tabData.accept(VariousWorldModItems.TOTEM_OF_DARK_SPIRIT.get());
                        tabData.accept(VariousWorldModItems.EXPLORER_NECKLACE.get());
                        tabData.accept(VariousWorldModItems.STRENGH_AMULET.get());
                        tabData.accept(VariousWorldModItems.REGENERATION_GEM.get());
                        tabData.accept(VariousWorldModItems.AMETHYST_RING.get());
                        tabData.accept(VariousWorldModItems.CRYSTAL_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldModItems.CRYSTAL_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldModItems.CRYSTAL_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldModItems.CRYSTAL_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldModItems.ANGEL_HELMET.get());
                        tabData.accept(VariousWorldModItems.ANGEL_CHESTPLATE.get());
                        tabData.accept(VariousWorldModItems.ANGEL_LEGGINGS.get());
                        tabData.accept(VariousWorldModItems.ANGEL_BOOTS.get());
                        tabData.accept(VariousWorldModItems.SCULK_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldModItems.SCULK_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldModItems.SCULK_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldModItems.SCULK_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldModItems.DARKNIUM_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldModItems.FURY_HELMET.get());
                        tabData.accept(VariousWorldModItems.FURY_CHESTPLATE.get());
                        tabData.accept(VariousWorldModItems.FURY_LEGGINGS.get());
                        tabData.accept(VariousWorldModItems.FURY_BOOTS.get());
                        tabData.accept(VariousWorldModItems.LORD_FURY_HELMET.get());
                        tabData.accept(VariousWorldModItems.LORD_FURY_CHESTPLATE.get());
                        tabData.accept(VariousWorldModItems.LORD_FURY_LEGGINGS.get());
                        tabData.accept(VariousWorldModItems.LORD_FURY_BOOTS.get());
                        tabData.accept(VariousWorldModItems.SLIME_ARMOR_HELMET.get());
                        tabData.accept(VariousWorldModItems.SLIME_ARMOR_CHESTPLATE.get());
                        tabData.accept(VariousWorldModItems.SLIME_ARMOR_LEGGINGS.get());
                        tabData.accept(VariousWorldModItems.SLIME_ARMOR_BOOTS.get());
                        tabData.accept(VariousWorldModBlocks.CRYSTAL_CLUSTER.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SMALL_CRYSTAL_CLUSTER.get().asItem());
                        tabData.accept(VariousWorldModBlocks.KUNZITE_COLOURFUL_CRYSTAL.get().asItem());
                        tabData.accept(VariousWorldModBlocks.BUDDING_KUNZITE_COLOURFUL_CRYSTAL.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTAL_LEAVES.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_WOOD.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_LOG.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_PLANKS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_FENCE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_DOOR.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_LEAVES.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_LOG.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_WOOD.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_PLANKS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_FENCE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SAKURA_DOOR.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_LEAVES.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_LOG.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_WOOD.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_PLANKS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_FENCE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_PLANKS_TRAPDOOR.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_DOOR.get().asItem());
                        tabData.accept(VariousWorldModBlocks.GNEISS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.COBBLED_GNEISS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.GNEISS_BRICKS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.GNEISS_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.GNEISS_BRICKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.GNEISS_BRICKS_WALL.get().asItem());
                        tabData.accept(VariousWorldModBlocks.BLACKLY_STONY_MAGMA.get().asItem());
                        tabData.accept(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.BLACKLY_STONY_MAGMA_BRICKS_WALL.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ENDER_BRICKS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ENDER_BRICKS_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ENDER_BRICKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ENDER_BRICKS_WALL.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_BRICKS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_BRICK_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_BRIKS_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_BRICKS_FENCE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ROSE_QUARTZ.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ROSE_QUARTZ_SLAB.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ROSE_QUARTZ_STAIRS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_GRASS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SHINY_GRASS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_GRASS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_MAGMA.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_MOSS_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModBlocks.DEEP_MOSS.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTALIC_SLIME_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModBlocks.LORD_FURY_HEAD.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ARTIFACTTABLE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.ARMOR_STATION_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModBlocks.DISENCHANT_TABLE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.MYCOLOCYFAROGRAPH.get().asItem());
                        tabData.accept(VariousWorldModItems.CRYSTALSHARD.get());
                        tabData.accept(VariousWorldModItems.DARKSHARD.get());
                        tabData.accept(VariousWorldModBlocks.CRYSTAL_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModBlocks.CRYSTAL_OF_CHANGED_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_GEM_ORE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.DEEPSLATE_SCULK_GEM_ORE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.SCULK_GEM_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModItems.RAW_SCULK_GEM.get());
                        tabData.accept(VariousWorldModItems.SCULK_GEM.get());
                        tabData.accept(VariousWorldModItems.SCULK_SHARD.get());
                        tabData.accept(VariousWorldModBlocks.DARKNIUM_ORE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.DEEPSLATE_DARKNIUM_ORE.get().asItem());
                        tabData.accept(VariousWorldModBlocks.DARKNIUM_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModItems.DARKNIUM_INGOT.get());
                        tabData.accept(VariousWorldModItems.RAW_DARKNIUM_INGOT.get());
                        tabData.accept(VariousWorldModItems.FURY_INGOT.get());
                        tabData.accept(VariousWorldModItems.FURY_SCALES.get());
                        tabData.accept(VariousWorldModBlocks.LORD_FURY_SCALES_BLOCK.get().asItem());
                        tabData.accept(VariousWorldModItems.LORD_FURY_SCALES_DUST.get());
                        tabData.accept(VariousWorldModItems.SLIME_CRYSTALIC.get());
                        tabData.accept(VariousWorldModItems.GLOW_PURPLE_DYE.get());
                        tabData.accept(VariousWorldModItems.CRYSTALIC_STICK.get());
                    }).build());

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (tabData.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            tabData.accept(VariousWorldModBlocks.SAKURA_FENCE_GATE.get().asItem());
            tabData.accept(VariousWorldModBlocks.SAKURA_PRESSURE_PLATE.get().asItem());
            tabData.accept(VariousWorldModBlocks.SAKURA_BUTTON.get().asItem());
            tabData.accept(VariousWorldModBlocks.SCULK_FENCE_GATE.get().asItem());
            tabData.accept(VariousWorldModBlocks.SCULK_PRESSURE_PLATE.get().asItem());
            tabData.accept(VariousWorldModBlocks.SCULK_BUTTON.get().asItem());
            tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_FENCE_GATE.get().asItem());
            tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_PRESSURE_PLATE.get().asItem());
            tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_BUTTON.get().asItem());
        }

        if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            tabData.accept(VariousWorldModItems.DARK_ZOMBIE_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.SCULK_SKELETON_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.DARK_FURY_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.CRYSTALIC_SLIME_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.SPIRITOF_PEACEFUL_WASTELAND_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.SPIRITOF_DEEP_CAVERN_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.ARMOREDSKELETON_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.WANDERING_SPIRIT_SUMMONED_OF_SCULKS_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.ZOMBIE_OF_STONY_MAGMA_SPAWN_EGG.get());
            tabData.accept(VariousWorldModItems.ZANY_VILER_WITCH_SPAWN_EGG.get());
        }

        if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            tabData.accept(VariousWorldModBlocks.MAGIC_VINES.get().asItem());
            tabData.accept(VariousWorldModBlocks.SMALL_SCULK_BUSH.get().asItem());
            tabData.accept(VariousWorldModBlocks.UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT.get().asItem());
            tabData.accept(VariousWorldModBlocks.SCULK_GROWTHS.get().asItem());
            tabData.accept(VariousWorldModBlocks.PURPLE_SAFFRON.get().asItem());
            tabData.accept(VariousWorldModBlocks.SHINY_PLUMERIA.get().asItem());
            tabData.accept(VariousWorldModBlocks.ANTHURIUM_SPROUTED_OF_MAGMA.get().asItem());
            tabData.accept(VariousWorldModBlocks.BIG_CRYSHROOM_BLOCK.get().asItem());
            tabData.accept(VariousWorldModItems.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK.get());
            tabData.accept(VariousWorldModBlocks.SCULK_SAPLING.get().asItem());
            tabData.accept(VariousWorldModBlocks.CRYSTALIC_OAK_SAPLING.get().asItem());
            tabData.accept(VariousWorldModBlocks.MAGNOLIA_SAPLING.get().asItem());
            tabData.accept(VariousWorldModBlocks.SHINY_SAPLING.get().asItem());
        }
        if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            tabData.accept(VariousWorldModItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get());
            tabData.accept(VariousWorldModItems.CRYSHROOM.get());
            tabData.accept(VariousWorldModItems.SCULKBERRY.get());
            tabData.accept(VariousWorldModItems.SCULK_FRUIT.get());
            tabData.accept(VariousWorldModItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get());
            tabData.accept(VariousWorldModItems.POTION_OF_DRAGON_EYE_EFFECT.get());
        }
    }
}
