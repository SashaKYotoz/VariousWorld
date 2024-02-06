
package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.sashakyotoz.variousworld.client.model.ModelWandering_Spirit;
import net.sashakyotoz.variousworld.entity.WanderingSpiritSummonedOfSculksEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class WanderingSpiritSummonedOfSculksRenderer extends MobRenderer<WanderingSpiritSummonedOfSculksEntity, ModelWandering_Spirit<WanderingSpiritSummonedOfSculksEntity>> {
	public WanderingSpiritSummonedOfSculksRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelWandering_Spirit(context.bakeLayer(ModelWandering_Spirit.LAYER_LOCATION)), 0.5f);
		this.addLayer(new EyesLayer<>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("various_world:textures/entities/glow_wandering_spirit.png"));
			}
		});
	}
	public void scale(PoseStack p_115984_) {
		float f = 3F;
		p_115984_.scale(f, f, f);
	}

	@Override
	public ResourceLocation getTextureLocation(WanderingSpiritSummonedOfSculksEntity entity) {
		return new ResourceLocation("various_world:textures/entities/wandering_spirit.png");
	}
}
