package net.sashakyotoz.variousworld.client.renderers.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.SquealingSpiderModel;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;

public class SquealingSpiderGlowingLayer<T extends SquealingSpiderEntity, M extends SquealingSpiderModel<T>> extends EyesLayer<T, M> {
    private static final RenderType SPIDER_EYES = RenderType.breezeEyes(VariousWorld.createVWLocation("textures/entity/squealing_spider/squealing_spider_glow_parts.png"));

    public SquealingSpiderGlowingLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity instanceof SquealingSpiderEntity entity && !entity.shoot.isStarted())
            super.render(poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }

    public RenderType renderType() {
        return SPIDER_EYES;
    }
}