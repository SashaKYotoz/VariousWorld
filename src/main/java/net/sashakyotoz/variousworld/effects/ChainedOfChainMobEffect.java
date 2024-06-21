
package net.sashakyotoz.variousworld.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ChainedOfChainMobEffect extends MobEffect {
	public ChainedOfChainMobEffect() {
		super(MobEffectCategory.HARMFUL, -13421773);
		addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -1F, AttributeModifier.Operation.ADDITION);
	}
	@Override
	public double getAttributeModifierValue(int i, AttributeModifier modifier) {
		if (i > 1)
			return modifier.getAmount() * (i + 0.25f);
		else
			return modifier.getAmount() * (i + 1f);
	}

	@Override
	public String getDescriptionId() {
		return "effect.various_world.chained_of_chain";
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
