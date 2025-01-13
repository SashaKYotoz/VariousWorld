
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
		for (int i = 0; i < 8; i++) {
			sy = -5;
			for (int j = 0; j < 8; j++) {
				sz = -5;
				for (int k = 0; k < 8; k++) {
					if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VWBlocks.DEEPSLATE_SCULK_GEM_ORE.get()) {
						BlockPos blockPos = BlockPos.containing(x + sx, y + sy, z + sz);
						if (Math.random() < 0.5) {
							BlockState state = Blocks.BLACKSTONE.defaultBlockState();
							world.setBlock(blockPos, state, 3);
						} else if (Math.random() < 0.125) {
							BlockState state = Blocks.AIR.defaultBlockState();
							world.setBlock(blockPos, state, 3);
						} else if (Math.random() < 0.25) {
							BlockState state = VWBlocks.SCULK_MAGMA.get().defaultBlockState();
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
