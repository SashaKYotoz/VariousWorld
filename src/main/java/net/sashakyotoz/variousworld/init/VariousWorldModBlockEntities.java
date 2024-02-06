
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.sashakyotoz.variousworld.block.entity.MycolocyfarographBlockEntity;
import net.sashakyotoz.variousworld.block.entity.DisenchantTableBlockEntity;
import net.sashakyotoz.variousworld.block.entity.ArtifacttableBlockEntity;
import net.sashakyotoz.variousworld.block.entity.ArmorStationBlockBlockEntity;
import net.sashakyotoz.variousworld.VariousWorldMod;

public class VariousWorldModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VariousWorldMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> ARTIFACTTABLE = register("artifacttable", VariousWorldModBlocks.ARTIFACTTABLE, ArtifacttableBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ARMOR_STATION_BLOCK = register("armor_station_block", VariousWorldModBlocks.ARMOR_STATION_BLOCK, ArmorStationBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> DISENCHANT_TABLE = register("disenchant_table", VariousWorldModBlocks.DISENCHANT_TABLE, DisenchantTableBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> MYCOLOCYFAROGRAPH = register("mycolocyfarograph", VariousWorldModBlocks.MYCOLOCYFAROGRAPH, MycolocyfarographBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
