
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class CrystalicSlimeballSwordItem extends SwordItem {
	public CrystalicSlimeballSwordItem() {
		super(new Tier() {
			public int getUses() {
				return 984;
			}

			public float getSpeed() {
				return 5f;
			}

			public float getAttackDamageBonus() {
				return 4f;
			}

			public int getLevel() {
				return 5;
			}

			public int getEnchantmentValue() {
				return 15;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(VariousWorldModItems.SLIME_CRYSTALIC.get()));
			}
		}, 3, -2.4f, new Item.Properties());
	}
}
