package net.sashakyotoz.variousworld.init;

import com.google.common.collect.Sets;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.sashakyotoz.variousworld.VariousWorld;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
public class VariousWorldVillagerType {
    public static final Set<VillagerType> CUSTOM_TYPES;
    public static VillagerType CRYSTAL;

    public void initVillagerTypes() {
        VariousWorld.LOGGER.debug("Registering villager types: " + VillagerTrades.TRADES.size());
        CRYSTAL = registerType("crystalic_forest");
        putTypeToBiome(VariousWorldBiomes.CRYSTALIC_FOREST, CRYSTAL);
    }

    public static void putTypeToBiome(ResourceKey<Biome> biomeIn, VillagerType type) {
        try {
            Field byBiomeField = VillagerType.class.getDeclaredField("BY_BIOME");
            byBiomeField.setAccessible(true);
            Map<ResourceKey<Biome>, VillagerType> byBiome = (Map<ResourceKey<Biome>, VillagerType>) byBiomeField.get(null);
            byBiome.put(biomeIn, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResourceLocation locate(String key) {
        return new ResourceLocation("various_world", key);
    }

    public static VillagerType registerType(String key) {
        VillagerType type = Registry.register(BuiltInRegistries.VILLAGER_TYPE, locate(key), new VillagerType(key));
        CUSTOM_TYPES.add(type);
        return type;
    }

    static {
        CUSTOM_TYPES = Sets.newHashSet();
    }
}
