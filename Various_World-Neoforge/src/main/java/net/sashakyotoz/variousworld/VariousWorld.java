package net.sashakyotoz.variousworld;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.sashakyotoz.variousworld.init.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("various_world")
public class VariousWorld {
    public static final Logger LOGGER = LogManager.getLogger(VariousWorld.class);
    public static final String MOD_ID = "various_world";

    public VariousWorld(IEventBus bus, ModContainer container) {
        VWRegistryHelper.register(bus);
        VWSounds.init();
        VWBlocks.init();
        VWItems.init();
        VWEntities.ENTITIES.register(bus);
        VWMiscRegistries.register(bus);
        VWTabs.CREATIVE_MODE_TABS.register(bus);
        VWFeatures.register(bus);

        container.registerConfig(ModConfig.Type.COMMON, VariousWorldConfig.SPEC);
    }

    public static ResourceLocation createVWLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
