package net.sashakyotoz.variousworld.common;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.sashakyotoz.variousworld.common.blocks.entities.GemsmithTableBlockEntity;
import net.sashakyotoz.variousworld.common.config.ConfiguredData;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.entities.CrystalicSlimeEntity;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWEntities;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class OnModActionsTrigger {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        ModConfigController.init();
        ConfiguredData.processPendingRecipes();
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(VWBlocks.SODALITE_WART.getId(), VWBlocks.POTTED_SODALITE_WART);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(VWBlocks.CRYSTALIC_OAK_SAPLING.getId(), VWBlocks.POTTED_CRYSTALIC_OAK_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(VWBlocks.BLUE_JACARANDA_SAPLING.getId(), VWBlocks.POTTED_BLUE_JACARANDA_SAPLING);
    }

    @SubscribeEvent
    public static void modifyDefaultComponentsMap(ModifyDefaultComponentsEvent event) {
        event.modifyMatching(item -> item instanceof TieredItem, builder ->
                builder.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(VWItems.SUPPLY_CRYSTAL.toStack(), 0)));
    }

    @SubscribeEvent
    public static void registerProviders(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(GemsmithTableBlockEntity.TABLE_ITEM_HANDLER, VWBlocks.GEMSMITH_TABLE_BE.get(),
                (table, side) -> new InvWrapper(table)
        );
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(VWEntities.CRYSTALIC_SLIME.get(), Monster.createMonsterAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnConditions(RegisterSpawnPlacementsEvent event) {
        event.register(VWEntities.CRYSTALIC_SLIME.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                CrystalicSlimeEntity::checkCrystalicSlimeSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
    }
}