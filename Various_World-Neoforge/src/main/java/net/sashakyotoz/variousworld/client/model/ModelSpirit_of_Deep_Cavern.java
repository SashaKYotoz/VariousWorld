package net.sashakyotoz.variousworld.client.model;

import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ArmedModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ModelSpirit_of_Deep_Cavern<T extends Entity> extends EntityModel<T> implements ArmedModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_spirit_of_deep_cavern"), "main");
	public final ModelPart head;
	public final ModelPart rightWing;
	public final ModelPart leftWing;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart aura;

	public ModelSpirit_of_Deep_Cavern(ModelPart root) {
		this.head = root.getChild("head");
		this.rightWing = root.getChild("rightWing");
		this.leftWing = root.getChild("leftWing");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
		this.aura = root.getChild("aura");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -20.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-1.0F)).texOffs(0, 47).addBox(-4.0F, -20.25F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(-0.75F)),
				PartPose.offset(0.0F, 26.0F, 0.0F));
		PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));
		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(32, 53).addBox(-3.5F, -1.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)).texOffs(32, 3).addBox(-3.5F, -1.0F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -5.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 4.0F));
		PartDefinition cube_r2 = rightWing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 33).addBox(-10.0F, -3.0F, -0.1F, 10.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.3054F, 0.0436F));
		PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 4.0F));
		PartDefinition cube_r3 = leftWing.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(1, 33).mirror().addBox(0.0F, -3.0F, -0.1F, 10.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, -0.3054F, -0.0436F));
		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(-2.0F, 14.0F, 0.0F));
		PartDefinition cube_r4 = rightArm.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(58, 0).mirror().addBox(-1.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false).texOffs(35, 2).addBox(-1.5F, -1.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.7854F, 0.0F, 0.2618F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(2.0F, 14.0F, 0.0F));
		PartDefinition cube_r5 = leftArm.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(58, 0).addBox(0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.25F)).texOffs(35, 2).mirror().addBox(0.5F, -1.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.7854F, 0.0F, -0.2618F));
		PartDefinition aura = partdefinition.addOrReplaceChild("aura",
				CubeListBuilder.create().texOffs(62, 8).addBox(-1.0F, -2.0F, -10.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.001F)).texOffs(58, 32).addBox(-2.5F, -1.0F, 10.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(58, 24)
						.addBox(-1.0F, -1.0F, 9.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0002F)).texOffs(62, 8).addBox(-1.0F, -2.0F, 10.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.001F)).texOffs(58, 24)
						.addBox(-1.0F, -1.0F, -11.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0002F)).texOffs(58, 32).addBox(-2.5F, -1.0F, -9.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, 16.0F, 0.0F));
		PartDefinition cube_r6 = aura.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(58, 16).addBox(-1.0F, -0.5F, -9.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(58, 16).addBox(-1.0F, -0.5F, 10.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, buffer, packedLight, packedOverlay);
		rightWing.render(poseStack, buffer, packedLight, packedOverlay);
		leftWing.render(poseStack, buffer, packedLight, packedOverlay);
		rightArm.render(poseStack, buffer, packedLight, packedOverlay);
		leftArm.render(poseStack, buffer, packedLight, packedOverlay);
		aura.render(poseStack, buffer, packedLight, packedOverlay);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.leftWing.yRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.rightWing.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.aura.yRot = ageInTicks / 20.f;
	}

	@Override
	public void translateToHand(HumanoidArm p_102854_, PoseStack p_102855_) {
		this.getArm(p_102854_).translateAndRotate(p_102855_);
		p_102855_.scale(0.7F, 0.7F, 0.7F);
		p_102855_.translate(-0.2F, -0.15F, -0.3F);
	}

	protected ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
	}
}
