package net.sashakyotoz.variousworld.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.lcc.sollib.api.common.registry.holder.EntityHolder;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModEntityTagProvider extends EntityTypeTagsProvider {

    public ModEntityTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, VariousWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        VWRegistryHelper.forEach(EntityHolder.class, holder -> {
            for (TagKey<EntityType<?>> tag : holder.getTags())
                this.tag(tag).add(holder.get());
        });
    }
}