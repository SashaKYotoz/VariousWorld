package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.client.model.ModelDromophant;
import net.sashakyotoz.variousworld.client.renderer.layers.DromophantSaddleLayer;
import net.sashakyotoz.variousworld.entity.DromophantEntity;

public class DromophantRenderer extends MobRenderer<DromophantEntity, ModelDromophant<DromophantEntity>> {
    public DromophantRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelDromophant<>(context.bakeLayer(ModelDromophant.LAYER_LOCATION)), 0.5f);
        this.addLayer(new DromophantSaddleLayer(this, context.getModelSet()));
    }

    @Override
    protected void scale(DromophantEntity entity, PoseStack stack, float p_115316_) {
        super.scale(entity, stack, p_115316_);
        stack.scale(entity.isBaby() ? 0.5f : 1.1f,entity.isBaby() ? 0.5f : 1.1f,entity.isBaby() ? 0.5f : 1.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(DromophantEntity entity) {
        return new ResourceLocation("various_world:textures/entities/dromophant.png");
    }
}