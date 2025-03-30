
package net.sashakyotoz.variousworld.world.features.ores;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;

import net.sashakyotoz.variousworld.init.VWBlocks;

import java.util.Set;

public class DeepslateSculkGemOreFeature extends OreFeature {
	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public DeepslateSculkGemOreFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		BlockPos pos = context.origin();
		if (!generate_dimensions.contains(world.getLevel().dimension()))
			return false;
		int sx,sy,sz;
		sx = -5;
		for (int i = 0; i < 8; i++) {
			sy = -5;
			for (int j = 0; j < 8; j++) {
				sz = -5;
				for (int k = 0; k < 8; k++) {
					if (world.getBlockState(pos.offset(sx,sy,sz)).is(VWBlocks.DEEPSLATE_SCULK_GEM_ORE.get())) {
						BlockPos pos1 = pos.offset(sx,sy,sz);
						if (Math.random() < 0.5) {
							BlockState _bs = VWBlocks.SCULK_MOSS_BLOCK.get().defaultBlockState();
							world.setBlock(pos1, _bs, 3);
						} else if (Math.random() < 0.25) {
							BlockState _bs = Blocks.AIR.defaultBlockState();
							world.setBlock(pos1, _bs, 3);
						} else if (Math.random() < 0.125) {
							BlockState _bs = VWBlocks.SCULK_MAGMA.get().defaultBlockState();
							world.setBlock(pos1, _bs, 3);
						}
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
		return super.place(context);
	}
}
