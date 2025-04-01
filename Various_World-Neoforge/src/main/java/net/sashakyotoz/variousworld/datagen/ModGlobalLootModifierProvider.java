package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, VariousWorld.MOD_ID);
    }

    @Override
    protected void start() {
//        this.add("radish_seeds_to_short_grass",
//                new AddItemModifier(new LootItemCondition[] {
//                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
//                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.RADISH_SEEDS.get()));
//        this.add("radish_seeds_to_tall_grass",
//                new AddItemModifier(new LootItemCondition[] {
//                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
//                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.RADISH_SEEDS.get()));
    }
}
