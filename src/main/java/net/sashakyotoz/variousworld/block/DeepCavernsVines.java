package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

import javax.annotation.Nullable;
import java.util.function.ToIntFunction;

public interface DeepCavernsVines {
    VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    BooleanProperty BERRIES = BlockStateProperties.BERRIES;

    static InteractionResult use(@Nullable Entity p_270738_, BlockState p_270772_, Level p_270721_, BlockPos p_270587_) {
        if (p_270772_.getValue(BERRIES)) {
            Block.popResource(p_270721_, p_270587_, new ItemStack(VariousWorldModItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get(), 1));
            float f = Mth.randomBetween(p_270721_.random, 0.8F, 1.2F);
            p_270721_.playSound(null, p_270587_, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            BlockState blockstate = p_270772_.setValue(BERRIES, Boolean.FALSE);
            p_270721_.setBlock(p_270587_, blockstate, 2);
            p_270721_.gameEvent(GameEvent.BLOCK_CHANGE, p_270587_, GameEvent.Context.of(p_270738_, blockstate));
            return InteractionResult.sidedSuccess(p_270721_.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
    static boolean hasGlowBerries(BlockState p_152952_) {
        return p_152952_.hasProperty(BERRIES) && p_152952_.getValue(BERRIES);
    }
    static ToIntFunction<BlockState> emission(int p_181218_) {
        return (p_181216_) -> {
            return p_181216_.getValue(BlockStateProperties.BERRIES) ? p_181218_ : 0;
        };
    }
}
