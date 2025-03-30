
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.sashakyotoz.variousworld.entity.ZombieOfVariousBiomesEntity;
import net.sashakyotoz.variousworld.client.model.ModelZombie_of_Various_Biomes;

public class ZombieOfVariousBiomesRenderer extends MobRenderer<ZombieOfVariousBiomesEntity, ModelZombie_of_Various_Biomes<ZombieOfVariousBiomesEntity>> {
	public ZombieOfVariousBiomesRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelZombie_of_Various_Biomes(context.bakeLayer(ModelZombie_of_Various_Biomes.LAYER_LOCATION)), 0.5f);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieOfVariousBiomesEntity entity) {
		return new ResourceLocation("various_world:textures/entities/wandering_zombie.png");
	}
}
