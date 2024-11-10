
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.sashakyotoz.variousworld.entity.ai.DarkSpiritFlyGoal;
import net.sashakyotoz.variousworld.entity.technical.DarkSpiritGlovesEntity;
import net.sashakyotoz.variousworld.init.VariousWorldItems;
import net.sashakyotoz.variousworld.init.VariousWorldParticleTypes;
import net.sashakyotoz.variousworld.procedures.AdvancementsManager;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DarkSpiritEntity extends Monster {
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState spawnAnimationState = new AnimationState();
    public final AnimationState shieldAriseAnimationState = new AnimationState();
    public final AnimationState spellAriseAnimationState = new AnimationState();
    public final AnimationState attack1AnimationState = new AnimationState();
    public final AnimationState flightAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleWithShieldAnimationState = new AnimationState();
    public final AnimationState flightWithShieldAnimationState = new AnimationState();
    public static int texture, chargeToShield;
    private static final EntityDataAccessor<Boolean> DATA_SHIELD_ROSE = SynchedEntityData.defineId(DarkSpiritEntity.class, EntityDataSerializers.BOOLEAN);
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.NOTCHED_6);

    public DarkSpiritEntity(EntityType<DarkSpiritEntity> type, Level world) {
        super(type, world);
        Random random = new Random();
        xpReward = 25;
        texture = (int) Math.round((Math.random()));
        setPersistenceRequired();
        this.moveControl = new FlyingMoveControl(this, 12, true);
        this.setItemSlotAndDropWhenKilled(EquipmentSlot.HEAD, new ItemStack(VariousWorldItems.ANGEL_HELMET.get()));
        this.setItemSlotAndDropWhenKilled(EquipmentSlot.CHEST, new ItemStack(VariousWorldItems.ANGEL_CHESTPLATE.get()));
        this.setItemSlotAndDropWhenKilled(EquipmentSlot.LEGS, new ItemStack(VariousWorldItems.ANGEL_LEGGINGS.get()));
        this.setItemSlotAndDropWhenKilled(EquipmentSlot.FEET, new ItemStack(VariousWorldItems.ANGEL_BOOTS.get()));
        this.setItemSlotAndDropWhenKilled(EquipmentSlot.FEET, new ItemStack(VariousWorldItems.ANGEL_BOOTS.get()));
        this.getItemBySlot(EquipmentSlot.HEAD).setDamageValue(random.nextInt(33));
        this.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(random.nextInt(71));
        this.getItemBySlot(EquipmentSlot.LEGS).setDamageValue(random.nextInt(52));
        this.getItemBySlot(EquipmentSlot.FEET).setDamageValue(random.nextInt(23));
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SHIELD_ROSE, false);
    }

    public boolean shieldRose() {
        return this.entityData.get(DATA_SHIELD_ROSE);
    }

    public void setShieldArise(boolean set) {
        this.entityData.set(DATA_SHIELD_ROSE, set);
    }

    private boolean isMovingInAir() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }


    @Override
    public void handleEntityEvent(byte handleByte) {
        if (handleByte >= 4 && handleByte <= 24) {
            if (this.random.nextBoolean())
                this.attackAnimationState.start(this.tickCount);
            else
                this.attack1AnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(handleByte);
        }
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        spawnAnimationState.start(this.tickCount);
        spawnFoundParticles();
    }

    private void spawnFoundParticles() {
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0) {
                this.level().addParticle(VariousWorldParticleTypes.LORD_SHOOT_PARTICLE.get(),
                        this.getX() + 0.5d, this.getY() + 1, this.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            this.level().broadcastEntityEvent(this, (byte) 4);
            this.playSound(SoundEvents.VEX_HURT, 1.0F, this.getVoicePitch());
            return DarkSpiritEntity.hurtAndThrowTarget(this, (LivingEntity) entity);
        }
    }

    @Override
    public void tick() {
        if (chargeToShield >= 4 && Math.random() < 0.75)
            setShieldArise(true);
        else if (chargeToShield < 2)
            setShieldArise(false);
        if (this.getTarget() != null && this.getTarget().isAlive())
            this.getNavigation().moveTo(this.getTarget(), 2);
        this.noPhysics = this.getTarget() != null && this.getTarget().isAlive();
        this.setNoGravity(true);
        super.tick();
    }

    public void aiStep() {
        super.aiStep();
        if (isMovingInAir()) {
            if (shieldRose()) {
                this.idleWithShieldAnimationState.stop();
                this.idleWithShieldAnimationState.stop();
                this.flightWithShieldAnimationState.startIfStopped(this.tickCount);
            } else {
                this.idleWithShieldAnimationState.stop();
                this.idleWithShieldAnimationState.stop();
                this.flightAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            if (shieldRose()) {
                this.flightWithShieldAnimationState.stop();
                this.flightAnimationState.stop();
                this.idleWithShieldAnimationState.startIfStopped(this.tickCount);
            } else {
                this.flightWithShieldAnimationState.stop();
                this.flightAnimationState.stop();
                this.idleAnimationState.startIfStopped(this.tickCount);
            }
        }
    }

    static boolean hurtAndThrowTarget(LivingEntity livingEntity, LivingEntity target) {
        float f = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return target.hurt(livingEntity.damageSources().mobAttack(livingEntity), f);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(Level world) {
        return new FlyingPathNavigation(this, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new DarkSpiritFlyGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 2, 20));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, false, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SculkNecromancerSkeletonEntity.class, false, true));
        this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new DarkSpiritEntity.Teleport(this));
    }

    static class Teleport extends Goal {
        private int attackTime = 0;
        private final DarkSpiritEntity spirit;

        public Teleport(DarkSpiritEntity spirit) {
            this.spirit = spirit;
        }

        @Override
        public boolean canUse() {
            return this.spirit.getTarget() != null && this.spirit.getTarget().isAlive() && this.spirit.shieldRose();
        }

        public void start() {
            this.spirit.spellAriseAnimationState.start(this.spirit.tickCount);
        }

        public void tick() {
            LivingEntity target = this.spirit.getTarget();
            if (target != null && target.distanceToSqr(this.spirit) < 512.0D && this.spirit.hasLineOfSight(target)) {
                if (attackTime == 60) {
                    attackTime = 0;
                    this.spirit.spellAriseAnimationState.stop();
                    this.spirit.level().addFreshEntity(new DarkSpiritGlovesEntity(this.spirit.level(), this.spirit, target, this.spirit.getMotionDirection().getAxis()));
                    if (Math.random() < 0.5) {
                        chargeToShield = -2;
                        this.spirit.setPos(new Vec3(target.getX(), target.getY(), target.getZ()));
                    }
                } else if (attackTime < 60) {
                    attackTime++;
                }
                this.spirit.getNavigation().stop();
            } else {
                if (target != null)
                    this.spirit.getNavigation().moveTo(target, 0.5);
            }
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.SOUL_SAND_BREAK;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.WITHER_SKELETON_DEATH;
    }

    @Override
    public boolean causeFallDamage(float l, float d, DamageSource source) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (chargeToShield <= 4 && !shieldRose())
            chargeToShield++;
        else if (chargeToShield == 4 && shieldRose())
            this.shieldAriseAnimationState.start(this.tickCount);
        else {
            chargeToShield--;
        }
        if (source.getEntity() instanceof Projectile projectile && this.shieldRose() && amount > 2 && Math.random() < 0.75) {
            this.hurt(this.damageSources().arrow((AbstractArrow) projectile, projectile.getOwner()), amount / 2f);
            chargeToShield -= 3;
        }
        if (source.is(DamageTypes.IN_FIRE))
            return false;
        if (source.is(DamageTypes.FALL))
            return false;
        if (source.is(DamageTypes.CACTUS))
            return false;
        if (source.is(DamageTypes.DROWN))
            return false;
        if (source.is(DamageTypes.LIGHTNING_BOLT))
            return false;
        if (source.is(DamageTypes.EXPLOSION))
            return false;
        if (source.is(DamageTypes.WITHER))
            return false;
        if (source.is(DamageTypes.WITHER_SKULL))
            return false;
        if (source.getEntity() instanceof Player && shieldRose()) {
            return false;
        }
        return super.hurt(source, amount);
    }

    public void move(MoverType moverType, Vec3 vec3) {
        super.move(moverType, vec3);
        this.checkInsideBlocks();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (source.getEntity() instanceof ServerPlayer player) {
            AdvancementsManager.addAdvancement(player, AdvancementsManager.DARK_SPIRIT_ADV);
            if (this.getDisplayName().getString().equals("Basics"))
                AdvancementsManager.addAdvancement(player, AdvancementsManager.EASTER_DARK_SPIRIT_ADV);
        }
        this.spawnAtLocation(new ItemStack(VariousWorldItems.TOTEM_OF_DARK_SPIRIT.get()));
        this.convertTo(EntityType.VEX, true);
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Override
    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 320);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 8);
        builder = builder.add(Attributes.ARMOR, 4);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 4);
        builder = builder.add(Attributes.FLYING_SPEED, 0.3);
        return builder;
    }
}
