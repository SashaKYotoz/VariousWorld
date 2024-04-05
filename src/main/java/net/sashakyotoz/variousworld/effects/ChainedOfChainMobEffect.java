
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
	public double getAttributeModifierValue(int p_19457_, AttributeModifier p_19458_) {
		if (p_19457_ > 1)
			return p_19458_.getAmount() * (p_19457_ + 0.25f);
		else
			return p_19458_.getAmount() * (p_19457_ + 1f);
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
