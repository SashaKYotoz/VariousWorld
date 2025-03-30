package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.sashakyotoz.variousworld.block.entity.*;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VariousWorld.MODID);
    public static final RegistryObject<BlockEntityType<ArtifactTableBlockEntity>> ARTIFACT_TABLE = BLOCK_ENTITIES.register("artifacttable", () ->
            BlockEntityType.Builder.of(ArtifactTableBlockEntity::new, VWBlocks.ARTIFACTTABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ArmorStationBlockEntity>> ARMOR_STATION_BLOCK = BLOCK_ENTITIES.register("armor_station_block", () ->
            BlockEntityType.Builder.of(ArmorStationBlockEntity::new, VWBlocks.ARMOR_STATION_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<DisenchantTableBlockEntity>> DISENCHANT_TABLE = BLOCK_ENTITIES.register("disenchant_table", () ->
            BlockEntityType.Builder.of(DisenchantTableBlockEntity::new, VWBlocks.DISENCHANT_TABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MycolocyfarographBlockEntity>> MYCOLOCYFAROGRAPH = BLOCK_ENTITIES.register("mycolocyfarograph", () ->
            BlockEntityType.Builder.of(MycolocyfarographBlockEntity::new, VWBlocks.MYCOLOCYFAROGRAPH.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("mod_signs", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
            VWBlocks.SAKURA_SIGN.get(), VWBlocks.SAKURA_WALL_SIGN.get(),
            VWBlocks.SCULK_SIGN.get(), VWBlocks.SCULK_WALL_SIGN.get(),
            VWBlocks.CRYSTALIC_OAK_SIGN.get(), VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITIES.register("mod_hanging_sign", () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
            VWBlocks.SCULK_HANGING_SIGN.get(),
            VWBlocks.SCULK_HANGING_WALL_SIGN.get(),
            VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(),
            VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get(),
            VWBlocks.SAKURA_HANGING_SIGN.get(),
            VWBlocks.SAKURA_HANGING_WALL_SIGN.get()
    ).build(null));
}