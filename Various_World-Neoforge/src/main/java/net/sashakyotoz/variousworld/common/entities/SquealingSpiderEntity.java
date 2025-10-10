package net.sashakyotoz.variousworld.common.entities;

import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.breeze.BreezeAi;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.entities.brain.SquealingSpiderAi;

import javax.annotation.Nullable;
import java.util.Optional;

public class SquealingSpiderEntity extends Spider {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(SquealingSpiderEntity.class, EntityDataSerializers.BYTE);
    public AnimationState slide = new AnimationState();
    public AnimationState slideBack = new AnimationState();
    public AnimationState longJump = new AnimationState();
    public AnimationState shoot = new AnimationState();
    public AnimationState inhale = new AnimationState();
    private int jumpTrailStartedTick = 0;
    private int soundTick = 0;

    public SquealingSpiderEntity(EntityType<? extends Spider> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 18);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        return builder;
    }

    public boolean canConnectToRoof() {
        return this.level().getBlockState(this.getOnPos().above()).canOcclude();
    }

    @Override
    protected void applyGravity() {
        double d0 = this.getGravity();
        if (canConnectToRoof())
            this.setDeltaMovement(this.getDeltaMovement().add(0.0F, d0, 0.0F));
        else {
            if (d0 != (double) 0.0F)
                this.setDeltaMovement(this.getDeltaMovement().add(0.0F, -d0, 0.0F));
        }
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SquealingSpiderAi.makeBrain(this, this.brainProvider().makeBrain(dynamic));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Brain<SquealingSpiderEntity> getBrain() {
        return (Brain<SquealingSpiderEntity>) super.getBrain();
    }

    @Override
    protected Brain.Provider<SquealingSpiderEntity> brainProvider() {
        return Brain.provider(SquealingSpiderAi.MEMORY_TYPES, SquealingSpiderAi.SENSOR_TYPES);
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
        if (!state.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(state, motionMultiplier);
        }
    }

    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte) 0);
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean climbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (climbing) {
            b0 = (byte) (b0 | 1);
        } else
            b0 = (byte) (b0 & -2);

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (this.level().isClientSide() && DATA_POSE.equals(key)) {
            this.resetAnimations();
            Pose pose = this.getPose();
            switch (pose) {
                case SHOOTING -> this.shoot.startIfStopped(this.tickCount);
                case INHALING -> this.longJump.startIfStopped(this.tickCount);
                case SLIDING -> this.slide.startIfStopped(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(key);
    }

    private void resetAnimations() {
        this.shoot.stop();
        this.inhale.stop();
        this.longJump.stop();
    }

    public void tick() {
        Pose pose = this.getPose();
        switch (pose) {
            case SHOOTING:
            case INHALING:
            case STANDING:
                this.resetJumpTrail().emitGroundParticles(1 + this.getRandom().nextInt(1));
                break;
            case SLIDING:
                this.emitGroundParticles(20);
                break;
            case LONG_JUMPING:
                this.emitJumpTrailParticles();
        }

        if (pose != Pose.SLIDING && this.slide.isStarted()) {
            this.slideBack.start(this.tickCount);
            this.slide.stop();
        }

        this.soundTick = this.soundTick == 0 ? this.random.nextIntBetweenInclusive(1, 80) : this.soundTick - 1;
        if (this.soundTick == 0) {
            this.playWhirlSound();
        }
        if (!this.level().isClientSide)
            this.setClimbing(this.horizontalCollision);
        super.tick();
    }

    public SquealingSpiderEntity resetJumpTrail() {
        this.jumpTrailStartedTick = 0;
        return this;
    }

    public void emitJumpTrailParticles() {
        if (++this.jumpTrailStartedTick <= 5) {
            BlockState blockstate = !this.getInBlockState().isAir() ? this.getInBlockState() : this.getBlockStateOn();
            Vec3 vec3 = this.getDeltaMovement();
            Vec3 vec31 = this.position().add(vec3).add(0.0F, 0.1F, 0.0F);

            for (int i = 0; i < 3; ++i) {
                this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), vec31.x, vec31.y, vec31.z, 0.0F, 0.0F, 0.0F);
            }
        }
    }

    public void emitGroundParticles(int count) {
        if (!this.isPassenger() && !OnActionsTrigger.isMovingOnLand(this)) {
            Vec3 vec3 = this.getBoundingBox().getCenter();
            Vec3 vec31 = new Vec3(vec3.x, this.position().y, vec3.z);
            BlockState blockstate = !this.getInBlockState().isAir() ? this.getInBlockState() : this.getBlockStateOn();
            if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                for (int i = 0; i < count; ++i) {
                    this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), vec31.x, vec31.y, vec31.z, 0.0F, 0.0F, 0.0F);
                }
            }
        }
    }

    public Optional<LivingEntity> getHurtBy() {
        return this.getBrain().getMemory(MemoryModuleType.HURT_BY).map(DamageSource::getEntity).filter((entity) -> entity instanceof LivingEntity).map((entity) -> (LivingEntity) entity);
    }

    public void playAmbientSound() {
        if (this.getTarget() == null || !this.onGround()) {
            this.level().playLocalSound(this, this.getAmbientSound(), this.getSoundSource(), 1.0F, 1.0F);
        }

    }

    public void playWhirlSound() {
        float f = 0.7F + 0.4F * this.random.nextFloat();
        float f1 = 0.8F + 0.2F * this.random.nextFloat();
        this.level().playLocalSound(this, SoundEvents.BREEZE_WHIRL, this.getSoundSource(), f1, f);
    }

    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_HURT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SPIDER_HURT;
    }

    protected SoundEvent getAmbientSound() {
        return this.onGround() ? SoundEvents.SPIDER_AMBIENT : SoundEvents.BREEZE_IDLE_AIR;
    }

    protected void customServerAiStep(ServerLevel level) {
        ProfilerFiller profilerfiller = Profiler.get();
        profilerfiller.push("spiderBrain");
        this.getBrain().tick(level, this);
        profilerfiller.popPush("spiderActivityUpdate");
        SquealingSpiderAi.updateActivity(this);
        profilerfiller.pop();
        super.customServerAiStep(level);
    }

    public double getFluidJumpThreshold() {
        return this.getEyeHeight();
    }

    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        if (fallDistance > 3.5F) {
            this.playSound(SoundEvents.BREEZE_LAND, 1.2F, 0.2F);
            return false;
        }

        return super.causeFallDamage(fallDistance, multiplier, source);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @org.jetbrains.annotations.Nullable SpawnGroupData spawnGroupData) {
        RandomSource randomsource = level.getRandom();
        SquealingSpiderAi.initMemories(this, randomsource);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    protected MovementEmission getMovementEmission() {
        return MovementEmission.EVENTS;
    }

    @Nullable
    public LivingEntity getTarget() {
        return this.getTargetFromBrain();
    }

    public static boolean checkSquealingSpiderSpawnRules(EntityType<SquealingSpiderEntity> spider, LevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
        if (EntitySpawnReason.isSpawner(spawnType))
            return checkMobSpawnRules(spider, level, spawnType, pos, random);
        else {
            if (level.getDifficulty() != Difficulty.PEACEFUL) {
                BlockPos posAbove = pos.above();
                BlockPos posBelow = pos.below();
                if (pos.getY() < 32)
                    return level.getBlockState(posBelow).isValidSpawn(level, posBelow, spider) || level.getBlockState(posAbove).canOcclude();
            }
            return false;
        }
    }
}