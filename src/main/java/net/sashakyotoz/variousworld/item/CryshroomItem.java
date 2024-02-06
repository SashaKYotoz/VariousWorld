
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.procedures.CryshroomRightClickedOnBlockProcedure;
import net.sashakyotoz.variousworld.procedures.CryshroomFoodEatenProcedure;

public class CryshroomItem extends ItemNameBlockItem {
	public CryshroomItem() {
		super(VariousWorldModBlocks.CRYSHROOM_PLANT.get(),new Item.Properties().stacksTo(64).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.5f).build()));
	}
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 48;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		CryshroomFoodEatenProcedure.execute(entity);
		return retval;
	}
}
