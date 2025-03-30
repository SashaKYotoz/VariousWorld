package net.sashakyotoz.variousworld.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VWBlockEntities;

public class ModHangingSignBlockEntity extends HangingSignBlockEntity {
    public ModHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
    @Override
    public BlockEntityType<?> getType() {
        return VWBlockEntities.MOD_HANGING_SIGN.get();
    }
}
