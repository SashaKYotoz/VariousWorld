package net.sashakyotoz.variousworld.common.entities.boat;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.init.VWEntities;
import org.jetbrains.annotations.Nullable;

public class VWChestBoat extends ChestBoat implements VWBoatAccess {
    private static final EntityDataAccessor<Integer> DATA_VW_TYPE = SynchedEntityData.defineId(VWChestBoat.class, EntityDataSerializers.INT);

    public VWChestBoat(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    @SuppressWarnings("unchecked")
    public VWChestBoat(Level level, double x, double y, double z) {
        this((EntityType<? extends Boat>) VWEntities.CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VW_TYPE, 0);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString(VWBoat.TYPE_TAG, this.getVWType().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains(VWBoat.TYPE_TAG))
            this.setVWType(VWBoatType.byName(tag.getString(VWBoat.TYPE_TAG)));
    }

    @Override
    public VWBoatType getVWType() {
        return VWBoatType.byId(this.entityData.get(DATA_VW_TYPE));
    }

    @Override
    public void setVWType(VWBoatType type) {
        this.entityData.set(DATA_VW_TYPE, type.ordinal());
    }

    @Override
    public Item getDropItem() {
        return this.getVWType().chestBoatItem();
    }

    @Nullable
    @Override
    public ItemEntity spawnAtLocation(ItemLike item) {
        if (item.asItem() == this.getVariant().getPlanks().asItem())
            return super.spawnAtLocation(this.getVWType().planks());
        return super.spawnAtLocation(item);
    }
}
