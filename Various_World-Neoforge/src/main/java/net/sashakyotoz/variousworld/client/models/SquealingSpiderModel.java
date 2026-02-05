package net.sashakyotoz.variousworld.client.models;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.models.states.SquealingSpiderRenderState;
import net.sashakyotoz.variousworld.common.entities.anim.SquealingSpiderAnimations;

public class SquealingSpiderModel extends EntityModel<SquealingSpiderRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(VariousWorld.createVWLocation("squealing_spider_model"), "main");
    private final ModelPart head;
    private final ModelPart teeth;
    private final ModelPart head_crystal;
    private final ModelPart small_body;
    private final ModelPart crystal;
    private final ModelPart belly;
    private final ModelPart rear_leg;
    private final ModelPart rear_leg1;
    private final ModelPart middle_leg;
    private final ModelPart middle_leg1;
    private final ModelPart front_leg;
    private final ModelPart front_leg1;

    private final KeyframeAnimation slide;
    private final KeyframeAnimation slideBack;
    private final KeyframeAnimation longJump;
    private final KeyframeAnimation shoot;
    private final KeyframeAnimation inhale;
    private final KeyframeAnimation walk;

    public SquealingSpiderModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.teeth = this.head.getChild("teeth");
        this.head_crystal = this.head.getChild("head_crystal");
        this.small_body = root.getChild("small_body");
        this.crystal = this.small_body.getChild("crystal");
        this.belly = root.getChild("belly");
        this.rear_leg = root.getChild("rear_leg");
        this.rear_leg1 = root.getChild("rear_leg1");
        this.middle_leg = root.getChild("middle_leg");
        this.middle_leg1 = root.getChild("middle_leg1");
        this.front_leg = root.getChild("front_leg");
        this.front_leg1 = root.getChild("front_leg1");

        this.slide = SquealingSpiderAnimations.JUMP.bake(root);
        this.slideBack = SquealingSpiderAnimations.JUMP.bake(root);
        this.longJump = SquealingSpiderAnimations.JUMP.bake(root);
        this.shoot = SquealingSpiderAnimations.ATTACK.bake(root);
        this.inhale = SquealingSpiderAnimations.ATTACK.bake(root);
        this.walk = SquealingSpiderAnimations.WALK.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -3.0F));

        PartDefinition teeth = head.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, 0.24F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -8.25F));

        PartDefinition head_crystal = head.addOrReplaceChild("head_crystal", CubeListBuilder.create().texOffs(49, 5).addBox(-2.5F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, -7.0F, 0.3491F, 0.0F, 0.3927F));

        PartDefinition cube_r1 = head_crystal.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(49, 5).addBox(-2.5F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition small_body = partdefinition.addOrReplaceChild("small_body", CubeListBuilder.create().texOffs(32, 20).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

        PartDefinition crystal = small_body.addOrReplaceChild("crystal", CubeListBuilder.create().texOffs(45, 1).mirror().addBox(-4.5F, -9.0F, 0.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r2 = crystal.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(45, 1).mirror().addBox(-5.0F, -9.0F, 0.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition belly = partdefinition.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(12, 51).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 0.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0).addBox(-3.0F, -13.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 9.0F));

        PartDefinition cube_r3 = belly.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 0).addBox(-5.0F, -10.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -3.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition rear_leg = partdefinition.addOrReplaceChild("rear_leg", CubeListBuilder.create().texOffs(0, 39).addBox(-9.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 50).addBox(-11.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 20.0F, 2.0F, 0.2618F, 0.7854F, 0.7854F));

        PartDefinition rear_leg1 = partdefinition.addOrReplaceChild("rear_leg1", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 50).addBox(9.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 20.0F, 2.0F, 0.2618F, -0.7854F, -0.7854F));

        PartDefinition middle_leg = partdefinition.addOrReplaceChild("middle_leg", CubeListBuilder.create().texOffs(0, 35).addBox(-9.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-11.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(19, 52).addBox(-13.0F, -1.0F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 20.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition middle_leg1 = partdefinition.addOrReplaceChild("middle_leg1", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(9.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(19, 52).mirror().addBox(11.0F, -1.0F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 20.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition front_leg = partdefinition.addOrReplaceChild("front_leg", CubeListBuilder.create().texOffs(0, 39).addBox(-9.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 50).addBox(-11.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 20.0F, -1.0F, -0.2618F, -0.7854F, 0.7854F));

        PartDefinition front_leg1 = partdefinition.addOrReplaceChild("front_leg1", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 50).addBox(9.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 20.0F, -1.0F, -0.2618F, 0.7854F, -0.7854F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SquealingSpiderRenderState renderState) {
        super.setupAnim(renderState);
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.walk.applyWalk(renderState.walkAnimationPos, renderState.walkAnimationSpeed, 4F, 4F);
        this.inhale.apply(renderState.inhale, renderState.ageInTicks, -1);
        this.shoot.apply(renderState.shoot, renderState.ageInTicks, 0.5f);
        this.longJump.apply(renderState.longJump, renderState.ageInTicks, 0.5f);
        this.slideBack.apply(renderState.slideBack, renderState.ageInTicks, -1);
        this.slide.apply(renderState.slide, renderState.ageInTicks, -0.5f);
    }
}