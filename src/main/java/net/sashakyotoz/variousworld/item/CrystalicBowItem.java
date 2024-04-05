
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.stats.Stats;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;

import net.sashakyotoz.variousworld.entity.CrystalicArrowEntity;

import java.util.function.Predicate;

public class CrystalicBowItem extends BowItem {
	public CrystalicBowItem() {
		super(new Item.Properties().durability(482));
	}
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int j) {
		if (entity instanceof Player player) {
			boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
			ItemStack itemstack = player.getProjectile(stack);
			int i = this.getUseDuration(stack) - j;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, level, player, i, !itemstack.isEmpty() || flag);
			if (i < 0)
				return;
			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(Items.ARROW);
				}
				float f = getPowerForTime(i);
				if (!((double) f < 0.1D)) {
					boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, stack, player));
					if (!level.isClientSide) {
						CrystalicArrowEntity bowEntity = CrystalicArrowEntity.shoot(level,stack, player, level.getRandom(), 3.0f * f, 2.5, 1);
						if (f == 1.0F) {
							bowEntity.setCritArrow(true);
						}
						stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(player.getUsedItemHand()));
						if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
							bowEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
						}
						level.addFreshEntity(bowEntity);
					}
					level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if (!flag1 && !player.getAbilities().instabuild) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.getInventory().removeItem(itemstack);
						}
					}
					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	public static float getPowerForTime(int i) {
		float f = (float) i / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}
		return f;
	}

	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		boolean flag = !player.getProjectile(itemstack).isEmpty();
		InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, player, hand, flag);
		if (ret != null)
			return ret;
		if (!player.getAbilities().instabuild && !flag) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(itemstack);
		}
	}

	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return ARROW_ONLY;
	}

	public AbstractArrow customArrow(AbstractArrow arrow) {
		return arrow;
	}

	public int getDefaultProjectileRange() {
		return 20;
	}
}
