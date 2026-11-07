package net.sashakyotoz.variousworld.common.blocks.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.common.blocks.entities.DisassemblyTableBlockEntity;

public class DisassemblyTableBlockEntityRenderer extends IBlockEntityRenderer<DisassemblyTableBlockEntity> {
    private final ItemRenderer itemRenderer;

    public DisassemblyTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(DisassemblyTableBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStack input = entity.getItem(3);
        poseStack.pushPose();
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.translate(1f, 1.55f, 1f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.translate(0, 0.01, 0);
        poseStack.scale(1.001f, 1.001f, 1.001f);
        if (!input.isEmpty())
            this.renderItem(this.itemRenderer, input, poseStack, buffer, entity.getLevel(), entity.getBlockPos());
        else {
            ItemStack outPut = entity.getItem(1);
            ItemStack outPut1 = entity.getItem(2);
            if (!outPut.isEmpty()) {
                poseStack.translate(0, 0, -0.025);
                this.renderItem(this.itemRenderer, outPut, poseStack, buffer, entity.getLevel(), entity.getBlockPos());
            }
            if (!outPut1.isEmpty()) {
                poseStack.translate(0, 0, -0.0255);
                this.renderItem(this.itemRenderer, outPut1, poseStack, buffer, entity.getLevel(), entity.getBlockPos());
            }
        }
        poseStack.popPose();
    }
}
