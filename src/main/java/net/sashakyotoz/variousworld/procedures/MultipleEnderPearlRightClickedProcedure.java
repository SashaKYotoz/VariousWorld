package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;

import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import net.sashakyotoz.variousworld.entity.MultipleEnderPearlEntity;

public class MultipleEnderPearlRightClickedProcedure {
    public static void execute(Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        Level projectileLevel = entity.level();
        if (!projectileLevel.isClientSide()) {
            Projectile projectile = new Object() {
                public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                    AbstractArrow entityToSpawn = new MultipleEnderPearlEntity(VariousWorldModEntities.MULTIPLE_ENDER_PEARL.get(), level);
                    entityToSpawn.setOwner(shooter);
                    entityToSpawn.setBaseDamage(damage);
                    entityToSpawn.setKnockback(knockback);
                    entityToSpawn.setSilent(true);
                    return entityToSpawn;
                }
            }.getArrow(projectileLevel, entity, 0, 0);
            projectile.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
            projectile.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, 2, 0);
            projectileLevel.addFreshEntity(projectile);
        }
        if (itemstack.hurt(1, RandomSource.create(), null)) {
            itemstack.shrink(1);
            itemstack.setDamageValue(0);
        }
    }
}
