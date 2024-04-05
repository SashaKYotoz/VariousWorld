
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;

import net.sashakyotoz.variousworld.init.VariousWorldModEntities;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class LordOfFuriesCrossbowEntity extends AbstractArrow implements ItemSupplier {
	public LordOfFuriesCrossbowEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(VariousWorldModEntities.LORD_OF_FURIES_CROSSBOW.get(), world);
	}

	public LordOfFuriesCrossbowEntity(EntityType<? extends LordOfFuriesCrossbowEntity> type, Level world) {
		super(type, world);
	}

	public LordOfFuriesCrossbowEntity(EntityType<? extends LordOfFuriesCrossbowEntity> type, LivingEntity entity, Level world) {
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
	public void tick() {
		super.tick();
		onTickParticle(this.level(), this.getX(), this.getY(), this.getZ());
		if (this.inGround)
			this.discard();
	}
	private void onTickParticle(Level level, double x, double y, double z) {
		if (this.random.nextFloat() < 0.25) {
			if (level instanceof ServerLevel serverLevel)
				serverLevel.sendParticles(ParticleTypes.FALLING_OBSIDIAN_TEAR, x, y, z, 9, 3, 3, 3, 1);
		}
	}

	public static LordOfFuriesCrossbowEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		LordOfFuriesCrossbowEntity arrow = new LordOfFuriesCrossbowEntity(VariousWorldModEntities.LORD_OF_FURIES_CROSSBOW.get(), entity, world);
		arrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		arrow.setSilent(true);
		arrow.setCritArrow(false);
		arrow.setBaseDamage(damage);
		arrow.setKnockback(knockback);
		arrow.setSecondsOnFire(100);
		world.addFreshEntity(arrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
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
