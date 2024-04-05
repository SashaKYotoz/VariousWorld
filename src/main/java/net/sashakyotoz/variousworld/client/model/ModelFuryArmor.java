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

// Made with Blockbench 4.5.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelFuryArmor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_fury_armor"), "main");
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart leftArm;
	public final ModelPart rightArm;
	public final ModelPart leftLeg;
	public final ModelPart rightLeg;
	public final ModelPart rightBoot;
	public final ModelPart leftBoot;

	public ModelFuryArmor(ModelPart root) {
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
				CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.01F)).texOffs(60, 0).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.05F)).texOffs(6, 38)
						.addBox(2.0F, -2.0F, -5.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(6, 38).mirror().addBox(-5.0F, -2.0F, -5.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 126)
						.addBox(-4.0F, -10.0F, 3.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = helmet.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 114).addBox(-3.0F, -5.0F, 6.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition cube_r2 = helmet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(29, 115).addBox(-7.0F, -6.0F, -2.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.1745F));
		PartDefinition cube_r3 = helmet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(29, 115).addBox(6.0F, -6.0F, -2.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, -0.1745F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition chestplate = body.addOrReplaceChild("chestplate", CubeListBuilder.create().texOffs(0, 33).addBox(-4.0F, 1.0F, -3.0F, 8.0F, 11.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r4 = chestplate.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 80).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(0.0F, 6.0F, -2.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r5 = chestplate.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(0, 66).addBox(-3.0F, 9.0F, 4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)).texOffs(0, 66).addBox(-3.0F, 4.25F, -0.75F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r6 = chestplate.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(0, 57).addBox(1.0F, 4.0F, 3.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.25F)).texOffs(2, 57).addBox(-2.0F, 0.0F, 5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)).texOffs(2, 57).mirror()
						.addBox(-3.0F, 1.0F, 5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)).mirror(false).texOffs(2, 57).addBox(2.0F, 1.0F, 5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)).texOffs(2, 57)
						.addBox(1.0F, 0.0F, 5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)).texOffs(0, 57).addBox(-3.0F, 4.0F, 3.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.3491F, 0.0F, 0.0F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(-7.0F, 2.0F, 0.0F));
		PartDefinition chestplate2 = leftArm.addOrReplaceChild("chestplate2",
				CubeListBuilder.create().texOffs(0, 94).mirror().addBox(-2.0F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false).texOffs(15, 51).addBox(-2.0F, 3.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(7.0F, 2.0F, 0.0F));
		PartDefinition chestplate3 = rightArm.addOrReplaceChild("chestplate3",
				CubeListBuilder.create().texOffs(0, 94).addBox(9.5F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.5F)).texOffs(15, 51).mirror().addBox(9.0F, 3.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-12.0F, 0.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition leggins = leftLeg.addOrReplaceChild("leggins", CubeListBuilder.create().texOffs(42, 2).addBox(-2.1F, 0.0F, -2.5F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.9F)), PartPose.offset(0.0F, 0.0F, 0.5F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition leggins2 = rightLeg.addOrReplaceChild("leggins2", CubeListBuilder.create().texOffs(42, 2).mirror().addBox(0.0F, -12.0F, -2.5F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.5F));
		PartDefinition rightBoot = partdefinition.addOrReplaceChild("rightBoot", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, 11.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.3F)).texOffs(41, 30)
				.addBox(-2.0F, 12.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)).texOffs(50, 24).addBox(-2.0F, 10.0F, 3.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
		PartDefinition leftBoot = partdefinition.addOrReplaceChild("leftBoot", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-2.0F, 11.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.3F)).mirror(false).texOffs(41, 30)
				.addBox(-2.0F, 12.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)).texOffs(50, 24).addBox(-2.0F, 10.0F, 3.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offset(2.0F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
