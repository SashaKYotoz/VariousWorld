package net.sashakyotoz.variousworld.mixin.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Unique
    private static final EquipmentSlot[] VARIOUSWORLD$ARMOR_SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    @Inject(method = "hurtArmor", at = @At("HEAD"))
    private void wearArmorCrystals(DamageSource source, float damage, CallbackInfo ci) {
        if (damage <= 0.0F)
            return;
        Player player = (Player) (Object) this;
        for (EquipmentSlot slot : VARIOUSWORLD$ARMOR_SLOTS)
            OnActionsTrigger.wearCrystalOf(player.getItemBySlot(slot), player, GemsmithingHelper.Category.ARMOR);
    }
}
