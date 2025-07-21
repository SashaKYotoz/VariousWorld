package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.datagen.builders.GemsmithRecipeBuilder;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;
import net.sashakyotoz.variousworld.init.VWTags;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        for (DeferredBlock<?> block : VWRegistryHelper.BLOCK_SETS.keySet()) {
            for (Map.Entry<VWRegistryHelper.Models, DeferredBlock<?>> entry : VWRegistryHelper.BLOCK_SETS.get(block).entrySet()) {
                switch (entry.getKey()) {
                    case STAIRS -> stairs(recipeOutput, entry.getValue().get(), block.get());
                    case SLAB ->
                            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, entry.getValue().get(), block.get());
                    case DOOR -> door(recipeOutput, entry.getValue().get(), block.get());
                    case TRAPDOOR -> trapdoor(recipeOutput, entry.getValue().get(), block.get());
                    case BUTTON -> button(recipeOutput, entry.getValue().get(), block.get());
                    case PRESSURE_PLATE -> pressurePlate(recipeOutput, entry.getValue().get(), block.get());
                    case FENCE -> fence(recipeOutput, entry.getValue().get(), block.get());
                    case FENCE_GATE -> fenceGate(recipeOutput, entry.getValue().get(), block.get());
                    case SIGN -> sign(recipeOutput, entry.getValue().get(), block.get());
                    case HANGING_SIGN -> hangingSign(recipeOutput, entry.getValue().get(), block.get());
                }
            }
        }
        woodFromLogs(recipeOutput, VWBlocks.CRYSTALIC_OAK_WOOD.get(), VWBlocks.CRYSTALIC_OAK_LOG.get());
        woodFromLogs(recipeOutput, VWBlocks.STRIPPED_CRYSTALIC_OAK_WOOD.get(), VWBlocks.STRIPPED_CRYSTALIC_OAK_LOG.get());

        planksFromLog(recipeOutput, VWBlocks.CRYSTALIC_OAK_PLANKS.get(), VWTags.Items.CRYSTALIC_OAK_LOGS, 4);

        woodFromLogs(recipeOutput, VWBlocks.BLUE_JACARANDA_WOOD.get(), VWBlocks.BLUE_JACARANDA_LOG.get());
        woodFromLogs(recipeOutput, VWBlocks.STRIPPED_BLUE_JACARANDA_WOOD.get(), VWBlocks.STRIPPED_BLUE_JACARANDA_LOG.get());

        planksFromLog(recipeOutput, VWBlocks.BLUE_JACARANDA_PLANKS.get(), VWTags.Items.BLUE_JACARANDA_LOGS, 4);

        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, VWBlocks.SODALITE_BLOCK.get(), VWItems.SODALITE_SHARD.get());

        BuiltInRegistries.ITEM.forEach(item -> {
            if (item instanceof TieredItem) {
                gemsmith(recipeOutput, item, VWItems.SODALITE_SHARD.get());
                gemsmith(recipeOutput, item, VWItems.CRYSTALLINE_SLIME_BALL.get());
                gemsmith(recipeOutput, item, Items.AMETHYST_SHARD);
            }
        });
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.MAGENTA_DYE)
                .unlockedBy("has_block", has(VWBlocks.BLUE_JACARANDA_PETALS.get()))
                .requires(VWBlocks.BLUE_JACARANDA_PETALS.get())
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, VWBlocks.GEMSMITH_TABLE.get())
                .define('i', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .define('d', Items.DEEPSLATE_BRICKS)
                .pattern("is ")
                .pattern("dd ")
                .pattern("dd ")
                .unlockedBy("has_block", has(Items.DEEPSLATE_BRICKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, VWBlocks.GEMSMITH_FURNACE.get())
                .define('f', Items.FURNACE)
                .define('d', Items.DEEPSLATE_BRICKS)
                .pattern("ddd")
                .pattern("dfd")
                .pattern("ddd")
                .unlockedBy("has_block", has(Items.DEEPSLATE_BRICKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, VWBlocks.ARTIFACT_TABLE.get())
                .define('g', Items.GLASS)
                .define('p', Items.PURPLE_CARPET)
                .define('s', VWItems.SODALITE_SHARD.get())
                .define('b', VWBlocks.BLUE_JACARANDA_PLANKS.get())
                .pattern("sgs")
                .pattern("bpb")
                .pattern("bbb")
                .unlockedBy("has_block", has(VWBlocks.BLUE_JACARANDA_PLANKS.get()))
                .save(recipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, VariousWorld.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    private void sign(RecipeOutput recipeOutput, ItemLike sign, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, sign, 3).group("sign").define('#', material).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ").unlockedBy("has_block", has(material)).save(recipeOutput);
    }

    private void stairs(RecipeOutput recipeOutput, ItemLike stairs, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4).define('#', material).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_block", has(material)).save(recipeOutput);
    }

    private void door(RecipeOutput recipeOutput, ItemLike door, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, door, 3).define('#', material).pattern("##").pattern("##").pattern("##").unlockedBy("has_block", has(material)).save(recipeOutput);
    }

    private void trapdoor(RecipeOutput recipeOutput, ItemLike trapdoor, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, trapdoor, 2).define('#', material).pattern("###").pattern("###").unlockedBy("has_block", has(material)).save(recipeOutput);
    }

    private void button(RecipeOutput recipeOutput, ItemLike button, ItemLike material) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, button).requires(material).unlockedBy("has_block", has(material)).save(recipeOutput);
    }

    private void fence(RecipeOutput recipeOutput, ItemLike fence, ItemLike material) {
        Item item = fence == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : Items.STICK;
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3).define('W', material).define('#', item).pattern("W#W").pattern("W#W").unlockedBy("has_block", has(material)).save(recipeOutput);
    }

    private void fenceGate(RecipeOutput recipeOutput, ItemLike fenceGate, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, fenceGate).define('#', Items.STICK).define('W', material).pattern("#W#").pattern("#W#").unlockedBy("has_block", has(material)).save(recipeOutput);
    }

    private void gemsmith(RecipeOutput recipeOutput, ItemLike tool, ItemLike gem) {
        GemsmithRecipeBuilder.smithing(Ingredient.of(tool), Ingredient.of(gem), RecipeCategory.COMBAT, tool.asItem()).unlocks("has_gem", has(gem)).save(recipeOutput, String.format("%s_%s_gemsmithing", getItemName(tool), getItemName(gem)));
    }
}