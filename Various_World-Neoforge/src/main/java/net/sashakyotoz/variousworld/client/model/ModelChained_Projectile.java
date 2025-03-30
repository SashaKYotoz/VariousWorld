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

// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelChained_Projectile<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_chained_projectile"), "main");
	public final ModelPart top;
	public final ModelPart chain;

	public ModelChained_Projectile(ModelPart root) {
		this.top = root.getChild("top");
		this.chain = root.getChild("chain");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition top = partdefinition.addOrReplaceChild("top",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -12.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(12, 0).addBox(-2.0F, -11.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(12, 0)
						.addBox(1.0F, -11.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(12, 0).addBox(-1.0F, -11.5F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 0)
						.addBox(-1.0F, -11.5F, -2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 8).addBox(-1.0F, -12.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition chain = partdefinition.addOrReplaceChild("chain",
				CubeListBuilder.create().texOffs(26, 0).addBox(-1.5F, -9.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(26, -3).addBox(0.0F, -9.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(26, 0)
						.addBox(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(26, -3).addBox(0.0F, -5.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		chain.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.top.xRot = ageInTicks / 20.f;
	}
}
