package net.sashakyotoz.variousworld.common.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import java.util.List;

public class ApplyGemFunction extends LootItemConditionalFunction {
    public static final MapCodec<ApplyGemFunction> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance)
            .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("gem").forGetter(function -> function.gem))
            .apply(instance, ApplyGemFunction::new));
    private final Item gem;

    protected ApplyGemFunction(List<LootItemCondition> conditions, Item gem) {
        super(conditions);
        this.gem = gem;
    }

    @Override
    public LootItemFunctionType<ApplyGemFunction> getType() {
        return VWMiscRegistries.APPLY_GEM.get();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        return GemsmithingHelper.applyGem(stack, new ItemStack(this.gem));
    }
}