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
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldItems;

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
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("grass"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("trapdoor"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("wood"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("log"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("planks"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("pressure_plate"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("fence_gate"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("rose"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("sculk_brick"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("ender_bricks"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("blackly_stony"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("button"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("sapling"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        VariousWorldBlocks.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("sign"))
                .filter(blockRegistryObject -> !blockRegistryObject.get().getDescriptionId().contains("wall"))
                .forEach(blockRegistryObject -> dropSelf(blockRegistryObject.get()));
        this.add(VariousWorldBlocks.DARKNIUM_ORE.get(), block->createFortureAppliedOreDrops(block, VariousWorldItems.RAW_DARKNIUM_INGOT.get(), 1,3));
        this.add(VariousWorldBlocks.DEEPSLATE_DARKNIUM_ORE.get(), block->createFortureAppliedOreDrops(block, VariousWorldItems.RAW_DARKNIUM_INGOT.get(), 1,4));
        this.add(VariousWorldBlocks.SCULK_GEM_ORE.get(), block->createFortureAppliedOreDrops(block, VariousWorldItems.RAW_SCULK_GEM.get(), 1,4));
        this.add(VariousWorldBlocks.DEEPSLATE_SCULK_GEM_ORE.get(), block->createFortureAppliedOreDrops(block, VariousWorldItems.RAW_SCULK_GEM.get(), 1,5));
        this.dropSelf(VariousWorldBlocks.LORD_FURY_SCALES_BLOCK.get());
        this.dropSelf(VariousWorldBlocks.DARKNIUM_BLOCK.get());
        this.dropSelf(VariousWorldBlocks.SCULK_GEM_BLOCK.get());
    }
    private LootTable.Builder createFortureAppliedOreDrops(Block block, Item item, int min, int max) {
        return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
    private LootTable.Builder silkTouchNeeded(Block block, Item item) {
        return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block).when(HAS_SILK_TOUCH).otherwise(LootItem.lootTableItem(item))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return VariousWorldBlocks.BLOCKS.getEntries().stream().filter(blockRegistryObject -> blockRegistryObject.get().getDescriptionId().contains("grass")
                || blockRegistryObject.get().getDescriptionId().contains("trapdoor") || blockRegistryObject.get().getDescriptionId().contains("log")
                || blockRegistryObject.get().getDescriptionId().contains("planks")
                || blockRegistryObject.get().getDescriptionId().contains("pressure_plate")
                || blockRegistryObject.get().getDescriptionId().contains("fence_gate")
                || blockRegistryObject.get().getDescriptionId().contains("rose")
                || blockRegistryObject.get().getDescriptionId().contains("sculk_brick")
                || blockRegistryObject.get().getDescriptionId().contains("ender_bricks")
                || blockRegistryObject.get().getDescriptionId().contains("lord_fury_scales_block")
                || blockRegistryObject.get().getDescriptionId().contains("blackly_stony")
                || blockRegistryObject.get().getDescriptionId().contains("darknium_block")
                || blockRegistryObject.get().getDescriptionId().contains("sculk_gem_block")
                || blockRegistryObject.get().getDescriptionId().contains("button")
                || (blockRegistryObject.get().getDescriptionId().contains("sign") && !blockRegistryObject.get().getDescriptionId().contains("wall"))
                || blockRegistryObject.get().getDescriptionId().contains("ore")
                || blockRegistryObject.get().getDescriptionId().contains("sapling")
                || blockRegistryObject.get().getDescriptionId().contains("wood")).map(RegistryObject::get)::iterator;
    }
}
