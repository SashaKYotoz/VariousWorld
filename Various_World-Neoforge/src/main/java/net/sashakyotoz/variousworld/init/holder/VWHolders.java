package net.sashakyotoz.variousworld.init.holder;

import net.lcc.sollib.api.common.registry.SHolder;
import net.lcc.sollib.api.common.registry.SolModContainer;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

import java.util.function.Supplier;

public final class VWHolders {
    private VWHolders() {
    }

    @SuppressWarnings("unchecked")
    private static <T> Registry<T> cast(Registry<?> registry) {
        return (Registry<T>) registry;
    }

    public static class BlockEntityTypeHolder<T extends BlockEntity> extends SHolder<BlockEntityType<T>> {
        public BlockEntityTypeHolder(SolModContainer mod, String name, Supplier<BlockEntityType<T>> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<BlockEntityType<T>> getRegistry() {
            return cast(BuiltInRegistries.BLOCK_ENTITY_TYPE);
        }
    }

    public static class MenuTypeHolder<T extends AbstractContainerMenu> extends SHolder<MenuType<T>> {
        public MenuTypeHolder(SolModContainer mod, String name, Supplier<MenuType<T>> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<MenuType<T>> getRegistry() {
            return cast(BuiltInRegistries.MENU);
        }
    }

    public static class DataComponentHolder<T> extends SHolder<DataComponentType<T>> {
        public DataComponentHolder(SolModContainer mod, String name, Supplier<DataComponentType<T>> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<DataComponentType<T>> getRegistry() {
            return cast(BuiltInRegistries.DATA_COMPONENT_TYPE);
        }
    }

    public static class SoundHolder extends SHolder<SoundEvent> {
        public SoundHolder(SolModContainer mod, String name, Supplier<SoundEvent> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<SoundEvent> getRegistry() {
            return BuiltInRegistries.SOUND_EVENT;
        }
    }

    public static class FeatureHolder<F extends Feature<?>> extends SHolder<F> {
        public FeatureHolder(SolModContainer mod, String name, Supplier<F> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<F> getRegistry() {
            return cast(BuiltInRegistries.FEATURE);
        }
    }

    public static class FoliagePlacerTypeHolder extends SHolder<FoliagePlacerType<?>> {
        public FoliagePlacerTypeHolder(SolModContainer mod, String name, Supplier<FoliagePlacerType<?>> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<FoliagePlacerType<?>> getRegistry() {
            return BuiltInRegistries.FOLIAGE_PLACER_TYPE;
        }
    }

    public static class TrunkPlacerTypeHolder extends SHolder<TrunkPlacerType<?>> {
        public TrunkPlacerTypeHolder(SolModContainer mod, String name, Supplier<TrunkPlacerType<?>> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<TrunkPlacerType<?>> getRegistry() {
            return BuiltInRegistries.TRUNK_PLACER_TYPE;
        }
    }

    public static class ParticleTypeHolder<T extends ParticleType<?>> extends SHolder<T> {
        public ParticleTypeHolder(SolModContainer mod, String name, Supplier<T> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<T> getRegistry() {
            return cast(BuiltInRegistries.PARTICLE_TYPE);
        }
    }

    public static class RecipeSerializerHolder<T extends RecipeSerializer<?>> extends SHolder<T> {
        public RecipeSerializerHolder(SolModContainer mod, String name, Supplier<T> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<T> getRegistry() {
            return cast(BuiltInRegistries.RECIPE_SERIALIZER);
        }
    }

    public static class SensorTypeHolder<T extends Sensor<?>> extends SHolder<SensorType<T>> {
        public SensorTypeHolder(SolModContainer mod, String name, Supplier<SensorType<T>> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<SensorType<T>> getRegistry() {
            return cast(BuiltInRegistries.SENSOR_TYPE);
        }
    }

    public static class PoiTypeHolder extends SHolder<PoiType> {
        public PoiTypeHolder(SolModContainer mod, String name, Supplier<PoiType> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<PoiType> getRegistry() {
            return BuiltInRegistries.POINT_OF_INTEREST_TYPE;
        }
    }

    public static class VillagerProfessionHolder extends SHolder<VillagerProfession> {
        public VillagerProfessionHolder(SolModContainer mod, String name, Supplier<VillagerProfession> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<VillagerProfession> getRegistry() {
            return BuiltInRegistries.VILLAGER_PROFESSION;
        }
    }

    public static class VillagerTypeHolder extends SHolder<VillagerType> {
        public VillagerTypeHolder(SolModContainer mod, String name, Supplier<VillagerType> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<VillagerType> getRegistry() {
            return BuiltInRegistries.VILLAGER_TYPE;
        }
    }

    public static class CreativeTabHolder extends SHolder<CreativeModeTab> {
        public CreativeTabHolder(SolModContainer mod, String name, Supplier<CreativeModeTab> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<CreativeModeTab> getRegistry() {
            return BuiltInRegistries.CREATIVE_MODE_TAB;
        }
    }

    public static class LootFunctionTypeHolder<T extends LootItemFunction> extends SHolder<LootItemFunctionType<T>> {
        public LootFunctionTypeHolder(SolModContainer mod, String name, Supplier<LootItemFunctionType<T>> supplier) {
            super(mod, name, supplier);
        }

        @Override
        public Registry<LootItemFunctionType<T>> getRegistry() {
            return cast(BuiltInRegistries.LOOT_FUNCTION_TYPE);
        }
    }
}