package net.sashakyotoz.variousworld.common.blocks.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.common.blocks.entities.ArtifactTableBlockEntity;

public class ArtifactTableBlockEntityRenderer implements BlockEntityRenderer<ArtifactTableBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ArtifactTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ArtifactTableBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, Vec3 vec3) {
        ItemStack item = entity.itemHandler.getStackInSlot(0);
        ItemStack potion = entity.itemHandler.getStackInSlot(1);
        if (entity.progress > 0) {
            poseStack.pushPose();
            poseStack.scale(0.5f,0.5f,0.5f);
            poseStack.translate(1f, 1.55f, 1f);
            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            this.itemRenderer.renderStatic(item, ItemDisplayContext.GUI, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.getLevel(), packedOverlay);
            poseStack.translate(0, 0.01, 0);
            poseStack.scale(1.001f, 1.001f, 1.001f);
            this.itemRenderer.renderStatic(potion, ItemDisplayContext.GUI, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.getLevel(), packedOverlay);
            poseStack.popPose();
        }
    }
}