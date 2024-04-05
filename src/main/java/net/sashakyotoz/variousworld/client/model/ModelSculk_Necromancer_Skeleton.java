package net.sashakyotoz.variousworld.client.model;

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
import net.minecraft.client.model.HierarchicalModel;

import net.sashakyotoz.variousworld.entity.SculkNecromancerSkeletonEntity;
import net.sashakyotoz.variousworld.entity.animations.SculkNecromancerAnimations;

import java.util.List;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

import com.google.common.collect.ImmutableList;

public class ModelSculk_Necromancer_Skeleton<T extends SculkNecromancerSkeletonEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_sculk_necromancer_skeleton"), "main");
	public final ModelPart head1;
	public final ModelPart root;
	public final ModelPart body;
	public final ModelPart cape;
	public final ModelPart arm0;
	public final ModelPart arm1;
	public final ModelPart leg0;
	public final ModelPart leg1;
	private final List<ModelPart> pulsatingSpotsLayerModelParts;

	public ModelSculk_Necromancer_Skeleton(ModelPart root) {
		this.root = root;
		this.head1 = root.getChild("head1");
		this.body = root.getChild("body");
		this.cape = body.getChild("cape");
		this.arm0 = root.getChild("arm0");
		this.arm1 = root.getChild("arm1");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
		this.pulsatingSpotsLayerModelParts = ImmutableList.of(this.body, this.head1, this.arm0, this.arm1, this.leg0, this.leg1);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head1 = partdefinition.addOrReplaceChild("head1",
				CubeListBuilder.create().texOffs(96, 33).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(88, 0).addBox(-5.0F, -12.0F, -5.0F, 10.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-1.5F, -12.5F, -6.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.5F, -12.5F, 5.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-6.0F, -12.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(5.0F, -12.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -6.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 70).mirror().addBox(-4.5F, 12.0F, -2.0F, 9.0F, 3.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false).texOffs(0, 0)
				.addBox(-6.5F, 0.0F, -3.0F, 13.0F, 12.0F, 6.0F, new CubeDeformation(0.01F)).texOffs(0, 46).addBox(-6.0F, 12.0F, -2.5F, 12.0F, 12.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -7.0F, 0.0F));
		PartDefinition upperBodyPart1 = body.addOrReplaceChild("upperBodyPart1", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, -1.0F));
		PartDefinition upperBodyPart2 = upperBodyPart1.addOrReplaceChild("upperBodyPart2",
				CubeListBuilder.create().texOffs(0, 22).addBox(0.5F, -13.9F, 0.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.25F)).texOffs(24, 22).addBox(-4.5F, -12.4F, 0.5F, 13.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 22)
						.addBox(-4.5F, -8.9F, 0.5F, 13.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 22).addBox(-4.5F, -5.4F, 0.5F, 13.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 7.9F, -0.5F));
		PartDefinition cape = body.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));
		PartDefinition cube_r1 = cape.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(91, 69).addBox(-8.0F, 0.0F, 1.25F, 16.0F, 23.0F, 0.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
		PartDefinition arm0 = partdefinition.addOrReplaceChild("arm0",
				CubeListBuilder.create().texOffs(0, 84).addBox(0.5F, -8.5F, 0.0F, 5.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(85, 101).mirror().addBox(-0.25F, -2.5F, -2.0F, 4.0F, 22.0F, 4.0F, new CubeDeformation(-0.5F)).mirror(false)
						.texOffs(0, 107).addBox(-0.5F, -3.5F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 107).addBox(-0.5F, 4.5F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1",
				CubeListBuilder.create().texOffs(110, 101).mirror().addBox(-3.75F, -2.5F, -2.0F, 4.0F, 22.0F, 4.0F, new CubeDeformation(-0.5F)).mirror(false).texOffs(0, 107).addBox(-4.5F, -3.5F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(0, 107).addBox(-4.5F, 4.5F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 84).mirror().addBox(-5.5F, -8.5F, 0.0F, 5.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-7.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition wand = arm1.addOrReplaceChild("wand", CubeListBuilder.create(), PartPose.offset(-2.0F, 18.0F, 0.0F));
		PartDefinition cube_r2 = wand.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, 3.0F, -5.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(34, 76).addBox(-0.5F, 8.0F, -9.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F)).texOffs(82, 22)
						.addBox(-0.5F, 6.0F, -8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(54, 68).addBox(-0.5F, 2.0F, -8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(87, 45)
						.addBox(-0.5F, 3.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(33, 68).addBox(-0.5F, 2.0F, -9.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(36, 79)
						.addBox(-0.5F, 8.0F, -6.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(42, 65).addBox(-0.5F, 10.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.01F)).texOffs(35, 62)
						.addBox(-0.5F, 7.0F, -3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(-0.01F)).texOffs(48, 78).addBox(-0.5F, 9.0F, -2.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(-0.01F)).texOffs(40, 71)
						.addBox(-0.5F, 8.0F, 3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(39, 54).addBox(-0.5F, 5.0F, -7.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(-0.01F)).texOffs(54, 76)
						.addBox(-0.5F, 10.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(48, 70).addBox(-0.5F, 9.0F, -5.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)).texOffs(37, 54)
						.addBox(-0.5F, 9.0F, -6.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(68, 14).addBox(-0.5F, 4.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(46, 0)
						.addBox(-0.5F, 2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)).texOffs(46, 0).addBox(-0.5F, 1.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(46, 0)
						.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)).texOffs(46, 0).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(46, 0)
						.addBox(-0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)).texOffs(0, 0).addBox(-0.5F, -4.0F, 2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 11.0F, 1.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition leg0_r1 = leg0.addOrReplaceChild("leg0_r1", CubeListBuilder.create().texOffs(21, 113).mirror().addBox(0.5F, 10.0F, -4.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(-0.75F)).mirror(false).texOffs(21, 105).mirror()
				.addBox(0.5F, -1.0F, -4.0F, 5.0F, 18.0F, 5.0F, new CubeDeformation(-1.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -3.0F, 0.0F, 0.0F, 0.0F, -0.0436F));
		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create(), PartPose.offset(-5.0F, 11.0F, 1.0F));
		PartDefinition leg1_r1 = leg1.addOrReplaceChild("leg1_r1",
				CubeListBuilder.create().texOffs(42, 113).addBox(-5.5F, 10.0F, -3.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(-0.75F)).texOffs(42, 105).addBox(-5.5F, -1.0F, -3.0F, 5.0F, 18.0F, 5.0F, new CubeDeformation(-1.0F)),
				PartPose.offsetAndRotation(5.0F, -3.0F, -1.0F, 0.0436F, 0.0F, 0.0436F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head1.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head1.xRot = headPitch / (180F / (float) Math.PI);
		this.animateWalk(SculkNecromancerAnimations.WALK,limbSwing,limbSwingAmount,2.0F, 2.5F);
		this.animate(entity.attackAnimationState, SculkNecromancerAnimations.ATTACK, ageInTicks);
		this.animate(entity.deathAnimationState, SculkNecromancerAnimations.DEATH, ageInTicks);
		this.animate(entity.spawnAnimationState, SculkNecromancerAnimations.SPAWN, ageInTicks);
		this.animateIdlePose(ageInTicks);
		int i = entity.getAttackAnimationRemainingTicks();
		if (i > 0) {
			head1.zRot = headPitch / (180F / (float) Math.PI) - 0.25f;
			arm1.xRot = 105f;
		}
	}

	private void animateIdlePose(float ageInTicks) {
		float f = ageInTicks * 0.1F;
		float f1 = Mth.cos(f);
		float f2 = Mth.sin(f);
		this.head1.zRot += 0.06F * f1;
		this.head1.xRot += 0.06F * f2;
		this.cape.xRot += 0.05F * f1;
	}

	public ModelPart root() {
		return this.root;
	}

	public List<ModelPart> getPulsatingSpotsLayerModelParts() {
		return this.pulsatingSpotsLayerModelParts;
	}
}
