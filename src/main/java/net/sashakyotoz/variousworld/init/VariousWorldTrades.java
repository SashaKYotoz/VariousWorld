package net.sashakyotoz.variousworld.init;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VariousWorldTrades {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        if (event.getType() == VillagerProfession.ARMORER) {
            trades.get(5)
                    .add(new BasicItemListing(new ItemStack(VariousWorldItems.DARKNIUM_ARMOR_HELMET.get()), new ItemStack(VariousWorldItems.FURY_INGOT.get(), 4), new ItemStack(VariousWorldItems.FURY_HELMET.get()), 1, 25, 0.1f));
            trades.get(5)
                    .add(new BasicItemListing(new ItemStack(VariousWorldItems.DARKNIUM_ARMOR_CHESTPLATE.get()), new ItemStack(VariousWorldItems.FURY_INGOT.get(), 6), new ItemStack(VariousWorldItems.FURY_CHESTPLATE.get()), 1, 25, 0.1f));
            trades.get(5)
                    .add(new BasicItemListing(new ItemStack(VariousWorldItems.DARKNIUM_ARMOR_LEGGINGS.get()), new ItemStack(VariousWorldItems.FURY_INGOT.get(), 5), new ItemStack(VariousWorldItems.FURY_LEGGINGS.get()), 1, 25, 0.1f));
            trades.get(5)
                    .add(new BasicItemListing(new ItemStack(VariousWorldItems.DARKNIUM_ARMOR_BOOTS.get()), new ItemStack(VariousWorldItems.FURY_INGOT.get(), 3), new ItemStack(VariousWorldItems.FURY_BOOTS.get()), 10, 25, 0.1f));
        }
        if (event.getType() == VillagerProfession.MASON) {
            trades.get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(VariousWorldBlocks.ARTIFACTTABLE.get()), 1, 30, 0.15f));
        }
        if (event.getType() == VillagerProfession.CLERIC) {
            trades.get(3).add(new BasicItemListing(new ItemStack(Items.GLASS_BOTTLE), new ItemStack(Items.EMERALD, 12), new ItemStack(VariousWorldItems.POTION_OF_DRAGON_EYE_EFFECT.get()), 8, 15, 0.1f));
        }
        if (event.getType() == VillagerProfession.FLETCHER) {
            trades.get(5).add(new BasicItemListing(new ItemStack(Items.BOW), new ItemStack(Items.EMERALD, 32), new ItemStack(VariousWorldItems.CRYSTALIC_BOW.get()), 1, 25, 0.2f));
        }
        if (event.getType() == VariousWorldVillagerProfessions.CRYSTALLOGRAPHER.get()) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.AMETHYST_SHARD, 4),
                    new ItemStack(Items.EMERALD), 10, 3, 0.1f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(VariousWorldItems.SLIME_CRYSTALIC.get(), 2),
                    new ItemStack(Items.EMERALD), 10, 6, 0.1f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 4),
                    new ItemStack(VariousWorldItems.CRYSTALSHARD.get()), 10, 5, 0.05f));
            event.getTrades().get(2)
                    .add(new BasicItemListing(new ItemStack(VariousWorldItems.DARKSHARD.get()), new ItemStack(VariousWorldItems.CRYSTALSHARD.get(), 3), new ItemStack(VariousWorldBlocks.CRYSTAL_OF_CHARGED_BLOCK.get()), 1, 8, 0.25f));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(VariousWorldItems.SCULK_GEM.get()), new ItemStack(VariousWorldItems.SCULK_SHARD.get(), 3), new ItemStack(Items.EMERALD, 2), 10, 5, 0.05f));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 8), new ItemStack(VariousWorldItems.DARKSHARD.get(), 4), new ItemStack(VariousWorldItems.CRYSTALIC_STRENGTH.get()), 4, 5, 0.05f));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(VariousWorldBlocks.CRYSTALIC_GRASS.get()), new ItemStack(Items.EMERALD, 4), new ItemStack(Items.AMETHYST_SHARD, 2), 10, 10, 0.25f));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(VariousWorldItems.CRYSTAL_GEM.get()), new ItemStack(VariousWorldItems.DARKSHARD.get()), new ItemStack(VariousWorldItems.REGENERATION_GEM.get()), 1, 3, 0.15f));
        }
        if (event.getType() == VariousWorldVillagerProfessions.MYCOLOGIST.get()) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Items.EMERALD, 4), new ItemStack(VariousWorldItems.CRYSHROOM.get()), 8, 5, 0.1f));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(VariousWorldItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get()), new ItemStack(Items.EMERALD), 10, 5, 0.05f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), new ItemStack(Blocks.RED_MUSHROOM_BLOCK), new ItemStack(VariousWorldBlocks.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK.get()), 8, 5, 0.05f));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(VariousWorldBlocks.BIG_CRYSHROOM_BLOCK.get()),

                    new ItemStack(Items.AMETHYST_SHARD), 10, 5, 0.1f));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(VariousWorldBlocks.DEEP_MOSS.get()), new ItemStack(Items.EMERALD, 2), new ItemStack(VariousWorldItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get(), 2), 8, 5, 0.2f));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(VariousWorldBlocks.SCULK_MOSS_BLOCK.get()), new ItemStack(Blocks.BROWN_MUSHROOM, 4), new ItemStack(VariousWorldBlocks.SCULK_GROWTHS.get()), 10, 5, 0.05f));
        }
    }
}
