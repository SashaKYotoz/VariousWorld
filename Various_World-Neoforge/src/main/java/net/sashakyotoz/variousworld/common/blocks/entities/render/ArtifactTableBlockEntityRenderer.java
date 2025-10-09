package net.sashakyotoz.variousworld.common.blocks.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.common.blocks.entities.ArtifactTableBlockEntity;

public class ArtifactTableBlockEntityRenderer extends IBlockEntityRenderer<ArtifactTableBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ArtifactTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ArtifactTableBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStack item = entity.getItem(0);
        ItemStack potion = entity.getItem(1);
        if (entity.progress > 0) {
            poseStack.pushPose();
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.translate(1f, 1.55f, 1f);
            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            this.renderItem(this.itemRenderer, item, poseStack, buffer, entity.getLevel(), entity.getBlockPos());
            poseStack.translate(0, 0.01, 0);
            poseStack.scale(1.001f, 1.001f, 1.001f);
            this.renderItem(this.itemRenderer, potion, poseStack, buffer, entity.getLevel(), entity.getBlockPos());
            poseStack.popPose();
        }
    }
}