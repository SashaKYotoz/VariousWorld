package net.sashakyotoz.variousworld.init;

import net.lcc.sollib.api.common.registry.holder.EntityHolder;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.sashakyotoz.variousworld.common.entities.CrystalicSlimeEntity;
import net.sashakyotoz.variousworld.common.entities.SquealingSpiderEntity;
import net.sashakyotoz.variousworld.common.entities.WanderingZombieEntity;
import net.sashakyotoz.variousworld.common.entities.boat.VWBoat;
import net.sashakyotoz.variousworld.common.entities.boat.VWChestBoat;

import java.util.List;

import static net.sashakyotoz.variousworld.init.VWRegistryHelper.entity;

public class VWEntities {
    public static void init() {
    }

    public static final EntityHolder CRYSTALIC_SLIME = entity("crystalic_slime",
            EntityType.Builder.of(CrystalicSlimeEntity::new, MobCategory.MONSTER)
                    .sized(0.52F, 0.52F).eyeHeight(0.325F).spawnDimensionsScale(3.0F).clientTrackingRange(10))
            .withTags(EntityTypeTags.FROG_FOOD)
            .withAttributes(Monster::createMonsterAttributes)
            .<CrystalicSlimeEntity>withSpawnRestrictions(SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    CrystalicSlimeEntity::checkCrystalicSlimeSpawnRules);
    public static final EntityHolder WANDERING_ZOMBIE = entity("wandering_zombie",
            EntityType.Builder.of(WanderingZombieEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F).clientTrackingRange(8))
            .withTags(EntityTypeTags.ZOMBIES, EntityTypeTags.UNDEAD)
            .withAttributes(WanderingZombieEntity::createAttributes)
            .<WanderingZombieEntity>withSpawnRestrictions(SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules)
            .withSpawn(List.of("#minecraft:stronghold_biased_to"), MobCategory.MONSTER, 5, 1, 2);
    public static final EntityHolder SQUEALING_SPIDER = entity("squealing_spider",
            EntityType.Builder.of(SquealingSpiderEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 0.6F).passengerAttachments(1F).clientTrackingRange(8))
            .withTags(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS, EntityTypeTags.ARTHROPOD)
            .withAttributes(SquealingSpiderEntity::createAttributes)
            .<SquealingSpiderEntity>withSpawnRestrictions(SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    SquealingSpiderEntity::checkSquealingSpiderSpawnRules);
    public static final EntityHolder BOAT = entity("boat",
            EntityType.Builder.<VWBoat>of(VWBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityHolder CHEST_BOAT = entity("chest_boat",
            EntityType.Builder.<VWChestBoat>of(VWChestBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
}
