package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.sashakyotoz.variousworld.common.blocks.entities.GemsmithTableBlockEntity;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GemsmithTableMenu extends ITableMenu {
    public GemsmithTableBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public GemsmithTableMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, extraData == null ? null : inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(3));
    }

    public GemsmithTableMenu(int id, Inventory inv, @Nullable BlockEntity entity, ContainerData data) {
        super(VWMiscRegistries.GEMSMITH_TABLE.get(), id);
        checkContainerSize(inv, 3);
        if (entity == null)
            blockEntity = findBlockEntity(inv.player, GemsmithTableBlockEntity.class);
        else
            blockEntity = (GemsmithTableBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        IItemHandler handler = this.level.getCapability(GemsmithTableBlockEntity.TABLE_ITEM_HANDLER, this.blockEntity.getBlockPos(), null);
        if (handler != null) {
            this.addSlot(new SlotItemHandler(handler, 0, 43, 48) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return super.mayPlace(stack) && stack.getItem() instanceof TieredItem;
                }
            });
            this.addSlot(new SlotItemHandler(handler, 1, 61, 48));
            this.addSlot(new SlotItemHandler(handler, 2, 119, 48) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });
        }
        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 22;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public float getLitProgress() {
        return Mth.clamp(this.data.get(2) / 1000f, 0.0F, 1.0F);
    }

    public boolean isLit() {
        return this.data.get(2) > 0;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, VWBlocks.GEMSMITH_TABLE.get());
    }

}