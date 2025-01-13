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
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import java.util.Comparator;
import java.util.List;

public class LordSwordRightClickedInAirProcedure {
    public static void execute(LevelAccessor accessor, Entity entity, ItemStack itemstack) {
        if (entity == null) return;
        double scaling = 0;
        Vec3 eyePosition = entity.getEyePosition(1f);
        Vec3 viewVector = entity.getViewVector(1f);
        for (int i = 0; i < 16; i++) {
            Vec3 targetPosition = eyePosition.add(viewVector.scale(scaling));
            BlockPos blockPos = entity.level().clip(new ClipContext(eyePosition, targetPosition, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos();
            if (!accessor.getBlockState(blockPos).canOcclude())
                scaling += 1;
            else
                break;
            accessor.addParticle(VWMiscRegistries.LORD_SHOOT_PARTICLE.get(),
                    blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    0, 0.25, 0);
            Vec3 center = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            List<Entity> entityList = accessor.getEntitiesOfClass(Entity.class,
                            new AABB(center, center).inflate(1.5d), e -> true)
                    .stream()
                    .sorted(Comparator.comparingDouble(entity1 -> entity1.distanceToSqr(center)))
                    .toList();
            for (Entity nearbyEntity : entityList) {
                if (nearbyEntity != entity && nearbyEntity instanceof LivingEntity livingEntity) {
                    livingEntity.hurt(livingEntity.damageSources().dragonBreath(),
                            3 + livingEntity.getRandom().nextIntBetweenInclusive(2, 6));
                    if (itemstack.hurt(1, RandomSource.create(), null)) {
                        itemstack.shrink(1);
                        itemstack.setDamageValue(0);
                    }
                }
            }
        }
    }

}
