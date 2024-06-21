package net.sashakyotoz.variousworld.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sashakyotoz.variousworld.VariousWorld;

@Mod.EventBusSubscriber(modid = VariousWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VariousWorldDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        generator.addProvider(true, VariousWorldLootTableProvider.create(packOutput));
    }
}
