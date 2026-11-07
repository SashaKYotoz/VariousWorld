package net.sashakyotoz.variousworld.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.sashakyotoz.variousworld.platform.IVWPlatform;

import java.util.function.Supplier;

public class FabricPlatform implements IVWPlatform {
    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createBlockMenu(BlockMenuFactory<T> factory) {
        return new ExtendedScreenHandlerType<>(factory::create, BlockPos.STREAM_CODEC);
    }

    @Override
    public void openBlockMenu(ServerPlayer player, MenuProvider provider, BlockPos pos) {
        player.openMenu(new ExtendedScreenHandlerFactory<BlockPos>() {
            @Override
            public BlockPos getScreenOpeningData(ServerPlayer serverPlayer) {
                return pos;
            }

            @Override
            public Component getDisplayName() {
                return provider.getDisplayName();
            }

            @Override
            public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player menuPlayer) {
                return provider.createMenu(containerId, inventory, menuPlayer);
            }
        });
    }

    @Override
    public Item createSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Item.Properties properties) {
        return new SpawnEggItem(type.get(), backgroundColor, highlightColor, properties);
    }

    @Override
    public FlowerPotBlock createFlowerPot(Supplier<? extends Block> content, BlockBehaviour.Properties properties) {
        return new FlowerPotBlock(content.get(), properties);
    }

    @Override
    public BlockBehaviour.Properties lootFrom(BlockBehaviour.Properties properties, Supplier<? extends Block> block) {
        return properties.dropsLike(block.get());
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        return FabricItemGroup.builder();
    }

    @Override
    public int getBurnTime(ItemStack stack) {
        if (stack.isEmpty())
            return 0;
        Integer time = FuelRegistry.INSTANCE.get(stack.getItem());
        return time == null ? 0 : time;
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return stack.getItem().getDefaultAttributeModifiers();
    }
}
