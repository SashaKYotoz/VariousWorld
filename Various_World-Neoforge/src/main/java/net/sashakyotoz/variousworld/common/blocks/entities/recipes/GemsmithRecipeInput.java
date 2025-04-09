package net.sashakyotoz.variousworld.common.blocks.entities.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record GemsmithRecipeInput(ItemStack tool, ItemStack gem) implements RecipeInput {
    public ItemStack getItem(int i) {
        ItemStack stack;
        switch (i) {
            case 0 -> stack = this.tool;
            case 1 -> stack = this.gem;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + i);
        }

        return stack;
    }

    public int size() {
        return 3;
    }

    public boolean isEmpty() {
        return this.tool.isEmpty() && this.gem.isEmpty();
    }

    public ItemStack tool() {
        return this.tool;
    }

    public ItemStack gem() {
        return this.gem;
    }
}