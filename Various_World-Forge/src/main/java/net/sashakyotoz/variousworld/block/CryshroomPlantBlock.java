package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.storage.loot.LootParams;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;

import java.util.Collections;
import java.util.List;

public class CryshroomPlantBlock extends FlowerBlock implements BonemealableBlock {
	public CryshroomPlantBlock() {
		super(() -> MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of().sound(SoundType.FUNGUS).instabreak().lightLevel(s -> 4).noCollission());
	}

	@Override
	public int getEffectDuration() {
		return 100;
	}
	@Override
	public ItemStack getCloneItemStack(BlockGetter p_152966_, BlockPos p_152967_, BlockState p_152968_) {
		return new ItemStack(VWItems.CRYSHROOM.get());
	}

	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(VWBlocks.BIG_CRYSHROOM_BLOCK.get()) || groundState.is(VWBlocks.MYCENA_FROM_CAVERN_OF_DEEP_BLOCK.get()) || groundState.is(Blocks.BROWN_MUSHROOM_BLOCK) || groundState.is(Blocks.RED_MUSHROOM_BLOCK)
				|| groundState.is(Blocks.MUSHROOM_STEM) || groundState.is(VWBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(VWBlocks.DEEP_MOSS.get()) || groundState.is(VWBlocks.FLOWER_DEEP_MOSS.get())
				|| groundState.is(Blocks.MOSS_BLOCK) || groundState.is(VWBlocks.SCULK_GRASS.get()) || groundState.is(VWBlocks.CRYSTALIC_GRASS.get()) || groundState.is(VWBlocks.SHINY_GRASS.get())
				|| groundState.is(Blocks.STONE) || groundState.is(VWBlocks.MUSHROOM_SPAWNER.get()) || groundState.is(VWBlocks.CRYSTALIC_OAK_LOG.get()) || groundState.is(Blocks.DIORITE);
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		return this.mayPlaceOn(groundState, worldIn, blockpos);
	}
	public static void execute(LevelAccessor world, double x, double y, double z) {
			if (Math.random() < 0.25) {
				if (world instanceof ServerLevel level) {
					StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation("various_world", "big_cryshroom_mushroom"));
					template.placeInWorld(level, BlockPos.containing(x - 2, y, z - 2), BlockPos.containing(x - 2, y, z - 2), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
							level.random, 3);
				}
		}
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VWItems.CRYSHROOM.get());
		return Collections.singletonList(itemStack);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		return Math.random() > 0.65;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos pos, BlockState blockState) {
		execute(serverLevel, pos.getX(), pos.getY(), pos.getZ());
	}
}
