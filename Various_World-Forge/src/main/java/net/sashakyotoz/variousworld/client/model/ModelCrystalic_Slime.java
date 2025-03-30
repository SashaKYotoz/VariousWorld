package net.sashakyotoz.variousworld.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.entity.CrystalicSlimeEntity;

public class ModelCrystalic_Slime<T extends CrystalicSlimeEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_crystalic_slime"), "main");
    public static final ModelLayerLocation OUTER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("various_world", "model_crystalic_slime"), "outer");
    private final ModelPart root;
    public ModelCrystalic_Slime(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createOuterBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 19.0F, 0.0F));
        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(23, 10).mirror().addBox(-1.5F, -2.5F, 0.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(6.0F, 0.5F, -2.0F, 1.5708F, -0.0873F, -2.9671F));
        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(23, 10).mirror().addBox(-1.5F, -2.5F, 0.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(6.0F, 0.5F, -2.5F, 0.0F, -0.0873F, -2.9671F));
        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(23, 5).addBox(-1.5F, -2.5F, 0.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-5.0F, 0.5F, 0.0F, 1.5708F, 0.0F, -3.1416F));
        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(23, 5).addBox(-1.5F, -2.5F, 0.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.5F, -0.5F, 0.0F, 0.0F, -3.1416F));
        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(23, 0).addBox(-1.5F, -2.5F, 0.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -3.5F, 0.0F, 1.5708F, 0.0F, -0.7854F));
        PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(23, 0).addBox(-1.5F, -2.5F, 0.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.5F, -0.5F, 0.0F, 0.0F, -0.7854F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    public static LayerDefinition createInnerBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition
                .addOrReplaceChild(
                        "head", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-3.3F, -2.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                                .mirror().addBox(1.3F, -2.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(16, 0).addBox(0.0F, 1.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                        PartPose.offset(0.0F, 20.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public ModelPart root() {
        return this.root;
    }
}
