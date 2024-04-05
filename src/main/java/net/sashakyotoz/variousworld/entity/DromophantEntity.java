package net.sashakyotoz.variousworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import net.sashakyotoz.variousworld.VariousWorldMod;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DromophantEntity extends TamableAnimal implements Saddleable {
    public static final EntityDataAccessor<Boolean> IS_SITTING = SynchedEntityData.defineId(DromophantEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> IS_SADDLED = SynchedEntityData.defineId(DromophantEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> CURRENT_ACTION = SynchedEntityData.defineId(DromophantEntity.class, EntityDataSerializers.STRING);
    private static final EntityDimensions SITTING_DIMENSIONS = EntityDimensions.scalable(1.5f, 1);
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState standUpAnimationState = new AnimationState();
    public final AnimationState eatingAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int timer;

    public DromophantEntity(EntityType<? extends DromophantEntity> type, Level level) {
        super(type, level);
        setMaxUpStep(1.5f);
        GroundPathNavigation groundpathnavigation = (GroundPathNavigation) this.getNavigation();
        this.navigation = groundpathnavigation;
        groundpathnavigation.setCanFloat(true);
        groundpathnavigation.setCanWalkOverFences(true);
        timer = this.getRandom().nextIntBetweenInclusive(100, 300);
    }

    public DromophantEntity(PlayMessages.SpawnEntity entity, Level level) {
        super(VariousWorldModEntities.DROMOPHANT.get(), level);
    }

    @Override
    public void tick() {
        if (timer > 0)
            timer--;
        else {
            setRandomAction();
            timer = this.getRandom().nextIntBetweenInclusive(160, 400);
        }
        if (this.isVehicle()) {
            LivingEntity livingEntity = null;
            final Vec3 center = new Vec3(this.getX(), this.getY(), this.getZ());
            List<Monster> entities = this.level().getEntitiesOfClass(Monster.class, new AABB(center, center).inflate(2d), e -> true).stream().sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(center))).toList();
            for (Monster entityIterator : entities) {
                if (entityIterator != null && entityIterator != this.getPassengers()) {
                    livingEntity = entityIterator;
                    break;
                }
            }
            if (this.getPose().equals(Pose.SITTING))
                this.standUp();
            if (livingEntity != null && this.getBoundingBox().intersects(livingEntity.getBoundingBox().inflate(2))){
                if (!this.attackAnimationState.isStarted())
                    this.attackAnimationState.start(this.tickCount);
                this.doHurtTarget(livingEntity);
            }
        }
        super.tick();
    }

    private void setRandomAction() {
        if (!this.isVehicle()){
            int tmp = this.getRandom().nextIntBetweenInclusive(0, 4);
            switch (tmp) {
                case 0 -> {
                    if (this.getPose() != Pose.SITTING) {
                        this.entityData.set(CURRENT_ACTION, "sitting");
                        sitDown();
                    }
                }
                case 1 -> {
                    if (this.getPose() != Pose.STANDING) {
                        this.entityData.set(CURRENT_ACTION, "standUp");
                        standUp();
                    }
                }
                case 2 -> {
                    if (this.getPose() == Pose.STANDING && this.level().getBlockState(this.getOnPos().below()).is(Blocks.GRASS_BLOCK)){
                        this.entityData.set(CURRENT_ACTION, "eating");
                        eating();
                    }
                }
            }
        }
    }
    private void sitDown() {
        this.sittingAnimationState.start(this.tickCount);
        VariousWorldMod.queueServerWork(15,()-> {
            this.entityData.set(IS_SITTING,true);
            this.setPose(Pose.SITTING);
        });
    }

    private void standUp() {
        this.sittingAnimationState.stop();
        this.standUpAnimationState.start(this.tickCount);
        VariousWorldMod.queueServerWork(15,()-> {
            this.entityData.set(IS_SITTING,false);
            this.setPose(Pose.STANDING);
        });
    }
    private void eating(){
        this.eatingAnimationState.start(this.tickCount);
        VariousWorldMod.queueServerWork(15,()-> this.level().setBlock(this.getOnPos().below(),Blocks.DIRT.defaultBlockState(), 3));
    }
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(new ItemStack(ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(new ResourceLocation("various_world:is_dromophant_food"))).getRandomElement(RandomSource.create()).get()).getItem());
    }

    protected void updateWalkAnimation(float updateTick) {
        float f;
        f = this.getPose() == Pose.STANDING ? Math.min(updateTick * 6.0F, 1.0F) : 0;
        this.walkAnimation.update(f, 0.2F);
    }

    public EntityDimensions getDimensions(Pose pose) {
        return pose == Pose.SITTING ? SITTING_DIMENSIONS.scale(this.getScale()) : super.getDimensions(pose);
    }

    public boolean isSaddle(ItemStack stack) {
        return Objects.equals(Items.SADDLE, stack.getItem());
    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height - 0.1F;
    }

    private boolean isSitting() {
        return this.entityData.get(IS_SITTING);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(1,new FollowParentGoal(this,1.0));
        super.registerGoals();
    }

    @Override
    public void defineSynchedData() {
        this.entityData.define(IS_SADDLED, false);
        this.entityData.define(IS_SITTING, false);
        this.entityData.define(CURRENT_ACTION, "");
        super.defineSynchedData();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult result = InteractionResult.sidedSuccess(this.level().isClientSide());
        Item item = itemstack.getItem();
        if (itemstack.getItem() instanceof SpawnEggItem) {
            result = super.mobInteract(player, hand);
        } else if (this.level().isClientSide()) {
            result = (this.isTame() && this.isOwnedBy(player) || this.isFood(itemstack)) ? InteractionResult.sidedSuccess(this.level().isClientSide()) : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (isSaddle(itemstack)) {
                    setIsSaddled(true);
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                }
                if (this.getPassengers().size() < 2 && !this.isBaby() && isSaddled()) {
                    player.startRiding(this);
                }
                if (this.isOwnedBy(player)) {
                    if (item.isEdible() && this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.usePlayerItem(player, hand, itemstack);
                        this.heal((float) item.getFoodProperties(itemstack, this).getNutrition());
                        result = InteractionResult.sidedSuccess(this.level().isClientSide());
                    } else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.usePlayerItem(player, hand, itemstack);
                        this.heal(4);
                        result = InteractionResult.sidedSuccess(this.level().isClientSide());
                    } else {
                        result = super.mobInteract(player, hand);
                    }
                }
            } else if (this.isFood(itemstack)) {
                this.usePlayerItem(player, hand, itemstack);
                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
                this.setPersistenceRequired();
                result = InteractionResult.sidedSuccess(this.level().isClientSide());
            } else {
                result = super.mobInteract(player, hand);
                if (result == InteractionResult.SUCCESS || result == InteractionResult.CONSUME) {
                    this.setPersistenceRequired();
                }
            }
        }
        return result;
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putBoolean("dromophantSaddled", isSaddled());
        super.addAdditionalSaveData(tag);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        setIsSaddled(tag.getBoolean("dromophantSaddled"));
        super.readAdditionalSaveData(tag);
    }

    @Override
    public void travel(Vec3 dir) {
        Entity entity = this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
        if (this.isVehicle() && !isSitting()) {
            this.setYRot(entity.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(entity.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = entity.getYRot();
            this.yHeadRot = entity.getYRot();
            if (entity instanceof LivingEntity passenger) {
                this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float forward = passenger.zza;
                float strafe = passenger.xxa;
                super.travel(new Vec3(strafe, 0, forward));
            }
            double d1 = this.getX() - this.xo;
            double d0 = this.getZ() - this.zo;
            float f1 = (float) Math.sqrt(d1 * d1 + d0 * d0) * 4;
            if (f1 > 1.0F)
                f1 = 0.9F;
            return;
        }
        super.travel(dir);
    }

    protected Vec3 getRiddenInput(Player player, Vec3 vec3) {
        if (this.onGround() && !this.isSitting()) {
            return Vec3.ZERO;
        } else {
            float f = player.xxa * 0.5F;
            float f1 = player.zza;
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }

            return new Vec3(f, 0.0D, f1);
        }
    }

    @Override
    public void die(DamageSource source) {
        if (isSaddled())
            this.spawnAtLocation(new ItemStack(Items.SADDLE));
        super.die(source);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        DromophantEntity entity = VariousWorldModEntities.DROMOPHANT.get().create(level);
        entity.finalizeSpawn(level, level.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.BREEDING, null, null);
        return entity;
    }

    @Override
    public boolean isSaddleable() {
        return isAlive() && !isBaby();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource source) {
        setIsSaddled(true);
        if (source != null) {
            this.level().playSound(null, this, SoundEvents.RAVAGER_ROAR, source, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(IS_SADDLED);
    }

    private void setIsSaddled(boolean tmp) {
        if (isSaddleable())
            this.entityData.set(IS_SADDLED, tmp);
    }

    protected boolean canAddPassenger(Entity entity) {
        return this.getPassengers().size() <= 2;
    }

    protected void positionRider(Entity entity, Entity.MoveFunction function) {
        int i = this.getPassengers().indexOf(entity);
        if (i >= 0) {
            boolean flag = i == 0;
            float f = 0.3F;
            if (this.getPassengers().size() > 1) {
                if (!flag)
                    f = -0.5F;
                if (entity instanceof Animal)
                    f += 0.2F;
            }
            Vec3 vec3 = (new Vec3(0.0D, 0.0D, f)).yRot(-this.yBodyRot * ((float) Math.PI / 180F));
            function.accept(entity, this.getX() + vec3.x, this.getY() + (this.isSitting() ? 0.9 : 1.5), this.getZ() + vec3.z);
        }
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        if (!this.getPassengers().isEmpty() && this.isSaddled()) {
            Entity entity = this.getPassengers().get(0);
            if (entity instanceof LivingEntity livingEntity) {
                return livingEntity;
            }
        }
        return null;
    }

    public static void init() {
        SpawnPlacements.register(VariousWorldModEntities.DROMOPHANT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DromophantEntity::checkSpiritConditions);
    }

    private static boolean checkSpiritConditions(EntityType<? extends DromophantEntity> type, ServerLevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, RandomSource source) {
        return accessor.canSeeSky(pos) && checkMobSpawnRules(type, accessor, spawnType, pos, source);
    }
    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder.add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.ARMOR, 6)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5);
        return builder;
    }
}