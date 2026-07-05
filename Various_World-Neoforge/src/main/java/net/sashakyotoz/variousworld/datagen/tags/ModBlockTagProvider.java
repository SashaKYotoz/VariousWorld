package net.sashakyotoz.variousworld.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;
import net.sashakyotoz.variousworld.init.VWTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VariousWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (TagKey<Block> tag : VWRegistryHelper.BLOCK_TAGS.keySet()) {
            for (Supplier<Block> block : VWRegistryHelper.BLOCK_TAGS.get(tag)) {
                this.tag(tag).add(block.get());
            }
        }
        this.tag(VWTags.Blocks.COMPASS_FINDABLE_CLUSTERS).add(
                VWBlocks.SODALITE_CLUSTER.get(),
                VWBlocks.MEDIUM_SODALITE_BUD.get(),
                VWBlocks.SMALL_SODALITE_BUD.get(),
                Blocks.AMETHYST_CLUSTER,
                Blocks.LARGE_AMETHYST_BUD,
                Blocks.MEDIUM_AMETHYST_BUD,
                Blocks.SMALL_AMETHYST_BUD
        );
    }
}