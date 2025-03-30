
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.sashakyotoz.variousworld.entity.ZombieOfStonyMagmaEntity;
import net.sashakyotoz.variousworld.client.model.ModelZombie_Stony_Magma;

public class ZombieOfStonyMagmaRenderer extends MobRenderer<ZombieOfStonyMagmaEntity, ModelZombie_Stony_Magma<ZombieOfStonyMagmaEntity>> {
	public ZombieOfStonyMagmaRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelZombie_Stony_Magma(context.bakeLayer(ModelZombie_Stony_Magma.LAYER_LOCATION)), 0.5f);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieOfStonyMagmaEntity entity) {
		return new ResourceLocation("various_world:textures/entities/zombie_stony_magma.png");
	}
}
