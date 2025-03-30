package net.sashakyotoz.variousworld.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
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
import net.sashakyotoz.variousworld.entity.DromophantEntity;
import net.sashakyotoz.variousworld.entity.animations.DromophantAnimations;

public class ModelDromophant<T extends DromophantEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_dromophant"), "main");
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart forwardRightLeg;
	private final ModelPart forwardLeftLeg;
	private final ModelPart backRightLeg;
	private final ModelPart backLeftLeg;
	private final ModelPart tail;

	public ModelDromophant(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.forwardRightLeg = root.getChild("forwardRightLeg");
		this.forwardLeftLeg = root.getChild("forwardLeftLeg");
		this.backRightLeg = root.getChild("backRightLeg");
		this.backLeftLeg = root.getChild("backLeftLeg");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(8, 0).addBox(-5.0F, -6.0F, -12.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(49, 70).addBox(-5.0F, -6.0F, -12.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.05F))
				.texOffs(0, 60).mirror().addBox(5.0F, -9.0F, -11.0F, 2.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 60).addBox(-7.0F, -9.0F, -11.0F, 2.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -3.0F, -15.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).mirror().addBox(4.0F, -3.0F, -15.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 4.0F, -12.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(25, 14).addBox(4.5F, 2.0F, -5.75F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(11, 14).addBox(-6.5F, 2.0F, -5.75F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition chain = head.addOrReplaceChild("chain", CubeListBuilder.create().texOffs(8, 0).addBox(-5.0F, 0.0F, -11.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(-0.01F))
				.texOffs(21, 92).addBox(-5.0F, -2.0F, -11.0F, 10.0F, 2.0F, 9.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 4.0F, -1.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(30, 0).addBox(-10.0F, -21.0F, -14.0F, 20.0F, 22.0F, 29.0F, new CubeDeformation(0.0F))
				.texOffs(30, 51).addBox(-10.0F, -21.0F, -14.0F, 20.0F, 12.0F, 29.0F, new CubeDeformation(0.25F))
				.texOffs(74, 100).mirror().addBox(4.0F, -26.0F, -11.0F, 4.0F, 5.0F, 23.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(74, 100).addBox(-8.0F, -26.0F, -11.0F, 4.0F, 5.0F, 23.0F, new CubeDeformation(0.0F))
				.texOffs(12, 112).addBox(-8.0F, -22.001F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition forwardRightLeg = partdefinition.addOrReplaceChild("forwardRightLeg", CubeListBuilder.create().texOffs(0, 98).addBox(-3.0F, -1.0F, -3.0F, 7.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 2.0F, -8.0F));

		PartDefinition forwardLeftLeg = partdefinition.addOrReplaceChild("forwardLeftLeg", CubeListBuilder.create().texOffs(0, 98).mirror().addBox(-4.0F, -1.0F, -3.0F, 7.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(10.0F, 2.0F, -8.0F));

		PartDefinition backRightLeg = partdefinition.addOrReplaceChild("backRightLeg", CubeListBuilder.create().texOffs(0, 80).addBox(-2.0F, 0.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 14.0F, 8.0F));

		PartDefinition backLeftLeg = partdefinition.addOrReplaceChild("backLeftLeg", CubeListBuilder.create().texOffs(0, 80).addBox(-5.0F, 0.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 14.0F, 8.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 43).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(0.0F, -8.0F, 0.0F, 0.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 15.0F));

		PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, -3.0F, 0.0F, 0.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 11.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.animate(entity.sittingAnimationState, DromophantAnimations.SITTING,ageInTicks,0.5f);
		this.animate(entity.standUpAnimationState, DromophantAnimations.STANDUP,ageInTicks,0.5f);
		this.animate(entity.eatingAnimationState, DromophantAnimations.EATING,ageInTicks,0.5f);
		this.animateWalk(DromophantAnimations.WALKING,limbSwing,limbSwingAmount,2.0F, 2.5F);
		this.animate(entity.attackAnimationState, DromophantAnimations.ATTACK,ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		forwardRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		forwardLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}