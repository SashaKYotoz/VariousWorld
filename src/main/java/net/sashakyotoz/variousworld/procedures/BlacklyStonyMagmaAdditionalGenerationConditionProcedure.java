package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class BlacklyStonyMagmaAdditionalGenerationConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
			sx = -4;
			for (int index0 = 0; index0 < 8; index0++) {
				sy = -3;
				for (int index1 = 0; index1 < 6; index1++) {
					sz = -4;
					for (int index2 = 0; index2 < 8; index2++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.BLACKLY_STONY_MAGMA.get() && world.getBlockState(BlockPos.containing(x + sx, (y + sy) - 1, z + sz)).canOcclude()
								&& world.getBlockState(BlockPos.containing((x + sx) - 1, y + sy, z + sz)).canOcclude() && world.getBlockState(BlockPos.containing(x + sx + 1, y + sy, z + sz)).canOcclude()
								&& world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x + sx, y + sy, (z + sz) - 1)).canOcclude()) {
							if (Math.random() < 0.5) {
								world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), Blocks.MAGMA_BLOCK.defaultBlockState(), 3);
							} else if (Math.random() < 0.125) {
								world.setBlock(BlockPos.containing(x + sx, y + sy + 1, z + sz), VariousWorldModBlocks.ANTHURIUM_SPROUTED_OF_MAGMA.get().defaultBlockState(), 3);
							} else if (Math.random() < 0.15) {
								world.setBlock(BlockPos.containing(x + sx, y + sy + 1, z + sz), Blocks.MAGMA_BLOCK.defaultBlockState(), 3);
								world.setBlock(BlockPos.containing(x + sx, y + sy + 2, z + sz), Blocks.GLOWSTONE.defaultBlockState(), 3);
							} else if (Math.random() < 0.35) {
								world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), Blocks.LAVA.defaultBlockState(), 3);
							}
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
		return true;
	}
}
