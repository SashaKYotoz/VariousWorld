
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class DarkniumPickaxeItem extends PickaxeItem {
	public DarkniumPickaxeItem() {
		super(new Tier() {
			public int getUses() {
				return 1248;
			}

			public float getSpeed() {
				return 10f;
			}

			public float getAttackDamageBonus() {
				return 4f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 36;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(VariousWorldModItems.DARKNIUM_INGOT.get()));
			}
		}, 1, -2.8f, new Item.Properties());
	}
}
