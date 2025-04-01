
package net.sashakyotoz.variousworld.common.effects;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ChainedOfChainMobEffect extends MobEffect {
	public ChainedOfChainMobEffect() {
		super(MobEffectCategory.HARMFUL, -13421773);
		addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.withDefaultNamespace("chained_of_chain_effects"), -1F, AttributeModifier.Operation.ADD_VALUE);
	}

	@Override
	public String getDescriptionId() {
		return "effect.various_world.chained_of_chain";
	}
}