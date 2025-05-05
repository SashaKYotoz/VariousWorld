package net.sashakyotoz.variousworld.common.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.sashakyotoz.variousworld.init.VWBiomes;

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

    public static boolean checkCrystalicSlimeSpawnRules(EntityType<CrystalicSlimeEntity> slime, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            if (EntitySpawnReason.isSpawner(spawnReason))
                return checkMobSpawnRules(slime, level, spawnReason, pos, random);
            if (level.getBiome(pos).is(VWBiomes.CRYSTALLINE_FOREST) && pos.getY() > 48 && pos.getY() < 72 && random.nextFloat() < 0.75F
                    && random.nextFloat() < level.getMoonBrightness() && level.getMaxLocalRawBrightness(pos) <= random.nextInt(9))
                return checkMobSpawnRules(slime, level, spawnReason, pos, random);

            if (!(level instanceof WorldGenLevel))
                return false;

            ChunkPos chunkpos = new ChunkPos(pos);
            boolean flag = WorldgenRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, ((WorldGenLevel) level).getSeed(), 987234911L).nextInt(9) == 0;
            if (random.nextInt(10) == 0 && flag && pos.getY() < 40)
                return checkMobSpawnRules(slime, level, spawnReason, pos, random);
        }

        return false;
    }
}