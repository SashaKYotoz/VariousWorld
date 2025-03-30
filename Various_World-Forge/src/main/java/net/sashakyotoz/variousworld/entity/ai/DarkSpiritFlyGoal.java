package net.sashakyotoz.variousworld.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;

import java.util.EnumSet;

public class DarkSpiritFlyGoal extends Goal {
    private final DarkSpiritEntity spirit;
    public DarkSpiritFlyGoal(DarkSpiritEntity spirit) {
        this.spirit = spirit;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }
    public boolean canUse() {
        return spirit.getTarget() != null && !spirit.getMoveControl().hasWanted();
    }
    @Override
    public boolean canContinueToUse() {
        return spirit.getMoveControl().hasWanted() && spirit.getTarget() != null && spirit.getTarget().isAlive();
    }

    @Override
    public void start() {
        LivingEntity livingentity = spirit.getTarget();
        Vec3 vec3d = livingentity.getEyePosition(1);
        spirit.getMoveControl().setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 3);
    }
    @Override
    public void tick() {
        LivingEntity livingentity = spirit.getTarget();
        if (spirit.getBoundingBox().intersects(livingentity.getBoundingBox())) {
            spirit.doHurtTarget(livingentity);
        } else {
            double d0 = spirit.distanceToSqr(livingentity);
            if (d0 < 32) {
                Vec3 vec3d = livingentity.getEyePosition(1);
                spirit.getMoveControl().setWantedPosition(vec3d.x, vec3d.y, vec3d.z, 3);
            }
        }
    }
}