package net.sashakyotoz.variousworld.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class WithDescriptionItem extends Item {
    private final Component component;
    public WithDescriptionItem(Properties properties,Component component) {
        super(properties);
        this.component = component;
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(component);
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}