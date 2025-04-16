
package net.sashakyotoz.variousworld.client.renderers.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.sashakyotoz.variousworld.client.models.CrystalicSlimeModel;
import net.sashakyotoz.variousworld.common.entities.CrystalicSlimeEntity;

public class CrystalicSlimeOuterLayer<T extends CrystalicSlimeEntity> extends RenderLayer<T, CrystalicSlimeModel<T>> {
	private final EntityModel<T> model;

	public CrystalicSlimeOuterLayer(RenderLayerParent<T, CrystalicSlimeModel<T>> layerParent, EntityModelSet context) {
		super(layerParent);
		this.model = new CrystalicSlimeModel<>(context.bakeLayer(CrystalicSlimeModel.OUTER_LAYER_LOCATION));
	}

	public void render(PoseStack stack, MultiBufferSource source, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
		Minecraft minecraft = Minecraft.getInstance();
		boolean flag = minecraft.shouldEntityAppearGlowing(entity) && entity.isInvisible();
		if (!entity.isInvisible() || flag) {
			VertexConsumer vertexconsumer;
			if (flag) {
				vertexconsumer = source.getBuffer(RenderType.outline(this.getTextureLocation(entity)));
			} else {
				vertexconsumer = source.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
			}
			this.getParentModel().copyPropertiesTo(this.model);
			this.model.prepareMobModel(entity, v, v1, v2);
			this.model.setupAnim(entity, v, v1, v3, v4, v5);
			this.model.renderToBuffer(stack, vertexconsumer, i, LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
		}
	}
}
