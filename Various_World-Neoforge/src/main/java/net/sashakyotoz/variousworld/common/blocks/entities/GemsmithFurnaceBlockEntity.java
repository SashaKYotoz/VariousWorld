package net.sashakyotoz.variousworld.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.sashakyotoz.variousworld.init.VWBlocks;

public class GemsmithFurnaceBlockEntity extends BlockEntity {
    public int fuel = 0;

    public GemsmithFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(VWBlocks.GEMSMITH_FURNACE_BE.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        fuel = tag.getInt("furnace.progress").get();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("furnace.progress", this.fuel);
    }

    public void tick(Level level, BlockPos pos, BlockState state, GemsmithFurnaceBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            if (blockEntity.fuel > 0)
                blockEntity.fuel--;
            if (blockEntity.fuel <= 0 && state.hasProperty(BlockStateProperties.LIT) && state.getValue(BlockStateProperties.LIT))
                level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, false));
        }
    }
}