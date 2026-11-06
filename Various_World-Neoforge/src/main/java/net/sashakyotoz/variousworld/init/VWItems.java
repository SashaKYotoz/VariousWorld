package net.sashakyotoz.variousworld.init;

import net.lcc.sollib.api.common.registry.holder.ItemHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.common.items.GeodeCompassItem;
import net.sashakyotoz.variousworld.common.items.data.GeodeCompassData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.platform.VWPlatform;

import static net.sashakyotoz.variousworld.init.VWRegistryHelper.hiddenItem;
import static net.sashakyotoz.variousworld.init.VWRegistryHelper.item;

public class VWItems {
    public static void init() {
    }

    public static final ItemHolder CRYSTALIC_OAK_SIGN = item("crystalic_oak_sign", () -> new SignItem(new Item.Properties(), VWBlocks.CRYSTALIC_OAK_SIGN.get(), VWBlocks.CRYSTALIC_OAK_WALL_SIGN.get()))
            .withModel(ModelTemplates.FLAT_ITEM);
    public static final ItemHolder CRYSTALIC_OAK_HANGING_SIGN = item("crystalic_oak_hanging_sign", () -> new HangingSignItem(VWBlocks.CRYSTALIC_OAK_HANGING_SIGN.get(), VWBlocks.CRYSTALIC_OAK_HANGING_WALL_SIGN.get(), new Item.Properties()))
            .withModel(ModelTemplates.FLAT_ITEM);

    public static final ItemHolder BLUE_JACARANDA_SIGN = item("blue_jacaranda_sign", () -> new SignItem(new Item.Properties(), VWBlocks.BLUE_JACARANDA_SIGN.get(), VWBlocks.BLUE_JACARANDA_WALL_SIGN.get()))
            .withModel(ModelTemplates.FLAT_ITEM);
    public static final ItemHolder BLUE_JACARANDA_HANGING_SIGN = item("blue_jacaranda_hanging_sign", () -> new HangingSignItem(VWBlocks.BLUE_JACARANDA_HANGING_SIGN.get(), VWBlocks.BLUE_JACARANDA_HANGING_WALL_SIGN.get(), new Item.Properties()))
            .withModel(ModelTemplates.FLAT_ITEM);

    public static final ItemHolder SODALITE_SHARD = item("sodalite_shard", () -> new Item(new Item.Properties())).withModel(ModelTemplates.FLAT_ITEM);
    public static final ItemHolder RECLAIMITE_SHARD = item("reclaimite_shard", () -> new Item(new Item.Properties())).withModel(ModelTemplates.FLAT_ITEM);
    public static final ItemHolder CRYSTALLINE_SLIME_BALL = item("crystalline_slime_ball", () -> new Item(new Item.Properties())).withModel(ModelTemplates.FLAT_ITEM);
    public static final ItemHolder CRYSTALLINE_STRENGTH = item("crystalline_strength", () -> new Item(new Item.Properties().rarity(Rarity.RARE))).withModel(ModelTemplates.FLAT_ITEM);
    public static final ItemHolder GEODE_COMPASS = item("geode_compass", () -> new GeodeCompassItem(new Item.Properties()
            .component(VWMiscRegistries.GEODE_COMPASS_DATA.get(), new GeodeCompassData(new GlobalPos(Level.OVERWORLD, new BlockPos(0, 0, 0))))
            .rarity(Rarity.UNCOMMON)));

    public static final ItemHolder SUPPLY_CRYSTAL = hiddenItem("supply_crystal", () -> new Item(new Item.Properties()
            .component(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(), new SupplyCrystalData(ItemStack.EMPTY, ""))));

    public static final ItemHolder CRYSTALIC_SLIME_SPAWN_EGG = item("crystalic_slime_spawn_egg", () ->
            VWPlatform.INSTANCE.createSpawnEgg(() -> mob(VWEntities.CRYSTALIC_SLIME.get()), -6693377, -3407617, new Item.Properties()));
    public static final ItemHolder WANDERING_ZOMBIE_SPAWN_EGG = item("wandering_zombie_spawn_egg", () ->
            VWPlatform.INSTANCE.createSpawnEgg(() -> mob(VWEntities.WANDERING_ZOMBIE.get()), -12425662, -13057, new Item.Properties()));
    public static final ItemHolder SQUEALING_SPIDER_SPAWN_EGG = item("squealing_spider_spawn_egg", () ->
            VWPlatform.INSTANCE.createSpawnEgg(() -> mob(VWEntities.SQUEALING_SPIDER.get()), 2430480, 5013401, new Item.Properties()));

    @SuppressWarnings("unchecked")
    private static EntityType<? extends Mob> mob(EntityType<?> type) {
        return (EntityType<? extends Mob>) type;
    }
}