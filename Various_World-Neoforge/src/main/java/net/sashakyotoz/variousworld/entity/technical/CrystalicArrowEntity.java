
package net.sashakyotoz.variousworld.entity.technical;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.sashakyotoz.variousworld.init.VWEntities;

public class CrystalicArrowEntity extends AbstractArrow {

    public CrystalicArrowEntity(EntityType<? extends CrystalicArrowEntity> type, Level world) {
        super(type, world);
    }

    public CrystalicArrowEntity(EntityType<? extends CrystalicArrowEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }
    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity effect && !effect.level().isClientSide())
            effect.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 1));
        if (this.level() instanceof ServerLevel level)
            level.sendParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 10, 2, 2, 2, 1);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount % 5 == 0){
            this.level().addParticle(ParticleTypes.GLOW, this.getX(), this.getY(), this.getZ(), 0, 1, 0);
            if (this.level() instanceof ServerLevel level && !this.onGround())
                level.sendParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(), this.getY(), this.getZ(), 15, 3, 3, 3, 1);
        }
    }

    public static CrystalicArrowEntity shoot(Level level,ItemStack stack, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        CrystalicArrowEntity bowEntity = new CrystalicArrowEntity(VWEntities.CRYSTALIC_BOW.get(), entity, level);
        bowEntity.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 1.5f, 0);
        bowEntity.setSilent(true);
        bowEntity.setCritArrow(false);
        bowEntity.setBaseDamage(damage);
        bowEntity.setKnockback(knockback);
        if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0)
            bowEntity.setSecondsOnFire(100);
        int j = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
        if (j > 0)
            bowEntity.setBaseDamage(bowEntity.getBaseDamage() + (double) j * 0.5D + 0.5D);
        int k = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
        if (k > 0)
            bowEntity.setKnockback(k);
        level.addFreshEntity(bowEntity);
        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return bowEntity;
    }

    public static CrystalicArrowEntity shoot(LivingEntity entity, LivingEntity target) {
        CrystalicArrowEntity bowEntity = new CrystalicArrowEntity(VWEntities.CRYSTALIC_BOW.get(), entity, entity.level());
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        bowEntity.shoot(dx, dy - bowEntity.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
        bowEntity.setSilent(true);
        bowEntity.setBaseDamage(4);
        bowEntity.setKnockback(2);
        bowEntity.setCritArrow(false);
        if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, entity.getMainHandItem()) > 0)
            bowEntity.setSecondsOnFire(100);
        int j = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, entity.getMainHandItem());
        if (j > 0)
            bowEntity.setBaseDamage(bowEntity.getBaseDamage() + (double) j * 0.5D + 0.5D);
        int k = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, entity.getMainHandItem());
        if (k > 0)
            bowEntity.setKnockback(k);
        entity.level().addFreshEntity(bowEntity);
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
        return bowEntity;
    }
}
