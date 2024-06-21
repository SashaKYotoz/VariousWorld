
package net.sashakyotoz.variousworld.effects;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.minecraft.world.level.GameType;
import net.sashakyotoz.variousworld.init.VariousWorldMobEffects;
import net.sashakyotoz.variousworld.network.VariousWorldModVariables;

public class DragonEyeMobEffect extends MobEffect {
	public DragonEyeMobEffect() {
		super(MobEffectCategory.NEUTRAL, -16759681);
	}

	@Override
	public String getDescriptionId() {
		return "effect.various_world.dragon_eye";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
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
		if (entity.hasEffect(VariousWorldMobEffects.DRAGON_EYE.get())) {
			if (entity instanceof ServerPlayer player)
				player.setGameMode(GameType.SPECTATOR);
		}
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		onEffectExpired(entity);
	}
	private void onEffectExpired(Entity entity) {
		if (entity == null)
			return;
		entity.teleportTo(((entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VariousWorldModVariables.PlayerVariables())).playerx),
				((entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VariousWorldModVariables.PlayerVariables())).playery),
				((entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VariousWorldModVariables.PlayerVariables())).playerz));
		if (entity instanceof ServerPlayer player){
			player.setGameMode(GameType.SURVIVAL);
			player.connection.teleport(((entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VariousWorldModVariables.PlayerVariables())).playerx),
					((entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VariousWorldModVariables.PlayerVariables())).playery),
					((entity.getCapability(VariousWorldModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new VariousWorldModVariables.PlayerVariables())).playerz), entity.getYRot(), entity.getXRot());
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
