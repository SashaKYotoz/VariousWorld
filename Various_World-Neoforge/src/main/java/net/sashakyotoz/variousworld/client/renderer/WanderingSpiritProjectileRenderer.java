package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.sashakyotoz.variousworld.entity.technical.WanderingSpiritProjectileEntity;
import net.sashakyotoz.variousworld.client.model.ModelProjectileCycle;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class WanderingSpiritProjectileRenderer extends EntityRenderer<WanderingSpiritProjectileEntity> {
	private static final ResourceLocation texture = new ResourceLocation("various_world:textures/entities/sculk_ball.png");
	private final ModelProjectileCycle<WanderingSpiritProjectileEntity> model;

	public WanderingSpiritProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new ModelProjectileCycle<>(context.bakeLayer(ModelProjectileCycle.LAYER_LOCATION));
	}

	@Override
	public void render(WanderingSpiritProjectileEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entity)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.XP.rotation(entity.tickCount % 360*0.25f));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.725f);
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(WanderingSpiritProjectileEntity entity) {
		return texture;
	}
}
