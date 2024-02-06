package net.sashakyotoz.variousworld.client.model;

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

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ModelDark_Fury<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_dark_fury"), "main");
	public final ModelPart head;
	public final ModelPart wing0;
	public final ModelPart wing1;
	public final ModelPart body;
	public final ModelPart tail;
	public final ModelPart tail1;

	public ModelDark_Fury(ModelPart root) {
		this.head = root.getChild("head");
		this.wing0 = root.getChild("wing0");
		this.wing1 = root.getChild("wing1");
		this.body = root.getChild("body");
		this.tail = root.getChild("tail");
		this.tail1 = root.getChild("tail1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-0.5F, 16.5F, -7.0F));
		PartDefinition body_r1 = head.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(66, 15).addBox(-5.0F, 4.5F, -3.0F, 9.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.5F, 2.0F, -1.309F, 0.0F, 0.0F));
		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(1, 54).addBox(-1.5F, -0.5F, -2.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 48).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.1F)),
				PartPose.offsetAndRotation(0.0F, 1.0F, -4.0F, 0.3491F, 0.0F, 0.0F));
		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(69, 0).addBox(-0.5F, -25.0F, -11.0F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 23.0F, 7.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(94, 0).addBox(-11.0F, -2.0F, -11.0F, 11.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition wing0 = partdefinition.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(23, 12).addBox(0.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 14.0F, -8.0F));
		PartDefinition wingtip0 = wing0.addOrReplaceChild("wingtip0", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -16.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
		PartDefinition wingtip0_r1 = wingtip0.addOrReplaceChild("wingtip0_r1",
				CubeListBuilder.create().texOffs(97, 34).addBox(0.2F, -1.5F, -1.25F, 13.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(97, 34).addBox(0.2F, 1.5F, -0.5F, 13.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 15.5F, 4.5F, 1.1345F, -0.2618F, 0.0F));
		PartDefinition wingtip0_r2 = wingtip0.addOrReplaceChild("wingtip0_r2", CubeListBuilder.create().texOffs(97, 32).addBox(0.2F, 1.5F, -0.5F, 13.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 15.5F, 4.5F, 1.5708F, -0.2618F, 0.0F));
		PartDefinition wingtip0_r3 = wingtip0.addOrReplaceChild("wingtip0_r3", CubeListBuilder.create().texOffs(16, 24).addBox(0.1837F, 0.2569F, -4.7191F, 13.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 15.5F, 4.5F, 0.0F, -0.2618F, 0.0F));
		PartDefinition wing1 = partdefinition.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(23, 12).mirror().addBox(-6.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 14.0F, -8.0F));
		PartDefinition wingtip1 = wing1.addOrReplaceChild("wingtip1", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -16.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
		PartDefinition wingtip1_r1 = wingtip1.addOrReplaceChild("wingtip1_r1", CubeListBuilder.create().texOffs(97, 34).mirror().addBox(-14.2F, -1.5F, -1.5F, 13.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(97, 34).mirror()
				.addBox(-14.2F, 1.5F, -0.5F, 13.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 15.5F, 4.5F, 1.1345F, 0.3054F, 0.0F));
		PartDefinition wingtip1_r2 = wingtip1.addOrReplaceChild("wingtip1_r2", CubeListBuilder.create().texOffs(97, 32).mirror().addBox(-14.2F, 1.5F, -0.5F, 13.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 15.5F, 4.5F, 1.5708F, 0.3054F, 0.0F));
		PartDefinition wingtip1_r3 = wingtip1.addOrReplaceChild("wingtip1_r3", CubeListBuilder.create().texOffs(16, 24).mirror().addBox(-14.1498F, 0.2569F, -4.8355F, 13.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 15.5F, 4.5F, 0.0F, 0.3054F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, 0.0F, -8.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));
		PartDefinition decor = body.addOrReplaceChild("decor",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -2.0F, -6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-0.5F, -2.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-0.5F, -2.0F, 6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)).texOffs(0, 0)
						.addBox(-0.5F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.5F, 1.5F, -7.0F));
		PartDefinition body_r2 = decor.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(66, 15).addBox(-5.0F, -6.5F, -1.0F, 9.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.5F, 2.0F, -1.309F, 0.0F, 0.0F));
		PartDefinition tail = partdefinition.addOrReplaceChild("tail",
				CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-2.5F, 0.0F, 1.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(54, 12).addBox(-4.5F, 1.5F, 1.0F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)).texOffs(0, 0).addBox(-0.5F, -0.5F, 7.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)),
				PartPose.offset(-0.5F, 14.0F, 0.0F));
		PartDefinition tail1 = partdefinition
				.addOrReplaceChild(
						"tail1", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, 0.5F, 10.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(55, 24).mirror().addBox(-6.5F, 1.5F, 11.0F, 13.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
								.mirror(false).texOffs(0, 32).addBox(-1.0F, 1.5F, 17.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.001F)).texOffs(0, 0).addBox(-0.5F, 0.0F, 11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)),
						PartPose.offset(-0.5F, 14.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		wing0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		wing1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.tail.yRot = -f * 0.25F * Mth.sin(0.5F * ageInTicks);
		this.tail.xRot = -f * 0.25F * Mth.sin(0.5F * ageInTicks);
		this.tail1.yRot = -f * 0.25F * Mth.sin(0.5F * ageInTicks);
		this.tail1.xRot = -f * 0.25F * Mth.sin(0.5F * ageInTicks);
		this.tail1.zRot = -f * 0.25F * Mth.sin(0.5F * ageInTicks);
		this.wing1.zRot = Mth.cos(limbSwing * 0.85F) * limbSwingAmount;
		this.wing0.zRot = Mth.cos(limbSwing * 0.85F + (float) Math.PI) * limbSwingAmount;
	}
}
