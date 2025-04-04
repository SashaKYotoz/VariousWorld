package net.sashakyotoz.variousworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
    public boolean addLandingEffects(BlockState state1, ServerLevel level, BlockPos pos, BlockState state2, LivingEntity entity, int numberOfParticles) {
        entity.playSound(SoundEvents.AMETHYST_BLOCK_FALL);
        return super.addLandingEffects(state1, level, pos, state2, entity, numberOfParticles);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, 1.05F, entity.damageSources().fall());
    }
}