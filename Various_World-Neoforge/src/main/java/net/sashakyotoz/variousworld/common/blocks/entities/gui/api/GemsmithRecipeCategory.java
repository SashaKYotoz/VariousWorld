package net.sashakyotoz.variousworld.common.blocks.entities.gui.api;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;
import net.sashakyotoz.variousworld.init.VWBlocks;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("removal")
public class GemsmithRecipeCategory implements IRecipeCategory<GemsmithTransformRecipe> {
    public static RecipeType<GemsmithTransformRecipe> GEMSMITH_RECIPE = RecipeType.create(VariousWorld.MOD_ID, "gemsmith_transforming", GemsmithTransformRecipe.class);

    private final IDrawable icon;
    private final IDrawable background;

    public GemsmithRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(GemsmithTableScreen.BACKGROUND_LOCATION, 0, 0, 174, 164);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(VWBlocks.GEMSMITH_TABLE.get()));
    }

    @Override
    public RecipeType<GemsmithTransformRecipe> getRecipeType() {
        return GEMSMITH_RECIPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.various_world.gemsmith_table");
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    @Nullable
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GemsmithTransformRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 43, 48).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 61, 48).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 48).addItemStack(recipe.getResultItem(null));
    }
}