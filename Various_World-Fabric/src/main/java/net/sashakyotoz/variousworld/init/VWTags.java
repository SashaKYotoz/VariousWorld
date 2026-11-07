package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWTags {
    public static void init() {
    }

    public static class Items {
        public static final TagKey<Item> CRYSTALIC_OAK_LOGS = createTag("crystalic_oak_logs");
        public static final TagKey<Item> BLUE_JACARANDA_LOGS = createTag("blue_jacaranda_logs");
        public static final TagKey<Item> STRONG_ARTIFACT_CATALYSTS = createTag("artifact_catalysts/strong");
        public static final TagKey<Item> WEAK_ARTIFACT_CATALYSTS = createTag("artifact_catalysts/weak");

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

    public static class Biomes {
        public static final TagKey<Biome> HAS_OLD_CASTLE_RUINS = createTag("has_old_castle_rules");

        private static TagKey<Biome> createTag(String name) {
            return TagKey.create(Registries.BIOME, VariousWorld.createVWLocation(name));
        }
    }
}