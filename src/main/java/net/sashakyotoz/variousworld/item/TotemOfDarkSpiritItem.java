
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

import net.sashakyotoz.variousworld.procedures.DarkGemAbilityProcedure;

public class TotemOfDarkSpiritItem extends Item {
	public TotemOfDarkSpiritItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		DarkGemAbilityProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
	}
}
