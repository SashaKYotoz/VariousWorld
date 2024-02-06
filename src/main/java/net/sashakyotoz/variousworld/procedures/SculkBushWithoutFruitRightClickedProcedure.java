package net.sashakyotoz.variousworld.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VariousWorldModBlocks;

public class SculkBushWithoutFruitRightClickedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.BONE_MEAL) {
            if (Math.random() < 0.5) {
                BlockPos _bp = BlockPos.containing(x, y, z);
                BlockState _bs = VariousWorldModBlocks.UNDERGROUND_SCULK_FRUIT_BUSH.get().defaultBlockState();
                world.setBlock(_bp, _bs, 3);
            }
        }
    }
}
