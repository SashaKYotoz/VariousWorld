
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvents;

public class SuperVisionCharmItem extends Item {

	public SuperVisionCharmItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.SPYGLASS;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 5;
	}

	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		this.stopUsing(livingEntity);
		return stack;
	}

	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int p_151216_) {
		this.stopUsing(entity);
	}

	private void stopUsing(LivingEntity livingEntity) {
		livingEntity.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);
	}
}
