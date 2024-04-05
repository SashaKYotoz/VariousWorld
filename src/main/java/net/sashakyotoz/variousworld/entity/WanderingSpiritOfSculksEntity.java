
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import org.jetbrains.annotations.NotNull;

public class WanderingSpiritOfSculksEntity extends Monster implements RangedAttackMob {
    public static boolean spiritShot = false;
    private int attackAnimationRemainingTicks;
    private int rangedAttackAnimationRemainingTicks;

    public int getAttackAnimationRemainingTicks() {
        return this.attackAnimationRemainingTicks;
    }

    public int getRangedAttackAnimationRemainingTicks() {
        return this.rangedAttackAnimationRemainingTicks;
    }

    public WanderingSpiritOfSculksEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(VariousWorldModEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), world);
    }

    public WanderingSpiritOfSculksEntity(EntityType<WanderingSpiritOfSculksEntity> type, Level world) {
        super(type, world);
        xpReward = 3;
        setNoAi(false);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
            }
        });
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AgeableMob.class, false, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new FollowMobGoal(this, (float) 1.2, 8, 4));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, (float) 16));
        this.goalSelector.addGoal(7, new LeapAtTargetGoal(this, (float) 0.5));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new WanderingSpiritOfSculksEntity.WanderingSpiritAbility(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
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
        SpawnPlacements.register(VariousWorldModEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WanderingSpiritOfSculksEntity::checkSpiritConditions);
    }

    public static boolean checkSpiritConditions(EntityType<? extends WanderingSpiritOfSculksEntity> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos p_219017_, RandomSource p_219018_) {
        return p_219015_.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
    }
    static class WanderingSpiritAbility extends Goal {
        private final WanderingSpiritOfSculksEntity spirit;
        private int attackTime = 0;
        float f1 = Mth.clamp(0.1F, 0.1F, 1.0F);

        public WanderingSpiritAbility(WanderingSpiritOfSculksEntity p_32776_) {
            this.spirit = p_32776_;
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
                if (livingentity.distanceToSqr(this.spirit) < 4096.0D && this.spirit.hasLineOfSight(livingentity)) {
                    if (attackTime == 30) {
                        spiritShot = true;
                        this.spirit.performRangedAttack(livingentity, f1);
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

    static boolean hurtAndTarget(LivingEntity p_34643_, LivingEntity p_34644_) {
        float f = (float) p_34643_.getAttributeValue(Attributes.ATTACK_DAMAGE);
        boolean flag = p_34644_.hurt(p_34643_.damageSources().mobAttack(p_34643_), f);
        if (flag) {
            p_34643_.doEnchantDamageEffects(p_34643_, p_34644_);
        }
        return flag;
    }

    public boolean doHurtTarget(Entity p_34491_) {
        if(Math.random() > 0.5)
            spiritShot = false;
        if (!(p_34491_ instanceof LivingEntity))
            return false;
         else {
            attackAnimationRemainingTicks = 30;
            this.level().broadcastEntityEvent(this, (byte) 4);
            this.playSound(SoundEvents.WARDEN_ATTACK_IMPACT, 0.75F, this.getVoicePitch());
            return hurtAndTarget(this, (LivingEntity) p_34491_);
        }
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity p_33317_, float p_33318_) {
        rangedAttackAnimationRemainingTicks = RandomSource.create().nextInt(20,40);
        WanderingSpiritAbilityShootEntity.shoot(this,p_33317_);
    }
}
