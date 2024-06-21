
package net.sashakyotoz.variousworld.entity.technical;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;

import net.sashakyotoz.variousworld.init.VariousWorldEntities;

public class LordOfFuriesCrossbowEntity extends AbstractArrow {

	public LordOfFuriesCrossbowEntity(EntityType<? extends LordOfFuriesCrossbowEntity> type, Level world) {
		super(type, world);
	}

	public LordOfFuriesCrossbowEntity(EntityType<? extends LordOfFuriesCrossbowEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	protected ItemStack getPickupItem() {
		return Items.SPECTRAL_ARROW.getDefaultInstance();
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	@Override
	public void tick() {
		super.tick();
		onTickParticle(this.level(), this.getX(), this.getY(), this.getZ());
	}
	private void onTickParticle(Level level, double x, double y, double z) {
		if (this.random.nextFloat() < 0.25) {
			if (level instanceof ServerLevel serverLevel)
				serverLevel.sendParticles(ParticleTypes.FALLING_OBSIDIAN_TEAR, x, y, z, 9, 3, 3, 3, 1);
		}
	}

	public static LordOfFuriesCrossbowEntity shoot(Level level, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		LordOfFuriesCrossbowEntity arrow = new LordOfFuriesCrossbowEntity(VariousWorldEntities.LORD_OF_FURIES_CROSSBOW.get(), entity, level);
		arrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		arrow.setSilent(true);
		arrow.setCritArrow(false);
		arrow.setBaseDamage(damage);
		arrow.setKnockback(knockback);
		arrow.setSecondsOnFire(100);
//		level.addFreshEntity(arrow);
		level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return arrow;
	}

//	public static LordOfFuriesCrossbowEntity shoot(LivingEntity entity, LivingEntity target) {
//		LordOfFuriesCrossbowEntity arrow = new LordOfFuriesCrossbowEntity(VariousWorldModEntities.LORD_OF_FURIES_CROSSBOW.get(), entity, entity.level());
//		double dx = target.getX() - entity.getX();
//		double dy = target.getY() + target.getEyeHeight() - 1.1;
//		double dz = target.getZ() - entity.getZ();
//		arrow.shoot(dx, dy - arrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1.25f * 2, 12.0F);
//		arrow.setSilent(true);
//		arrow.setBaseDamage(8);
//		arrow.setKnockback(5);
//		arrow.setCritArrow(false);
//		arrow.setSecondsOnFire(100);
//		entity.level().addFreshEntity(arrow);
//		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
//		return arrow;
//	}
}
