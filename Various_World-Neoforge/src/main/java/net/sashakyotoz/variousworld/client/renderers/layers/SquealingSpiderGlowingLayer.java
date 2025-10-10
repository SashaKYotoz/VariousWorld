package net.sashakyotoz.variousworld.client.renderers.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.SquealingSpiderModel;
import net.sashakyotoz.variousworld.client.models.states.SquealingSpiderRenderState;

public class SquealingSpiderGlowingLayer extends RenderLayer<SquealingSpiderRenderState, SquealingSpiderModel> {
    private static final RenderType SPIDER_EYES = RenderType.breezeEyes(VariousWorld.createVWLocation("textures/entity/squealing_spider/squealing_spider_glow_parts.png"));

    public SquealingSpiderGlowingLayer(RenderLayerParent<SquealingSpiderRenderState, SquealingSpiderModel> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource source, int p_116985_, SquealingSpiderRenderState state, float p_116987_, float p_116988_) {
        VertexConsumer vertexconsumer = source.getBuffer(this.renderType());
        if (!state.shoot.isStarted())
            this.getParentModel().renderToBuffer(stack, vertexconsumer, p_116985_, OverlayTexture.NO_OVERLAY);
    }

    public RenderType renderType() {
        return SPIDER_EYES;
    }
}