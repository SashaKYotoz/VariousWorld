package net.sashakyotoz.variousworld.mixin.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Function;

@Mixin(BlockRenderDispatcher.class)
public abstract class BlockRenderDispatcherMixin {
    @Shadow
    public abstract void renderBreakingTexture(BlockState state, BlockPos pos, BlockAndTintGetter level, PoseStack poseStack, VertexConsumer consumer);

    @Shadow
    public abstract void renderBatched(BlockState state, BlockPos pos, BlockAndTintGetter getter, PoseStack poseStack, Function<ChunkSectionLayer, VertexConsumer> bufferLookup, boolean p_234361_, List<BlockModelPart> p_410643_);

    @Inject(method = "renderBreakingTexture(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/BlockAndTintGetter;Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V", at = @At("HEAD"), cancellable = true)
    public void renderReclamiteDamage(BlockState state, BlockPos pos, BlockAndTintGetter world, PoseStack stack, VertexConsumer vertexConsumer, CallbackInfo ci) {
        if (BlockUtils.isReclamited(state)) {
            this.renderBreakingTexture(BlockUtils.getReclamiteEquivalent(state), pos, world, stack, vertexConsumer);
            ci.cancel();
        }
    }

    @Inject(method = "renderBatched(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/BlockAndTintGetter;Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/util/function/Function;ZLjava/util/List;)V", at = @At("HEAD"))
    private void renderReclamite(BlockState state, BlockPos pos, BlockAndTintGetter getter, PoseStack poseStack, Function<ChunkSectionLayer, VertexConsumer> bufferLookup, boolean flag, List<BlockModelPart> list, CallbackInfo ci) {
        if (BlockUtils.isReclamited(state))
            this.renderBatched(BlockUtils.getReclamiteEquivalent(state), pos, getter, poseStack, bufferLookup, flag, list);
    }
}