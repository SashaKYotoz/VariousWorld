package net.sashakyotoz.variousworld.init;

import net.lcc.sollib.api.common.registry.data.block.BlockModel;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
import net.sashakyotoz.variousworld.common.blocks.ModWoodType;
import net.sashakyotoz.variousworld.common.blocks.custom.*;
import net.sashakyotoz.variousworld.common.blocks.entities.*;
import net.sashakyotoz.variousworld.common.blocks.signs.ModHangingSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModHangingWallSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModStandingSignBlock;
import net.sashakyotoz.variousworld.common.blocks.signs.ModWallSignBlock;
import net.sashakyotoz.variousworld.common.world.VWTreeGrowers;
import net.sashakyotoz.variousworld.init.holder.VWHolders;
import net.sashakyotoz.variousworld.platform.VWPlatform;

import java.util.List;
import java.util.function.ToIntFunction;

import static net.sashakyotoz.variousworld.init.VWRegistryHelper.*;

public class VWBlocks {
    public static void init() {
    }

    public record WoodSigns(BlockHolder planks, BlockHolder sign, BlockHolder wallSign, BlockHolder hangingSign, BlockHolder wallHangingSign) {
    }

    private static BlockBehaviour.Properties crystalWood() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().strength(2);
    }

    private static BlockBehaviour.Properties jacarandaWood() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).ignitedByLava().strength(1.5f);
    }

    private static BlockBehaviour.Properties crystalDirt() {
        return BlockBehaviour.Properties.of().strength(0.5F).requiresCorrectToolForDrops();
    }

    private static BlockHolder woodSet(BlockHolder planks, String prefix, BlockSetType setType, WoodType woodType) {
        return planks.withSetPrefix(prefix)
                .withStairs(stairs -> creative(stairs.withTags(BlockTags.WOODEN_STAIRS).withTags(tool("_axe")).dropsSelf()))
                .withSlab(slab -> creative(slab.withTags(BlockTags.WOODEN_SLABS).withTags(tool("_axe")).dropsSelf()))
                .withPressurePlate(setType)
                .withPressurePlate(plate -> creative(plate.withTags(BlockTags.WOODEN_PRESSURE_PLATES).withTags(tool("_axe")).dropsSelf()))
                .withButton(setType, 30)
                .withButton(button -> creative(button.withTags(BlockTags.WOODEN_BUTTONS).withTags(tool("_axe")).dropsSelf()))
                .withFence(fence -> creative(fence.withTags(tool("_axe")).dropsSelf()))
                .withFenceGate(woodType)
                .withFenceGate(gate -> creative(gate.withTags(tool("_axe")).dropsSelf()));
    }

    public static final BlockHolder STRIPPED_CRYSTALIC_OAK_LOG = blockWithItem("stripped_crystalic_oak_log", () -> new RotatedPillarBlock(crystalWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.PILLAR).dropsSelf();
    public static final BlockHolder CRYSTALIC_OAK_LOG = blockWithItem("crystalic_oak_log", () -> new RotatedPillarBlock(crystalWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.PILLAR).dropsSelf()
            .withStripResult(STRIPPED_CRYSTALIC_OAK_LOG);
    public static final BlockHolder STRIPPED_CRYSTALIC_OAK_WOOD = blockWithItem("stripped_crystalic_oak_wood", () -> new RotatedPillarBlock(crystalWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.CUBE).dropsSelf();
    public static final BlockHolder CRYSTALIC_OAK_WOOD = blockWithItem("crystalic_oak_wood", () -> new RotatedPillarBlock(crystalWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.CUBE).dropsSelf()
            .withStripResult(STRIPPED_CRYSTALIC_OAK_WOOD);
    public static final BlockHolder CRYSTALIC_OAK_LEAVES = blockWithItem("crystalic_oak_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().sound(SoundType.CHERRY_LEAVES).instabreak().noOcclusion()))
            .withTags(BlockTags.LEAVES).withTags(tool("_hoe")).withModel(BlockModel.CUBE).cutout();
    public static final BlockHolder CRYSTALIC_OAK_PLANKS = woodSet(blockWithItem("crystalic_oak_planks", () -> new Block(crystalWood()))
            .withTags(BlockTags.PLANKS).withTags(tool("_axe")).dropsSelf(), "crystalic_oak", BlockSetType.ACACIA, ModWoodType.CRYSTALIC_OAK);
    public static final BlockHolder CRYSTALIC_OAK_STAIRS = CRYSTALIC_OAK_PLANKS.getStairs();
    public static final BlockHolder CRYSTALIC_OAK_SLAB = CRYSTALIC_OAK_PLANKS.getSlab();
    public static final BlockHolder CRYSTALIC_OAK_PRESSURE_PLATE = CRYSTALIC_OAK_PLANKS.getPressurePlate();
    public static final BlockHolder CRYSTALIC_OAK_BUTTON = CRYSTALIC_OAK_PLANKS.getButton();
    public static final BlockHolder CRYSTALIC_OAK_FENCE = CRYSTALIC_OAK_PLANKS.getFence();
    public static final BlockHolder CRYSTALIC_OAK_FENCE_GATE = CRYSTALIC_OAK_PLANKS.getFenceGate();
    public static final BlockHolder CRYSTALIC_OAK_DOOR = doubleBlockWithItem("crystalic_oak_door", () -> new DoorBlock(BlockSetType.ACACIA, crystalWood().noOcclusion()))
            .withTags(BlockTags.WOODEN_DOORS).withTags(tool("_axe")).withModel(BlockModel.DOOR).dropsSelf();
    public static final BlockHolder CRYSTALIC_OAK_TRAPDOOR = blockWithItem("crystalic_oak_trapdoor", () -> new TrapDoorBlock(BlockSetType.ACACIA, crystalWood().noOcclusion()))
            .withTags(BlockTags.WOODEN_TRAPDOORS).withTags(tool("_axe")).cutout().withModel(BlockModel.TRAPDOOR).dropsSelf();
    public static final BlockHolder CRYSTALIC_OAK_SIGN = creative(block("crystalic_oak_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodType.CRYSTALIC_OAK))
            .withTags(BlockTags.STANDING_SIGNS).dropsSelf());
    public static final BlockHolder CRYSTALIC_OAK_WALL_SIGN = block("crystalic_oak_wall_sign", () -> new ModWallSignBlock(VWPlatform.INSTANCE.lootFrom(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), CRYSTALIC_OAK_SIGN), ModWoodType.CRYSTALIC_OAK));
    public static final BlockHolder CRYSTALIC_OAK_HANGING_SIGN = creative(block("crystalic_oak_hanging_sign", () -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodType.CRYSTALIC_OAK))
            .withTags(BlockTags.CEILING_HANGING_SIGNS).dropsSelf());
    public static final BlockHolder CRYSTALIC_OAK_HANGING_WALL_SIGN = block("crystalic_oak_hanging_wall_sign", () -> new ModHangingWallSignBlock(VWPlatform.INSTANCE.lootFrom(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), CRYSTALIC_OAK_HANGING_SIGN), ModWoodType.CRYSTALIC_OAK));
    public static final BlockHolder CRYSTALIC_OAK_SAPLING = blockWithItem("crystalic_oak_sapling", () -> new SaplingBlock(VWTreeGrowers.CRYSTALIC_TREE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instabreak().noCollission().randomTicks().sound(SoundType.CHERRY_SAPLING)))
            .withTags(BlockTags.SAPLINGS).withTags(tool("_hoe")).withModel(BlockModel.CROSS).dropsSelf().cutout();
    public static final BlockHolder DIRT_WITH_CRYSTALS = blockWithItem("dirt_with_crystals", () -> new Block(crystalDirt().mapColor(MapColor.DIRT)))
            .withTags(BlockTags.DIRT, BlockTags.SNIFFER_DIGGABLE_BLOCK).withTags(tool("stone_shovel")).withModel(BlockModel.CUBE).dropsSelf();
    public static final BlockHolder CRYSTALIC_GRASS_BLOCK = blockWithItem("crystalic_grass_block", () -> new CrystallineGrassBlock(crystalDirt().mapColor(MapColor.COLOR_CYAN)))
            .withTags(BlockTags.DIRT, BlockTags.SNIFFER_DIGGABLE_BLOCK).withTags(tool("stone_shovel"));
    public static final BlockHolder SODALITE_WART = blockWithItem("sodalite_wart", () -> new SodaliteWartBlock(MobEffects.GLOWING, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.PEONY).randomTicks()
            .emissiveRendering((blockState, blockGetter, blockPos) -> blockState.getValue(SodaliteWartBlock.CLOSED)).lightLevel(lightLevelFromBlockState(6, SodaliteWartBlock.CLOSED))))
            .cutout().withTags(BlockTags.FLOWERS);
    public static final BlockHolder POTTED_SODALITE_WART = hiddenBlockWithItem("potted_sodalite_wart", () -> VWPlatform.INSTANCE.createFlowerPot(SODALITE_WART, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)))
            .withModel(BlockModel.CROSS_POTTED).cutout().withTags(BlockTags.FLOWER_POTS);
    public static final BlockHolder POTTED_CRYSTALIC_OAK_SAPLING = hiddenBlockWithItem("potted_crystalic_oak_sapling", () -> VWPlatform.INSTANCE.createFlowerPot(CRYSTALIC_OAK_SAPLING, BlockBehaviour.Properties.ofFullCopy(CRYSTALIC_OAK_SAPLING.get())))
            .withModel(BlockModel.CROSS_POTTED).cutout().withTags(BlockTags.FLOWER_POTS);

    public static final BlockHolder STRIPPED_BLUE_JACARANDA_LOG = blockWithItem("stripped_blue_jacaranda_log", () -> new RotatedPillarBlock(jacarandaWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.PILLAR).dropsSelf();
    public static final BlockHolder BLUE_JACARANDA_LOG = blockWithItem("blue_jacaranda_log", () -> new RotatedPillarBlock(jacarandaWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.PILLAR).dropsSelf()
            .withStripResult(STRIPPED_BLUE_JACARANDA_LOG);
    public static final BlockHolder STRIPPED_BLUE_JACARANDA_WOOD = blockWithItem("stripped_blue_jacaranda_wood", () -> new RotatedPillarBlock(jacarandaWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.CUBE).dropsSelf();
    public static final BlockHolder BLUE_JACARANDA_WOOD = blockWithItem("blue_jacaranda_wood", () -> new RotatedPillarBlock(jacarandaWood()))
            .withTags(BlockTags.LOGS_THAT_BURN).withTags(tool("_axe")).withModel(BlockModel.CUBE).dropsSelf()
            .withStripResult(STRIPPED_BLUE_JACARANDA_WOOD);
    public static final BlockHolder BLUE_JACARANDA_LEAVES = blockWithItem("blue_jacaranda_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).ignitedByLava().sound(SoundType.CHERRY_LEAVES).instabreak().noOcclusion()))
            .withTags(BlockTags.LEAVES).withTags(tool("_hoe")).withModel(BlockModel.CUBE).cutout();
    public static final BlockHolder BLUE_JACARANDA_PLANKS = woodSet(blockWithItem("blue_jacaranda_planks", () -> new Block(jacarandaWood()))
            .withTags(BlockTags.PLANKS).withTags(tool("_axe")).dropsSelf(), "blue_jacaranda", BlockSetType.MANGROVE, ModWoodType.BLUE_JACARANDA);
    public static final BlockHolder BLUE_JACARANDA_STAIRS = BLUE_JACARANDA_PLANKS.getStairs();
    public static final BlockHolder BLUE_JACARANDA_SLAB = BLUE_JACARANDA_PLANKS.getSlab();
    public static final BlockHolder BLUE_JACARANDA_PRESSURE_PLATE = BLUE_JACARANDA_PLANKS.getPressurePlate();
    public static final BlockHolder BLUE_JACARANDA_BUTTON = BLUE_JACARANDA_PLANKS.getButton();
    public static final BlockHolder BLUE_JACARANDA_FENCE = BLUE_JACARANDA_PLANKS.getFence();
    public static final BlockHolder BLUE_JACARANDA_FENCE_GATE = BLUE_JACARANDA_PLANKS.getFenceGate();
    public static final BlockHolder BLUE_JACARANDA_DOOR = doubleBlockWithItem("blue_jacaranda_door", () -> new DoorBlock(BlockSetType.MANGROVE, jacarandaWood().noOcclusion()))
            .withTags(BlockTags.WOODEN_DOORS).withTags(tool("_axe")).withModel(BlockModel.DOOR).cutout().dropsSelf();
    public static final BlockHolder BLUE_JACARANDA_TRAPDOOR = blockWithItem("blue_jacaranda_trapdoor", () -> new TrapDoorBlock(BlockSetType.MANGROVE, jacarandaWood().noOcclusion()))
            .withTags(BlockTags.WOODEN_TRAPDOORS).withTags(tool("_axe")).withModel(BlockModel.TRAPDOOR).dropsSelf();
    public static final BlockHolder BLUE_JACARANDA_SIGN = creative(block("blue_jacaranda_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodType.BLUE_JACARANDA))
            .withTags(BlockTags.STANDING_SIGNS).dropsSelf());
    public static final BlockHolder BLUE_JACARANDA_WALL_SIGN = block("blue_jacaranda_wall_sign", () -> new ModWallSignBlock(VWPlatform.INSTANCE.lootFrom(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), BLUE_JACARANDA_SIGN), ModWoodType.BLUE_JACARANDA));
    public static final BlockHolder BLUE_JACARANDA_HANGING_SIGN = creative(block("blue_jacaranda_hanging_sign", () -> new ModHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodType.BLUE_JACARANDA))
            .withTags(BlockTags.CEILING_HANGING_SIGNS).dropsSelf());
    public static final BlockHolder BLUE_JACARANDA_HANGING_WALL_SIGN = block("blue_jacaranda_hanging_wall_sign", () -> new ModHangingWallSignBlock(VWPlatform.INSTANCE.lootFrom(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), BLUE_JACARANDA_HANGING_SIGN), ModWoodType.BLUE_JACARANDA));
    public static final BlockHolder BLUE_JACARANDA_SAPLING = blockWithItem("blue_jacaranda_sapling", () -> new SaplingBlock(VWTreeGrowers.BLUE_JACARANDA_TREE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).ignitedByLava().instabreak().noCollission().randomTicks().sound(SoundType.MANGROVE_ROOTS)))
            .withTags(BlockTags.SAPLINGS).withTags(tool("_hoe")).withModel(BlockModel.CROSS).dropsSelf().cutout();
    public static final BlockHolder BLUE_JACARANDA_PETALS = blockWithItem("blue_jacaranda_petals", () -> new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS).mapColor(MapColor.TERRACOTTA_MAGENTA)))
            .withTags(BlockTags.FLOWERS).withTags(tool("_hoe")).cutout();
    public static final BlockHolder POTTED_BLUE_JACARANDA_SAPLING = hiddenBlockWithItem("potted_blue_jacaranda_sapling", () -> VWPlatform.INSTANCE.createFlowerPot(BLUE_JACARANDA_SAPLING, BlockBehaviour.Properties.ofFullCopy(BLUE_JACARANDA_SAPLING.get())))
            .withModel(BlockModel.CROSS_POTTED).cutout().withTags(BlockTags.FLOWER_POTS);

    public static final BlockHolder SHINY_PLUMERIA = blockWithItem("shiny_plumeria", () -> new FlowerBlock(MobEffects.SLOW_FALLING, 7.0F,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OXEYE_DAISY).emissiveRendering((blockState, blockGetter, blockPos) -> true)))
            .withTags(BlockTags.SMALL_FLOWERS).withModel(BlockModel.CROSS).dropsSelf().cutout();
    public static final BlockHolder POTTED_SHINY_PLUMERIA = hiddenBlockWithItem("potted_shiny_plumeria", () -> VWPlatform.INSTANCE.createFlowerPot(SHINY_PLUMERIA, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OXEYE_DAISY)))
            .withModel(BlockModel.CROSS_POTTED).cutout().withTags(BlockTags.FLOWER_POTS);

    public static final BlockHolder CRYSTALIC_SLIME_BLOCK = translucent(blockWithItem("crystalic_slime_block", () -> new CrystalicSlimeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_CYAN)))
            .withModel(BlockModel.CUBE).dropsSelf());

    public static final BlockHolder SODALITE_BRAZIER = blockWithItem("sodalite_brazier", () -> new BrazierBlock(ParticleTypes.FLAME, 1, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().noOcclusion()
            .lightLevel(lightLevelFromBlockState(15, BrazierBlock.LIT))))
            .withTags(BlockTags.CAMPFIRES).withTags(tool("_pickaxe")).dropsSelf().cutout();
    public static final BlockHolder SOUL_SODALITE_BRAZIER = blockWithItem("soul_sodalite_brazier", () -> new BrazierBlock(ParticleTypes.SOUL_FIRE_FLAME, 2, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().noOcclusion()
            .lightLevel(lightLevelFromBlockState(10, BrazierBlock.LIT))))
            .withTags(BlockTags.CAMPFIRES, BlockTags.PIGLIN_REPELLENTS).withTags(tool("_pickaxe")).dropsSelf().cutout();

    public static final BlockHolder SODALITE_BLOCK = blockWithItem("sodalite_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(2F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()))
            .withTags(BlockTags.CRYSTAL_SOUND_BLOCKS).withTags(tool("stone_pickaxe")).withModel(BlockModel.CUBE).dropsSelf();
    public static final BlockHolder BUDDING_SODALITE = blockWithItem("budding_sodalite", () -> new BuddingSodaliteBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).randomTicks().strength(2F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()))
            .withTags(BlockTags.CRYSTAL_SOUND_BLOCKS).withTags(tool("stone_pickaxe")).withModel(BlockModel.CUBE);
    public static final BlockHolder SODALITE_CLUSTER = blockWithItem("sodalite_cluster", () -> new AmethystClusterBlock(8.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(2F)))
            .withModel(BlockModel.CROSS_DIRECTIONAL).withTags(tool("stone_pickaxe")).cutout();
    public static final BlockHolder MEDIUM_SODALITE_BUD = blockWithItem("medium_sodalite_bud", () -> new AmethystClusterBlock(6.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(SODALITE_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD)))
            .withModel(BlockModel.CROSS_DIRECTIONAL).withTags(tool("stone_pickaxe")).cutout();
    public static final BlockHolder SMALL_SODALITE_BUD = blockWithItem("small_sodalite_bud", () -> new AmethystClusterBlock(4.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy(SODALITE_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD)))
            .withModel(BlockModel.CROSS_DIRECTIONAL).withTags(tool("stone_pickaxe")).cutout();
    public static final BlockHolder RECLAIMITE_CRYSTAL = blockWithItem("reclaimite_crystal", () -> new AmethystClusterBlock(6.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN)
            .emissiveRendering((blockState, blockGetter, blockPos) -> blockGetter.getBlockState(blockPos).hasProperty(BlockUtils.RECLAMITE_SHARDED))
            .forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1F).sound(SoundType.MEDIUM_AMETHYST_BUD)))
            .withModel(BlockModel.CROSS_DIRECTIONAL).withTags(tool("stone_pickaxe")).cutout();

    public static final BlockHolder GEMSMITH_TABLE = blockWithItem("gemsmith_table", () -> new GemsmithTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS).noOcclusion()))
            .withTags(tool("stone_pickaxe")).dropsSelf().cutout();
    public static final BlockHolder GEMSMITH_FURNACE = blockWithItem("gemsmith_furnace", () -> new GemsmithFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)))
            .withTags(tool("stone_pickaxe")).dropsSelf();
    public static final BlockHolder ARTIFACT_TABLE = translucent(blockWithItem("artifact_table", () -> new ArtifactTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).noOcclusion().strength(2.5F).sound(SoundType.WOOD).ignitedByLava()))
            .withTags(tool("_axe")).dropsSelf());
    public static final BlockHolder DISASSEMBLY_TABLE = translucent(blockWithItem("disassembly_table", () -> new DisassemblyTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMITHING_TABLE).noOcclusion()))
            .withTags(tool("stone_pickaxe")).dropsSelf());

    public static final List<WoodSigns> SIGNS = List.of(
            new WoodSigns(CRYSTALIC_OAK_PLANKS, CRYSTALIC_OAK_SIGN, CRYSTALIC_OAK_WALL_SIGN, CRYSTALIC_OAK_HANGING_SIGN, CRYSTALIC_OAK_HANGING_WALL_SIGN),
            new WoodSigns(BLUE_JACARANDA_PLANKS, BLUE_JACARANDA_SIGN, BLUE_JACARANDA_WALL_SIGN, BLUE_JACARANDA_HANGING_SIGN, BLUE_JACARANDA_HANGING_WALL_SIGN)
    );

    public static ToIntFunction<BlockState> lightLevelFromBlockState(int litLevel, BooleanProperty property) {
        return state -> state.getValue(property) ? litLevel : 0;
    }

    public static final VWHolders.BlockEntityTypeHolder<ModSignBlockEntity> MOD_SIGN = blockEntity("mod_signs", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
            CRYSTALIC_OAK_SIGN.get(),
            CRYSTALIC_OAK_WALL_SIGN.get(),
            BLUE_JACARANDA_SIGN.get(),
            BLUE_JACARANDA_WALL_SIGN.get()
    ).build(null));
    public static final VWHolders.BlockEntityTypeHolder<ModHangingSignBlockEntity> MOD_HANGING_SIGN = blockEntity("mod_hanging_sign", () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
            CRYSTALIC_OAK_HANGING_SIGN.get(),
            CRYSTALIC_OAK_HANGING_WALL_SIGN.get(),
            BLUE_JACARANDA_HANGING_SIGN.get(),
            BLUE_JACARANDA_HANGING_WALL_SIGN.get()
    ).build(null));
    public static final VWHolders.BlockEntityTypeHolder<GemsmithTableBlockEntity> GEMSMITH_TABLE_BE = blockEntity("gemsmith_table", () -> BlockEntityType.Builder.of(GemsmithTableBlockEntity::new,
            GEMSMITH_TABLE.get()
    ).build(null));
    public static final VWHolders.BlockEntityTypeHolder<GemsmithFurnaceBlockEntity> GEMSMITH_FURNACE_BE = blockEntity("gemsmith_furnace", () -> BlockEntityType.Builder.of(GemsmithFurnaceBlockEntity::new,
            GEMSMITH_FURNACE.get()
    ).build(null));
    public static final VWHolders.BlockEntityTypeHolder<ArtifactTableBlockEntity> ARTIFACT_TABLE_BE = blockEntity("artifact_table", () -> BlockEntityType.Builder.of(ArtifactTableBlockEntity::new,
            ARTIFACT_TABLE.get()
    ).build(null));
    public static final VWHolders.BlockEntityTypeHolder<DisassemblyTableBlockEntity> DISASSEMBLY_TABLE_BE = blockEntity("disassembly_table", () -> BlockEntityType.Builder.of(DisassemblyTableBlockEntity::new,
            DISASSEMBLY_TABLE.get()
    ).build(null));
}