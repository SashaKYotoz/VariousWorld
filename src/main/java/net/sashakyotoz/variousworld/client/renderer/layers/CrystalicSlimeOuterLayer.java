
package net.sashakyotoz.variousworld.client.renderer.layers;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sashakyotoz.variousworld.client.model.ModelCrystalic_Slime;
import net.sashakyotoz.variousworld.entity.CrystalicSlimeEntity;

@OnlyIn(Dist.CLIENT)
public class CrystalicSlimeOuterLayer<T extends CrystalicSlimeEntity> extends RenderLayer<T, ModelCrystalic_Slime<T>> {
	private final EntityModel<T> model;

	public CrystalicSlimeOuterLayer(RenderLayerParent<T, ModelCrystalic_Slime<T>> p_174536_, EntityModelSet context) {
		super(p_174536_);
		this.model = new ModelCrystalic_Slime<>(context.bakeLayer(ModelCrystalic_Slime.OUTER_LAYER_LOCATION));
	}

	public void render(PoseStack stack, MultiBufferSource source, int p_117472_, T entity, float p_117474_, float p_117475_, float p_117476_, float p_117477_, float p_117478_, float p_117479_) {
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
			this.model.prepareMobModel(entity, p_117474_, p_117475_, p_117476_);
			this.model.setupAnim(entity, p_117474_, p_117475_, p_117477_, p_117478_, p_117479_);
			this.model.renderToBuffer(stack, vertexconsumer, p_117472_, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
