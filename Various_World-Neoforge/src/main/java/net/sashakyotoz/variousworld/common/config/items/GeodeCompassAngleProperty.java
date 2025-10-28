package net.sashakyotoz.variousworld.common.config.items;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public record GeodeCompassAngleProperty(GeodeCompassAngleState state) implements RangeSelectItemModelProperty {
    public static final MapCodec<GeodeCompassAngleProperty> MAP_CODEC = GeodeCompassAngleState.MAP_CODEC.xmap(GeodeCompassAngleProperty::new, (s) -> s.state);

    public GeodeCompassAngleProperty(boolean wobble, GeodeCompassAngleState.CompassTarget compassTarget) {
        this(new GeodeCompassAngleState(wobble, compassTarget));
    }

    public float get(ItemStack stack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
        return this.state.get(stack, level, owner, seed);
    }

    public MapCodec<GeodeCompassAngleProperty> type() {
        return MAP_CODEC;
    }
}