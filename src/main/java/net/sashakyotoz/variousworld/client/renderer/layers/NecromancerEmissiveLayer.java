package net.sashakyotoz.variousworld.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.sashakyotoz.variousworld.client.model.ModelSculk_Necromancer_Skeleton;
import net.sashakyotoz.variousworld.entity.SculkNecromancerSkeletonEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class NecromancerEmissiveLayer<T extends SculkNecromancerSkeletonEntity, M extends ModelSculk_Necromancer_Skeleton<T>> extends RenderLayer<T, M> {
    private final ResourceLocation texture;
    private final NecromancerEmissiveLayer.AlphaFunction<T> alphaFunction;
    private final NecromancerEmissiveLayer.DrawSelector<T, M> drawSelector;

    public NecromancerEmissiveLayer(RenderLayerParent<T, M> p_234885_, ResourceLocation p_234886_, NecromancerEmissiveLayer.AlphaFunction<T> p_234887_, NecromancerEmissiveLayer.DrawSelector<T, M> p_234888_) {
        super(p_234885_);
        this.texture = p_234886_;
        this.alphaFunction = p_234887_;
        this.drawSelector = p_234888_;
    }

    public void render(PoseStack stack, MultiBufferSource source, int p_234904_, T entity, float p_234906_, float p_234907_, float p_234908_, float p_234909_, float p_234910_, float p_234911_) {
        if (!entity.isInvisible()) {
            this.onlyDrawSelectedParts();
            VertexConsumer vertexconsumer = source.getBuffer(RenderType.entityTranslucentEmissive(this.texture));
            this.getParentModel().renderToBuffer(stack, vertexconsumer, p_234904_, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, this.alphaFunction.apply(entity, p_234908_, p_234909_));
            this.resetDrawForAllParts();
        }
    }

    private void onlyDrawSelectedParts() {
        List<ModelPart> list = this.drawSelector.getPartsToDraw(this.getParentModel());
        this.getParentModel().root().getAllParts().forEach((p_234918_) -> {
            p_234918_.skipDraw = true;
        });
        list.forEach((p_234916_) -> {
            p_234916_.skipDraw = false;
        });
    }

    private void resetDrawForAllParts() {
        this.getParentModel().root().getAllParts().forEach((p_234913_) -> {
            p_234913_.skipDraw = false;
        });
    }

    @OnlyIn(Dist.CLIENT)
    public interface AlphaFunction<T extends SculkNecromancerSkeletonEntity> {
        float apply(T p_234920_, float p_234921_, float p_234922_);
    }

    @OnlyIn(Dist.CLIENT)
    public interface DrawSelector<T extends SculkNecromancerSkeletonEntity, M extends EntityModel<T>> {
        List<ModelPart> getPartsToDraw(M p_234924_);
    }
}
