
package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.procedures.UndergroundSculkFruitBushUpdateTickProcedure;

import java.util.Collections;
import java.util.List;

public class UndergroundSculkFruitBushBlock extends FlowerBlock {
	public UndergroundSculkFruitBushBlock() {
		super(() -> MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.copy(Blocks.GRASS).sound(SoundType.AZALEA_LEAVES).instabreak().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)
				.lightLevel(s -> 8).noOcclusion().dynamicShape());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 offset = state.getOffset(world, pos);
		return Shapes.or(box(6, 0, 6, 10, 4, 10), box(1, 4, 1, 15, 15, 15)).move(offset.x, offset.y, offset.z);
	}

	@Override
	public int getEffectDuration() {
		return 100;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 20;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 50;
	}

	@Override
	public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
		return groundState.is(VWBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(Blocks.STONE) || groundState.is(Blocks.COBBLESTONE) || groundState.is(Blocks.DEEPSLATE) || groundState.is(VWBlocks.SCULK_GRASS.get());
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState groundState = worldIn.getBlockState(blockpos);
		return this.mayPlaceOn(groundState, worldIn, blockpos);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		UndergroundSculkFruitBushUpdateTickProcedure.execute(world, pos);
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, level, pos, player, hand, hit);
		if (player.getMainHandItem().is(Items.BONE_MEAL) && !player.level().isClientSide()) {
			if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
				ItemStack stack = new ItemStack(Items.BONE_MEAL);
				player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
				player.getInventory().setChanged();
				if (Math.random() < 0.25) {
					BlockState state = VWBlocks.UNDERGROUND_SCULK_BUSH_WITHOUT_FRUIT.get().defaultBlockState();
					level.setBlock(pos, state, 3);
					player.spawnAtLocation(new ItemStack(VWItems.SCULK_FRUIT.get()));
				}
			}
		}
		return InteractionResult.SUCCESS;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VWItems.SCULK_FRUIT.get());
		return Collections.singletonList(itemStack);
	}
}
