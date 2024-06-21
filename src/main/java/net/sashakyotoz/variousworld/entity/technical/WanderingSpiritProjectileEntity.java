
package net.sashakyotoz.variousworld.entity.technical;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.init.VariousWorldEntities;
import net.sashakyotoz.variousworld.init.VariousWorldParticleTypes;
import net.sashakyotoz.variousworld.init.VariousWorldSounds;

public class WanderingSpiritProjectileEntity extends AbstractArrow {

    public WanderingSpiritProjectileEntity(EntityType<? extends WanderingSpiritProjectileEntity> type, Level world) {
        super(type, world);
    }

    public WanderingSpiritProjectileEntity(EntityType<? extends WanderingSpiritProjectileEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel level && this.tickCount % 5 == 0) {
            this.level().addParticle(VariousWorldParticleTypes.WANDERING_SPIRIT_PROJECTILE_PARTICLE.get(), this.getX(),this.getY(), this.getZ(), 0, 0, 0);
            level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, this.getX(),this.getY(), this.getZ(), 9, 3, 3, 3, 1);
        }
        if (this.inGround)
            this.discard();
    }

    public static WanderingSpiritProjectileEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        WanderingSpiritProjectileEntity projectile = new WanderingSpiritProjectileEntity(VariousWorldEntities.WANDERING_SPIRIT_PROJECTILE.get(), entity, world);
        projectile.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        projectile.setSilent(true);
        projectile.setCritArrow(false);
        projectile.setBaseDamage(damage);
        projectile.setKnockback(knockback);
        world.addFreshEntity(projectile);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return projectile;
    }

    public static WanderingSpiritProjectileEntity shoot(LivingEntity entity, LivingEntity target) {
        WanderingSpiritProjectileEntity projectile = new WanderingSpiritProjectileEntity(VariousWorldEntities.WANDERING_SPIRIT_PROJECTILE.get(), entity, entity.level());
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 0.75;
        double dz = target.getZ() - entity.getZ();
        projectile.shoot(dx, dy - projectile.getY() + Math.hypot(dx, dz) * 0.2F, dz, 4.5f, 12.0F);
        projectile.setSilent(true);
        projectile.setBaseDamage(projectile.random.nextIntBetweenInclusive(4,10)+1);
        projectile.setKnockback(2);
        projectile.setCritArrow(false);
        entity.level().addFreshEntity(projectile);
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
        return projectile;
    }
}
