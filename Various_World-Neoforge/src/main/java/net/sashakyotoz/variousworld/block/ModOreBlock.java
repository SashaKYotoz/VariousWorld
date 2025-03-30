package net.sashakyotoz.variousworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class ModOreBlock extends ModFacingableBlock {
    private final IntProvider xpRange;
    public ModOreBlock(Properties properties, IntProvider provider) {
        super(properties);
        this.xpRange = provider;
    }
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean b) {
        super.spawnAfterBreak(state, level, pos, stack, b);

    }
    @Override
    public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        return silkTouchLevel == 0 ? this.xpRange.sample(randomSource) : 0;
    }
}
