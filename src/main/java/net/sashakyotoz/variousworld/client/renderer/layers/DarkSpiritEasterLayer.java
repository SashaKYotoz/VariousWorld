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
import net.sashakyotoz.variousworld.client.model.ModelSpirit_of_the_Dark;
import net.sashakyotoz.variousworld.client.model.Modelcrystalic_warrior;
import net.sashakyotoz.variousworld.client.renderer.DarkSpiritRenderer;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;
import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;

public class DarkSpiritEasterLayer extends RenderLayer<DarkSpiritEntity, ModelSpirit_of_the_Dark<DarkSpiritEntity>> {

    private final ModelSpirit_of_the_Dark<DarkSpiritEntity> model;

    public DarkSpiritEasterLayer(RenderLayerParent<DarkSpiritEntity, ModelSpirit_of_the_Dark<DarkSpiritEntity>> layerParent, EntityModelSet modelPart) {
        super(layerParent);
        this.model = new ModelSpirit_of_the_Dark<>(modelPart.bakeLayer(ModelSpirit_of_the_Dark.LAYER_LOCATION));
    }
    public void render(PoseStack poseStack, MultiBufferSource buffer, int p_117034_, DarkSpiritEntity spiritEntity, float p_117036_, float p_117037_, float p_117038_, float p_117039_, float p_117040_, float p_117041_) {
        ResourceLocation location = new ResourceLocation(VariousWorldMod.MODID,"textures/entities/spirit_of_the_dark_easter.png");
        if (spiritEntity.getDisplayName().getString().equals("Basics")) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(spiritEntity, p_117036_, p_117037_, p_117038_);
            this.model.setupAnim(spiritEntity, p_117036_, p_117037_, p_117039_, p_117040_, p_117041_);
            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(location));
            this.model.renderToBuffer(poseStack, vertexconsumer, p_117034_, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0F);
        }
    }
}
