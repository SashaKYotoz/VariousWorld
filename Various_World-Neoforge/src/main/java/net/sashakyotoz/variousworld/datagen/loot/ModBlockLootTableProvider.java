package net.sashakyotoz.variousworld.datagen.loot;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        for (Map.Entry<DeferredBlock<?>, ItemLike> entry : VWRegistryHelper.BLOCK_DROPS.entrySet()) {
            if (VWRegistryHelper.BLOCK_MODELS.get(VWRegistryHelper.Models.SLAB) != null && VWRegistryHelper.BLOCK_MODELS.get(VWRegistryHelper.Models.SLAB).contains(entry.getKey()))
                add(entry.getKey().get(), createSlabItemTable(entry.getKey().get()));
            else if (VWRegistryHelper.BLOCK_MODELS.get(VWRegistryHelper.Models.DOOR) != null && VWRegistryHelper.BLOCK_MODELS.get(VWRegistryHelper.Models.DOOR).contains(entry.getKey()))
                add(entry.getKey().get(), createDoorTable(entry.getKey().get()));
            else if (VWRegistryHelper.BLOCK_SILK_DROPS.containsKey(entry.getKey().get()))
                createOreDrop(entry.getKey().get(), entry.getValue().asItem());
            else
                dropOther(entry.getKey().get(), entry.getValue());
        }
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
        this.add(VWBlocks.BUDDING_SODALITE.get(), noDrop());
        this.add(VWBlocks.SODALITE_CLUSTER.get(), block -> this.createSilkTouchDispatchTable(
                block,
                LootItem.lootTableItem(Items.AMETHYST_SHARD)
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

        add(VWBlocks.BLUE_JACARANDA_PETALS.get(), this::createPetalsDrops);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return VWRegistryHelper.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}