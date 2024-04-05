
package net.sashakyotoz.variousworld.world.features.ores;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

import java.util.Set;

public class ShinyValleyDecorGeneratorFeature extends Feature<NoneFeatureConfiguration> {
	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public ShinyValleyDecorGeneratorFeature() {
		super(NoneFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		double sx, sy, sz;
		if (!(generate_dimensions.contains(world.getLevel().dimension()) && world.getBlockState(BlockPos.containing(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()))
			return false;
		else{
			sx = -4;
			for (int i = 0; i < 8; i++) {
				sy = -2;
				for (int j = 0; j < 4; j++) {
					sz = -4;
					for (int l = 0; l < 8; l++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy + 1, z + sz))).getBlock() == Blocks.AIR && (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.SHINY_GRASS.get()
								&& ((world.getBlockState(BlockPos.containing(x + sx + 1, y + sy, z + sz))).getBlock() == Blocks.WATER || world.getBlockState(BlockPos.containing(x + sx + 1, y + sy, z + sz)).canOcclude())
								&& ((world.getBlockState(BlockPos.containing((x + sx) - 1, y + sy, z + sz))).getBlock() == Blocks.WATER || world.getBlockState(BlockPos.containing((x + sx) - 1, y + sy, z + sz)).canOcclude())
								&& ((world.getBlockState(BlockPos.containing(x + sx, y + sy, (z + sz) - 1))).getBlock() == Blocks.WATER || world.getBlockState(BlockPos.containing(x + sx, y + sy, (z + sz) - 1)).canOcclude())
								&& world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz + 1)).canOcclude()) {
								BlockPos pos = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState waterState = Blocks.WATER.defaultBlockState();
								world.setBlock(pos, waterState, 3);
							if (Math.random() < 0.25) {
								BlockPos pos1 = BlockPos.containing(x + sx, (y + sy) - 1, z + sz);
								world.setBlock(pos1, waterState, 3);
							}
						}
						sz = sz + Mth.nextDouble(RandomSource.create(), 1, 2);
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
		return true;
	}
}
