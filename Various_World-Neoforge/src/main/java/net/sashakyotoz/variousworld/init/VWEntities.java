package net.sashakyotoz.variousworld.init;


import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.sashakyotoz.variousworld.common.entities.CrystalicSlimeEntity;

@SuppressWarnings("unchecked")
public class VWEntities {
    public static void init() {
    }

    public static final DeferredHolder<EntityType<?>, EntityType<CrystalicSlimeEntity>> CRYSTALIC_SLIME = VWRegistryHelper.ofEntity("crystalic_slime",
                    EntityType.Builder.of(CrystalicSlimeEntity::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F).eyeHeight(0.325F).spawnDimensionsScale(3.0F).clientTrackingRange(10))
            .drop(VWItems.CRYSTALLINE_SLIME_BALL).tag(EntityTypeTags.FROG_FOOD).build();
}