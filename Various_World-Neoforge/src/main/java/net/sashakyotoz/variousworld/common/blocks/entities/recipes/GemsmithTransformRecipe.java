package net.sashakyotoz.variousworld.common.blocks.entities.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import java.util.List;

public record GemsmithTransformRecipe(Ingredient tool, Ingredient gem,
                                      ItemStack result) implements Recipe<GemsmithRecipeInput> {

    @Override
    public boolean matches(GemsmithRecipeInput input, Level level) {
        return this.tool.test(input.tool()) && this.gem.test(input.gem());
    }

    @Override
    public ItemStack assemble(GemsmithRecipeInput input, HolderLookup.Provider provider) {
        List<ModConfigController.GemsmithingSetting> setting = ModConfigController.CRYSTALING_CONFIG_VALUES;
        if (OnActionsTrigger.isInstanceOfAny(input.tool().getItem()) && setting != null) {
            ItemStack itemstack = input.tool().copy();
            ItemStack supplyGemStack = VWItems.SUPPLY_CRYSTAL.toStack();
            String toolName;
            for (ModConfigController.GemsmithingSetting crystalingSetting : setting) {
                if (input.gem().is(crystalingSetting.item().build())) {
                    switch (itemstack.getItem()) {
                        case Item item when item instanceof SwordItem -> toolName = "sword";
                        case Item item when item instanceof PickaxeItem -> toolName = "pickaxe";
                        case Item item when item instanceof AxeItem -> toolName = "axe";
                        case Item item when item instanceof HoeItem -> toolName = "hoe";
                        case Item item when item instanceof ShovelItem -> toolName = "shovel";
                        default -> toolName = "";
                    }
                    supplyGemStack.set(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(), new SupplyCrystalData(
                            input.gem(),
                            toolName
                    ));
                    itemstack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(
                            supplyGemStack,
                            crystalingSetting.durability()
                    ));
                }
            }
            return itemstack;
        } else
            return input.tool();
    }

    @Override
    public RecipeSerializer<? extends Recipe<GemsmithRecipeInput>> getSerializer() {
        return VWMiscRegistries.GEMSMITH_TRANSFORM.get();
    }

    @Override
    public RecipeType<? extends Recipe<GemsmithRecipeInput>> getType() {
        return VWMiscRegistries.GEMSMITH_TRANSFORM_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of(tool, gem));
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.SMITHING;
    }

    public static class Serializer implements RecipeSerializer<GemsmithTransformRecipe> {
        private static final MapCodec<GemsmithTransformRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Ingredient.CODEC.fieldOf("tool").forGetter(GemsmithTransformRecipe::tool),
                                Ingredient.CODEC.fieldOf("gem").forGetter(GemsmithTransformRecipe::gem),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(GemsmithTransformRecipe::result)
                        )
                        .apply(instance, GemsmithTransformRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, GemsmithTransformRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, GemsmithTransformRecipe::tool,
                        Ingredient.CONTENTS_STREAM_CODEC, GemsmithTransformRecipe::gem,
                        ItemStack.STREAM_CODEC, GemsmithTransformRecipe::result,
                        GemsmithTransformRecipe::new
                );

        @Override
        public MapCodec<GemsmithTransformRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GemsmithTransformRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}