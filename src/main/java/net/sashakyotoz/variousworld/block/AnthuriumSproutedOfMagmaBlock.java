
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.PlantType;

import java.util.Collections;
import java.util.List;

public class AnthuriumSproutedOfMagmaBlock extends FlowerBlock {
	public AnthuriumSproutedOfMagmaBlock() {
		super(() -> MobEffects.FIRE_RESISTANCE, 600, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS).instabreak().lightLevel(s -> 10).noCollission());
	}

	@Override
	public int getEffectDuration() {
		return 600;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 100;
	}

	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return BlockPathTypes.LAVA;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}


	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(VariousWorldModBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(VariousWorldModBlocks.DEEP_MOSS.get()) || groundState.is(VariousWorldModBlocks.BLACKLY_STONY_MAGMA.get())
				|| groundState.is(VariousWorldModBlocks.SCULK_MAGMA.get()) || groundState.is(Blocks.MAGMA_BLOCK) || groundState.is(VariousWorldModBlocks.GNEISS.get());
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		return this.mayPlaceOn(groundState, worldIn, blockpos);
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.CAVE;
	}

	@OnlyIn(Dist.CLIENT)
	public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
		event.getBlockColors().register((bs, world, pos, index) -> {
			return world != null && pos != null ? Minecraft.getInstance().level.getBiome(pos).value().getFogColor() : 12638463;
		}, VariousWorldModBlocks.ANTHURIUM_SPROUTED_OF_MAGMA.get());
	}

	@OnlyIn(Dist.CLIENT)
	public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
		event.getItemColors().register((stack, index) -> {
			return 12638463;
		}, VariousWorldModBlocks.ANTHURIUM_SPROUTED_OF_MAGMA.get());
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
			ItemStack itemStack = new ItemStack(VariousWorldModBlocks.ANTHURIUM_SPROUTED_OF_MAGMA.get());
			return Collections.singletonList(itemStack);
	}
}
