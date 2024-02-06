package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.sashakyotoz.variousworld.network.VariousWorldModVariables;
import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;

public class DragonEyeEffectStartedAppliedProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.playerx = entity.getX();
            capability.syncPlayerVariables(entity);
        });
        entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.playery = entity.getY();
            capability.syncPlayerVariables(entity);
        });
        entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.playerz = entity.getZ();
            capability.syncPlayerVariables(entity);
        });
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(VariousWorldModMobEffects.DRAGON_EYE.get())) {
            if (entity instanceof ServerPlayer player)
                player.setGameMode(GameType.SPECTATOR);
        }
    }
}
