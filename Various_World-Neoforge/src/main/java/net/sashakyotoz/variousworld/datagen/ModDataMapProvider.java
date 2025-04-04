package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        for (Map.Entry<ItemLike, Integer> entry : VWRegistryHelper.ITEM_BURNABLE.entrySet())
            this.builder(NeoForgeDataMaps.FURNACE_FUELS).add(entry.getKey().asItem().builtInRegistryHolder(), new FurnaceFuel(entry.getValue()), false);
    }
}
