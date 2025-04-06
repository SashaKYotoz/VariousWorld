package net.sashakyotoz.variousworld.common.world;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.sashakyotoz.variousworld.common.world.features.ModConfiguredFeatures;

import java.util.Optional;

public class VWTreeGrowers {
    public static final TreeGrower CRYSTALIC_TREE = new TreeGrower(
            "crystalic",
            0.25F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.CRYSTALIC_TREE),
            Optional.of(ModConfiguredFeatures.SMALL_CRYSTALIC_TREE),
            Optional.empty(),
            Optional.empty()
    );
//    public static final TreeGrower MAGNOLIA_TREE = new TreeGrower(
//            "magnolia",
//            0.25F,
//            Optional.empty(),
//            Optional.empty(),
//            Optional.of(VWFeatures.MAGNOLIA_TREE),
//            Optional.empty(),
//            Optional.empty(),
//            Optional.empty()
//    );
}