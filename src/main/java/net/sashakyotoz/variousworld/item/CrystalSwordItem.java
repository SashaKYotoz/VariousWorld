
package net.sashakyotoz.variousworld.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import net.sashakyotoz.variousworld.init.VariousWorldItems;

public class CrystalSwordItem extends SwordItem{
	public CrystalSwordItem() {
		super(new Tier() {
			public int getUses() {
				return 2031;
			}

			public float getSpeed() {
				return 15f;
			}

			public float getAttackDamageBonus() {
				return 6f;
			}

			public int getLevel() {
				return 5;
			}

			public int getEnchantmentValue() {
				return 15;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(VariousWorldItems.DARKSHARD.get()));
			}
		}, 3, -2.4f, new Item.Properties().fireResistant());
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity livingEntity) {
		if (livingEntity.getMainHandItem().getOrCreateTag().getDouble("CustomModelData") == 1) {
			if (livingEntity.level() instanceof ServerLevel level)
				level.sendParticles(ParticleTypes.CRIT, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 15, 3, 3, 3, 1);
			entity.setSecondsOnFire(3);
		}
		return super.hurtEnemy(itemstack, entity, livingEntity);
	}
}
