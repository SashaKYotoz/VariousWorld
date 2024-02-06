
package net.sashakyotoz.variousworld.world.features.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.procedures.SculkMossBlockSpawnCaveProcedure;

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
        int x = context.origin().getX();
        int y = context.origin().getY();
        int z = context.origin().getZ();
        if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == VariousWorldModBlocks.SCULK_GRASS.get())
            SculkMossBlockSpawnCaveProcedure.execute(world, x, (y - 24), z);
        return true;
    }
}
