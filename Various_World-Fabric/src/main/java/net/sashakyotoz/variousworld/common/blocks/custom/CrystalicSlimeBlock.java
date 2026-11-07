package net.sashakyotoz.variousworld.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class CrystalicSlimeBlock extends HalfTransparentBlock {
    private static final double CHIME_SPEED = 0.35;

    public CrystalicSlimeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce())
            super.fallOn(level, state, pos, entity, fallDistance);
        else
            entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
            return;
        }
        Vec3 movement = entity.getDeltaMovement();
        if (movement.y < 0.0) {
            double restitution = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setDeltaMovement(movement.x, -movement.y * restitution, movement.z);
            Level world = entity.level();
            if (-movement.y > CHIME_SPEED && !world.isClientSide())
                world.playSound(null, entity.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 0.8F, 0.8F + world.getRandom().nextFloat() * 0.4F);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        double vertical = Math.abs(entity.getDeltaMovement().y);
        if (vertical < 0.1 && !entity.isSteppingCarefully()) {
            double factor = 0.4 + vertical * 0.2;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(factor, 1.0, factor));
        }
        super.stepOn(level, pos, state, entity);
    }
}
