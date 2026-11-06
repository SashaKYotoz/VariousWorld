package net.sashakyotoz.variousworld.common.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.lcc.sollib.api.common.SolRegistries;
import net.lcc.sollib.api.common.data.reload.IReloadListener;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.List;

public class VWRuntimeData implements IReloadListener {
    public static final String[] TOOL_NAMES = {"sword", "pickaxe", "axe", "shovel", "hoe"};

    public static void register() {
        for (int i = 0; i < 32; i++) {
            String index = "%02d".formatted(i);
            SolRegistries.Data.RUNTIME.addDefault(VariousWorld.createVWLocation("models/item/geode_compass_%s.json".formatted(index)),
                    compassModel(index).toString());
        }
        SolRegistries.Data.RELOAD.register(new VWRuntimeData());
    }

    @Override
    public void preload(ResourceManager manager) {
        List<ModConfigController.GemsmithingSetting> settings = ModConfigController.CRYSTALING_CONFIG_VALUES;
        if (settings == null)
            return;

        SolRegistries.Data.RUNTIME.addJson(VariousWorld.createVWLocation("models/item/supply_crystal.json"),
                json -> supplyCrystalModel(settings)).withEphemeral(true);

        for (ModConfigController.GemsmithingSetting setting : settings) {
            Item gem = setting.item().build();
            if (gem == Items.AIR)
                continue;
            ResourceLocation gemId = BuiltInRegistries.ITEM.getKey(gem);

            for (String tool : TOOL_NAMES)
                SolRegistries.Data.RUNTIME.addDefault(VariousWorld.createVWLocation("models/item/%s_%s.json".formatted(setting.prefix(), tool)),
                        missingCrystalModel(tool, gemId).toString()).withEphemeral(true);

            for (Item item : BuiltInRegistries.ITEM) {
                if (!(item instanceof TieredItem))
                    continue;
                ResourceLocation toolId = BuiltInRegistries.ITEM.getKey(item);
                ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(toolId.getNamespace(),
                        "recipe/%s_%s_gemsmithing.json".formatted(toolId.getPath(), gemId.getPath()));
                SolRegistries.Data.RUNTIME.addDefault(recipeId, gemsmithRecipe(toolId, gemId).toString()).withEphemeral(true);
            }
        }
    }

    @Override
    public void reload(ResourceManager manager) {
    }

    private static JsonObject compassModel(String index) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", "minecraft:item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "various_world:item/geode_compass/geode_compass_%s".formatted(index));
        model.add("textures", textures);
        return model;
    }

    private static JsonObject supplyCrystalModel(List<ModConfigController.GemsmithingSetting> settings) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "various_world:item/crystals/null");
        root.add("textures", textures);

        JsonArray overrides = new JsonArray();
        for (int i = 0; i < settings.size(); i++) {
            for (int j = 0; j < TOOL_NAMES.length; j++) {
                JsonObject predicate = new JsonObject();
                predicate.addProperty("crystal", i + 1);
                predicate.addProperty("tool", j + 1);
                JsonObject override = new JsonObject();
                override.add("predicate", predicate);
                override.addProperty("model", "various_world:item/" + settings.get(i).prefix() + "_" + TOOL_NAMES[j]);
                overrides.add(override);
            }
        }
        root.add("overrides", overrides);
        return root;
    }

    private static JsonObject missingCrystalModel(String tool, ResourceLocation gemId) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "various_world:item/missing/crystal_" + tool);
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", gemId.withPrefix("item/").toString());
        root.add("textures", textures);
        return root;
    }

    private static JsonObject gemsmithRecipe(ResourceLocation toolId, ResourceLocation gemId) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "various_world:gemsmith_transform");
        JsonObject tool = new JsonObject();
        tool.addProperty("item", toolId.toString());
        root.add("tool", tool);
        JsonObject gem = new JsonObject();
        gem.addProperty("item", gemId.toString());
        root.add("gem", gem);
        JsonObject result = new JsonObject();
        result.addProperty("id", toolId.toString());
        result.addProperty("count", 1);
        root.add("result", result);
        return root;
    }
}
