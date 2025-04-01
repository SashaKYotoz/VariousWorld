package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VariousWorld.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

//    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
//        simpleBlock(blockRegistryObject.get(),
//                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
//    }
//
//    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
//        simpleBlockWithItem(blockRegistryObject.get(),
//                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
//                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
//    }
//
//    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
//        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);
//
//        getVariantBuilder(block).forAllStates(function);
//    }
//
//    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
//        ConfiguredModel[] models = new ConfiguredModel[1];
//        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(GojiBerryBushBlock.AGE),
//                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + textureName + state.getValue(GojiBerryBushBlock.AGE))).renderType("cutout"));
//
//        return models;
//    }
//
//    public void makeCrop(CropBlock block, String modelName, String textureName) {
//        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
//
//        getVariantBuilder(block).forAllStates(function);
//    }
//
//    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
//        ConfiguredModel[] models = new ConfiguredModel[1];
//        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((RadishCropBlock) block).getAgeProperty()),
//                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + textureName + state.getValue(((RadishCropBlock) block).getAgeProperty()))).renderType("cutout"));
//
//        return models;
//    }
//
//    private void customLamp() {
//        getVariantBuilder(ModBlocks.BISMUTH_LAMP.get()).forAllStates(state -> {
//            if(state.getValue(BismuthLampBlock.CLICKED)) {
//                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_on",
//                        ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "bismuth_lamp_on")))};
//            } else {
//                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_off",
//                        ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "bismuth_lamp_off")))};
//            }
//        });
//
//        simpleBlockItem(ModBlocks.BISMUTH_LAMP.get(), models().cubeAll("bismuth_lamp_on",
//                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "bismuth_lamp_on")));
//    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
