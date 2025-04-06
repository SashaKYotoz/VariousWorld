package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sashakyotoz.variousworld.VariousWorld;

public class VWTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VariousWorld.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, ?> VARIOUS_WORLD_TAB = CREATIVE_MODE_TABS.register("various_world_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(VWBlocks.CRYSTALIC_OAK_LOG))
                    .title(Component.translatable("creativetab.various_world_tab")).displayItems((pParameters, tabData) -> {
                        VWRegistryHelper.BLOCKS.getEntries().forEach(block -> {
                            if (!block.getRegisteredName().contains("pot"))
                                tabData.accept(block.get().asItem());
                        });
                        VWRegistryHelper.ITEMS.getEntries().forEach(item -> {
                            if (!item.is(VWItems.SUPPLY_CRYSTAL.getId()) && !item.getRegisteredName().contains("sign") && !item.getRegisteredName().contains("pot"))
                                tabData.accept(item.get().asItem());
                        });
                    }).build());
}