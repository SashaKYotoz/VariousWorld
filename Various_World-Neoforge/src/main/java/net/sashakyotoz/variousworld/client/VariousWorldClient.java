package net.sashakyotoz.variousworld.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.blocks.ModWoodType;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VariousWorldClient {
    private static final ModelLayerLocation CRYSTALIC_OAK_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, "boat/crystalic_oak"), "main");
    private static final ModelLayerLocation CRYSTALIC_OAK_CHEST_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, "chest_boat/crystalic_oak"), "main");
    private static final ModelLayerLocation MAGNOLIA_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, "boat/magnolia"), "main");
    private static final ModelLayerLocation MAGNOLIA_CHEST_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, "chest_boat/magnolia"), "main");

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(VWBlocks.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(VWBlocks.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
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

    @SubscribeEvent
    public static void clientSetUp(FMLClientSetupEvent event) {
        for (DeferredBlock block : VWRegistryHelper.BLOCK_CUTOUT)
            ItemBlockRenderTypes.setRenderLayer((Block) block.get(), RenderType.cutout());
        for (DeferredBlock block : VWRegistryHelper.BLOCK_TRANSLUCENT)
            ItemBlockRenderTypes.setRenderLayer((Block) block.get(), RenderType.translucent());
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.CRYSTALIC_OAK);
        });
    }
}
