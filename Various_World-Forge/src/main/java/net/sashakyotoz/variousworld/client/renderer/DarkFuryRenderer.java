
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.sashakyotoz.variousworld.entity.DarkFuryEntity;
import net.sashakyotoz.variousworld.client.model.ModelDark_Fury;

public class DarkFuryRenderer extends MobRenderer<DarkFuryEntity, ModelDark_Fury<DarkFuryEntity>> {
	public DarkFuryRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelDark_Fury(context.bakeLayer(ModelDark_Fury.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(DarkFuryEntity entity) {
		return new ResourceLocation("various_world:textures/entities/dark_phantom_various.png");
	}

	@Override
	protected boolean isBodyVisible(DarkFuryEntity darkFury) {
		Level world = darkFury.level();
		double x = darkFury.getX();
		double y = darkFury.getY();
		double z = darkFury.getZ();
		return world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world.isDay();
	}
}
