package net.sashakyotoz.variousworld.common.world.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BlocksChainFeature extends Feature<BlockStateConfiguration> {

    public BlocksChainFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        BlockState chainState = context.config().state;
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        int maxSearchDistance = 12 + random.nextInt(13);
        if (tryPlaceAlongAxis(level, origin, chainState, Direction.Axis.X, maxSearchDistance, random)) return true;
        return tryPlaceAlongAxis(level, origin, chainState, Direction.Axis.Z, maxSearchDistance, random);
    }

    private boolean tryPlaceAlongAxis(WorldGenLevel level, BlockPos origin, BlockState chainState, Direction.Axis axis, int maxSearchDistance, RandomSource random) {
        Direction negative = (axis == Direction.Axis.X) ? Direction.WEST : Direction.NORTH;
        Direction positive = (axis == Direction.Axis.X) ? Direction.EAST : Direction.SOUTH;

        BlockPos wallNeg = findFirstNonEmpty(level, origin, negative, maxSearchDistance);
        BlockPos wallPos = findFirstNonEmpty(level, origin, positive, maxSearchDistance);

        if (wallNeg == null || wallPos == null) return false;

        int coordNeg = (axis == Direction.Axis.X) ? wallNeg.getX() : wallNeg.getZ();
        int coordPos = (axis == Direction.Axis.X) ? wallPos.getX() : wallPos.getZ();

        if (Math.abs(coordPos - coordNeg) <= 1) return false;
        int startCoord = Math.min(coordNeg, coordPos) + 1;
        int endCoord = Math.max(coordNeg, coordPos) - 1;
        int y = origin.getY();

        BlockPos start = (axis == Direction.Axis.X)
                ? new BlockPos(startCoord, y, origin.getZ())
                : new BlockPos(origin.getX(), y, startCoord);

        BlockPos end = (axis == Direction.Axis.X)
                ? new BlockPos(endCoord, y, origin.getZ())
                : new BlockPos(origin.getX(), y, endCoord);
        if (!rectangularGapIsEmpty(level, start, end)) return false;
        int samples = Math.max((int) Math.ceil(start.distSqr(end) * 0.5), 12);
        samples = Math.min(samples, 256);

        List<BlockPos> curvePositions;
        for (int attempt = 0; attempt < 3; attempt++) {
            double sideOffset = computeSideOffset(start, end, random);
            double sag = computeSag(start, end, random);
            curvePositions = generateCurvePositions(start, end, axis, sideOffset, sag, samples, random);
            int currSamples = samples;
            while ((!isFaceConnectedSequence(curvePositions)) && currSamples <= 1024) {
                currSamples *= 2;
                if (currSamples > 1024) break;
                curvePositions = generateCurvePositions(start, end, axis, sideOffset, sag, currSamples, random);
            }

            if (isFaceConnectedSequence(curvePositions)) {
                // final emptiness check: make sure each position is empty
                boolean allEmpty = true;
                for (BlockPos p : curvePositions) {
                    if (!level.isEmptyBlock(p)) {
                        allEmpty = false;
                        break;
                    }
                }
                if (allEmpty) {
                    // place blocks
                    for (BlockPos p : curvePositions) {
                        level.setBlock(p, chainState, 2);
                    }
                    return true;
                }
            }
            samples = Math.min(samples * 2, 512);
        }

        return false;
    }

    private static double computeSideOffset(BlockPos a, BlockPos b, RandomSource random) {
        double dx = b.getX() - a.getX();
        double dz = b.getZ() - a.getZ();
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        double base = Math.max(1.0, horizontal) * (0.15 + random.nextDouble() * 0.45);
        return (random.nextBoolean() ? 1 : -1) * base;
    }

    private static double computeSag(BlockPos a, BlockPos b, RandomSource random) {
        double dx = b.getX() - a.getX();
        double dz = b.getZ() - a.getZ();
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        double sag = horizontal * (0.05 + random.nextDouble() * 0.20);
        return -sag;
    }
    private List<BlockPos> generateCurvePositions(BlockPos start, BlockPos end, Direction.Axis axis, double sideOffset, double sag, int samples, RandomSource random) {
        double mx = (start.getX() + end.getX()) * 0.5;
        double my = (start.getY() + end.getY()) * 0.5;
        double mz = (start.getZ() + end.getZ()) * 0.5;
        double cx = mx;
        double cy = my + sag;
        double cz = mz;
        if (axis == Direction.Axis.X)
            cz += sideOffset;
         else
            cx += sideOffset;
        Set<BlockPos> ordered = new LinkedHashSet<>();
        for (int i = 0; i <= samples; i++) {
            double t = (double) i / (double) samples;
            double omt = 1.0 - t;
            double bx = omt * omt * start.getX() + 2 * omt * t * cx + t * t * end.getX();
            double by = omt * omt * start.getY() + 2 * omt * t * cy + t * t * end.getY();
            double bz = omt * omt * start.getZ() + 2 * omt * t * cz + t * t * end.getZ();
            int ix = (int) Math.round(bx);
            int iy = (int) Math.round(by);
            int iz = (int) Math.round(bz);
            ordered.add(new BlockPos(ix, iy, iz));
        }
        return new ArrayList<>(ordered);
    }

    private boolean rectangularGapIsEmpty(WorldGenLevel level, BlockPos a, BlockPos b) {
        int minX = Math.min(a.getX(), b.getX());
        int maxX = Math.max(a.getX(), b.getX());
        int minZ = Math.min(a.getZ(), b.getZ());
        int maxZ = Math.max(a.getZ(), b.getZ());
        int y = a.getY();
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                if (!level.isEmptyBlock(new BlockPos(x, y, z))) return false;
            }
        }
        return true;
    }

    private boolean isFaceConnectedSequence(List<BlockPos> list) {
        if (list == null || list.isEmpty()) return false;
        for (int i = 1; i < list.size(); i++) {
            if (!areFaceAdjacent(list.get(i - 1), list.get(i))) return false;
        }
        return true;
    }

    private boolean areFaceAdjacent(BlockPos a, BlockPos b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());
        int dz = Math.abs(a.getZ() - b.getZ());
        return (dx + dy + dz) == 1;
    }

    private BlockPos findFirstNonEmpty(WorldGenLevel level, BlockPos start, Direction dir, int maxDist) {
        for (int i = 1; i <= maxDist; i++) {
            BlockPos p = start.relative(dir, i);
            if (!level.isEmptyBlock(p)) {
                return p;
            }
        }
        return null;
    }
}