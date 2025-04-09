package net.sashakyotoz.variousworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.sashakyotoz.variousworld.init.VWBlocks;
import org.jetbrains.annotations.Nullable;

public class ModRotatedPillarBlock extends RotatedPillarBlock {
    public ModRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            return switch (state.getBlock()) {
                case ModRotatedPillarBlock block when block == VWBlocks.CRYSTALIC_OAK_LOG.get() ->
                        VWBlocks.STRIPPED_CRYSTALIC_OAK_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                case ModRotatedPillarBlock block when block == VWBlocks.CRYSTALIC_OAK_WOOD.get() ->
                        VWBlocks.STRIPPED_CRYSTALIC_OAK_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                default -> super.getToolModifiedState(state, context, itemAbility, simulate);
            };
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}