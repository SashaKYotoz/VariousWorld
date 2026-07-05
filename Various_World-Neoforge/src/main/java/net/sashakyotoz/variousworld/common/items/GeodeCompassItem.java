package net.sashakyotoz.variousworld.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.sashakyotoz.variousworld.common.items.data.GeodeCompassData;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GeodeCompassItem extends Item {
    public GeodeCompassItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.level() instanceof ServerLevel serverLevel && itemStack.has(VWMiscRegistries.GEODE_COMPASS_DATA.get())) {
            BlockPos pos = findNearestCluster(player, serverLevel, 512);
            if (pos == null)
                player.displayClientMessage(Component.translatable("message.various_world.geode_not_found"), true);
            else
                itemStack.set(VWMiscRegistries.GEODE_COMPASS_DATA.get(), new GeodeCompassData(new GlobalPos(serverLevel.getLevel().dimension(), pos)));
        }
        player.getCooldowns().addCooldown(itemStack.getItem(), 100);
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (stack.has(VWMiscRegistries.GEODE_COMPASS_DATA.get())
                && context.level() != null
                && tooltipFlag.hasShiftDown()
                && context.level().getBlockState(stack.get(VWMiscRegistries.GEODE_COMPASS_DATA.get()).globalPos().pos()).getBlock() instanceof AmethystClusterBlock block) {
            tooltipComponents.add(block.getName());
        }
    }

    @Nullable
    public static BlockPos findNearestCluster(Player player, ServerLevel level, int maxChunkRadius) {
        BlockPos origin = player.blockPosition();
        final int originChunkX = origin.getX() >> 4;
        final int originChunkZ = origin.getZ() >> 4;
        final int worldMinY = level.getMinBuildHeight();
        final int worldMaxY = level.getMaxBuildHeight() - 1;
        final int scanMinY = Math.max(worldMinY, -58);
        final int scanMaxY = Math.min(worldMaxY, 30);

        BlockPos bestPos = null;
        long bestDistSq = Long.MAX_VALUE;
        for (int r = 0; r <= maxChunkRadius; r++) {
            if (r == 0) {
                bestPos = scanChunkForCluster(level, originChunkX, originChunkZ, scanMinY, scanMaxY, origin, bestPos, bestDistSq);
                if (bestPos != null) {
                    bestDistSq = squaredDistance(origin, bestPos);
                    if (bestDistSq == 0L) return bestPos;
                }
                continue;
            }
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (Math.abs(dx) != r && Math.abs(dz) != r) continue;
                    int cx = originChunkX + dx;
                    int cz = originChunkZ + dz;
                    ChunkAccess chunk = level.getChunk(cx, cz, ChunkStatus.FULL, false);
                    if (chunk == null) continue;

                    final int baseX = cx << 4;
                    final int baseZ = cz << 4;
                    for (int y = scanMinY; y <= scanMaxY; y++) {
                        for (int lx = 0; lx < 16; lx++) {
                            for (int lz = 0; lz < 16; lz++) {
                                BlockPos pos = new BlockPos(baseX + lx, y, baseZ + lz);
                                BlockState bs = chunk.getBlockState(pos);
                                if (bs.getBlock() instanceof AmethystClusterBlock) {
                                    long distSq = squaredDistance(origin, pos);
                                    if (distSq < bestDistSq) {
                                        bestDistSq = distSq;
                                        bestPos = pos;
                                        if (bestDistSq == 0L) return bestPos;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bestPos;
    }

    private static BlockPos scanChunkForCluster(ServerLevel level, int chunkX, int chunkZ,
                                                int minY, int maxY, BlockPos origin,
                                                BlockPos currentBestPos, long currentBestDistSq) {
        ChunkAccess chunk = level.getChunk(chunkX, chunkZ, ChunkStatus.FULL, false);
        if (chunk == null) return currentBestPos;

        final int baseX = chunkX << 4;
        final int baseZ = chunkZ << 4;
        BlockPos bestPos = currentBestPos;
        long bestDistSq = currentBestDistSq;
        for (int y = minY; y <= maxY; y++) {
            for (int lx = 0; lx < 16; lx++) {
                for (int lz = 0; lz < 16; lz++) {
                    BlockPos pos = new BlockPos(baseX + lx, y, baseZ + lz);
                    BlockState bs = chunk.getBlockState(pos);
                    if (bs.getBlock() instanceof AmethystClusterBlock) {
                        long distSq = squaredDistance(origin, pos);
                        if (distSq < bestDistSq) {
                            bestDistSq = distSq;
                            bestPos = pos;
                            if (bestDistSq == 0L) return bestPos;
                        }
                    }
                }
            }
        }

        return bestPos;
    }

    private static long squaredDistance(BlockPos a, BlockPos b) {
        long dx = (long) a.getX() - b.getX();
        long dy = (long) a.getY() - b.getY();
        long dz = (long) a.getZ() - b.getZ();
        return dx * dx + dy * dy + dz * dz;
    }
}