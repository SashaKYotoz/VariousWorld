
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.sashakyotoz.variousworld.entity.ArmoredSkeletonEntity;
import net.sashakyotoz.variousworld.client.model.ModelVarious_Armored_Skeleton;

public class ArmoredSkeletonRenderer extends MobRenderer<ArmoredSkeletonEntity, ModelVarious_Armored_Skeleton<ArmoredSkeletonEntity>> {
    public ArmoredSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelVarious_Armored_Skeleton(context.bakeLayer(ModelVarious_Armored_Skeleton.LAYER_LOCATION)), 0.5f);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(ArmoredSkeletonEntity entity) {
        return new ResourceLocation("various_world:textures/entities/various_armored_skeleton" + entity.texture + ".png");
    }
}
