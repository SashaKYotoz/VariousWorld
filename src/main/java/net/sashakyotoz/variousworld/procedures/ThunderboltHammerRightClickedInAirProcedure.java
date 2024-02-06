package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class ThunderboltHammerRightClickedInAirProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        if (world instanceof ServerLevel level) {
            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(level);
            entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -4, 4), y, z + Mth.nextDouble(RandomSource.create(), -4, 4))));
            level.addFreshEntity(entityToSpawn);
        }
        if (entity instanceof Player player)
            player.getCooldowns().addCooldown(itemstack.getItem(), 30);
        if (entity instanceof LivingEntity _entity)
            _entity.swing(InteractionHand.MAIN_HAND, true);
        if (entity instanceof LivingEntity _entity)
            _entity.swing(InteractionHand.OFF_HAND, true);
        if (Math.random() < 0.25) {
            if (itemstack.hurt(1, RandomSource.create(), null)) {
                itemstack.shrink(1);
                itemstack.setDamageValue(0);
            }
        }
    }
}
