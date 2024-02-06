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

import net.sashakyotoz.variousworld.entity.FuryLordEntity;
import net.sashakyotoz.variousworld.entity.animations.LordOfFuryAnimations;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ModelFury_Lord_Advanced<T extends FuryLordEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_fury_lord_advanced"), "main");
	public final ModelPart root;
	public final ModelPart generic;
	public final ModelPart head;
	public final ModelPart neck;
	public final ModelPart body;
	public final ModelPart bottom_tooth;
	public final ModelPart wingRight;
	public final ModelPart wingLeft;
	public final ModelPart forwardLeftLeg;
	public final ModelPart forwardRightLeg;
	public final ModelPart rearRightLeg;
	public final ModelPart rearLeftLeg;
	public final ModelPart tail;
	public final ModelPart tail2;
	public final ModelPart tail3;
	public final ModelPart tail4;
	public final ModelPart tail5;
	public final ModelPart tail6;

	public ModelFury_Lord_Advanced(ModelPart root) {
		this.root = root;
		this.generic = root.getChild("generic");
		this.neck = generic.getChild("neck");
		this.head = neck.getChild("head");
		this.bottom_tooth = head.getChild("bottom_tooth");
		this.body = generic.getChild("body");
		this.wingRight = generic.getChild("wingRight");
		this.wingLeft = generic.getChild("wingLeft");
		this.forwardLeftLeg = generic.getChild("forwardLeftLeg");
		this.forwardRightLeg = generic.getChild("forwardRightLeg");
		this.rearRightLeg = generic.getChild("rearRightLeg");
		this.rearLeftLeg = generic.getChild("rearLeftLeg");
		this.tail = generic.getChild("tail");
		this.tail2 = tail.getChild("tail2");
		this.tail3 = tail2.getChild("tail3");
		this.tail4 = tail3.getChild("tail4");
		this.tail5 = tail4.getChild("tail5");
		this.tail6 = tail5.getChild("tail6");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition generic = partdefinition.addOrReplaceChild("generic", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition neck = generic.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -10.0F));

		PartDefinition cube_r1 = neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(157, 44).addBox(-5.0F, -6.0F, -19.25F, 10.0F, 9.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.75F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r2 = neck.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(119, 44).addBox(-5.0F, 2.0F, -28.0F, 10.0F, 8.0F, 9.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.0F, 0.5F, 5.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r3 = neck.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(210, 44).addBox(-6.0F, -7.25F, -12.0F, 12.0F, 12.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.75F, 0.1309F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -10.5F, -11.5F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.5F))
				.texOffs(0, 24).addBox(-6.0F, -4.5F, -21.5F, 12.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(6.5F, -16.6745F, -5.4962F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).mirror().addBox(-9.5F, -16.6745F, -5.4962F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).addBox(-6.5F, -16.6745F, -5.4962F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).mirror().addBox(3.5F, -16.6745F, -5.4962F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(36, 0).mirror().addBox(3.0F, -6.0F, -20.25F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(36, 0).addBox(-5.0F, -6.0F, -20.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -23.5F));

		PartDefinition bottom_tooth = head.addOrReplaceChild("bottom_tooth", CubeListBuilder.create().texOffs(0, 40).addBox(-7.0F, -1.5F, -9.5F, 12.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -12.0F));

		PartDefinition body = generic.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(132, 27).mirror().addBox(-2.0F, -12.25F, -16.0F, 3.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(132, 27).mirror().addBox(-2.0F, 1.75F, 2.0F, 3.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(132, 27).mirror().addBox(-2.0F, -5.25F, -6.0F, 3.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -20.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(185, 33).mirror().addBox(-2.0F, -4.5F, -3.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -16.0F, 15.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(44, 28).addBox(-7.0F, -9.6F, 12.75F, 14.0F, 14.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -8.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(44, 28).addBox(-7.0F, -7.35F, -1.25F, 14.0F, 14.0F, 16.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -12.0F, -8.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition wingRight = generic.addOrReplaceChild("wingRight", CubeListBuilder.create(), PartPose.offset(-7.0F, 0.0F, -3.0F));

		PartDefinition cube_r8 = wingRight.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-19, 104).mirror().addBox(-31.0F, -1.0F, 3.0F, 32.0F, 0.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.2618F, -0.1658F));

		PartDefinition cube_r9 = wingRight.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(70, 107).addBox(-31.0F, -4.0F, -3.0F, 32.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.2618F, -0.1745F));

		PartDefinition wingRightBone = wingRight.addOrReplaceChild("wingRightBone", CubeListBuilder.create(), PartPose.offsetAndRotation(-31.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r10 = wingRightBone.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(49, 34).addBox(0.0F, 3.0F, 5.0F, 3.0F, 2.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.1745F, -0.1745F));

		PartDefinition cube_r11 = wingRightBone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(137, 100).addBox(-32.0F, 4.0F, 5.0F, 32.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(69, 118).mirror().addBox(-32.0F, 2.0F, -1.0F, 32.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.2618F, -0.1745F));

		PartDefinition wingLeft = generic.addOrReplaceChild("wingLeft", CubeListBuilder.create(), PartPose.offset(7.0F, 0.0F, -3.0F));

		PartDefinition cube_r12 = wingLeft.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(-19, 104).addBox(-1.0F, -1.0F, 3.0F, 32.0F, 0.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.2618F, 0.1833F));

		PartDefinition cube_r13 = wingLeft.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(70, 107).addBox(-1.0F, -4.0F, -3.0F, 32.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.2618F, 0.1745F));

		PartDefinition wingLeftBone = wingLeft.addOrReplaceChild("wingLeftBone", CubeListBuilder.create(), PartPose.offset(31.0F, 0.0F, 5.0F));

		PartDefinition cube_r14 = wingLeftBone.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(49, 34).mirror().addBox(-3.0F, 4.0F, 5.0F, 3.0F, 2.0F, 22.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.1745F, 0.1745F));

		PartDefinition cube_r15 = wingLeftBone.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(137, 100).mirror().addBox(0.0F, 5.0F, 5.0F, 32.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(69, 118).addBox(0.0F, 2.0F, -1.0F, 32.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.2618F, 0.1745F));

		PartDefinition forwardLeftLeg = generic.addOrReplaceChild("forwardLeftLeg", CubeListBuilder.create(), PartPose.offset(7.0F, 11.0F, -3.0F));

		PartDefinition cube_r16 = forwardLeftLeg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(72, 0).mirror().addBox(-1.0F, 17.0F, -10.0F, 6.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition cube_r17 = forwardLeftLeg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(128, 24).addBox(-1.0F, 10.0F, -6.0F, 6.0F, 12.0F, 8.0F, new CubeDeformation(-1.0F))
				.texOffs(99, 23).mirror().addBox(-1.0F, 0.0F, -6.0F, 6.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, -0.0873F, 0.0F, -0.0436F));

		PartDefinition forwardRightLeg = generic.addOrReplaceChild("forwardRightLeg", CubeListBuilder.create(), PartPose.offset(-7.0F, 11.0F, -3.0F));

		PartDefinition cube_r18 = forwardRightLeg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(72, 0).addBox(-19.0F, 17.0F, -10.0F, 6.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r19 = forwardRightLeg.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(128, 24).mirror().addBox(-5.0F, 10.0F, -6.0F, 6.0F, 12.0F, 8.0F, new CubeDeformation(-1.0F)).mirror(false)
				.texOffs(99, 23).addBox(-5.0F, 0.0F, -6.0F, 6.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition rearRightLeg = generic.addOrReplaceChild("rearRightLeg", CubeListBuilder.create(), PartPose.offset(-7.0F, 11.0F, 12.0F));

		PartDefinition cube_r20 = rearRightLeg.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(72, 0).addBox(-19.0F, 22.932F, -17.5405F, 6.0F, 3.0F, 11.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(14.0F, -5.932F, 12.5405F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r21 = rearRightLeg.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(99, 23).addBox(-5.0F, 14.932F, -13.5405F, 6.0F, 13.0F, 8.0F, new CubeDeformation(-1.0F))
				.texOffs(99, 23).addBox(-5.0F, 4.932F, -13.5405F, 6.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.932F, 12.5405F, -0.0873F, 0.0F, 0.0436F));

		PartDefinition rearLeftLeg = generic.addOrReplaceChild("rearLeftLeg", CubeListBuilder.create(), PartPose.offset(7.0F, 11.0F, 12.0F));

		PartDefinition cube_r22 = rearLeftLeg.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(99, 23).mirror().addBox(-1.0F, 10.0F, -6.0F, 6.0F, 13.0F, 8.0F, new CubeDeformation(-1.0F)).mirror(false)
				.texOffs(99, 23).mirror().addBox(-1.0F, 0.0F, -6.0F, 6.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.0873F, 0.0F, -0.0436F));

		PartDefinition cube_r23 = rearLeftLeg.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(72, 0).mirror().addBox(-1.0F, 18.0F, -10.0F, 6.0F, 3.0F, 11.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 5.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition tail = generic.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 21.0F));

		PartDefinition cube_r24 = tail.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(158, 15).addBox(-4.5F, -3.5F, 0.0F, 8.0F, 8.0F, 10.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r25 = tail.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(185, 33).mirror().addBox(-2.0F, -5.0F, -3.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, 4.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition cube_r26 = tail2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(185, 33).mirror().addBox(-2.0F, -5.0F, -3.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(198, 0).addBox(-4.0F, -2.5F, -4.0F, 7.0F, 7.0F, 11.0F, new CubeDeformation(0.9F)), PartPose.offsetAndRotation(0.0F, -2.0F, 9.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition cube_r27 = tail3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(203, 45).addBox(-1.5F, -5.75F, -2.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(138, 0).addBox(-3.5F, -3.25F, -3.0F, 6.0F, 7.0F, 9.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, -1.0F, 12.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition cube_r28 = tail4.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(192, 54).addBox(-1.5F, -5.5F, 1.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(168, 0).addBox(-3.5F, -3.0F, 0.75F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, -1.5F, 11.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition cube_r29 = tail5.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(190, 44).mirror().addBox(-1.5F, -5.75F, 0.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(108, 0).addBox(-3.5F, -4.0F, 0.5F, 6.0F, 4.0F, 9.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(0.0F, -2.0F, 13.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition tail6 = tail5.addOrReplaceChild("tail6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition cube_r30 = tail6.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(232, 12).addBox(-7.0F, -0.75F, 0.25F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -6.0F, 17.0F, 0.1309F, 0.1745F, 0.0F));

		PartDefinition cube_r31 = tail6.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(185, 44).mirror().addBox(-1.5F, -3.75F, 1.25F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -6.0F, 17.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r32 = tail6.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(232, 12).mirror().addBox(0.0F, -0.75F, 0.5F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, -6.0F, 17.0F, 0.1309F, -0.1745F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 128);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.animate(entity.flyAnimationState, LordOfFuryAnimations.FLY, ageInTicks, 1.2f);
		this.animate(entity.idleInAirAnimationState, LordOfFuryAnimations.IDLEINAIR, ageInTicks);
		this.animate(entity.windAttackAnimationState, LordOfFuryAnimations.WINDCURRENTATTACK, ageInTicks);
		this.animate(entity.tailAttackAnimationState, LordOfFuryAnimations.TAILATTACK, ageInTicks);
		this.animate(entity.summonAbilityAnimationState, LordOfFuryAnimations.SUMMONOFFURIES, ageInTicks);
		int i = entity.getAttackAnimationRemainingTicks();
		if (i > 0) {
			this.bottom_tooth.xRot = 20f;
		} else {
			this.bottom_tooth.xRot = 0f;
		}
	}

	public ModelPart root() {
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		generic.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
