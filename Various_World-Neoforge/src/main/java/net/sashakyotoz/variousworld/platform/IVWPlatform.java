package net.sashakyotoz.variousworld.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public interface IVWPlatform {
    <T extends AbstractContainerMenu> MenuType<T> createBlockMenu(BlockMenuFactory<T> factory);

    void openBlockMenu(ServerPlayer player, MenuProvider provider, BlockPos pos);

    Item createSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Item.Properties properties);

    FlowerPotBlock createFlowerPot(Supplier<? extends Block> content, BlockBehaviour.Properties properties);

    BlockBehaviour.Properties lootFrom(BlockBehaviour.Properties properties, Supplier<? extends Block> block);

    CreativeModeTab.Builder creativeTabBuilder();

    int getBurnTime(ItemStack stack);

    ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack);

    @FunctionalInterface
    interface BlockMenuFactory<T extends AbstractContainerMenu> {
        T create(int containerId, Inventory inventory, BlockPos pos);
    }
}
