package net.sashakyotoz.variousworld.common.config.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.NeedleDirectionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

import javax.annotation.Nullable;

public class GeodeCompassAngleState extends NeedleDirectionHelper {
    public static final MapCodec<GeodeCompassAngleState> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.BOOL.optionalFieldOf("wobble", true).forGetter(NeedleDirectionHelper::wobble), GeodeCompassAngleState.CompassTarget.CODEC.fieldOf("target").forGetter(GeodeCompassAngleState::target)).apply(instance, GeodeCompassAngleState::new));
    private final NeedleDirectionHelper.Wobbler wobbler = this.newWobbler(0.8F);
    private final NeedleDirectionHelper.Wobbler noTargetWobbler = this.newWobbler(0.8F);
    private final GeodeCompassAngleState.CompassTarget compassTarget;
    private final RandomSource random = RandomSource.create();

    public GeodeCompassAngleState(boolean wobble, GeodeCompassAngleState.CompassTarget compassTarget) {
        super(wobble);
        this.compassTarget = compassTarget;
    }

    protected float calculate(ItemStack stack, ClientLevel level, int seed, @Nullable ItemOwner owner) {
        GlobalPos globalpos = this.compassTarget.get(level, stack, owner);
        long i = level.getGameTime();
        return !isValidCompassTargetPos(owner, globalpos) ? this.getRandomlySpinningRotation(seed, i) : this.getRotationTowardsCompassTarget(owner, i, globalpos.pos());
    }

    private float getRandomlySpinningRotation(int seed, long gameTime) {
        if (this.noTargetWobbler.shouldUpdate(gameTime)) {
            this.noTargetWobbler.update(gameTime, this.random.nextFloat());
        }

        float f = this.noTargetWobbler.rotation() + (float) hash(seed) / (float) Integer.MAX_VALUE;
        return Mth.positiveModulo(f, 1.0F);
    }

    private float getRotationTowardsCompassTarget(ItemOwner owner, long gameTime, BlockPos pos) {
        float f = (float) getAngleFromEntityToPos(owner, pos);
        float f1 = getWrappedVisualRotationY(owner);
        LivingEntity var9 = owner.asLivingEntity();
        float f2;
        if (var9 instanceof Player player) {
            if (player.isLocalPlayer() && player.level().tickRateManager().runsNormally()) {
                if (this.wobbler.shouldUpdate(gameTime)) {
                    this.wobbler.update(gameTime, 0.5F - (f1 - 0.25F));
                }

                f2 = f + this.wobbler.rotation();
                return Mth.positiveModulo(f2, 1.0F);
            }
        }

        f2 = 0.5F - (f1 - 0.25F - f);
        return Mth.positiveModulo(f2, 1.0F);
    }

    private static boolean isValidCompassTargetPos(@Nullable ItemOwner owner, @Nullable GlobalPos pos) {
        return pos != null && owner != null && pos.dimension() == owner.level().dimension() && !(pos.pos().distToCenterSqr(owner.position()) < (double) 1.0E-5F);
    }

    private static double getAngleFromEntityToPos(ItemOwner owner, BlockPos pos) {
        Vec3 vec3 = Vec3.atCenterOf(pos);
        Vec3 vec31 = owner.position();
        return Math.atan2(vec3.z() - vec31.z(), vec3.x() - vec31.x()) / (double) ((float) Math.PI * 2F);
    }

    private static float getWrappedVisualRotationY(ItemOwner owner) {
        return Mth.positiveModulo(owner.getVisualRotationYInDegrees() / 360.0F, 1.0F);
    }

    private static int hash(int seed) {
        return seed * 1327217883;
    }

    protected GeodeCompassAngleState.CompassTarget target() {
        return this.compassTarget;
    }
    public enum CompassTarget implements StringRepresentable {
        NONE("none") {
            @Nullable
            public GlobalPos get(ClientLevel level, ItemStack stack, @Nullable ItemOwner owner) {
                return null;
            }
        },
        GEODE("geode") {
            @Nullable
            public GlobalPos get(ClientLevel level, ItemStack stack, @Nullable ItemOwner owner) {
                if (stack.has(VWMiscRegistries.GEODE_COMPASS_DATA.get()))
                    return stack.get(VWMiscRegistries.GEODE_COMPASS_DATA.get()).globalPos();
                return new GlobalPos(Level.OVERWORLD, new BlockPos(0, 0, 0));
            }
        };

        public static final Codec<GeodeCompassAngleState.CompassTarget> CODEC = StringRepresentable.fromEnum(GeodeCompassAngleState.CompassTarget::values);
        private final String name;

        CompassTarget(String name) {
            this.name = name;
        }

        public String getSerializedName() {
            return this.name;
        }

        @Nullable
        abstract GlobalPos get(ClientLevel var1, ItemStack var2, @Nullable ItemOwner var3);
    }
}