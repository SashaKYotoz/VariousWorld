package net.sashakyotoz.variousworld.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicReference;

public class DisenchantTableUpdateTickProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        BlockPos pos = BlockPos.containing(x, y, z);
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity != null) {
            ItemStack slot0Stack = getItemStack(world, pos, 0);
            ItemStack slot1Stack = getItemStack(world, pos, 1);
            ItemStack slot2Stack = getItemStack(world, pos, 2);
            if (slot0Stack.isEnchanted() &&
                    slot1Stack.getItem() == Items.LAPIS_LAZULI && slot1Stack.getCount() >= 3 &&
                    slot2Stack.getItem() == Items.BOOK) {
                consumeItems(blockEntity, 1, 3);
                consumeItems(blockEntity, 0, 1);
                ItemStack enchantedBook = EnchantmentHelper.enchantItem(RandomSource.create(), new ItemStack(Items.BOOK), 40 * slot0Stack.getEnchantmentValue() /10, true);
                enchantedBook.setCount(slot2Stack.getCount());
                setItemStack(blockEntity, 2, enchantedBook);
            }
        }
    }

    private static ItemStack getItemStack(LevelAccessor accessor, BlockPos pos, int slotId) {
        BlockEntity blockEntity = accessor.getBlockEntity(pos);
        if (blockEntity != null) {
            AtomicReference<ItemStack> itemStackRef = new AtomicReference<>(ItemStack.EMPTY);
            blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> itemStackRef.set(capability.getStackInSlot(slotId).copy()));
            return itemStackRef.get();
        }
        return ItemStack.EMPTY;
    }

    private static void consumeItems(BlockEntity blockEntity, int slotId, int amount) {
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
            if (capability instanceof IItemHandlerModifiable) {
                ItemStack stack = capability.getStackInSlot(slotId).copy();
                stack.shrink(amount);
                ((IItemHandlerModifiable) capability).setStackInSlot(slotId, stack);
            }
        });
    }

    private static void setItemStack(BlockEntity blockEntity, int slotId, ItemStack stack) {
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
            if (capability instanceof IItemHandlerModifiable) {
                ((IItemHandlerModifiable) capability).setStackInSlot(slotId, stack);
            }
        });
    }
}