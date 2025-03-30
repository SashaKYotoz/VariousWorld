
package net.sashakyotoz.variousworld.entity;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.sashakyotoz.variousworld.init.VWEntities;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.procedures.AdvancementsManager;

import java.util.Comparator;
import java.util.List;

public class SpiritOfDeepCavernEntity extends TamableAnimal {


    public SpiritOfDeepCavernEntity(EntityType<SpiritOfDeepCavernEntity> type, Level world) {
        super(type, world);
        xpReward = 1;
        setNoGravity(true);
        this.moveControl = new FlyingMoveControl(this, 10, true);
        double randomItem = Math.round((Math.random()));
        if (randomItem <= 0.125)
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_PICKAXE));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new FlyingPathNavigation(this, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.5, 20) {
            @Override
            protected Vec3 getPosition() {
                RandomSource random = SpiritOfDeepCavernEntity.this.getRandom();
                double dir_x = SpiritOfDeepCavernEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
                double dir_y = SpiritOfDeepCavernEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
                double dir_z = SpiritOfDeepCavernEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
                return new Vec3(dir_x, dir_y, dir_z);
            }
        });
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1, (float) 8, (float) 2, false));
        this.targetSelector.addGoal(3, new OwnerHurtTargetGoal(this) {
            public void start() {
                super.start();
                double x = SpiritOfDeepCavernEntity.this.getX();
                double y = SpiritOfDeepCavernEntity.this.getY();
                double z = SpiritOfDeepCavernEntity.this.getZ();
                Level world = SpiritOfDeepCavernEntity.this.level();
                LivingEntity player;
                player = world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 9, 9, 9), e -> true).stream().min(new Object() {
                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                        return Comparator.comparingDouble(entity -> entity.distanceToSqr(_x, _y, _z));
                    }
                }.compareDistOf(x, y, z)).orElse(null);
                if(player != null)
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,60,2));
            }
        });
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.VEX_AMBIENT;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENDER_DRAGON_FLAP, 0.15f, 1);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.BEE_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.GLOW_SQUID_DEATH;
    }

    @Override
    public boolean causeFallDamage(float l, float d, DamageSource source) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_FIRE))
            return false;
        if (source.is(DamageTypes.DROWN))
            return false;
        if (source.is(DamageTypes.WITHER))
            return false;
        if (source.is(DamageTypes.WITHER_SKULL))
            return false;
        return super.hurt(source, amount);
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
                if (this.isOwnedBy(player)) {
                    if (item.isEdible() && this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.usePlayerItem(player, hand, itemstack);
                        this.heal((float) item.getFoodProperties().getNutrition());
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
                if (result == InteractionResult.SUCCESS || result == InteractionResult.CONSUME)
                    this.setPersistenceRequired();
            }
        }
        if (this.isOwnedBy(player))
            AdvancementsManager.addAdvancement(player, AdvancementsManager.MINING_ALLAY_ADV);
        if (player.getMainHandItem().is(VWItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get())) {
            ItemStack stack = new ItemStack(VWItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get());
            player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
            player.getInventory().setChanged();
            if (player.getRandom().nextBoolean()) {
                if (this.level() instanceof ServerLevel serverLevel)
                    serverLevel.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, player.getEyePosition(), Vec2.ZERO, serverLevel, 4, "", Component.literal(""), serverLevel.getServer(), null).withSuppressedOutput(),
                            "/effect give @e[type= various_world:spiritof_deep_cavern,distance=..8] minecraft:regeneration 5 2");
            }
        }
        return result;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.tickCount % 20 == 0){
            if (this.isTame() && this.getRandom().nextBoolean()){
                final Vec3 center = new Vec3(this.getX(), this.getY(), this.getZ());
                List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, new AABB(center, center).inflate(16), e -> true).stream().sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(center))).toList();
                for (LivingEntity entityIterator : entities) {
                    if (entityIterator instanceof Player player) {
                        if (!player.hasEffect(MobEffects.DIG_SPEED) && !player.level().isClientSide())
                            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,200,3));
                    }
                }
            }
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob ageable) {
        SpiritOfDeepCavernEntity entity = VWEntities.SPIRITOF_DEEP_CAVERN.get().create(level);
        entity.finalizeSpawn(level, level.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.BREEDING, null, null);
        return entity;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return List.of(VWItems.BRANCH_WITH_DRAGON_EYE_FRUIT.get(), VWItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get(), VWItems.SCULKBERRY.get()).contains(stack.getItem());
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public static void init() {
        SpawnPlacements.register(VWEntities.SPIRITOF_DEEP_CAVERN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 10);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        builder = builder.add(Attributes.FLYING_SPEED, 0.3);
        return builder;
    }
}
