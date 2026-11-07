package net.sashakyotoz.variousworld.common.entities.brain;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.NearestLivingEntitySensor;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.monster.Spider;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SpiderAttackEntitySensor extends NearestLivingEntitySensor<Spider> {

    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.copyOf(Iterables.concat(super.requires(), List.of(MemoryModuleType.NEAREST_ATTACKABLE)));
    }

    protected void doTick(ServerLevel level, Spider spider) {
        super.doTick(level, spider);
        spider.getBrain().getMemory(MemoryModuleType.NEAREST_LIVING_ENTITIES)
                .stream().flatMap(Collection::stream)
                .filter(EntitySelector.NO_CREATIVE_OR_SPECTATOR)
                .filter(EntitySelector.ENTITY_NOT_BEING_RIDDEN)
                .filter((entity) -> !entity.getType().is(EntityTypeTags.ARTHROPOD))
                .filter((attackable) -> Sensor.isEntityAttackable(spider, attackable))
                .findFirst().ifPresentOrElse((living) ->
                                spider.getBrain().setMemory(MemoryModuleType.NEAREST_ATTACKABLE, living),
                        () -> spider.getBrain().eraseMemory(MemoryModuleType.NEAREST_ATTACKABLE));
    }

    protected int radiusXZ() {
        return 32;
    }

    protected int radiusY() {
        return 24;
    }
}