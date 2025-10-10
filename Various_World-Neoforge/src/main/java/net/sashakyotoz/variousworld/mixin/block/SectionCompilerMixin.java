package net.sashakyotoz.variousworld.mixin.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.chunk.SectionCompiler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.AddSectionGeometryEvent;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
import org.spongepowered.asm.mixin.Mixin;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexSorting;

import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SectionBufferBuilderPack;
import net.minecraft.client.renderer.chunk.RenderSectionRegion;
import net.minecraft.core.SectionPos;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(value = {SectionCompiler.class})
public abstract class SectionCompilerMixin {
    @Shadow
    protected abstract BufferBuilder getOrBeginLayer(Map<ChunkSectionLayer, BufferBuilder> map, SectionBufferBuilderPack sectionBufferBuilderPack, ChunkSectionLayer chunkSectionLayer);

    @Shadow
    @Final
    private BlockRenderDispatcher blockRenderer;

    @Inject(
            method = "compile(Lnet/minecraft/core/SectionPos;Lnet/minecraft/client/renderer/chunk/RenderSectionRegion;Lcom/mojang/blaze3d/vertex/VertexSorting;Lnet/minecraft/client/renderer/SectionBufferBuilderPack;Ljava/util/List;)Lnet/minecraft/client/renderer/chunk/SectionCompiler$Results;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getRenderShape()Lnet/minecraft/world/level/block/RenderShape;",
                    shift = At.Shift.BEFORE
            )
    )
    public void compileWithReclaimite(
            SectionPos sectionPos, RenderSectionRegion region, VertexSorting vertexSorting, SectionBufferBuilderPack sectionBufferBuilderPack, List<AddSectionGeometryEvent.AdditionalSectionRenderer> additionalRenderers, CallbackInfoReturnable<SectionCompiler.Results> cir, @Local PoseStack poseStack, @Local Map<ChunkSectionLayer, BufferBuilder> map, @Local RandomSource randomSource, @Local(ordinal = 0, argsOnly = true) List<BlockModelPart> list, @Local(ordinal = 2) BlockPos blockPos2, @Local BlockState blockState
    ) {
        if (!BlockUtils.isReclamited(blockState)) return;
        BlockState reclamiteEquivalent = BlockUtils.getReclamiteEquivalent(blockState);
        ChunkSectionLayer chunkSectionLayer = ItemBlockRenderTypes.getChunkRenderType(reclamiteEquivalent);
        BufferBuilder bufferBuilder = this.getOrBeginLayer(map, sectionBufferBuilderPack, chunkSectionLayer);
        randomSource.setSeed(reclamiteEquivalent.getSeed(blockPos2));
        this.blockRenderer.getBlockModel(reclamiteEquivalent).collectParts(randomSource, list);
        poseStack.pushPose();
        poseStack.translate(
                (float) SectionPos.sectionRelative(blockPos2.getX()),
                (float) SectionPos.sectionRelative(blockPos2.getY()),
                (float) SectionPos.sectionRelative(blockPos2.getZ())
        );
        this.blockRenderer.renderBatched(reclamiteEquivalent, blockPos2, region, poseStack, bufferBuilder, true, list);
        poseStack.popPose();
        list.clear();
    }
}