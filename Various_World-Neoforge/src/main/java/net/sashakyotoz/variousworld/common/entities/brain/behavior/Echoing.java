package net.sashakyotoz.variousworld.common.entities.brain.behavior;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.util.Util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.LongJumpUtil;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;

import java.util.ArrayList;
import java.util.Optional;

public class Echoing extends Behavior<SquealingSpiderEntity> {
    private static final ObjectArrayList<Integer> ALLOWED_ANGLES = new ObjectArrayList(Lists.newArrayList(30, 60, 90, 100, 110));
    private static final int SHOOT_INITIAL_DELAY_TICKS = Math.round(45.0F);
    private static final int SHOOT_RECOVER_DELAY_TICKS = Math.round(12.0F);
    private static final int SHOOT_COOLDOWN_TICKS = Math.round(60.0F);

    @VisibleForTesting
    public Echoing() {
        super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.BREEZE_SHOOT_COOLDOWN, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_SHOOT_CHARGING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_SHOOT_RECOVERING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_SHOOT, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_JUMP_TARGET, MemoryStatus.VALUE_ABSENT), SHOOT_INITIAL_DELAY_TICKS + 1 + SHOOT_RECOVER_DELAY_TICKS);
    }

    protected boolean checkExtraStartConditions(ServerLevel level, SquealingSpiderEntity owner) {
        return owner.getPose() == Pose.STANDING && !OnActionsTrigger.findBlocksInRegion(
                level,
                owner.getOnPos().offset(-16, -8, -16),
                owner.getOnPos().offset(16, 8, 16),
                (state) -> state != null && state.is(Blocks.COBWEB),
                6).isEmpty() && owner.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).map((living) -> isTargetWithinRange(owner, living)).map((aBoolean) -> {
            if (!aBoolean)
                owner.getBrain().eraseMemory(MemoryModuleType.BREEZE_SHOOT);
            return aBoolean;
        }).orElse(false);
    }

    static Vec3 randomPointBehindSpider(SquealingSpiderEntity spider, RandomSource random) {
        float f = spider.yHeadRot + 180.0F + (float) random.nextGaussian() * 90.0F / 2.0F;
        float f1 = Mth.lerp(random.nextFloat(), 3.0F, 8.0F);
        Vec3 vec3 = Vec3.directionFromRotation(0.0F, f).scale(f1);
        return spider.position().add(vec3);
    }

    protected boolean canStillUse(ServerLevel level, SquealingSpiderEntity entity, long gameTime) {
        return entity.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET) && entity.getBrain().hasMemoryValue(MemoryModuleType.BREEZE_SHOOT);
    }

    protected void start(ServerLevel level, SquealingSpiderEntity entity, long gameTime) {
        entity.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).ifPresent((p_312833_) -> entity.setPose(Pose.SHOOTING));
        entity.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT_CHARGING, Unit.INSTANCE, SHOOT_INITIAL_DELAY_TICKS);
        entity.playSound(SoundEvents.BREEZE_INHALE, 1.2F, 0.1F);
        entity.getNavigation().stop();
    }

    protected void stop(ServerLevel level, SquealingSpiderEntity entity, long gameTime) {
        if (entity.getPose() == Pose.SHOOTING)
            entity.setPose(Pose.STANDING);

        entity.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT_COOLDOWN, Unit.INSTANCE, SHOOT_COOLDOWN_TICKS);
        entity.getBrain().eraseMemory(MemoryModuleType.BREEZE_SHOOT);
        BlockPos blockpos = LongLeaping.snapToSurface(entity, randomPointBehindSpider(entity, entity.getRandom()));
        if (blockpos != null) {
            entity.setPose(Pose.INHALING);
            entity.setPose(Pose.LONG_JUMPING);
            entity.setYRot(entity.yBodyRot);
            entity.setDiscardFriction(true);
            Optional<Vec3> vec3 = calculateOptimalJumpVector(entity, entity.getRandom(), blockpos.getCenter());
            vec3.ifPresent(entity::setDeltaMovement);
        }
    }

    private Optional<Vec3> calculateOptimalJumpVector(SquealingSpiderEntity spider, RandomSource random, Vec3 target) {
        for (int i : Util.shuffledCopy(ALLOWED_ANGLES, random)) {
            Optional<Vec3> optional = LongJumpUtil.calculateJumpVectorForAngle(spider, target, 1.5F, i, false);
            if (optional.isPresent()) {
                return optional;
            }
        }
        return Optional.empty();
    }

    protected void tick(ServerLevel level, SquealingSpiderEntity spider, long gameTime) {
        Brain<SquealingSpiderEntity> brain = spider.getBrain();
        LivingEntity livingentity = brain.getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
        if (livingentity != null) {
            spider.lookAt(EntityAnchorArgument.Anchor.EYES, livingentity.position());
            if (brain.getMemory(MemoryModuleType.BREEZE_SHOOT_CHARGING).isEmpty() && brain.getMemory(MemoryModuleType.BREEZE_SHOOT_RECOVERING).isEmpty()) {
                brain.setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT_RECOVERING, Unit.INSTANCE, SHOOT_RECOVER_DELAY_TICKS);
                if (isFacingTarget(spider, livingentity)) {
                    level.playSound(spider, spider.getOnPos().above(), SoundEvents.WARDEN_SONIC_CHARGE, SoundSource.HOSTILE, 1.0F, 1.0F);
                    ArrayList<BlockPos> cobwebs = OnActionsTrigger.findBlocksInRegion(
                            level,
                            spider.getOnPos().offset(-16, -8, -16),
                            spider.getOnPos().offset(16, 8, 16),
                            (state) -> state != null && state.is(Blocks.COBWEB),
                            6);
                    if (!cobwebs.isEmpty() && Optional.ofNullable(cobwebs.getFirst()).isPresent()) {
                        BlockPos cobweb = cobwebs.getFirst();
                        if (!level.isClientSide()) {
                            level.destroyBlock(cobweb, false);
                            level.setBlockAndUpdate(livingentity.getOnPos().above(), Blocks.COBWEB.defaultBlockState());
                            level.playSound(null, cobweb.getX(), cobweb.getY(), cobweb.getZ(), SoundEvents.COBWEB_BREAK, SoundSource.NEUTRAL, 0.7F, 0.4F + level.getRandom().nextFloat() * 1.2F);
                        } else {
                            level.playLocalSound(cobweb, SoundEvents.COBWEB_BREAK, SoundSource.BLOCKS, 1, 0.3F, true);
                            level.addParticle(ParticleTypes.VAULT_CONNECTION, cobweb.getX() + 0.5f, cobweb.getY() + 0.5f, cobweb.getZ() + 0.5f, 0.0D, 1.0D, 0.0D);
                        }
                    }
                }
            }
        }
    }

    @VisibleForTesting
    public static boolean isFacingTarget(SquealingSpiderEntity spider, LivingEntity target) {
        Vec3 vec3 = spider.getViewVector(1.0F);
        Vec3 vec31 = target.position().subtract(spider.position()).normalize();
        return vec3.dot(vec31) > (double) 0.5F;
    }

    private static boolean isTargetWithinRange(SquealingSpiderEntity spider, LivingEntity target) {
        double d0 = spider.position().distanceToSqr(target.position());
        return d0 > (double) 4.0F && d0 < (double) 256.0F;
    }
}