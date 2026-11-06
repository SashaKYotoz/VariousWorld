package net.sashakyotoz.variousworld.datagen;

import net.minecraft.data.PackOutput;
import net.lcc.sollib.api.common.registry.holder.BlockHolder;
import net.lcc.sollib.api.common.registry.holder.EntityHolder;
import net.lcc.sollib.api.common.registry.holder.ItemHolder;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWRegistryHelper;
import net.sashakyotoz.variousworld.init.holder.VWHolders;

public class ModLanguageProvider extends LanguageProvider {
    private static final String NORMAL_CHARS = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_,;.?!/\\'";
    private static final String UPSIDE_DOWN_CHARS = " ɐqɔpǝɟbɥıظʞןɯuodbɹsʇnʌʍxʎzⱯᗺƆᗡƎℲ⅁HIſʞꞀWNOԀὉᴚS⟘∩ΛMXʎZ0ƖᄅƐㄣϛ9ㄥ86‾'؛˙¿¡/\\,";
    private final boolean upsideDown;

    public ModLanguageProvider(PackOutput output, String locale, boolean upsideDown) {
        super(output, VariousWorld.MOD_ID, locale);
        this.upsideDown = upsideDown;
    }

    @Override
    protected void addTranslations() {
        VWRegistryHelper.forEach(ItemHolder.class, holder -> {
            if (!(holder.get() instanceof BlockItem))
                addEntry("item.various_world.", holder.getID().getPath());
        });
        VWRegistryHelper.forEach(BlockHolder.class, holder -> addEntry("block.various_world.", holder.getID().getPath()));
        VWRegistryHelper.forEach(EntityHolder.class, holder -> addEntry("entity.various_world.", holder.getID().getPath()));
        VWRegistryHelper.forEach(VWHolders.VillagerProfessionHolder.class, holder -> addEntry("entity.minecraft.villager.various_world.", holder.getID().getPath()));
        this.add("creativetab.various_world_tab", "Various World");
        this.add("message.various_world.geode_was_found", "Crystal cluster was found");
        this.add("message.various_world.geode_not_found", "No crystal cluster was found");
        this.add("message.various_world.press_shift", "Press [ Shift ]");
        this.add("biome.various_world.crystalline_forest", "Crystalline Forest");
        this.add("biome.various_world.blue_jacaranda_meadow", "Blue Jacaranda Meadow");
        this.add("biome.various_world.reclaimite_caves", "Reclaimite Caves");

        this.add("jei.various_world.disassembly_table", "Disassembly Table \n table block that allows to gather part of crafting ingredients of item. Only 3x3 recipes are detectable");
        this.add("jei.various_world.artifact_table", "Artifact Table \n table block that allows to spread effect of potion in small area. \n Requires redstone power, potion, dragon breath and some valuable item to work.");
        this.add("jei.various_world.gemsmith_table.furnace", "Needs a lit Gemsmith Furnace placed right next to the table");
        this.add("jei.various_world.gemsmith_furnace", "Gemsmith Furnace \n use any furnace fuel on it to heat it up, up to %s seconds of heat. \n Place it next to a Gemsmithing Table; gemsmithing only progresses while the furnace is lit");
        this.add("jei.various_world.crystalline_slime_ball", "Crystalline Slime Ball \n use it on a water cauldron to wash the crystals off and get a regular Slime Ball");
        this.add("gui.various_world.gemsmith_table.preview", "Gemsmithing result");
        this.add("gui.various_world.gemsmith_table.bonus", "%s %s");
        this.add("message.various_world.gemsmith_furnace_full", "Gemsmith Furnace is full of heat");
        this.add("jei.various_world.gemsmith_table", "Gemsmithing Table \n table block that allows to modify tools adding materials. \n Recipes and models are registering dynamically, only config changes are required");

        this.add("advancement.various_world.across_the_world", "Across The Various World");
        this.add("advancement.various_world.across_the_world.desc", "Get into any biome of Various World");
        this.add("advancement.various_world.master_of_various_world", "Master of Various World");
        this.add("advancement.various_world.master_of_various_world.desc", "Get a curiosity from a each of foreign biome");
        this.add("advancement.various_world.tabled", "Tabled");
        this.add("advancement.various_world.tabled.desc", "Acquire each of mod's working table");

        this.add("subtitles.various_world.squealing_spider.ambient", "Squealing Spider squeals");
        this.add("subtitles.various_world.squealing_spider.hurt", "Squealing Spider hurts");
        this.add("subtitles.various_world.squealing_spider.death", "Squealing Spider dies");
        this.add("subtitles.various_world.squealing_spider.shatter", "Crystals shatter");
        this.add("subtitles.various_world.squealing_spider.step", "Squealing Spider skitters");
    }

    private void addEntry(String prefix, String key) {
        super.add(prefix + key, convertToName(key));
    }

    @Override
    public void add(String key, String value) {
        if (upsideDown) super.add(key, toUpsideDown(value));
        else super.add(key, value);
    }

    private String convertToName(String key) {
        StringBuilder builder = new StringBuilder(key.substring(0, 1).toUpperCase() + key.substring(1));
        for (int i = 1; i < builder.length(); i++) {
            if (builder.charAt(i) == '_') {
                builder.deleteCharAt(i);
                builder.replace(i, i + 1, " " + Character.toUpperCase(builder.charAt(i)));
            }
        }
        String name = builder.toString();
        return upsideDown ? toUpsideDown(name) : name;
    }

    private static String toUpsideDown(String name) {
        StringBuilder builder = new StringBuilder();

        for (int i = name.length() - 1; i >= 0; i--) {
            if (i > 2 && name.substring(i - 3, i + 1).equals("%1$s")) {
                builder.append(name, i - 3, i + 1);
                i -= 4;
                continue;
            }

            char upsideDown = UPSIDE_DOWN_CHARS.charAt(NORMAL_CHARS.indexOf(name.charAt(i)));
            builder.append(upsideDown);
        }

        return builder.toString();
    }
}