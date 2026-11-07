package net.sashakyotoz.variousworld.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.entities.boat.VWBoatAccess;
import net.sashakyotoz.variousworld.common.entities.boat.VWBoatType;
import org.joml.Quaternionf;

import java.util.EnumMap;
import java.util.Map;

public class VWBoatRenderer extends EntityRenderer<Boat> {
    private final Map<VWBoatType, Pair<ResourceLocation, ListModel<Boat>>> resources = new EnumMap<>(VWBoatType.class);

    public VWBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context);
        this.shadowRadius = 0.8F;
        for (VWBoatType type : VWBoatType.values()) {
            ModelPart root = context.bakeLayer(layer(type, hasChest));
            ListModel<Boat> model = hasChest ? new ChestBoatModel(root) : new BoatModel(root);
            this.resources.put(type, Pair.of(texture(type, hasChest), model));
        }
    }

    public static ModelLayerLocation layer(VWBoatType type, boolean hasChest) {
        return new ModelLayerLocation(VariousWorld.createVWLocation((hasChest ? "chest_boat/" : "boat/") + type.getSerializedName()), "main");
    }

    public static ResourceLocation texture(VWBoatType type, boolean hasChest) {
        return VariousWorld.createVWLocation("textures/entity/" + (hasChest ? "chest_boat/" : "boat/") + type.getSerializedName() + ".png");
    }

    private Pair<ResourceLocation, ListModel<Boat>> resourcesFor(Boat boat) {
        VWBoatType type = boat instanceof VWBoatAccess access ? access.getVWType() : VWBoatType.CRYSTALIC_OAK;
        return this.resources.get(type);
    }

    @Override
    public void render(Boat boat, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        float hurtTime = boat.getHurtTime() - partialTicks;
        float damage = Math.max(boat.getDamage() - partialTicks, 0.0F);
        if (hurtTime > 0.0F)
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(hurtTime) * hurtTime * damage / 10.0F * boat.getHurtDir()));
        float bubbleAngle = boat.getBubbleAngle(partialTicks);
        if (!Mth.equal(bubbleAngle, 0.0F))
            poseStack.mulPose(new Quaternionf().setAngleAxis(bubbleAngle * (float) (Math.PI / 180.0), 1.0F, 0.0F, 1.0F));
        Pair<ResourceLocation, ListModel<Boat>> pair = this.resourcesFor(boat);
        ListModel<Boat> model = pair.getSecond();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        model.setupAnim(boat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer consumer = buffer.getBuffer(model.renderType(pair.getFirst()));
        model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
        if (!boat.isUnderWater() && model instanceof WaterPatchModel waterPatchModel)
            waterPatchModel.waterPatch().render(poseStack, buffer.getBuffer(RenderType.waterMask()), packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(boat, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Boat boat) {
        return this.resourcesFor(boat).getFirst();
    }
}
