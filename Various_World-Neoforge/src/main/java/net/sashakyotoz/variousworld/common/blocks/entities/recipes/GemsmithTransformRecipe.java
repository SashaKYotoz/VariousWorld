package net.sashakyotoz.variousworld.common.blocks.entities.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import java.util.List;

public class GemsmithTransformRecipe implements GemsmithRecipe {
    public final Ingredient tool;
    public final Ingredient gem;
    final ItemStack result;

    public GemsmithTransformRecipe(Ingredient tool, Ingredient gem, ItemStack result) {
        this.tool = tool;
        this.gem = gem;
        this.result = result;
    }

    @Override
    public boolean isToolIngredient(ItemStack tool) {
        return this.tool.test(tool);
    }

    @Override
    public boolean isGemIngredient(ItemStack gem) {
        return this.gem.test(gem);
    }

    @Override
    public boolean matches(GemsmithRecipeInput input, Level level) {
        return this.tool.test(input.tool()) && this.gem.test(input.gem());
    }

    @Override
    public ItemStack assemble(GemsmithRecipeInput input, HolderLookup.Provider provider) {
        List<ModConfigController.GemsmithingSetting> setting = ModConfigController.CRYSTALING_CONFIG_VALUES;
        if (input.tool().getItem() instanceof TieredItem && setting != null) {
            ItemStack itemstack = input.tool().copy();
            ItemStack supplyGemStack = VWItems.SUPPLY_CRYSTAL.toStack();
            String toolName;
            for (ModConfigController.GemsmithingSetting crystalingSetting : setting) {
                if (input.gem().is(crystalingSetting.item().build())) {
                    switch (itemstack.getItem()) {
                        case TieredItem item when item instanceof SwordItem -> toolName = "sword";
                        case TieredItem item when item instanceof PickaxeItem -> toolName = "pickaxe";
                        case TieredItem item when item instanceof AxeItem -> toolName = "axe";
                        case TieredItem item when item instanceof HoeItem -> toolName = "hoe";
                        case TieredItem item when item instanceof ShovelItem -> toolName = "shovel";
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
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> completeInput = NonNullList.create();
        completeInput.add(this.tool);
        completeInput.add(this.gem);
        return completeInput;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<GemsmithTransformRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<GemsmithTransformRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        private static final MapCodec<GemsmithTransformRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Ingredient.CODEC.fieldOf("tool").forGetter(recipe -> recipe.tool),
                                Ingredient.CODEC.fieldOf("gem").forGetter(recipe -> recipe.gem),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                        )
                        .apply(instance, GemsmithTransformRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, GemsmithTransformRecipe> STREAM_CODEC = StreamCodec.of(
                GemsmithTransformRecipe.Serializer::toNetwork, GemsmithTransformRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<GemsmithTransformRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GemsmithTransformRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static GemsmithTransformRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new GemsmithTransformRecipe(ingredient, ingredient1, itemstack);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, GemsmithTransformRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.tool);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.gem);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }
    }
}
