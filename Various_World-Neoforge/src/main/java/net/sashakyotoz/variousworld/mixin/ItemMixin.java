package net.sashakyotoz.variousworld.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Consumer;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void appendDesc(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag, CallbackInfo ci) {
        Item item = (Item) ((Object) this);
        if (OnActionsTrigger.isInstanceOfAny(item) && ModConfigController.CRYSTALING_CONFIG_VALUES != null && stack.has(VWMiscRegistries.CRYSTAL_DATA.get()) && stack.get(VWMiscRegistries.CRYSTAL_DATA.get()).crystalDurability() > 0) {
            List<ModConfigController.GemsmithingSetting> setting = ModConfigController.CRYSTALING_CONFIG_VALUES;
            for (ModConfigController.GemsmithingSetting crystalingSetting : setting) {
                if (stack.get(VWMiscRegistries.CRYSTAL_DATA.get()).crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()).crystalStack().is(crystalingSetting.item().build())) {
                    tooltipAdder.accept(stack.get(VWMiscRegistries.CRYSTAL_DATA.get()).crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()).crystalStack().getDisplayName());
                }
            }
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"))
    private void handleAttack(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfo ci) {
        if (stack.has(VWMiscRegistries.CRYSTAL_DATA.get())) {
            CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
            if (data.crystalDurability() > 0)
                stack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(data.crystalStack(), data.crystalDurability() - 1));
            else {
                attacker.playSound(SoundEvents.BASALT_BREAK, 2, 1.5f);
                OnActionsTrigger.returnDefaultStack(stack, attacker);
            }
        }
    }

    @Inject(method = "mineBlock", at = @At("HEAD"))
    private void handleMine(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity, CallbackInfoReturnable<Boolean> cir) {
        if (stack.has(VWMiscRegistries.CRYSTAL_DATA.get())) {
            CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
            if (data.crystalDurability() > 0)
                stack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(data.crystalStack(), data.crystalDurability() - 1));
            else {
                miningEntity.playSound(SoundEvents.BASALT_BREAK);
                OnActionsTrigger.returnDefaultStack(stack, miningEntity);
            }
        }
    }
}