
package net.sashakyotoz.variousworld.item;

import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.entity.SculkScytheEntity;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class SculkScytheItem extends SwordItem {
	public SculkScytheItem() {
		super(new Tier() {
			public int getUses() {
				return 1024;
			}

			public float getSpeed() {
				return 5f;
			}

			public float getAttackDamageBonus() {
				return 3f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 10;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(VariousWorldModItems.SCULK_GEM.get()));
			}
		}, 3, -2.8f, new Item.Properties());
	}
	@Override
	public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
		if (p_40669_ instanceof Player player) {
			int i = this.getUseDuration(p_40667_) - p_40670_;
			if (i < 0)
				return;
			var f = getPowerForTime(i);
				if (!((double) f < 0.325D)) {
					SculkScytheEntity sculkScytheRangedItem = SculkScytheEntity.shoot(p_40668_,p_40669_, RandomSource.create(),2 * f,5,1);
					p_40668_.addFreshEntity(sculkScytheRangedItem);
					p_40667_.hurtAndBreak(1, player, (p_40665_) -> {
						p_40665_.broadcastBreakEvent(player.getUsedItemHand());
					});
					player.awardStat(Stats.ITEM_USED.get(this));
				}
		}
	}
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return new InteractionResultHolder(InteractionResult.SUCCESS, entity.getItemInHand(hand));
	}
	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 72000;
	}
	private static float getPowerForTime(int p_40662_) {
		float f = (float) p_40662_ / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}
		return f;
	}
}