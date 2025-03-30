
package net.sashakyotoz.variousworld.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MagicVinesBlock extends Block implements IForgeShearable {
	public static final BooleanProperty UP = PipeBlock.UP;
	public static final BooleanProperty NORTH = PipeBlock.NORTH;
	public static final BooleanProperty EAST = PipeBlock.EAST;
	public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
	public static final BooleanProperty WEST = PipeBlock.WEST;
	public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((p_57886_) -> p_57886_.getKey() != Direction.DOWN).collect(Util.toMap());
	private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	private final Map<BlockState, VoxelShape> shapesCache;

	public MagicVinesBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.VINE).sound(SoundType.VINE).strength(0.15f, 5f).lightLevel(s -> 5).noCollission().noOcclusion().hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(
				this.stateDefinition.any().setValue(UP, Boolean.FALSE).setValue(NORTH, Boolean.FALSE).setValue(EAST, Boolean.FALSE).setValue(SOUTH, Boolean.FALSE).setValue(WEST, Boolean.FALSE));
		this.shapesCache = ImmutableMap.copyOf(this.stateDefinition.getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), MagicVinesBlock::calculateShape)));
	}

	private static VoxelShape calculateShape(BlockState state) {
		VoxelShape voxelshape = Shapes.empty();
		if (state.getValue(UP)) {
			voxelshape = UP_AABB;
		}
		if (state.getValue(NORTH)) {
			voxelshape = Shapes.or(voxelshape, NORTH_AABB);
		}
		if (state.getValue(SOUTH)) {
			voxelshape = Shapes.or(voxelshape, SOUTH_AABB);
		}
		if (state.getValue(EAST)) {
			voxelshape = Shapes.or(voxelshape, EAST_AABB);
		}
		if (state.getValue(WEST)) {
			voxelshape = Shapes.or(voxelshape, WEST_AABB);
		}
		return voxelshape.isEmpty() ? Shapes.block() : voxelshape;
	}

	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return this.shapesCache.get(state);
	}

	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
		return true;
	}

	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return this.hasFaces(this.getUpdatedState(state, reader, pos));
	}

	private boolean hasFaces(BlockState state) {
		return this.countFaces(state) > 0;
	}

	private int countFaces(BlockState state) {
		int i = 0;
		for (BooleanProperty booleanproperty : PROPERTY_BY_DIRECTION.values()) {
			if (state.getValue(booleanproperty)) {
				++i;
			}
		}
		return i;
	}

	private boolean canSupportAtFace(BlockGetter getter, BlockPos pos, Direction direction) {
		if (direction == Direction.DOWN) {
			return false;
		} else {
			BlockPos blockpos = pos.relative(direction);
			if (isAcceptableNeighbour(getter, blockpos, direction)) {
				return true;
			} else if (direction.getAxis() == Direction.Axis.Y) {
				return false;
			} else {
				BooleanProperty booleanproperty = PROPERTY_BY_DIRECTION.get(direction);
				BlockState blockstate = getter.getBlockState(pos.above());
				return blockstate.is(this) && blockstate.getValue(booleanproperty);
			}
		}
	}

	public static boolean isAcceptableNeighbour(BlockGetter getter, BlockPos pos, Direction direction) {
		return MultifaceBlock.canAttachTo(getter, direction, pos, getter.getBlockState(pos));
	}

	private BlockState getUpdatedState(BlockState state, BlockGetter getter, BlockPos pos) {
		BlockPos blockpos = pos.above();
		if (state.getValue(UP)) {
			state = state.setValue(UP, isAcceptableNeighbour(getter, blockpos, Direction.DOWN));
		}
		BlockState blockstate = null;
		for (Direction direction : Direction.Plane.HORIZONTAL) {
			BooleanProperty booleanproperty = getPropertyForFace(direction);
			if (state.getValue(booleanproperty)) {
				boolean flag = this.canSupportAtFace(getter, pos, direction);
				if (!flag) {
					if (blockstate == null) {
						blockstate = getter.getBlockState(blockpos);
					}
					flag = blockstate.is(this) && blockstate.getValue(booleanproperty);
				}
				state = state.setValue(booleanproperty, flag);
			}
		}
		return state;
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos p_57879_, BlockPos p_57880_) {
		if (direction == Direction.DOWN) {
			return super.updateShape(state, direction, state1, accessor, p_57879_, p_57880_);
		} else {
			BlockState blockstate = this.getUpdatedState(state, accessor, p_57879_);
			return !this.hasFaces(blockstate) ? Blocks.AIR.defaultBlockState() : blockstate;
		}
	}

	public void randomTick(BlockState state, ServerLevel level, BlockPos p_222657_, RandomSource p_222658_) {
		if (level.random.nextInt(4) == 0 && level.isAreaLoaded(p_222657_, 4)) { // Forge: check area to prevent loading unloaded chunks
			Direction direction = Direction.getRandom(p_222658_);
			BlockPos blockpos = p_222657_.above();
			if (direction.getAxis().isHorizontal() && !state.getValue(getPropertyForFace(direction))) {
				if (this.canSpread(level, p_222657_)) {
					BlockPos blockpos4 = p_222657_.relative(direction);
					BlockState blockstate4 = level.getBlockState(blockpos4);
					if (blockstate4.isAir()) {
						Direction direction3 = direction.getClockWise();
						Direction direction4 = direction.getCounterClockWise();
						boolean flag = state.getValue(getPropertyForFace(direction3));
						boolean flag1 = state.getValue(getPropertyForFace(direction4));
						BlockPos blockpos2 = blockpos4.relative(direction3);
						BlockPos blockpos3 = blockpos4.relative(direction4);
						if (flag && isAcceptableNeighbour(level, blockpos2, direction3)) {
							level.setBlock(blockpos4, this.defaultBlockState().setValue(getPropertyForFace(direction3), Boolean.valueOf(true)), 2);
						} else if (flag1 && isAcceptableNeighbour(level, blockpos3, direction4)) {
							level.setBlock(blockpos4, this.defaultBlockState().setValue(getPropertyForFace(direction4), Boolean.valueOf(true)), 2);
						} else {
							Direction direction1 = direction.getOpposite();
							if (flag && level.isEmptyBlock(blockpos2) && isAcceptableNeighbour(level, p_222657_.relative(direction3), direction1)) {
								level.setBlock(blockpos2, this.defaultBlockState().setValue(getPropertyForFace(direction1), Boolean.valueOf(true)), 2);
							} else if (flag1 && level.isEmptyBlock(blockpos3) && isAcceptableNeighbour(level, p_222657_.relative(direction4), direction1)) {
								level.setBlock(blockpos3, this.defaultBlockState().setValue(getPropertyForFace(direction1), Boolean.valueOf(true)), 2);
							} else if ((double) p_222658_.nextFloat() < 0.05D && isAcceptableNeighbour(level, blockpos4.above(), Direction.UP)) {
								level.setBlock(blockpos4, this.defaultBlockState().setValue(UP, Boolean.valueOf(true)), 2);
							}
						}
					} else if (isAcceptableNeighbour(level, blockpos4, direction)) {
						level.setBlock(p_222657_, state.setValue(getPropertyForFace(direction), Boolean.valueOf(true)), 2);
					}
				}
			} else {
				if (direction == Direction.UP && p_222657_.getY() < level.getMaxBuildHeight() - 1) {
					if (this.canSupportAtFace(level, p_222657_, direction)) {
						level.setBlock(p_222657_, state.setValue(UP, Boolean.valueOf(true)), 2);
						return;
					}
					if (level.isEmptyBlock(blockpos)) {
						if (!this.canSpread(level, p_222657_)) {
							return;
						}
						BlockState blockstate3 = state;
						for (Direction direction2 : Direction.Plane.HORIZONTAL) {
							if (p_222658_.nextBoolean() || !isAcceptableNeighbour(level, blockpos.relative(direction2), direction2)) {
								blockstate3 = blockstate3.setValue(getPropertyForFace(direction2), Boolean.valueOf(false));
							}
						}
						if (this.hasHorizontalConnection(blockstate3)) {
							level.setBlock(blockpos, blockstate3, 2);
						}
						return;
					}
				}
				if (p_222657_.getY() > level.getMinBuildHeight()) {
					BlockPos blockpos1 = p_222657_.below();
					BlockState blockstate = level.getBlockState(blockpos1);
					if (blockstate.isAir() || blockstate.is(this)) {
						BlockState blockstate1 = blockstate.isAir() ? this.defaultBlockState() : blockstate;
						BlockState blockstate2 = this.copyRandomFaces(state, blockstate1, p_222658_);
						if (blockstate1 != blockstate2 && this.hasHorizontalConnection(blockstate2)) {
							level.setBlock(blockpos1, blockstate2, 2);
						}
					}
				}
			}
		}
	}

	private BlockState copyRandomFaces(BlockState p_222651_, BlockState p_222652_, RandomSource p_222653_) {
		for (Direction direction : Direction.Plane.HORIZONTAL) {
			if (p_222653_.nextBoolean()) {
				BooleanProperty booleanproperty = getPropertyForFace(direction);
				if (p_222651_.getValue(booleanproperty)) {
					p_222652_ = p_222652_.setValue(booleanproperty, Boolean.valueOf(true));
				}
			}
		}
		return p_222652_;
	}

	private boolean hasHorizontalConnection(BlockState state) {
		return state.getValue(NORTH) || state.getValue(EAST) || state.getValue(SOUTH) || state.getValue(WEST);
	}

	private boolean canSpread(BlockGetter getter, BlockPos pos) {
		Iterable<BlockPos> iterable = BlockPos.betweenClosed(pos.getX() - 4, pos.getY() - 1, pos.getZ() - 4, pos.getX() + 4, pos.getY() + 1, pos.getZ() + 4);
		int j = 5;
		for (BlockPos blockpos : iterable) {
			if (getter.getBlockState(blockpos).is(this)) {
				--j;
				if (j <= 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canBeReplaced(BlockState state, BlockPlaceContext p_57859_) {
		BlockState blockstate = p_57859_.getLevel().getBlockState(p_57859_.getClickedPos());
		if (blockstate.is(this)) {
			return this.countFaces(blockstate) < PROPERTY_BY_DIRECTION.size();
		} else {
			return super.canBeReplaced(state, p_57859_);
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		boolean flag = blockstate.is(this);
		BlockState blockstate1 = flag ? blockstate : this.defaultBlockState();
		for (Direction direction : context.getNearestLookingDirections()) {
			if (direction != Direction.DOWN) {
				BooleanProperty booleanproperty = getPropertyForFace(direction);
				boolean flag1 = flag && blockstate.getValue(booleanproperty);
				if (!flag1 && this.canSupportAtFace(context.getLevel(), context.getClickedPos(), direction)) {
					return blockstate1.setValue(booleanproperty, Boolean.TRUE);
				}
			}
		}
		return flag ? blockstate1 : null;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57882_) {
		p_57882_.add(UP, NORTH, EAST, SOUTH, WEST);
	}

	public BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case CLOCKWISE_180 ->
                    state.setValue(NORTH, state.getValue(SOUTH)).setValue(EAST, state.getValue(WEST)).setValue(SOUTH, state.getValue(NORTH)).setValue(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90 ->
                    state.setValue(NORTH, state.getValue(EAST)).setValue(EAST, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(WEST)).setValue(WEST, state.getValue(NORTH));
            case CLOCKWISE_90 ->
                    state.setValue(NORTH, state.getValue(WEST)).setValue(EAST, state.getValue(NORTH)).setValue(SOUTH, state.getValue(EAST)).setValue(WEST, state.getValue(SOUTH));
            default -> state;
        };
	}

	public BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case LEFT_RIGHT -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK -> state.setValue(EAST, state.getValue(WEST)).setValue(WEST, state.getValue(EAST));
            default -> super.mirror(state, mirror);
        };
	}

	public static BooleanProperty getPropertyForFace(Direction property) {
		return PROPERTY_BY_DIRECTION.get(property);
	}
}
