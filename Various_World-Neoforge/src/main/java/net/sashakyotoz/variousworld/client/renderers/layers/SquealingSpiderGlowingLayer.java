package net.sashakyotoz.variousworld.client.renderers.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.SquealingSpiderModel;
import net.sashakyotoz.variousworld.client.models.states.SquealingSpiderRenderState;

public class SquealingSpiderGlowingLayer extends RenderLayer<SquealingSpiderRenderState, SquealingSpiderModel> {
    private static final RenderType SPIDER_EYES = RenderTypes.breezeEyes(VariousWorld.createVWLocation("textures/entity/squealing_spider/squealing_spider_glow_parts.png"));

    public SquealingSpiderGlowingLayer(RenderLayerParent<SquealingSpiderRenderState, SquealingSpiderModel> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, SquealingSpiderRenderState renderState, float v, float v1) {
        nodeCollector.order(1).submitModel(this.getParentModel(), renderState, poseStack, SPIDER_EYES, packedLight, OverlayTexture.NO_OVERLAY, -1, null, renderState.outlineColor, null);
    }
}