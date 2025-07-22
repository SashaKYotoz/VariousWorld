package net.sashakyotoz.variousworld.common.config;

import com.google.gson.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

//a bit of tweaks to Lyof's magic:
//https://github.com/Lyof429/EndsPhantasm/blob/master/src/main/java/net/lyof/phantasm/setup/datagen/config/ConfiguredData.java

public class ConfiguredData {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final String[] toolNames = {"sword", "pickaxe", "axe", "shovel", "hoe"};

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

    public static Map<Integer, MultiPackResourceManager> MANAGER_KEEPER = new HashMap<>();

    public static List<ConfiguredData> INSTANCES = new LinkedList<>();

    public static @Nullable ConfiguredData get(ResourceLocation id) {
        return INSTANCES.stream().filter(data -> data.target.equals(id)).findAny().orElse(null);
    }

    protected static void register(ResourceLocation target, Supplier<Boolean> enabled, Function<JsonElement, String> provider) {
        INSTANCES.add(new ConfiguredData(target, enabled, provider));
    }


    public static void register() {
        supplyCrystalJson();
        register(
                ResourceLocation.fromNamespaceAndPath("various_world", "items/supply_crystal.json"),
                () -> true,
                json -> gson.toJson(supplyCrystalJson())
        );
        ConfiguredData.registerModels();
        registerMissingRecipes();
    }

    public static JsonObject supplyCrystalJson() {
        JsonObject root = new JsonObject();
        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:select");
        JsonArray casesArray = getCases();
        model.add("cases", casesArray);
        JsonObject fallback = new JsonObject();
        fallback.addProperty("type", "minecraft:model");
        fallback.addProperty("model", "various_world:item/supply_crystal");
        model.add("fallback", fallback);
        model.addProperty("property", "various_world:crystal");
        root.add("model", model);
        VariousWorld.LOGGER.debug("Model: %s".formatted(root.toString()));
        return root;
    }

    private static JsonArray getCases() {
        JsonArray cases = new JsonArray();
        if (ModConfigController.CRYSTALING_CONFIG_VALUES != null) {
            for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                for (String toolName : toolNames) {
                    JsonObject caseEntry = getEntry(setting, toolName);
                    cases.add(caseEntry);
                }
            }
            return cases;
        }
        return new JsonArray();
    }

    private static final List<String> pendingModels = new ArrayList<>();

    private static @NotNull JsonObject getEntry(ModConfigController.GemsmithingSetting setting, String toolName) {
        JsonObject caseEntry = new JsonObject();
        JsonObject caseModel = new JsonObject();
        caseModel.addProperty("type", "minecraft:model");
        pendingModels.add("various_world:item/" + setting.prefix() + "_" + toolName);
        caseModel.addProperty(
                "model",
                "various_world:item/" + setting.prefix() + "_" + toolName
        );
        caseEntry.add("model", caseModel);
        JsonObject when = new JsonObject();
        when.addProperty("tool_with_gem", "%s_%s".formatted(setting.prefix(), toolName));
        caseEntry.add("when", when);
        return caseEntry;
    }

    public static void registerModels() {
        if (ModConfigController.CRYSTALING_CONFIG_VALUES != null)
            for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                for (int j = 0; j < ConfiguredData.toolNames.length; j++) {
                    String toolName = ConfiguredData.toolNames[j];
                    for (String model : pendingModels) {
                        if (model.contains(toolName) && model.contains(setting.prefix()))
                            register(ResourceLocation.fromNamespaceAndPath("various_world", "models/item/%s_%s.json".formatted(setting.prefix(), toolName)),
                                    () -> true,
                                    json -> gson.toJson(missingCrystalJson(toolName, setting.item().build())));
                    }
                }
            }
    }

    public static JsonObject missingCrystalJson(String tool, Item item) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "various_world:item/missing/crystal_" + tool);

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", String.format("%s:%s",
                BuiltInRegistries.ITEM.getKey(item).getNamespace(),
                BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").getPath()));
        root.add("textures", textures);
        return root;
    }

    private record PendingRecipe(ResourceLocation key, ModConfigController.LazyItem lazyGem,
                                 String prefix) {
    }

    private static final List<PendingRecipe> pendingRecipes = new ArrayList<>();

    public static void registerMissingRecipes() {
        BuiltInRegistries.ITEM.forEach(item -> {
            if (OnActionsTrigger.isInstanceOfAny(item)) {
                if (ModConfigController.CRYSTALING_CONFIG_VALUES != null) {
                    for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES)
                        pendingRecipes.add(new PendingRecipe(setting.item().getId(), setting.item(), setting.prefix()));
                }
            }
        });
    }

    public static void processPendingRecipes() {
        BuiltInRegistries.ITEM.forEach(item -> {
            if (OnActionsTrigger.isInstanceOfAny(item)) {
                ResourceLocation toolRL = BuiltInRegistries.ITEM.getKey(item);
                for (PendingRecipe pending : pendingRecipes) {
                    Item gemItem = pending.lazyGem.build();
                    if (gemItem.equals(Items.AIR)) {
                        VariousWorld.LOGGER.info("Pending recipe: gem item {} still not found.", pending.lazyGem.getId());
                        continue;
                    }
                    ResourceLocation gemRL = pending.lazyGem.getId();
                    ResourceLocation key = ResourceLocation.fromNamespaceAndPath(
                            toolRL.getNamespace(),
                            String.format("recipe/%s_%s_gemsmithing.json", toolRL.getPath(), gemRL.getPath())
                    );
                    if (MANAGER_KEEPER.get(0) != null
                            && !((IResourceExistence) MANAGER_KEEPER.get(0)).resourceExists(key)) {
                        register(key, () -> true, json ->
                                gson.toJson(missingRecipeJson(String.format("%s:%s", toolRL.getNamespace(), toolRL.getPath()), gemRL.toString()))
                        );
                    }
                }
            }
        });
    }


    private static JsonObject missingRecipeJson(String toolName, String gemName) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "various_world:gemsmith_transform");
        root.addProperty("tool", toolName);
        root.addProperty("gem", gemName);
        JsonObject resultObj = new JsonObject();
        resultObj.addProperty("id", toolName);
        root.add("result", resultObj);
        return root;
    }
}