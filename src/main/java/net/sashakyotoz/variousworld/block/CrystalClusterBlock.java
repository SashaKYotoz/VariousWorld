package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VWItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CrystalClusterBlock extends AmethystBlock implements SimpleWaterloggedBlock {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	protected final VoxelShape northAabb;
	protected final VoxelShape southAabb;
	protected final VoxelShape eastAabb;
	protected final VoxelShape westAabb;
	protected final VoxelShape upAabb;
	protected final VoxelShape downAabb;

	public CrystalClusterBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(SoundType.AMETHYST_CLUSTER).strength(0.8f, 5f).lightLevel(s -> 2)
				.requiresCorrectToolForDrops().noCollission().noOcclusion().hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.UP));
		this.upAabb = Block.box(7, 0.0D, 7, 16 - 7, 5, 16 - 7);
		this.downAabb = Block.box(7, 16 - 7, 7, 16 - 7, 16.0D, 16 - 7);
		this.northAabb = Block.box(7, 7, 16 - 7, 16 - 7, 16 - 7, 16.0D);
		this.southAabb = Block.box(7, 7, 0.0D, 16 - 7, 16 - 7, 5);
		this.eastAabb = Block.box(0.0D, 7, 7, 7, 16 - 7, 16 - 7);
		this.westAabb = Block.box(16 - 5, 7, 7, 16.0D, 16 - 7, 16 - 7);
	}

	public VoxelShape getShape(BlockState p_152021_, BlockGetter p_152022_, BlockPos p_152023_, CollisionContext p_152024_) {
		Direction direction = p_152021_.getValue(FACING);
		switch (direction) {
			case NORTH :
				return this.northAabb;
			case SOUTH :
				return this.southAabb;
			case EAST :
				return this.eastAabb;
			case WEST :
				return this.westAabb;
			case DOWN :
				return this.downAabb;
			case UP :
			default :
				return this.upAabb;
		}
	}

	public boolean canSurvive(BlockState p_152026_, LevelReader p_152027_, BlockPos p_152028_) {
		Direction direction = p_152026_.getValue(FACING);
		BlockPos blockpos = p_152028_.relative(direction.getOpposite());
		return p_152027_.getBlockState(blockpos).isFaceSturdy(p_152027_, blockpos, direction);
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
			return tieredItem.getTier().getLevel() >= 1;
		return false;
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState p_152038_, LevelAccessor p_152039_, BlockPos p_152040_,
			BlockPos p_152041_) {
		if (state.getValue(WATERLOGGED)) {
			p_152039_.scheduleTick(p_152040_, Fluids.WATER, Fluids.WATER.getTickDelay(p_152039_));
		}
		return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(p_152039_, p_152040_)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, direction, p_152038_, p_152039_, p_152040_, p_152041_);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		LevelAccessor levelaccessor = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		return this.defaultBlockState().setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER)
				.setValue(FACING, context.getClickedFace());
	}

	public BlockState rotate(BlockState p_152033_, Rotation p_152034_) {
		return p_152033_.setValue(FACING, p_152034_.rotate(p_152033_.getValue(FACING)));
	}

	public BlockState mirror(BlockState p_152030_, Mirror p_152031_) {
		return p_152030_.rotate(p_152031_.getRotation(p_152030_.getValue(FACING)));
	}

	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152043_) {
		p_152043_.add(WATERLOGGED, FACING);
	}

	public PushReaction getPistonPushReaction(BlockState p_152047_) {
		return PushReaction.DESTROY;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		int random = builder.getLevel().random.nextInt(10);
		if (random == 0){
			ItemStack itemStack = new ItemStack(VWItems.CRYSTALSHARD.get());
			return Collections.singletonList(itemStack);
		}
		return super.getDrops(blockState, builder);
	}
}
