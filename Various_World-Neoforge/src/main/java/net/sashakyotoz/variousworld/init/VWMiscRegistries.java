package net.sashakyotoz.variousworld.init;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.ArtifactTableMenu;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableMenu;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;

import java.util.function.UnaryOperator;

public class VWMiscRegistries {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, VariousWorld.MOD_ID);
//    public static final DeferredHolder<MobEffect, AmethystSpikesMobEffect> AMETHYST_SPIKES = EFFECTS.register("amethyst_spikes", AmethystSpikesMobEffect::new);
//    public static final DeferredHolder<MobEffect, ChainedOfChainMobEffect> CHAINED_OF_CHAIN = EFFECTS.register("chained_of_chain", ChainedOfChainMobEffect::new);

//	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHA, VariousWorld.MODID);
//	public static final RegistryObject<Enchantment> WITHERED = ENCHANTMENTS.register("withered", WitheredEnchantment::new);
//	public static final RegistryObject<Enchantment> JUMPER = ENCHANTMENTS.register("jumper", JumperEnchantment::new);

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, VariousWorld.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CrystalData>> CRYSTAL_DATA = register("crystal_data",
            builder -> builder.persistent(CrystalData.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SupplyCrystalData>> SUPPLY_CRYSTAL_DATA = register("supply_crystal_data",
            builder -> builder.persistent(SupplyCrystalData.CODEC));

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                           UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, VariousWorld.MOD_ID);
    public static final DeferredHolder<MenuType<?>, MenuType<GemsmithTableMenu>> GEMSMITH_TABLE = MENUS.register("gemsmith_table", () -> IMenuTypeExtension.create(GemsmithTableMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<ArtifactTableMenu>> ARTIFACT_TABLE = MENUS.register("artifact_table", () -> IMenuTypeExtension.create(ArtifactTableMenu::new));

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, VariousWorld.MOD_ID);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PEACEFUL_PARTICLE = PARTICLES.register("peaceful_particle", () -> new SimpleParticleType(false));
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_SERIALIZER, VariousWorld.MOD_ID);
    public static final DeferredHolder<RecipeSerializer<?>, GemsmithTransformRecipe.Serializer> GEMSMITH_TRANSFORM =
            RECIPES.register("gemsmith_transform", () -> GemsmithTransformRecipe.Serializer.INSTANCE);

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
        DATA_COMPONENT_TYPES.register(bus);
        MENUS.register(bus);
        PARTICLES.register(bus);
        RECIPES.register(bus);
    }
}