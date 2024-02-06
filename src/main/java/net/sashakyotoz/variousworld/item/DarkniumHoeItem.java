
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class DarkniumHoeItem extends HoeItem {
	public DarkniumHoeItem() {
		super(new Tier() {
			public int getUses() {
				return 1148;
			}

			public float getSpeed() {
				return 10f;
			}

			public float getAttackDamageBonus() {
				return 0f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 39;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(VariousWorldModItems.DARKNIUM_INGOT.get()));
			}
		}, 0, 0, new Item.Properties());
	}
}
