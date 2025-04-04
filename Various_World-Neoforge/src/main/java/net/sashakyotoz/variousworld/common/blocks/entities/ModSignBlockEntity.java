package net.sashakyotoz.variousworld.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VWBlocks;

public class ModSignBlockEntity extends SignBlockEntity {
    public ModSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(VWBlocks.MOD_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return VWBlocks.MOD_SIGN.get();
    }
}
