package net.sashakyotoz.variousworld.common.items.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.GlobalPos;

public record GeodeCompassData(GlobalPos globalPos) {
    public static final Codec<GeodeCompassData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    GlobalPos.CODEC.fieldOf("global_pos").forGetter(GeodeCompassData::globalPos)
            ).apply(instance, GeodeCompassData::new)
    );
}