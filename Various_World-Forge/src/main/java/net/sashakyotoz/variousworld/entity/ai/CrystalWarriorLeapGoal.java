package net.sashakyotoz.variousworld.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;

public class CrystalWarriorLeapGoal extends LeapAtTargetGoal {
    private final Mob mob;
    private LivingEntity target;
    private final float vecModifier;
    public CrystalWarriorLeapGoal(CrystalWarriorEntity entity) {
        super(entity, 0.75f);
        this.mob = entity;
        this.vecModifier = 0.75f;
    }
    @Override
    public boolean canUse() {
        if (this.mob.isVehicle()) {
            return false;
        } else {
            this.target = this.mob.getTarget();
            if (this.target == null) {
                return false;
            } else {
                double d0 = this.mob.distanceToSqr(this.target);
                if ((!(d0 < 4.0D) && !(d0 > 16.0D)) || !(this.target.getY() > this.mob.getY()+3)) {
                    if (!this.mob.onGround()) {
                        return false;
                    } else {
                        return this.mob.getRandom().nextInt(reducedTickDelay(5)) == 0;
                    }
                } else {
                    return false;
                }
            }
        }
    }
    public void start() {
        Vec3 vec3 = this.mob.getDeltaMovement();
        Vec3 vec31 = new Vec3(this.target.getX() - this.mob.getX(), 0.0D, this.target.getZ() - this.mob.getZ());
        if (vec31.lengthSqr() > 1.0E-7D)
            vec31 = vec31.normalize().scale(0.5D).add(vec3.scale(0.25D));
        this.mob.setDeltaMovement(vec31.x, this.vecModifier, vec31.z);
    }
}