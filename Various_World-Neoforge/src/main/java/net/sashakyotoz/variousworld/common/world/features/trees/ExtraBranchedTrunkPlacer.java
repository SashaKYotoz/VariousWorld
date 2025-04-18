package net.sashakyotoz.variousworld.common.world.features.trees;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.sashakyotoz.variousworld.init.VWFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ExtraBranchedTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<ExtraBranchedTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            trunkPlacerParts(instance).apply(instance, ExtraBranchedTrunkPlacer::new)
    );

    public ExtraBranchedTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return VWFeatures.EXTRA_BRANCHED_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader reader,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            int height,
            BlockPos origin,
            TreeConfiguration config
    ) {
        List<FoliagePlacer.FoliageAttachment> foliageAttachments = new ArrayList<>();
        BlockState logState = config.trunkProvider.getState(random, origin);
        for (int y = 0; y < height; y++) {
            BlockPos pos = origin.above(y);
            trunkSetter.accept(pos, logState);
        }
        foliageAttachments.add(new FoliagePlacer.FoliageAttachment(origin.above(height), 0, false));

        int branchCount = 3 + random.nextInt(2);
        for (int i = 0; i < branchCount; i++) {
            Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            int branchStartY = (int) ((0.5 + random.nextFloat() * 0.3) * height);
            BlockPos branchBase = origin.above(branchStartY);
            int branchLength = 2 + random.nextInt(3);
            for (int j = 1; j <= branchLength; j++) {
                BlockPos branchPos = branchBase.relative(dir, j)
                        .above((j + 1) / 2);
                trunkSetter.accept(branchPos, logState);
                if (j == branchLength)
                    foliageAttachments.add(new FoliagePlacer.FoliageAttachment(branchPos, 0, false));
            }
        }

        return foliageAttachments;
    }
}