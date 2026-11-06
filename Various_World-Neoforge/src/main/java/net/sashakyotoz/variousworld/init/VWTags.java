package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWTags {
    public static void init() {
    }

    public static class Items {
        public static final TagKey<Item> CRYSTALIC_OAK_LOGS = createTag("crystalic_oak_logs");
        public static final TagKey<Item> BLUE_JACARANDA_LOGS = createTag("blue_jacaranda_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, VariousWorld.createVWLocation(name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> COMPASS_FINDABLE_CLUSTERS = createTag("compass_findable_clusters");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, VariousWorld.createVWLocation(name));
        }
    }
}