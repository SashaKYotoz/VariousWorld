
package net.sashakyotoz.variousworld.entity.technical;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.sashakyotoz.variousworld.init.VariousWorldEntities;
import net.sashakyotoz.variousworld.init.VariousWorldParticleTypes;
import net.sashakyotoz.variousworld.init.VariousWorldSounds;

public class SculkScytheEntity extends AbstractArrow {

	public SculkScytheEntity(EntityType<? extends SculkScytheEntity> type, Level world) {
		super(type, world);
	}

	public SculkScytheEntity(EntityType<? extends SculkScytheEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
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
	public void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		for (int i = 0; i < 360; i++) {
			if (i % 20 == 0) {
				this.level().addParticle(VariousWorldParticleTypes.WANDERING_SPIRIT_PROJECTILE_PARTICLE.get(),
						this.getX(), this.getY() + 0.5, this.getZ(),
						Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
			}
		}
		if (entityHitResult.getEntity() instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide()){
			if(livingEntity.getRandom().nextBoolean())
				livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1, false, false));
			else
				livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 1, false, false));
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.inGround)
			this.discard();
		else{
			if (this.tickCount % 20 == 0) {
				this.level().addParticle(ParticleTypes.SONIC_BOOM, this.getX(),this.getY(),this.getZ(), 0, 0, 0);
			}
		}
	}

	public static SculkScytheEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		SculkScytheEntity sculkScythe = new SculkScytheEntity(VariousWorldEntities.SCULK_SCYTHE_PROJECTILE.get(), entity, world);
		sculkScythe.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		sculkScythe.setSilent(true);
		sculkScythe.setCritArrow(false);
		sculkScythe.setBaseDamage(damage);
		sculkScythe.setKnockback(knockback);
		world.addFreshEntity(sculkScythe);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return sculkScythe;
	}

	public static SculkScytheEntity shoot(LivingEntity entity, LivingEntity target) {
		SculkScytheEntity scythe = new SculkScytheEntity(VariousWorldEntities.SCULK_SCYTHE_PROJECTILE.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		scythe.shoot(dx, dy - scythe.getY() + Math.hypot(dx, dz) * 0.2F, dz, 2f, 12.0F);
		scythe.setSilent(true);
		scythe.setBaseDamage(5);
		scythe.setKnockback(3);
		scythe.setCritArrow(false);
		entity.level().addFreshEntity(scythe);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return scythe;
	}
}
