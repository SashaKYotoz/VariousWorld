package net.sashakyotoz.variousworld.init;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.sashakyotoz.variousworld.item.LordOfFuriesCrossbowItem;

public class VariousWorldModItemProperties {
	public static void addCustomItemProperties() {
		makeBow(VariousWorldModItems.CRYSTALIC_BOW.get());
		makeCrossBow(VariousWorldModItems.LORD_OF_FURIES_CROSSBOW.get());
	}

	public static void makeBow(Item item) {
		ItemProperties.register(item, new ResourceLocation("pull"), (stack, level, entity, i) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
			}
		});
		ItemProperties.register(item, new ResourceLocation("pulling"), (stack, level, entity, i) -> {
			return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
		});
	}
		private static void makeCrossBow(Item item) {
		ItemProperties.register(item, new ResourceLocation("pull"), (stack, p_174621_, p_174622_, p_174623_) -> {
			if (p_174622_ == null) {
				return 0.0F;
			} else {
				return LordOfFuriesCrossbowItem.isCharged(stack) ? 0.0F : (float) (stack.getUseDuration() - p_174622_.getUseItemRemainingTicks()) / (float) LordOfFuriesCrossbowItem.getChargeDuration(stack);
			}
		});
		ItemProperties.register(item, new ResourceLocation("pulling"), (stack, level, entity, p_174618_) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !LordOfFuriesCrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
		ItemProperties.register(item, new ResourceLocation("charged"), (stack, p_174611_, entity, p_174613_) -> entity != null && LordOfFuriesCrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
		ItemProperties.register(item, new ResourceLocation("firework"), (stack, p_174606_, entity, p_174608_) -> entity != null && LordOfFuriesCrossbowItem.isCharged(stack) && LordOfFuriesCrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
	}

}
