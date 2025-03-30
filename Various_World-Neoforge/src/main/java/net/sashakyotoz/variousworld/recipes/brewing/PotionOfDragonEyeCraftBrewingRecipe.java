package net.sashakyotoz.variousworld.recipes.brewing;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import net.sashakyotoz.variousworld.init.VWItems;

public class PotionOfDragonEyeCraftBrewingRecipe implements IBrewingRecipe {

	@Override
	public boolean isInput(ItemStack input) {
		return Ingredient.of(new ItemStack(Items.HONEY_BOTTLE)).test(input);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return Ingredient.of(new ItemStack(VWItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get())).test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return new ItemStack(VWItems.POTION_OF_DRAGON_EYE_EFFECT.get());
		}
		return ItemStack.EMPTY;
	}
}
