package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

public class MultipleEnderPearlProjectileHitsBlockProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			entity.teleportTo(x, (y + 0.5), z);
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(x, (y + 0.5), z, entity.getYRot(), entity.getXRot());
		}
	}
}
