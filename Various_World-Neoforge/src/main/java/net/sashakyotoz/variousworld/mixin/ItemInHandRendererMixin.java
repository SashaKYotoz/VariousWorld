package net.sashakyotoz.variousworld.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {
    @Shadow
    @Final
    private ItemModelResolver itemModelResolver;

    @Inject(method = "renderItem", at = @At("TAIL"))
    public void renderItem(LivingEntity entity, ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, CallbackInfo ci) {
        if (!stack.isEmpty() && stack.has(VWMiscRegistries.CRYSTAL_DATA.get())) {
            ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
            poseStack.scale(1.025F, 1.025F, 1.025F);
            this.itemModelResolver.updateForTopItem(itemstackrenderstate, stack.get(VWMiscRegistries.CRYSTAL_DATA.get()).crystalStack(), displayContext, entity.level(), entity, entity.getId() + displayContext.ordinal());
            itemstackrenderstate.submit(poseStack, nodeCollector, packedLight, OverlayTexture.NO_OVERLAY, 0);
        }
    }
}