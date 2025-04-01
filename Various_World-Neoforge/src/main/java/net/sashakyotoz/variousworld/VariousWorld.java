package net.sashakyotoz.variousworld;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.sashakyotoz.variousworld.init.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mod("various_world")
public class VariousWorld {
    public static final Logger LOGGER = LogManager.getLogger(VariousWorld.class);
    public static final String MOD_ID = "various_world";

    public VariousWorld(IEventBus bus) {
        VWRegistryHelper.register(bus);
        VWSounds.init();
        VWBlocks.init();
        VWItems.init();
        VWEntities.ENTITIES.register(bus);
        VWMiscRegistries.register(bus);
        VWTabs.CREATIVE_MODE_TABS.register(bus);
        VWFeatures.REGISTRY.register(bus);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VariousWorldConfig.SPEC);
//        bus.addListener(this::commonSetup);
//        bus.addListener(this::clientSetup);
    }

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }
    public static ResourceLocation createVWLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
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

    private void commonSetup(final FMLCommonSetupEvent event) {
        VWVillagerType villagerType = new VWVillagerType();
        villagerType.initVillagerTypes();
    }
}
