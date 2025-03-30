package net.sashakyotoz.variousworld.block.signs;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static WoodType CRYSTALIC_OAK = WoodType.register(new WoodType("crystalic_oak", BlockSetType.OAK));
    public static WoodType MAGNOLIA = WoodType.register(new WoodType("magnolia", BlockSetType.ACACIA));
    public static WoodType SCULK = WoodType.register(new WoodType("sculk", BlockSetType.SPRUCE));
}