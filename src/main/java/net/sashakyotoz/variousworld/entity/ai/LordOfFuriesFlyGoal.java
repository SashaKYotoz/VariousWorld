package net.sashakyotoz.variousworld.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;
import net.sashakyotoz.variousworld.entity.FuryLordEntity;

import java.util.EnumSet;

public class LordOfFuriesFlyGoal extends Goal {
    private final FuryLordEntity lordEntity;
    public LordOfFuriesFlyGoal(FuryLordEntity lordEntity) {
        this.lordEntity = lordEntity;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }
    public boolean canUse() {
        return lordEntity.getTarget() != null && !lordEntity.getMoveControl().hasWanted();
    }
    @Override
    public boolean canContinueToUse() {
        return lordEntity.getMoveControl().hasWanted() && lordEntity.getTarget() != null && lordEntity.getTarget().isAlive();
    }

    @Override
    public void start() {
        LivingEntity livingentity = lordEntity.getTarget();
        Vec3 vec3d = livingentity.getEyePosition(1);
        lordEntity.getMoveControl().setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 10);
    }
    @Override
    public void tick() {
        LivingEntity livingentity = lordEntity.getTarget();
        if (lordEntity.getBoundingBox().intersects(livingentity.getBoundingBox())) {
            lordEntity.doHurtTarget(livingentity);
        } else {
            double d0 = lordEntity.distanceToSqr(livingentity);
            if (d0 < 64) {
                Vec3 vec3d = livingentity.getEyePosition(1);
                lordEntity.getMoveControl().setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 10);
            }
        }
    }
}