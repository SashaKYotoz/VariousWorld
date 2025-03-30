
package net.sashakyotoz.variousworld.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;

import net.sashakyotoz.variousworld.entity.CrystalicSlimeEntity;
import net.sashakyotoz.variousworld.client.model.ModelCrystalic_Slime;
import net.sashakyotoz.variousworld.client.renderer.layers.CrystalicSlimeOuterLayer;

import com.mojang.blaze3d.vertex.PoseStack;

public class CrystalicSlimeRenderer extends MobRenderer<CrystalicSlimeEntity, ModelCrystalic_Slime<CrystalicSlimeEntity>> {
	private static final ResourceLocation SLIME_LOCATION = new ResourceLocation("various_world:textures/entities/crystalic_slime0.png");
	private static final ResourceLocation SLIME_LOCATION1 = new ResourceLocation("various_world:textures/entities/crystalic_slime1.png");
	private static final ResourceLocation SLIME_LOCATION2 = new ResourceLocation("various_world:textures/entities/crystalic_slime2.png");

	public CrystalicSlimeRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelCrystalic_Slime(context.bakeLayer(ModelCrystalic_Slime.LAYER_LOCATION)), 0.9f);
		this.addLayer(new CrystalicSlimeOuterLayer<>(this, context.getModelSet()));
	}

	public void render(CrystalicSlimeEntity entity, float p_115977_, float p_115978_, PoseStack stack, MultiBufferSource p_115980_, int p_115981_) {
		this.shadowRadius = 0.25F * (float) entity.getSize();
		super.render(entity, p_115977_, p_115978_, stack, p_115980_, p_115981_);
	}

	protected void scale(CrystalicSlimeEntity entity, PoseStack stack, float p_115985_) {
		stack.scale(0.999F, 0.999F, 0.999F);
		stack.translate(0.0D, 0.001F, 0.0D);
		float f1 = (float) entity.getSize();
		float f2 = Mth.lerp(p_115985_, entity.oSquish, entity.squish) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		stack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	public ResourceLocation getTextureLocation(CrystalicSlimeEntity entity) {
		return switch (entity.texture) {
			case 1 -> SLIME_LOCATION1;
			case 2 -> SLIME_LOCATION2;
			default -> SLIME_LOCATION;
		};
	}
}
