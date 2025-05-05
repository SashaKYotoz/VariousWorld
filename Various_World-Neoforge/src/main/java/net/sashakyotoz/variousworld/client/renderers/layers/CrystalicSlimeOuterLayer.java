
package net.sashakyotoz.variousworld.client.renderers.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.sashakyotoz.variousworld.client.models.CrystalicSlimeModel;
import net.sashakyotoz.variousworld.client.renderers.CrystalicSlimeRenderer;

public class CrystalicSlimeOuterLayer extends RenderLayer<SlimeRenderState, CrystalicSlimeModel> {
    private final CrystalicSlimeModel model;

    public CrystalicSlimeOuterLayer(RenderLayerParent<SlimeRenderState, CrystalicSlimeModel> layerParent, EntityModelSet context) {
        super(layerParent);
        this.model = new CrystalicSlimeModel(context.bakeLayer(CrystalicSlimeModel.OUTER_LAYER_LOCATION));
    }

    public void render(PoseStack stack, MultiBufferSource source, int i, SlimeRenderState entity, float v, float v1) {
        boolean flag = entity.appearsGlowing && entity.isInvisible;
        if (!entity.isInvisible || flag) {
            VertexConsumer vertexconsumer = flag ? source.getBuffer(RenderType.outline(CrystalicSlimeRenderer.SLIME_LOCATION))
                    : source.getBuffer(RenderType.entityTranslucent(CrystalicSlimeRenderer.SLIME_LOCATION));
            this.model.setupAnim(entity);
            this.model.renderToBuffer(stack, vertexconsumer, i, LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
        }
    }
}
