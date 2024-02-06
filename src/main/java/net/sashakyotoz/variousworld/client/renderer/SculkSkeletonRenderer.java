
package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.sashakyotoz.variousworld.entity.SculkSkeletonEntity;
import net.sashakyotoz.variousworld.client.model.ModelSculk_Skeleton;

public class SculkSkeletonRenderer extends MobRenderer<SculkSkeletonEntity, ModelSculk_Skeleton<SculkSkeletonEntity>> {
	public SculkSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelSculk_Skeleton(context.bakeLayer(ModelSculk_Skeleton.LAYER_LOCATION)), 0.5f);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}
	protected void scale(SculkSkeletonEntity p_115983_, PoseStack p_115984_, float p_115985_) {
		if(!p_115983_.isBaby())
		p_115984_.scale(1,1,1);
		else{
			p_115984_.scale(0.5f,0.5f,0.5f);
		}
	}


	@Override
	public ResourceLocation getTextureLocation(SculkSkeletonEntity entity) {
		return new ResourceLocation("various_world:textures/entities/sculk_skeleton.png");
	}
}
