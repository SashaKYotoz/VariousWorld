
package net.sashakyotoz.variousworld.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import net.sashakyotoz.variousworld.entity.ai.LordOfFuriesFlyGoal;
import net.sashakyotoz.variousworld.init.VariousWorldEntities;
import net.sashakyotoz.variousworld.init.VariousWorldItems;
import net.sashakyotoz.variousworld.procedures.AdvancementsManager;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class FuryLordEntity extends Monster {
    private int timer = 200;
    private final TargetingConditions targetCountTargeting = TargetingConditions.forNonCombat().range(32.0D).ignoreLineOfSight().ignoreInvisibilityTesting();
    private static final EntityDataAccessor<Boolean> DATA_IS_ADVANCED = SynchedEntityData.defineId(FuryLordEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(FuryLordEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> DATA_ATTACK_ABILITY = SynchedEntityData.defineId(FuryLordEntity.class, EntityDataSerializers.STRING);
    private final ServerBossEvent bossInfo = new ServerBossEvent(Component.translatable("entity.various_world.fury_lord").withStyle(ChatFormatting.DARK_PURPLE), ServerBossEvent.BossBarColor.PURPLE, ServerBossEvent.BossBarOverlay.NOTCHED_10);
    private final FuryLordPart[] subEntities;
    public final FuryLordPart head;
    private final FuryLordPart body;
    private final FuryLordPart tail1;
    private final FuryLordPart tail2;
    private final FuryLordPart tail3;
    private final FuryLordPart tail4;
    private final FuryLordPart wing1;
    private final FuryLordPart wing2;
    private int explosionPower = 3;
    private static int attackAnimationRemainingTicks;
    public final AnimationState windAttackAnimationState = new AnimationState();
    public final AnimationState tailAttackAnimationState = new AnimationState();
    public final AnimationState summonAbilityAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();
    public final AnimationState idleInAirAnimationState = new AnimationState();

    public FuryLordEntity(EntityType<FuryLordEntity> type, Level world) {
        super(type, world);
        xpReward = 50;
        this.moveControl = new FlyingMoveControl(this, 25, false);
        this.head = new FuryLordPart(this, "head", 2.0F, 2.0F);
        this.body = new FuryLordPart(this, "body", 4.0F, 3.0F);
        this.tail1 = new FuryLordPart(this, "tail", 2.0F, 2.0F);
        this.tail2 = new FuryLordPart(this, "tail", 1.5F, 1.5F);
        this.tail3 = new FuryLordPart(this, "tail", 1.5F, 1.5F);
        this.tail4 = new FuryLordPart(this, "tail", 1.5F, 1.5F);
        this.wing1 = new FuryLordPart(this, "wing", 3.0F, 2.0F);
        this.wing2 = new FuryLordPart(this, "wing", 3.0F, 2.0F);
        this.subEntities = new FuryLordPart[]{this.head, this.body, this.tail1, this.tail2, this.tail3, this.tail4, this.wing1, this.wing2};
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
        this.setNoGravity(true);
    }

    @Override
    public PartEntity<?>[] getParts() {
        return this.subEntities;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(id + i + 1);
    }

    public void travel(Vec3 vec3) {
        if (this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.1F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.1F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
            } else {
                BlockPos ground = getBlockPosBelowThatAffectsMyMovement();
                float f = 0.91F;
                if (this.onGround()) {
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                }
                float f1 = 0.1635f / (f * f * f);
                f = 1.25F;
                if (this.onGround())
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 1.25F;
                this.moveRelative(this.onGround() ? 0.1F * f1 : 0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(f));
            }
        }
        this.calculateEntityAnimation(false);
    }

    public boolean isAdvanced() {
        return this.entityData.get(DATA_IS_ADVANCED);
    }

    public String getAttackAbility() {
        return this.entityData.get(DATA_ATTACK_ABILITY);
    }

    public void setAttackAbility(String set) {
        this.entityData.set(DATA_ATTACK_ABILITY, set);
    }

    public void setAdvanced(boolean set) {
        this.entityData.set(DATA_IS_ADVANCED, set);
    }

    public double getXVector(double yaw) {
        return 3 * Math.cos((yaw + 90) * (Math.PI / 180));
    }

    public double getZVector(double yaw) {
        return 3 * Math.sin((yaw + 90) * (Math.PI / 180));
    }

    private boolean isOrNotFuriesAround() {
        int i = this.level().getNearbyEntities(DarkFuryEntity.class, this.targetCountTargeting, this, this.getBoundingBox().inflate(32.0D)).size();
        return this.getRandom().nextInt(5) + 1 > i;
    }

    private boolean isMovingInAir() {
        return !this.onGround() && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    public void handleEntityEvent(byte handleByte) {
        if (handleByte >= 4 && handleByte <= 20)
            this.tailAttackAnimationState.start(this.tickCount);
        else {
            super.handleEntityEvent(handleByte);
        }
    }

    public void tick() {
        if (this.getHealth() < (this.getMaxHealth() / 2)) {
            this.explosionPower = 4;
            this.setAdvanced(true);
        } else {
            this.explosionPower = 3;
            this.setAdvanced(false);
        }
        if (this.getTarget() != null && this.distanceToSqr(this.getTarget()) >= 24) {
            if (this.timer > 0)
                this.timer--;
            else {
                ability();
                this.timer += this.getRandom().nextIntBetweenInclusive(150, 300);
            }
            if (this.getY() > this.getTarget().getY() + 8)
                this.setDeltaMovement(new Vec3(0, -0.5, 0));
        }
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.75D, 1.0D);
        if (!this.level().isClientSide && this.getTarget() != null) {
            if (!this.level().loadedAndEntityCanStandOn(this.getOnPos(), this))
                this.teleportTo(this.getTarget().getX(), this.getTarget().getY() + 3, this.getZ());
            Entity entity = this.getTarget();
            if (entity != null) {
                double d0 = vec3.y;
                if (this.getY() < entity.getY() || !this.isAdvanced() && this.getY() < entity.getY() + 6.5D) {
                    d0 = Math.max(0.0D, d0);
                    d0 += 0.3D - d0 * (double) 0.6F;
                }
                vec3 = new Vec3(vec3.x, d0, vec3.z);
                Vec3 vec31 = new Vec3(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
                if (vec31.horizontalDistanceSqr() > 12.0D) {
                    Vec3 vec32 = vec31.normalize();
                    vec3 = vec3.add(vec32.x * 0.3D - vec3.x * 0.6D, 0.0D, vec32.z * 0.3D - vec3.z * 0.6D);
                }
            }
        }
        this.setDeltaMovement(vec3);
        if (vec3.horizontalDistanceSqr() > 0.05D) {
            this.setYRot((float) Mth.atan2(vec3.z, vec3.x) * (180F / (float) Math.PI) - 90.0F);
        }
        this.flyAnimationState.animateWhen(this.isMovingInAir() && timer > 0 && !this.windAttackAnimationState.isStarted() && !this.summonAbilityAnimationState.isStarted(), this.tickCount);
        this.idleInAirAnimationState.animateWhen(!this.isMovingInAir() && timer > 0 && !this.windAttackAnimationState.isStarted() && !this.summonAbilityAnimationState.isStarted(), this.tickCount);
        super.tick();
    }

    private void ability() {
        LivingEntity target = this.getTarget();
        int tmp = this.getRandom().nextIntBetweenInclusive(0, 2);
        switch (tmp) {
            default -> setAttackAbility("tailAttack");
            case 1 -> setAttackAbility("summoning");
            case 2 -> setAttackAbility("winding");
        }
        if (target != null) {
            switch (getAttackAbility()) {
                case "summoning" -> {
                    if (!isOrNotFuriesAround()) {
                        ability();
                    } else {
                        this.summonAbilityAnimationState.start(this.tickCount);
                        int randomCount = this.getRandom().nextIntBetweenInclusive(1, 6);
                        for (int i = 0; i < randomCount; i++) {
                            if (this.level() instanceof ServerLevel level) {
                                DarkFuryEntity entityToSpawn = new DarkFuryEntity(VariousWorldEntities.DARK_FURY.get(), level);
                                entityToSpawn.moveTo(this.getX(), this.getY(), this.getZ(), level.getRandom().nextFloat() * 360F, 0);
                                entityToSpawn.finalizeSpawn(level, level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                                level.addFreshEntity(entityToSpawn);
                            }
                        }
                        setAttackAbility("tailAttack");
                    }
                }
                case "winding" -> {
                    this.windAttackAnimationState.start(this.tickCount);
                    target.setDeltaMovement(new Vec3(getXVector(this.getYRot()), this.getY(), getZVector(this.getYRot())));
                    target.hurt(this.damageSources().dragonBreath(), 10);
                    setAttackAbility("tailAttack");
                }
            }
        }
    }

    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (entity) -> entity.getMobType() != MobType.WATER && entity.attackable() && !(entity instanceof DarkFuryEntity);

    @Override
    public boolean doHurtTarget(@NotNull Entity entity) {
        if (entity instanceof LivingEntity entity1) {
            this.playSound(SoundEvents.ENDER_DRAGON_GROWL, 1.0F, this.getVoicePitch());
            return FuryLordEntity.hurtTarget(this, entity1);
        }
        return false;
    }

    static boolean hurtTarget(LivingEntity livingEntity, LivingEntity target) {
        float f = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return target.hurt(livingEntity.damageSources().mobAttack(livingEntity), f);
    }

    public void setCharging(boolean charging) {
        this.entityData.set(DATA_IS_CHARGING, charging);
    }

    public int getExplosionPower() {
        return this.explosionPower;
    }

    public int getAttackAnimationRemainingTicks() {
        return attackAnimationRemainingTicks;
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
        this.entityData.define(DATA_IS_ADVANCED, false);
        this.entityData.define(DATA_ATTACK_ABILITY, "tailAttack");
    }

    public void move(MoverType moverType, Vec3 vec3) {
        super.move(moverType, vec3);
        this.checkInsideBlocks();
    }

    public void aiStep() {
        if (attackAnimationRemainingTicks > 0)
            --attackAnimationRemainingTicks;
        Vec3[] avec3 = new Vec3[this.subEntities.length];
        for (int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }
        float f14 = this.getYRot() * ((float) Math.PI / 180F);
        float f1 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        this.tickPart(this.head, f1 * -2.5F, 1.0D, -f15 * -2.5F);
        this.tickPart(this.body, f1 * 0.5F, 0.0D, -f15 * 0.5F);
        this.tickPart(this.wing1, f15 * 3.5F, 1.5D, f1 * 3.5F);
        this.tickPart(this.wing2, f15 * -3.5F, 1.5D, f1 * -3.5F);
        this.tickPart(this.tail1, f1 * 2F, 0.5D, -f15 * 2F);
        this.tickPart(this.tail2, f1 * 3F, 0.5D, -f15 * 3F);
        this.tickPart(this.tail3, f1 * 4F, 0.5D, -f15 * 4F);
        this.tickPart(this.tail4, f1 * 5F, 0.5D, -f15 * 5F);
        for (int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }
        super.aiStep();
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level world) {
        return new FlyingPathNavigation(this, world);
    }

    private void tickPart(FuryLordPart lordPart, double pOffsetX, double pOffsetY, double pOffsetZ) {
        lordPart.setPos(this.getX() + pOffsetX, this.getY() + pOffsetY, this.getZ() + pOffsetZ);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LordOfFuriesFlyGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 10));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));
        this.goalSelector.addGoal(4, new FuryLordEntity.FuryLordShootFireballGoal(this));
        this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.5, false));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    static class FuryLordShootFireballGoal extends Goal {
        private final FuryLordEntity lordEntity;
        public int chargeTime;

        public FuryLordShootFireballGoal(FuryLordEntity lordEntity) {
            this.lordEntity = lordEntity;
        }

        public boolean canUse() {
            return this.lordEntity.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void stop() {
            this.lordEntity.setCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.lordEntity.getTarget();
            if (livingentity != null) {
                if (livingentity.distanceToSqr(this.lordEntity) < 4096.0D && this.lordEntity.hasLineOfSight(livingentity)) {
                    Level level = this.lordEntity.level();
                    ++this.chargeTime;
                    if (this.chargeTime == 20 && !this.lordEntity.isSilent()) {
                        this.lordEntity.playSound(SoundEvents.ENDER_DRAGON_SHOOT);
                    }
                    if (lordEntity.isAdvanced()) {
                        if (this.chargeTime == 60) {
                            Vec3 vec3 = this.lordEntity.getViewVector(1.0F);
                            double d2 = livingentity.getX() - (this.lordEntity.getX() + vec3.x * 4.0D);
                            double d3 = livingentity.getY(0.5D) - (0.5D + this.lordEntity.getY(0.5D));
                            double d4 = livingentity.getZ() - (this.lordEntity.getZ() + vec3.z * 4.0D);
                            if (!this.lordEntity.isSilent())
                                this.lordEntity.playSound(SoundEvents.ENDER_DRAGON_SHOOT);
                            this.lordEntity.setDeltaMovement(0, 0.325f, 0);
                            LargeFireball largefireball = new LargeFireball(level, this.lordEntity, d2, d3, d4, this.lordEntity.getExplosionPower());
                            largefireball.setPos(this.lordEntity.getX() + vec3.x * 4.0D, this.lordEntity.getY(0.5D) + 0.5D, largefireball.getZ() + vec3.z * 4.0D);
                            level.addFreshEntity(largefireball);
                            this.chargeTime = -60;
                            attackAnimationRemainingTicks = 30;
                            this.lordEntity.windAttackAnimationState.start(this.lordEntity.tickCount);
                        }
                    } else {
                        if (this.chargeTime == 80) {
                            Vec3 vec3 = this.lordEntity.getViewVector(1.0F);
                            double d2 = livingentity.getX() - (this.lordEntity.getX() + vec3.x * 4.0D);
                            double d3 = livingentity.getY(0.5D) - (0.5D + this.lordEntity.getY(0.5D));
                            double d4 = livingentity.getZ() - (this.lordEntity.getZ() + vec3.z * 4.0D);
                            if (!this.lordEntity.isSilent())
                                this.lordEntity.playSound(SoundEvents.ENDER_DRAGON_SHOOT);
                            this.lordEntity.setDeltaMovement(0, 0.325f, 0);
                            LargeFireball largefireball = new LargeFireball(level, this.lordEntity, d2, d3, d4, this.lordEntity.getExplosionPower());
                            largefireball.setPos(this.lordEntity.getX() + vec3.x * 4.0D, this.lordEntity.getY(0.5D) + 0.5D, largefireball.getZ() + vec3.z * 4.0D);
                            level.addFreshEntity(largefireball);
                            this.chargeTime = -80;
                            attackAnimationRemainingTicks = 30;
                        }
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }
                this.lordEntity.setCharging(this.chargeTime > 20);
            }
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENDER_DRAGON_DEATH;
    }

    @Override
    public boolean causeFallDamage(float l, float d, @NotNull DamageSource source) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL) || source.is(DamageTypes.LIGHTNING_BOLT) || source.is(DamageTypes.WITHER) || source.is(DamageTypes.EXPLOSION))
            return false;
        if (source.is(DamageTypes.FIREBALL))
            return false;
        if (source.getMsgId().equals("witherSkull"))
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public void die(@NotNull DamageSource source) {
        super.die(source);
        if (source.getEntity() instanceof ServerPlayer player)
            AdvancementsManager.addAdvancement(player, AdvancementsManager.LORD_OF_FURIES_ADV);
        this.spawnAtLocation(new ItemStack(VariousWorldItems.LORD_FURY_HEAD.get()));
        this.spawnAtLocation(new ItemStack(VariousWorldItems.LORD_FURY_SCALE.get(), this.random.nextIntBetweenInclusive(4, 9)));
        int countOfMinions = Mth.nextInt(RandomSource.create(), 2, 5);
        for (int i = 0; i < countOfMinions; i++) {
            if (this.level() instanceof ServerLevel level) {
                DarkFuryEntity entityToSpawn = new DarkFuryEntity(VariousWorldEntities.DARK_FURY.get(), level);
                entityToSpawn.moveTo(this.getX(), this.getY(), this.getZ(), this.level().getRandom().nextFloat() * 360F, 0);
                entityToSpawn.finalizeSpawn(level, level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                level.addFreshEntity(entityToSpawn);
            }
        }
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 1.5);
        builder = builder.add(Attributes.MAX_HEALTH, 500);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 12);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 3);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.5);
        builder = builder.add(Attributes.FLYING_SPEED, 1.5);
        return builder;
    }
}