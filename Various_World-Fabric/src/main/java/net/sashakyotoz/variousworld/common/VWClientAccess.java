package net.sashakyotoz.variousworld.common;

import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class VWClientAccess {
    private static Supplier<Level> levelSupplier = () -> null;
    private static BooleanSupplier shiftSupplier = () -> false;

    private VWClientAccess() {
    }

    public static void bind(Supplier<Level> level, BooleanSupplier shiftDown) {
        levelSupplier = level;
        shiftSupplier = shiftDown;
    }

    @Nullable
    public static Level level() {
        return levelSupplier.get();
    }

    public static boolean hasShiftDown() {
        return shiftSupplier.getAsBoolean();
    }
}
