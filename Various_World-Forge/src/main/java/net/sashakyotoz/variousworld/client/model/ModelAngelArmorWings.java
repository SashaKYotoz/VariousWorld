package net.sashakyotoz.variousworld.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ModelAngelArmorWings<T extends LivingEntity> extends AgeableListModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_angel_armor_wings"), "main");
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public ModelAngelArmorWings(ModelPart root) {
		this.rightWing = root.getChild("rightWing");
		this.leftWing = root.getChild("leftWing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 1.5F));

		PartDefinition cube_r1 = rightWing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 17).addBox(-3.0F, -16.0F, -3.0F, 4.0F, 15.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, -3.0543F, 0.0F, 1.7453F));

		PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 1.5F));

		PartDefinition cube_r2 = leftWing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 0).addBox(-3.0F, 1.8007F, -3.0F, 4.0F, 15.0F, 0.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 3.0543F, 0.0F, 1.3963F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 0.2618F;
		if(entity instanceof Player player && player.getAbilities().flying){
			this.leftWing.yRot = Mth.triangleWave(limbSwing,10f) * -f*1.5f - 0.3f;
			this.rightWing.yRot = -(Mth.triangleWave(limbSwing,10f) * -f*1.5f - 0.3f);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.rightWing.render(poseStack, buffer, packedLight, packedOverlay);
		this.leftWing.render(poseStack, buffer, packedLight, packedOverlay);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.rightWing,this.leftWing);
	}
}
