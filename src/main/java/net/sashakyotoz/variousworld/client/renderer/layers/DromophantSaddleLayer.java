package net.sashakyotoz.variousworld.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.VariousWorldMod;
import net.sashakyotoz.variousworld.client.model.ModelDromophant;
import net.sashakyotoz.variousworld.entity.DromophantEntity;

public class DromophantSaddleLayer extends RenderLayer<DromophantEntity, ModelDromophant<DromophantEntity>> {
    private final ModelDromophant<DromophantEntity> model;
    public DromophantSaddleLayer(RenderLayerParent<DromophantEntity, ModelDromophant<DromophantEntity>> layerParent, EntityModelSet modelPart) {
        super(layerParent);
        this.model = new ModelDromophant<>(modelPart.bakeLayer(ModelDromophant.LAYER_LOCATION));
    }
    public void render(PoseStack poseStack, MultiBufferSource buffer, int p_117034_, DromophantEntity dromophant, float p_117036_, float p_117037_, float p_117038_, float p_117039_, float p_117040_, float p_117041_) {
        ResourceLocation location = new ResourceLocation(VariousWorldMod.MODID,"textures/entities/dromophant_saddled.png");
        if (dromophant.isSaddled()) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(dromophant, p_117036_, p_117037_, p_117038_);
            this.model.setupAnim(dromophant, p_117036_, p_117037_, p_117039_, p_117040_, p_117041_);
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(location));
            this.model.renderToBuffer(poseStack, vertexconsumer, p_117034_, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0F);
        }
    }
}