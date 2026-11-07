package net.sashakyotoz.variousworld.datagen;

import net.lcc.sollib.api.common.registry.data.block.BlockModel;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.sashakyotoz.variousworld.datagen.builders.GemsmithRecipeBuilder;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;
import net.sashakyotoz.variousworld.init.VWTags;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        VWRegistryHelper.forEach(BlockHolder.class, holder -> {
            Block base = holder.get();
            for (Map.Entry<BlockModel, BlockHolder> entry : holder.getBlockSet().entrySet()) {
                Block child = entry.getValue().get();
                switch (entry.getKey()) {
                    case STAIRS -> stairs(recipeOutput, child, base);
                    case SLAB -> slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, child, base);
                    case BUTTON -> button(recipeOutput, child, base);
                    case PRESSURE_PLATE -> pressurePlate(recipeOutput, child, base);
                    case FENCE -> fence(recipeOutput, child, base);
                    case FENCE_GATE -> fenceGate(recipeOutput, child, base);
                    case WALL -> wall(recipeOutput, RecipeCategory.DECORATIONS, child, base);
                    default -> {
                    }
                }
            }
        });
        for (VWBlocks.WoodSigns signs : VWBlocks.SIGNS) {
            sign(recipeOutput, signs.sign().get(), signs.planks().get());
            hangingSign(recipeOutput, signs.hangingSign().get(), signs.planks().get());
        }
        door(recipeOutput, VWBlocks.CRYSTALIC_OAK_DOOR.get(), VWBlocks.CRYSTALIC_OAK_PLANKS.get());
        trapdoor(recipeOutput, VWBlocks.CRYSTALIC_OAK_TRAPDOOR.get(), VWBlocks.CRYSTALIC_OAK_PLANKS.get());
        door(recipeOutput, VWBlocks.BLUE_JACARANDA_DOOR.get(), VWBlocks.BLUE_JACARANDA_PLANKS.get());
        trapdoor(recipeOutput, VWBlocks.BLUE_JACARANDA_TRAPDOOR.get(), VWBlocks.BLUE_JACARANDA_PLANKS.get());

        woodFromLogs(recipeOutput, VWBlocks.CRYSTALIC_OAK_WOOD.get(), VWBlocks.CRYSTALIC_OAK_LOG.get());
        woodFromLogs(recipeOutput, VWBlocks.STRIPPED_CRYSTALIC_OAK_WOOD.get(), VWBlocks.STRIPPED_CRYSTALIC_OAK_LOG.get());

        planksFromLog(recipeOutput, VWBlocks.CRYSTALIC_OAK_PLANKS.get(), VWTags.Items.CRYSTALIC_OAK_LOGS, 4);

        woodFromLogs(recipeOutput, VWBlocks.BLUE_JACARANDA_WOOD.get(), VWBlocks.BLUE_JACARANDA_LOG.get());
        woodFromLogs(recipeOutput, VWBlocks.STRIPPED_BLUE_JACARANDA_WOOD.get(), VWBlocks.STRIPPED_BLUE_JACARANDA_LOG.get());

        planksFromLog(recipeOutput, VWBlocks.BLUE_JACARANDA_PLANKS.get(), VWTags.Items.BLUE_JACARANDA_LOGS, 4);

        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, VWBlocks.SODALITE_BLOCK.get(), VWItems.SODALITE_SHARD.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, VWItems.GEODE_COMPASS.get())
                .define('A', Items.AMETHYST_SHARD)
                .define('#', Items.DRIPSTONE_BLOCK)
                .define('S', VWItems.SODALITE_SHARD.get())
                .define('R', VWItems.RECLAIMITE_SHARD.get())
                .pattern(" #A")
                .pattern("#R#")
                .pattern("S# ")
                .unlockedBy("has_shard", has(VWItems.RECLAIMITE_SHARD.get()))
                .save(recipeOutput);

        woodenBoat(recipeOutput, VWItems.CRYSTALIC_OAK_BOAT.get(), VWBlocks.CRYSTALIC_OAK_PLANKS.get());
        chestBoat(recipeOutput, VWItems.CRYSTALIC_OAK_CHEST_BOAT.get(), VWItems.CRYSTALIC_OAK_BOAT.get());
        woodenBoat(recipeOutput, VWItems.BLUE_JACARANDA_BOAT.get(), VWBlocks.BLUE_JACARANDA_PLANKS.get());
        chestBoat(recipeOutput, VWItems.BLUE_JACARANDA_CHEST_BOAT.get(), VWItems.BLUE_JACARANDA_BOAT.get());

        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, VWItems.CRYSTALLINE_SLIME_BALL.get(), RecipeCategory.REDSTONE, VWBlocks.CRYSTALIC_SLIME_BLOCK.get());
        oneToOneConversionRecipe(recipeOutput, Items.WHITE_DYE, VWBlocks.SHINY_PLUMERIA.get(), "white_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, VWBlocks.SODALITE_BRAZIER.get())
                .define('S', VWItems.SODALITE_SHARD.get())
                .define('C', ItemTags.COALS)
                .define('B', VWBlocks.SODALITE_BLOCK.get())
                .pattern("S S")
                .pattern("SCS")
                .pattern(" B ")
                .unlockedBy("has_sodalite_shard", has(VWItems.SODALITE_SHARD.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, VWBlocks.SOUL_SODALITE_BRAZIER.get())
                .define('S', VWItems.SODALITE_SHARD.get())
                .define('C', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .define('B', VWBlocks.SODALITE_BLOCK.get())
                .pattern("S S")
                .pattern("SCS")
                .pattern(" B ")
                .unlockedBy("has_sodalite_shard", has(VWItems.SODALITE_SHARD.get()))
                .save(recipeOutput);

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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, VWBlocks.DISASSEMBLY_TABLE.get())
                .define('g', Items.GLASS)
                .define('i', Items.IRON_INGOT)
                .define('s', Items.SMOOTH_STONE)
                .define('d', Items.DRIPSTONE_BLOCK)
                .pattern("igi")
                .pattern("sds")
                .pattern("sds")
                .unlockedBy("has_block", has(Items.DRIPSTONE_BLOCK))
                .save(recipeOutput);
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