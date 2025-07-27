package net.sashakyotoz.variousworld.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.monster.Zombie;
import net.sashakyotoz.variousworld.VariousWorld;

public class WanderingZombieModel<T extends Zombie> extends ZombieModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(VariousWorld.createVWLocation("wandering_zombie_model"), "main");
    public final ModelPart root;

    public WanderingZombieModel(ModelPart root) {
        super(root);
        this.root = root;

    }

    public static LayerDefinition createBodyLayer() {
        CubeDeformation cubeDeformation = CubeDeformation.NONE;
        float yOffset = 0.0F;
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation.extend(0.5F)), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F));
        body.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(29, 32).addBox(0.0F, -4.0F, 2.45F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(29, 32).addBox(0.0F, -4.0F, -2.55F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, .0F, 0.0F, 0.0F, 0.0F, 1.5708F));
        body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-4.0F, 6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).mirror(false),
                PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));
         partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation).texOffs(48, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-5.0F, 2.0F + yOffset, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation).texOffs(48, 32).mirror()
                .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(5.0F, 2.0F + yOffset, 0.0F));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(-1.9F, 12.0F + yOffset, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(1.9F, 12.0F + yOffset, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}