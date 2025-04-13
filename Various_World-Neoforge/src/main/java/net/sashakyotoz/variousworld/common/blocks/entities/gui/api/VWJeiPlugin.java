package net.sashakyotoz.variousworld.common.blocks.entities.gui.api;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;
import net.sashakyotoz.variousworld.init.VWBlocks;

import java.util.List;

@JeiPlugin
public class VWJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return VariousWorld.createVWLocation("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GemsmithRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<GemsmithTransformRecipe> recipes = Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(GemsmithTransformRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(GemsmithRecipeCategory.GEMSMITH_RECIPE, recipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(GemsmithTableScreen.class, 82, 48, 24, 24,
                GemsmithRecipeCategory.GEMSMITH_RECIPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(VWBlocks.GEMSMITH_TABLE.get()), GemsmithRecipeCategory.GEMSMITH_RECIPE);
    }
}