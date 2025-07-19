package net.sashakyotoz.variousworld.common.blocks.entities.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record GemsmithRecipeInput(ItemStack tool, ItemStack gem) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        ItemStack stack;
        switch (i) {
            case 0 -> stack = this.tool;
            case 1 -> stack = this.gem;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + i);
        }

        return stack;
    }

    @Override
    public int size() {
        return 2;
    }
}