package net.sashakyotoz.variousworld.init;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, VariousWorld.MOD_ID);

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
	}
}