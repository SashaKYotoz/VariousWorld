package net.sashakyotoz.variousworld.common.config;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.sashakyotoz.variousworld.VariousWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

//following code takes its place from magic of Lyof:
//https://github.com/Lyof429/EndsPhantasm/blob/master/src/main/java/net/lyof/phantasm/setup/datagen/config/ConfiguredDataResourcePack.java
public class ConfiguredDataResourcePack implements PackResources {
    public static final ConfiguredDataResourcePack INSTANCE = new ConfiguredDataResourcePack();

    @Override
    public @Nullable IoSupplier<InputStream> getRootResource(String... strings) {
        return null;
    }

    @Override
    public @Nullable IoSupplier<InputStream> getResource(PackType packType, ResourceLocation resourceLocation) {
        return null;
    }

    @Override
    public void listResources(PackType packType, String s, String s1, ResourceOutput resourceOutput) {

    }

    @Override
    public Set<String> getNamespaces(PackType packType) {
        return Set.of();
    }

    @Override
    public @Nullable <T> T getMetadataSection(MetadataSectionType<T> metadataSectionType) {
        return null;
    }

    @Override
    public @NotNull PackLocationInfo location() {
        return new PackLocationInfo("various_world_configured_data", Component.literal("Various World Configured Data"),
                PackSource.DEFAULT, Optional.of(new KnownPack(VariousWorld.MOD_ID,"configured_data","1.0")));
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isHidden() {
        return PackResources.super.isHidden();
    }
}
