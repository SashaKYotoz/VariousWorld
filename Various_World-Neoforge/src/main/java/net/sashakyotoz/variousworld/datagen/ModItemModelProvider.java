package net.sashakyotoz.variousworld.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.lcc.sollib.api.common.registry.holder.ItemHolder;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VariousWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        VWRegistryHelper.forEach(ItemHolder.class, holder -> {
            if (holder.hasModel() && holder.getModel() == ModelTemplates.FLAT_ITEM)
                simpleItem(holder.getID().getPath());
            if (holder.get() instanceof SpawnEggItem)
                simpleEggItem(holder.getID().getPath());
        });
        simpleItemlessModel("sodalite_sword");
        simpleItemlessModel("sodalite_pickaxe");
        simpleItemlessModel("sodalite_axe");
        simpleItemlessModel("sodalite_shovel");
        simpleItemlessModel("sodalite_hoe");
        simpleItemlessModel("amethyst_sword");
        simpleItemlessModel("amethyst_pickaxe");
        simpleItemlessModel("amethyst_axe");
        simpleItemlessModel("amethyst_shovel");
        simpleItemlessModel("amethyst_hoe");
        simpleGlowItemlessModel("reclaimite_sword");
        simpleGlowItemlessModel("reclaimite_pickaxe");
        simpleGlowItemlessModel("reclaimite_axe");
        simpleGlowItemlessModel("reclaimite_shovel");
        simpleGlowItemlessModel("reclaimite_hoe");
    }

    private ItemModelBuilder simpleItem(String path) {
        return withExistingParent(path,
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, "item/" + path));
    }

    private ItemModelBuilder simpleItemlessModel(String path) {
        return withExistingParent(VariousWorld.createVWLocation("item/" + path).getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                VariousWorld.createVWLocation("item/crystals/" + path));

    }

    private ItemModelBuilder simpleGlowItemlessModel(String path) {
        String modelName = VariousWorld.createVWLocation("item/" + path).getPath();
        ItemModelBuilder base = withExistingParent(modelName, ResourceLocation.parse("item/generated"))
                .texture("layer0", VariousWorld.createVWLocation("item/crystals/" + path));
        base.customLoader((parent, existingFileHelper) -> new CustomLoaderBuilder<ItemModelBuilder>(
                ResourceLocation.fromNamespaceAndPath("neoforge", "item_layers"),
                parent,
                existingFileHelper, true
        ) {
            @Override
            public JsonObject toJson(JsonObject json) {
                JsonObject neoforgeData = new JsonObject();
                JsonObject layers = new JsonObject();
                JsonObject layer0 = new JsonObject();
                layer0.addProperty("block_light", 15);
                layer0.addProperty("sky_light", 15);
                layers.add("0", layer0);
                neoforgeData.add("layers", layers);
                json.add("neoforge_data", neoforgeData);
                JsonObject renderTypes = new JsonObject();
                JsonArray arr = new JsonArray();
                arr.add(0);
                renderTypes.add("minecraft:translucent", arr);
                json.add("render_types", renderTypes);
                return super.toJson(json);
            }
        });
        return base;
    }

    private ItemModelBuilder simpleEggItem(String path) {
        return withExistingParent(path, ResourceLocation.parse("item/template_spawn_egg"));
    }
}