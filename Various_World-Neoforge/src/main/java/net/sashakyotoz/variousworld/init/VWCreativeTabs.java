package net.sashakyotoz.variousworld.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.init.holder.VWHolders;
import net.sashakyotoz.variousworld.platform.VWPlatform;

public class VWCreativeTabs {
    public static void init() {
    }

    public static final VWHolders.CreativeTabHolder VARIOUS_WORLD_TAB = VWRegistryHelper.creativeTab("various_world_tab",
            () -> VWPlatform.INSTANCE.creativeTabBuilder()
                    .icon(() -> new ItemStack(VWBlocks.BLUE_JACARANDA_SAPLING.get()))
                    .title(Component.translatable("creativetab.various_world_tab"))
                    .displayItems((parameters, output) -> VWRegistryHelper.creativeItems().forEach(output::accept))
                    .build());
}