package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.sashakyotoz.variousworld.entity.NecromancerStaffEntity;
import net.sashakyotoz.variousworld.client.model.ModelChained_Projectile;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class NecromancerStaffRenderer extends EntityRenderer<NecromancerStaffEntity> {
	private static final ResourceLocation texture = new ResourceLocation("various_world:textures/entities/chained_projectile.png");
	private static final ResourceLocation textureMagma = new ResourceLocation("various_world:textures/entities/chained_projectile_magma.png");
	private final ModelChained_Projectile<NecromancerStaffEntity> model;

	public NecromancerStaffRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new ModelChained_Projectile(context.bakeLayer(ModelChained_Projectile.LAYER_LOCATION));
	}

	@Override
	public void render(NecromancerStaffEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(NecromancerStaffEntity entity) {
		return entity.isIsMagmaColor() ? textureMagma : texture;
	}
}
