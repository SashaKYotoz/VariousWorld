
package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.entity.Pose;
import net.sashakyotoz.variousworld.client.renderer.layers.DarkSpiritEasterLayer;
import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;
import net.sashakyotoz.variousworld.client.model.ModelSpiritOfTheDark;
import org.jetbrains.annotations.NotNull;

public class DarkSpiritRenderer extends MobRenderer<DarkSpiritEntity, ModelSpiritOfTheDark<DarkSpiritEntity>> {
    public DarkSpiritRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelSpiritOfTheDark(context.bakeLayer(ModelSpiritOfTheDark.LAYER_LOCATION)), 0.5f);
        this.addLayer(new DarkSpiritEasterLayer(this,context.getModelSet()));
    }
    @Override
    protected void setupRotations(@NotNull DarkSpiritEntity spiritEntity, PoseStack stack, float p_115319_, float p_115320_, float p_115321_) {
        if (this.isShaking(spiritEntity))
            p_115320_ += (float)(Math.cos((double)spiritEntity.tickCount * 3.25D) * Math.PI * (double)0.4F);
        if (!spiritEntity.hasPose(Pose.SLEEPING))
            stack.mulPose(Axis.YP.rotationDegrees(180.0F - p_115320_));
        if (spiritEntity.isAutoSpinAttack()) {
            stack.mulPose(Axis.XP.rotationDegrees(-90.0F - spiritEntity.getXRot()));
            stack.mulPose(Axis.YP.rotationDegrees(((float)spiritEntity.tickCount + p_115321_) * -75.0F));
        }
        else if (isEntityUpsideDown(spiritEntity)) {
            stack.translate(0.0F, spiritEntity.getBbHeight() + 0.1F, 0.0F);
            stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        }
    }
    @Override
    public ResourceLocation getTextureLocation(DarkSpiritEntity entity) {
        return entity.shieldRose() ? new ResourceLocation("various_world:textures/entities/spirit_of_the_dark_shield" + DarkSpiritEntity.texture + ".png") : new ResourceLocation("various_world:textures/entities/spirit_of_the_dark" + DarkSpiritEntity.texture + ".png");
    }
}
