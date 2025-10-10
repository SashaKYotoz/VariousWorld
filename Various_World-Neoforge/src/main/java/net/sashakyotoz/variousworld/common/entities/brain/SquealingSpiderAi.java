package net.sashakyotoz.variousworld.common.entities.brain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;
import net.sashakyotoz.variousworld.common.entities.brain.behavior.Echoing;
import net.sashakyotoz.variousworld.common.entities.brain.behavior.EchoingWhenStuck;
import net.sashakyotoz.variousworld.common.entities.brain.behavior.Leaping;
import net.sashakyotoz.variousworld.common.entities.brain.behavior.LongLeaping;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import java.util.List;
import java.util.Set;

public class SquealingSpiderAi {
    public static final List<SensorType<? extends Sensor<? super SquealingSpiderEntity>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, SensorType.NEAREST_PLAYERS, VWMiscRegistries.SQUEALING_SPIDER_ATTACK_SENSOR.get());
    public static final List<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_ATTACKABLE, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.RAM_TARGET, MemoryModuleType.BREEZE_JUMP_COOLDOWN, MemoryModuleType.BREEZE_JUMP_INHALING, MemoryModuleType.BREEZE_SHOOT, MemoryModuleType.BREEZE_SHOOT_CHARGING, MemoryModuleType.BREEZE_SHOOT_RECOVERING, MemoryModuleType.BREEZE_SHOOT_COOLDOWN, MemoryModuleType.BREEZE_JUMP_TARGET, MemoryModuleType.BREEZE_LEAVING_WATER, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.PATH);

    private static final UniformInt TIME_BETWEEN_LEAPING = UniformInt.of(400, 4000);

    public static Brain<?> makeBrain(SquealingSpiderEntity entity, Brain<SquealingSpiderEntity> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        initFightActivity(entity, brain);
        brain.setCoreActivities(Set.of(Activity.CORE));
        brain.setDefaultActivity(Activity.FIGHT);
        brain.useDefaultActivity();
        return brain;
    }

    public static void initMemories(SquealingSpiderEntity entity, RandomSource random) {
        entity.getBrain().setMemory(MemoryModuleType.RAM_COOLDOWN_TICKS, TIME_BETWEEN_LEAPING.sample(random));
    }

    private static void initCoreActivity(Brain<SquealingSpiderEntity> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(new Swim<>(0.8F), new LookAtTargetSink(45, 90), new CountDownCooldownTicks(MemoryModuleType.RAM_COOLDOWN_TICKS)));
    }

    private static void initIdleActivity(Brain<SquealingSpiderEntity> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, StartAttacking.create((serverLevel,mob) -> mob.getBrain().getMemory(MemoryModuleType.NEAREST_ATTACKABLE))),
                Pair.of(1, StartAttacking.create((serverLevel,mob) -> mob.getHurtBy())),
                Pair.of(2, new SlideToTargetSink(20, 40)),
                Pair.of(3, new RunOne(ImmutableList.of(Pair.of(new DoNothing(100, 200), 1),
                        Pair.of(RandomStroll.stroll(0.8F), 2))))));
    }

    private static void initFightActivity(SquealingSpiderEntity entity, Brain<SquealingSpiderEntity> brain) {
        brain.addActivityWithConditions(Activity.FIGHT,
                ImmutableList.of(
                        Pair.of(0, StopAttackingIfTargetInvalid.create(Sensor.wasEntityAttackableLastNTicks(entity, 100).negate()::test)),
                        Pair.of(1, new Echoing()),
                        Pair.of(2, new LongLeaping()),
                        Pair.of(3, new EchoingWhenStuck()),
                        Pair.of(4, new Leaping())),
                ImmutableSet.of(Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT)));
    }

    public static void updateActivity(SquealingSpiderEntity spider) {
        spider.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
    }

    public static class SlideToTargetSink extends MoveToTargetSink {
        public SlideToTargetSink(int i, int i1) {
            super(i, i1);
        }

        protected void start(ServerLevel level, Mob mob, long gameTime) {
            super.start(level, mob, gameTime);
            mob.playSound(SoundEvents.SPIDER_STEP);
            mob.setPose(Pose.SLIDING);
        }

        protected void stop(ServerLevel level, Mob mob, long gameTime) {
            super.stop(level, mob, gameTime);
            mob.setPose(Pose.STANDING);
            if (mob.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET))
                mob.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT, Unit.INSTANCE, 60L);
        }
    }
}