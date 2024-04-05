
package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.world.entity.Pose;
import net.sashakyotoz.variousworld.client.model.ModelFury_Lord;
import net.sashakyotoz.variousworld.client.model.ModelFury_Lord_Advanced;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;
import net.sashakyotoz.variousworld.entity.FuryLordEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FuryLordRenderer extends MobRenderer<FuryLordEntity, EntityModel<FuryLordEntity>> {
    private final EntityModel<FuryLordEntity> ordinary;
    private final EntityModel<FuryLordEntity> advanced;
    private static final ResourceLocation LORD_OF_FURRIES = new ResourceLocation("various_world:textures/entities/fury_lord.png");
    private static final ResourceLocation LORD_OF_FURRIES_ADVANCED = new ResourceLocation("various_world:textures/entities/fury_lord_advanced.png");

    public FuryLordRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelFury_Lord<>(context.bakeLayer(ModelFury_Lord.LAYER_LOCATION)), 0.5f);
        this.ordinary = new ModelFury_Lord<>(context.bakeLayer(ModelFury_Lord.LAYER_LOCATION));
        this.advanced = new ModelFury_Lord_Advanced<>(context.bakeLayer(ModelFury_Lord_Advanced.LAYER_LOCATION));
    }
    @Override
    protected void setupRotations(@NotNull FuryLordEntity furyLordEntity, PoseStack stack, float p_115319_, float shaking, float p_115321_) {
        if (this.isShaking(furyLordEntity))
            shaking += (float)(Math.cos((double)furyLordEntity.tickCount * 3.25D) * Math.PI * (double)0.4F);
        if (!furyLordEntity.hasPose(Pose.SLEEPING))
            stack.mulPose(Axis.YP.rotationDegrees(180.0F - shaking));
        if (furyLordEntity.isAutoSpinAttack()) {
            stack.mulPose(Axis.XP.rotationDegrees(-90.0F - furyLordEntity.getXRot()));
            stack.mulPose(Axis.YP.rotationDegrees(((float)furyLordEntity.tickCount + p_115321_) * -75.0F));
        }
        else if (isEntityUpsideDown(furyLordEntity)) {
            stack.translate(0.0F, furyLordEntity.getBbHeight() + 0.1F, 0.0F);
            stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        }
    }
    public void render(FuryLordEntity entity, float p_115778_, float p_115779_, PoseStack poseStack, MultiBufferSource bufferSource, int p_115782_) {
        this.model = entity.isAdvanced() ? this.advanced : this.ordinary;
        super.render(entity, p_115778_, p_115779_, poseStack, bufferSource, p_115782_);
    }

    @Override
    public ResourceLocation getTextureLocation(FuryLordEntity entity) {
        return entity.isAdvanced() ? LORD_OF_FURRIES_ADVANCED : LORD_OF_FURRIES;
    }
}
