package net.sashakyotoz.variousworld.common.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.*;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.sashakyotoz.variousworld.init.VWBiomes;
import org.jetbrains.annotations.Nullable;

public class CrystalicSlimeEntity extends Slime {
    private static final EntityDataAccessor<Integer> TEXTURE_DATA = SynchedEntityData.defineId(CrystalicSlimeEntity.class, EntityDataSerializers.INT);

    public CrystalicSlimeEntity(EntityType<? extends Slime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData) {
        int i = this.getRandom().nextInt(3);
        this.entityData.set(TEXTURE_DATA, i);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TEXTURE_DATA, 0);
    }

    public int getTextureInt() {
        return this.entityData.get(TEXTURE_DATA);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        input.getInt("texture_variant").ifPresent(value -> this.entityData.set(TEXTURE_DATA, value));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("texture_variant", this.entityData.get(TEXTURE_DATA));
    }

    public static boolean checkCrystalicSlimeSpawnRules(EntityType<CrystalicSlimeEntity> slime, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            if (EntitySpawnReason.isSpawner(spawnReason))
                return checkMobSpawnRules(slime, level, spawnReason, pos, random);
            if (level.getBiome(pos).is(VWBiomes.CRYSTALLINE_FOREST) && pos.getY() > 48 && pos.getY() < 72 && random.nextFloat() < 0.75F
                    && random.nextFloat() < level.environmentAttributes().getValue(EnvironmentAttributes.SURFACE_SLIME_SPAWN_CHANCE, pos) && level.getMaxLocalRawBrightness(pos) <= random.nextInt(9))
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