package net.sashakyotoz.variousworld.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWSounds {
    public static final SoundEvent BLOCK_STONY_MAGMA_BREAK = create("block.stony_magma.break");
    public static final SoundEvent BLOCK_STONY_MAGMA_STEP = create("block.stony_magma.step");
    public static final SoundEvent BLOCK_STONY_MAGMA_PLACE = create("block.stony_magma.place");
    public static final SoundEvent BIOME_SCULK_VALLEY = create("biome.sculk_valley");
    public static final SoundEvent BIOME_PEACEFUL_WASTELAND = create("biome.peaceful_wasteland");
    public static final SoundEvent ENTITY_VARIOUS_ZOMBIE_AMBIENT = create("entity.various_zombie_ambient");
    public static final SoundType STONY_MAGMA = new DeferredSoundType(1.0F, 1.0F,
            () -> BLOCK_STONY_MAGMA_BREAK,
            () -> BLOCK_STONY_MAGMA_STEP,
            () -> BLOCK_STONY_MAGMA_PLACE,
            () -> SoundEvents.DEEPSLATE_HIT,
            () -> SoundEvents.EMPTY);

    public static void init() {
    }

    private static SoundEvent create(String name) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, name);
        SoundEvent sound = SoundEvent.createVariableRangeEvent(location);
//        BuiltInRegistries.SOUND_EVENT.register(location, sound);
        return sound;
    }
}