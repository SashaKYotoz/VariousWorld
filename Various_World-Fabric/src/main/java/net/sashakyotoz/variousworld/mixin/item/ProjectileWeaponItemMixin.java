package net.sashakyotoz.variousworld.mixin.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProjectileWeaponItem.class)
public abstract class ProjectileWeaponItemMixin {

    @Inject(method = "createProjectile", at = @At("RETURN"))
    private void applyGemToArrow(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit,
                                              CallbackInfoReturnable<Projectile> cir) {
        if (level.isClientSide())
            return;
        double bonus = GemsmithingHelper.arrowDamageBonus(weapon);
        if (bonus > 0.0 && cir.getReturnValue() instanceof AbstractArrow arrow)
            arrow.setBaseDamage(arrow.getBaseDamage() + bonus);
        OnActionsTrigger.wearCrystalOf(weapon, shooter, GemsmithingHelper.Category.RANGED);
    }
}
