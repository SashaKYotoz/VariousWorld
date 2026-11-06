package net.sashakyotoz.variousworld;

import net.lcc.sollib.api.common.registry.SolModContainer;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.config.VWRuntimeData;
import net.sashakyotoz.variousworld.init.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VariousWorld {
    public static final Logger LOGGER = LogManager.getLogger(VariousWorld.class);
    public static final String MOD_ID = "various_world";
    public static final SolModContainer MOD = new SolModContainer("Various World", MOD_ID);

    public static void init() {
        ModConfigController.init();
        VWSounds.init();
        VWMiscRegistries.init();
        VWFeatures.init();
        VWBlocks.init();
        VWEntities.init();
        VWItems.init();
        VWVillagers.init();
        VWCreativeTabs.init();
        VWTags.init();
        VWRuntimeData.register();
    }

    public static ResourceLocation createVWLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}