package net.sashakyotoz.variousworld.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;
import net.sashakyotoz.variousworld.init.VWTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, VariousWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (TagKey<Item> tag : VWRegistryHelper.ITEM_TAGS.keySet()) {
            for (Item item : VWRegistryHelper.ITEM_TAGS.get(tag))
                this.tag(tag).add(item);
            this.tag(VWTags.Items.CRYSTALIC_OAK_LOGS).add(
                    VWBlocks.CRYSTALIC_OAK_LOG.get().asItem(),
                    VWBlocks.STRIPPED_CRYSTALIC_OAK_LOG.get().asItem()
            );
        }
        for (DeferredHolder<Item, ? extends Item> item : VWRegistryHelper.ITEMS.getEntries()) {
            if (item.get() instanceof ArmorItem armorItem)
                this.tag(ItemTags.TRIMMABLE_ARMOR).add(armorItem);
        }
    }
}
