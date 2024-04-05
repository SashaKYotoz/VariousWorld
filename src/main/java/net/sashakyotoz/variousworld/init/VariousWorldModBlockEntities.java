package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.sashakyotoz.variousworld.block.entity.MycolocyfarographBlockEntity;
import net.sashakyotoz.variousworld.block.entity.DisenchantTableBlockEntity;
import net.sashakyotoz.variousworld.block.entity.ArtifactTableBlockEntity;
import net.sashakyotoz.variousworld.block.entity.ArmorStationBlockEntity;
import net.sashakyotoz.variousworld.VariousWorldMod;

public class VariousWorldModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VariousWorldMod.MODID);
	public static final RegistryObject<BlockEntityType<ArtifactTableBlockEntity>> ARTIFACT_TABLE = BLOCK_ENTITIES.register("artifacttable", ()->
			BlockEntityType.Builder.of(ArtifactTableBlockEntity::new,VariousWorldModBlocks.ARTIFACTTABLE.get()).build(null));
	public static final RegistryObject<BlockEntityType<ArmorStationBlockEntity>> ARMOR_STATION_BLOCK = BLOCK_ENTITIES.register("armor_station_block", ()->
			BlockEntityType.Builder.of(ArmorStationBlockEntity::new,VariousWorldModBlocks.ARMOR_STATION_BLOCK.get()).build(null));
	public static final RegistryObject<BlockEntityType<DisenchantTableBlockEntity>> DISENCHANT_TABLE = BLOCK_ENTITIES.register("disenchant_table",()->
			BlockEntityType.Builder.of(DisenchantTableBlockEntity::new,VariousWorldModBlocks.DISENCHANT_TABLE.get()).build(null));
	public static final RegistryObject<BlockEntityType<MycolocyfarographBlockEntity>> MYCOLOCYFAROGRAPH = BLOCK_ENTITIES.register("mycolocyfarograph", ()->
			BlockEntityType.Builder.of(MycolocyfarographBlockEntity::new,VariousWorldModBlocks.MYCOLOCYFAROGRAPH.get()).build(null));
}
