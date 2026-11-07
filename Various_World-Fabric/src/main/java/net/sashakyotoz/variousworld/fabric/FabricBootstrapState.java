package net.sashakyotoz.variousworld.fabric;

public final class FabricBootstrapState {
    private static volatile boolean registriesBootstrapped;

    private FabricBootstrapState() {
    }

    public static void markRegistriesBootstrapped() {
        registriesBootstrapped = true;
    }

    public static boolean registriesBootstrapped() {
        return registriesBootstrapped;
    }
}
