
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.sashakyotoz.variousworld.entity.ArmoredSkeletonEntity;
import net.sashakyotoz.variousworld.client.model.ModelVariousArmoredSkeleton;

public class ArmoredSkeletonRenderer extends MobRenderer<ArmoredSkeletonEntity, ModelVariousArmoredSkeleton<ArmoredSkeletonEntity>> {

    public ArmoredSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelVariousArmoredSkeleton<>(context.bakeLayer(ModelVariousArmoredSkeleton.LAYER_LOCATION)), 0.5f);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new HumanoidArmorLayer<>(this, new ModelVariousArmoredSkeleton<>(context.bakeLayer(ModelVariousArmoredSkeleton.LAYER_LOCATION)), new ModelVariousArmoredSkeleton<>(context.bakeLayer(ModelVariousArmoredSkeleton.OUTER_LAYER_LOCATION)), context.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(ArmoredSkeletonEntity entity) {
        return new ResourceLocation("various_world:textures/entities/various_armored_skeleton" + entity.texture + ".png");
    }
}
