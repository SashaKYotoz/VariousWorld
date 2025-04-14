package net.sashakyotoz.variousworld.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWTags {
    public static void init() {
    }

    public static class Items {
        public static final TagKey<Item> CRYSTALIC_OAK_LOGS = createTag("crystalic_oak_logs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(VariousWorld.createVWLocation(name));
        }
    }

    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(VariousWorld.createVWLocation(name));
        }
    }
}