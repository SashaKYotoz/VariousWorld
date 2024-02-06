package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class SmallSculkBushPlantRightClickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.BONE_MEAL) {
			world.addParticle(ParticleTypes.GLOW_SQUID_INK, x, y, z, 0, 1, 0);
			if (Math.random() < 0.25) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = VariousWorldModBlocks.SCULK_BUSH_WITHOUT_BERRY.get().defaultBlockState();
					world.setBlock(_bp, _bs, 3);
				}
				if (entity instanceof LivingEntity living) {
					ItemStack stack = living.getMainHandItem();
					stack.setCount(living.getMainHandItem().getCount() - 1);
					living.setItemInHand(InteractionHand.MAIN_HAND, stack);
					if (living instanceof Player player)
						player.getInventory().setChanged();
				}
			}
		}
	}
}
