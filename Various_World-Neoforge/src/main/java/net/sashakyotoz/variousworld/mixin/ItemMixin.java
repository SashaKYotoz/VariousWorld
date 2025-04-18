package net.sashakyotoz.variousworld.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
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

@Mixin(Item.class)
public abstract class ItemMixin {

    @Shadow
    public abstract SoundEvent getBreakingSound();

    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void appendDesc(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag, CallbackInfo ci) {
        Item item = (Item) ((Object) this);
        if (item instanceof TieredItem && ModConfigController.CRYSTALING_CONFIG_VALUES != null && stack.has(VWMiscRegistries.CRYSTAL_DATA.get()) && stack.get(VWMiscRegistries.CRYSTAL_DATA.get()).crystalDurability() > 0) {
            List<ModConfigController.CrystalingSetting> setting = ModConfigController.CRYSTALING_CONFIG_VALUES;
            for (ModConfigController.CrystalingSetting crystalingSetting : setting) {
                if (stack.get(VWMiscRegistries.CRYSTAL_DATA.get()).crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()).crystalStack().is(crystalingSetting.item().build())) {
                    tooltipComponents.add(stack.get(VWMiscRegistries.CRYSTAL_DATA.get()).crystalStack().get(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get()).crystalStack().getDisplayName());
                }
            }
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"))
    private void handleAttack(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if (stack.has(VWMiscRegistries.CRYSTAL_DATA.get())) {
            CrystalData data = stack.get(VWMiscRegistries.CRYSTAL_DATA.get());
            if (data.crystalDurability() > 0)
                stack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(data.crystalStack(), data.crystalDurability() - 1));
            else {
                attacker.playSound(this.getBreakingSound(), 2, 1.5f);
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
                miningEntity.playSound(this.getBreakingSound());
                OnActionsTrigger.returnDefaultStack(stack, miningEntity);
            }
        }
    }
}