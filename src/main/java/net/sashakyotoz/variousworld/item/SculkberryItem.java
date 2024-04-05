
package net.sashakyotoz.variousworld.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class SculkberryItem extends Item {
	public SculkberryItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(3).saturationMod(2.5f).build()));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 40;
	}

	@Override
	public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
		return 0F;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack stack = new ItemStack(VariousWorldModItems.SCULK_SHARD.get());
		super.finishUsingItem(itemstack, world, entity);
		if (itemstack.isEmpty()) {
			return stack;
		} else {
			if (entity instanceof Player player && !player.getAbilities().instabuild) {
				if (!player.getInventory().add(stack))
					player.drop(stack, false);
			}
			return itemstack;
		}
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ());
		return InteractionResult.SUCCESS;
	}
	private void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldModBlocks.SCULK_GRASS.get()) {
			if (Math.random() < 0.5) {
				world.setBlock(BlockPos.containing(x, y + 1, z), VariousWorldModBlocks.SMALL_SCULK_BUSH.get().defaultBlockState(), 3);
			}
		}
	}
}
