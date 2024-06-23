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

public class ModelLordFuryBottom<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "modellord_fury_bottom"), "main");
	public final ModelPart Body;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;

	public ModelLordFuryBottom(ModelPart root) {
		this.Body = root.getChild("Body");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).texOffs(48, 16).addBox(-2.1F, 1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.65F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false).texOffs(48, 16).mirror()
				.addBox(-1.9F, 1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.65F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
		RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.LeftLeg.xRot = Mth.cos(limbSwing) * -1.0F * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing) * 1.0F * limbSwingAmount;
	}
}
