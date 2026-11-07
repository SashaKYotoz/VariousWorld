package net.sashakyotoz.variousworld.common.blocks.entities.gui.api;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.MapColor;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableScreen;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;
import net.sashakyotoz.variousworld.init.VWBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("removal")
public class GemsmithRecipeCategory implements IRecipeCategory<GemsmithTransformRecipe> {
    public static RecipeType<GemsmithTransformRecipe> GEMSMITH_RECIPE = RecipeType.create(VariousWorld.MOD_ID, "gemsmith_transforming", GemsmithTransformRecipe.class);

    private static final int WIDTH = 174;
    private static final int TABLE_HEIGHT = 80;
    private static final int HINT_HEIGHT = 40;

    private final IDrawable icon;
    private final IDrawable background;

    public GemsmithRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(GemsmithTableScreen.BACKGROUND_LOCATION, 0, 0, WIDTH, TABLE_HEIGHT)
                .addPadding(0, HINT_HEIGHT, 0, 0)
                .build();
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
        builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 48).addItemStack(previewResult(recipe));
        builder.addSlot(RecipeIngredientRole.CATALYST, 4, TABLE_HEIGHT + 4).addItemStack(new ItemStack(VWBlocks.GEMSMITH_FURNACE.get()));
    }

    @Override
    public void draw(GemsmithTransformRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;
        List<FormattedCharSequence> lines = font.split(Component.translatable("jei.various_world.gemsmith_table.furnace"), WIDTH - 28);
        int y = TABLE_HEIGHT + 2;
        for (FormattedCharSequence line : lines) {
            guiGraphics.drawString(font, line, 24, y, MapColor.COLOR_GRAY.col, false);
            y += font.lineHeight;
        }
    }

    private static ItemStack previewResult(GemsmithTransformRecipe recipe) {
        ItemStack[] tools = recipe.tool.getItems();
        ItemStack[] gems = recipe.gem.getItems();
        if (tools.length == 0 || gems.length == 0)
            return recipe.getResultItem(null);
        return GemsmithingHelper.applyGem(tools[0], gems[0]);
    }
}