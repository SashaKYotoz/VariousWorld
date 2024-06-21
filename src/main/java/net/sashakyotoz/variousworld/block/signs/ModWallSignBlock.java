package net.sashakyotoz.variousworld.block.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.sashakyotoz.variousworld.block.entity.ModSignBlockEntity;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(Properties pProperties,WoodType type) {
        super(pProperties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ModSignBlockEntity(pPos, pState);
    }
}