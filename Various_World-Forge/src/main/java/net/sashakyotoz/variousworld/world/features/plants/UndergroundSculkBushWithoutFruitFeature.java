
package net.sashakyotoz.variousworld.world.features.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;

import net.sashakyotoz.variousworld.init.VWBlocks;

import java.util.Set;

public class UndergroundSculkBushWithoutFruitFeature extends RandomPatchFeature {
    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

    public UndergroundSculkBushWithoutFruitFeature() {
        super(RandomPatchConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel world = context.level();
        if (!generate_dimensions.contains(world.getLevel().dimension()))
            return false;
        BlockPos pos = context.origin();
        if ((world.getBlockState(pos.below())).is(VWBlocks.SCULK_GRASS.get())) {
            int sx;
            int sy;
            int sz;
            BlockPos pos1 = pos.below(21);
            sx = -5;
            for (int i = 0; i < 8; i++) {
                sy = -5;
                for (int j = 0; j < 8; j++) {
                    sz = -5;
                    for (int k = 0; k < 8; k++) {
                        if (world.getBlockState(pos1.offset(sx, sy, sz)).is(BlockTags.BASE_STONE_OVERWORLD)) {
                            BlockPos pos2 = pos1.offset(sx, sy, sz);
                            BlockState state = Blocks.AIR.defaultBlockState();
                            if (Math.random() < 0.5)
                                state = VWBlocks.SCULK_MOSS_BLOCK.get().defaultBlockState();
                            else if (Math.random() < 0.25)
                                state = VWBlocks.SCULK_GROWTHS.get().defaultBlockState();
                            else if (Math.random() < 0.125)
                                state = VWBlocks.UNDERGROUND_SCULK_FRUIT_BUSH.get().defaultBlockState();
                            world.setBlock(pos2, state, 3);
                        }
                        sz = sz + 1;
                    }
                    sy = sy + 1;
                }
                sx = sx + 1;
            }
        }
        return true;
    }
}