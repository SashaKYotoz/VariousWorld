
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.entity.technical.SculkScytheEntity;
import net.sashakyotoz.variousworld.init.VariousWorldEntities;
import net.sashakyotoz.variousworld.init.VariousWorldItems;
import net.sashakyotoz.variousworld.init.VariousWorldParticleTypes;
import net.sashakyotoz.variousworld.procedures.AdvancementsManager;

public class SculkNecromancerSkeletonEntity extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Integer> DATA_SUMMON_COOLDOWN = SynchedEntityData.defineId(SculkNecromancerSkeletonEntity.class, EntityDataSerializers.INT);
    public AnimationState attackAnimationState = new AnimationState();
    public AnimationState deathAnimationState = new AnimationState();
    public AnimationState spawnAnimationState = new AnimationState();
    public static int attackAnimationRemainingTicks;
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.NOTCHED_10);

    public SculkNecromancerSkeletonEntity(EntityType<SculkNecromancerSkeletonEntity> type, Level world) {
        super(type, world);
        xpReward = 25;
        setNoAi(false);
        setPersistenceRequired();
        if (this.getRandom().nextBoolean())
            this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, new ItemStack(VariousWorldItems.NECROMANCER_WAND.get()));
        else
            this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, new ItemStack(VariousWorldItems.SCULK_SCYTHE.get()));
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SUMMON_COOLDOWN, 0);
    }

    public int summonCooldown() {
        return this.entityData.get(DATA_SUMMON_COOLDOWN);
    }

    public void setSummonCooldown(int set) {
        this.entityData.set(DATA_SUMMON_COOLDOWN, set);
    }

    public int getAttackAnimationRemainingTicks() {
        return attackAnimationRemainingTicks;
    }

    public boolean doHurtTarget(Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            attackAnimationRemainingTicks = 30;
            this.level().broadcastEntityEvent(this, (byte) 4);
            this.playSound(SoundEvents.EVOKER_FANGS_ATTACK, 1.0F, this.getVoicePitch());
            return CrystalWarriorEntity.hurtAndThrowTarget(this, (LivingEntity) entity);
        }
    }

    public void handleEntityEvent(byte handleByte) {
        if (handleByte == 4) {
            this.attackAnimationState.start(this.tickCount);
            this.playSound(SoundEvents.EVOKER_FANGS_ATTACK, 1.0F, this.getVoicePitch());
        } else {
            super.handleEntityEvent(handleByte);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, true) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth() + 4.0D;
            }
        });
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, false, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, ArmoredSkeletonEntity.class, false, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.75));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new SculkNecromancerSkeletonEntity.SculkNecromancerSkeletonAbility(this));
        this.goalSelector.addGoal(8, new NecromancerSummonSpellGoal(this));
    }

    static class SculkNecromancerSkeletonAbility extends Goal {
        private final SculkNecromancerSkeletonEntity skeleton;
        private int attackTime = 0;
        float f1 = Mth.clamp(0.15F, 0.1F, 1.0F);

        public SculkNecromancerSkeletonAbility(SculkNecromancerSkeletonEntity skeletonEntity) {
            this.skeleton = skeletonEntity;
        }

        public boolean canUse() {
            return this.skeleton.getTarget() != null && this.skeleton.summonCooldown() < 80;
        }

        public void start() {
            attackAnimationRemainingTicks = 0;
        }

        public void stop() {
            attackAnimationRemainingTicks = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.skeleton.getTarget();
            if (livingentity != null) {
                if (livingentity.distanceToSqr(this.skeleton) < 4096.0D && this.skeleton.hasLineOfSight(livingentity)) {
                    if (attackTime == 30) {
                        attackTime = 0;
                        this.skeleton.performRangedAttack(livingentity, f1);
                    } else if (attackTime < 30)
                        attackTime++;
                    this.skeleton.getNavigation().stop();
                    attackAnimationRemainingTicks = 30;
                } else if (attackAnimationRemainingTicks > 0) {
                    this.skeleton.getNavigation().moveTo(livingentity, 0.3);
                    --attackAnimationRemainingTicks;
                }
            }
        }
    }

    static class NecromancerSummonSpellGoal extends Goal {
        private final TargetingConditions skeletonCountTargeting = TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();
        private final SculkNecromancerSkeletonEntity skeleton;

        public NecromancerSummonSpellGoal(SculkNecromancerSkeletonEntity entity) {
            this.skeleton = entity;
        }

        public void start() {
            VariousWorld.LOGGER.debug("Spell started");
            action();
        }

        public boolean canUse() {
            int i = this.skeleton.level().getNearbyEntities(SculkSkeletonEntity.class, this.skeletonCountTargeting, this.skeleton, this.skeleton.getBoundingBox().inflate(16.0D)).size();
            return this.skeleton.getTarget() != null && this.skeleton.summonCooldown() >= 80 && this.skeleton.random.nextInt(4) + 1 > i;
        }

        public void stop() {
            this.skeleton.setSummonCooldown(0);
        }

        public void action() {
            ServerLevel serverlevel = (ServerLevel) this.skeleton.level();
            this.skeleton.getNavigation().stop();
            int i = this.skeleton.level().getNearbyEntities(SculkSkeletonEntity.class, this.skeletonCountTargeting, this.skeleton, this.skeleton.getBoundingBox().inflate(16.0D)).size();
            if (i == 0)
                i += 1 + this.skeleton.random.nextIntBetweenInclusive(0,2);
            for (int j = 0; j < i; j++) {
                SculkSkeletonEntity sculkSkeleton = new SculkSkeletonEntity(VariousWorldEntities.SCULK_SKELETON.get(), this.skeleton.level());
                sculkSkeleton.moveTo(new BlockPos((int) this.skeleton.getX(), (int) this.skeleton.getY(), (int) this.skeleton.getZ()), 0, 0);
                sculkSkeleton.finalizeSpawn(serverlevel, this.skeleton.level().getCurrentDifficultyAt(new BlockPos((int) this.skeleton.getX(), (int) this.skeleton.getY(), (int) this.skeleton.getZ())), MobSpawnType.MOB_SUMMONED, null, null);
                sculkSkeleton.setOwner(this.skeleton);
                this.skeleton.level().addFreshEntity(sculkSkeleton);
            }
        }
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float f) {
        if (this.getRandom().nextBoolean())
            SculkScytheEntity.shoot(this, target);
        else
            NecromancerStaffEntity.shoot(this, target);
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
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SKELETON_STEP, 0.15f, 1);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.SCULK_BLOCK_BREAK;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        setSummonCooldown(summonCooldown() + (int) (Math.random() * 20));
        if (source.is(DamageTypes.FALL))
            return false;
        if (source.is(DamageTypes.CACTUS))
            return false;
        if (source.is(DamageTypes.DROWN))
            return false;
        if (source.is(DamageTypes.WITHER))
            return false;
        if (source.getMsgId().equals("witherSkull"))
            return false;
        return super.hurt(source, amount);
    }

    protected void tickDeath() {
        if (!this.deathAnimationState.isStarted())
            this.deathAnimationState.start(this.tickCount);
        super.tickDeath();
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        spawnAnimationState.start(this.tickCount);
        spawnFoundParticles();
    }

    private void spawnFoundParticles() {
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0) {
                this.level().addParticle(VariousWorldParticleTypes.WANDERING_SPIRIT_PROJECTILE_PARTICLE.get(),
                        this.getX() + 0.5d, this.getY() + 1, this.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    @Override
    public void die(DamageSource source) {
        this.deathTime = -40;
        if (source.getEntity() instanceof Player player)
            AdvancementsManager.addAdvancement(player, AdvancementsManager.SCULK_NECROMANCER_ADV);
        if (this.random.nextBoolean()) {
            this.spawnAtLocation(new ItemStack(Items.BONE), this.random.nextIntBetweenInclusive(2, 11));
            this.spawnAtLocation(new ItemStack(Items.SCULK_SENSOR), this.random.nextIntBetweenInclusive(1, 3));
        }
        super.die(source);
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

    public static void init() {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 350);
        builder = builder.add(Attributes.ARMOR, 12);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 12);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.1);
        return builder;
    }
}