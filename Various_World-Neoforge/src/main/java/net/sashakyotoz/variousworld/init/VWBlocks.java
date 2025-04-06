package net.sashakyotoz.variousworld.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.sashakyotoz.variousworld.common.blocks.ModRotatedPillarBlock;
import net.sashakyotoz.variousworld.common.blocks.ModWoodType;
import net.sashakyotoz.variousworld.common.blocks.custom.BuddingSodaliteBlock;
import net.sashakyotoz.variousworld.common.blocks.custom.CrystallineGrassBlock;
import net.sashakyotoz.variousworld.common.blocks.custom.SodaliteWartBlock;
import net.sashakyotoz.variousworld.common.blocks.entities.ModHangingSignBlockEntity;
import net.sashakyotoz.variousworld.common.blocks.entities.ModSignBlockEntity;
import net.sashakyotoz.variousworld.common.blocks.signs.ModHangingSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModHangingWallSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModStandingSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModWallSignBlock;
import net.sashakyotoz.variousworld.common.world.VWTreeGrowers;

import java.util.Map;
import java.util.function.ToIntFunction;

@SuppressWarnings("unchecked")
public class VWBlocks {
    public static void init() {
        VWRegistryHelper.registerSet(CRYSTALIC_OAK_PLANKS, Map.of(
                VWRegistryHelper.Models.STAIRS, CRYSTALIC_OAK_STAIRS,
                VWRegistryHelper.Models.SLAB, CRYSTALIC_OAK_SLAB,
                VWRegistryHelper.Models.BUTTON, CRYSTALIC_OAK_BUTTON,
                VWRegistryHelper.Models.PRESSURE_PLATE, CRYSTALIC_OAK_PRESSURE_PLATE,
                VWRegistryHelper.Models.FENCE, CRYSTALIC_OAK_FENCE,
                VWRegistryHelper.Models.FENCE_GATE, CRYSTALIC_OAK_FENCE_GATE,
                VWRegistryHelper.Models.SIGN, CRYSTALIC_OAK_SIGN,
                VWRegistryHelper.Models.WALL_SIGN, CRYSTALIC_OAK_WALL_SIGN,
                VWRegistryHelper.Models.HANGING_SIGN, CRYSTALIC_OAK_HANGING_SIGN,
                VWRegistryHelper.Models.WALL_HANGING_SIGN, CRYSTALIC_OAK_HANGING_WALL_SIGN
        ));
    }

    private static final BlockBehaviour.Properties crystalWood = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2);
    private static final BlockBehaviour.Properties crystalDirt = BlockBehaviour.Properties.of().strength(0.5F).requiresCorrectToolForDrops();
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_CRYSTALIC_OAK_LOG = VWRegistryHelper.ofBlock("stripped_crystalic_oak_log", () -> new ModRotatedPillarBlock(crystalWood))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model(VWRegistryHelper.Models.PILLAR).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> CRYSTALIC_OAK_LOG = VWRegistryHelper.ofBlock("crystalic_oak_log", () -> new ModRotatedPillarBlock(crystalWood))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model(VWRegistryHelper.Models.PILLAR).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_CRYSTALIC_OAK_WOOD = VWRegistryHelper.ofBlock("stripped_crystalic_oak_wood", () -> new ModRotatedPillarBlock(crystalWood))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model().drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> CRYSTALIC_OAK_WOOD = VWRegistryHelper.ofBlock("crystalic_oak_wood", () -> new ModRotatedPillarBlock(crystalWood))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model().drop().build();
    public static final DeferredBlock<LeavesBlock> CRYSTALIC_OAK_LEAVES = VWRegistryHelper.ofBlock("crystalic_oak_leaves", () -> new LeavesBlock(crystalWood.instabreak().noOcclusion())).tag(BlockTags.LEAVES).tool("_hoe").model().cutout().build();
    public static final DeferredBlock<Block> CRYSTALIC_OAK_PLANKS = VWRegistryHelper.ofBlock("crystalic_oak_planks", () -> new Block(crystalWood)).tag(BlockTags.PLANKS).tool("_axe").drop().build();
    public static final DeferredBlock<StairBlock> CRYSTALIC_OAK_STAIRS = VWRegistryHelper.ofBlock("crystalic_oak_stairs", () -> new StairBlock(CRYSTALIC_OAK_PLANKS.get().defaultBlockState(), crystalWood)).tool("_axe").tag(BlockTags.WOODEN_STAIRS).drop().build();
    public static final DeferredBlock<SlabBlock> CRYSTALIC_OAK_SLAB = VWRegistryHelper.ofBlock("crystalic_oak_slab", () -> new SlabBlock(crystalWood)).tag(BlockTags.WOODEN_SLABS).tool("_axe").drop().build();
    public static final DeferredBlock<PressurePlateBlock> CRYSTALIC_OAK_PRESSURE_PLATE = VWRegistryHelper.ofBlock("crystalic_pressure_plate", () -> new PressurePlateBlock(BlockSetType.ACACIA, crystalWood)).tool("_axe").tag(BlockTags.WOODEN_PRESSURE_PLATES).drop().build();
    public static final DeferredBlock<ButtonBlock> CRYSTALIC_OAK_BUTTON = VWRegistryHelper.ofBlock("crystalic_oak_button", () -> new ButtonBlock(BlockSetType.ACACIA, 30, crystalWood)).tool("_axe").tag(BlockTags.WOODEN_BUTTONS).drop().build();
    public static final DeferredBlock<FenceBlock> CRYSTALIC_OAK_FENCE = VWRegistryHelper.ofBlock("crystalic_oak_fence", () -> new FenceBlock(crystalWood)).tag(BlockTags.FENCES).tool("_axe").drop().build();
    public static final DeferredBlock<FenceGateBlock> CRYSTALIC_OAK_FENCE_GATE = VWRegistryHelper.ofBlock("crystalic_oak_fence_gate", () -> new FenceGateBlock(ModWoodType.CRYSTALIC_OAK, crystalWood)).tag(BlockTags.FENCE_GATES).tool("_axe").drop().build();
    public static final DeferredBlock<DoorBlock> CRYSTALIC_OAK_DOOR = VWRegistryHelper.ofBlock("crystalic_oak_door", () -> new DoorBlock(BlockSetType.ACACIA, crystalWood), true, true).tag(BlockTags.WOODEN_DOORS).model(VWRegistryHelper.Models.DOOR).tool("_axe").drop().build();
    public static final DeferredBlock<TrapDoorBlock> CRYSTALIC_OAK_TRAPDOOR = VWRegistryHelper.ofBlock("crystalic_oak_trapdoor", () -> new TrapDoorBlock(BlockSetType.ACACIA, crystalWood.noOcclusion())).tag(BlockTags.WOODEN_TRAPDOORS).tool("_axe").cutout().model(VWRegistryHelper.Models.TRAPDOOR).drop().build();
    public static final DeferredBlock<ModStandingSignBlock> CRYSTALIC_OAK_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodType.CRYSTALIC_OAK), false).tag(BlockTags.STANDING_SIGNS).drop().build();
    public static final DeferredBlock<ModWallSignBlock> CRYSTALIC_OAK_WALL_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN).lootFrom(CRYSTALIC_OAK_SIGN), ModWoodType.CRYSTALIC_OAK), false).build();
    public static final DeferredBlock<ModHangingSignBlock> CRYSTALIC_OAK_HANGING_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_hanging_sign", () -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodType.CRYSTALIC_OAK), false).tag(BlockTags.CEILING_HANGING_SIGNS).drop().build();
    public static final DeferredBlock<ModHangingWallSignBlock> CRYSTALIC_OAK_HANGING_WALL_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_hanging_wall_sign", () -> new ModHangingWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).lootFrom(CRYSTALIC_OAK_HANGING_SIGN), ModWoodType.CRYSTALIC_OAK), false).build();
    public static final DeferredBlock<SaplingBlock> CRYSTALIC_OAK_SAPLING = VWRegistryHelper.ofBlock("crystalic_oak_sapling", () -> new SaplingBlock(VWTreeGrowers.CRYSTALIC_TREE, crystalWood)).tag(BlockTags.SAPLINGS).tool("_hoe").model(VWRegistryHelper.Models.CROSS).drop().cutout().build();
    public static final DeferredBlock<Block> DIRT_WITH_CRYSTALS = VWRegistryHelper.ofBlock("dirt_with_crystals", () -> new Block(crystalDirt.mapColor(MapColor.DIRT))).tag(BlockTags.DIRT, BlockTags.SNIFFER_DIGGABLE_BLOCK).tool("stone_shovel").model().drop().build();
    public static final DeferredBlock<CrystallineGrassBlock> CRYSTALIC_GRASS_BLOCK = VWRegistryHelper.ofBlock("crystalic_grass_block", () -> new CrystallineGrassBlock(crystalDirt.mapColor(MapColor.COLOR_CYAN))).tag(BlockTags.DIRT, BlockTags.SNIFFER_DIGGABLE_BLOCK).tool("stone_shovel").model(VWRegistryHelper.Models.GRASS).drop().build();
    public static final DeferredBlock<SodaliteWartBlock> SODALITE_WART = VWRegistryHelper.ofBlock("sodalite_wart", () -> new SodaliteWartBlock(MobEffects.GLOWING, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.PEONY).randomTicks()
            .emissiveRendering((blockState, blockGetter, blockPos) -> blockState.getValue(SodaliteWartBlock.CLOSED)).lightLevel(lightLevelFromBlockState(6, SodaliteWartBlock.CLOSED)))).cutout().tag(BlockTags.FLOWERS).build();
    public static final DeferredBlock<FlowerPotBlock> POTTED_SODALITE_WART = VWRegistryHelper.ofBlock("potted_sodalite_wart", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SODALITE_WART, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)))
            .model(VWRegistryHelper.Models.CROSS_POTTED).cutout().tag(BlockTags.FLOWER_POTS).build();
    public static final DeferredBlock<FlowerPotBlock> POTTED_CRYSTALIC_OAK_SAPLING = VWRegistryHelper.ofBlock("potted_crystalic_oak_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CRYSTALIC_OAK_SAPLING, BlockBehaviour.Properties.ofFullCopy(CRYSTALIC_OAK_SAPLING.get())))
            .model(VWRegistryHelper.Models.CROSS_POTTED).cutout().tag(BlockTags.FLOWER_POTS).build();
    public static final DeferredBlock<Block> SODALITE_BLOCK = VWRegistryHelper.ofBlock("sodalite_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()))
            .tag(BlockTags.CRYSTAL_SOUND_BLOCKS).tool("stone_pickaxe").model().drop().build();
    public static final DeferredBlock<BuddingSodaliteBlock> BUDDING_SODALITE = VWRegistryHelper.ofBlock("budding_sodalite", () -> new BuddingSodaliteBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).randomTicks().strength(2F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()))
            .tag(BlockTags.CRYSTAL_SOUND_BLOCKS).tool("stone_pickaxe").model().build();
    public static final DeferredBlock<AmethystClusterBlock> SODALITE_CLUSTER = VWRegistryHelper.ofBlock("sodalite_cluster", () -> new AmethystClusterBlock(8.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(2F)))
            .model(VWRegistryHelper.Models.DIRECTIONAL_CROSS).tool("stone_pickaxe").cutout().build();
    public static final DeferredBlock<AmethystClusterBlock> MEDIUM_SODALITE_BUD = VWRegistryHelper.ofBlock("medium_sodalite_bud", () -> new AmethystClusterBlock(6.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(SODALITE_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD)))
            .model(VWRegistryHelper.Models.DIRECTIONAL_CROSS).tool("stone_pickaxe").cutout().build();
    public static final DeferredBlock<AmethystClusterBlock> SMALL_SODALITE_BUD = VWRegistryHelper.ofBlock("small_sodalite_bud", () -> new AmethystClusterBlock(4.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy(SODALITE_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD)))
            .model(VWRegistryHelper.Models.DIRECTIONAL_CROSS).tool("stone_pickaxe").cutout().build();

    public static ToIntFunction<BlockState> lightLevelFromBlockState(int litLevel, BooleanProperty property) {
        return state -> state.getValue(property) ? litLevel : 0;
    }

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> MOD_SIGN = VWRegistryHelper.BLOCK_ENTITIES.register("mod_signs", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
            VWBlocks.CRYSTALIC_OAK_SIGN.get(), VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = VWRegistryHelper.BLOCK_ENTITIES.register("mod_hanging_sign", () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
            VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(),
            VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get()
    ).build(null));
}