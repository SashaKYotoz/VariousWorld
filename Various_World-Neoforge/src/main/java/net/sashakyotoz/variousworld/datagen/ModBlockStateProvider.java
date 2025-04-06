package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.blocks.custom.SodaliteWartBlock;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VariousWorld.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.CUBE))
            simpleBlockWithItem(block.get(), cubeAll(block.get()));
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.PILLAR))
            logBlockWithItem(block);
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.CROSS))
            crossBlock(block);
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.DIRECTIONAL_CROSS))
            directionalCrossBlock(block);
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.GRASS))
            grassBlock(block);
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.CROSS_POTTED))
            pottedCrossBlock(block);
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.TRAPDOOR)) {
            trapdoorBlock((TrapDoorBlock) block.get(),
                    blockTexture(block.get()), true);
            blockItem(block, "_bottom");
        }
        for (DeferredBlock<?> block : VWRegistryHelper.getModelList(VWRegistryHelper.Models.DOOR)) {
            doorBlock((DoorBlock) block.get(),
                    extend(blockTexture(block.get()), "_top"), extend(blockTexture(block.get()), "_bottom"));
            itemModels().basicItem(block.asItem());
        }
        for (DeferredBlock<?> block : VWRegistryHelper.BLOCK_SETS.keySet()) {
            blockWithItem(block);
            for (Map.Entry<VWRegistryHelper.Models, DeferredBlock<?>> entry : VWRegistryHelper.BLOCK_SETS.get(block).entrySet()) {
                if (entry.getKey() == VWRegistryHelper.Models.STAIRS) {
                    stairsBlock((StairBlock) entry.getValue().get(), blockTexture(block.get()));
                    simpleBlockItem(entry.getValue().get(), new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + entry.getValue().getId().getPath()));
                }
                if (entry.getKey() == VWRegistryHelper.Models.SLAB) {
                    slabBlock((SlabBlock) entry.getValue().get(),
                            blockTexture(block.get()),
                            blockTexture(block.get()));
                    simpleBlockItem(entry.getValue().get(), new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + entry.getValue().getId().getPath()));
                }
                if (entry.getKey() == VWRegistryHelper.Models.BUTTON) {
                    buttonBlock((ButtonBlock) entry.getValue().get(),
                            blockTexture(block.get()));
                    buttonItem(entry.getValue(), block);
                }
                if (entry.getKey() == VWRegistryHelper.Models.PRESSURE_PLATE) {
                    pressurePlateBlock((PressurePlateBlock) entry.getValue().get(), blockTexture(block.get()));
                    simpleBlockItem(entry.getValue().get(), new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + entry.getValue().getId().getPath()));
                }
                if (entry.getKey() == VWRegistryHelper.Models.FENCE) {
                    fenceBlock((FenceBlock) entry.getValue().get(), blockTexture(block.get()));
                    fenceItem(entry.getValue(), block);
                }
                if (entry.getKey() == VWRegistryHelper.Models.FENCE_GATE) {
                    fenceGateBlock((FenceGateBlock) entry.getValue().get(), blockTexture(block.get()));
                    blockItem(entry.getValue());
                }
                if (entry.getKey() == VWRegistryHelper.Models.SIGN)
                    signBlock((StandingSignBlock) entry.getValue().get(), (WallSignBlock) VWRegistryHelper.BLOCK_SETS.get(block).get(VWRegistryHelper.Models.WALL_SIGN).get(), blockTexture(block.get()));
                if (entry.getKey() == VWRegistryHelper.Models.HANGING_SIGN)
                    hangingSignBlock((CeilingHangingSignBlock) entry.getValue().get(), (WallHangingSignBlock) VWRegistryHelper.BLOCK_SETS.get(block).get(VWRegistryHelper.Models.WALL_HANGING_SIGN).get(), blockTexture(block.get()));
            }
        }
        crossWithPropertyBlock(VWBlocks.SODALITE_WART);
    }

    private void grassBlock(DeferredBlock<?> block) {
        ResourceLocation resourcelocation = TextureMapping.getBlockTexture(VWBlocks.DIRT_WITH_CRYSTALS.get());
        simpleBlock(block.get(), this.models().cubeBottomTop(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                TextureMapping.getBlockTexture(block.get(), "_side"),
                resourcelocation,
                TextureMapping.getBlockTexture(block.get(), "_top")
        ));
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + block.getId().getPath()));
    }

    private void buttonItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        itemModels().withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private void fenceItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        itemModels().withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private void fenceGateItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        itemModels().withExistingParent(block.getId().getPath(), mcLoc("block/fence_gate_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private void wallItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        itemModels().withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private void crossBlock(DeferredBlock<?> block) {
        simpleBlock(block.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), blockTexture(block.get())));
        itemModels().getBuilder(BuiltInRegistries.BLOCK.getKey(block.get()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block.get()));
    }

    private void pottedCrossBlock(DeferredBlock<?> block) {
        simpleBlock(block.get(), models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                mcLoc("block/flower_pot_cross")).texture("plant", blockBVWTexture(((FlowerPotBlock) block.get()).getPotted())));
        itemModels().simpleBlockItem(block.get());
    }

    private void directionalCrossBlock(DeferredBlock<?> block) {
        Function<BlockState, ModelFile> modelFunc = (state) -> models().cross(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), blockTexture(block.get()));
        this.getVariantBuilder(block.get()).forAllStates((state) -> {
            Direction dir = state.getValue(BlockStateProperties.FACING);
            return ConfiguredModel.builder().modelFile(modelFunc.apply(state)).rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 90 : 0)).rotationY(dir.getAxis().isVertical() ? 0 : ((int) dir.toYRot() + 180) % 360).build();
        });
        itemModels().getBuilder(BuiltInRegistries.BLOCK.getKey(block.get()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block.get()));
    }

    private ResourceLocation blockBVWTexture(Block block) {
        return VariousWorld.createVWLocation(ModelProvider.BLOCK_FOLDER + "/" + BuiltInRegistries.BLOCK.getKey(block).getPath());
    }

    private void crossWithPropertyBlock(DeferredBlock<?> block) {
        if (block.get() instanceof SodaliteWartBlock)
            this.getVariantBuilder(block.get()).partialState().with(SodaliteWartBlock.CLOSED, true).modelForState()
                    .modelFile(models().cross(BuiltInRegistries.BLOCK.getKey(block.get()).getPath() + "_closed", extend(blockTexture(block.get()), "_closed"))).addModel().
                    partialState().with(SodaliteWartBlock.CLOSED, false).modelForState()
                    .modelFile(models().cross(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), blockTexture(block.get()))).addModel();
        else
            simpleBlock(block.get(),
                    models().cross(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), blockTexture(block.get())));
        itemModels().getBuilder(BuiltInRegistries.BLOCK.getKey(block.get()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block.get()));
    }

    private void logBlockWithItem(DeferredBlock<?> block) {
        logBlock((RotatedPillarBlock) block.get());
        simpleBlockItem(block.get(), this.models().cubeColumn(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                this.blockTexture(block.get()), this.extend(this.blockTexture(block.get()), "_top")));
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        String var10000 = rl.getNamespace();
        String var10001 = rl.getPath();
        return ResourceLocation.fromNamespaceAndPath(var10000, var10001 + suffix);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + deferredBlock.getId().getPath() + appendix));
    }
}
