
package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldItems;

import java.util.Collections;
import java.util.List;

public class SculkGrowthsBlock extends MultifaceBlock implements BonemealableBlock, SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    public SculkGrowthsBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.SCULK_SENSOR).strength(2.5f, 10f).lightLevel(s -> 1).requiresCorrectToolForDrops().noCollission().noOcclusion().randomTicks().hasPostProcess((bs, br, bp) -> true)
                .emissiveRendering((bs, br, bp) -> true));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        super.entityInside(blockstate, world, pos, entity);
        entity.hurt(entity.damageSources().stalagmite(), 1);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
        super.stepOn(world, pos, blockstate, entity);
        entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.STALAGMITE)), 1);
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        ItemStack itemStack = new ItemStack(VariousWorldBlocks.SCULK_GROWTHS.get());
        return Collections.singletonList(itemStack);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor accessor, BlockPos p_153306_, BlockPos p_153307_) {
        if (state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(p_153306_, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }

        return super.updateShape(state, direction, blockState, accessor, p_153306_, p_153307_);
    }

    public boolean canBeReplaced(BlockState p_153299_, BlockPlaceContext p_153300_) {
        return !p_153300_.getItemInHand().is(VariousWorldItems.SCULK_GROWTHS.get()) || super.canBeReplaced(p_153299_, p_153300_);
    }

    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state, boolean p_153292_) {
        return Direction.stream().anyMatch((p_153316_) -> this.spreader.canSpreadInAnyDirection(state, reader, pos, p_153316_.getOpposite()));
    }

    public boolean isBonemealSuccess(Level p_221264_, RandomSource p_221265_, BlockPos p_221266_, BlockState p_221267_) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState p_221262_) {
        this.spreader.spreadFromRandomFaceTowardRandomDirection(p_221262_, level, pos, random);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }
}
