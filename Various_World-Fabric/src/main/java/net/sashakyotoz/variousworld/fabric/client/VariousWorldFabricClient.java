package net.sashakyotoz.variousworld.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.sashakyotoz.variousworld.client.VWItemProperties;
import net.sashakyotoz.variousworld.client.VariousWorldClient;
import net.sashakyotoz.variousworld.common.blocks.ModWoodType;
import net.sashakyotoz.variousworld.fabric.FabricBootstrapState;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import java.util.List;

@Environment(EnvType.CLIENT)
public class VariousWorldFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        VariousWorldClient.init();
        if (FabricBootstrapState.registriesBootstrapped())
            SolLibFabricClientFallback.register();

        VariousWorldClient.registerRenderLayers(BlockRenderLayerMap.INSTANCE::putBlock);
        VariousWorldClient.registerBlockEntityRenderers(BlockEntityRenderers::register);
        VariousWorldClient.registerScreens(MenuScreens::register);
        VWItemProperties.init();
        registerSignMaterials();
        ParticleFactoryRegistry.getInstance().register(VWMiscRegistries.PEACEFUL_PARTICLE.get(), SuspendedTownParticle.HappyVillagerProvider::new);
    }

    private static void registerSignMaterials() {
        for (WoodType type : List.of(ModWoodType.CRYSTALIC_OAK, ModWoodType.BLUE_JACARANDA)) {
            Sheets.SIGN_MATERIALS.putIfAbsent(type,
                    new Material(Sheets.SIGN_SHEET, ResourceLocation.withDefaultNamespace("entity/signs/" + type.name())));
            Sheets.HANGING_SIGN_MATERIALS.putIfAbsent(type,
                    new Material(Sheets.SIGN_SHEET, ResourceLocation.withDefaultNamespace("entity/signs/hanging/" + type.name())));
        }
    }
}
