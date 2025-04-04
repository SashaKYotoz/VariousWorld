package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
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
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return VWRegistryHelper.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}