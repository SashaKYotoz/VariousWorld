
package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.world.entity.Pose;
import net.sashakyotoz.variousworld.client.renderer.layers.NecromancerEmissiveLayer;
import net.sashakyotoz.variousworld.client.model.ModelSculkNecromancerSkeleton;
import net.sashakyotoz.variousworld.entity.technical.SculkNecromancerSkeletonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class SculkNecromancerSkeletonRenderer extends MobRenderer<SculkNecromancerSkeletonEntity, ModelSculkNecromancerSkeleton<SculkNecromancerSkeletonEntity>> {
	private static final ResourceLocation PULSATING_SPOTS_TEXTURE = new ResourceLocation("various_world:textures/entities/sculk_necromancer_skeleton_pulsating.png");
	public SculkNecromancerSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelSculkNecromancerSkeleton(context.bakeLayer(ModelSculkNecromancerSkeleton.LAYER_LOCATION)), 0.5f);
		this.addLayer(new NecromancerEmissiveLayer<>(this, PULSATING_SPOTS_TEXTURE, (p_234805_, p_234806_, p_234807_) -> Math.max(0.0F, Mth.cos(p_234807_ * 0.045F) * 0.25F), ModelSculkNecromancerSkeleton::getPulsatingSpotsLayerModelParts));
	}
	@Override
	protected void setupRotations(@NotNull SculkNecromancerSkeletonEntity skeletonEntity, PoseStack stack, float p_115319_, float p_115320_, float p_115321_) {
		if (this.isShaking(skeletonEntity))
			p_115320_ += (float)(Math.cos((double)skeletonEntity.tickCount * 3.25D) * Math.PI * (double)0.4F);
		if (!skeletonEntity.hasPose(Pose.SLEEPING))
			stack.mulPose(Axis.YP.rotationDegrees(180.0F - p_115320_));
		if (skeletonEntity.isAutoSpinAttack()) {
			stack.mulPose(Axis.XP.rotationDegrees(-90.0F - skeletonEntity.getXRot()));
			stack.mulPose(Axis.YP.rotationDegrees(((float)skeletonEntity.tickCount + p_115321_) * -75.0F));
		}
		else if (isEntityUpsideDown(skeletonEntity)) {
			stack.translate(0.0F, skeletonEntity.getBbHeight() + 0.1F, 0.0F);
			stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(SculkNecromancerSkeletonEntity entity) {
		return new ResourceLocation("various_world:textures/entities/sculk_necromancer_skeleton.png");
	}
}
