
package net.sashakyotoz.variousworld.client.renderers.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.sashakyotoz.variousworld.client.models.CrystalicSlimeModel;
import net.sashakyotoz.variousworld.client.models.states.CrystalicSlimeRenderState;
import net.sashakyotoz.variousworld.client.renderers.CrystalicSlimeRenderer;

public class CrystalicSlimeOuterLayer extends RenderLayer<CrystalicSlimeRenderState, CrystalicSlimeModel> {
    private final CrystalicSlimeModel model;

    public CrystalicSlimeOuterLayer(RenderLayerParent<CrystalicSlimeRenderState, CrystalicSlimeModel> layerParent, EntityModelSet context) {
        super(layerParent);
        this.model = new CrystalicSlimeModel(context.bakeLayer(CrystalicSlimeModel.OUTER_LAYER_LOCATION));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, CrystalicSlimeRenderState renderState, float v, float v1) {
        boolean flag = renderState.appearsGlowing() && renderState.isInvisible;
        if (!renderState.isInvisible || flag) {
            int i = LivingEntityRenderer.getOverlayCoords(renderState, 0.0F);
            nodeCollector.order(1).submitModel(this.model, renderState, poseStack, flag ? RenderType.outline(CrystalicSlimeRenderer.SLIME_LOCATION) : RenderType.entityTranslucent(CrystalicSlimeRenderer.SLIME_LOCATION), packedLight, i, -1, null, renderState.outlineColor, null);
        }
    }
}