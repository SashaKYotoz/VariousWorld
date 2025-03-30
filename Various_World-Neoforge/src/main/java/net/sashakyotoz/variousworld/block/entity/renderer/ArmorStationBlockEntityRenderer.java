package net.sashakyotoz.variousworld.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.sashakyotoz.variousworld.block.entity.ArmorStationBlockEntity;

public class ArmorStationBlockEntityRenderer implements BlockEntityRenderer<ArmorStationBlockEntity> {
    public ArmorStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(ArmorStationBlockEntity entity, float pPartialTick, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = entity.getItemStackToShow();
        poseStack.pushPose();
        if (entity.getLevel() != null && !itemStack.isEmpty()){
            long gameTime = entity.getLevel().getDayTime();
            poseStack.translate(0.5f, 1f, 0.5f);
            poseStack.scale(0.4f, 0.4f, 0.4f);
            poseStack.mulPose(Axis.YP.rotationDegrees((gameTime % 360f)*0.75f));
            itemRenderer.renderStatic(itemStack, ItemDisplayContext.GROUND, getLightLevel(entity.getLevel(), entity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, poseStack, pBuffer, entity.getLevel(), 1);
        }
        poseStack.popPose();
    }
    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
