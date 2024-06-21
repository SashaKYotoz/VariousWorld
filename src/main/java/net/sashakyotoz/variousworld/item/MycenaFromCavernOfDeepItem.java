
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.InteractionResult;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class MycenaFromCavernOfDeepItem extends ItemNameBlockItem {
	public MycenaFromCavernOfDeepItem() {
		super(VariousWorldModBlocks.MYCENA_FROM_CAVERN_OF_DEEP.get(),new Item.Properties().stacksTo(64).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.5f).fast().build()));
	}
}
