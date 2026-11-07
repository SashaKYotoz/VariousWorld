package net.sashakyotoz.variousworld.mixin.fabric;

import net.minecraft.core.registries.BuiltInRegistries;
import net.sashakyotoz.variousworld.fabric.FabricBootstrapState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltInRegistries.class)
public abstract class BuiltInRegistriesMixin {
    @Inject(method = "bootStrap", at = @At("TAIL"))
    private static void variousworld$markBootstrapped(CallbackInfo ci) {
        FabricBootstrapState.markRegistriesBootstrapped();
    }
}
