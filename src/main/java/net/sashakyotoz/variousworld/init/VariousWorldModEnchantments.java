package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.sashakyotoz.variousworld.enchantment.WitheredEnchantment;
import net.sashakyotoz.variousworld.enchantment.JumperEnchantment;
import net.sashakyotoz.variousworld.VariousWorldMod;

public class VariousWorldModEnchantments {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, VariousWorldMod.MODID);
	public static final RegistryObject<Enchantment> WITHERED = ENCHANTMENTS.register("withered", WitheredEnchantment::new);
	public static final RegistryObject<Enchantment> JUMPER = ENCHANTMENTS.register("jumper", JumperEnchantment::new);
}