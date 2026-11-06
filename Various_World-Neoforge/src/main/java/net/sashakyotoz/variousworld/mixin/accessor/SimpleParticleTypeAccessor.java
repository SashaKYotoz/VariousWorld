package net.sashakyotoz.variousworld.mixin.accessor;

import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SimpleParticleType.class)
public interface SimpleParticleTypeAccessor {
    @Invoker("<init>")
    static SimpleParticleType variousworld$create(boolean overrideLimiter) {
        throw new UnsupportedOperationException();
    }
}
