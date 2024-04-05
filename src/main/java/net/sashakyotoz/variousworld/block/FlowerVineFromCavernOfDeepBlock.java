
package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class FlowerVineFromCavernOfDeepBlock extends GrowingPlantHeadBlock implements BonemealableBlock, DeepCavernsVines {
    public FlowerVineFromCavernOfDeepBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.ROOTS).instabreak().lightLevel(s -> 3).requiresCorrectToolForDrops().noCollission().randomTicks(), Direction.DOWN, SHAPE, false, 0.1);
        this.registerDefaultState(this.stateDefinition.any().setValue(BERRIES, false));
    }

    protected Block getBodyBlock() {
        return VariousWorldModBlocks.VINE_FROM_CAVERNOF_DEEP.get();
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
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource p_220928_) {
        return 1;
    }

    protected boolean canGrowInto(BlockState p_152998_) {
        return p_152998_.isAir();
    }

    protected BlockState updateBodyAfterConvertedFromHead(BlockState p_152987_, BlockState p_152988_) {
        return p_152988_.setValue(BERRIES, p_152987_.getValue(BERRIES));
    }

    protected BlockState getGrowIntoState(BlockState p_220935_, RandomSource p_220936_) {
        return super.getGrowIntoState(p_220935_, p_220936_).setValue(BERRIES, Boolean.valueOf(p_220936_.nextFloat() < 0.15F));
    }
    @Override
    public ItemStack getCloneItemStack(BlockGetter p_152966_, BlockPos p_152967_, BlockState p_152968_) {
        return new ItemStack(VariousWorldModItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get());
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        return DeepCavernsVines.use(player, state, level, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BERRIES);
    }

    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state, boolean p_152973_) {
        return !state.getValue(BERRIES);
    }

    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState p_220933_) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(BERRIES, Boolean.TRUE), 2);
    }
}
