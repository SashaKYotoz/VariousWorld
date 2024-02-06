
package net.sashakyotoz.variousworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.world.entity.Pose;
import net.sashakyotoz.variousworld.client.model.Modelcrystalic_warrior;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CrystalWarriorRenderer extends MobRenderer<CrystalWarriorEntity, Modelcrystalic_warrior<CrystalWarriorEntity>> {
	public CrystalWarriorRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcrystalic_warrior(context.bakeLayer(Modelcrystalic_warrior.LAYER_LOCATION)), 0.5f);
		this.addLayer(new EyesLayer<>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("various_world:textures/entities/crystal_warrior.png"));
			}
		});
	}
	@Override
	protected void setupRotations(@NotNull CrystalWarriorEntity warriorEntity, PoseStack stack, float p_115319_, float p_115320_, float p_115321_) {
		if (this.isShaking(warriorEntity))
			p_115320_ += (float)(Math.cos((double)warriorEntity.tickCount * 3.25D) * Math.PI * (double)0.4F);
		if (!warriorEntity.hasPose(Pose.SLEEPING))
			stack.mulPose(Axis.YP.rotationDegrees(180.0F - p_115320_));
		if (warriorEntity.isAutoSpinAttack()) {
			stack.mulPose(Axis.XP.rotationDegrees(-90.0F - warriorEntity.getXRot()));
			stack.mulPose(Axis.YP.rotationDegrees(((float)warriorEntity.tickCount + p_115321_) * -75.0F));
		}
		else if (isEntityUpsideDown(warriorEntity)) {
			stack.translate(0.0F, warriorEntity.getBbHeight() + 0.1F, 0.0F);
			stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(CrystalWarriorEntity entity) {
		return new ResourceLocation("various_world:textures/entities/crystal_warrior.png");
	}
}
