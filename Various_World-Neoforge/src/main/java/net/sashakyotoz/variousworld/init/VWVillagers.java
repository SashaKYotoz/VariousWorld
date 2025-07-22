package net.sashakyotoz.variousworld.init;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;

import java.lang.reflect.Field;
import java.util.Map;

public class VWVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, VariousWorld.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, VariousWorld.MOD_ID);
    public static final DeferredRegister<VillagerType> VILLAGER_TYPES =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_TYPE, VariousWorld.MOD_ID);

    public static final Holder<PoiType> GEMSMITHER_POI = POI_TYPES.register("gemsmither_poi",
            () -> new PoiType(ImmutableSet.copyOf(VWBlocks.GEMSMITH_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final Holder<PoiType> ARTIFACTOLOGIST_POI = POI_TYPES.register("artifactologist_poi",
            () -> new PoiType(ImmutableSet.copyOf(VWBlocks.ARTIFACT_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final Holder<VillagerProfession> GEMSMITHER = VILLAGER_PROFESSIONS.register("gemsmither",
            () -> new VillagerProfession(Component.literal("gemsmither"), holder -> holder.value() == GEMSMITHER_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == GEMSMITHER_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.AMETHYST_BLOCK_CHIME));
    public static final Holder<VillagerProfession> ARTIFACTOLOGIST = VILLAGER_PROFESSIONS.register("artifactologist",
            () -> new VillagerProfession(Component.literal("artifactologist"), holder -> holder.value() == ARTIFACTOLOGIST_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == ARTIFACTOLOGIST_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static final Holder<VillagerType> CRYSTALLINE = VILLAGER_TYPES.register("crystalline", VillagerType::new);

    public static void putTypeToBiome(ResourceKey<Biome> biomeIn, VillagerType type) {
        try {
            Field byBiomeField = VillagerType.class.getDeclaredField("BY_BIOME");
            byBiomeField.setAccessible(true);
            Map<ResourceKey<Biome>, VillagerType> byBiome = (Map<ResourceKey<Biome>, VillagerType>) byBiomeField.get(null);
            byBiome.put(biomeIn, type);
        } catch (Exception e) {
            VariousWorld.LOGGER.error("Failed to register villager type: {}", type);
        }
    }

    public static void register(IEventBus bus) {
        POI_TYPES.register(bus);
        VILLAGER_PROFESSIONS.register(bus);
        VILLAGER_TYPES.register(bus);
    }
}