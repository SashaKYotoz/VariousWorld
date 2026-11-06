package net.sashakyotoz.variousworld.mixin.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void appendDesc(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag, CallbackInfo ci) {
        Item item = (Item) ((Object) this);
        CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
        if (item instanceof TieredItem && ModConfigController.CRYSTALING_CONFIG_VALUES != null && data != null && data.crystalDurability() > 0
                && data.crystalStack().has(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get())) {
            ItemStack gem = data.crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()).crystalStack();
            for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                if (gem.is(setting.item().build()))
                    tooltipComponents.add(gem.getDisplayName());
            }
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"))
    private void handleAttack(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        OnActionsTrigger.wearCrystal(stack, attacker);
    }

    @Inject(method = "mineBlock", at = @At("HEAD"))
    private void handleMine(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity, CallbackInfoReturnable<Boolean> cir) {
        OnActionsTrigger.wearCrystal(stack, miningEntity);
    }
}