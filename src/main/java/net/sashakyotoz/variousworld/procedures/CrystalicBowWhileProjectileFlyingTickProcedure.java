package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class CrystalicBowWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.addParticle(ParticleTypes.GLOW, x, y, z, 0, 1, 0);
		if (world instanceof ServerLevel level)
			level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 15, 3, 3, 3, 1);
	}
}
