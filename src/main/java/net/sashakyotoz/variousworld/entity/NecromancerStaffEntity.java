
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import net.sashakyotoz.variousworld.init.VariousWorldModSounds;
import net.sashakyotoz.variousworld.procedures.NecromancerStaffProjectileHitsLivingEntityProcedure;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class NecromancerStaffEntity extends AbstractArrow implements ItemSupplier {
	private static boolean isMagmaColor = false;
	public NecromancerStaffEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(VariousWorldModEntities.NECROMANCER_STAFF.get(), world);
	}

	public NecromancerStaffEntity(EntityType<? extends NecromancerStaffEntity> type, Level world) {
		super(type, world);
	}

	public NecromancerStaffEntity(EntityType<? extends NecromancerStaffEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return ItemStack.EMPTY;
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
	public boolean isIsMagmaColor(){
		return isMagmaColor;
	}

	@Override
	public void playerTouch(Player entity) {
		super.playerTouch(entity);
		NecromancerStaffProjectileHitsLivingEntityProcedure.execute(entity, this.getOwner());
	}

	@Override
	public void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		NecromancerStaffProjectileHitsLivingEntityProcedure.execute(entityHitResult.getEntity(), this.getOwner());
	}

	@Override
	public void tick() {
		super.tick();
		if (this.inGround)
			this.discard();
		else if (Math.random() > 0.5){
			if(isIsMagmaColor())
				this.level().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,this.getX(),this.getY(),this.getZ(),RandomSource.create().nextInt(0,8)-4,-0.1,RandomSource.create().nextInt(0,8)-4);
			else
				this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME,this.getX(),this.getY(),this.getZ(),RandomSource.create().nextInt(0,4)-2,-0.1,RandomSource.create().nextInt(0,4)-2);
		}
	}

	public static NecromancerStaffEntity shoot(Level world, LivingEntity entity,ItemStack stack, RandomSource random, float power, double damage, int knockback) {
		NecromancerStaffEntity entityarrow = new NecromancerStaffEntity(VariousWorldModEntities.NECROMANCER_STAFF.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		isMagmaColor = stack.getOrCreateTag().getDouble("CustomModelData") == 1;
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldModSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static NecromancerStaffEntity shoot(LivingEntity entity, LivingEntity target) {
		NecromancerStaffEntity entityarrow = new NecromancerStaffEntity(VariousWorldModEntities.NECROMANCER_STAFF.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 2f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(5);
		entityarrow.setKnockback(2);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), VariousWorldModSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
