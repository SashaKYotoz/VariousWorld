package net.sashakyotoz.variousworld.datagen.builders;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GemsmithRecipeBuilder {
    private final Ingredient tool;
    private final Ingredient gem;
    private final RecipeCategory category;
    private final Item result;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public GemsmithRecipeBuilder(Ingredient pTool, Ingredient pGem, RecipeCategory pCategory, Item pResult) {
        this.category = pCategory;
        this.tool = pTool;
        this.gem = pGem;
        this.result = pResult;
    }

    public static GemsmithRecipeBuilder smithing(Ingredient pTool, Ingredient pGem, RecipeCategory pCategory, Item pResult) {
        return new GemsmithRecipeBuilder(pTool, pGem, pCategory, pResult);
    }

    public GemsmithRecipeBuilder unlocks(String key, Criterion<?> criterion) {
        this.criteria.put(key, criterion);
        return this;
    }

    public void save(RecipeOutput recipeOutput, String recipeId) {
        this.save(recipeOutput, ResourceLocation.parse(recipeId));
    }

    public void save(RecipeOutput recipeOutput, ResourceLocation recipeId) {
        this.ensureValid(recipeId);
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancement$builder);
        this.criteria.forEach(advancement$builder::addCriterion);
        GemsmithTransformRecipe recipe = new GemsmithTransformRecipe(this.tool, this.gem, new ItemStack(this.result));
        recipeOutput.accept(recipeId, recipe, advancement$builder.build(recipeId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }
}
