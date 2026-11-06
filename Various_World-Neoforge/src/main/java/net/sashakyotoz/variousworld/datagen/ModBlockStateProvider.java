package net.sashakyotoz.variousworld.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.lcc.sollib.api.common.registry.data.block.BlockModel;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.blocks.custom.SodaliteWartBlock;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.Map;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VariousWorld.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        VWRegistryHelper.forEach(BlockHolder.class, holder -> {
            if (!holder.hasModel())
                return;
            Block block = holder.get();
            switch (holder.getModel()) {
                case CUBE -> simpleBlockWithItem(block, cubeAll(block));
                case PILLAR -> logBlockWithItem(block);
                case CROSS -> crossBlock(block);
                case CROSS_DIRECTIONAL -> directionalCrossBlock(block);
                case CROSS_POTTED -> pottedCrossBlock(block);
                case TRAPDOOR -> {
                    trapdoorBlock((TrapDoorBlock) block, blockTexture(block), true);
                    blockItem(block, "_bottom");
                }
                case DOOR -> {
                    doorBlock((DoorBlock) block, extend(blockTexture(block), "_top"), extend(blockTexture(block), "_bottom"));
                    itemModels().basicItem(block.asItem());
                }
                default -> {
                }
            }
        });
        VWRegistryHelper.forEach(BlockHolder.class, holder -> {
            if (holder.getBlockSet().isEmpty())
                return;
            Block base = holder.get();
            ResourceLocation texture = blockTexture(base);
            simpleBlockWithItem(base, cubeAll(base));
            for (Map.Entry<BlockModel, BlockHolder> entry : holder.getBlockSet().entrySet()) {
                Block child = entry.getValue().get();
                switch (entry.getKey()) {
                    case STAIRS -> {
                        stairsBlock((StairBlock) child, texture);
                        blockItem(child);
                    }
                    case SLAB -> {
                        slabBlock((SlabBlock) child, texture, texture);
                        blockItem(child);
                    }
                    case BUTTON -> {
                        buttonBlock((ButtonBlock) child, texture);
                        inventoryItem(child, base, "block/button_inventory", "texture");
                    }
                    case PRESSURE_PLATE -> {
                        pressurePlateBlock((PressurePlateBlock) child, texture);
                        blockItem(child);
                    }
                    case FENCE -> {
                        fenceBlock((FenceBlock) child, texture);
                        inventoryItem(child, base, "block/fence_inventory", "texture");
                    }
                    case FENCE_GATE -> {
                        fenceGateBlock((FenceGateBlock) child, texture);
                        blockItem(child);
                    }
                    case WALL -> {
                        wallBlock((WallBlock) child, texture);
                        inventoryItem(child, base, "block/wall_inventory", "wall");
                    }
                    default -> {
                    }
                }
            }
        });
        for (VWBlocks.WoodSigns signs : VWBlocks.SIGNS) {
            ResourceLocation texture = blockTexture(signs.planks().get());
            signBlock((StandingSignBlock) signs.sign().get(), (WallSignBlock) signs.wallSign().get(), texture);
            hangingSignBlock((CeilingHangingSignBlock) signs.hangingSign().get(), (WallHangingSignBlock) signs.wallHangingSign().get(), texture);
        }
        grassBlock(VWBlocks.CRYSTALIC_GRASS_BLOCK.get());
        crossWithPropertyBlock(VWBlocks.SODALITE_WART.get());
        stateFromBlockModel(VWBlocks.GEMSMITH_TABLE.get());
        stateFromBlockModel(VWBlocks.ARTIFACT_TABLE.get());
        stateFromBlockModel(VWBlocks.DISASSEMBLY_TABLE.get());
        furnaceFromBlockModels(VWBlocks.GEMSMITH_FURNACE.get());

        petalsBlock(VWBlocks.BLUE_JACARANDA_PETALS.get());
    }

    private void inventoryItem(Block block, Block baseBlock, String parent, String textureKey) {
        itemModels().withExistingParent(name(block), mcLoc(parent))
                .texture(textureKey, VariousWorld.createVWLocation("block/" + name(baseBlock)));
    }

    private void grassBlock(Block block) {
        ResourceLocation resourcelocation = TextureMapping.getBlockTexture(VWBlocks.DIRT_WITH_CRYSTALS.get());
        simpleBlock(block, this.models().cubeBottomTop(name(block),
                TextureMapping.getBlockTexture(block, "_side"),
                resourcelocation,
                TextureMapping.getBlockTexture(block, "_top")
        ));
        blockItem(block);
    }

    private void stateFromBlockModel(Block block) {
        simpleBlock(block, new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + name(block)));
        blockItem(block);
    }

    private void furnaceFromBlockModels(Block block) {
        Function<BlockState, ModelFile> furnaceFired = (state) -> new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + name(block) + "_fired");
        Function<BlockState, ModelFile> furnace = (state) -> new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + name(block));
        this.getVariantBuilder(block).forAllStates(state -> {
            int rotationX = 0;
            int rotationY = 0;
            switch (state.getValue(BlockStateProperties.FACING)) {
                case DOWN -> rotationX = 90;
                case UP -> rotationX = 270;
                case EAST -> rotationY = 90;
                case SOUTH -> rotationY = 180;
                case WEST -> rotationY = 270;
            }
            ModelFile selectedModel = state.getValue(BlockStateProperties.LIT)
                    ? furnaceFired.apply(state)
                    : furnace.apply(state);

            return ConfiguredModel.builder()
                    .modelFile(selectedModel)
                    .rotationX(rotationX)
                    .rotationY(rotationY)
                    .build();
        });
        blockItem(block);
    }

    private void crossBlock(Block block) {
        simpleBlock(block, models().cross(name(block), blockTexture(block)));
        itemModels().getBuilder(key(block).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block));
    }

    private void pottedCrossBlock(Block block) {
        simpleBlock(block, models().withExistingParent(name(block),
                mcLoc("block/flower_pot_cross")).texture("plant", blockBVWTexture(((FlowerPotBlock) block).getPotted())));
        itemModels().simpleBlockItem(block);
    }

    private void directionalCrossBlock(Block block) {
        Function<BlockState, ModelFile> modelFunc = (state) -> models().cross(name(block), blockTexture(block));
        this.getVariantBuilder(block).forAllStates((state) -> {
            Direction dir = state.getValue(BlockStateProperties.FACING);
            return ConfiguredModel.builder().modelFile(modelFunc.apply(state)).rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 90 : 0)).rotationY(dir.getAxis().isVertical() ? 0 : ((int) dir.toYRot() + 180) % 360).build();
        });
        itemModels().getBuilder(key(block).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block));
    }

    private ResourceLocation blockBVWTexture(Block block) {
        return VariousWorld.createVWLocation(ModelProvider.BLOCK_FOLDER + "/" + BuiltInRegistries.BLOCK.getKey(block).getPath());
    }

    private void crossWithPropertyBlock(Block block) {
        if (block instanceof SodaliteWartBlock)
            this.getVariantBuilder(block).partialState().with(SodaliteWartBlock.CLOSED, true).modelForState()
                    .modelFile(models().cross(name(block) + "_closed", extend(blockTexture(block), "_closed"))).addModel().
                    partialState().with(SodaliteWartBlock.CLOSED, false).modelForState()
                    .modelFile(models().cross(name(block), blockTexture(block))).addModel();
        else
            simpleBlock(block, models().cross(name(block), blockTexture(block)));
        itemModels().getBuilder(key(block).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", blockTexture(block));
    }

    private void petalsBlock(Block block) {
        models().withExistingParent(name(block), "minecraft:block/flowerbed_" + 1)
                .texture("flowerbed", modLoc("block/" + name(block)))
                .texture("stem", modLoc("block/" + name(block) + "_stem"));
        for (int i = 1; i < 5; i++) {
            models().withExistingParent(name(block) + "_" + i, "minecraft:block/flowerbed_" + i)
                    .texture("flowerbed", modLoc("block/" + name(block)))
                    .texture("stem", modLoc("block/" + name(block) + "_stem"));
        }
        MultiPartBlockStateBuilder multipart = getMultipartBuilder(block);
        for (int amount = 1; amount < 5; amount++) {
            ModelFile model = models().getExistingFile(modLoc("block/" + name(block) + "_" + amount));
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                int yRot = switch (dir) {
                    case EAST -> 90;
                    case SOUTH -> 180;
                    case WEST -> 270;
                    default -> 0;
                };
                switch (amount) {
                    case 2 -> multipart
                            .part()
                            .modelFile(model)
                            .rotationY(yRot)
                            .addModel()
                            .condition(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4)
                            .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                            .end();
                    case 3 -> multipart
                            .part()
                            .modelFile(model)
                            .rotationY(yRot)
                            .addModel()
                            .condition(BlockStateProperties.FLOWER_AMOUNT, 3, 4)
                            .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                            .end();
                    case 4 -> multipart
                            .part()
                            .modelFile(model)
                            .rotationY(yRot)
                            .addModel()
                            .condition(BlockStateProperties.FLOWER_AMOUNT, 4)
                            .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                            .end();
                    default -> multipart
                            .part()
                            .modelFile(model)
                            .rotationY(yRot)
                            .addModel()
                            .condition(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4)
                            .condition(BlockStateProperties.HORIZONTAL_FACING, dir)
                            .end();
                }
            }
        }
        itemModels().basicItem(block.asItem());
    }

    public String name(Block block) {
        return this.key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private void logBlockWithItem(Block block) {
        logBlock((RotatedPillarBlock) block);
        simpleBlockItem(block, this.models().cubeColumn(name(block), this.blockTexture(block), this.extend(this.blockTexture(block), "_top")));
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
    }

    private void blockItem(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + name(block)));
    }

    private void blockItem(Block block, String appendix) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(VariousWorld.MOD_ID + ":block/" + name(block) + appendix));
    }
}
