package net.sashakyotoz.variousworld.common.entities;

import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWEntities;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

public class WanderingZombieEntity extends Zombie {

    public WanderingZombieEntity(EntityType<Entity> entityEntityType, Level level) {
        super(VWEntities.WANDERING_ZOMBIE.get(), level);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
            ItemStack gem = VWItems.SUPPLY_CRYSTAL.toStack();
            ItemStack stack = random.nextInt(3) == 0 ? new ItemStack(Items.IRON_SWORD) : new ItemStack(Items.IRON_SHOVEL);
            gem.set(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(), new SupplyCrystalData(VWItems.SODALITE_SHARD.toStack(),
                    stack.getItem() instanceof AxeItem ? "axe" : "sword"));
            stack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(gem, 16));
            this.setItemSlot(EquipmentSlot.MAINHAND, stack);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.24);
        builder = builder.add(Attributes.MAX_HEALTH, 22);
        builder = builder.add(Attributes.ARMOR, 2);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
        return builder;
    }
}