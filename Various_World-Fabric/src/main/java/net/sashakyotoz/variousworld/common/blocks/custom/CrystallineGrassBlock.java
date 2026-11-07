package net.sashakyotoz.variousworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CrystallineGrassBlock extends SnowyDirtBlock {
    public CrystallineGrassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SNOWY, false));
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!level.isClientSide() && fallDistance > 3.0F && entity instanceof LivingEntity)
            entity.playSound(SoundEvents.AMETHYST_BLOCK_FALL);
        entity.causeFallDamage(fallDistance, 1.05F, entity.damageSources().fall());
    }
}
