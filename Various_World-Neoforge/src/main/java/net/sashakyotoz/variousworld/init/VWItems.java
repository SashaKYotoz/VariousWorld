package net.sashakyotoz.variousworld.init;

import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.neoforged.neoforge.registries.DeferredItem;

@SuppressWarnings("unchecked")
public class VWItems {
    public static void init() {
    }

    public static final DeferredItem<SignItem> CRYSTALIC_OAK_SIGN = VWRegistryHelper.ofItem("crystalic_oak_sign", () -> new SignItem(new Item.Properties(), VWBlocks.CRYSTALIC_OAK_SIGN.get(), VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get())).model().build();
    public static final DeferredItem<HangingSignItem> CRYSTALIC_OAK_HANGING_SIGN = VWRegistryHelper.ofItem("crystalic_oak_hanging_sign", () -> new HangingSignItem(VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(), VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get(), new Item.Properties())).model().build();
}