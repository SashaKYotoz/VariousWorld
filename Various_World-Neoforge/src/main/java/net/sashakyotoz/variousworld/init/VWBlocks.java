package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.common.blocks.ModWoodType;
import net.sashakyotoz.variousworld.common.blocks.custom.*;
import net.sashakyotoz.variousworld.common.blocks.entities.*;
import net.sashakyotoz.variousworld.common.blocks.signs.ModHangingSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModHangingWallSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModStandingSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModWallSignBlock;
import net.sashakyotoz.variousworld.common.world.VWTreeGrowers;

import java.util.Map;
import java.util.function.Supplier;
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
        VWRegistryHelper.registerSet(BLUE_JACARANDA_PLANKS, Map.of(
                VWRegistryHelper.Models.STAIRS, BLUE_JACARANDA_STAIRS,
                VWRegistryHelper.Models.SLAB, BLUE_JACARANDA_SLAB,
                VWRegistryHelper.Models.BUTTON, BLUE_JACARANDA_BUTTON,
                VWRegistryHelper.Models.PRESSURE_PLATE, BLUE_JACARANDA_PRESSURE_PLATE,
                VWRegistryHelper.Models.FENCE, BLUE_JACARANDA_FENCE,
                VWRegistryHelper.Models.FENCE_GATE, BLUE_JACARANDA_FENCE_GATE,
                VWRegistryHelper.Models.SIGN, BLUE_JACARANDA_SIGN,
                VWRegistryHelper.Models.WALL_SIGN, BLUE_JACARANDA_WALL_SIGN,
                VWRegistryHelper.Models.HANGING_SIGN, BLUE_JACARANDA_HANGING_SIGN,
                VWRegistryHelper.Models.WALL_HANGING_SIGN, BLUE_JACARANDA_HANGING_WALL_SIGN
        ));
    }

    private static final BlockBehaviour.Properties crystalWood = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().strength(2);
    private static final BlockBehaviour.Properties jacarandaWood = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).ignitedByLava().strength(1.5f);
    private static final BlockBehaviour.Properties crystalDirt = BlockBehaviour.Properties.of().strength(0.5F).requiresCorrectToolForDrops();
    //crystalic oak
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_CRYSTALIC_OAK_LOG = VWRegistryHelper.ofBlock("stripped_crystalic_oak_log", registry -> new ModRotatedPillarBlock(crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model(VWRegistryHelper.Models.PILLAR).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> CRYSTALIC_OAK_LOG = VWRegistryHelper.ofBlock("crystalic_oak_log", registry -> new ModRotatedPillarBlock(crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model(VWRegistryHelper.Models.PILLAR).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_CRYSTALIC_OAK_WOOD = VWRegistryHelper.ofBlock("stripped_crystalic_oak_wood", registry -> new ModRotatedPillarBlock(crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model().drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> CRYSTALIC_OAK_WOOD = VWRegistryHelper.ofBlock("crystalic_oak_wood", registry -> new ModRotatedPillarBlock(crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model().drop().build();
    public static final DeferredBlock<LeavesBlock> CRYSTALIC_OAK_LEAVES = VWRegistryHelper.ofBlock("crystalic_oak_leaves", registry -> new TintedParticleLeavesBlock(0.01F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.CHERRY_LEAVES).instabreak().noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.LEAVES).tool("_hoe").model().cutout().build();
    public static final DeferredBlock<Block> CRYSTALIC_OAK_PLANKS = VWRegistryHelper.ofBlock("crystalic_oak_planks", registry -> new Block(crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.PLANKS).tool("_axe").drop().build();
    public static final DeferredBlock<StairBlock> CRYSTALIC_OAK_STAIRS = VWRegistryHelper.ofBlock("crystalic_oak_stairs", registry -> new StairBlock(CRYSTALIC_OAK_PLANKS.get().defaultBlockState(), crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tool("_axe").tag(BlockTags.WOODEN_STAIRS).drop().build();
    public static final DeferredBlock<SlabBlock> CRYSTALIC_OAK_SLAB = VWRegistryHelper.ofBlock("crystalic_oak_slab", registry -> new SlabBlock(crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.WOODEN_SLABS).tool("_axe").drop().build();
    public static final DeferredBlock<PressurePlateBlock> CRYSTALIC_OAK_PRESSURE_PLATE = VWRegistryHelper.ofBlock("crystalic_oak_pressure_plate", registry -> new PressurePlateBlock(BlockSetType.ACACIA, crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tool("_axe").tag(BlockTags.WOODEN_PRESSURE_PLATES).drop().build();
    public static final DeferredBlock<ButtonBlock> CRYSTALIC_OAK_BUTTON = VWRegistryHelper.ofBlock("crystalic_oak_button", registry -> new ButtonBlock(BlockSetType.ACACIA, 30, crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tool("_axe").tag(BlockTags.WOODEN_BUTTONS).drop().build();
    public static final DeferredBlock<FenceBlock> CRYSTALIC_OAK_FENCE = VWRegistryHelper.ofBlock("crystalic_oak_fence", registry -> new FenceBlock(crystalWood)).tag(BlockTags.FENCES).tool("_axe").drop().build();
    public static final DeferredBlock<FenceGateBlock> CRYSTALIC_OAK_FENCE_GATE = VWRegistryHelper.ofBlock("crystalic_oak_fence_gate", registry -> new FenceGateBlock(ModWoodType.CRYSTALIC_OAK, crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.FENCE_GATES).tool("_axe").drop().build();
    public static final DeferredBlock<DoorBlock> CRYSTALIC_OAK_DOOR = VWRegistryHelper.ofBlock("crystalic_oak_door", registry -> new DoorBlock(BlockSetType.ACACIA, crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry))), true, true).tag(BlockTags.WOODEN_DOORS).model(VWRegistryHelper.Models.DOOR).tool("_axe").drop().build();
    public static final DeferredBlock<TrapDoorBlock> CRYSTALIC_OAK_TRAPDOOR = VWRegistryHelper.ofBlock("crystalic_oak_trapdoor", registry -> new TrapDoorBlock(BlockSetType.ACACIA, crystalWood.setId(ResourceKey.create(Registries.BLOCK, registry)).noOcclusion())).tag(BlockTags.WOODEN_TRAPDOORS).tool("_axe").cutout().model(VWRegistryHelper.Models.TRAPDOOR).drop().build();
    public static final DeferredBlock<ModStandingSignBlock> CRYSTALIC_OAK_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_sign", registry -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)), ModWoodType.CRYSTALIC_OAK), false).tag(BlockTags.STANDING_SIGNS).drop().build();
    public static final DeferredBlock<ModWallSignBlock> CRYSTALIC_OAK_WALL_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_wall_sign", registry -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)).overrideLootTable(CRYSTALIC_OAK_SIGN.get().getLootTable()), ModWoodType.CRYSTALIC_OAK), false).build();
    public static final DeferredBlock<ModHangingSignBlock> CRYSTALIC_OAK_HANGING_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_hanging_sign", registry -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)), ModWoodType.CRYSTALIC_OAK), false).tag(BlockTags.CEILING_HANGING_SIGNS).drop().build();
    public static final DeferredBlock<ModHangingWallSignBlock> CRYSTALIC_OAK_HANGING_WALL_SIGN = VWRegistryHelper.ofBlock("crystalic_oak_hanging_wall_sign", registry -> new ModHangingWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)).overrideLootTable(CRYSTALIC_OAK_HANGING_SIGN.get().getLootTable()), ModWoodType.CRYSTALIC_OAK), false).build();
    public static final DeferredBlock<SaplingBlock> CRYSTALIC_OAK_SAPLING = VWRegistryHelper.ofBlock("crystalic_oak_sapling", registry -> new SaplingBlock(VWTreeGrowers.CRYSTALIC_TREE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).setId(ResourceKey.create(Registries.BLOCK, registry)).ignitedByLava().instabreak().noCollission().randomTicks().sound(SoundType.CHERRY_SAPLING))).tag(BlockTags.SAPLINGS).tool("_hoe").model(VWRegistryHelper.Models.CROSS).drop().cutout().build();
    public static final DeferredBlock<Block> DIRT_WITH_CRYSTALS = VWRegistryHelper.ofBlock("dirt_with_crystals", registry -> new Block(crystalDirt.mapColor(MapColor.DIRT).setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.DIRT, BlockTags.SNIFFER_DIGGABLE_BLOCK).tool("stone_shovel").model().drop().build();
    public static final DeferredBlock<CrystallineGrassBlock> CRYSTALIC_GRASS_BLOCK = VWRegistryHelper.ofBlock("crystalic_grass_block", registry -> new CrystallineGrassBlock(crystalDirt.mapColor(MapColor.COLOR_CYAN).setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.DIRT, BlockTags.SNIFFER_DIGGABLE_BLOCK).tool("stone_shovel").model(VWRegistryHelper.Models.GRASS).build();
    public static final DeferredBlock<SodaliteWartBlock> SODALITE_WART = VWRegistryHelper.ofBlock("sodalite_wart", registry -> new SodaliteWartBlock(MobEffects.GLOWING, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.PEONY).setId(ResourceKey.create(Registries.BLOCK, registry)).randomTicks()
            .emissiveRendering((blockState, blockGetter, blockPos) -> blockState.getValue(SodaliteWartBlock.CLOSED)).lightLevel(lightLevelFromBlockState(6, SodaliteWartBlock.CLOSED)))).cutout().tag(BlockTags.FLOWERS).build();
    public static final DeferredBlock<FlowerPotBlock> POTTED_SODALITE_WART = VWRegistryHelper.ofBlock("potted_sodalite_wart", registry -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SODALITE_WART, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).setId(ResourceKey.create(Registries.BLOCK, registry))))
            .model(VWRegistryHelper.Models.CROSS_POTTED).cutout().tag(BlockTags.FLOWER_POTS).build();
    public static final DeferredBlock<FlowerPotBlock> POTTED_CRYSTALIC_OAK_SAPLING = VWRegistryHelper.ofBlock("potted_crystalic_oak_sapling", registry -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CRYSTALIC_OAK_SAPLING, BlockBehaviour.Properties.ofFullCopy(CRYSTALIC_OAK_SAPLING.get()).setId(ResourceKey.create(Registries.BLOCK, registry))))
            .model(VWRegistryHelper.Models.CROSS_POTTED).cutout().tag(BlockTags.FLOWER_POTS).build();
    //jacaranda
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_BLUE_JACARANDA_LOG = VWRegistryHelper.ofBlock("stripped_blue_jacaranda_log", registry -> new ModRotatedPillarBlock(jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model(VWRegistryHelper.Models.PILLAR).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> BLUE_JACARANDA_LOG = VWRegistryHelper.ofBlock("blue_jacaranda_log", registry -> new ModRotatedPillarBlock(jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model(VWRegistryHelper.Models.PILLAR).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_BLUE_JACARANDA_WOOD = VWRegistryHelper.ofBlock("stripped_blue_jacaranda_wood", registry -> new ModRotatedPillarBlock(jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model().drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> BLUE_JACARANDA_WOOD = VWRegistryHelper.ofBlock("blue_jacaranda_wood", registry -> new ModRotatedPillarBlock(jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tag(BlockTags.LOGS_THAT_BURN).tool("_axe").model().drop().build();
    public static final DeferredBlock<LeavesBlock> BLUE_JACARANDA_LEAVES = VWRegistryHelper.ofBlock("blue_jacaranda_leaves", registry -> new TintedParticleLeavesBlock(0.02F, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).setId(ResourceKey.create(Registries.BLOCK, registry)).ignitedByLava().sound(SoundType.CHERRY_LEAVES).instabreak().noOcclusion())).tag(BlockTags.LEAVES).tool("_hoe").model().cutout().build();
    public static final DeferredBlock<Block> BLUE_JACARANDA_PLANKS = VWRegistryHelper.ofBlock("blue_jacaranda_planks", registry -> new Block(jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.PLANKS).tool("_axe").drop().build();
    public static final DeferredBlock<StairBlock> BLUE_JACARANDA_STAIRS = VWRegistryHelper.ofBlock("blue_jacaranda_stairs", registry -> new StairBlock(CRYSTALIC_OAK_PLANKS.get().defaultBlockState(), jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tool("_axe").tag(BlockTags.WOODEN_STAIRS).drop().build();
    public static final DeferredBlock<SlabBlock> BLUE_JACARANDA_SLAB = VWRegistryHelper.ofBlock("blue_jacaranda_slab", registry -> new SlabBlock(jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.WOODEN_SLABS).tool("_axe").drop().build();
    public static final DeferredBlock<PressurePlateBlock> BLUE_JACARANDA_PRESSURE_PLATE = VWRegistryHelper.ofBlock("blue_jacaranda_pressure_plate", registry -> new PressurePlateBlock(BlockSetType.MANGROVE, jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tool("_axe").tag(BlockTags.WOODEN_PRESSURE_PLATES).drop().build();
    public static final DeferredBlock<ButtonBlock> BLUE_JACARANDA_BUTTON = VWRegistryHelper.ofBlock("blue_jacaranda_button", registry -> new ButtonBlock(BlockSetType.MANGROVE, 30, jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tool("_axe").tag(BlockTags.WOODEN_BUTTONS).drop().build();
    public static final DeferredBlock<FenceBlock> BLUE_JACARANDA_FENCE = VWRegistryHelper.ofBlock("blue_jacaranda_fence", registry -> new FenceBlock(jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.FENCES).tool("_axe").drop().build();
    public static final DeferredBlock<FenceGateBlock> BLUE_JACARANDA_FENCE_GATE = VWRegistryHelper.ofBlock("blue_jacaranda_fence_gate", registry -> new FenceGateBlock(ModWoodType.CRYSTALIC_OAK, jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.FENCE_GATES).tool("_axe").drop().build();
    public static final DeferredBlock<DoorBlock> BLUE_JACARANDA_DOOR = VWRegistryHelper.ofBlock("blue_jacaranda_door", registry -> new DoorBlock(BlockSetType.MANGROVE, jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry))), true, true).tag(BlockTags.WOODEN_DOORS).model(VWRegistryHelper.Models.DOOR).tool("_axe").cutout().drop().build();
    public static final DeferredBlock<TrapDoorBlock> BLUE_JACARANDA_TRAPDOOR = VWRegistryHelper.ofBlock("blue_jacaranda_trapdoor", registry -> new TrapDoorBlock(BlockSetType.MANGROVE, jacarandaWood.setId(ResourceKey.create(Registries.BLOCK, registry)).noOcclusion())).tag(BlockTags.WOODEN_TRAPDOORS).tool("_axe").model(VWRegistryHelper.Models.TRAPDOOR).drop().build();
    public static final DeferredBlock<ModStandingSignBlock> BLUE_JACARANDA_SIGN = VWRegistryHelper.ofBlock("blue_jacaranda_sign", registry -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)), ModWoodType.BLUE_JACARANDA), false).tag(BlockTags.STANDING_SIGNS).drop().build();
    public static final DeferredBlock<ModWallSignBlock> BLUE_JACARANDA_WALL_SIGN = VWRegistryHelper.ofBlock("blue_jacaranda_wall_sign", registry -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)).overrideLootTable(BLUE_JACARANDA_SIGN.get().getLootTable()), ModWoodType.BLUE_JACARANDA), false).build();
    public static final DeferredBlock<ModHangingSignBlock> BLUE_JACARANDA_HANGING_SIGN = VWRegistryHelper.ofBlock("blue_jacaranda_hanging_sign", registry -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)), ModWoodType.BLUE_JACARANDA), false).tag(BlockTags.CEILING_HANGING_SIGNS).drop().build();
    public static final DeferredBlock<ModHangingWallSignBlock> BLUE_JACARANDA_HANGING_WALL_SIGN = VWRegistryHelper.ofBlock("blue_jacaranda_hanging_wall_sign", registry -> new ModHangingWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN).setId(ResourceKey.create(Registries.BLOCK, registry)).overrideLootTable(BLUE_JACARANDA_HANGING_SIGN.get().getLootTable()), ModWoodType.BLUE_JACARANDA), false).build();
    public static final DeferredBlock<SaplingBlock> BLUE_JACARANDA_SAPLING = VWRegistryHelper.ofBlock("blue_jacaranda_sapling", registry -> new SaplingBlock(VWTreeGrowers.BLUE_JACARANDA_TREE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).setId(ResourceKey.create(Registries.BLOCK, registry)).ignitedByLava().instabreak().noCollission().randomTicks().sound(SoundType.MANGROVE_ROOTS))).tag(BlockTags.SAPLINGS).tool("_hoe").model(VWRegistryHelper.Models.CROSS).drop().cutout().build();
    public static final DeferredBlock<FlowerBedBlock> BLUE_JACARANDA_PETALS = VWRegistryHelper.ofBlock("blue_jacaranda_petals", registry -> new FlowerBedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS).mapColor(MapColor.TERRACOTTA_MAGENTA).setId(ResourceKey.create(Registries.BLOCK, registry)))).tag(BlockTags.FLOWERS).tool("_hoe").cutout().build();
    public static final DeferredBlock<FlowerPotBlock> POTTED_BLUE_JACARANDA_SAPLING = VWRegistryHelper.ofBlock("potted_blue_jacaranda_sapling", registry -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_JACARANDA_SAPLING, BlockBehaviour.Properties.ofFullCopy(BLUE_JACARANDA_SAPLING.get()).setId(ResourceKey.create(Registries.BLOCK, registry))))
            .model(VWRegistryHelper.Models.CROSS_POTTED).cutout().tag(BlockTags.FLOWER_POTS).build();
    //
    public static final DeferredBlock<Block> SODALITE_BLOCK = VWRegistryHelper.ofBlock("sodalite_block", registry -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).setId(ResourceKey.create(Registries.BLOCK, registry)).strength(2F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()))
            .tag(BlockTags.CRYSTAL_SOUND_BLOCKS).tool("stone_pickaxe").model().drop().build();
    public static final DeferredBlock<BuddingSodaliteBlock> BUDDING_SODALITE = VWRegistryHelper.ofBlock("budding_sodalite", registry -> new BuddingSodaliteBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).setId(ResourceKey.create(Registries.BLOCK, registry)).randomTicks().strength(2F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()))
            .tag(BlockTags.CRYSTAL_SOUND_BLOCKS).tool("stone_pickaxe").model().build();
    public static final DeferredBlock<AmethystClusterBlock> SODALITE_CLUSTER = VWRegistryHelper.ofBlock("sodalite_cluster", registry -> new AmethystClusterBlock(8.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).setId(ResourceKey.create(Registries.BLOCK, registry)).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(2F)))
            .model(VWRegistryHelper.Models.DIRECTIONAL_CROSS).tool("stone_pickaxe").cutout().build();
    public static final DeferredBlock<AmethystClusterBlock> MEDIUM_SODALITE_BUD = VWRegistryHelper.ofBlock("medium_sodalite_bud", registry -> new AmethystClusterBlock(6.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(SODALITE_CLUSTER.get()).setId(ResourceKey.create(Registries.BLOCK, registry)).sound(SoundType.MEDIUM_AMETHYST_BUD)))
            .model(VWRegistryHelper.Models.DIRECTIONAL_CROSS).tool("stone_pickaxe").cutout().build();
    public static final DeferredBlock<AmethystClusterBlock> SMALL_SODALITE_BUD = VWRegistryHelper.ofBlock("small_sodalite_bud", registry -> new AmethystClusterBlock(4.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy(SODALITE_CLUSTER.get()).setId(ResourceKey.create(Registries.BLOCK, registry)).sound(SoundType.SMALL_AMETHYST_BUD)))
            .model(VWRegistryHelper.Models.DIRECTIONAL_CROSS).tool("stone_pickaxe").cutout().build();

    public static final DeferredBlock<GemsmithTableBlock> GEMSMITH_TABLE = VWRegistryHelper.ofBlock("gemsmith_table", registry -> new GemsmithTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS).setId(ResourceKey.create(Registries.BLOCK, registry)).noOcclusion()))
            .tool("stone_pickaxe").drop().cutout().build();
    public static final DeferredBlock<GemsmithFurnaceBlock> GEMSMITH_FURNACE = VWRegistryHelper.ofBlock("gemsmith_furnace", registry -> new GemsmithFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS).setId(ResourceKey.create(Registries.BLOCK, registry))))
            .tool("stone_pickaxe").drop().build();
    public static final DeferredBlock<ArtifactTableBlock> ARTIFACT_TABLE = VWRegistryHelper.ofBlock("artifact_table", registry -> new ArtifactTableBlock(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, registry)).mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).noOcclusion().strength(2.5F).sound(SoundType.WOOD).ignitedByLava()))
            .tool("_axe").drop().translucent().build();

    public static ToIntFunction<BlockState> lightLevelFromBlockState(int litLevel, BooleanProperty property) {
        return state -> state.getValue(property) ? litLevel : 0;
    }

    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = VWRegistryHelper.BLOCK_ENTITIES.register("mod_signs", () -> new BlockEntityType<>(ModSignBlockEntity::new,
            VWBlocks.CRYSTALIC_OAK_SIGN.get(),
            VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get(),
            VWBlocks.BLUE_JACARANDA_SIGN.get(),
            VWBlocks.BLUE_JACARANDA_WALL_SIGN.get()
    ));
    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = VWRegistryHelper.BLOCK_ENTITIES.register("mod_hanging_sign", () -> new BlockEntityType<>(ModHangingSignBlockEntity::new,
            VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(),
            VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get(),
            VWBlocks.BLUE_JACARANDA_HANGING_SIGN.get(),
            VWBlocks.BLUE_JACARANDA_HANGING_WALL_SIGN.get()
    ));
    public static final Supplier<BlockEntityType<GemsmithTableBlockEntity>> GEMSMITH_TABLE_BE = VWRegistryHelper.BLOCK_ENTITIES.register("gemsmith_table", () -> new BlockEntityType<>(GemsmithTableBlockEntity::new,
            VWBlocks.GEMSMITH_TABLE.get()
    ));
    public static final Supplier<BlockEntityType<GemsmithFurnaceBlockEntity>> GEMSMITH_FURNACE_BE = VWRegistryHelper.BLOCK_ENTITIES.register("gemsmith_furnace", () -> new BlockEntityType<>(GemsmithFurnaceBlockEntity::new,
            VWBlocks.GEMSMITH_FURNACE.get()
    ));
    public static final Supplier<BlockEntityType<ArtifactTableBlockEntity>> ARTIFACT_TABLE_BE = VWRegistryHelper.BLOCK_ENTITIES.register("artifact_table", () -> new BlockEntityType<>(ArtifactTableBlockEntity::new,
            VWBlocks.ARTIFACT_TABLE.get()
    ));
}