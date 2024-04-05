
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.sashakyotoz.variousworld.VariousWorldMod;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;
import net.sashakyotoz.variousworld.procedures.AdvancementsManager;

public class CrystalWarriorEntity extends IronGolem {
    public AnimationState attackAnimationState = new AnimationState();
    public AnimationState groundAttackAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState deathAnimationState = new AnimationState();
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.PURPLE, ServerBossEvent.BossBarOverlay.NOTCHED_10);

    public CrystalWarriorEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(VariousWorldModEntities.CRYSTAL_WARRIOR.get(), world);
    }

    public CrystalWarriorEntity(EntityType<CrystalWarriorEntity> type, Level world) {
        super(type, world);
        xpReward = 25;
        setNoAi(false);
        setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(VariousWorldModItems.CRYSTAL_SWORD.get()));
    }

    private boolean isMovingOnLand() {
        return this.onGround() && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    private void setupAnimationStates() {
        if (this.isMovingOnLand())
            this.walkAnimationState.startIfStopped(this.tickCount);
         else
            this.walkAnimationState.stop();
    }
    protected void tickDeath() {
        if (!this.deathAnimationState.isStarted())
            this.deathAnimationState.start(this.tickCount);
        super.tickDeath();
    }

    @Override
    public void die(DamageSource source) {
        this.deathTime = -80;
        if(source.getEntity() instanceof Player player)
            AdvancementsManager.addAdvancement(player,AdvancementsManager.CRYSTALIC_WARRIOR_ADV);
        this.spawnAtLocation(new ItemStack(VariousWorldModItems.CRYSTALSHARD.get(),this.getRandom().nextIntBetweenInclusive(2,6)));
        this.spawnAtLocation(new ItemStack(VariousWorldModItems.DARKSHARD.get(),this.getRandom().nextIntBetweenInclusive(1,5)));
        this.spawnAtLocation(new ItemStack(Items.AMETHYST_SHARD,this.getRandom().nextIntBetweenInclusive(1,7)));
        if(this.getRandom().nextBoolean()){
            this.spawnAtLocation(new ItemStack(VariousWorldModItems.CRYSTAL_ARMOR_BOOTS.get()));
            this.spawnAtLocation(new ItemStack(VariousWorldModItems.CRYSTAL_ARMOR_LEGGINGS.get()));
        }else{
            this.spawnAtLocation(new ItemStack(VariousWorldModItems.CRYSTAL_ARMOR_CHESTPLATE.get()));
            this.spawnAtLocation(new ItemStack(VariousWorldModItems.CRYSTAL_ARMOR_HELMET.get()));
        }
        super.die(source);
    }

    public void tick() {
        if (this.level().isClientSide())
            setupAnimationStates();
        if (this.isDeadOrDying() && this.hasEffect(VariousWorldModMobEffects.AMETHYST_SPIKES.get()))
            this.removeEffect(VariousWorldModMobEffects.AMETHYST_SPIKES.get());
        super.tick();
    }

    public boolean doHurtTarget(Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            this.level().broadcastEntityEvent(this, (byte) 4);
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, this.getVoicePitch());
            return CrystalWarriorEntity.hurtAndThrowTarget(this, (LivingEntity) entity);
        }
    }

    static boolean hurtAndThrowTarget(LivingEntity warrior, LivingEntity target) {
        float f = (float) warrior.getAttributeValue(Attributes.ATTACK_DAMAGE);
        boolean flag = target.hurt(warrior.damageSources().mobAttack(warrior), f);
        if (flag) {
            warrior.doEnchantDamageEffects(warrior, target);
            throwTarget(warrior, target);
        }
        return flag;
    }

    public void handleEntityEvent(byte handleByte) {
        if (handleByte >= 4 && handleByte <= 20 && !(this.getTarget() != null && this.getTarget().getY() > this.getY() + 2)) {
            this.attackAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(handleByte);
        }
    }

    static void throwTarget(LivingEntity warrior, LivingEntity target) {
        double d0 = warrior.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        double d1 = target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        double d2 = 0.5D + d0 - d1;
        if (!(d2 <= 0.0D)) {
            double d3 = target.getX() - warrior.getX();
            double d4 = target.getZ() - warrior.getZ();
            float f = (float) (warrior.level().random.nextInt(21) - 8);
            double d5 = d2 * (double) (warrior.level().random.nextFloat() * 0.75F + 0.25F);
            Vec3 vec3 = (new Vec3(d3, 0.0D, d4)).normalize().scale(d5).yRot(f);
            double d6 = d2 * (double) warrior.level().random.nextFloat() * 0.5D;
            target.push(vec3.x, d6, vec3.z);
            target.hurtMarked = true;
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, (float) 0.5));
        this.goalSelector.addGoal(6, new FloatGoal(this));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, Player.class, false, false));

    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(VariousWorldModItems.CRYSTAL_GEM.get()));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.LARGE_AMETHYST_BUD_BREAK, 0.15f, 1);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.WARDEN_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.LARGE_AMETHYST_BUD_BREAK;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() != null)
            hurtAction(source.getEntity());
        if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
            return false;
        if (source.is(DamageTypes.CACTUS))
            return false;
        if (source.is(DamageTypes.DROWN))
            return false;
        if (source.is(DamageTypes.LIGHTNING_BOLT))
            return false;
        if (source.is(DamageTypes.STALAGMITE))
            return false;
        if (source.is(DamageTypes.WITHER))
            return false;
        if (source.getMsgId().equals("witherSkull"))
            return false;
        return super.hurt(source, amount);
    }

    private void hurtAction(Entity sourceentity) {
        boolean flag = this.getRandom().nextBoolean();
        double speed;
        double Yaw;
        if (this.getRandom().nextBoolean()) {
            if (flag && sourceentity != null) {
                speed = 0.8;
                Yaw = this.getYRot();
                sourceentity.setDeltaMovement(new Vec3((speed * Math.cos((Yaw + 90) * (Math.PI / 180))), 0.4, (speed * Math.sin((Yaw + 90) * (Math.PI / 180)))));
            } else if (!flag && !this.hasEffect(VariousWorldModMobEffects.AMETHYST_SPIKES.get())) {
                this.addEffect(new MobEffectInstance(VariousWorldModMobEffects.AMETHYST_SPIKES.get(), 100, 1));
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION,60,3));
            }
            if (sourceentity != null && sourceentity.getY() > this.getY() + 2) {
                this.getNavigation().stop();
                this.groundAttackAnimationState.start(this.tickCount);
                this.walkAnimationState.stop();
                this.attackAnimationState.stop();
                VariousWorldMod.queueServerWork(20, () -> {
                    this.doHurtTarget(sourceentity);
                    sourceentity.setDeltaMovement(0, 1, 0);
                });
            }
        }
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
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35);
        builder = builder.add(Attributes.MAX_HEALTH, 400);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 8);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.5);
        return builder;
    }
}
