package net.sashakyotoz.variousworld.common.blocks.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.common.blocks.entities.DisassemblyTableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class DisassemblyTableBlockEntityRenderer extends IBlockEntityRenderer<DisassemblyTableBlockEntity> {
    private final ItemModelResolver itemModelResolver;

    public DisassemblyTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public void extractRenderState(DisassemblyTableBlockEntity blockEntity, TableBlockRenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        renderState.input = blockEntity.getItem(0);
        renderState.input1 = blockEntity.getItem(1);
        renderState.input2 = blockEntity.getItem(2);
        renderState.output = blockEntity.getItem(3);
        renderState.progress = blockEntity.progress;
        renderState.hasRecipe = blockEntity.hasRecipe(blockEntity.getLevel(), blockEntity);
        this.itemModelResolver.updateForTopItem(renderState.item, renderState.input, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 1);
        this.itemModelResolver.updateForTopItem(renderState.item1, renderState.input1, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 1);
        this.itemModelResolver.updateForTopItem(renderState.item2, renderState.input2, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 1);
        this.itemModelResolver.updateForTopItem(renderState.item3, renderState.output, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 1);
    }

    @Override
    public void submit(TableBlockRenderState state, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.translate(1f, 1.55f, 1f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.translate(0, 0.01, 0);
        poseStack.scale(1.001f, 1.001f, 1.001f);
        if (!state.output.isEmpty())
            this.renderItem(state.item3, poseStack, nodeCollector);
        else {
            if (!state.input1.isEmpty()) {
                poseStack.translate(0, 0, -0.025);
                this.renderItem(state.item1, poseStack, nodeCollector);
            }
            if (!state.input2.isEmpty()) {
                poseStack.translate(0, 0, -0.0255);
                this.renderItem(state.item2, poseStack, nodeCollector);
            }
        }
        poseStack.popPose();
    }
}
