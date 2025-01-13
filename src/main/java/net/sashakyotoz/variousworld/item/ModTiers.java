package net.sashakyotoz.variousworld.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.sashakyotoz.variousworld.init.VWItems;

public class ModTiers {
    public static ModTier DARKNIUM = new ModTier(3,1184,9f,3f,32,()-> Ingredient.of(VWItems.DARKNIUM_INGOT.get()));
    public static ModTier LORD_SCALE = new ModTier(4,2008,12f,6f,28,()-> Ingredient.of(VWItems.LORD_FURY_SCALE.get()));
    public static ModTier CRYSTALIC_SLIME = new ModTier(3,1236,9f,4f,24,()-> Ingredient.of(VWItems.SLIME_CRYSTALIC.get()));
}