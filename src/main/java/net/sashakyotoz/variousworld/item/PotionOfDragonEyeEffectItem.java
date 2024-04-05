
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;

public class PotionOfDragonEyeEffectItem extends Item {
	public PotionOfDragonEyeEffectItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.5f).build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 48;
	}

	@Override
	public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
		return 0F;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack stack = super.finishUsingItem(itemstack, world, entity);
		if (!entity.level().isClientSide())
			entity.addEffect(new MobEffectInstance(VariousWorldModMobEffects.DRAGON_EYE.get(), 100, 0));
		return stack;
	}
}
