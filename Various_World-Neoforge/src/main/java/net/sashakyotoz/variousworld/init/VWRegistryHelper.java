package net.sashakyotoz.variousworld.init;

import net.lcc.sollib.api.common.registry.SHolder;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.lcc.sollib.api.common.registry.holder.EntityHolder;
import net.lcc.sollib.api.common.registry.holder.ItemHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.holder.VWHolders;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class VWRegistryHelper {
    private static final List<Supplier<? extends ItemLike>> CREATIVE_ORDER = new ArrayList<>();
    public static final List<BlockHolder> TRANSLUCENT = new ArrayList<>();

    private VWRegistryHelper() {
    }

    public static BlockHolder block(String name, Supplier<? extends Block> supplier) {
        return VariousWorld.MOD.register(BlockHolder.class, name, supplier::get);
    }

    public static BlockHolder blockWithItem(String name, Supplier<? extends Block> supplier) {
        return creative(block(name, supplier).withItem());
    }

    public static BlockHolder hiddenBlockWithItem(String name, Supplier<? extends Block> supplier) {
        return block(name, supplier).withItem();
    }

    public static BlockHolder doubleBlockWithItem(String name, Supplier<? extends Block> supplier) {
        BlockHolder holder = creative(block(name, supplier));
        VariousWorld.MOD.register(ItemHolder.class, name, () -> new DoubleHighBlockItem(holder.get(), new Item.Properties()));
        return holder;
    }

    public static ItemHolder item(String name, Supplier<? extends Item> supplier) {
        return creative(hiddenItem(name, supplier));
    }

    public static ItemHolder hiddenItem(String name, Supplier<? extends Item> supplier) {
        return VariousWorld.MOD.register(ItemHolder.class, name, supplier::get);
    }

    public static <T extends Entity> EntityHolder entity(String name, EntityType.Builder<T> builder) {
        return VariousWorld.MOD.register(EntityHolder.class, name, () -> builder.build(name));
    }

    public static <T extends BlockEntity> VWHolders.BlockEntityTypeHolder<T> blockEntity(String name, Supplier<BlockEntityType<T>> supplier) {
        return register(VWHolders.BlockEntityTypeHolder.class, name, supplier);
    }

    public static <T extends AbstractContainerMenu> VWHolders.MenuTypeHolder<T> menu(String name, Supplier<MenuType<T>> supplier) {
        return register(VWHolders.MenuTypeHolder.class, name, supplier);
    }

    public static <T> VWHolders.DataComponentHolder<T> dataComponent(String name, Supplier<DataComponentType<T>> supplier) {
        return register(VWHolders.DataComponentHolder.class, name, supplier);
    }

    public static VWHolders.SoundHolder sound(String name) {
        return register(VWHolders.SoundHolder.class, name,
                (Supplier<SoundEvent>) () -> SoundEvent.createVariableRangeEvent(VariousWorld.createVWLocation(name)));
    }

    public static <F extends Feature<?>> VWHolders.FeatureHolder<F> feature(String name, Supplier<F> supplier) {
        return register(VWHolders.FeatureHolder.class, name, supplier);
    }

    public static VWHolders.FoliagePlacerTypeHolder foliagePlacer(String name, Supplier<FoliagePlacerType<?>> supplier) {
        return register(VWHolders.FoliagePlacerTypeHolder.class, name, supplier);
    }

    public static VWHolders.TrunkPlacerTypeHolder trunkPlacer(String name, Supplier<TrunkPlacerType<?>> supplier) {
        return register(VWHolders.TrunkPlacerTypeHolder.class, name, supplier);
    }

    public static <T extends ParticleType<?>> VWHolders.ParticleTypeHolder<T> particle(String name, Supplier<T> supplier) {
        return register(VWHolders.ParticleTypeHolder.class, name, supplier);
    }

    public static <T extends RecipeSerializer<?>> VWHolders.RecipeSerializerHolder<T> recipeSerializer(String name, Supplier<T> supplier) {
        return register(VWHolders.RecipeSerializerHolder.class, name, supplier);
    }

    public static <T extends Sensor<?>> VWHolders.SensorTypeHolder<T> sensor(String name, Supplier<SensorType<T>> supplier) {
        return register(VWHolders.SensorTypeHolder.class, name, supplier);
    }

    public static VWHolders.PoiTypeHolder poi(String name, Supplier<PoiType> supplier) {
        return register(VWHolders.PoiTypeHolder.class, name, supplier);
    }

    public static VWHolders.VillagerProfessionHolder profession(String name, Supplier<VillagerProfession> supplier) {
        return register(VWHolders.VillagerProfessionHolder.class, name, supplier);
    }

    public static VWHolders.VillagerTypeHolder villagerType(String name, Supplier<VillagerType> supplier) {
        return register(VWHolders.VillagerTypeHolder.class, name, supplier);
    }

    public static VWHolders.CreativeTabHolder creativeTab(String name, Supplier<CreativeModeTab> supplier) {
        return register(VWHolders.CreativeTabHolder.class, name, supplier);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <H extends SHolder<?>> H register(Class<?> holderClass, String name, Supplier<?> supplier) {
        return (H) VariousWorld.MOD.getRegistrar((Class) holderClass).register(name, (Supplier) supplier);
    }

    public static <T, H extends SHolder<T>> void forEach(Class<H> holderClass, Consumer<H> consumer) {
        if (VariousWorld.MOD.hasRegistrar(holderClass))
            VariousWorld.MOD.getRegistrar(holderClass).iterate(consumer);
    }

    public static <H extends Supplier<? extends ItemLike>> H creative(H holder) {
        CREATIVE_ORDER.add(holder);
        return holder;
    }

    public static BlockHolder translucent(BlockHolder holder) {
        TRANSLUCENT.add(holder);
        return holder;
    }

    public static List<Item> creativeItems() {
        Set<Item> items = new LinkedHashSet<>();
        for (Supplier<? extends ItemLike> entry : CREATIVE_ORDER) {
            Item item = entry.get().asItem();
            if (item != Items.AIR)
                items.add(item);
        }
        return List.copyOf(items);
    }

    @SuppressWarnings("unchecked")
    public static TagKey<Block>[] tool(String toolMaterial) {
        String[] needed = toolMaterial.split("_");
        List<TagKey<Block>> tags = new ArrayList<>();

        switch (needed[0]) {
            case "stone" -> tags.add(BlockTags.NEEDS_STONE_TOOL);
            case "iron" -> tags.add(BlockTags.NEEDS_IRON_TOOL);
            case "diamond" -> tags.add(BlockTags.NEEDS_DIAMOND_TOOL);
            default -> {
            }
        }
        switch (needed[1]) {
            case "pickaxe" -> tags.add(BlockTags.MINEABLE_WITH_PICKAXE);
            case "axe" -> tags.add(BlockTags.MINEABLE_WITH_AXE);
            case "shovel" -> tags.add(BlockTags.MINEABLE_WITH_SHOVEL);
            case "hoe" -> tags.add(BlockTags.MINEABLE_WITH_HOE);
            case "sword" -> tags.add(BlockTags.SWORD_EFFICIENT);
            default -> {
            }
        }
        return tags.toArray(TagKey[]::new);
    }
}