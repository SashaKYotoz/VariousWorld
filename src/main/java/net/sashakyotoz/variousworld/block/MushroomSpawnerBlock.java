
package net.sashakyotoz.variousworld.block;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomSpawnerBlock extends Block {
    public MushroomSpawnerBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.ANCIENT_DEBRIS).strength(100f, 15f).randomTicks().noLootTable());
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
            return tieredItem.getTier().getLevel() >= 2;
        return false;
    }

    @Override
    public void randomTick(BlockState blockstate, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(blockstate, level, pos, random);
        execute(level, pos);
    }

    private void execute(ServerLevel level, BlockPos pos) {
        if ((level.getBlockState(pos.above())).is(VariousWorldBlocks.MYCENA_FROM_CAVERN_OF_DEEP.get())) {
            level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, pos.getCenter(), Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(),
                    "/effect give @e[type= various_world:crystal_warrior,distance=..15] minecraft:regeneration 5 2");
        } else if ((level.getBlockState(pos.above())).getBlock() == VariousWorldBlocks.CRYSHROOM_PLANT.get()) {
            level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, pos.getCenter(), Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(),
                    "/effect give @e[type= various_world:crystal_warrior,distance=..15] minecraft:invisibility 10 0");
        }
        if (level.getBlockState(pos.above()).isAir() && level.getGameTime() % 60 == 0) {
            if (RandomSource.create().nextBoolean())
                level.setBlock(pos.above(), VariousWorldBlocks.MYCENA_FROM_CAVERN_OF_DEEP.get().defaultBlockState(), 3);
            else
                level.setBlock(pos.above(), VariousWorldBlocks.CRYSHROOM_PLANT.get().defaultBlockState(), 3);
        }
    }
}