package net.sashakyotoz.variousworld.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Predicate;

@Mixin(ServerRecipeBook.class)
public class ServerRecipeBookMixin<T> {
    @WrapOperation(
            method = "loadRecipes(Lnet/minecraft/nbt/ListTag;Ljava/util/function/Consumer;Ljava/util/function/Predicate;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/function/Predicate;test(Ljava/lang/Object;)Z"
            )
    )
    private boolean redirectIsRecognized(Predicate<ResourceKey<Recipe<?>>> instance, T t, Operation<Boolean> original) {
        if (((ResourceKey<Recipe<?>>) t).registry().toString().contains("_gemsmithing"))
            return true;
        return original.call(instance, t);
    }
}