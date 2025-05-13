package net.sashakyotoz.variousworld.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;

@SuppressWarnings("unchecked")
public class VWItems {
    public static void init() {
    }

    public static final DeferredItem<SignItem> CRYSTALIC_OAK_SIGN = VWRegistryHelper.ofItem("crystalic_oak_sign", properties -> new SignItem(VWBlocks.CRYSTALIC_OAK_SIGN.get(), VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get(), new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("crystalic_oak_sign"))))).model().build();
    public static final DeferredItem<HangingSignItem> CRYSTALIC_OAK_HANGING_SIGN = VWRegistryHelper.ofItem("crystalic_oak_hanging_sign", properties -> new HangingSignItem(VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(), VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get(), new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("crystalic_oak_hanging_sign"))))).model().build();

    public static final DeferredItem<SignItem> BLUE_JACARANDA_SIGN = VWRegistryHelper.ofItem("blue_jacaranda_sign", properties -> new SignItem(VWBlocks.BLUE_JACARANDA_SIGN.get(), VWBlocks.BLUE_JACARANDA_WALL_SIGN.get(), new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("blue_jacaranda_sign"))))).model().build();
    public static final DeferredItem<HangingSignItem> BLUE_JACARANDA_HANGING_SIGN = VWRegistryHelper.ofItem("blue_jacaranda_hanging_sign", properties -> new HangingSignItem(VWBlocks.BLUE_JACARANDA_HANGING_SIGN.get(), VWBlocks.BLUE_JACARANDA_HANGING_WALL_SIGN.get(), new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("blue_jacaranda_hanging_sign")))
    )).model().build();

    public static final DeferredItem<Item> SODALITE_SHARD = VWRegistryHelper.ofItem("sodalite_shard", properties -> new Item(new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("sodalite_shard"))))).model().build();
    public static final DeferredItem<Item> CRYSTALLINE_SLIME_BALL = VWRegistryHelper.ofItem("crystalline_slime_ball", properties -> new Item(new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("crystalline_slime_ball"))))).model().build();
    public static final DeferredItem<Item> CRYSTALLINE_STRENGTH = VWRegistryHelper.ofItem("crystalline_strength", properties -> new Item(new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("crystalline_strength")))
            .rarity(Rarity.RARE))).model().build();

    public static final DeferredItem<Item> SUPPLY_CRYSTAL = VWRegistryHelper.ofItem("supply_crystal", properties -> new Item(new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("supply_crystal")))
            .component(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(), new SupplyCrystalData(ItemStack.EMPTY, "")))).build();

    public static final DeferredItem<SpawnEggItem> CRYSTALIC_SLIME_SPAWN_EGG = VWRegistryHelper.ofItem("crystalic_slime_spawn_egg", properties ->
            new SpawnEggItem(VWEntities.CRYSTALIC_SLIME.get(), new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("crystalic_slime_spawn_egg"))))).build();
    public static final DeferredItem<SpawnEggItem> WANDERING_ZOMBIE_SPAWN_EGG = VWRegistryHelper.ofItem("wandering_zombie_spawn_egg", properties ->
            new SpawnEggItem(VWEntities.WANDERING_ZOMBIE.get(), new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, VariousWorld.createVWLocation("wandering_zombie_spawn_egg"))))).build();
}