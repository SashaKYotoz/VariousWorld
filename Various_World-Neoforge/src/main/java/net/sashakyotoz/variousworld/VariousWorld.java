package net.sashakyotoz.variousworld;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.sashakyotoz.variousworld.block.signs.ModWoodType;
import net.sashakyotoz.variousworld.client.gui.ArchOfGemsScreen;
import net.sashakyotoz.variousworld.client.gui.ArmorStationScreen;
import net.sashakyotoz.variousworld.client.gui.DisenchantTableGUIScreen;
import net.sashakyotoz.variousworld.client.gui.MycolocyfarographGUIScreen;
import net.sashakyotoz.variousworld.init.*;
import net.sashakyotoz.variousworld.recipes.brewing.PotionOfDragonEyeCraftBrewingRecipe;
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

    public VariousWorld(IEventBus bus) {
        VWSounds.init();
        VWBlocks.BLOCKS.register(bus);
        VWItems.ITEMS.register(bus);
        VWEntities.ENTITIES.register(bus);
        VWBlockEntities.BLOCK_ENTITIES.register(bus);
        VWMiscRegistries.register(bus);
        VWTabs.CREATIVE_MODE_TABS.register(bus);
        VWMenus.REGISTRY.register(bus);
        VWFeatures.REGISTRY.register(bus);
        VWVillagerProfessions.PROFESSIONS.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VariousWorldConfig.SPEC);
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
    public void tick(ServerTickEvent.Post event) {
        List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getKey().run());
        workQueue.removeAll(actions);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        VWItemProperties.addCustomItemProperties();
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodType.CRYSTALIC_OAK);
            Sheets.addWoodType(ModWoodType.SCULK);
            Sheets.addWoodType(ModWoodType.MAGNOLIA);
            MenuScreens.register(VWMenus.ARCH_OF_GEMS.get(), ArchOfGemsScreen::new);
            MenuScreens.register(VWMenus.ARMOR_STATION.get(), ArmorStationScreen::new);
            MenuScreens.register(VWMenus.DISENCHANT_TABLE_GUI.get(), DisenchantTableGUIScreen::new);
            MenuScreens.register(VWMenus.MYCOLOCYFAROGRAPH_GUI.get(), MycolocyfarographGUIScreen::new);
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        VWVillagerType villagerType = new VWVillagerType();
        villagerType.initVillagerTypes();
        BrewingRecipeRegistry.addRecipe(new PotionOfDragonEyeCraftBrewingRecipe());
    }
}
