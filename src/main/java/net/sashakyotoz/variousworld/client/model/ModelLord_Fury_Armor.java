package net.sashakyotoz.variousworld.client.model;

import net.minecraft.world.entity.Entity;
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

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelLord_Fury_Armor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_lord_fury_armor"), "main");
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart leftArm;
	public final ModelPart rightArm;
	public final ModelPart leftLeg;
	public final ModelPart rightLeg;
	public final ModelPart rightBoot;
	public final ModelPart leftBoot;

	public ModelLord_Fury_Armor(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
		this.rightBoot = root.getChild("rightBoot");
		this.leftBoot = root.getChild("leftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition helmet = head.addOrReplaceChild("helmet",
				CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.0F, -11.0F, -5.25F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-1.0F, -11.0F, 4.25F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(45, 27).addBox(4.75F, -7.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(45, 27)
						.addBox(-6.75F, -7.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 6).addBox(5.75F, -10.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 6)
						.addBox(-6.75F, -10.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = helmet.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 20).addBox(-2.5F, 0.0F, 0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, 5.0F, -0.2182F, 0.0F, 0.0F));
		PartDefinition cube_r2 = helmet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 20).addBox(-1.5F, 0.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.2182F));
		PartDefinition cube_r3 = helmet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 20).addBox(0.5F, 0.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.2182F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition chestplate = body.addOrReplaceChild("chestplate", CubeListBuilder.create().texOffs(100, 26).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r4 = chestplate.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(59, 0).addBox(0.25F, 0.0F, 0.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(59, 11).addBox(-4.25F, 0.0F, 0.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.0F, -3.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r5 = chestplate.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(100, 30).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 3.0543F, 0.0F, 3.1416F));
		PartDefinition cube_r6 = chestplate.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(100, 30).addBox(-4.0F, -1.0F, -3.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(-6.0F, 2.0F, 0.0F));
		PartDefinition leftchestplate = leftArm.addOrReplaceChild("leftchestplate",
				CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)).texOffs(20, 69).addBox(-3.0F, 11.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.24F)).texOffs(92, 0)
						.addBox(-3.0F, 6.0F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.25F)).texOffs(0, 39).addBox(-2.0F, 2.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(-0.1F)),
				PartPose.offset(0.0F, -1.0F, 0.0F));
		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(6.0F, 2.0F, 0.0F));
		PartDefinition rightchestplate = rightArm.addOrReplaceChild("rightchestplate",
				CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.1F)).texOffs(0, 39).addBox(-2.0F, 2.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(-0.1F)).texOffs(0, 69)
						.addBox(-1.5F, 11.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.24F)).texOffs(0, 92).addBox(0.5F, 6.0F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, -1.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition leggins = leftLeg.addOrReplaceChild("leggins", CubeListBuilder.create().texOffs(0, 112).addBox(-2.1F, 1.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r7 = leggins.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(31, 113).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.0436F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition legginsright = rightLeg.addOrReplaceChild("legginsright", CubeListBuilder.create().texOffs(0, 112).addBox(-1.9F, 1.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r8 = legginsright.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(31, 113).addBox(-0.1F, 0.0F, -1.5F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.2F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0436F));
		PartDefinition rightBoot = partdefinition.addOrReplaceChild("rightBoot",
				CubeListBuilder.create().texOffs(108, 69).addBox(-2.0F, 8.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(69, 126).addBox(-2.0F, 9.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 25.0F, 0.0F));
		PartDefinition leftBoot = partdefinition.addOrReplaceChild("leftBoot",
				CubeListBuilder.create().texOffs(108, 69).addBox(-2.0F, 8.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(69, 126).addBox(-2.0F, 9.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, 25.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		leftArm.render(poseStack, buffer, packedLight, packedOverlay);
		rightArm.render(poseStack, buffer, packedLight, packedOverlay);
		leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		rightBoot.render(poseStack, buffer, packedLight, packedOverlay);
		leftBoot.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
