package net.sashakyotoz.variousworld.common.entities.brain.behavior;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;

import java.util.Map;

public class EchoingWhenStuck extends Behavior<SquealingSpiderEntity> {
    public EchoingWhenStuck() {
        super(Map.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.BREEZE_JUMP_INHALING, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_JUMP_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.BREEZE_SHOOT, MemoryStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(ServerLevel level, SquealingSpiderEntity owner) {
        return owner.isPassenger() || owner.isInWater() || owner.getEffect(MobEffects.LEVITATION) != null;
    }

    protected boolean canStillUse(ServerLevel level, SquealingSpiderEntity spider, long gameTime) {
        return false;
    }

    protected void start(ServerLevel level, SquealingSpiderEntity spider, long gameTime) {
        spider.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT, Unit.INSTANCE, 80L);
    }
}
