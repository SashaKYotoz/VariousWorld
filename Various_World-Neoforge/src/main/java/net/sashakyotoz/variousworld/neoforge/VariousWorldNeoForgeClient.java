package net.sashakyotoz.variousworld.neoforge;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.Sheets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.VWItemProperties;
import net.sashakyotoz.variousworld.client.VariousWorldClient;
import net.sashakyotoz.variousworld.common.blocks.ModWoodType;

@Mod(value = VariousWorld.MOD_ID, dist = Dist.CLIENT)
public class VariousWorldNeoForgeClient {
    public VariousWorldNeoForgeClient(IEventBus bus) {
        VariousWorldClient.init();
        bus.addListener(VariousWorldNeoForgeClient::clientSetup);
        bus.addListener(VariousWorldNeoForgeClient::registerRenderers);
        bus.addListener(VariousWorldNeoForgeClient::registerScreens);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        VariousWorldClient.registerRenderLayers(ItemBlockRenderTypes::setRenderLayer);
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.CRYSTALIC_OAK);
            Sheets.addWoodType(ModWoodType.BLUE_JACARANDA);
            VWItemProperties.init();
        });
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        VariousWorldClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer);
    }

    private static void registerScreens(RegisterMenuScreensEvent event) {
        VariousWorldClient.registerScreens(event::register);
    }
}
