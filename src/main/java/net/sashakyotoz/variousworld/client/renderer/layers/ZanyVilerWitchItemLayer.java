
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
	public ZanyVilerWitchItemLayer(RenderLayerParent<T, ModelZany_Viler_Witch<T>> parent, ItemInHandRenderer item) {
		super(parent, item);
	}

	public void render(PoseStack stack, MultiBufferSource source, int p_117687_, T entity, float p_117689_, float p_117690_, float p_117691_, float p_117692_, float p_117693_, float p_117694_) {
		stack.pushPose();
		super.render(stack, source, p_117687_, entity, p_117689_, p_117690_, p_117691_, p_117692_, p_117693_, p_117694_);
		stack.popPose();
	}
}
