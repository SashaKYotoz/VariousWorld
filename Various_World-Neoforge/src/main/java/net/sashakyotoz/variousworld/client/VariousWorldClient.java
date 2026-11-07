package net.sashakyotoz.variousworld.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lcc.sollib.api.client.SolClientRegistries;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.sashakyotoz.variousworld.client.models.CrystalicSlimeModel;
import net.sashakyotoz.variousworld.client.models.SquealingSpiderModel;
import net.sashakyotoz.variousworld.client.models.WanderingZombieModel;
import net.sashakyotoz.variousworld.client.renderers.CrystalicSlimeRenderer;
import net.sashakyotoz.variousworld.client.renderers.SquealingSpiderRenderer;
import net.sashakyotoz.variousworld.client.renderers.VWBoatRenderer;
import net.sashakyotoz.variousworld.client.renderers.WanderingZombieRenderer;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.ArtifactTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.DisassemblyTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.render.ArtifactTableBlockEntityRenderer;
import net.sashakyotoz.variousworld.common.blocks.entities.render.DisassemblyTableBlockEntityRenderer;
import net.sashakyotoz.variousworld.common.entities.boat.VWBoatType;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWEntities;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;
import net.sashakyotoz.variousworld.mixin.accessor.ItemRendererAccessor;

import java.util.function.BiConsumer;

public class VariousWorldClient {
    public interface BlockEntityRendererRegistrar {
        <T extends BlockEntity> void register(BlockEntityType<? extends T> type, BlockEntityRendererProvider<T> provider);
    }

    public interface MenuScreenRegistrar {
        <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(MenuType<? extends M> type, MenuScreens.ScreenConstructor<M, U> constructor);
    }

    public static void init() {
        VWEntities.CRYSTALIC_SLIME.addRenderer(CrystalicSlimeRenderer::new)
                .addModelLayer(CrystalicSlimeModel.LAYER_LOCATION, CrystalicSlimeModel::createInnerBodyLayer)
                .addModelLayer(CrystalicSlimeModel.OUTER_LAYER_LOCATION, CrystalicSlimeModel::createOuterBodyLayer);
        VWEntities.WANDERING_ZOMBIE.addRenderer(WanderingZombieRenderer::new)
                .addModelLayer(WanderingZombieModel.LAYER_LOCATION, WanderingZombieModel::createBodyLayer);
        VWEntities.SQUEALING_SPIDER.addRenderer(SquealingSpiderRenderer::new)
                .addModelLayer(SquealingSpiderModel.LAYER_LOCATION, SquealingSpiderModel::createBodyLayer);
        VWEntities.BOAT.<Boat>addRenderer(context -> new VWBoatRenderer(context, false));
        VWEntities.CHEST_BOAT.<Boat>addRenderer(context -> new VWBoatRenderer(context, true));
        for (VWBoatType type : VWBoatType.values()) {
            VWEntities.BOAT.addModelLayer(VWBoatRenderer.layer(type, false), BoatModel::createBodyModel);
            VWEntities.CHEST_BOAT.addModelLayer(VWBoatRenderer.layer(type, true), ChestBoatModel::createBodyModel);
        }

        SolClientRegistries.Render.BLOCK.register(BlockUtils::isReclamited,
                (renderer, state, pos, getter, random, poseStack, consumer) -> renderer.accept(pos, BlockUtils.getReclamiteEquivalent(state)));
        SolClientRegistries.Render.ITEM.register(VariousWorldClient::hasActiveCrystal, VariousWorldClient::renderCrystal);
    }

    public static void registerBlockEntityRenderers(BlockEntityRendererRegistrar registrar) {
        registrar.register(VWBlocks.MOD_SIGN.get(), SignRenderer::new);
        registrar.register(VWBlocks.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        registrar.register(VWBlocks.ARTIFACT_TABLE_BE.get(), ArtifactTableBlockEntityRenderer::new);
        registrar.register(VWBlocks.DISASSEMBLY_TABLE_BE.get(), DisassemblyTableBlockEntityRenderer::new);
    }

    public static void registerScreens(MenuScreenRegistrar registrar) {
        registrar.register(VWMiscRegistries.GEMSMITH_TABLE.get(), GemsmithTableScreen::new);
        registrar.register(VWMiscRegistries.ARTIFACT_TABLE.get(), ArtifactTableScreen::new);
        registrar.register(VWMiscRegistries.DISASSEMBLY_TABLE.get(), DisassemblyTableScreen::new);
    }

    public static void registerRenderLayers(BiConsumer<Block, RenderType> registrar) {
        VWRegistryHelper.forEach(BlockHolder.class, holder -> {
            if (holder.isCutout())
                registrar.accept(holder.get(), RenderType.cutout());
        });
        for (BlockHolder holder : VWRegistryHelper.TRANSLUCENT)
            registrar.accept(holder.get(), RenderType.translucent());
    }

    private static boolean hasActiveCrystal(ItemStack stack) {
        CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
        return data != null && data.crystalDurability() > 0;
    }

    private static void renderCrystal(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource,
                                      int light, int overlay) {
        CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
        if (data == null)
            return;
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel model = itemRenderer.getModel(data.crystalStack(), null, null, displayContext.ordinal());
        poseStack.scale(1.001f, 1.001f, 1.001f);
        ((ItemRendererAccessor) itemRenderer).variousworld$renderModelLists(model, stack, light, overlay, poseStack, bufferSource.getBuffer(RenderType.cutout()));
    }
}
