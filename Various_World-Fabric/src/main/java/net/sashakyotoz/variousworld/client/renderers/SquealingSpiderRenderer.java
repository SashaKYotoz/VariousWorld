package net.sashakyotoz.variousworld.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.SquealingSpiderModel;
import net.sashakyotoz.variousworld.client.renderers.layers.SquealingSpiderGlowingLayer;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;

public class SquealingSpiderRenderer extends MobRenderer<SquealingSpiderEntity, SquealingSpiderModel<SquealingSpiderEntity>> {
    private static final ResourceLocation SPIDER_LOCATION = VariousWorld.createVWLocation("textures/entity/squealing_spider/squealing_spider.png");

    public SquealingSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new SquealingSpiderModel<>(context.bakeLayer(SquealingSpiderModel.LAYER_LOCATION)), 0.8f);
        this.addLayer(new SquealingSpiderGlowingLayer<>(this));
    }
    @Override
    protected float getFlipDegrees(SquealingSpiderEntity livingEntity) {
        return 180.0F;
    }

    @Override
    protected void setupRotations(SquealingSpiderEntity entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);
        if (entity.canConnectToRoof()) {
            poseStack.translate(0.0F, (entity.getBbHeight() + 0.1F) / scale, 0.0F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(SquealingSpiderEntity squealingSpiderEntity) {
        return SPIDER_LOCATION;
    }
}
