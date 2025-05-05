package net.sashakyotoz.variousworld.common.config;

import com.google.gson.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
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
        register(
                ResourceLocation.fromNamespaceAndPath("various_world", "models/item/supply_crystal.json"),
                () -> true,
                json -> gson.toJson(supplyCrystalJson())
        );
        registerMissingRecipes();
    }

    private static JsonObject supplyCrystalJson() {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "various_world:item/crystals/null");
        root.add("textures", textures);
        JsonArray overrides = getOverrides();
        root.add("overrides", overrides);
        return root;
    }

    private static JsonArray getOverrides() {
        JsonArray overrides = new JsonArray();
        for (int i = 0; i < ModConfigController.CRYSTALING_CONFIG_VALUES.size(); i++) {
            ModConfigController.GemsmithingSetting setting = ModConfigController.CRYSTALING_CONFIG_VALUES.get(i);
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
        BuiltInRegistries.ITEM.stream().filter(OnActionsTrigger::isInstanceOfAny).forEach(item -> {
            if (ModConfigController.CRYSTALING_CONFIG_VALUES != null) {
                for (ModConfigController.GemsmithingSetting setting : ModConfigController.CRYSTALING_CONFIG_VALUES) {
                    ResourceLocation tool = BuiltInRegistries.ITEM.getKey(item);
                    ResourceLocation gem = BuiltInRegistries.ITEM.getKey(setting.item().build());
                    if (setting.item().build().equals(Items.AIR) && !gem.getNamespace().equals(VariousWorld.MOD_ID)) {
                        pendingRecipes.add(new PendingRecipe(setting.item().getId(), setting.item(), setting.prefix()));
                        continue;
                    }
                    ResourceLocation key;
                    if (gem.getNamespace().equals(VariousWorld.MOD_ID))
                        key = ResourceLocation.parse(String.format("recipe/%s_%s_gemsmithing.json", tool.getPath(), gem.getPath()));
                    else
                        key = ResourceLocation.fromNamespaceAndPath(tool.getNamespace(), String.format("recipe/%s_%s_gemsmithing.json", tool.getPath(), gem.getPath()));
                    if (MANAGER_KEEPER.get(0) != null && !((IResourceExistence) MANAGER_KEEPER.get(0)).resourceExists(key))
                        register(key, () -> true, json -> gson.toJson(missingRecipeJson(tool.getPath(), gem.getPath())));
                }
            }
        });
    }

    public static void processPendingRecipes() {
        BuiltInRegistries.ITEM.forEach(item -> {
            if (OnActionsTrigger.isInstanceOfAny(item)) {
                ResourceLocation toolRL = BuiltInRegistries.ITEM.getKey(item);
                for (PendingRecipe pending : pendingRecipes) {
                    if (!pending.lazyGem.getId().getNamespace().equals("minecraft")) {
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
//                            VariousWorld.LOGGER.info("Registered recipe for tool {} using gem {}.", toolRL, gemRL);
                        }
                    }
                }
            }
        });
    }


    private static JsonObject missingRecipeJson(String toolName, String gemName) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "various_world:gemsmith_transform");

        JsonObject tool = new JsonObject();
        tool.addProperty("item", toolName);
        root.add("tool", tool);
        JsonObject gem = new JsonObject();
        gem.addProperty("item", gemName);
        root.add("gem", gem);
        JsonObject result = new JsonObject();
        result.addProperty("id", toolName);
        result.addProperty("count", 1);
        root.add("result", result);
        return root;
    }
}