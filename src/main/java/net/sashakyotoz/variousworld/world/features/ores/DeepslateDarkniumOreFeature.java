
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

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

import java.util.Set;

public class DeepslateDarkniumOreFeature extends OreFeature {
	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public DeepslateDarkniumOreFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		double sx, sy, sz;
		if (!generate_dimensions.contains(world.getLevel().dimension()))
			return false;
		sx = -5;
		for (int index0 = 0; index0 < 8; index0++) {
			sy = -5;
			for (int index1 = 0; index1 < 8; index1++) {
				sz = -5;
				for (int index2 = 0; index2 < 8; index2++) {
					if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.DEEPSLATE_SCULK_GEM_ORE.get()) {
						BlockPos blockPos = BlockPos.containing(x + sx, y + sy, z + sz);
						if (Math.random() < 0.5) {
							BlockState state = Blocks.BLACKSTONE.defaultBlockState();
							world.setBlock(blockPos, state, 3);
						} else if (Math.random() < 0.125) {
							BlockState state = Blocks.AIR.defaultBlockState();
							world.setBlock(blockPos, state, 3);
						} else if (Math.random() < 0.25) {
							BlockState state = VariousWorldModBlocks.SCULK_MAGMA.get().defaultBlockState();
							world.setBlock(blockPos, state, 3);
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
