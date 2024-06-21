
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

import net.sashakyotoz.variousworld.init.VariousWorldBlocks;

import java.util.Set;

public class ShinyValleyDecorGeneratorFeature extends Feature<NoneFeatureConfiguration> {
	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public ShinyValleyDecorGeneratorFeature() {
		super(NoneFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		int sx, sy, sz;
		if (!(generate_dimensions.contains(level.getLevel().dimension()) && level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.below()).canOcclude()))
			return false;
		else{
			sx = -4;
			for (int i = 0; i < 8; i++) {
				sy = -2;
				for (int j = 0; j < 4; j++) {
					sz = -4;
					for (int l = 0; l < 8; l++) {
						BlockPos pos1 = pos.offset(sx,sy,sz);
						if (level.getBlockState(pos1).isAir() && level.getBlockState(pos1).is(VariousWorldBlocks.SHINY_GRASS.get())
								&& (level.getBlockState(pos.offset(sx+1,sy,sz)).is(Blocks.WATER) || level.getBlockState(pos.offset(sx+1,sy,sz)).canOcclude())
								&& (level.getBlockState(pos.offset(sx-1,sy,sz)).is(Blocks.WATER) || level.getBlockState(pos.offset(sx-1,sy,sz)).canOcclude())
								&& (level.getBlockState(pos.offset(sx,sy,sz-1)).is(Blocks.WATER) || level.getBlockState(pos.offset(sx,sy,sz-1)).canOcclude())
								&& level.getBlockState(pos.offset(sx,sy,sz+1)).canOcclude()) {
								BlockState waterState = Blocks.WATER.defaultBlockState();
								level.setBlock(pos1, waterState, 3);
							if (Math.random() < 0.25) {
								BlockPos pos2 = pos1.below();
								level.setBlock(pos2, waterState, 3);
							}
						}
						sz = sz + Mth.nextInt(RandomSource.create(), 1, 2);
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
		return true;
	}
}
