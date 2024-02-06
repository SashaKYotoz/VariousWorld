package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.VariousWorldMod;

public class MushroomSpawnerUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double Random;
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == VariousWorldModBlocks.MYCENA_FROM_CAVERN_OF_DEEP.get()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/effect give @e[type= various_world:crystal_warrior,distance=..15] minecraft:regeneration 5 2");
		} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == VariousWorldModBlocks.CRYSHROOM_PLANT.get()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/effect give @e[type= various_world:crystal_warrior,distance=..15] minecraft:invisibility 10 0");
		}
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
			Random = Math.round(Math.random());
			if (Random == 0) {
				VariousWorldMod.queueServerWork(60, () -> {
					world.setBlock(BlockPos.containing(x, y + 1, z), VariousWorldModBlocks.MYCENA_FROM_CAVERN_OF_DEEP.get().defaultBlockState(), 3);
				});
			} else if (Random == 1) {
				VariousWorldMod.queueServerWork(60, () -> {
					world.setBlock(BlockPos.containing(x, y + 1, z), VariousWorldModBlocks.CRYSHROOM_PLANT.get().defaultBlockState(), 3);
				});
			}
		}
	}
}
