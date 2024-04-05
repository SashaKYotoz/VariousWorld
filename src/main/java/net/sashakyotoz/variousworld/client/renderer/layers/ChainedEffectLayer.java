package net.sashakyotoz.variousworld.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.client.model.ModelChained;
import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;

public class ChainedEffectLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation CHAIN_LOCATION = new ResourceLocation("various_world:textures/entities/chained.png");
    private final ModelChained<T> armorModel;

    public ChainedEffectLayer(RenderLayerParent<T, M> layerParent, EntityModelSet set) {
        super(layerParent);
        this.armorModel = new ModelChained<>(set.bakeLayer(ModelChained.LAYER_LOCATION));
    }

    public void render(PoseStack stack, MultiBufferSource bufferSource, int p_116953_, T location, float p_116955_, float p_116956_, float p_116957_, float p_116958_, float p_116959_, float p_116960_) {
        ItemStack itemstack = location.getItemBySlot(EquipmentSlot.CHEST);
        if (shouldRender(location)) {
            ResourceLocation resourcelocation;
            resourcelocation = getRodsLocation(itemstack, location);
            stack.pushPose();
            stack.translate(0.0F, 0.0F, 0.125F);
            this.getParentModel().copyPropertiesTo(this.armorModel);
            this.armorModel.setupAnim(location, p_116955_, p_116956_, p_116958_, p_116959_, p_116960_);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferSource, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil());
            this.armorModel.renderToBuffer(stack, vertexconsumer, p_116953_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.popPose();
        }
    }

    public boolean shouldRender(T entity) {
        return entity.hasEffect(VariousWorldModMobEffects.CHAINED_OF_CHAIN.get());
    }

    public ResourceLocation getRodsLocation(ItemStack stack, T entity) {
        return CHAIN_LOCATION;
    }
}
