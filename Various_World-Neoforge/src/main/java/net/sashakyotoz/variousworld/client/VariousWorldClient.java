package net.sashakyotoz.variousworld.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.sashakyotoz.variousworld.VariousWorld;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VariousWorldClient {
    private static final ModelLayerLocation CRYSTALIC_OAK_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"boat/crystalic_oak"),"main");
    private static final ModelLayerLocation CRYSTALIC_OAK_CHEST_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"chest_boat/crystalic_oak"),"main");
    private static final ModelLayerLocation MAGNOLIA_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"boat/magnolia"),"main");
    private static final ModelLayerLocation MAGNOLIA_CHEST_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,"chest_boat/magnolia"),"main");
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

    }
    @SubscribeEvent
    public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
    }

    @SubscribeEvent
    public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
    }
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
    }       

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.AddLayers event) {
    }
}
