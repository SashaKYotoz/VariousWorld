package net.sashakyotoz.variousworld.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.lcc.sollib.api.common.registry.data.entity.SpawnRestrictions;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.lcc.sollib.api.common.registry.holder.EntityHolder;
import net.lcc.sollib.api.common.registry.holder.ItemHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

public final class SolLibFabricFallback {
    private SolLibFabricFallback() {
    }

    public static void registerCommon() {
        VariousWorld.LOGGER.info("SolLib bootstrap ran before Various World init, applying Various World holder data directly");
        VWRegistryHelper.forEach(ItemHolder.class, holder -> {
            if (holder.isFuel())
                FuelRegistry.INSTANCE.add(holder.get(), holder.getFuelDuration());
        });
        VWRegistryHelper.forEach(BlockHolder.class, holder -> {
            if (holder.hasStripResult())
                StrippableBlockRegistry.register(holder.get(), holder.getStripResult().get());
            if (holder.isFlammable())
                FlammableBlockRegistry.getDefaultInstance().add(holder.get(), holder.getFlammability().ignite(), holder.getFlammability().spread());
        });
        VWRegistryHelper.forEach(EntityHolder.class, holder -> {
            if (holder.hasAttributes())
                registerAttributes(holder);
            if (holder.hasSpawnRestrictions())
                registerSpawnPlacement(holder);
            if (holder.shouldSpawn())
                BiomeModifications.addSpawn(context -> holder.getSpawn().matchesBiome(key -> context.getBiomeKey().equals(key), context::hasTag),
                        holder.getSpawn().category(), holder.get(), holder.getSpawn().weight(), holder.getSpawn().min(), holder.getSpawn().max());
        });
    }

    @SuppressWarnings("unchecked")
    private static void registerAttributes(EntityHolder holder) {
        FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) holder.get(), holder.getAttributes());
    }

    @SuppressWarnings("unchecked")
    private static void registerSpawnPlacement(EntityHolder holder) {
        SpawnRestrictions<Mob> restrictions = holder.getSpawnRestrictions();
        SpawnPlacements.register((EntityType<Mob>) holder.get(), restrictions.location(), restrictions.heightmap(), restrictions.predicate());
    }
}
