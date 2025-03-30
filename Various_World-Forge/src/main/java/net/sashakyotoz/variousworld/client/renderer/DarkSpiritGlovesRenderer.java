package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.sashakyotoz.variousworld.entity.technical.DarkSpiritGlovesEntity;
import net.sashakyotoz.variousworld.client.model.ModelDark_Spirit_Boom;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class DarkSpiritGlovesRenderer extends EntityRenderer<DarkSpiritGlovesEntity> {
	private static final ResourceLocation texture = new ResourceLocation("various_world:textures/entities/dark_spirit_boom.png");
	private final ModelDark_Spirit_Boom model;

	public DarkSpiritGlovesRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new ModelDark_Spirit_Boom(context.bakeLayer(ModelDark_Spirit_Boom.LAYER_LOCATION));
	}

	@Override
	public void render(DarkSpiritGlovesEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(DarkSpiritGlovesEntity entity) {
		return texture;
	}
}
