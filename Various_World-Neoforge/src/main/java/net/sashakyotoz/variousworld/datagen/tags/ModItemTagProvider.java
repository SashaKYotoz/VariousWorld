package net.sashakyotoz.variousworld.datagen.tags;

import net.lcc.sollib.api.common.registry.holder.ItemHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
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
        VWRegistryHelper.forEach(ItemHolder.class, holder -> {
            for (TagKey<Item> tag : holder.getTags())
                this.tag(tag).add(holder.get());
            if (holder.get() instanceof ArmorItem armorItem)
                this.tag(ItemTags.TRIMMABLE_ARMOR).add(armorItem);
        });
        this.tag(VWTags.Items.CRYSTALIC_OAK_LOGS).add(
                VWBlocks.CRYSTALIC_OAK_LOG.get().asItem(),
                VWBlocks.STRIPPED_CRYSTALIC_OAK_LOG.get().asItem(),
                VWBlocks.CRYSTALIC_OAK_WOOD.get().asItem(),
                VWBlocks.STRIPPED_CRYSTALIC_OAK_WOOD.get().asItem()
        );
        this.tag(ItemTags.SMALL_FLOWERS).add(VWBlocks.SHINY_PLUMERIA.get().asItem());
        this.tag(VWTags.Items.STRONG_ARTIFACT_CATALYSTS).add(Items.DRAGON_BREATH);
        this.tag(VWTags.Items.WEAK_ARTIFACT_CATALYSTS).add(Items.GLOWSTONE_DUST, VWItems.SODALITE_SHARD.get());
        this.tag(VWTags.Items.BLUE_JACARANDA_LOGS).add(
                VWBlocks.BLUE_JACARANDA_LOG.get().asItem(),
                VWBlocks.STRIPPED_BLUE_JACARANDA_LOG.get().asItem(),
                VWBlocks.STRIPPED_BLUE_JACARANDA_WOOD.get().asItem(),
                VWBlocks.BLUE_JACARANDA_WOOD.get().asItem()
        );
    }
}
