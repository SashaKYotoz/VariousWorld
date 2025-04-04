package net.sashakyotoz.variousworld.common.blocks.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.sashakyotoz.variousworld.common.blocks.entities.ModSignBlockEntity;

public class ModStandingSignBlock extends StandingSignBlock {
    public ModStandingSignBlock(Properties pProperties,WoodType type) {
        super(type, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ModSignBlockEntity(pPos, pState);
    }
}