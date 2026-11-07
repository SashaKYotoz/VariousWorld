package net.sashakyotoz.variousworld.datagen.loot;

import net.lcc.sollib.api.common.registry.data.block.BlockModel;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        VWRegistryHelper.forEach(BlockHolder.class, holder -> {
            if (!holder.hasDrop())
                return;
            Block block = holder.get();
            if (holder.getModel() == BlockModel.SLAB)
                add(block, createSlabItemTable(block));
            else if (holder.getModel() == BlockModel.DOOR)
                add(block, createDoorTable(block));
            else
                dropOther(block, holder.getDrop().get());
        });
        add(VWBlocks.CRYSTALIC_OAK_LEAVES.get(), block -> createLeavesDrops(block, VWBlocks.CRYSTALIC_OAK_SAPLING.get(), 0.25f));
        add(VWBlocks.BLUE_JACARANDA_LEAVES.get(), block -> createLeavesDrops(block, VWBlocks.BLUE_JACARANDA_SAPLING.get(), 0.25f));
        add(VWBlocks.SODALITE_WART.get(), block -> this.createSilkTouchDispatchTable(
                block,
                LootItem.lootTableItem(VWItems.SODALITE_SHARD.get())
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(
                                this.applyExplosionDecay(
                                        block, LootItem.lootTableItem(VWItems.SODALITE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                )
                        )
        ));
        this.dropPottedContents(VWBlocks.POTTED_SODALITE_WART.get());
        this.dropPottedContents(VWBlocks.POTTED_CRYSTALIC_OAK_SAPLING.get());
        this.dropPottedContents(VWBlocks.POTTED_BLUE_JACARANDA_SAPLING.get());
        this.dropPottedContents(VWBlocks.POTTED_SHINY_PLUMERIA.get());
        this.add(VWBlocks.BUDDING_SODALITE.get(), noDrop());
        this.add(VWBlocks.SODALITE_CLUSTER.get(), block -> this.createSilkTouchDispatchTable(
                block,
                LootItem.lootTableItem(VWItems.SODALITE_SHARD.get())
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(
                                this.applyExplosionDecay(
                                        block, LootItem.lootTableItem(Items.AMETHYST_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                )
                        )
        ));
        this.dropWhenSilkTouch(VWBlocks.MEDIUM_SODALITE_BUD.get());
        this.dropWhenSilkTouch(VWBlocks.SMALL_SODALITE_BUD.get());

        this.add(VWBlocks.RECLAIMITE_CRYSTAL.get(), block -> this.createSilkTouchDispatchTable(
                block,
                LootItem.lootTableItem(VWItems.RECLAIMITE_SHARD.get())
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(
                                this.applyExplosionDecay(
                                        block, LootItem.lootTableItem(VWItems.RECLAIMITE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                )
                        )
        ));

        this.add(VWBlocks.BLUE_JACARANDA_PETALS.get(), this::createPetalsDrops);
        this.add(VWBlocks.CRYSTALIC_GRASS_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, VWBlocks.DIRT_WITH_CRYSTALS.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ArrayList<>();
        VWRegistryHelper.forEach(BlockHolder.class, holder -> blocks.add(holder.get()));
        return blocks;
    }
}