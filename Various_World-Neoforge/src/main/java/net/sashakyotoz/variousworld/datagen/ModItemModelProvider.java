package net.sashakyotoz.variousworld.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
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
        for (Map.Entry<DeferredItem<?>, ModelTemplate> entry : VWRegistryHelper.ITEM_MODELS.entrySet()) {
            if (entry.getValue() == ModelTemplates.FLAT_ITEM)
                simpleItem(entry.getKey());
        }
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
    }

    private ItemModelBuilder simpleItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleItemlessModel(String path) {
        return withExistingParent(VariousWorld.createVWLocation("item/" + path).getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                VariousWorld.createVWLocation("item/crystals/" + path));

    }

    private ItemModelBuilder simpleEggItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/template_spawn_egg"));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(VariousWorld.MOD_ID, "item/" + item.getId().getPath()));
    }
}
