package net.sashakyotoz.variousworld.procedures;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class ExplorerNecklaceItemInHandTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y - 3, z))).getBlock() == VariousWorldModBlocks.SAKURA_LEAVES.get() || (world.getBlockState(BlockPos.containing(x, y - 3, z))).getBlock() == VariousWorldModBlocks.CRYSTAL_LEAVES.get()
				|| (world.getBlockState(BlockPos.containing(x, y - 3, z))).getBlock() == VariousWorldModBlocks.SCULK_LEAVES.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 1));
		}
		if ((world.getBlockState(BlockPos.containing(x, y - 3, z))).is(BlockTags.LEAVES)) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 1));
		}
		if (!world.getBlockState(BlockPos.containing(x, y, z).below()).canOcclude()) {
			if (entity instanceof LivingEntity living && !living.level().isClientSide())
				living.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 1));
		}
	}
}
