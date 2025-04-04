package net.sashakyotoz.variousworld.common.blocks.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.sashakyotoz.variousworld.common.blocks.entities.ModHangingSignBlockEntity;

public class ModHangingWallSignBlock extends WallHangingSignBlock {
    public ModHangingWallSignBlock(Properties properties, WoodType type) {
        super(type, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModHangingSignBlockEntity(pos,state);
    }
}
