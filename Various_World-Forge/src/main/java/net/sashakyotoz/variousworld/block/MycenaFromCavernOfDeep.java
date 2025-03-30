
package net.sashakyotoz.variousworld.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.common.PlantType;

import java.util.Collections;
import java.util.List;

public class MycenaFromCavernOfDeep extends FlowerBlock implements BonemealableBlock {
	public MycenaFromCavernOfDeep() {
		super(() -> MobEffects.MOVEMENT_SPEED, 100,
				BlockBehaviour.Properties.of().sound(SoundType.CORAL_BLOCK).instabreak().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 3).noCollission());
	}

	@Override
	public int getEffectDuration() {
		return 100;
	}
	@Override
	public ItemStack getCloneItemStack(BlockGetter p_152966_, BlockPos p_152967_, BlockState p_152968_) {
		return new ItemStack(VWItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get());
	}

	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(VWBlocks.DEEP_MOSS.get()) || groundState.is(Blocks.MOSS_BLOCK) || groundState.is(VWBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(VWBlocks.MUSHROOM_SPAWNER.get())
				|| groundState.is(VWBlocks.FLOWER_DEEP_MOSS.get()) || groundState.is(Blocks.GRANITE);
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
	public static void execute(LevelAccessor world, double x, double y, double z) {
			if (Math.random() < 0.25) {
				if (world instanceof ServerLevel serverLevel) {
					StructureTemplate template = serverLevel.getStructureManager().getOrCreate(new ResourceLocation("various_world", "mycena_big_mushroom"));
					template.placeInWorld(serverLevel, BlockPos.containing(x - 3, y, z - 3), BlockPos.containing(x - 3, y, z - 3), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
							serverLevel.random, 3);
				}
			}
		}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VWItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get());
		return Collections.singletonList(itemStack);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
		return Math.random() > 0.5;
	}

	@Override
	public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos pos, BlockState blockState) {
		execute(serverLevel, pos.getX(), pos.getY(), pos.getZ());
	}
}
