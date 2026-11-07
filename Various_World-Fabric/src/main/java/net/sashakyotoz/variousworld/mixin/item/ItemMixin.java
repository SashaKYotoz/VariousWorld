package net.sashakyotoz.variousworld.mixin.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void appendDesc(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag, CallbackInfo ci) {
        Item item = (Item) ((Object) this);
        if (!GemsmithingHelper.isGemmable(item))
            return;
        ItemStack gem = GemsmithingHelper.activeGem(stack);
        if (gem == null || GemsmithingHelper.settingFor(gem) == null)
            return;
        tooltipComponents.add(gem.getHoverName().copy().withStyle(ChatFormatting.AQUA));
        double arrowBonus = GemsmithingHelper.arrowDamageBonus(stack);
        if (arrowBonus > 0.0)
            tooltipComponents.add(Component.translatable("tooltip.various_world.arrow_damage",
                    ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(arrowBonus)).withStyle(ChatFormatting.BLUE));
    }
}
