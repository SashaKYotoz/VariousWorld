package net.sashakyotoz.variousworld.common.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class CrystalicSlimeEntity extends Slime {
    private static final EntityDataAccessor<Integer> TEXTURE_DATA = SynchedEntityData.defineId(CrystalicSlimeEntity.class, EntityDataSerializers.INT);

    public CrystalicSlimeEntity(EntityType<? extends Slime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void onAddedToLevel() {
        super.onAddedToLevel();
        this.entityData.set(TEXTURE_DATA, this.getRandom().nextInt(3));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TEXTURE_DATA, 0);
    }

    public int getTextureInt() {
        return this.entityData.get(TEXTURE_DATA);
    }
}