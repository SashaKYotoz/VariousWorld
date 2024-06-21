package net.sashakyotoz.variousworld;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.sashakyotoz.variousworld.block.signs.ModWoodType;
import net.sashakyotoz.variousworld.client.gui.ArchofgemsScreen;
import net.sashakyotoz.variousworld.client.gui.ArmorStationScreen;
import net.sashakyotoz.variousworld.client.gui.DisenchantTableGUIScreen;
import net.sashakyotoz.variousworld.client.gui.MycolocyfarographGUIScreen;
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
public class VariousWorld {
    public static final Logger LOGGER = LogManager.getLogger(VariousWorld.class);
    public static final String MODID = "various_world";

    public VariousWorld() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        VariousWorldSounds.init();
        VariousWorldBlocks.BLOCKS.register(bus);
        VariousWorldItems.ITEMS.register(bus);
        VariousWorldEntities.ENTITIES.register(bus);
        VariousWorldBlockEntities.BLOCK_ENTITIES.register(bus);
        VariousWorldEnchantments.ENCHANTMENTS.register(bus);
        VariousWorldPaintings.PAINTINGS.register(bus);
        VariousWorldParticleTypes.REGISTRY.register(bus);
        VariousWorldMobEffects.REGISTRY.register(bus);
        VariousWorldTabs.CREATIVE_MODE_TABS.register(bus);
        VariousWorldMenus.REGISTRY.register(bus);
        VariousWorldFeatures.REGISTRY.register(bus);
        VariousWorldVillagerProfessions.PROFESSIONS.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,VariousWorldConfig.SPEC);
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
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
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
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
    private void clientSetup(final FMLClientSetupEvent event){
        VariousWorldItemProperties.addCustomItemProperties();
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.CRYSTALIC_OAK);
            Sheets.addWoodType(ModWoodType.SCULK);
            Sheets.addWoodType(ModWoodType.MAGNOLIA);
            MenuScreens.register(VariousWorldMenus.ARCH_OF_GEMS.get(), ArchofgemsScreen::new);
            MenuScreens.register(VariousWorldMenus.ARMOR_STATION.get(), ArmorStationScreen::new);
            MenuScreens.register(VariousWorldMenus.DISENCHANT_TABLE_GUI.get(), DisenchantTableGUIScreen::new);
            MenuScreens.register(VariousWorldMenus.MYCOLOCYFAROGRAPH_GUI.get(), MycolocyfarographGUIScreen::new);
        });
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        VariousWorldVillagerType villagerType = new VariousWorldVillagerType();
        villagerType.initVillagerTypes();
    }
}
