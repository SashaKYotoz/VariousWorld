package net.sashakyotoz.variousworld.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    public abstract void renderModelLists(BakedModel model, ItemStack stack, int combinedLight, int combinedOverlay, PoseStack poseStack, VertexConsumer buffer);

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"))
    private void renderExtra(ItemStack stack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay, BakedModel p_model, CallbackInfo ci) {
        CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
        Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse("modid"));
        if (data != null) {
            poseStack.scale(1.001f, 1.001f, 1.001f);
            this.renderModelLists(minecraft.getItemRenderer().getModel(data.crystalStack(),
                    null, null, displayContext.ordinal()), stack, combinedLight, combinedOverlay, poseStack, bufferSource.getBuffer(RenderType.cutout()));
        }
    }
}