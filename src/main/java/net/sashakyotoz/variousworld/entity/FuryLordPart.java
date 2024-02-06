package net.sashakyotoz.variousworld.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.entity.PartEntity;

import javax.annotation.Nullable;

public class FuryLordPart extends PartEntity<FuryLordEntity> {
    public final FuryLordEntity parentMob;
    public final String name;
    private final EntityDimensions size;

    public FuryLordPart(FuryLordEntity lordEntity, String name, float x, float z) {
        super(lordEntity);
        this.size = EntityDimensions.scalable(x, z);
        this.refreshDimensions();
        this.parentMob = lordEntity;
        this.name = name;
    }

    protected void defineSynchedData() {
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
    }

    public boolean isPickable() {
        return true;
    }

    @Nullable
    public ItemStack getPickResult() {
        return this.parentMob.getPickResult();
    }

    public boolean hurt(DamageSource damageSource, float amount) {
        return !this.isInvulnerableTo(damageSource) && this.parentMob.hurt(damageSource, amount);
    }

    public boolean is(Entity entity) {
        return this == entity || this.parentMob == entity;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    public EntityDimensions getDimensions(Pose p_31023_) {
        return this.size;
    }

    public boolean shouldBeSaved() {
        return false;
    }
}
