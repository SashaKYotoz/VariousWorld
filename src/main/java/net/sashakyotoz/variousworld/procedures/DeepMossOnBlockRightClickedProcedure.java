package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class DeepMossOnBlockRightClickedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        double sx;
        double sy;
        double sz;
        sx = -3;
        for (int index0 = 0; index0 < 6; index0++) {
            sy = -3;
            for (int index1 = 0; index1 < 6; index1++) {
                sz = -3;
                for (int index2 = 0; index2 < 6; index2++) {
                    if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.STONE || (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.DEEPSLATE) {
                        if (Math.random() < 0.5) {
                            {
                                BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
                                BlockState _bs = VariousWorldModBlocks.DEEP_MOSS.get().defaultBlockState();
                                world.setBlock(_bp, _bs, 3);
                            }
                            if (Math.random() < 0.125 && !world.getBlockState(BlockPos.containing(x + sx, y + sy + 1, z + sz)).canOcclude()) {
                                {
                                    BlockPos _bp = BlockPos.containing(x + sx, y + sy + 1, z + sz);
                                    BlockState _bs = VariousWorldModBlocks.DEEP_MOSS.get().defaultBlockState();
                                    world.setBlock(_bp, _bs, 3);
                                }
                            }
                        }
                    }
                    sz = sz + 1;
                }
                sy = sy + 1;
            }
            sx = sx + 1;
        }
    }
}
