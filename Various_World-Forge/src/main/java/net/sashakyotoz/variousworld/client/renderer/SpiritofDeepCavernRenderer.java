
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;

import net.sashakyotoz.variousworld.entity.SpiritOfDeepCavernEntity;
import net.sashakyotoz.variousworld.client.model.ModelSpirit_of_Deep_Cavern;

public class SpiritofDeepCavernRenderer extends MobRenderer<SpiritOfDeepCavernEntity, ModelSpirit_of_Deep_Cavern<SpiritOfDeepCavernEntity>> {
	public SpiritofDeepCavernRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelSpirit_of_Deep_Cavern(context.bakeLayer(ModelSpirit_of_Deep_Cavern.LAYER_LOCATION)), 0.5f);
		this.addLayer(new EyesLayer<>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("various_world:textures/entities/glow_spirit_of_deep_cavern.png"));
			}
		});
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(SpiritOfDeepCavernEntity entity) {
		return new ResourceLocation("various_world:textures/entities/spirit_of_deep_cavern.png");
	}

	@Override
	protected boolean isShaking(SpiritOfDeepCavernEntity cavernEntity) {
		Level world = cavernEntity.level();
		double x = cavernEntity.getX();
		double y = cavernEntity.getY();
		double z = cavernEntity.getZ();
		return world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world.isDay();
	}
}
