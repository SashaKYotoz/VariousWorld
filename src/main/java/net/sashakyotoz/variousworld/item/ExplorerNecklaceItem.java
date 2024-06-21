
package net.sashakyotoz.variousworld.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

public class ExplorerNecklaceItem extends Item {
	public ExplorerNecklaceItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 1;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level level, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, level, entity, slot, selected);
		if (level.getBlockState(entity.blockPosition().below(3)).is(BlockTags.LEAVES)) {
			if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide())
				livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 1));
		}
		if (level.getBlockState(entity.blockPosition().below()).is(BlockTags.DIRT)) {
			if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide())
				livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 1));
		}
		if (!level.getBlockState(entity.blockPosition().below()).canOcclude()) {
			if (entity instanceof LivingEntity living && !living.level().isClientSide())
				living.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 2));
		}
	}
}
