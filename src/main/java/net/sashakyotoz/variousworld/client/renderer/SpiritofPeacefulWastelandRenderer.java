
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;

import net.sashakyotoz.variousworld.entity.SpiritofPeacefulWastelandEntity;
import net.sashakyotoz.variousworld.client.model.ModelSpirit_of_Peaceful_Wasteland;

public class SpiritofPeacefulWastelandRenderer extends MobRenderer<SpiritofPeacefulWastelandEntity, ModelSpirit_of_Peaceful_Wasteland<SpiritofPeacefulWastelandEntity>> {
	public SpiritofPeacefulWastelandRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelSpirit_of_Peaceful_Wasteland(context.bakeLayer(ModelSpirit_of_Peaceful_Wasteland.LAYER_LOCATION)), 0.3f);
		this.addLayer(new EyesLayer<>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("various_world:textures/entities/glow_spirit_texture.png"));
			}
		});
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(SpiritofPeacefulWastelandEntity entity) {
		return new ResourceLocation("various_world:textures/entities/spirit_texture.png");
	}
}
