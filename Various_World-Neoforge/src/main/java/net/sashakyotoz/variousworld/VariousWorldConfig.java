package net.sashakyotoz.variousworld;

import net.neoforged.neoforge.common.ModConfigSpec;

public class VariousWorldConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue GENERATE_CRYSTALIC_FOREST = BUILDER.comment("Determine if Crystalline Forest have to be generated").define("generate_crystalline_forest",true);
    public static final ModConfigSpec SPEC = BUILDER.build();
}