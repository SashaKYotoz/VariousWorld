package net.sashakyotoz.variousworld.common.entities.boat;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

public class VWBoatDispenseItemBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior fallback = new DefaultDispenseItemBehavior();
    private final VWBoatType type;
    private final boolean hasChest;

    public VWBoatDispenseItemBehavior(VWBoatType type, boolean hasChest) {
        this.type = type;
        this.hasChest = hasChest;
    }

    public static void register() {
        for (VWBoatType type : VWBoatType.values()) {
            register(type.boatItem(), new VWBoatDispenseItemBehavior(type, false));
            register(type.chestBoatItem(), new VWBoatDispenseItemBehavior(type, true));
        }
    }

    private static void register(Item item, VWBoatDispenseItemBehavior behavior) {
        DispenserBlock.registerBehavior(item, behavior);
    }

    @Override
    protected ItemStack execute(BlockSource source, ItemStack stack) {
        Direction direction = source.state().getValue(DispenserBlock.FACING);
        ServerLevel level = source.level();
        Vec3 center = source.center();
        double offset = 0.5625 + EntityType.BOAT.getWidth() / 2.0;
        double x = center.x() + direction.getStepX() * offset;
        double y = center.y() + direction.getStepY() * 1.125F;
        double z = center.z() + direction.getStepZ() * offset;
        BlockPos front = source.pos().relative(direction);
        double lift;
        if (level.getFluidState(front).is(FluidTags.WATER)) {
            lift = 1.0;
        } else {
            if (!level.getBlockState(front).isAir() || !level.getFluidState(front.below()).is(FluidTags.WATER))
                return this.fallback.dispense(source, stack);
            lift = 0.0;
        }
        Boat boat = VWBoatItem.create(level, new Vec3(x, y + lift, z), this.hasChest, this.type);
        EntityType.<Boat>createDefaultStackConfig(level, stack, null).accept(boat);
        boat.setYRot(direction.toYRot());
        level.addFreshEntity(boat);
        stack.shrink(1);
        return stack;
    }

    @Override
    protected void playSound(BlockSource source) {
        source.level().levelEvent(1000, source.pos(), 0);
    }
}
