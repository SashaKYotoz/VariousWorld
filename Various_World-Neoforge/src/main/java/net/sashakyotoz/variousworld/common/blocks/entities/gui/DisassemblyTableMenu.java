package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.sashakyotoz.variousworld.common.blocks.entities.DisassemblyTableBlockEntity;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DisassemblyTableMenu extends ITableMenu {
    public DisassemblyTableBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public DisassemblyTableMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, extraData == null ? null : inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(3));
    }

    public DisassemblyTableMenu(int id, Inventory inv, @Nullable BlockEntity entity, ContainerData data) {
        super(VWMiscRegistries.DISASSEMBLY_TABLE.get(), id);
        checkContainerSize(inv, 4);
        if (entity == null)
            blockEntity = findBlockEntity(inv.player, DisassemblyTableBlockEntity.class);
        else
            blockEntity = (DisassemblyTableBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        IItemHandler handler = this.level.getCapability(DisassemblyTableBlockEntity.TABLE_ITEM_HANDLER, this.blockEntity.getBlockPos(), null);
        if (handler != null) {
            this.addSlot(new SlotItemHandler(handler, 0, 80, 22));
            this.addSlot(new SlotItemHandler(handler, 1, 56, 48) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new SlotItemHandler(handler, 2, 104, 48) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });
            this.addSlot(new SlotItemHandler(handler, 3, 80, 48));
        }
        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public float getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        final int progressArrowSize = 17;
        double ratio = (double) progress / maxProgress;
        int scaled = (int) Math.round(ratio * progressArrowSize);
        return Math.min(scaled, progressArrowSize) / 20f;
    }

    public float getFuelProgress() {
        return Mth.clamp(this.data.get(2) / 60f, 0.0F, 1.0F);
    }

    public boolean isFueled() {
        return this.data.get(2) > 0;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, VWBlocks.DISASSEMBLY_TABLE.get());
    }
}