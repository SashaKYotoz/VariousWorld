
package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.sashakyotoz.variousworld.procedures.UndergroundSculkFruitBushUpdateTickProcedure;

public class UndergroundSculkBushWithoutFruitBlock extends BushBlock {
	public UndergroundSculkBushWithoutFruitBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.GRASS).sound(SoundType.AZALEA_LEAVES).instabreak().lightLevel(s -> 8).noOcclusion().dynamicShape());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 offset = state.getOffset(world, pos);
		return Shapes.or(box(6, 0, 6, 10, 4, 10), box(1, 4, 1, 15, 15, 15)).move(offset.x, offset.y, offset.z);
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
		return groundState.is(VariousWorldBlocks.SCULK_MOSS_BLOCK.get()) || groundState.is(Blocks.STONE) || groundState.is(Blocks.GRANITE) || groundState.is(Blocks.DIORITE) || groundState.is(Blocks.COBBLESTONE) || groundState.is(Blocks.DEEPSLATE)
				|| groundState.is(Blocks.ANDESITE) || groundState.is(VariousWorldBlocks.SCULK_GRASS.get()) || groundState.is(VariousWorldBlocks.DEEP_MOSS.get()) || groundState.is(VariousWorldBlocks.FLOWER_DEEP_MOSS.get())
				|| groundState.is(Blocks.MOSS_BLOCK);
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
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, player, hand, hit);
		if (player.getMainHandItem().is(Items.BONE_MEAL)) {
			player.getInventory().clearOrCountMatchingItems(p -> player.getMainHandItem().getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
			player.getInventory().setChanged();
			if (player.getRandom().nextBoolean()) {
				BlockState state = VariousWorldBlocks.UNDERGROUND_SCULK_FRUIT_BUSH.get().defaultBlockState();
				world.setBlock(pos, state, 3);
			}
		}
		return InteractionResult.SUCCESS;
	}
}
