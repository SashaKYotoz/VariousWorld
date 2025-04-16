package net.sashakyotoz.variousworld.common.config;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.neoforged.fml.loading.FMLPaths;
import net.sashakyotoz.variousworld.VariousWorld;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ModConfigController {
    public static Configs MOD_CONFIG_VALUES;
    public static List<CrystalingSetting> CRYSTALING_CONFIG_VALUES;
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LazyItem.class, new LazyItemDeserializer())
            .registerTypeAdapter(Attribute.class, new AttributeDeserializer())
            .setPrettyPrinting()
            .create();

    public static class ConfigData {
        public Configs configs;
        @SerializedName("crystaling_settings")
        public List<CrystalingSetting> crystalingSettings;
    }

    public record Configs(boolean do_crystalline_forest, boolean do_guava_blossom) {
    }

    public record CrystalingSetting(LazyItem item, String prefix, int durability, Attribute attribute,
                                    double modify_value) {
    }

    public static class LazyItem {
        private final ResourceLocation itemId;

        public LazyItem(String id) {
            this.itemId = ResourceLocation.parse(id);
        }
        public Item build() {
            return BuiltInRegistries.ITEM.get(itemId);
        }
        public ResourceLocation getId() {
            return itemId;
        }
    }
    public static class LazyItemDeserializer implements JsonDeserializer<LazyItem> {
        @Override
        public LazyItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String itemId = json.getAsString();
            return new LazyItem(itemId);
        }
    }

    public static class AttributeDeserializer implements JsonDeserializer<Attribute> {
        @Override
        public Attribute deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String attributeId = json.getAsString();
            return BuiltInRegistries.ATTRIBUTE.get(ResourceLocation.parse(attributeId));
        }
    }

    public static void init() {
        try {
            Path configPath = FMLPaths.CONFIGDIR.get().resolve("various_world-config.json");
            if (!Files.exists(configPath)) {
                Files.createDirectories(configPath.getParent());
                Files.writeString(configPath, getDefaultConfigJson());
            }
            String json = Files.readString(configPath);

            ConfigData data = gson.fromJson(json, ConfigData.class);
            MOD_CONFIG_VALUES = data.configs;
            CRYSTALING_CONFIG_VALUES = data.crystalingSettings;
        } catch (Exception e) {
            VariousWorld.LOGGER.error("Various World config can't be read");
        }
    }

    private static String getDefaultConfigJson() {
        return """
                {
                    "configs": {
                      "do_crystalline_forest": true,
                      "do_guava_blossom": true
                    },
                    "crystaling_settings": [
                      {
                        "item": "various_world:sodalite_shard",
                        "prefix": "sodalite",
                        "durability": 128,
                        "attribute": "player.block_break_speed",
                        "modify_value": 0.2
                      },
                      {
                        "item": "various_world:crystalline_slime_ball",
                        "prefix": "crystalline_slime_ball",
                        "durability": 32,
                        "attribute": "generic.attack_knockback",
                        "modify_value": 0.1
                      },
                      {
                        "item": "minecraft:amethyst_shard",
                        "prefix": "amethyst",
                        "durability": 72,
                        "attribute": "generic.attack_damage",
                        "modify_value": 1
                      }
                    ]
                  }
                """;
    }
}