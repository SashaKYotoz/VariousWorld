package net.sashakyotoz.variousworld.common.entities.boat;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class VWBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final VWBoatType type;
    private final boolean hasChest;

    public VWBoatItem(boolean hasChest, VWBoatType type, Item.Properties properties) {
        super(properties);
        this.hasChest = hasChest;
        this.type = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS)
            return InteractionResultHolder.pass(stack);
        Vec3 view = player.getViewVector(1.0F);
        List<Entity> entities = level.getEntities(player, player.getBoundingBox().expandTowards(view.scale(5.0)).inflate(1.0), ENTITY_PREDICATE);
        if (!entities.isEmpty()) {
            Vec3 eye = player.getEyePosition();
            for (Entity entity : entities) {
                AABB box = entity.getBoundingBox().inflate(entity.getPickRadius());
                if (box.contains(eye))
                    return InteractionResultHolder.pass(stack);
            }
        }
        if (hitResult.getType() != HitResult.Type.BLOCK)
            return InteractionResultHolder.pass(stack);
        Boat boat = create(level, hitResult.getLocation(), this.hasChest, this.type);
        if (level instanceof ServerLevel serverLevel)
            EntityType.<Boat>createDefaultStackConfig(serverLevel, stack, player).accept(boat);
        boat.setYRot(player.getYRot());
        if (!level.noCollision(boat, boat.getBoundingBox()))
            return InteractionResultHolder.fail(stack);
        if (!level.isClientSide()) {
            level.addFreshEntity(boat);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
            stack.consume(1, player);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static Boat create(Level level, Vec3 pos, boolean hasChest, VWBoatType type) {
        if (hasChest) {
            VWChestBoat boat = new VWChestBoat(level, pos.x, pos.y, pos.z);
            boat.setVWType(type);
            return boat;
        }
        VWBoat boat = new VWBoat(level, pos.x, pos.y, pos.z);
        boat.setVWType(type);
        return boat;
    }

    public VWBoatType getBoatType() {
        return this.type;
    }

    public boolean hasChest() {
        return this.hasChest;
    }
}
