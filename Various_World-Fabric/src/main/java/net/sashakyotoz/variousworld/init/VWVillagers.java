package net.sashakyotoz.variousworld.init;

import com.google.common.collect.ImmutableSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.sashakyotoz.variousworld.init.holder.VWHolders;
import net.sashakyotoz.variousworld.mixin.accessor.VillagerTypeAccessor;

public class VWVillagers {
    public static void init() {
    }

    public static final VWHolders.PoiTypeHolder GEMSMITHER_POI = VWRegistryHelper.poi("gemsmither_poi",
            () -> new PoiType(ImmutableSet.copyOf(VWBlocks.GEMSMITH_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final VWHolders.PoiTypeHolder ARTIFACTOLOGIST_POI = VWRegistryHelper.poi("artifactologist_poi",
            () -> new PoiType(ImmutableSet.copyOf(VWBlocks.ARTIFACT_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final VWHolders.VillagerProfessionHolder GEMSMITHER = VWRegistryHelper.profession("gemsmither",
            () -> new VillagerProfession("gemsmither", holder -> holder.value() == GEMSMITHER_POI.get(),
                    holder -> holder.value() == GEMSMITHER_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.AMETHYST_BLOCK_CHIME));
    public static final VWHolders.VillagerProfessionHolder ARTIFACTOLOGIST = VWRegistryHelper.profession("artifactologist",
            () -> new VillagerProfession("artifactologist", holder -> holder.value() == ARTIFACTOLOGIST_POI.get(),
                    holder -> holder.value() == ARTIFACTOLOGIST_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static final VWHolders.VillagerTypeHolder CRYSTALLINE = VWRegistryHelper.villagerType("crystalline",
            () -> new VillagerType("crystalline"));

    public static void putTypeToBiome(ResourceKey<Biome> biome, VillagerType type) {
        VillagerTypeAccessor.variousworld$getByBiome().put(biome, type);
    }
}
