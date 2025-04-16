package net.sashakyotoz.variousworld.client;


import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

public class VWItemProperties {
    public static void init() {
        ItemProperties.register(VWItems.SUPPLY_CRYSTAL.get(), ResourceLocation.withDefaultNamespace("crystal"), (itemStack, clientLevel, livingEntity, i) -> {
            if (itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()) != null && ModConfigController.CRYSTALING_CONFIG_VALUES != null) {
                for (ModConfigController.CrystalingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                    SupplyCrystalData data = itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get());
                    if (data != null && data.crystalStack().is(setting.item().build()))
                        return ModConfigController.CRYSTALING_CONFIG_VALUES.indexOf(setting) + 1;
                }
            }
            return 0;
        });
        ItemProperties.register(VWItems.SUPPLY_CRYSTAL.get(), ResourceLocation.withDefaultNamespace("tool"), (itemStack, clientLevel, livingEntity, i) -> {
            if (itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()) != null) {
                SupplyCrystalData data = itemStack.get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get());
                if (data != null)
                    return switch (data.toolType()) {
                        case "sword" -> 1;
                        case "pickaxe" -> 2;
                        case "axe" -> 3;
                        case "shovel" -> 4;
                        case "hoe" -> 5;
                        default -> 0;
                    };
            }
            return 0;
        });
    }
//	public static void addCustomItemProperties() {
//		makeBow(VWItems.CRYSTALIC_BOW.get());
//		makeCrossBow(VWItems.LORD_OF_FURIES_CROSSBOW.get());
//	}
//
//	public static void makeBow(Item item) {
//		ItemProperties.register(item, new ResourceLocation("pull"), (stack, level, entity, i) -> {
//			if (entity == null) {
//				return 0.0F;
//			} else {
//				return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
//			}
//		});
//		ItemProperties.register(item, new ResourceLocation("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
//	}
//		private static void makeCrossBow(Item item) {
//		ItemProperties.register(item, new ResourceLocation("pull"), (stack, level, entity, i) -> {
//			if (entity == null) {
//				return 0.0F;
//			} else {
//				return LordOfFuriesCrossbowItem.isCharged(stack) ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / (float) LordOfFuriesCrossbowItem.getChargeDuration(stack);
//			}
//		});
//		ItemProperties.register(item, new ResourceLocation("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !LordOfFuriesCrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
//		ItemProperties.register(item, new ResourceLocation("charged"), (stack, level, entity, i) -> entity != null && LordOfFuriesCrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
//		ItemProperties.register(item, new ResourceLocation("firework"), (stack, level, entity, i) -> entity != null && LordOfFuriesCrossbowItem.isCharged(stack) && LordOfFuriesCrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
//	}

}
