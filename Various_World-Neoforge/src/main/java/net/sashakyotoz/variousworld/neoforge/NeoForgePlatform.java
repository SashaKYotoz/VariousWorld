package net.sashakyotoz.variousworld.neoforge;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.sashakyotoz.variousworld.platform.IVWPlatform;

import java.util.function.Supplier;

public class NeoForgePlatform implements IVWPlatform {
    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createBlockMenu(BlockMenuFactory<T> factory) {
        return IMenuTypeExtension.create((containerId, inventory, buffer) -> factory.create(containerId, inventory, buffer.readBlockPos()));
    }

    @Override
    public void openBlockMenu(ServerPlayer player, MenuProvider provider, BlockPos pos) {
        player.openMenu(provider, pos);
    }

    @Override
    public Item createSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Item.Properties properties) {
        return new DeferredSpawnEggItem(type, backgroundColor, highlightColor, properties);
    }

    @Override
    public FlowerPotBlock createFlowerPot(Supplier<? extends Block> content, BlockBehaviour.Properties properties) {
        return new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, content, properties);
    }

    @Override
    public BlockBehaviour.Properties lootFrom(BlockBehaviour.Properties properties, Supplier<? extends Block> block) {
        return properties.lootFrom(block);
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        return CreativeModeTab.builder();
    }

    @Override
    public int getBurnTime(ItemStack stack) {
        return stack.getBurnTime(RecipeType.SMELTING);
    }
}
