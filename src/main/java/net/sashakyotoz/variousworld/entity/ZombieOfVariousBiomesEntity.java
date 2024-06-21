
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.network.PlayMessages;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.init.VariousWorldModSounds;
import org.jetbrains.annotations.NotNull;

public class ZombieOfVariousBiomesEntity extends Monster {
	private int attackAnimationRemainingTicks;
	private ItemStack itemStack;

	public ZombieOfVariousBiomesEntity(EntityType<ZombieOfVariousBiomesEntity> type, Level world) {
		super(type, world);
		xpReward = 4;
		float randomItem = (float) Math.random();
		if(randomItem <= 0.125)
			this.itemStack = new ItemStack(VariousWorldModItems.SLIME_CRYSTALIC.get());
		else if(randomItem >= 0.875)
			this.itemStack = new ItemStack(VariousWorldModItems.CRYSTALIC_STRENGTH.get());
		if(itemStack != null)
			this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND,itemStack);
	}
	public void dropCustomDeathLoot(@NotNull DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		if(itemStack != null)
			this.spawnAtLocation(itemStack);
	}


	public int getAttackAnimationRemainingTicks() {
		return this.attackAnimationRemainingTicks;
	}

	public boolean doHurtTarget(@NotNull Entity entity) {
		if (!(entity instanceof LivingEntity)) {
			return false;
		} else {
			this.attackAnimationRemainingTicks = 30;
			this.level().broadcastEntityEvent(this, (byte) 4);
			this.playSound(SoundEvents.RAVAGER_ATTACK, 1.0F, this.getVoicePitch());
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
			this.playSound(SoundEvents.ZOMBIE_HURT, 1.0F, this.getVoicePitch());
		} else {
			super.handleEntityEvent(bytes);
		}
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false, true));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return VariousWorldModSounds.ENTITY_VARIOUS_ZOMBIE_AMBIENT;
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ZOMBIE_STEP, 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(@NotNull DamageSource ds) {
		return SoundEvents.ZOMBIE_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.ZOMBIE_DEATH;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.DROWN))
			return false;
		if (source.is(DamageTypes.EXPLOSION))
			return false;
		if (source.is(DamageTypes.WITHER))
			return false;
		if (source.getMsgId().equals("witherSkull"))
			return false;
		return super.hurt(source, amount);
	}

	public static void init() {
		SpawnPlacements.register(VariousWorldModEntities.ZOMBIE_OF_VARIOUS_BIOMES.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
		builder = builder.add(Attributes.MAX_HEALTH, 22);
		builder = builder.add(Attributes.ARMOR, 2.5);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 24);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.1);
		return builder;
	}
}
