
package net.sashakyotoz.variousworld.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.procedures.ThunderboltHammerBlockDestroyedWithToolProcedure;

public class ThunderboltHammerItem extends PickaxeItem {
	public ThunderboltHammerItem() {
		super(new Tier() {
			public int getUses() {
				return 2000;
			}

			public float getSpeed() {
				return 8f;
			}

			public float getAttackDamageBonus() {
				return 10f;
			}

			public int getLevel() {
				return 4;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(VariousWorldModItems.DARKSHARD.get()), new ItemStack(Items.AMETHYST_SHARD), new ItemStack(VariousWorldModItems.CRYSTALSHARD.get()));
			}
		}, 1, -2.8f, new Item.Properties().fireResistant());
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		ThunderboltHammerBlockDestroyedWithToolProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
		return super.mineBlock(itemstack, world, blockstate, pos, entity);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		InteractionResultHolder<ItemStack> result = super.use(level, player, hand);
		ItemStack stack = result.getObject();
		if (level instanceof ServerLevel serverLevel) {
			LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(serverLevel);
			entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(player.getX() + Mth.nextDouble(RandomSource.create(), -4, 4), player.getY(), player.getZ() + Mth.nextDouble(RandomSource.create(), -4, 4))));
			serverLevel.addFreshEntity(entityToSpawn);
		}
		player.getCooldowns().addCooldown(stack.getItem(), 30);
		player.swing(InteractionHand.MAIN_HAND, true);
		player.swing(InteractionHand.OFF_HAND, true);
		if (player.getRandom().nextBoolean()) {
			if (stack.hurt(1, RandomSource.create(), null)) {
				stack.shrink(1);
				stack.setDamageValue(0);
			}
		}
		return result;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}
}
