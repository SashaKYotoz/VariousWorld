package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.init.VariousWorldParticleTypes;

import java.util.Comparator;
import java.util.List;

public class LordSwordRightClickedInAirProcedure {
    public static void execute(LevelAccessor accessor, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        double scaling = 0;
        for (int i = 0; i < 16; i++) {
            if (!accessor.getBlockState(new BlockPos(
                            entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
                            entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(),
                            entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()))
                    .canOcclude()) {
                scaling = scaling + 1;
            } else {
                break;
            }
            accessor.addParticle(VariousWorldParticleTypes.LORD_SHOOT_PARTICLE.get(),
                    (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
                    (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
                    (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()), 0, 0.25, 0);
            final Vec3 center = new Vec3(
                    (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
                    (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
                    (entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(scaling)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()));
            List<Entity> entityList = accessor.getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(1.5d), e -> true).stream().sorted(Comparator.comparingDouble(entity1 -> entity1.distanceToSqr(center))).toList();
            for (Entity entityiterator : entityList) {
                if (!(entityiterator == entity)) {
                    if (entityiterator instanceof LivingEntity livingEntity)
                        livingEntity.hurt(livingEntity.damageSources().dragonBreath(), 3 + livingEntity.getRandom().nextIntBetweenInclusive(2,6));
                    if (itemstack.hurt(1, RandomSource.create(), null)) {
                        itemstack.shrink(1);
                        itemstack.setDamageValue(0);
                    }
                }
            }
        }
    }
}
