package net.sashakyotoz.variousworld.common.blocks.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class IBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    public void renderItem(ItemRenderer renderer, ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, Level level, BlockPos pos) {
        renderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(level, pos), OverlayTexture.NO_OVERLAY, poseStack, buffer, level, 1);
    }

    public int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}