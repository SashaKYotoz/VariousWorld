
package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.sashakyotoz.variousworld.client.model.ModelWanderingSpirit;
import net.sashakyotoz.variousworld.entity.WanderingSpiritOfSculksEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WanderingSpiritSummonedOfSculksRenderer extends MobRenderer<WanderingSpiritOfSculksEntity, ModelWanderingSpirit<WanderingSpiritOfSculksEntity>> {
	public WanderingSpiritSummonedOfSculksRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelWanderingSpirit<>(context.bakeLayer(ModelWanderingSpirit.LAYER_LOCATION)), 0.5f);
		this.addLayer(new EyesLayer<>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("various_world:textures/entities/glow_wandering_spirit.png"));
			}
		});
	}

	@Override
	protected void scale(@NotNull WanderingSpiritOfSculksEntity entity, PoseStack stack, float modifier) {
		float f = 1.35f - (entity.getMaxHealth() - entity.getHealth())/200f;
		stack.scale(f,f,f);
	}

	@Override
	public ResourceLocation getTextureLocation(WanderingSpiritOfSculksEntity entity) {
		return new ResourceLocation("various_world:textures/entities/wandering_spirit.png");
	}
}
