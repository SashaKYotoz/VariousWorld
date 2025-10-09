package net.sashakyotoz.variousworld.common.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.sashakyotoz.variousworld.init.VWBlocks;

public class BlockUtils {
    public static final BooleanProperty RECLAMITE_SHARDED = BooleanProperty.create("reclamite_sharded");

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
}