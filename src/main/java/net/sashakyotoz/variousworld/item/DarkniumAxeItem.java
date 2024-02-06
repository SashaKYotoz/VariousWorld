
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class DarkniumAxeItem extends AxeItem {
	public DarkniumAxeItem() {
		super(new Tier() {
			public int getUses() {
				return 1248;
			}

			public float getSpeed() {
				return 10f;
			}

			public float getAttackDamageBonus() {
				return 8f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 40;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(VariousWorldModItems.DARKNIUM_INGOT.get()));
			}
		}, 1, -3f, new Item.Properties());
	}
}
