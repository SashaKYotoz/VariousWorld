package net.sashakyotoz.variousworld.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.WanderingZombieModel;
import net.sashakyotoz.variousworld.common.entities.WanderingZombieEntity;

public class WanderingZombieRenderer extends AbstractZombieRenderer<WanderingZombieEntity, ZombieRenderState, WanderingZombieModel<ZombieRenderState>> {
    private static final ResourceLocation ZOMBIE_LOCATION = VariousWorld.createVWLocation("textures/entity/wandering_zombie.png");

    public WanderingZombieRenderer(EntityRendererProvider.Context context) {
        this(context, WanderingZombieModel.LAYER_LOCATION, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    public WanderingZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation location, ModelLayerLocation innerArmor, ModelLayerLocation outerArmor) {
        super(context, new WanderingZombieModel(context.bakeLayer(location)), new WanderingZombieModel(context.bakeLayer(innerArmor)), new WanderingZombieModel(context.bakeLayer(outerArmor)),
                new WanderingZombieModel(context.bakeLayer(location)), new WanderingZombieModel(context.bakeLayer(innerArmor)), new WanderingZombieModel(context.bakeLayer(outerArmor)));
    }

    @Override
    protected void scale(ZombieRenderState livingEntity, PoseStack poseStack) {
        float scaleModifier = livingEntity.isBaby ? 0.5f : 1;
        poseStack.scale(scaleModifier, scaleModifier, scaleModifier);
        super.scale(livingEntity, poseStack);
    }

    @Override
    public ResourceLocation getTextureLocation(ZombieRenderState entity) {
        return ZOMBIE_LOCATION;
    }
}