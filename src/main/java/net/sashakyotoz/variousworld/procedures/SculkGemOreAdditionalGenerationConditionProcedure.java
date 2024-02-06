package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class SculkGemOreAdditionalGenerationConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double sx;
		double sy;
		double sz;
		double randomizer;
		if (world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
			sx = -3;
			for (int index0 = 0; index0 < (int) Mth.nextDouble(RandomSource.create(), 5, 7); index0++) {
				sy = -3;
				for (int index1 = 0; index1 < 7; index1++) {
					sz = -3;
					for (int index2 = 0; index2 < (int) Mth.nextDouble(RandomSource.create(), 5, 7); index2++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.STONE || (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.DEEPSLATE) {
							randomizer = Mth.nextDouble(RandomSource.create(), 0, 1);
							if (randomizer == 0) {
								world.setBlock(BlockPos.containing(x, y, z), VariousWorldModBlocks.SCULK_MOSS_BLOCK.get().defaultBlockState(), 3);
							} else {
								world.setBlock(BlockPos.containing(x, y, z), Blocks.SCULK.defaultBlockState(), 3);
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
