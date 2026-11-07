package net.sashakyotoz.variousworld.common;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.init.VWItems;

public final class VWCauldronInteractions {
    private VWCauldronInteractions() {
    }

    public static void register() {
        CauldronInteraction.WATER.map().put(VWItems.CRYSTALLINE_SLIME_BALL.get(), VWCauldronInteractions::washCrystallineSlimeBall);
    }

    private static ItemInteractionResult washCrystallineSlimeBall(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        if (!level.isClientSide()) {
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.SLIME_BALL)));
            player.awardStat(Stats.USE_CAULDRON);
            LayeredCauldronBlock.lowerFillLevel(state, level, pos);
            level.playSound(null, pos, SoundEvents.SLIME_SQUISH_SMALL, SoundSource.BLOCKS, 1.0F, 1.2F);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}