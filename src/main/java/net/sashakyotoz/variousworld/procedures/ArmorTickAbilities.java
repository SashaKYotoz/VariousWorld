package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.init.VariousWorldModMobEffects;

public class ArmorTickAbilities {
    public static void onTick(Entity entity){
        if (!(entity instanceof LivingEntity livingEntity))
            return;
        ItemStack headSlot = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestSlot = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legsSlot = livingEntity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feetSlot = livingEntity.getItemBySlot(EquipmentSlot.FEET);
        if(entity instanceof Player player){
            if(isAngelStarArmorSet(headSlot,chestSlot,legsSlot,feetSlot) || player.isCreative() || player.isSpectator()){
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }else if ((isAngelStarArmorSet(headSlot,chestSlot,legsSlot,feetSlot) || player.isCreative() || player.isSpectator() || player.getInventory().contains(new ItemStack(VariousWorldModItems.TOTEM_OF_DARK_SPIRIT.get())))){
                player.getAbilities().mayfly = false;
                player.onUpdateAbilities();
            }
            if(isCrystalicArmorSet(headSlot,chestSlot,legsSlot,feetSlot) && !player.hasEffect(VariousWorldModMobEffects.AMETHYST_SPIKES.get()) && !player.level().isClientSide()){
                player.addEffect(new MobEffectInstance(VariousWorldModMobEffects.AMETHYST_SPIKES.get(), 120, 0));
            }
            if (isFuryArmorSet(headSlot,chestSlot,legsSlot,feetSlot) && !player.hasEffect(MobEffects.DAMAGE_RESISTANCE) && !player.level().isClientSide())
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
            if(isLordFuryArmorSet(headSlot,chestSlot,legsSlot,feetSlot) && !player.level().isClientSide()){
                if(!player.hasEffect(MobEffects.FIRE_RESISTANCE))
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 1));
                if(!player.hasEffect(MobEffects.DAMAGE_RESISTANCE))
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
            }
        }
    }
    private static boolean isAngelStarArmorSet(ItemStack head, ItemStack chest, ItemStack legs, ItemStack feet) {
        return head.getItem() == VariousWorldModItems.ANGEL_HELMET.get()
                && chest.getItem() == VariousWorldModItems.ANGEL_CHESTPLATE.get()
                && legs.getItem() == VariousWorldModItems.ANGEL_LEGGINGS.get()
                && feet.getItem() == VariousWorldModItems.ANGEL_BOOTS.get();
    }
    private static boolean isCrystalicArmorSet(ItemStack head, ItemStack chest, ItemStack legs, ItemStack feet) {
        return head.getItem() == VariousWorldModItems.CRYSTAL_ARMOR_HELMET.get()
                && chest.getItem() == VariousWorldModItems.CRYSTAL_ARMOR_CHESTPLATE.get()
                && legs.getItem() == VariousWorldModItems.CRYSTAL_ARMOR_LEGGINGS.get()
                && feet.getItem() == VariousWorldModItems.CRYSTAL_ARMOR_BOOTS.get();
    }
    private static boolean isFuryArmorSet(ItemStack head, ItemStack chest, ItemStack legs, ItemStack feet) {
        return head.getItem() == VariousWorldModItems.FURY_HELMET.get()
                && chest.getItem() == VariousWorldModItems.FURY_CHESTPLATE.get()
                && legs.getItem() == VariousWorldModItems.FURY_LEGGINGS.get()
                && feet.getItem() == VariousWorldModItems.FURY_BOOTS.get();
    }
    private static boolean isLordFuryArmorSet(ItemStack head, ItemStack chest, ItemStack legs, ItemStack feet) {
        return head.getItem() == VariousWorldModItems.LORD_FURY_HELMET.get()
                && chest.getItem() == VariousWorldModItems.LORD_FURY_CHESTPLATE.get()
                && legs.getItem() == VariousWorldModItems.LORD_FURY_LEGGINGS.get()
                && feet.getItem() == VariousWorldModItems.LORD_FURY_BOOTS.get();
    }
}
