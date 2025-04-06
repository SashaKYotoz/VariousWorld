package net.sashakyotoz.variousworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class SodaliteWartBlock extends FlowerBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty CLOSED = BooleanProperty.create("closed");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public SodaliteWartBlock(Holder<MobEffect> effect, float seconds, Properties properties) {
        super(effect, seconds, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(CLOSED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLOSED, WATERLOGGED);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.defaultFluidState() : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.canSeeSky(pos.above()) && (level.isRainingAt(pos.above()) || level.isNight()))
            level.setBlockAndUpdate(pos, state.setValue(CLOSED, true));
        if (!(level.isRainingAt(pos.above()) || level.isNight()))
            level.setBlockAndUpdate(pos, state.setValue(CLOSED, false));
    }
}