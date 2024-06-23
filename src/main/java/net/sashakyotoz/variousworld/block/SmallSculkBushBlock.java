
package net.sashakyotoz.variousworld.block;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.sashakyotoz.variousworld.init.VariousWorldBlocks;
import net.sashakyotoz.variousworld.init.VariousWorldItems;
import net.sashakyotoz.variousworld.procedures.SculkBushEntityCollidesWithPlantProcedure;

import java.util.Collections;
import java.util.List;

public class SmallSculkBushBlock extends FlowerBlock {
    public SmallSculkBushBlock() {
        super(() -> MobEffects.GLOWING, 100,
                BlockBehaviour.Properties.copy(Blocks.GRASS).sound(SoundType.GRASS).instabreak().randomTicks().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 3).noCollission());
    }

    @Override
    public int getEffectDuration() {
        return 100;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 100;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 60;
    }


    @Override
    public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
        return groundState.is(VariousWorldBlocks.SCULK_GRASS.get()) || groundState.is(Blocks.SCULK);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader reader, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = reader.getBlockState(blockpos);
        return this.mayPlaceOn(groundState, reader, blockpos);
    }

    @Override
    public void randomTick(BlockState blockstate, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(blockstate, level, pos, random);
        if (Math.random() < 0.025 && level.getBlockState(pos.above()).isAir()) {
            BlockState state = VariousWorldBlocks.SCULK_BUSH_WITHOUT_BERRY.get().defaultBlockState();
            level.setBlock(pos, state, 3);
        }
    }

    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        SculkBushEntityCollidesWithPlantProcedure.execute(entity);
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, player, hand, hit);
        if (player.getMainHandItem().is(Items.BONE_MEAL)) {
            if (Math.random() < 0.25) {
                world.setBlock(pos, VariousWorldBlocks.SCULK_BUSH_WITHOUT_BERRY.get().defaultBlockState(), 3);
                ItemStack stack = player.getMainHandItem();
                player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
                player.getInventory().setChanged();
            }
        }
        return InteractionResult.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
        event.register((bs, world, pos, index) -> world != null && pos != null ? Minecraft.getInstance().level.getBiome(pos).value().getSkyColor() : 8562943, VariousWorldBlocks.SMALL_SCULK_BUSH.get());
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        ItemStack itemStack = new ItemStack(VariousWorldItems.SCULKBERRY.get());
        return Collections.singletonList(itemStack);
    }
}
