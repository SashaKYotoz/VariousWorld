
package net.sashakyotoz.variousworld.world.features.ores;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

import java.util.Set;

public class SculkGemOreFeature extends OreFeature {
	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public SculkGemOreFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		if (!generate_dimensions.contains(level.getLevel().dimension()))
			return false;
		int sx,sy,sz;
		if (level.getBlockState(pos.below()).canOcclude()) {
			sx = -3;
			for (int i = 0; i < (int) Mth.nextDouble(RandomSource.create(), 5, 7); i++) {
				sy = -3;
				for (int j = 0; j < 7; j++) {
					sz = -3;
					for (int k = 0; k < (int) Mth.nextDouble(RandomSource.create(), 5, 7); k++) {
						if (level.getBlockState(pos.offset(sx,sy,sz)).is(BlockTags.BASE_STONE_OVERWORLD)) {
							if (RandomSource.create().nextBoolean())
								level.setBlock(pos.above(), VariousWorldModBlocks.SCULK_MOSS_BLOCK.get().defaultBlockState(), 3);
							else
								level.setBlock(pos.below(), Blocks.SCULK.defaultBlockState(), 3);
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
		return super.place(context);
	}
}