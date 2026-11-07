package net.sashakyotoz.variousworld.init;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.ArtifactTableMenu;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.DisassemblyTableMenu;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableMenu;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;
import net.sashakyotoz.variousworld.common.entities.brain.SpiderAttackEntitySensor;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.GeodeCompassData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.common.loot.ApplyGemFunction;
import net.sashakyotoz.variousworld.init.holder.VWHolders;
import net.sashakyotoz.variousworld.mixin.accessor.SensorTypeAccessor;
import net.sashakyotoz.variousworld.mixin.accessor.SimpleParticleTypeAccessor;
import net.sashakyotoz.variousworld.platform.VWPlatform;

import java.util.function.UnaryOperator;

public class VWMiscRegistries {
    public static void init() {
    }

    public static final VWHolders.DataComponentHolder<CrystalData> CRYSTAL_DATA = component("crystal_data",
            builder -> builder.persistent(CrystalData.CODEC));
    public static final VWHolders.DataComponentHolder<SupplyCrystalData> SUPPLY_CRYSTAL_DATA = component("supply_crystal_data",
            builder -> builder.persistent(SupplyCrystalData.CODEC));
    public static final VWHolders.DataComponentHolder<GeodeCompassData> GEODE_COMPASS_DATA = component("geode_compass_data",
            builder -> builder.persistent(GeodeCompassData.CODEC));

    private static <T> VWHolders.DataComponentHolder<T> component(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return VWRegistryHelper.dataComponent(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static final VWHolders.MenuTypeHolder<GemsmithTableMenu> GEMSMITH_TABLE = VWRegistryHelper.menu("gemsmith_table",
            () -> VWPlatform.INSTANCE.createBlockMenu(GemsmithTableMenu::new));
    public static final VWHolders.MenuTypeHolder<ArtifactTableMenu> ARTIFACT_TABLE = VWRegistryHelper.menu("artifact_table",
            () -> VWPlatform.INSTANCE.createBlockMenu(ArtifactTableMenu::new));
    public static final VWHolders.MenuTypeHolder<DisassemblyTableMenu> DISASSEMBLY_TABLE = VWRegistryHelper.menu("disassembly_table",
            () -> VWPlatform.INSTANCE.createBlockMenu(DisassemblyTableMenu::new));

    public static final VWHolders.ParticleTypeHolder<SimpleParticleType> PEACEFUL_PARTICLE = VWRegistryHelper.particle("peaceful_particle",
            () -> SimpleParticleTypeAccessor.variousworld$create(false));

    public static final VWHolders.RecipeSerializerHolder<GemsmithTransformRecipe.Serializer> GEMSMITH_TRANSFORM = VWRegistryHelper.recipeSerializer("gemsmith_transform",
            () -> GemsmithTransformRecipe.Serializer.INSTANCE);

    public static final VWHolders.LootFunctionTypeHolder<ApplyGemFunction> APPLY_GEM = VWRegistryHelper.lootFunction("apply_gem",
            () -> new LootItemFunctionType<>(ApplyGemFunction.CODEC));

    public static final VWHolders.SensorTypeHolder<SpiderAttackEntitySensor> SQUEALING_SPIDER_ATTACK_SENSOR = VWRegistryHelper.sensor("squealing_spider_attack_sensor",
            () -> SensorTypeAccessor.variousworld$create(SpiderAttackEntitySensor::new));
}
