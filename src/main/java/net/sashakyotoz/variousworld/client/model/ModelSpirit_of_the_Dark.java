package net.sashakyotoz.variousworld.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.HierarchicalModel;

import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;
import net.sashakyotoz.variousworld.entity.animations.DarkSpiritAnimations;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ModelSpirit_of_the_Dark<T extends DarkSpiritEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "modelspirit_of_the_dark"), "main");
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightWing;
	public final ModelPart LeftWing;
	public final ModelPart RightArm;
	public final ModelPart RightArmLayers;
	public final ModelPart LeftArmLayers;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart shield;
	public final ModelPart root;

	public ModelSpirit_of_the_Dark(ModelPart root) {
		this.root = root;
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.RightWing = root.getChild("RightWing");
		this.LeftWing = root.getChild("LeftWing");
		this.RightArm = root.getChild("RightArm");
		this.RightArmLayers = RightArm.getChild("RightArmLayers");
		this.LeftArm = root.getChild("LeftArm");
		this.LeftArmLayers = LeftArm.getChild("LeftArmLayers");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.shield = root.getChild("shield");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition HatLayer_r1 = Head.addOrReplaceChild("HatLayer_r1", CubeListBuilder.create().texOffs(68, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
				PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(84, 15).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition RightWing = partdefinition.addOrReplaceChild("RightWing", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));
		PartDefinition cube_r1 = RightWing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 36).addBox(-5.0F, -15.0F, -1.0F, 6.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.2618F, 0.0F, -1.4835F));
		PartDefinition LeftWing = partdefinition.addOrReplaceChild("LeftWing", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));
		PartDefinition cube_r2 = LeftWing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 36).mirror().addBox(-1.0F, -15.0F, -1.0F, 6.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.2618F, 0.0F, 1.4835F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(64, 33).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)),
				PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition RightArmLayers = RightArm.addOrReplaceChild("RightArmLayers",
				CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, 13.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(38, 0).addBox(-2.5F, 14.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.0F, -2.0F, 0.0F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(64, 33).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)).mirror(false),
				PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
		PartDefinition LeftArmLayers = LeftArm.addOrReplaceChild("LeftArmLayers", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition LeftArmLayer_r1 = LeftArmLayers.addOrReplaceChild("LeftArmLayer_r1", CubeListBuilder.create().texOffs(38, 0).mirror().addBox(-2.5F, 14.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(32, 0)
				.mirror().addBox(-1.5F, 13.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(68, 22).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.2601F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg",
				CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(68, 22).mirror().addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.26F)).mirror(false),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition shield = partdefinition.addOrReplaceChild("shield",
				CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -8.0F, -16.0F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 32).addBox(-8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 8.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		shield.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public ModelPart root() {
		return this.root;
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.idleAnimationState, DarkSpiritAnimations.IDLE, ageInTicks);
		this.animate(entity.idleWithShieldAnimationState, DarkSpiritAnimations.IDLESHIELD, ageInTicks);
		this.animate(entity.attackAnimationState, DarkSpiritAnimations.ATTACK, ageInTicks);
		this.animate(entity.attack1AnimationState, DarkSpiritAnimations.ATTACK1, ageInTicks);
		this.animate(entity.flightAnimationState, DarkSpiritAnimations.FLIGHT, ageInTicks);
		this.animate(entity.flightWithShieldAnimationState, DarkSpiritAnimations.FLIGHTSHIELD, ageInTicks);
		this.animate(entity.shieldAriseAnimationState, DarkSpiritAnimations.SHIELD, ageInTicks);
		this.animate(entity.spellAriseAnimationState, DarkSpiritAnimations.SPELL, ageInTicks);
		this.animate(entity.spawnAnimationState, DarkSpiritAnimations.SPAWNANIM, ageInTicks);
	}
}
