
package net.sashakyotoz.variousworld.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sashakyotoz.variousworld.client.model.ModelZany_Viler_Witch;

@OnlyIn(Dist.CLIENT)
public class ZanyVilerWitchItemLayer<T extends LivingEntity> extends CrossedArmsItemLayer<T, ModelZany_Viler_Witch<T>> {
	public ZanyVilerWitchItemLayer(RenderLayerParent<T, ModelZany_Viler_Witch<T>> p_234926_, ItemInHandRenderer p_234927_) {
		super(p_234926_, p_234927_);
	}

	public void render(PoseStack p_117685_, MultiBufferSource p_117686_, int p_117687_, T p_117688_, float p_117689_, float p_117690_, float p_117691_, float p_117692_, float p_117693_, float p_117694_) {
		p_117685_.pushPose();
		super.render(p_117685_, p_117686_, p_117687_, p_117688_, p_117689_, p_117690_, p_117691_, p_117692_, p_117693_, p_117694_);
		p_117685_.popPose();
	}
}
