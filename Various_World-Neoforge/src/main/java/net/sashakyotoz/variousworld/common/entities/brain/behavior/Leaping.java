package net.sashakyotoz.variousworld.common.entities.brain.behavior;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;
import net.sashakyotoz.variousworld.init.VWEntities;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class Leaping extends Behavior<SquealingSpiderEntity> {
    private final Function<SquealingSpiderEntity, UniformInt> getTimeBetweenLeaping;
    private final TargetingConditions ramTargeting = TargetingConditions.forCombat().selector((living) -> !living.getType().equals(VWEntities.SQUEALING_SPIDER.get()) && living.level().getWorldBorder().isWithinBounds(living.getBoundingBox()));
    private final float speed;
    private final ToDoubleFunction<SquealingSpiderEntity> getKnockbackForce;
    private Vec3 ramDirection;
    private final Function<SquealingSpiderEntity, SoundEvent> getImpactSound;

    public Leaping() {
        super(ImmutableMap.of(MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryStatus.VALUE_ABSENT, MemoryModuleType.RAM_TARGET, MemoryStatus.VALUE_PRESENT), 200);
        this.getTimeBetweenLeaping = (spider) -> spider.getHealth() < 9 ? UniformInt.of(200, 2000) : UniformInt.of(400, 4000);
        this.speed = 3;
        this.getKnockbackForce = (entity) -> 2;
        this.getImpactSound = (spider -> SoundEvents.SPIDER_AMBIENT);
        this.ramDirection = Vec3.ZERO;
    }

    protected boolean checkExtraStartConditions(ServerLevel level, SquealingSpiderEntity owner) {
        return owner.getBrain().hasMemoryValue(MemoryModuleType.RAM_TARGET);
    }

    protected boolean canStillUse(ServerLevel level, SquealingSpiderEntity entity, long gameTime) {
        return entity.getBrain().hasMemoryValue(MemoryModuleType.RAM_TARGET);
    }

    protected void start(ServerLevel level, SquealingSpiderEntity entity, long gameTime) {
        BlockPos blockpos = entity.blockPosition();
        Brain<?> brain = entity.getBrain();
        Vec3 vec3 = brain.getMemory(MemoryModuleType.RAM_TARGET).get();
        entity.getMoveControl().strafe(-1, 0);
        this.ramDirection = (new Vec3((double) blockpos.getX() - vec3.x(), 0.2F, (double) blockpos.getZ() - vec3.z())).normalize();
        brain.setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(vec3, this.speed, 0));
    }

    protected void tick(ServerLevel level, SquealingSpiderEntity owner, long gameTime) {
        List<LivingEntity> list = level.getNearbyEntities(LivingEntity.class, this.ramTargeting, owner, owner.getBoundingBox());
        Brain<?> brain = owner.getBrain();
        if (!list.isEmpty()) {
            LivingEntity livingentity = list.getFirst();
            DamageSource damagesource = level.damageSources().noAggroMobAttack(owner);
            if (livingentity.hurt(damagesource, (float) owner.getAttributeValue(Attributes.ATTACK_DAMAGE)))
                EnchantmentHelper.doPostAttackEffects(level, livingentity, damagesource);

            int i = owner.hasEffect(MobEffects.MOVEMENT_SPEED) ? owner.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() + 1 : 0;
            int j = owner.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? owner.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getAmplifier() + 1 : 0;
            float f = 0.25F * (float) (i - j);
            float f1 = Mth.clamp(owner.getSpeed() * 1.65F, 0.2F, 3.0F) + f;
            float f2 = livingentity.isDamageSourceBlocked(level.damageSources().mobAttack(owner)) ? 0.5F : 1.0F;
            owner.addDeltaMovement(ramDirection);
            livingentity.knockback((double) (f2 * f1) * this.getKnockbackForce.applyAsDouble(owner), this.ramDirection.x(), this.ramDirection.z());
            this.finishRam(level, owner);
            level.playSound(null, owner, this.getImpactSound.apply(owner), SoundSource.NEUTRAL, 1.0F, 1.0F);
        } else if (this.hasRammedHornBreakingBlock(level, owner)) {
            level.playSound(null, owner, this.getImpactSound.apply(owner), SoundSource.NEUTRAL, 1.0F, 1.0F);
            this.finishRam(level, owner);
        } else {
            Optional<WalkTarget> optional = brain.getMemory(MemoryModuleType.WALK_TARGET);
            Optional<Vec3> optional1 = brain.getMemory(MemoryModuleType.RAM_TARGET);
            boolean flag1 = optional.isEmpty() || optional1.isEmpty() || optional.get().getTarget().currentPosition().closerThan(optional1.get(), 0.25F);
            if (flag1) {
                this.finishRam(level, owner);
            }
        }

    }

    private boolean hasRammedHornBreakingBlock(ServerLevel level, SquealingSpiderEntity owner) {
        Vec3 vec3 = owner.getDeltaMovement().multiply(1.0F, 0.0F, 1.0F).normalize();
        BlockPos blockpos = BlockPos.containing(owner.position().add(vec3));
        return level.getBlockState(blockpos).is(BlockTags.SNAPS_GOAT_HORN) || level.getBlockState(blockpos.above()).is(BlockTags.SNAPS_GOAT_HORN);
    }

    protected void finishRam(ServerLevel level, SquealingSpiderEntity owner) {
        level.broadcastEntityEvent(owner, (byte) 59);
        owner.getBrain().setMemory(MemoryModuleType.RAM_COOLDOWN_TICKS, this.getTimeBetweenLeaping.apply(owner).sample(level.random));
        owner.getBrain().eraseMemory(MemoryModuleType.RAM_TARGET);
    }
}