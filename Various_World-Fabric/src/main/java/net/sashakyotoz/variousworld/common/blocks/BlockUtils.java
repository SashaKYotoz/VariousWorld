package net.sashakyotoz.variousworld.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.material.FluidState;
import net.sashakyotoz.variousworld.init.VWBiomes;
import net.sashakyotoz.variousworld.init.VWBlocks;

public class BlockUtils {
    public static final BooleanProperty RECLAMITE_SHARDED = BooleanProperty.create("reclamite_sharded");
    public static final float RECLAIMITE_REGROW_CHANCE = 0.06F;
    private static final int MAX_STALACTITE_LENGTH = 11;

    public static boolean isReclamited(BlockState state) {
        return canBeReclamited(state) && state.getValue(RECLAMITE_SHARDED);
    }

    public static boolean canBeReclamited(BlockState state) {
        return state != null
                && state.hasProperty(RECLAMITE_SHARDED)
                && state.hasProperty(BlockStateProperties.DRIPSTONE_THICKNESS)
                && state.getValue(BlockStateProperties.DRIPSTONE_THICKNESS) == DripstoneThickness.TIP;
    }

    public static BlockState getReclamiteEquivalent(BlockState state) {
        if (canBeReclamited(state)) {
            return state.getValue(BlockStateProperties.VERTICAL_DIRECTION) == Direction.DOWN ?
                    VWBlocks.RECLAIMITE_CRYSTAL.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.DOWN) :
                    VWBlocks.RECLAIMITE_CRYSTAL.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.UP);
        }
        return state;
    }

    public static void tryRegrowReclaimite(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeReclamited(state) || state.getValue(RECLAMITE_SHARDED)
                || state.getValue(BlockStateProperties.VERTICAL_DIRECTION) != Direction.DOWN)
            return;
        if (random.nextFloat() >= RECLAIMITE_REGROW_CHANCE)
            return;
        if (!level.getBiome(pos).is(VWBiomes.RECLAIMITE_CAVES) || !hasWaterSourceAbove(level, pos))
            return;
        level.setBlockAndUpdate(pos, state.setValue(RECLAMITE_SHARDED, true));
        level.playSound(null, pos, SoundEvents.SMALL_AMETHYST_BUD_PLACE, SoundSource.BLOCKS, 0.6F, 1.2F);
    }

    private static boolean hasWaterSourceAbove(LevelReader level, BlockPos tip) {
        BlockPos.MutableBlockPos cursor = tip.mutable();
        for (int i = 0; i < MAX_STALACTITE_LENGTH; i++) {
            cursor.move(Direction.UP);
            if (level.getBlockState(cursor).is(Blocks.POINTED_DRIPSTONE))
                continue;
            if (level.getBlockState(cursor).isAir())
                return false;
            FluidState fluid = level.getFluidState(cursor.move(Direction.UP));
            return fluid.is(FluidTags.WATER) && fluid.isSource();
        }
        return false;
    }
}