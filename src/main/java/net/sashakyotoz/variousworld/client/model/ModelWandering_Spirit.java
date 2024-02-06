package net.sashakyotoz.variousworld.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.sashakyotoz.variousworld.entity.WanderingSpiritSummonedOfSculksEntity;

public class ModelWandering_Spirit<T extends WanderingSpiritSummonedOfSculksEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_wandering_spirit"), "main");
    public final ModelPart head;
    public final ModelPart body;
    public final ModelPart rightArm;
    public final ModelPart leftArm;
    public final ModelPart rightLeg;
    public final ModelPart leftLeg;

    public ModelWandering_Spirit(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("rightArm");
        this.leftArm = root.getChild("leftArm");
        this.rightLeg = root.getChild("rightLeg");
        this.leftLeg = root.getChild("leftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(116, 0).addBox(-3.0F, -13.0F, -2.5F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-4.0F, -6.0F, -5.5F, 8.0F, 5.0F, 5.0F, new CubeDeformation(1.25F))
                .texOffs(0, 0).addBox(-4.0F, -6.0F, -5.5F, 8.0F, 5.0F, 5.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(101, 0).addBox(-12.0F, -7.0F, -0.5F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0873F, 0.0F, 0.2618F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(101, 0).mirror().addBox(6.0F, -7.0F, -0.5F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0873F, 0.0F, -0.2618F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));

        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 8).addBox(-1.0F, -10.0F, -5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.125F))
                .texOffs(36, 24).addBox(-4.5F, -8.0F, -5.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 24).mirror().addBox(3.5F, -8.0F, 3.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(34, 24).addBox(-5.5F, -8.0F, 3.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(36, 24).mirror().addBox(2.5F, -8.0F, -5.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 8).addBox(5.0F, -10.0F, 2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.26F))
                .texOffs(36, 8).addBox(-7.0F, -10.0F, 2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.26F))
                .texOffs(36, 8).mirror().addBox(5.0F, -10.0F, -5.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.26F)).mirror(false)
                .texOffs(36, 8).addBox(-7.0F, -10.0F, -5.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.26F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(34, 49).addBox(-6.0F, -9.0F, -2.5F, 12.0F, 10.0F, 5.0F, new CubeDeformation(1.25F))
                .texOffs(0, 49).addBox(-6.0F, -9.0F, -2.5F, 12.0F, 10.0F, 5.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(-7.0F, 8.0F, -1.0F));

        PartDefinition cube_r6 = rightArm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(64, 24).mirror().addBox(-11.25F, -2.0F, -1.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.0F, -4.5F, 2.0F, 0.0873F, 0.0F, 0.0873F));

        PartDefinition cube_r7 = rightArm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(100, 36).mirror().addBox(-4.0F, 4.75F, -2.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(1.1F)).mirror(false)
                .texOffs(100, 55).addBox(-4.0F, 11.0F, -2.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(1.25F))
                .texOffs(114, 45).addBox(-3.25F, -2.0F, -2.0F, 2.0F, 14.0F, 5.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(7.0F, 8.0F, -1.0F));

        PartDefinition cube_r8 = leftArm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(64, 24).addBox(7.25F, -2.0F, -1.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -4.5F, 2.0F, 0.0873F, 0.0F, -0.0873F));

        PartDefinition cube_r9 = leftArm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(100, 36).addBox(1.0F, 5.0F, -2.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(1.1F))
                .texOffs(100, 55).mirror().addBox(1.0F, 11.0F, -2.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(1.25F)).mirror(false)
                .texOffs(114, 45).mirror().addBox(1.25F, -2.0F, -2.0F, 2.0F, 14.0F, 5.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, 1.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offset(-3.0F, 15.0F, 0.0F));

        PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-1.0F, 1.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(3.0F, 15.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);
        this.head.zRot = Mth.cos(limbSwing * 0.5F) * 0.5F * limbSwingAmount;
        this.rightLeg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.leftLeg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.body.zRot = Mth.cos(limbSwing * 0.5F) * 0.5F * limbSwingAmount;
    }
    public void prepareMobModel(T p_102957_, float p_102958_, float p_102959_, float p_102960_) {
        int i = p_102957_.getAttackAnimationRemainingTicks();
        if (i > 0) {
            this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - p_102960_, 10.0F);
            this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i - p_102960_, 10.0F);
        } else {
            int j = p_102957_.getRangedAttackAnimationRemainingTicks();
            if (j > 0) {
                this.rightArm.xRot = -0.8F + 0.025F * Mth.triangleWave((float)j, 70.0F);
                this.rightArm.yRot = -0.8F + 0.025F * Mth.triangleWave((float)j, 35.0F);
                this.leftArm.xRot = -0.8F + 0.025F * Mth.triangleWave((float)j, 70.0F);
                this.leftArm.yRot = -0.8F + 0.025F * Mth.triangleWave((float)j, 35.0F);
            } else {
                this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
                this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(p_102958_, 13.0F)) * p_102959_;
            }
        }

    }
}
