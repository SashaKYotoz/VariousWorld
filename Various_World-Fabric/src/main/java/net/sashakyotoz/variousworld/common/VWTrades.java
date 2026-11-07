package net.sashakyotoz.variousworld.common;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWVillagers;

import java.util.Optional;
import java.util.function.BiConsumer;

public final class VWTrades {
    private VWTrades() {
    }

    public static void fill(VillagerProfession profession, BiConsumer<Integer, VillagerTrades.ItemListing> trades) {
        if (profession == VWVillagers.GEMSMITHER.get()) {
            trades.accept(1, trade(new ItemStack(Items.AMETHYST_SHARD, 4), new ItemStack(Items.EMERALD), 10, 3, 0.1f));
            trades.accept(1, trade(new ItemStack(VWItems.CRYSTALLINE_SLIME_BALL.get(), 2), new ItemStack(Items.EMERALD), 10, 6, 0.1f));
            trades.accept(2, trade(new ItemStack(Items.EMERALD, 4), new ItemStack(VWItems.SODALITE_SHARD.get()), 10, 5, 0.05f));
            trades.accept(2, trade(new ItemStack(Items.EMERALD, 8), new ItemStack(Items.IRON_AXE),
                    OnActionsTrigger.returnStackWithGem(Items.IRON_AXE.getDefaultInstance(), Items.AMETHYST_SHARD.getDefaultInstance()), 1, 8, 0.25f));
            trades.accept(3, trade(new ItemStack(Items.EMERALD), new ItemStack(Items.AMETHYST_SHARD, 2), 10, 5, 0.05f));
            trades.accept(4, trade(new ItemStack(VWItems.CRYSTALLINE_SLIME_BALL.get()), new ItemStack(Items.EMERALD, 1), new ItemStack(Items.SLIME_BALL, 2), 10, 10, 0.25f));
            trades.accept(5, trade(new ItemStack(Items.EMERALD, 11), new ItemStack(Items.DIAMOND_PICKAXE),
                    OnActionsTrigger.returnStackWithGem(Items.DIAMOND_PICKAXE.getDefaultInstance(), new ItemStack(VWItems.SODALITE_SHARD.get())), 1, 3, 0.15f));
        }
        if (profession == VWVillagers.ARTIFACTOLOGIST.get()) {
            trades.accept(1, trade(new ItemStack(Items.EMERALD, 8), new ItemStack(VWItems.CRYSTALLINE_STRENGTH.get()), 8, 5, 0.1f));
            trades.accept(1, trade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.REDSTONE, 2), 8, 5, 0.1f));
            trades.accept(1, trade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.SUGAR, 2), 8, 5, 0.1f));
            trades.accept(2, trade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.GLOWSTONE, 2), 8, 5, 0.1f));
            trades.accept(2, trade(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.EMERALD), 8, 5, 0.1f));
            trades.accept(2, trade(new ItemStack(Items.GLOWSTONE, 3), new ItemStack(Items.EMERALD), 8, 5, 0.1f));
            trades.accept(3, trade(new ItemStack(VWItems.CRYSTALLINE_STRENGTH.get()), new ItemStack(Items.ECHO_SHARD), new ItemStack(Items.RECOVERY_COMPASS), 2, 5, 0.1f));
            trades.accept(4, trade(new ItemStack(Items.EMERALD, 16), new ItemStack(VWBlocks.ARTIFACT_TABLE.get()), 1, 5, 0.1f));
        }
    }

    private static VillagerTrades.ItemListing trade(ItemStack cost, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        return trade(cost, ItemStack.EMPTY, result, maxUses, xp, priceMultiplier);
    }

    private static VillagerTrades.ItemListing trade(ItemStack cost, ItemStack secondCost, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        return (trader, random) -> new MerchantOffer(
                new ItemCost(cost.getItem(), cost.getCount()),
                secondCost.isEmpty() ? Optional.empty() : Optional.of(new ItemCost(secondCost.getItem(), secondCost.getCount())),
                result.copy(), maxUses, xp, priceMultiplier);
    }
}
