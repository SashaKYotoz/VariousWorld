package net.sashakyotoz.variousworld.common.config.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record Crystal(String tool_with_gem) {
    public static final Codec<Crystal> CODEC = RecordCodecBuilder.create(crystalInstance ->
            crystalInstance.group(Codec.STRING.fieldOf("tool_with_gem").forGetter(Crystal::tool_with_gem)).apply(crystalInstance, Crystal::new));
}