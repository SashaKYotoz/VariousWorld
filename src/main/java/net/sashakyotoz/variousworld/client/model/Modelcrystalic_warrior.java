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

import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;
import net.sashakyotoz.variousworld.entity.animations.CrystalicWarriorAnimations;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class Modelcrystalic_warrior<T extends CrystalWarriorEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "modelcrystalic_warrior"), "main");
	public final ModelPart root;
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart arm0;
	public final ModelPart arm1;
	public final ModelPart leg0;
	public final ModelPart lap0;
	public final ModelPart leg1;
	public final ModelPart lap1;

	public Modelcrystalic_warrior(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.arm0 = root.getChild("arm0");
		this.arm1 = root.getChild("arm1");
		this.leg0 = root.getChild("leg0");
		this.lap0 = leg0.getChild("lap0");
		this.leg1 = root.getChild("leg1");
		this.lap1 = leg1.getChild("lap1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.5F, -13.0F, -5.0F, 11.0F, 12.0F, 11.0F, new CubeDeformation(-1.0F)).mirror(false)
				.texOffs(84, 0).mirror().addBox(-5.5F, -13.0F, -5.0F, 11.0F, 12.0F, 11.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offset(0.0F, -4.5F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(2, 40).mirror().addBox(-9.0F, -7.0F, -4.5F, 18.0F, 13.0F, 10.0F, new CubeDeformation(-0.25F)).mirror(false)
				.texOffs(8, 47).mirror().addBox(-7.0F, -5.0F, -5.0F, 14.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 70).mirror().addBox(-7.5F, 4.0F, -2.0F, 15.0F, 5.0F, 5.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(0, 71).mirror().addBox(-5.5F, 4.5F, -2.5F, 11.0F, 4.0F, 5.0F, new CubeDeformation(0.5F)).mirror(false)
				.texOffs(0, 72).mirror().addBox(-5.5F, 4.5F, -1.0F, 11.0F, 4.0F, 5.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition arm0 = partdefinition.addOrReplaceChild("arm0", CubeListBuilder.create(), PartPose.offset(8.0F, -6.0F, 1.0F));

		PartDefinition cube_r1 = arm0.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(104, 85).addBox(0.0F, -3.0F, -4.0F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 2.0F, 0.5F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r2 = arm0.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(106, 25).mirror().addBox(-1.0F, 16.0F, -3.5F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.25F)).mirror(false)
				.texOffs(0, 81).addBox(-0.5F, -1.5F, -3.5F, 4.0F, 21.0F, 6.0F, new CubeDeformation(0.15F))
				.texOffs(60, 21).mirror().addBox(-0.5F, -1.5F, -3.5F, 4.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 2.0F, 0.5F, 0.0F, 0.0F, -0.0436F));

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create(), PartPose.offset(-8.0F, -6.0F, 1.0F));

		PartDefinition cube_r3 = arm1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(104, 85).mirror().addBox(-5.0F, -3.0F, -4.0F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.0F, 0.5F, 0.0F, 0.0F, 0.6109F));

		PartDefinition cube_r4 = arm1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(106, 25).addBox(-4.0F, 16.0F, -3.5F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.25F))
				.texOffs(0, 81).mirror().addBox(-3.5F, -1.5F, -3.5F, 4.0F, 21.0F, 6.0F, new CubeDeformation(0.15F)).mirror(false)
				.texOffs(60, 58).mirror().addBox(-3.5F, -1.5F, -3.5F, 4.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.0F, 0.5F, 0.0F, 0.0F, 0.0436F));

		PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(106, 48).addBox(-2.5F, -3.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(37, 0).mirror().addBox(-2.5F, -3.0F, -2.5F, 6.0F, 9.0F, 5.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offset(4.0F, 11.0F, 1.0F));

		PartDefinition lap0 = leg0.addOrReplaceChild("lap0", CubeListBuilder.create().texOffs(37, 8).mirror().addBox(-2.5F, 1.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(-0.251F)).mirror(false)
				.texOffs(0, 120).addBox(-2.0F, 8.0F, -4.5F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.26F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(106, 48).mirror().addBox(-2.5F, -3.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(60, 0).addBox(-2.5F, -3.0F, -2.5F, 6.0F, 9.0F, 5.0F, new CubeDeformation(-0.25F)), PartPose.offset(-5.0F, 11.0F, 1.0F));

		PartDefinition lap1 = leg1.addOrReplaceChild("lap1", CubeListBuilder.create().texOffs(60, 8).addBox(-2.5F, 1.0F, -2.5F, 6.0F, 8.0F, 5.0F, new CubeDeformation(-0.251F))
				.texOffs(0, 120).mirror().addBox(-2.0F, 8.0F, -4.5F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.26F)).mirror(false), PartPose.offset(0.0F, 4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float f = Math.min((float) entity.getDeltaMovement().lengthSqr() * 200.0F, 8.0F);
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.animate(entity.walkAnimationState, CrystalicWarriorAnimations.WALK, ageInTicks, f);
		this.animate(entity.attackAnimationState, CrystalicWarriorAnimations.ATTACK, ageInTicks);
		this.animate(entity.deathAnimationState, CrystalicWarriorAnimations.DEATH, ageInTicks);
		this.animate(entity.groundAttackAnimationState, CrystalicWarriorAnimations.GROUND_ATTACK, ageInTicks);
	}

	public ModelPart root() {
		return this.root;
	}
}
