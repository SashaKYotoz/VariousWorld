package net.sashakyotoz.variousworld.procedures;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ThunderboltHammerBlockDestroyedWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Direction direction = entity.getDirection();
		double[][] offsets;
		if (entity.getXRot() > 40 || entity.getXRot() < -40) {
			offsets = new double[][] {
					{1, 0, 0}, {-1, 0, 0}, {1, 0, 1}, {1, 0, -1},
					{-1, 0, -1}, {-1, 0, 1}, {0, 0, 1}, {0, 0, -1}
			};
		} else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
			offsets = new double[][] {
					{1, 0, 0}, {-1, 0, 0}, {1, 1, 0}, {1, -1, 0},
					{-1, -1, 0}, {-1, 1, 0}, {0, 1, 0}, {0, -1, 0}
			};
		} else if (direction == Direction.WEST || direction == Direction.EAST) {
			offsets = new double[][] {
					{0, 0, 1}, {0, 0, -1}, {0, 1, 1}, {0, -1, 1},
					{0, -1, -1}, {0, 1, -1}, {0, 1, 0}, {0, -1, 0}
			};
		} else
			return;
		for (double[] offset : offsets) {
			BlockPos pos = BlockPos.containing(x + offset[0], y + offset[1], z + offset[2]);
			destroyBlockIfApplicable(world, pos, entity.blockPosition().above());
		}
	}

	private static void destroyBlockIfApplicable(LevelAccessor accessor, BlockPos pos, BlockPos dropPos) {
		if (accessor.getBlockState(pos).is(BlockTags.MINEABLE_WITH_PICKAXE) && !(accessor.getBlockState(pos).getBlock().defaultDestroyTime() <= -1)) {
			Block.dropResources(accessor.getBlockState(pos), accessor, dropPos, null);
			accessor.destroyBlock(pos, false);
		}
	}
}