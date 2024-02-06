
package net.sashakyotoz.variousworld;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.sashakyotoz.variousworld.client.gui.ArchofgemsScreen;
import net.sashakyotoz.variousworld.client.gui.ArmorStationScreen;
import net.sashakyotoz.variousworld.client.gui.DisenchantTableGUIScreen;
import net.sashakyotoz.variousworld.client.gui.MycolocyfarographGUIScreen;
import net.sashakyotoz.variousworld.client.renderer.CrystalWarriorRenderer;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;
import net.sashakyotoz.variousworld.client.renderer.layers.AmethystSpikesEffectLayer;
import net.sashakyotoz.variousworld.client.renderer.layers.AngelStarWingsLayer;
import net.sashakyotoz.variousworld.client.renderer.layers.ChainedEffectLayer;
import net.sashakyotoz.variousworld.init.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod("various_world")
public class VariousWorldMod {
    public static final Logger LOGGER = LogManager.getLogger(VariousWorldMod.class);
    public static final String MODID = "various_world";

    public VariousWorldMod() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        VariousWorldModSounds.init();
        VariousWorldModBlocks.REGISTRY.register(bus);
        VariousWorldModItems.REGISTRY.register(bus);
        VariousWorldModEntities.REGISTRY.register(bus);
        VariousWorldModBlockEntities.REGISTRY.register(bus);
        VariousWorldModEnchantments.REGISTRY.register(bus);
        VariousWorldModPaintings.REGISTRY.register(bus);
        VariousWorldModParticleTypes.REGISTRY.register(bus);
        VariousWorldModMobEffects.REGISTRY.register(bus);
        VariousWorldModTabs.CREATIVE_MODE_TABS.register(bus);
        VariousWorldModMenus.REGISTRY.register(bus);
        VariousWorldModFeatures.REGISTRY.register(bus);
        VariousWorldModVillagerProfessions.PROFESSIONS.register(bus);
        if (FMLEnvironment.dist.isClient()) {
            bus.addListener(this::registerLayer);
            bus.addListener(this::commonSetup);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void registerLayer(EntityRenderersEvent event) {
        if (event instanceof EntityRenderersEvent.AddLayers addLayersEvent) {
            EntityModelSet entityModels = addLayersEvent.getEntityModels();
            addLayersEvent.getSkins().forEach((s) -> {
                LivingEntityRenderer<? extends Player, ? extends EntityModel<? extends Player>> livingEntityRenderer = addLayersEvent.getSkin(s);
                if (livingEntityRenderer instanceof PlayerRenderer playerRenderer) {
                    playerRenderer.addLayer(new AngelStarWingsLayer<>(playerRenderer, entityModels));
                    playerRenderer.addLayer(new AmethystSpikesEffectLayer<>(playerRenderer, entityModels));
                    playerRenderer.addLayer(new ChainedEffectLayer<>(playerRenderer, entityModels));
                }
            });
            LivingEntityRenderer<CrystalWarriorEntity, ? extends EntityModel<CrystalWarriorEntity>> livingEntityRenderer = addLayersEvent.getRenderer(VariousWorldModEntities.CRYSTAL_WARRIOR.get());
            if (livingEntityRenderer instanceof CrystalWarriorRenderer armorStandRenderer) {
                armorStandRenderer.addLayer(new AmethystSpikesEffectLayer<>(armorStandRenderer, entityModels));
            }
        }
    }

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry(action, tick));
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
            workQueue.forEach(work -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0)
                    actions.add(work);
            });
            actions.forEach(e -> e.getKey().run());
            workQueue.removeAll(actions);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void commonSetup(final FMLCommonSetupEvent event) {
        VariousWorldModItemProperties.addCustomItemProperties();
        new VariousWorldVillagerType().initVillagerTypes();
		event.enqueueWork(() -> {
			MenuScreens.register(VariousWorldModMenus.ARCHOFGEMS.get(), ArchofgemsScreen::new);
			MenuScreens.register(VariousWorldModMenus.ARMOR_STATION.get(), ArmorStationScreen::new);
			MenuScreens.register(VariousWorldModMenus.DISENCHANT_TABLE_GUI.get(), DisenchantTableGUIScreen::new);
			MenuScreens.register(VariousWorldModMenus.MYCOLOCYFAROGRAPH_GUI.get(), MycolocyfarographGUIScreen::new);
		});
    }
}
