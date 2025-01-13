
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
import net.sashakyotoz.variousworld.entity.technical.SculkScytheEntity;
import net.sashakyotoz.variousworld.init.VWItems;

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
				return Ingredient.of(new ItemStack(VWItems.SCULK_GEM.get()));
			}
		}, 3, -2.8f, new Item.Properties());
	}
	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int j) {
		if (entity instanceof Player player) {
			int i = this.getUseDuration(stack) - j;
			if (i < 0)
				return;
			var f = getPowerForTime(i);
				if (!((double) f < 0.325D)) {
					SculkScytheEntity sculkScytheRangedItem = SculkScytheEntity.shoot(level,entity, RandomSource.create(),2 * f,5,1);
					level.addFreshEntity(sculkScytheRangedItem);
					stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(player.getUsedItemHand()));
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
	private static float getPowerForTime(int i) {
		float f = (float) i / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}
		return f;
	}
}
