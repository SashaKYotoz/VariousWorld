
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class SculkMagmaBlock extends Block {
	public SculkMagmaBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SHROOMLIGHT).strength(0.5f, 10f).lightLevel(s -> 5).requiresCorrectToolForDrops().speedFactor(0.8f).jumpFactor(0.8f).hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true).forceSolidOn());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	public void stepOn(Level p_153777_, BlockPos p_153778_, BlockState p_153779_, Entity p_153780_) {
		if (!p_153780_.isSteppingCarefully() && p_153780_ instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) p_153780_) && !((p_153780_ instanceof LivingEntity entity ? entity.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == VariousWorldModItems.SCULK_ARMOR_BOOTS.get())) {
			p_153780_.hurt(p_153777_.damageSources().hotFloor(), 1.0F);
		}
		super.stepOn(p_153777_, p_153778_, p_153779_, p_153780_);
	}

	public void tick(BlockState p_221415_, ServerLevel p_221416_, BlockPos p_221417_, RandomSource p_221418_) {
		BubbleColumnBlock.updateColumn(p_221416_, p_221417_.above(), p_221415_);
	}

	public void randomTick(BlockState p_221420_, ServerLevel p_221421_, BlockPos p_221422_, RandomSource p_221423_) {
		BlockPos blockpos = p_221422_.above();
		if (p_221421_.getFluidState(p_221422_).canExtinguish(p_221421_, p_221422_)) {
			p_221421_.playSound(null, p_221422_, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (p_221421_.random.nextFloat() - p_221421_.random.nextFloat()) * 0.8F);
			p_221421_.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, (double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.25D, (double) blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
		}
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
			return tieredItem.getTier().getLevel() >= 1;
		return false;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldModBlocks.SCULK_MAGMA.get());
		return Collections.singletonList(itemStack);
	}
}
