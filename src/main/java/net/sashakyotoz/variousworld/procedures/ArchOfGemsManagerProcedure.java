package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;

import net.sashakyotoz.variousworld.init.VariousWorldItems;

import java.util.function.Supplier;
import java.util.Map;

public class ArchOfGemsManagerProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof ServerPlayer player && !player.level().isClientSide() && player.containerMenu instanceof Supplier<?> supplier && supplier.get() instanceof Map slot) {
            if (((Slot) slot.get(0)).getItem().is(VariousWorldItems.STRENGH_AMULET.get()))
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 2));
            if (((Slot) slot.get(1)).getItem().is(VariousWorldItems.REGENERATION_GEM.get()))
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 4));
            if (((Slot) slot.get(3)).getItem().is(VariousWorldItems.EXPLORER_NECKLACE.get()))
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 300, 2));
            if (((Slot) slot.get(2)).getItem().is(VariousWorldItems.AMETHYST_RING.get()))
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 2));
        }
    }
}