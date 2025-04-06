package net.sashakyotoz.variousworld.init;

import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;

@SuppressWarnings("unchecked")
public class VWItems {
    public static void init() {
    }

    public static final DeferredItem<SignItem> CRYSTALIC_OAK_SIGN = VWRegistryHelper.ofItem("crystalic_oak_sign", () -> new SignItem(new Item.Properties(), VWBlocks.CRYSTALIC_OAK_SIGN.get(), VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get())).model().build();
    public static final DeferredItem<HangingSignItem> CRYSTALIC_OAK_HANGING_SIGN = VWRegistryHelper.ofItem("crystalic_oak_hanging_sign", () -> new HangingSignItem(VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(), VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get(), new Item.Properties())).model().build();

    public static final DeferredItem<Item> SODALITE_SHARD = VWRegistryHelper.ofItem("sodalite_shard", () -> new Item(new Item.Properties())).model().build();
    public static final DeferredItem<Item> CRYSTALLINE_SLIME_BALL = VWRegistryHelper.ofItem("crystalline_slime_ball", () -> new Item(new Item.Properties())).model().build();
    public static final DeferredItem<Item> CRYSTALLINE_STRENGTH = VWRegistryHelper.ofItem("crystalline_strength", () -> new Item(new Item.Properties().rarity(Rarity.RARE))).model().build();

    public static final DeferredItem<Item> SUPPLY_CRYSTAL = VWRegistryHelper.ofItem("supply_crystal", () -> new Item(new Item.Properties()
            .component(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(), new SupplyCrystalData(ItemStack.EMPTY, "")))).build();
}