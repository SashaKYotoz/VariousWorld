package net.sashakyotoz.variousworld.fabric.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.lcc.sollib.api.common.registry.holder.EntityHolder;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.Map;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class SolLibFabricClientFallback {
    private SolLibFabricClientFallback() {
    }

    public static void register() {
        VariousWorld.LOGGER.info("SolLib client bootstrap ran before Various World client init, registering entity renderers directly");
        VWRegistryHelper.forEach(EntityHolder.class, holder -> {
            if (holder.hasRenderer())
                registerRenderer(holder);
            for (Map.Entry<ModelLayerLocation, Supplier<LayerDefinition>> entry : holder.getModelLayers())
                EntityModelLayerRegistry.registerModelLayer(entry.getKey(), () -> entry.getValue().get());
        });
    }

    @SuppressWarnings("unchecked")
    private static void registerRenderer(EntityHolder holder) {
        EntityRendererProvider<Entity> renderer = holder.getRenderer();
        EntityRendererRegistry.register((EntityType<Entity>) holder.get(), renderer);
    }
}
