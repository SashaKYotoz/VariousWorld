
package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.DungeonHooks;
import net.sashakyotoz.variousworld.init.VariousWorldEntities;
import net.sashakyotoz.variousworld.init.VariousWorldItems;

import java.util.Arrays;
import java.util.List;

public class ArmoredSkeletonEntity extends Monster {
    private int attackAnimationRemainingTicks;
    public int texture;

    public ArmoredSkeletonEntity(EntityType<ArmoredSkeletonEntity> type, Level level) {
        super(type, level);
        xpReward = 5;
        texture = this.getRandom().nextInt(2);
        List<ItemStack> armorSet = List.of(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY);
        ArmorType armorType = ArmorType.values()[this.getRandom().nextIntBetweenInclusive(0, ArmorType.values().length - 1)];
        switch (armorType) {
            case NETHERITE -> armorSet = ArmorType.NETHERITE.armorSet;
            case CHAIN -> armorSet = ArmorType.CHAIN.armorSet;
            case FURY -> armorSet = ArmorType.FURY.armorSet;
            case DARKNIUM -> armorSet = ArmorType.DARKNIUM.armorSet;
        }
        this.setItemSlot(EquipmentSlot.HEAD, armorSet.get(0));
        this.setItemSlot(EquipmentSlot.CHEST, armorSet.get(1));
        this.setItemSlot(EquipmentSlot.LEGS, armorSet.get(2));
        this.setItemSlot(EquipmentSlot.FEET, armorSet.get(3));
        Arrays.fill(this.armorDropChances, 0.1F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, false, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SculkSkeletonEntity.class, false, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(VariousWorldItems.RAW_DARKNIUM_INGOT.get()));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SKELETON_STEP, 0.15f, 1);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    public int getAttackAnimationRemainingTicks() {
        return this.attackAnimationRemainingTicks;
    }

    public boolean doHurtTarget(Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            this.attackAnimationRemainingTicks = 30;
            this.level().broadcastEntityEvent(this, (byte) 4);
            this.playSound(SoundEvents.EVOKER_FANGS_ATTACK, 1.0F, this.getVoicePitch());
            return CrystalWarriorEntity.hurtAndThrowTarget(this, (LivingEntity) entity);
        }
    }

    public void aiStep() {
        if (this.attackAnimationRemainingTicks > 0) {
            --this.attackAnimationRemainingTicks;
        }
        super.aiStep();
    }

    public void handleEntityEvent(byte bytes) {
        if (bytes == 4) {
            this.attackAnimationRemainingTicks = 30;
        } else {
            super.handleEntityEvent(bytes);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
            return false;
        if (source.is(DamageTypes.DROWN))
            return false;
        if (source.is(DamageTypes.WITHER))
            return false;
        if (source.getMsgId().equals("witherSkull"))
            return false;
        return super.hurt(source, amount);
    }

    public static void init() {
        SpawnPlacements.register(VariousWorldEntities.ARMORED_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
        DungeonHooks.addDungeonMob(VariousWorldEntities.ARMORED_SKELETON.get(), 150);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 32);
        builder = builder.add(Attributes.ARMOR, 4);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        return builder;
    }

    enum ArmorType {
        NETHERITE(Items.NETHERITE_HELMET.getDefaultInstance(), Items.NETHERITE_CHESTPLATE.getDefaultInstance(), Items.NETHERITE_LEGGINGS.getDefaultInstance(), Items.NETHERITE_BOOTS.getDefaultInstance()),
        DARKNIUM(VariousWorldItems.DARKNIUM_ARMOR_HELMET.get().getDefaultInstance(), VariousWorldItems.DARKNIUM_ARMOR_CHESTPLATE.get().getDefaultInstance(), VariousWorldItems.DARKNIUM_ARMOR_LEGGINGS.get().getDefaultInstance(), VariousWorldItems.DARKNIUM_ARMOR_BOOTS.get().getDefaultInstance()),
        FURY(VariousWorldItems.FURY_HELMET.get().getDefaultInstance(), VariousWorldItems.FURY_CHESTPLATE.get().getDefaultInstance(), VariousWorldItems.FURY_LEGGINGS.get().getDefaultInstance(), VariousWorldItems.FURY_HELMET.get().getDefaultInstance()),
        CHAIN(Items.CHAINMAIL_HELMET.getDefaultInstance(), Items.CHAINMAIL_CHESTPLATE.getDefaultInstance(), Items.CHAINMAIL_LEGGINGS.getDefaultInstance(), Items.CHAINMAIL_BOOTS.getDefaultInstance());

        ArmorType(ItemStack headStack, ItemStack chestStack, ItemStack legsStack, ItemStack feetStack) {
            armorSet = List.of(headStack, chestStack, legsStack, feetStack);
        }

        public final List<ItemStack> armorSet;
    }
}