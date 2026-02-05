package net.sashakyotoz.variousworld.common.entities.brain.behavior;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.util.Util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.LongJumpUtil;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class LongLeaping extends Behavior<SquealingSpiderEntity> {
    private static final int INHALING_DURATION_TICKS = Math.round(10.0F);
    private static final ObjectArrayList<Integer> ALLOWED_ANGLES = new ObjectArrayList<>(Lists.newArrayList(40, 55, 60, 75, 80));

    @VisibleForTesting
    public LongLeaping() {
        super(Map.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.BREEZE_JUMP_COOLDOWN, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_JUMP_INHALING, MemoryStatus.REGISTERED, MemoryModuleType.BREEZE_JUMP_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.BREEZE_SHOOT, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_LEAVING_WATER, MemoryStatus.REGISTERED), 200);
    }

    public static boolean canRun(ServerLevel level, SquealingSpiderEntity spider) {
        if (!spider.onGround() && !spider.isInWater()) {
            return false;
        } else if (Swim.shouldSwim(spider)) {
            return false;
        } else if (spider.getBrain().checkMemory(MemoryModuleType.BREEZE_JUMP_TARGET, MemoryStatus.VALUE_PRESENT)) {
            return true;
        } else {
            LivingEntity livingentity = spider.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
            if (livingentity == null) {
                return false;
            } else if (outOfAggroRange(spider, livingentity)) {
                spider.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
                return false;
            } else if (tooCloseForJump(spider, livingentity)) {
                return false;
            } else if (!canJumpFromCurrentPosition(level, spider)) {
                return false;
            } else {
                BlockPos blockpos = snapToSurface(spider, randomPointBehindTarget(livingentity, spider.getRandom()));
                if (blockpos == null) {
                    return false;
                } else {
                    BlockState blockstate = level.getBlockState(blockpos.below());
                    if (spider.getType().isBlockDangerous(blockstate)) {
                        return false;
                    } else if (!hasLineOfSight(spider, blockpos.getCenter()) && !hasLineOfSight(spider, blockpos.above(4).getCenter())) {
                        return false;
                    } else {
                        spider.getBrain().setMemory(MemoryModuleType.BREEZE_JUMP_TARGET, blockpos);
                        return true;
                    }
                }
            }
        }
    }
    static Vec3 randomPointBehindTarget(LivingEntity target, RandomSource random) {
        float f = target.yHeadRot + 180.0F + (float)random.nextGaussian() * 90.0F / 2.0F;
        float f1 = Mth.lerp(random.nextFloat(), 4.0F, 8.0F);
        Vec3 vec3 = Vec3.directionFromRotation(0.0F, f).scale(f1);
        return target.position().add(vec3);
    }

    static boolean hasLineOfSight(SquealingSpiderEntity spider, Vec3 pos) {
        Vec3 vec3 = new Vec3(spider.getX(), spider.getY(), spider.getZ());
        return !(pos.distanceTo(vec3) > (double) 50.0F) && spider.level().clip(new ClipContext(vec3, pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, spider)).getType() == HitResult.Type.MISS;
    }

    protected boolean checkExtraStartConditions(ServerLevel level, SquealingSpiderEntity owner) {
        return canRun(level, owner);
    }

    protected boolean canStillUse(ServerLevel level, SquealingSpiderEntity entity, long gameTime) {
        return entity.getPose() != Pose.STANDING && !entity.getBrain().hasMemoryValue(MemoryModuleType.BREEZE_JUMP_COOLDOWN);
    }

    protected void start(ServerLevel level, SquealingSpiderEntity entity, long gameTime) {
        if (entity.getBrain().checkMemory(MemoryModuleType.BREEZE_JUMP_INHALING, MemoryStatus.VALUE_ABSENT)) {
            entity.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_JUMP_INHALING, Unit.INSTANCE, INHALING_DURATION_TICKS);
        }

        entity.setPose(Pose.INHALING);
        level.playSound(null, entity, SoundEvents.BREEZE_CHARGE, SoundSource.HOSTILE, 1.0F, 1.0F);
        entity.getBrain().getMemory(MemoryModuleType.BREEZE_JUMP_TARGET).ifPresent((p_312818_) -> entity.lookAt(EntityAnchorArgument.Anchor.EYES, p_312818_.getCenter()));
    }

    protected void tick(ServerLevel level, SquealingSpiderEntity owner, long gameTime) {
        boolean flag = owner.isInWater();
        if (!flag && owner.getBrain().checkMemory(MemoryModuleType.BREEZE_LEAVING_WATER, MemoryStatus.VALUE_PRESENT)) {
            owner.getBrain().eraseMemory(MemoryModuleType.BREEZE_LEAVING_WATER);
        }

        if (isFinishedInhaling(owner)) {
            Vec3 vec3 = owner.getBrain().getMemory(MemoryModuleType.BREEZE_JUMP_TARGET).flatMap((target) -> calculateOptimalJumpVector(owner, owner.getRandom(), Vec3.atBottomCenterOf(target))).orElse(null);
            if (vec3 == null) {
                owner.setPose(Pose.STANDING);
                return;
            }

            if (flag) {
                owner.getBrain().setMemory(MemoryModuleType.BREEZE_LEAVING_WATER, Unit.INSTANCE);
            }

            owner.playSound(SoundEvents.BREEZE_JUMP, 1.0F, 1.0F);
            owner.setPose(Pose.LONG_JUMPING);
            owner.setYRot(owner.yBodyRot);
            owner.setDiscardFriction(true);
            owner.setDeltaMovement(vec3);
        } else if (isFinishedJumping(owner)) {
            owner.playSound(SoundEvents.BREEZE_LAND, 1.0F, 1.0F);
            owner.setPose(Pose.STANDING);
            owner.setDiscardFriction(false);
            boolean flag1 = owner.getBrain().hasMemoryValue(MemoryModuleType.HURT_BY);
            owner.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_JUMP_COOLDOWN, Unit.INSTANCE, flag1 ? 2L : 10L);
            owner.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT, Unit.INSTANCE, 100L);
        }

    }

    protected void stop(ServerLevel level, SquealingSpiderEntity spider, long gameTime) {
        if (spider.getPose() == Pose.LONG_JUMPING || spider.getPose() == Pose.INHALING) {
            spider.setPose(Pose.STANDING);
        }

        spider.getBrain().eraseMemory(MemoryModuleType.BREEZE_JUMP_TARGET);
        spider.getBrain().eraseMemory(MemoryModuleType.BREEZE_JUMP_INHALING);
        spider.getBrain().eraseMemory(MemoryModuleType.BREEZE_LEAVING_WATER);
    }

    private static boolean isFinishedInhaling(SquealingSpiderEntity breeze) {
        return breeze.getBrain().getMemory(MemoryModuleType.BREEZE_JUMP_INHALING).isEmpty() && breeze.getPose() == Pose.INHALING;
    }

    private static boolean isFinishedJumping(SquealingSpiderEntity breeze) {
        boolean flag = breeze.getPose() == Pose.LONG_JUMPING;
        boolean flag1 = breeze.onGround();
        boolean flag2 = breeze.isInWater() && breeze.getBrain().checkMemory(MemoryModuleType.BREEZE_LEAVING_WATER, MemoryStatus.VALUE_ABSENT);
        return flag && (flag1 || flag2);
    }

    @Nullable
    public static BlockPos snapToSurface(LivingEntity owner, Vec3 targetPos) {
        ClipContext clipcontext = new ClipContext(targetPos, targetPos.relative(Direction.DOWN, 10.0F), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, owner);
        HitResult hitresult = owner.level().clip(clipcontext);
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            return BlockPos.containing(hitresult.getLocation()).above();
        } else {
            ClipContext clipcontext1 = new ClipContext(targetPos, targetPos.relative(Direction.UP, 10.0F), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, owner);
            HitResult hitresult1 = owner.level().clip(clipcontext1);
            return hitresult1.getType() == HitResult.Type.BLOCK ? BlockPos.containing(hitresult1.getLocation()).above() : null;
        }
    }

    private static boolean outOfAggroRange(SquealingSpiderEntity spider, LivingEntity target) {
        return !target.closerThan(spider, 24.0F);
    }

    private static boolean tooCloseForJump(SquealingSpiderEntity spider, LivingEntity target) {
        return target.distanceTo(spider) - 4.0F <= 0.0F;
    }

    private static boolean canJumpFromCurrentPosition(ServerLevel level, SquealingSpiderEntity spider) {
        BlockPos blockpos = spider.blockPosition();

        for(int i = 1; i <= 4; ++i) {
            BlockPos blockpos1 = blockpos.relative(Direction.UP, i);
            if (!level.getBlockState(blockpos1).isAir() && !level.getFluidState(blockpos1).is(FluidTags.WATER)) {
                return false;
            }
        }

        return true;
    }

    private static Optional<Vec3> calculateOptimalJumpVector(SquealingSpiderEntity spider, RandomSource random, Vec3 target) {
        for(int i : Util.shuffledCopy(ALLOWED_ANGLES, random)) {
            Optional<Vec3> optional = LongJumpUtil.calculateJumpVectorForAngle(spider, target, 1.4F, i, false);
            if (optional.isPresent()) {
                return optional;
            }
        }

        return Optional.empty();
    }
}