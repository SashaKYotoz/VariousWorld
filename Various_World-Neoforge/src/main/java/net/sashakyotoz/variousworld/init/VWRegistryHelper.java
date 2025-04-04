package net.sashakyotoz.variousworld.init;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class VWRegistryHelper {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VariousWorld.MOD_ID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VariousWorld.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, VariousWorld.MOD_ID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }

    public static class BlockBuilder {
        public BlockBuilder(String name, Supplier<? extends Block> block, boolean doubleBlock, boolean item) {
            this.name = name;
            this.block = doubleBlock ? registerDoubleBlock(name, block, item) : registerBlock(name, block, item);
        }

        public DeferredBlock build() {
            return this.block;
        }

        public String name;
        public DeferredBlock<?> block;

        public BlockBuilder drop() {
            return this.drop(this.block);
        }

        public BlockBuilder drop(ItemLike loot) {
            BLOCK_DROPS.putIfAbsent(this.block, loot);
            return this;
        }

        public BlockBuilder tag(TagKey<Block> tagname) {
            BLOCK_TAGS.putIfAbsent(tagname, new ArrayList<>());
            BLOCK_TAGS.get(tagname).add(() -> this.block.get());
            return this;
        }

        @SafeVarargs
        public final BlockBuilder tag(TagKey<Block>... tags) {
            for (TagKey<Block> tagname : tags) {
                this.tag(tagname);
            }
            return this;
        }

        public BlockBuilder tagitem(TagKey<Item> tagname) {
            ITEM_TAGS.putIfAbsent(tagname, new ArrayList<>());
            ITEM_TAGS.get(tagname).add(this.block.asItem());
            return this;
        }

        @SafeVarargs
        public final BlockBuilder tagitem(TagKey<Item>... tags) {
            for (TagKey<Item> tagname : tags) {
                this.tagitem(tagname);
            }
            return this;
        }

        public BlockBuilder tool(String tool_material) {
            String[] needed = tool_material.split("_");

            if (needed[0].equals("stone")) this.tag(BlockTags.NEEDS_STONE_TOOL);
            if (needed[0].equals("iron")) this.tag(BlockTags.NEEDS_IRON_TOOL);
            if (needed[0].equals("diamond")) this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

            if (needed[1].equals("pickaxe")) this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
            if (needed[1].equals("axe")) this.tag(BlockTags.MINEABLE_WITH_AXE);
            if (needed[1].equals("shovel")) this.tag(BlockTags.MINEABLE_WITH_SHOVEL);
            if (needed[1].equals("hoe")) this.tag(BlockTags.MINEABLE_WITH_HOE);
            if (needed[1].equals("sword")) this.tag(BlockTags.SWORD_EFFICIENT);

            return this;
        }

        public BlockBuilder model() {
            return this.model(Models.CUBE);
        }

        public BlockBuilder model(Models model) {
            BLOCK_MODELS.putIfAbsent(model, new ArrayList<>());
            BLOCK_MODELS.get(model).add(this.block);
            return this;
        }

//        public BlockBuilder itemModel(ModelTemplate model) {
//            ITEM_MODELS.put(this.block, model);
//            return this;
//        }

        public BlockBuilder cutout() {
            BLOCK_CUTOUT.add(this.block);
            return this;
        }

        public BlockBuilder translucent() {
            BLOCK_TRANSLUCENT.add(this.block);
            return this;
        }

        public BlockBuilder fuel(int duration) {
            ITEM_BURNABLE.put(this.block, duration);
            return this;
        }

        public BlockBuilder flammable(int duration, int spread) {
            BLOCK_FLAMMABLE.put(this.block, new Pair<>(duration, spread));
            return this;
        }

        public BlockBuilder strip(Block stripped) {
            BLOCK_STRIPPED.putIfAbsent(this.block, stripped);
            return this;
        }
    }

    public static class ItemBuilder {
        protected ItemBuilder(String name, Supplier<? extends Item> item) {
            this.name = name;
            this.item = ITEMS.registerItem(name, properties -> item.get());
        }

        public DeferredItem build() {
            return this.item;
        }

        protected String name;
        protected DeferredItem<?> item;

        public ItemBuilder tag(TagKey<Item> tagname) {
            ITEM_TAGS.putIfAbsent(tagname, new ArrayList<>());
            ITEM_TAGS.get(tagname).add(this.item.get());
            return this;
        }

        @SafeVarargs
        public final ItemBuilder tag(TagKey<Item>... tags) {
            for (TagKey<Item> tagname : tags) {
                this.tag(tagname);
            }
            return this;
        }

        public ItemBuilder model() {
            return this.model(ModelTemplates.FLAT_ITEM);
        }

        public ItemBuilder model(ModelTemplate model) {
            ITEM_MODELS.put(this.item, model);
            return this;
        }

        public ItemBuilder fuel(int duration) {
            ITEM_BURNABLE.put(this.item, duration);
            return this;
        }
    }

    public static BlockBuilder ofBlock(String id, Supplier<? extends Block> block) {
        return VWRegistryHelper.ofBlock(id, block, true);
    }

    public static BlockBuilder ofBlock(String id, Supplier<? extends Block> block, boolean doubleBlock, boolean item) {
        return new BlockBuilder(id, block, doubleBlock, item);
    }

    public static BlockBuilder ofBlock(String id, Supplier<? extends Block> block, boolean item) {
        return new BlockBuilder(id, block, false, item);
    }

    public static ItemBuilder ofItem(String id, Supplier<? extends Item> item) {
        return new ItemBuilder(id, item);
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, boolean registerItem) {
        DeferredBlock<T> toReturn = VWRegistryHelper.BLOCKS.register(name, block);
        if (registerItem)
            registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> DeferredBlock<T> registerDoubleBlock(String name, Supplier<T> block, boolean registerItem) {
        DeferredBlock<T> toReturn = VWRegistryHelper.BLOCKS.register(name, block);
        if (registerItem)
            registerDoubleBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block) {
        return VWRegistryHelper.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredItem<Item> registerDoubleBlockItem(String name, DeferredBlock<T> block) {
        return VWRegistryHelper.ITEMS.register(name, () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }

    public static List<DeferredBlock<?>> getModelList(Models key) {
        return BLOCK_MODELS.getOrDefault(key, new ArrayList<>());
    }


    public static void registerStairsAndSlab(DeferredBlock<?> parent, DeferredBlock<?> stairs, DeferredBlock<?> slab) {
        registerSet(parent, Map.of(
                Models.STAIRS, stairs,
                Models.SLAB, slab
        ));
    }

    public static void registerGlass(DeferredBlock<?> parent, DeferredBlock<?> pane) {
        registerSet(parent, Map.of(
                Models.PANE, pane
        ));
    }

    public static void registerSet(DeferredBlock<?> parent, Map<Models, DeferredBlock<?>> set) {
        BLOCK_SETS.putIfAbsent(parent, set);
        for (Models model : set.keySet()) {
            BLOCK_MODELS.putIfAbsent(model, new ArrayList<>());
            BLOCK_MODELS.get(model).add(set.get(model));
        }
    }

    public static void addDrop(DeferredBlock<?> block, ItemLike loot) {
        BLOCK_DROPS.putIfAbsent(block, loot);
    }

    public static Map<TagKey<Block>, List<Supplier<Block>>> BLOCK_TAGS = new HashMap<>();

    public static Map<DeferredBlock<?>, ItemLike> BLOCK_DROPS = new HashMap<>();
    public static Map<Block, ItemLike> BLOCK_SILK_DROPS = new HashMap<>();

    public static Map<DeferredBlock<?>, Block> BLOCK_STRIPPED = new HashMap<>();
    public static Map<DeferredBlock<?>, Map<Models, DeferredBlock<?>>> BLOCK_SETS = new HashMap<>();

    public static Map<Models, List<DeferredBlock<?>>> BLOCK_MODELS = new HashMap<>();
    public static List<DeferredBlock<?>> BLOCK_CUTOUT = new ArrayList<>();
    public static List<DeferredBlock<?>> BLOCK_TRANSLUCENT = new ArrayList<>();

    public static Map<DeferredBlock<?>, Pair<Integer, Integer>> BLOCK_FLAMMABLE = new HashMap<>();

    public static Map<TagKey<Item>, List<Item>> ITEM_TAGS = new HashMap<>();
    public static Map<DeferredItem<?>, ModelTemplate> ITEM_MODELS = new HashMap<>();
    public static Map<ItemLike, Integer> ITEM_BURNABLE = new HashMap<>();

    public enum Models {
        CUBE,
        CROSS,
        PILLAR,
        WOOD,
        STAIRS,
        SLAB,
        BUTTON,
        PRESSURE_PLATE,
        FENCE,
        FENCE_GATE,
        DOOR,
        TRAPDOOR,
        WALL,
        SIGN,
        WALL_SIGN,
        HANGING_SIGN,
        WALL_HANGING_SIGN,
        PANE,
        GRASS,
        ROTATABLE
    }
}