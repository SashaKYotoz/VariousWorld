
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class BranchWithDragonEyeFruitItem extends ItemNameBlockItem {
	public BranchWithDragonEyeFruitItem() {
		super(VariousWorldModBlocks.FLOWER_VINE_FROM_CAVERNOF_DEEP.get(),new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).food((new FoodProperties.Builder()).nutrition(3).saturationMod(3f).build()));
	}
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 36;
	}
}
