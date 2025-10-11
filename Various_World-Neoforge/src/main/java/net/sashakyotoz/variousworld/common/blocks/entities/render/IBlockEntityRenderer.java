package net.sashakyotoz.variousworld.common.blocks.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class IBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T, TableBlockRenderState> {
    public void renderItem(ItemStackRenderState item, PoseStack poseStack, SubmitNodeCollector nodeCollector) {
        item.submit(poseStack, nodeCollector, 12, OverlayTexture.NO_OVERLAY, 0);
    }

    @Override
    public TableBlockRenderState createRenderState() {
        return new TableBlockRenderState();
    }
}