package net.sashakyotoz.variousworld.common.entities.boat;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;

import java.util.function.Supplier;

public enum VWBoatType implements StringRepresentable {
    CRYSTALIC_OAK("crystalic_oak", () -> VWBlocks.CRYSTALIC_OAK_PLANKS.get(), () -> VWItems.CRYSTALIC_OAK_BOAT.get(), () -> VWItems.CRYSTALIC_OAK_CHEST_BOAT.get()),
    BLUE_JACARANDA("blue_jacaranda", () -> VWBlocks.BLUE_JACARANDA_PLANKS.get(), () -> VWItems.BLUE_JACARANDA_BOAT.get(), () -> VWItems.BLUE_JACARANDA_CHEST_BOAT.get());

    private final String name;
    private final Supplier<Block> planks;
    private final Supplier<Item> boat;
    private final Supplier<Item> chestBoat;

    VWBoatType(String name, Supplier<Block> planks, Supplier<Item> boat, Supplier<Item> chestBoat) {
        this.name = name;
        this.planks = planks;
        this.boat = boat;
        this.chestBoat = chestBoat;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public Block planks() {
        return this.planks.get();
    }

    public Item boatItem() {
        return this.boat.get();
    }

    public Item chestBoatItem() {
        return this.chestBoat.get();
    }

    public static VWBoatType byId(int id) {
        VWBoatType[] values = values();
        return id >= 0 && id < values.length ? values[id] : CRYSTALIC_OAK;
    }

    public static VWBoatType byName(String name) {
        for (VWBoatType type : values()) {
            if (type.name.equals(name))
                return type;
        }
        return CRYSTALIC_OAK;
    }
}
