package net.sashakyotoz.variousworld;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

public class VariousWorldConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.BooleanValue GENERATE_SCULK_VALLEY = BUILDER.comment("Determine if Sculk Valley have to be generated").define("generate_sculk_valley",true);
    public static final ForgeConfigSpec.BooleanValue GENERATE_CRYSTALIC_FOREST = BUILDER.comment("Determine if Crystalic Forest have to be generated").define("generate_crystalic_forest",true);
    public static final ForgeConfigSpec.BooleanValue GENERATE_PEACEFUL_WASTELAND = BUILDER.comment("Determine if Peaceful Wasteland have to be generated").define("generate_peaceful_wasteland",true);
    public static final ForgeConfigSpec.BooleanValue GENERATE_SHINY_VALLEY = BUILDER.comment("Determine if Shiny Valley have to be generated").define("generate_shiny_valley",true);
    public static final ForgeConfigSpec.BooleanValue GENERATE_CAVERN_OF_DEEP = BUILDER.comment("Determine if Caverns of Deep have to be generated").define("generate_caverns_of_deep",true);
    public static final ForgeConfigSpec.BooleanValue GENERATE_CAVERNS_OF_MAGMA_GROWTH = BUILDER.comment("Determine if Caverns of Magma Growth have to be generated").define("generate_caverns_of_magma",true);
    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
