
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.sashakyotoz.variousworld.entity.technical.WanderingSpiritProjectileEntity;
import net.sashakyotoz.variousworld.init.VariousWorldEntities;
import net.sashakyotoz.variousworld.procedures.EventManager;
import org.jetbrains.annotations.NotNull;

public class WanderingSpiritOfSculksEntity extends Monster implements RangedAttackMob {
    public final AnimationState spawn = new AnimationState();
    public final AnimationState walk = new AnimationState();
    public final AnimationState attack = new AnimationState();
    public final AnimationState abilityAttack = new AnimationState();
    public static boolean spiritShot = false;
    private int rangedAttackAnimationRemainingTicks;
    public WanderingSpiritOfSculksEntity(EntityType<WanderingSpiritOfSculksEntity> type, Level world) {
        super(type, world);
        xpReward = 3;
        this.setMaxUpStep(1.5f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false){
            @Override
            protected void checkAndPerformAttack(LivingEntity entity, double distance) {
                double d0 = this.getAttackReachSqr(entity);
                if (distance <= d0 && this.mob instanceof WanderingSpiritOfSculksEntity entity1)
                    entity1.attack.start(entity1.tickCount);
                super.checkAndPerformAttack(entity, distance);
            }
        });
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, true) {
            @Override
            protected void findTarget() {
                if (!this.targetType.equals(Player.class) && !this.targetType.equals(ServerPlayer.class)) {
                    LivingEntity entity = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (entity1) -> true), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                    if (entity != null && entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D && !(entity instanceof WanderingSpiritOfSculksEntity))
                        this.target = entity;
                } else {
                    this.target = this.mob.level().getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                }
            }
        });
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new FollowMobGoal(this, (float) 1.2, 8, 4));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WanderingSpiritOfSculksEntity.WanderingSpiritAbility(this));
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.spawn.start(this.tickCount);
    }
    private void setupAnimationStates() {
        if (EventManager.isMovingOnLand(this))
            this.walk.startIfStopped(this.tickCount);
        else
            this.walk.stop();
    }
    @Override
    public void tick() {
        if (this.level().isClientSide())
            setupAnimationStates();
        super.tick();
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SCULK_BLOCK_STEP, 0.15f, 1);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.SCULK_BLOCK_HIT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.SCULK_SENSOR_BREAK;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.DROWN))
            return false;
        if (source.is(DamageTypes.EXPLOSION))
            return false;
        if (source.is(DamageTypes.FALLING_ANVIL))
            return false;
        if (source.is(DamageTypes.WITHER))
            return false;
        if (source.is(DamageTypes.WITHER_SKULL))
            return false;
        return super.hurt(source, amount);
    }

    public static void init() {
        SpawnPlacements.register(VariousWorldEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WanderingSpiritOfSculksEntity::checkSpiritConditions);
    }

    public static boolean checkSpiritConditions(EntityType<? extends WanderingSpiritOfSculksEntity> type, ServerLevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return accessor.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, accessor, spawnType, pos, random);
    }

    static class WanderingSpiritAbility extends Goal {
        private final WanderingSpiritOfSculksEntity spirit;
        private int attackTime = 0;
        float f1 = Mth.clamp(0.1F, 0.1F, 1.0F);

        public WanderingSpiritAbility(WanderingSpiritOfSculksEntity entity) {
            this.spirit = entity;
        }

        public boolean canUse() {
            return this.spirit.getTarget() != null && !spiritShot;
        }

        public void start() {
            this.spirit.rangedAttackAnimationRemainingTicks = 0;
        }

        public void stop() {
            this.spirit.rangedAttackAnimationRemainingTicks = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.spirit.getTarget();
            if (livingentity != null) {
                if (livingentity.distanceToSqr(this.spirit) < 4096.0D && livingentity.distanceToSqr(this.spirit) > 16.0D && this.spirit.hasLineOfSight(livingentity)) {
                    if (attackTime == 30) {
                        spiritShot = true;
                        this.spirit.performRangedAttack(livingentity, f1);
                        this.spirit.abilityAttack.start(this.spirit.tickCount);
                        this.spirit.getNavigation().moveTo(livingentity, 0.3);
                        this.spirit.rangedAttackAnimationRemainingTicks = 0;
                        attackTime = 0;
                    } else if (attackTime < 30) {
                        attackTime++;
                        this.spirit.getNavigation().stop();
                    }
                }
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 100);
        builder = builder.add(Attributes.ARMOR, 1);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 10);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 3);
        return builder;
    }

    static boolean hurtAndTarget(LivingEntity spirit, LivingEntity entity) {
        float f = (float) spirit.getAttributeValue(Attributes.ATTACK_DAMAGE);
        boolean flag = entity.hurt(spirit.damageSources().mobAttack(spirit), f);
        if (flag)
            spirit.doEnchantDamageEffects(spirit, entity);
        return flag;
    }
    public void handleEntityEvent(byte handleByte) {
        if (handleByte >= 4 && handleByte <= 20 && !(this.getTarget() != null && this.getTarget().getY() > this.getY() + 2)) {
            this.attack.start(this.tickCount);
        } else
            super.handleEntityEvent(handleByte);
    }
    public boolean doHurtTarget(Entity entity) {
        if (this.random.nextBoolean())
            spiritShot = false;
        if (!(entity instanceof LivingEntity))
            return false;
        else {
            this.level().broadcastEntityEvent(this, (byte) 4);
            this.playSound(SoundEvents.WARDEN_ATTACK_IMPACT, 0.75F, this.getVoicePitch());
            return hurtAndTarget(this, (LivingEntity) entity);
        }
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity livingEntity, float v) {
        rangedAttackAnimationRemainingTicks = RandomSource.create().nextInt(20, 40);
        this.abilityAttack.startIfStopped(this.tickCount);
        WanderingSpiritProjectileEntity.shoot(this, livingEntity);
    }
}
