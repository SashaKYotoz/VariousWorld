package net.sashakyotoz.variousworld.data;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

import java.util.Set;

public class VariousWorldBlockLootTableProvider extends BlockLootSubProvider {
    protected static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);

    public VariousWorldBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        VariousWorldModBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("grass"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldModBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("trapdoor"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldModBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("wood"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldModBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("log"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldModBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("planks"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldModBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("pressure_plate"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldModBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("fence_gate"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
    }

    private LootTable.Builder silkTouchNeeded(Block block, Item item) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block).when(HAS_SILK_TOUCH).otherwise(LootItem.lootTableItem(item))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return VariousWorldModBlocks.BLOCKS.getEntries().stream().filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("grass")
                || blockRegistryObject.get().getDescriptionId().contains("trapdoor") || blockRegistryObject.get().getDescriptionId().contains("log")
                || blockRegistryObject.get().getDescriptionId().contains("planks")
                || blockRegistryObject.get().getDescriptionId().contains("pressure_plate")
                || blockRegistryObject.get().getDescriptionId().contains("fence_gate")
                || blockRegistryObject.get().getDescriptionId().contains("wood")).map(RegistryObject::get)::iterator;
    }
}
