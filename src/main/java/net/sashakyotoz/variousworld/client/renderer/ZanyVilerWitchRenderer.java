
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.sashakyotoz.variousworld.entity.ZanyVilerWitchEntity;
import net.sashakyotoz.variousworld.client.model.ModelZany_Viler_Witch;
import net.sashakyotoz.variousworld.client.renderer.layers.ZanyVilerWitchItemLayer;

@OnlyIn(Dist.CLIENT)
public class ZanyVilerWitchRenderer extends MobRenderer<ZanyVilerWitchEntity, ModelZany_Viler_Witch<ZanyVilerWitchEntity>> {
	public ZanyVilerWitchRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelZany_Viler_Witch(context.bakeLayer(ModelZany_Viler_Witch.LAYER_LOCATION)), 0.5f);
		this.addLayer(new ZanyVilerWitchItemLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(ZanyVilerWitchEntity entity) {
		return new ResourceLocation("various_world:textures/entities/zany_viler_witch" + entity.texture + ".png");
	}
}
