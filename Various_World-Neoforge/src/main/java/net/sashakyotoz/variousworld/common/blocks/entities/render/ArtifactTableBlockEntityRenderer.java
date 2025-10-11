package net.sashakyotoz.variousworld.common.blocks.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.common.blocks.entities.ArtifactTableBlockEntity;
import net.sashakyotoz.variousworld.common.blocks.entities.DisassemblyTableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ArtifactTableBlockEntityRenderer extends IBlockEntityRenderer<ArtifactTableBlockEntity> {
    private final ItemModelResolver itemModelResolver;

    public ArtifactTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public void extractRenderState(ArtifactTableBlockEntity blockEntity, TableBlockRenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        renderState.input = blockEntity.getItem(0);
        renderState.input1 = blockEntity.getItem(1);
        renderState.input2 = blockEntity.getItem(2);
        renderState.progress = blockEntity.progress;
        renderState.hasRecipe = blockEntity.progress > 0;
        this.itemModelResolver.updateForTopItem(renderState.item, renderState.input, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 1);
        this.itemModelResolver.updateForTopItem(renderState.item1, renderState.input1, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 1);
        this.itemModelResolver.updateForTopItem(renderState.item2, renderState.input2, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 1);
    }

    @Override
    public void submit(TableBlockRenderState state, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        if (state.hasRecipe) {
            poseStack.pushPose();
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.translate(1f, 1.55f, 1f);
            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            this.renderItem(state.item, poseStack, nodeCollector);
            poseStack.translate(0, 0.01, 0);
            poseStack.scale(1.001f, 1.001f, 1.001f);
            this.renderItem(state.item1, poseStack, nodeCollector);
            poseStack.popPose();
        }
    }
}