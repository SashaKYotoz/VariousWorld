package net.sashakyotoz.variousworld.client;

import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

public class VWItemProperties {
    public static void init() {
//        ItemProperties.register(VWItems.SUPPLY_CRYSTAL.get(), ResourceLocation.withDefaultNamespace("crystal"), (itemStack, clientLevel, livingEntity, i) -> {
//            if (itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()) != null && ModConfigController.CRYSTALING_CONFIG_VALUES != null) {
//                for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
//                    SupplyCrystalData data = itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get());
//                    if (data != null && data.crystalStack().is(setting.item().build()))
//                        return ModConfigController.CRYSTALING_CONFIG_VALUES.indexOf(setting) + 1;
//                }
//            }
//            return 0;
//        });
//        ItemProperties.register(VWItems.SUPPLY_CRYSTAL.get(), ResourceLocation.withDefaultNamespace("tool"), (itemStack, clientLevel, livingEntity, i) -> {
//            if (itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()) != null) {
//                SupplyCrystalData data = itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get());
//                if (data != null)
//                    return switch (data.toolType()) {
//                        case "sword" -> 1;
//                        case "pickaxe" -> 2;
//                        case "axe" -> 3;
//                        case "shovel" -> 4;
//                        case "hoe" -> 5;
//                        default -> 0;
//                    };
//            }
//            return 0;
//        });
    }
}