
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.sashakyotoz.variousworld.init.VWEntities;

import java.util.EnumSet;
import java.util.function.Predicate;

public class DarkFuryEntity extends Monster {

	public DarkFuryEntity(EntityType<DarkFuryEntity> type, Level world) {
		super(type, world);
		xpReward = 6;
		setNoGravity(true);
		this.moveControl = new FlyingMoveControl(this, 15, false);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}
	private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (entity) -> entity.attackable() && entity.getMobType() != MobType.WATER && !(entity instanceof DarkFuryEntity) && !(entity instanceof FuryLordEntity);
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this, 2));
		this.goalSelector.addGoal(2, new Goal() {
			{
				this.setFlags(EnumSet.of(Goal.Flag.MOVE));
			}

			public boolean canUse() {
				return DarkFuryEntity.this.getTarget() != null && !DarkFuryEntity.this.getMoveControl().hasWanted();
			}

			@Override
			public boolean canContinueToUse() {
				return DarkFuryEntity.this.getMoveControl().hasWanted() && DarkFuryEntity.this.getTarget() != null && DarkFuryEntity.this.getTarget().isAlive();
			}

			@Override
			public void start() {
				LivingEntity livingentity = DarkFuryEntity.this.getTarget();
				Vec3 vec3d = livingentity.getEyePosition(1);
				DarkFuryEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 2.5);
			}

			@Override
			public void tick() {
				LivingEntity livingentity = DarkFuryEntity.this.getTarget();
				if (DarkFuryEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
					DarkFuryEntity.this.doHurtTarget(livingentity);
				} else {
					double d0 = DarkFuryEntity.this.distanceToSqr(livingentity);
					if (d0 < 32) {
						Vec3 vec3d = livingentity.getEyePosition(1);
						DarkFuryEntity.this.moveControl.setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 2.5);
					}
				}
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class,5, false, true,LIVING_ENTITY_SELECTOR));
		this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setAlertOthers());
		this.goalSelector.addGoal(5, new FollowMobGoal(this, 1, 12, 6));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.PHANTOM_AMBIENT;
	}

	@Override
	public boolean isAlliedTo(Entity entity) {
		return entity instanceof DarkFuryEntity || entity instanceof FuryLordEntity;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return SoundEvents.PHANTOM_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.VEX_DEATH;
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.IN_FIRE))
			return false;
		if (source.is(DamageTypes.EXPLOSION))
			return false;
		if (source.is(DamageTypes.WITHER))
			return false;
		if (source.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	public static void init() {
		SpawnPlacements.register(VWEntities.DARK_FURY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.5);
		builder = builder.add(Attributes.MAX_HEALTH, 30);
		builder = builder.add(Attributes.ARMOR, 5);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.1);
		builder = builder.add(Attributes.FLYING_SPEED, 0.5);
		return builder;
	}
}
