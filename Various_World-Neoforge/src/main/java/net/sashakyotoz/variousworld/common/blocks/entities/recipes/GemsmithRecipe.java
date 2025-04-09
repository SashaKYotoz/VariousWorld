package net.sashakyotoz.variousworld.common.blocks.entities.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.sashakyotoz.variousworld.init.VWBlocks;

public interface GemsmithRecipe extends Recipe<GemsmithRecipeInput> {

    default boolean canCraftInDimensions(int width, int height) {
        return width >= 2 && height >= 1;
    }

    default ItemStack getToastSymbol() {
        return VWBlocks.GEMSMITH_TABLE.toStack();
    }

    boolean isToolIngredient(ItemStack tool);

    boolean isGemIngredient(ItemStack gem);
}