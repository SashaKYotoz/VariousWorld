package net.sashakyotoz.variousworld.mixin.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
import net.sashakyotoz.variousworld.init.VWBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {

    @Inject(method = "createBlockStateDefinition", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/StateDefinition$Builder;add([Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/world/level/block/state/StateDefinition$Builder;"))
    private void createBlockState(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        if (!((StateDefinitionAccess) builder).getProperties().containsKey(BlockUtils.RECLAMITE_SHARDED.getName()))
            builder.add(BlockUtils.RECLAMITE_SHARDED);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/PointedDripstoneBlock;registerDefaultState(Lnet/minecraft/world/level/block/state/BlockState;)V"))
    private void registerDefaultState(PointedDripstoneBlock instance, BlockState blockState, Operation<Void> original) {
        original.call(instance, blockState.setValue(BlockUtils.RECLAMITE_SHARDED, false));
    }

    @Inject(method = "growStalactiteOrStalagmiteIfPossible", at = @At("HEAD"), cancellable = true)
    private static void grow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (BlockUtils.isReclamited(state))
            ci.cancel();
    }

    @Inject(method = "spawnDripParticle(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", at = @At("HEAD"), cancellable = true)
    private static void cancelLiquidDrop(Level level, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (BlockUtils.isReclamited(state))
            ci.cancel();
    }

    @Inject(method = "getStateForPlacement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/PointedDripstoneBlock;calculateDripstoneThickness(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Lnet/minecraft/world/level/block/state/properties/DripstoneThickness;"))
    private void generateDecorations(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getNearestLookingVerticalDirection().getOpposite();
        if (BlockUtils.isReclamited(context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()))) && context.getLevel().isClientSide()) {
            level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, VWBlocks.RECLAIMITE_CRYSTAL.get().defaultBlockState()), pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
            level.playLocalSound(pos, SoundEvents.MEDIUM_AMETHYST_BUD_BREAK, SoundSource.BLOCKS, 1, 1, true);
        }
    }
}