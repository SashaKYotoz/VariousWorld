package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, VariousWorld.MOD_ID);
    public static final DeferredHolder<SoundEvent, SoundEvent> BIOME_SCULK_VALLEY = SOUND_EVENTS.register("biome.sculk_valley",
            () -> SoundEvent.createVariableRangeEvent(VariousWorld.createVWLocation("biome.sculk_valley")));
    public static final DeferredHolder<SoundEvent, SoundEvent> WANDERING_ZOMBIE_AMBIENT = SOUND_EVENTS.register("entity.wandering_zombie_ambient",
            () -> SoundEvent.createVariableRangeEvent(VariousWorld.createVWLocation("entity.wandering_zombie_ambient")));
}