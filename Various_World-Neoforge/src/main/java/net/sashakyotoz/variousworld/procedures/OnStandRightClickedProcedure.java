package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.TierSortingRegistry;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VWEntities;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.entity.SculkNecromancerSkeletonEntity;
import net.sashakyotoz.variousworld.entity.FuryLordEntity;
import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;

public class OnStandRightClickedProcedure {
    public static void execute(LevelAccessor level, double x, double y, double z, Player player) {
        if (player == null || level == null) return;

        BlockPos blockPos = BlockPos.containing(x, y, z);
        Block blockAtPos = level.getBlockState(blockPos).getBlock();

        if (blockAtPos == VWBlocks.QUARTZ_STAND.get()) {
            handleQuartzStand(level, blockPos, player, x, y, z);
        } else if (blockAtPos == VWBlocks.AIR_STAND.get()) {
            handleAirStand(level, blockPos, player, x, y, z);
        } else if (blockAtPos == VWBlocks.LORD_FURY_STAND.get()) {
            handleLordFuryStand(level, blockPos, player, x, y, z);
        } else if (blockAtPos == VWBlocks.SCULK_NECROMANCER_BLOCK_SPAWNER.get()) {
            handleSculkNecromancerBlockSpawner(level, blockPos, x, y, z);
        }
    }

    private static void handleQuartzStand(LevelAccessor world, BlockPos blockPos, Player player, double x, double y, double z) {
        ItemStack crystalicStrengthStack = new ItemStack(VWItems.CRYSTALIC_STRENGTH.get());
        if (player.getInventory().contains(crystalicStrengthStack)) {
            player.getInventory().clearOrCountMatchingItems(
                    p -> crystalicStrengthStack.getItem() == p.getItem(),
                    1,
                    player.inventoryMenu.getCraftSlots()
            );
            if (world instanceof ServerLevel level) {
                spawnEntity(level, new CrystalWarriorEntity(VWEntities.CRYSTAL_WARRIOR.get(), level), x, y, z);
            }
            world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
            clearSurroundingBlocks(world, blockPos, x, y, z, -3, 6, -1, 6, -3, 6);
        }
    }

    private static void handleAirStand(LevelAccessor world, BlockPos blockPos, Player player, double x, double y, double z) {
        ItemStack crystalGemStack = new ItemStack(VWItems.CRYSTAL_GEM.get());
        if (player.getMainHandItem().getItem() == VWItems.CRYSTAL_GEM.get()) {
            player.getInventory().clearOrCountMatchingItems(
                    p -> crystalGemStack.getItem() == p.getItem(),
                    1,
                    player.inventoryMenu.getCraftSlots()
            );
            world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
            if (world instanceof ServerLevel level) {
                spawnEntity(level, new DarkSpiritEntity(VWEntities.DARK_SPIRIT.get(), level), x, y, z);
            }
        }
    }

    private static void handleLordFuryStand(LevelAccessor world, BlockPos blockPos, Player player, double x, double y, double z) {
        ItemStack lordFuryShardStack = new ItemStack(VWItems.LORD_FURY_SHARD.get());
        if (player.getMainHandItem().is(VWItems.LORD_FURY_SHARD.get()) && player.getMainHandItem().getCount() >= 3) {
            world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
            world.addParticle(ParticleTypes.CRIT, x, y, z, 0, 1, 0);
            player.getInventory().clearOrCountMatchingItems(
                    p -> lordFuryShardStack.getItem() == p.getItem(),
                    3,
                    player.inventoryMenu.getCraftSlots()
            );
            if (world instanceof ServerLevel level) {
                spawnEntity(level, new FuryLordEntity(VWEntities.FURY_LORD.get(), level), x, y + 3, z);
            }
        }
    }

    private static void handleSculkNecromancerBlockSpawner(LevelAccessor world, BlockPos blockPos, double x, double y, double z) {
        world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
        if (world instanceof ServerLevel level) {
            spawnEntity(level, new SculkNecromancerSkeletonEntity(VWEntities.SCULK_NECROMANCER_SKELETON.get(), level), x, y, z);
        }
    }

    private static void spawnEntity(ServerLevel level, Mob entity, double x, double y, double z) {
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360F, 0);
        entity.finalizeSpawn(level, level.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
        level.addFreshEntity(entity);
    }

    private static void clearSurroundingBlocks(LevelAccessor world, BlockPos origin, double x, double y, double z, int startX, int lengthX, int startY, int lengthY, int startZ, int lengthZ) {
        for (int sx = startX; sx < startX + lengthX; sx++) {
            for (int sy = startY; sy < startY + lengthY; sy++) {
                for (int sz = startZ; sz < startZ + lengthZ; sz++) {
                    BlockPos targetPos = BlockPos.containing(x + sx, y + sy, z + sz);
                    Block block = world.getBlockState(targetPos).getBlock();
                    if (getHarvestLevel(world.getBlockState(targetPos)) <= 3 && block != VWBlocks.ROSE_QUARTZ_STAIRS.get() && block != VWBlocks.ROSE_QUARTZ.get()) {
                        world.setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    private static int getHarvestLevel(BlockState blockState) {
        return TierSortingRegistry.getSortedTiers().stream()
                .filter(t -> t.getTag() != null && blockState.is(t.getTag()))
                .map(Tier::getLevel)
                .findFirst()
                .orElse(0);
    }
}
