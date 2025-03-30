
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.sashakyotoz.variousworld.entity.technical.SculkScytheEntity;
import net.sashakyotoz.variousworld.init.VWEntities;
import net.sashakyotoz.variousworld.init.VWItems;

import javax.annotation.Nullable;

public class SculkSkeletonEntity extends AbstractSkeleton {
    @Nullable
    private Mob owner;

    public void setOwner(Mob mob) {
        this.owner = mob;
    }

    public SculkSkeletonEntity(EntityType<SculkSkeletonEntity> type, Level world) {
        super(type, world);
        xpReward = 8;
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(VWItems.SCULK_ARMOR_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(VWItems.SCULK_ARMOR_CHESTPLATE.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(VWItems.SCULK_ARMOR_LEGGINGS.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(VWItems.SCULK_ARMOR_BOOTS.get()));
    }

    @Override
    public void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance instance) {
        super.populateDefaultEquipmentSlots(random, instance);
        ItemStack itemStack = new ItemStack(VWItems.DARKNIUM_SWORD.get());
        if (0.5 <= Math.round((Math.random())))
            itemStack.getOrCreateTag().putDouble("CustomModelData", 1);
        this.setItemSlot(EquipmentSlot.MAINHAND, random.nextFloat() > 0.875 ? new ItemStack(Items.BOW) : itemStack);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, ArmoredSkeletonEntity.class, false, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.targetSelector.addGoal(7, new SculkSkeletonEntity.SculkSkeletonCopyOwnerTarget(this));
    }

    class SculkSkeletonCopyOwnerTarget extends TargetGoal {
        private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

        public SculkSkeletonCopyOwnerTarget(PathfinderMob mob) {
            super(mob, false);
        }

        public boolean canUse() {
            return SculkSkeletonEntity.this.owner != null && SculkSkeletonEntity.this.owner.getTarget() != null && this.canAttack(SculkSkeletonEntity.this.owner.getTarget(), this.copyOwnerTargeting);
        }

        public void start() {
            SculkSkeletonEntity.this.setTarget(SculkSkeletonEntity.this.owner.getTarget());
            super.start();
        }
    }

    public boolean isAlliedTo(Entity entity) {
        return entity instanceof SculkNecromancerSkeletonEntity;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_SKELETON_AMBIENT;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SCULK_BLOCK_FALL, 0.15f, 1);
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.CHAIN_HIT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.SCULK_BLOCK_SPREAD;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.SCULK_VEIN_BREAK;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof SculkScytheEntity || source.getEntity() instanceof NecromancerStaffEntity || source.getEntity() instanceof SculkNecromancerSkeletonEntity)
            return false;
        if (source.is(DamageTypes.IN_FIRE))
            return false;
        if (source.is(DamageTypes.CACTUS))
            return false;
        if (source.is(DamageTypes.EXPLOSION))
            return false;
        if (source.is(DamageTypes.WITHER))
            return false;
        if (source.is(DamageTypes.WITHER_SKULL))
            return false;
        return super.hurt(source, amount);
    }

    public static void init() {
        SpawnPlacements.register(VWEntities.SCULK_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 4);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 8);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.3);
        builder = builder.add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
        return builder;
    }
}