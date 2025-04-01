package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VariousWorld.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, ?> VARIOUS_WORLD_TAB = CREATIVE_MODE_TABS.register("various_world_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.ACACIA_FENCE))
                    .title(Component.translatable("creativetab.various_world_tab")).displayItems((pParameters, tabData) -> {
                        VWRegistryHelper.BLOCKS.getEntries().forEach(block -> tabData.accept(block.get().asItem()));
                    }).build());

}