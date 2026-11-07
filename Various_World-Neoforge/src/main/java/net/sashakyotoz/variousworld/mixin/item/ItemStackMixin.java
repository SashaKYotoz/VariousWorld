package net.sashakyotoz.variousworld.mixin.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "hurtEnemy", at = @At("RETURN"))
    private void wearOnHit(LivingEntity enemy, Player attacker, CallbackInfoReturnable<Boolean> cir) {
        OnActionsTrigger.wearCrystalOf((ItemStack) (Object) this, attacker, GemsmithingHelper.Category.TOOL);
    }

    @Inject(method = "mineBlock", at = @At("TAIL"))
    private void wearOnMine(Level level, BlockState state, BlockPos pos, Player player, CallbackInfo ci) {
        OnActionsTrigger.wearCrystalOf((ItemStack) (Object) this, player, GemsmithingHelper.Category.TOOL);
    }
}