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
import net.sashakyotoz.variousworld.client.model.ModelAngel_Armor_Wings;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class AngelStarWingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation WINGS_LOCATION = new ResourceLocation("various_world:textures/entities/angel_armor_top.png");
    private final ModelAngel_Armor_Wings<T> armorModel;

    public AngelStarWingsLayer(RenderLayerParent<T, M> parent, EntityModelSet modelSet) {
        super(parent);
        this.armorModel = new ModelAngel_Armor_Wings<>(modelSet.bakeLayer(ModelAngel_Armor_Wings.LAYER_LOCATION));
    }

    public void render(PoseStack stack, MultiBufferSource source, int p_116953_, T entity, float p_116955_, float p_116956_, float p_116957_, float p_116958_, float p_116959_, float p_116960_) {
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (shouldRender(itemstack)) {
            ResourceLocation resourcelocation;
            resourcelocation = getWingsLocation(itemstack, entity);
            stack.pushPose();
            stack.translate(0.0F, 0.0F, 0.125F);
            this.getParentModel().copyPropertiesTo(this.armorModel);
            this.armorModel.setupAnim(entity, p_116955_, p_116956_, p_116958_, p_116959_, p_116960_);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(source, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil());
            this.armorModel.renderToBuffer(stack, vertexconsumer, p_116953_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.popPose();
        }
    }

    public boolean shouldRender(ItemStack stack) {
        return stack.getItem() == VariousWorldModItems.ANGEL_CHESTPLATE.get();
    }

    public ResourceLocation getWingsLocation(ItemStack stack, T entity) {
        return WINGS_LOCATION;
    }
}
