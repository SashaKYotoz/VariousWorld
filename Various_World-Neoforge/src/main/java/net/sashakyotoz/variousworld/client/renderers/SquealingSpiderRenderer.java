package net.sashakyotoz.variousworld.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.SquealingSpiderModel;
import net.sashakyotoz.variousworld.client.models.states.SquealingSpiderRenderState;
import net.sashakyotoz.variousworld.client.renderers.layers.SquealingSpiderGlowingLayer;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;

public class SquealingSpiderRenderer extends MobRenderer<SquealingSpiderEntity, SquealingSpiderRenderState, SquealingSpiderModel> {
    private static final ResourceLocation SPIDER_LOCATION = VariousWorld.createVWLocation("textures/entity/squealing_spider/squealing_spider.png");

    public SquealingSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new SquealingSpiderModel(context.bakeLayer(SquealingSpiderModel.LAYER_LOCATION)), 0.8f);
        this.addLayer(new SquealingSpiderGlowingLayer(this));
    }

    @Override
    public SquealingSpiderRenderState createRenderState() {
        return new SquealingSpiderRenderState();
    }

    @Override
    protected float getFlipDegrees() {
        return 180.0F;
    }

    @Override
    public void extractRenderState(SquealingSpiderEntity entity, SquealingSpiderRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.canConnectToRoof = entity.canConnectToRoof();
        reusedState.inhale = entity.inhale;
        reusedState.longJump = entity.longJump;
        reusedState.slide = entity.slide;
        reusedState.slideBack = entity.slideBack;
        reusedState.shoot = entity.shoot;
    }

    @Override
    protected void setupRotations(SquealingSpiderRenderState renderState, PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(renderState, poseStack, bodyRot, scale);
        if (renderState.canConnectToRoof) {
            poseStack.translate(0.0F, (renderState.boundingBoxHeight + 0.1F) / scale, 0.0F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(SquealingSpiderRenderState squealingSpiderEntity) {
        return SPIDER_LOCATION;
    }
}
