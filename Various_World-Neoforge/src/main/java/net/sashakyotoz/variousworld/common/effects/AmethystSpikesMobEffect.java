
package net.sashakyotoz.variousworld.common.effects;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class AmethystSpikesMobEffect extends MobEffect {
	public AmethystSpikesMobEffect() {
		super(MobEffectCategory.BENEFICIAL, 2145255607);
	}

	@Override
	public String getDescriptionId() {
		return "effect.various_world.amethyst_spikes";
	}

	@Override
	public boolean isInstantenous() {
		return true;
	}
}