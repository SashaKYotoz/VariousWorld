
package net.sashakyotoz.variousworld.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldMobEffects;

public class CryshroomItem extends ItemNameBlockItem {
    public CryshroomItem() {
        super(VariousWorldBlocks.CRYSHROOM_PLANT.get(), new Item.Properties().stacksTo(64).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.5f).build()));
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 48;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        ItemStack stack = super.finishUsingItem(itemstack, world, entity);
        if (!entity.level().isClientSide()) {
            if (entity.getRandom().nextBoolean())
                entity.addEffect(new MobEffectInstance(VariousWorldMobEffects.AMETHYST_SPIKES.get(), 180, 1, true, false));
            else if (Math.random() < 0.25)
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 2, true, false));
        }
        return stack;
    }
}
