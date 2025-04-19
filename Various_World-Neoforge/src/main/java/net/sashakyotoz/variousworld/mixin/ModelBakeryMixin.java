package net.sashakyotoz.variousworld.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.config.ConfiguredData;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Shadow
    @Final
    private Map<ResourceLocation, UnbakedModel> unbakedCache;

    @WrapOperation(method = "getModel", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 1))
    private Object handleNotRegisteredModel(Map instance, Object key, Object defaultValue, Operation<Object> original) {
        if (ModConfigController.CRYSTALING_CONFIG_VALUES != null)
            for (ModConfigController.CrystalingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                for (int j = 0; j < ConfiguredData.toolNames.length; j++) {
                    String toolName = ConfiguredData.toolNames[j];
                    if (((ResourceLocation) key).getNamespace().contains(VariousWorld.MOD_ID)
                            && !((ResourceLocation) key).toString().contains("air")
                            && ((ResourceLocation) key).toString().contains(toolName)
                            && !setting.item().build().equals(Items.AIR)
                            && ((ResourceLocation) key).toString().contains(setting.prefix())) {
                        BlockModel blockmodel = BlockModel.fromString(ConfiguredData.missingCrystalJson(toolName, setting.item().build()).toString());
                        this.unbakedCache.put(((ResourceLocation) key), blockmodel);
                        return original.call(instance, key, blockmodel);
                    }
                }
            }
        return original.call(instance, key, defaultValue);
    }
}