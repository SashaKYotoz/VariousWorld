package net.sashakyotoz.variousworld.platform;

import net.lcc.sollib.platform.Services;

public final class VWPlatform {
    public static final IVWPlatform INSTANCE = Services.load(IVWPlatform.class);

    private VWPlatform() {
    }
}
