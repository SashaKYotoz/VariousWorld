package net.sashakyotoz.variousworld.client.model;

import net.minecraft.world.entity.HumanoidArm;
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

import net.sashakyotoz.variousworld.entity.SculkSkeletonEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ModelSculk_Skeleton<T extends SculkSkeletonEntity> extends EntityModel<T> implements ArmedModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_sculk_skeleton"), "main");
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;
	public final ModelPart right_Boot;
	public final ModelPart leftBoot;

	public ModelSculk_Skeleton(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.right_Boot = root.getChild("right_Boot");
		this.leftBoot = root.getChild("leftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(118, 63).addBox(-15.0F, -13.5F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.0F, 2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(118, 63).mirror().addBox(-1.0F, -7.5F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.0F, -9.0F, 0.0F, 0.0F, 0.0F, -1.5708F));
		PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(0, 44).addBox(-4.5F, -9.75F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r3 = helmet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -1.0F, 3.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.1F)),
				PartPose.offsetAndRotation(4.0F, -4.0F, -5.0F, 0.1745F, 0.0F, 1.5708F));
		PartDefinition cube_r4 = helmet.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 95).addBox(-2.0F, -1.0F, 3.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.1F)),
				PartPose.offsetAndRotation(-4.0F, -4.0F, -5.0F, 0.1745F, 0.0F, -1.5708F));
		PartDefinition cube_r5 = helmet.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 70).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -6.0F, -5.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition chestplate = body.addOrReplaceChild("chestplate", CubeListBuilder.create().texOffs(104, 21).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(112, 0).mirror()
				.addBox(-3.5F, -2.0F, -2.75F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(112, 0).addBox(-3.5F, -3.0F, -2.75F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));
		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm",
				CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-0.75F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(112, 46).addBox(-1.25F, -2.25F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r6 = rightArm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(120, 81).addBox(-0.5F, -1.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm",
				CubeListBuilder.create().texOffs(40, 16).addBox(-1.25F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 46).addBox(-1.75F, -2.25F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r7 = leftArm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(120, 81).addBox(-0.5F, -1.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg",
				CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(114, 98).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.1F)),
				PartPose.offset(2.0F, 12.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg",
				CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(114, 98).mirror().addBox(-2.0F, 0.0F, -1.5F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.099F)).mirror(false),
				PartPose.offset(-2.0F, 12.0F, 0.0F));
		PartDefinition right_Boot = partdefinition.addOrReplaceChild("right_Boot", CubeListBuilder.create().texOffs(71, 63).addBox(-2.0F, 10.25F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));
		PartDefinition leftBoot = partdefinition.addOrReplaceChild("leftBoot", CubeListBuilder.create().texOffs(71, 75).addBox(-2.0F, 10.25F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_Boot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.rightLeg.xRot = Mth.cos(limbSwing) * 1.0F * limbSwingAmount;
		this.rightArm.xRot = Mth.cos(limbSwing * 0.75F + (float) Math.PI) * limbSwingAmount;
		this.right_Boot.xRot = Mth.cos(limbSwing) * 1.0F * limbSwingAmount;
		this.leftArm.xRot = Mth.cos(limbSwing * 0.75F) * limbSwingAmount - 1.0f;
		this.leftLeg.xRot = Mth.cos(limbSwing) * -1.0F * limbSwingAmount;
		this.leftBoot.xRot = Mth.cos(limbSwing) * -1.0F * limbSwingAmount;
	}

	@Override
	public void translateToHand(HumanoidArm p_102854_, PoseStack p_102855_) {
		this.getArm(p_102854_).translateAndRotate(p_102855_);
	}

	protected ModelPart getArm(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.rightArm : this.leftArm;
	}
}
