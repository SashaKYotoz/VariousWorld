package net.sashakyotoz.variousworld.mixin;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(method = "getCategory", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"), cancellable = true)
    private static void turnOffWarn(RecipeHolder<?> p_recipe, CallbackInfoReturnable<RecipeBookCategories> cir) {
        if (p_recipe.id().getPath().contains("_gemsmithing"))
            cir.setReturnValue(RecipeBookCategories.SMITHING);
    }
}