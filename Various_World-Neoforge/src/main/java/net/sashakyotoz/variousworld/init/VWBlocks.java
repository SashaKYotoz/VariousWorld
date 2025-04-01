package net.sashakyotoz.variousworld.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sashakyotoz.variousworld.common.blocks.ModRotatedPillarBlock;

@SuppressWarnings("unchecked")
public class VWBlocks {
    public static void init() {
    }

    private static final BlockBehaviour.Properties crystalWood = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2, 2);
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_CRYSTALIC_LOG = VWRegistryHelper.ofBlock("stripped_crystalic_log", () -> new ModRotatedPillarBlock(crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> CRYSTALIC_LOG = VWRegistryHelper.ofBlock("crystalic_log", () -> new ModRotatedPillarBlock(crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_CRYSTALIC_WOOD = VWRegistryHelper.ofBlock("stripped_crystalic_wood", () -> new ModRotatedPillarBlock(crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<ModRotatedPillarBlock> CRYSTALIC_WOOD = VWRegistryHelper.ofBlock("crystalic_wood", () -> new ModRotatedPillarBlock(crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<LeavesBlock> CRYSTALIC_LEAVES = VWRegistryHelper.ofBlock("crystalic_leaves", () -> new LeavesBlock(crystalWood.instabreak())).build();
    public static final DeferredBlock<Block> CRYSTALIC_PLANKS = VWRegistryHelper.ofBlock("crystalic_planks", () -> new Block(crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<StairBlock> CRYSTALIC_STAIRS = VWRegistryHelper.ofBlock("crystalic_stairs", () -> new StairBlock(CRYSTALIC_PLANKS.get().defaultBlockState(), crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<SlabBlock> CRYSTALIC_SLAB = VWRegistryHelper.ofBlock("crystalic_slab", () -> new SlabBlock(crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<PressurePlateBlock> CRYSTALIC_PRESSURE_PLATE = VWRegistryHelper.ofBlock("crystalic_pressure_plate", () -> new PressurePlateBlock(BlockSetType.ACACIA, crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<ButtonBlock> CRYSTALIC_BUTTON = VWRegistryHelper.ofBlock("crystalic_button", () -> new ButtonBlock(BlockSetType.ACACIA, 30, crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<FenceBlock> CRYSTALIC_FENCE = VWRegistryHelper.ofBlock("crystalic_fence", () -> new FenceBlock(crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<FenceGateBlock> CRYSTALIC_FENCE_GATE = VWRegistryHelper.ofBlock("crystalic_fence_gate", () -> new FenceGateBlock(WoodType.ACACIA, crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<DoorBlock> CRYSTALIC_DOOR = VWRegistryHelper.ofBlock("crystalic_door", () -> new DoorBlock(BlockSetType.ACACIA, crystalWood), true, true).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<TrapDoorBlock> CRYSTALIC_TRAPDOOR = VWRegistryHelper.ofBlock("crystalic_trapdoor", () -> new TrapDoorBlock(BlockSetType.ACACIA, crystalWood)).tag(BlockTags.MINEABLE_WITH_AXE).drop().build();
    public static final DeferredBlock<SaplingBlock> CRYSTALIC_SAPLING = VWRegistryHelper.ofBlock("crystalic_sapling", () -> new SaplingBlock(TreeGrower.ACACIA,crystalWood)).drop().build();
}