package net.sashakyotoz.variousworld.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.client.models.CrystalicSlimeModel;
import net.sashakyotoz.variousworld.client.models.WanderingZombieModel;
import net.sashakyotoz.variousworld.client.renderers.CrystalicSlimeRenderer;
import net.sashakyotoz.variousworld.client.renderers.WanderingZombieRenderer;
import net.sashakyotoz.variousworld.common.blocks.ModWoodType;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.ArtifactTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.render.ArtifactTableBlockEntityRenderer;
import net.sashakyotoz.variousworld.common.config.ConfiguredData;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWEntities;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

@EventBusSubscriber(value = Dist.CLIENT)
public class VariousWorldClient {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(VWBlocks.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(VWBlocks.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        event.registerBlockEntityRenderer(VWBlocks.ARTIFACT_TABLE_BE.get(), ArtifactTableBlockEntityRenderer::new);

        event.registerEntityRenderer(VWEntities.CRYSTALIC_SLIME.get(), CrystalicSlimeRenderer::new);
        event.registerEntityRenderer(VWEntities.WANDERING_ZOMBIE.get(), WanderingZombieRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(VWMiscRegistries.GEMSMITH_TABLE.get(), GemsmithTableScreen::new);
        event.register(VWMiscRegistries.ARTIFACT_TABLE.get(), ArtifactTableScreen::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CrystalicSlimeModel.LAYER_LOCATION, CrystalicSlimeModel::createInnerBodyLayer);
        event.registerLayerDefinition(CrystalicSlimeModel.OUTER_LAYER_LOCATION, CrystalicSlimeModel::createOuterBodyLayer);
        event.registerLayerDefinition(WanderingZombieModel.LAYER_LOCATION, WanderingZombieModel::createBodyLayer);
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
            Sheets.addWoodType(ModWoodType.BLUE_JACARANDA);
            VWItemProperties.init();
        });
    }
}
