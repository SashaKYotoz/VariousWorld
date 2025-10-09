package net.sashakyotoz.variousworld.common.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.sashakyotoz.variousworld.VariousWorld;

public class ModCarvers {
    public static final ResourceKey<ConfiguredWorldCarver<?>> RECLAIMITE_CAVE = registerKey("reclaimite_cave");

    public static ResourceKey<ConfiguredWorldCarver<?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, VariousWorld.createVWLocation(name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
        context.register(
                RECLAIMITE_CAVE,
                WorldCarver.CAVE
                        .configured(
                                new CaveCarverConfiguration(
                                        0.35F,
                                        UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(128)),
                                        UniformFloat.of(0.25F, 0.5F),
                                        VerticalAnchor.aboveBottom(4),
                                        CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()),
                                        holdergetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES),
                                        UniformFloat.of(0.8F, 1.2F),
                                        UniformFloat.of(0.45F, 0.9F),
                                        ConstantFloat.of(-0.4F)
                                )
                        )
        );
    }
}