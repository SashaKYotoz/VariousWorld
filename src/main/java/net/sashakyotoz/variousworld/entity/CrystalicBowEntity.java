
package net.sashakyotoz.variousworld.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import net.sashakyotoz.variousworld.procedures.CrystalicBowProjectileHitsLivingEntityProcedure;
import net.sashakyotoz.variousworld.procedures.CrystalicBowWhileProjectileFlyingTickProcedure;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class CrystalicBowEntity extends AbstractArrow implements ItemSupplier {
	public CrystalicBowEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(VariousWorldModEntities.CRYSTALIC_BOW.get(), world);
	}
	public CrystalicBowEntity(EntityType<? extends CrystalicBowEntity> type, Level world) {
		super(type, world);
	}
	public CrystalicBowEntity(EntityType<? extends CrystalicBowEntity> type, LivingEntity entity, Level world) {
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
		CrystalicBowProjectileHitsLivingEntityProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), entityHitResult.getEntity());
	}

	@Override
	public void tick() {
		super.tick();
		CrystalicBowWhileProjectileFlyingTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
	}

	public static CrystalicBowEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		CrystalicBowEntity bowEntity = new CrystalicBowEntity(VariousWorldModEntities.CRYSTALIC_BOW.get(), entity, world);
		bowEntity.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 1,0);
		bowEntity.setSilent(true);
		bowEntity.setCritArrow(false);
		bowEntity.setBaseDamage(damage);
		bowEntity.setKnockback(knockback);
		if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, entity.getMainHandItem()) > 0)
			bowEntity.setSecondsOnFire(100);
		int j = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, entity.getMainHandItem());
		if (j > 0)
			bowEntity.setBaseDamage(bowEntity.getBaseDamage() + (double)j * 0.5D + 0.5D);
		int k = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, entity.getMainHandItem());
		if (k > 0)
			bowEntity.setKnockback(k);
		world.addFreshEntity(bowEntity);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return bowEntity;
	}

	public static CrystalicBowEntity shoot(LivingEntity entity, LivingEntity target) {
		CrystalicBowEntity bowEntity = new CrystalicBowEntity(VariousWorldModEntities.CRYSTALIC_BOW.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		bowEntity.shoot(dx, dy - bowEntity.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
		bowEntity.setSilent(true);
		bowEntity.setBaseDamage(4);
		bowEntity.setKnockback(2);
		bowEntity.setCritArrow(false);
		if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, entity.getMainHandItem()) > 0) {
			bowEntity.setSecondsOnFire(100);
		}
		int j = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, entity.getMainHandItem());
		if (j > 0) {
			bowEntity.setBaseDamage(bowEntity.getBaseDamage() + (double)j * 0.5D + 0.5D);
		}
		int k = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, entity.getMainHandItem());
		if (k > 0) {
			bowEntity.setKnockback(k);
		}
		entity.level().addFreshEntity(bowEntity);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),  SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return bowEntity;
	}
}
