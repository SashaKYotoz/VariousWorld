
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import net.sashakyotoz.variousworld.init.VariousWorldModSounds;
import net.sashakyotoz.variousworld.procedures.SculkScytheRangedItemProjectileHitsLivingEntityProcedure;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class SculkScytheEntity extends AbstractArrow implements ItemSupplier {
	public SculkScytheEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(VariousWorldModEntities.SCULK_SCYTHE_RANGED_ITEM.get(), world);
	}

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
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return new ItemStack(Blocks.AIR);
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
		SculkScytheRangedItemProjectileHitsLivingEntityProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), entityHitResult.getEntity());
	}

	@Override
	public void tick() {
		super.tick();
		execute(this.level(), this.getX(), this.getY(), this.getZ());
		if (this.inGround)
			this.discard();
	}
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.25) {
			world.addParticle(ParticleTypes.SONIC_BOOM, x, y, z, 0, 0, 0);
		}
	}

	public static SculkScytheEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		SculkScytheEntity sculkScythe = new SculkScytheEntity(VariousWorldModEntities.SCULK_SCYTHE_RANGED_ITEM.get(), entity, world);
		sculkScythe.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		sculkScythe.setSilent(true);
		sculkScythe.setCritArrow(false);
		sculkScythe.setBaseDamage(damage);
		sculkScythe.setKnockback(knockback);
		world.addFreshEntity(sculkScythe);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldModSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return sculkScythe;
	}

	public static SculkScytheEntity shoot(LivingEntity entity, LivingEntity target) {
		SculkScytheEntity entityarrow = new SculkScytheEntity(VariousWorldModEntities.SCULK_SCYTHE_RANGED_ITEM.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 2f, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(5);
		entityarrow.setKnockback(3);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldModSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
