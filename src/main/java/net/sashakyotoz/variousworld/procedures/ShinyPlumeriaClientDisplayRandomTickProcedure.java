package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class ShinyPlumeriaClientDisplayRandomTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.125) {
			if (world instanceof ServerLevel server)
				server.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, server, 4, "", Component.literal(""), server.getServer(), null).withSuppressedOutput(),
						"/effect give @e[distance=..9] minecraft:glowing 20");
		}
	}
}
