package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class CryshroomRightClickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STONE || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldModBlocks.DEEP_MOSS.get()
				|| (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldModBlocks.CRYSTALIC_GRASS.get()) && world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) <= 6) {
			world.setBlock(BlockPos.containing(x, y + 1, z), VariousWorldModBlocks.CRYSHROOM_PLANT.get().defaultBlockState(), 3);
		}
	}
}
