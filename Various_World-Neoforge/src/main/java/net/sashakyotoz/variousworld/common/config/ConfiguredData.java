package net.sashakyotoz.variousworld.common.config;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.sashakyotoz.variousworld.VariousWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

//a bit of tweaks to Lyof's magic:
//https://github.com/Lyof429/EndsPhantasm/blob/master/src/main/java/net/lyof/phantasm/setup/datagen/config/ConfiguredData.java

public class ConfiguredData {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public final ResourceLocation target;
    public Function<JsonElement, String> provider;
    public final Supplier<Boolean> enabled;

    public ConfiguredData(ResourceLocation target, Supplier<Boolean> enabled, Function<JsonElement, String> provider) {
        this.target = target;
        this.provider = provider;
        this.enabled = enabled;
    }

    public String apply(@Nullable String original) {
        return gson.fromJson(this.provider.apply(gson.fromJson(original == null ? "" : original, JsonElement.class)),
                JsonElement.class).toString();
    }


    public static List<ConfiguredData> INSTANCES = new LinkedList<>();

    public static @Nullable ConfiguredData get(ResourceLocation id) {
        return INSTANCES.stream().filter(data -> data.target.equals(id)).findAny().orElse(null);
    }

    protected static void register(ResourceLocation target, Supplier<Boolean> enabled, Function<JsonElement, String> provider) {
        INSTANCES.add(new ConfiguredData(target, enabled, provider));
    }


    public static void register() {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "various_world:item/crystals/null");
        root.add("textures", textures);
        JsonArray overrides = getJsonElements();
        root.add("overrides", overrides);
        VariousWorld.LOGGER.info(gson.toJson(root));
        register(
                ResourceLocation.fromNamespaceAndPath("various_world", "models/item/supply_crystal.json"),
                () -> true,
                json -> gson.toJson(root)
        );
    }

    private static @NotNull JsonArray getJsonElements() {
        JsonArray overrides = new JsonArray();
        String[] toolNames = {"sword", "pickaxe", "axe", "shovel", "hoe"};
        for (int i = 0; i < ModConfigController.CRYSTALING_CONFIG_VALUES.size(); i++) {
            ModConfigController.CrystalingSetting setting = ModConfigController.CRYSTALING_CONFIG_VALUES.get(i);
            int crystalValue = i + 1;
            for (int j = 0; j < toolNames.length; j++) {
                int toolValue = j + 1;
                String toolName = toolNames[j];

                JsonObject overrideEntry = new JsonObject();
                JsonObject predicate = new JsonObject();
                predicate.addProperty("crystal", crystalValue);
                predicate.addProperty("tool", toolValue);
                overrideEntry.add("predicate", predicate);
                overrideEntry.addProperty("model", "various_world:item/" + setting.prefix() + "_" + toolName);
                overrides.add(overrideEntry);
            }
        }
        return overrides;
    }
}
