
package net.sashakyotoz.variousworld.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.CrystalicSlimeModel;
import net.sashakyotoz.variousworld.client.renderers.layers.CrystalicSlimeOuterLayer;
import net.sashakyotoz.variousworld.common.entities.CrystalicSlimeEntity;

public class CrystalicSlimeRenderer extends MobRenderer<CrystalicSlimeEntity,SlimeRenderState, CrystalicSlimeModel> {
    public static final ResourceLocation SLIME_LOCATION = VariousWorld.createVWLocation("textures/entity/crystalic_slime0.png");
    private static final ResourceLocation SLIME_LOCATION1 = VariousWorld.createVWLocation("textures/entity/crystalic_slime1.png");
    private static final ResourceLocation SLIME_LOCATION2 = VariousWorld.createVWLocation("textures/entity/crystalic_slime2.png");

    public CrystalicSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new CrystalicSlimeModel(context.bakeLayer(CrystalicSlimeModel.LAYER_LOCATION)), 0.9f);
        this.addLayer(new CrystalicSlimeOuterLayer(this, context.getModelSet()));
    }

    @Override
    public SlimeRenderState createRenderState() {
        return new SlimeRenderState();
    }

    protected void scale(SlimeRenderState state, PoseStack stack) {
        stack.scale(0.999F, 0.999F, 0.999F);
        stack.translate(0.0F, 0.001F, 0.0F);
        float f1 = (float)state.size;
        float f2 = state.squish / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        stack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(SlimeRenderState slimeRenderState) {
        return SLIME_LOCATION;
    }
}