
package net.sashakyotoz.variousworld.block;

import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;
import java.util.List;

public class CrystalicSlimeBlockBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public CrystalicSlimeBlockBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).sound(SoundType.SLIME_BLOCK).strength(0.5f, 10f).lightLevel(s -> 3).requiresCorrectToolForDrops().friction(0.8f).jumpFactor(2f).noOcclusion().hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	public void fallOn(Level p_154567_, BlockState p_154568_, BlockPos p_154569_, Entity p_154570_, float p_154571_) {
		if (p_154570_.isSuppressingBounce()) {
			super.fallOn(p_154567_, p_154568_, p_154569_, p_154570_, p_154571_);
		} else {
			p_154570_.causeFallDamage(p_154571_, 0.0F, p_154567_.damageSources().fall());
		}
	}

	public void updateEntityAfterFallOn(BlockGetter p_56406_, Entity p_56407_) {
		if (p_56407_.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(p_56406_, p_56407_);
		} else {
			this.bounceUp(p_56407_);
		}
	}

	private void bounceUp(Entity p_56404_) {
		Vec3 vec3 = p_56404_.getDeltaMovement();
		if (vec3.y < 0.0D) {
			double d0 = p_56404_ instanceof LivingEntity ? 1.0D : 0.8D;
			p_56404_.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
		}
	}

	public void stepOn(Level p_154573_, BlockPos p_154574_, BlockState p_154575_, Entity p_154576_) {
		double d0 = Math.abs(p_154576_.getDeltaMovement().y);
		if (d0 < 0.1D && !p_154576_.isSteppingCarefully()) {
			double d1 = 0.4D + d0 * 0.2D;
			p_154576_.setDeltaMovement(p_154576_.getDeltaMovement().multiply(d1, 1.0D, d1));
		}
		super.stepOn(p_154573_, p_154574_, p_154575_, p_154576_);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
			return tieredItem.getTier().getLevel() >= 0;
		return false;
	}
	@Override
	public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
		ItemStack itemStack = new ItemStack(VariousWorldModBlocks.CRYSTALIC_SLIME_BLOCK.get());
		return Collections.singletonList(itemStack);
	}
}
