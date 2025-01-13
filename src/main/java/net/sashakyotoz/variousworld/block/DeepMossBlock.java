
package net.sashakyotoz.variousworld.block;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.procedures.DeepMossOnBlockRightClickedProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.common.IPlantable;

import java.util.Collections;
import java.util.List;

public class DeepMossBlock extends Block implements BonemealableBlock {
	public DeepMossBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).lightLevel(s -> 1));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 14;
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction direction, IPlantable plantable) {
		return true;
	}

	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VWBlocks.DEEP_MOSS.get());
		return Collections.singletonList(itemStack);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		return Math.random() > 0.75;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos pos, BlockState blockState) {
		DeepMossOnBlockRightClickedProcedure.execute(serverLevel, pos.getX(), pos.getY(), pos.getZ());
	}
}
