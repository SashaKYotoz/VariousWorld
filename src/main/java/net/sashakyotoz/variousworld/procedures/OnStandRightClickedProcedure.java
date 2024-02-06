package net.sashakyotoz.variousworld.procedures;

import net.minecraftforge.common.TierSortingRegistry;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.init.VariousWorldModEntities;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;
import net.sashakyotoz.variousworld.entity.SculkNecromancerSkeletonEntity;
import net.sashakyotoz.variousworld.entity.FuryLordEntity;
import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;

public class OnStandRightClickedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Player player) {
        if (player == null)
            return;
        double sx, sy, sz;
        if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldModBlocks.QUARTZ_STAND.get()) {
            if (player.getInventory().contains(new ItemStack(VariousWorldModItems.CRYSTALIC_STRENGTH.get()))) {
                ItemStack stack = new ItemStack(VariousWorldModItems.CRYSTALIC_STRENGTH.get());
                player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
                if (world instanceof ServerLevel level) {
                    Entity entityToSpawn = new CrystalWarriorEntity(VariousWorldModEntities.CRYSTAL_WARRIOR.get(), level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    if (entityToSpawn instanceof Mob _mobToSpawn)
                        _mobToSpawn.finalizeSpawn(level, level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    level.addFreshEntity(entityToSpawn);
                }
                world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
                sx = -3;
                for (int index0 = 0; index0 < 6; index0++) {
                    sy = -2;
                    for (int index1 = 0; index1 < 6; index1++) {
                        sz = -3;
                        for (int index2 = 0; index2 < 6; index2++) {
                            if (new Object() {
                                public int getHarvestLevel(BlockState _bs) {
                                    return TierSortingRegistry.getSortedTiers().stream().filter(t -> t.getTag() != null && _bs.is(t.getTag())).map(Tier::getLevel).findFirst().orElse(0);
                                }
                            }.getHarvestLevel(world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))) <= 3 && !((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.ROSE_QUARTZ_STAIRS.get()
                                    || (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == VariousWorldModBlocks.ROSE_QUARTZ.get())) {
                                world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), Blocks.AIR.defaultBlockState(), 3);
                            }
                            sz = sz + 1;
                        }
                        sy = sy + 1;
                    }
                    sx = sx + 1;
                }
            }
        } else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldModBlocks.AIR_STAND.get()) {
            if (player.getMainHandItem().getItem() == VariousWorldModItems.CRYSTAL_GEM.get()) {
                ItemStack stack = new ItemStack(VariousWorldModItems.CRYSTAL_GEM.get());
                player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
                world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
                if (world instanceof ServerLevel level) {
                    Entity entityToSpawn = new DarkSpiritEntity(VariousWorldModEntities.DARK_SPIRIT.get(), level);
                    entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                    if (entityToSpawn instanceof Mob mobToSpawn)
                        mobToSpawn.finalizeSpawn(level, level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    level.addFreshEntity(entityToSpawn);
                }
            }
        } else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldModBlocks.LORD_FURY_STAND.get()) {
            if (player.getMainHandItem().getItem() == VariousWorldModItems.LORD_FURY_SHARD.get()
                    && player.getMainHandItem().getCount() >= 3) {
                world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
                world.addParticle(ParticleTypes.CRIT, x, y, z, 0, 1, 0);
                ItemStack stack = new ItemStack(VariousWorldModItems.LORD_FURY_SHARD.get());
                player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 3, player.inventoryMenu.getCraftSlots());
                if (world instanceof ServerLevel level) {
                    Entity entityToSpawn = new FuryLordEntity(VariousWorldModEntities.FURY_LORD.get(), level);
                    entityToSpawn.moveTo(x, (y + 3), z, world.getRandom().nextFloat() * 360F, 0);
                    if (entityToSpawn instanceof Mob _mobToSpawn)
                        _mobToSpawn.finalizeSpawn(level, level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    level.addFreshEntity(entityToSpawn);
                }
            }
        } else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldModBlocks.SCULK_NECROMANCER_BLOCK_SPAWNER.get()) {
            world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
            if (world instanceof ServerLevel level) {
                Entity entityToSpawn = new SculkNecromancerSkeletonEntity(VariousWorldModEntities.SCULK_NECROMANCER_SKELETON.get(), level);
                entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                if (entityToSpawn instanceof Mob groupData)
                    groupData.finalizeSpawn(level, level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                level.addFreshEntity(entityToSpawn);
            }
        }
    }
}
