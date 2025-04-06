package net.sashakyotoz.variousworld.common;

import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class OnModActionsTrigger {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(VWBlocks.SODALITE_WART.getId(), VWBlocks.POTTED_SODALITE_WART);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(VWBlocks.CRYSTALIC_OAK_SAPLING.getId(), VWBlocks.POTTED_CRYSTALIC_OAK_SAPLING);
    }

    @SubscribeEvent
    public static void modifyDefaultComponentsMap(ModifyDefaultComponentsEvent event) {
        event.modifyMatching(item -> item instanceof TieredItem, builder ->
                builder.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(VWItems.SUPPLY_CRYSTAL.toStack(), 0)));
    }
}