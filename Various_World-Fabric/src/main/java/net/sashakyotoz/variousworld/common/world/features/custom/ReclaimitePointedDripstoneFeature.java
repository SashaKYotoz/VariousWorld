package net.sashakyotoz.variousworld.common.world.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.PointedDripstoneConfiguration;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;

import java.util.Optional;
import java.util.function.Consumer;

public class ReclaimitePointedDripstoneFeature extends Feature<PointedDripstoneConfiguration> {
    public ReclaimitePointedDripstoneFeature(Codec<PointedDripstoneConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<PointedDripstoneConfiguration> context) {
        LevelAccessor levelaccessor = context.level();
        BlockPos blockpos = context.origin();
        RandomSource randomsource = context.random();
        PointedDripstoneConfiguration pointeddripstoneconfiguration = context.config();
        Optional<Direction> optional = getTipDirection(levelaccessor, blockpos, randomsource);
        if (optional.isEmpty()) {
            return false;
        } else {
            BlockPos blockpos1 = blockpos.relative(optional.get().getOpposite());
            createPatchOfDripstoneBlocks(levelaccessor, randomsource, blockpos1, pointeddripstoneconfiguration);
            int i = randomsource.nextFloat() < pointeddripstoneconfiguration.chanceOfTallerDripstone && isEmptyOrWater(levelaccessor.getBlockState(blockpos.relative(optional.get()))) ? 2 : 1;
            growPointedDripstone(levelaccessor, blockpos, optional.get(), randomsource, i);
            return true;
        }
    }

    private static Optional<Direction> getTipDirection(LevelAccessor level, BlockPos pos, RandomSource random) {
        boolean flag = isDripstoneBase(level.getBlockState(pos.above()));
        boolean flag1 = isDripstoneBase(level.getBlockState(pos.below()));
        if (flag && flag1) {
            return Optional.of(random.nextBoolean() ? Direction.DOWN : Direction.UP);
        } else if (flag) {
            return Optional.of(Direction.DOWN);
        } else {
            return flag1 ? Optional.of(Direction.UP) : Optional.empty();
        }
    }

    private static void createPatchOfDripstoneBlocks(LevelAccessor level, RandomSource random, BlockPos pos, PointedDripstoneConfiguration config) {
        placeDripstoneBlockIfPossible(level, pos);

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!(random.nextFloat() > config.chanceOfDirectionalSpread)) {
                BlockPos blockpos = pos.relative(direction);
                placeDripstoneBlockIfPossible(level, blockpos);
                if (!(random.nextFloat() > config.chanceOfSpreadRadius2)) {
                    BlockPos blockpos1 = blockpos.relative(Direction.getRandom(random));
                    placeDripstoneBlockIfPossible(level, blockpos1);
                    if (!(random.nextFloat() > config.chanceOfSpreadRadius3)) {
                        BlockPos blockpos2 = blockpos1.relative(Direction.getRandom(random));
                        placeDripstoneBlockIfPossible(level, blockpos2);
                    }
                }
            }
        }
    }

    protected static void buildBaseToTipColumn(Direction direction, int height, RandomSource random, Consumer<BlockState> blockSetter) {
        if (height >= 3) {
            blockSetter.accept(createPointedDripstone(direction, DripstoneThickness.BASE, false));
            for (int i = 0; i < height - 3; ++i) {
                blockSetter.accept(createPointedDripstone(direction, DripstoneThickness.MIDDLE, false));
            }
        }
        if (height >= 2)
            blockSetter.accept(createPointedDripstone(direction, DripstoneThickness.FRUSTUM, false));
        if (height >= 1)
            blockSetter.accept(createPointedDripstone(direction, DripstoneThickness.TIP, random.nextFloat() > 0.2));
    }

    protected static void growPointedDripstone(LevelAccessor level, BlockPos pos, Direction direction, RandomSource random, int height) {
        if (isDripstoneBase(level.getBlockState(pos.relative(direction.getOpposite())))) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
            buildBaseToTipColumn(direction, height, random, (state) -> {
                if (state.is(Blocks.POINTED_DRIPSTONE)) {
                    state = state.setValue(PointedDripstoneBlock.WATERLOGGED, level.isWaterAt(blockpos$mutableblockpos));
                }

                level.setBlock(blockpos$mutableblockpos, state, 2);
                blockpos$mutableblockpos.move(direction);
            });
        }

    }

    protected static void placeDripstoneBlockIfPossible(LevelAccessor level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.is(BlockTags.DRIPSTONE_REPLACEABLE))
            level.setBlock(pos, Blocks.DRIPSTONE_BLOCK.defaultBlockState(), 2);
    }

    private static BlockState createPointedDripstone(Direction direction, DripstoneThickness dripstoneThickness, boolean reclaimite) {
        return Blocks.POINTED_DRIPSTONE.defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)
                .setValue(PointedDripstoneBlock.THICKNESS, dripstoneThickness).setValue(BlockUtils.RECLAMITE_SHARDED, reclaimite);
    }

    public static boolean isDripstoneBase(BlockState state) {
        return state.is(Blocks.DRIPSTONE_BLOCK) || state.is(BlockTags.DRIPSTONE_REPLACEABLE);
    }

    public static boolean isEmptyOrWater(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER);
    }
}