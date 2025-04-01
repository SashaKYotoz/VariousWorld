package net.sashakyotoz.variousworld.client;


public class VWItemProperties {
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
