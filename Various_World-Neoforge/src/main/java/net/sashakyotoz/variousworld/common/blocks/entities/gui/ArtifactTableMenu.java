package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.sashakyotoz.variousworld.common.blocks.entities.ArtifactTableBlockEntity;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArtifactTableMenu extends AbstractContainerMenu {
    public ArtifactTableBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public ArtifactTableMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, extraData == null ? null : inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(3));
    }

    public ArtifactTableMenu(int id, Inventory inv, @Nullable BlockEntity entity, ContainerData data) {
        super(VWMiscRegistries.ARTIFACT_TABLE.get(), id);
        checkContainerSize(inv, 3);
        if (entity == null)
            blockEntity = entitySeeker(inv.player);
        else
            blockEntity = (ArtifactTableBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        IItemHandler handler = this.level.getCapability(ArtifactTableBlockEntity.TABLE_ITEM_HANDLER, this.blockEntity.getBlockPos(), null);
        if (handler != null) {
            this.addSlot(new SlotItemHandler(handler, 0, 56, 48));
            this.addSlot(new SlotItemHandler(handler, 1, 104, 48){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.getItem() instanceof PotionItem;
                }
            });
            this.addSlot(new SlotItemHandler(handler, 2, 80, 48) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.is(Items.DRAGON_BREATH);
                }
            });
        }
        addDataSlots(data);
    }

    public boolean isRefreshing() {
        return data.get(0) > 0;
    }

    public boolean isPowered() {
        return this.blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getValue(BlockStateProperties.TRIGGERED);
    }

    private ArtifactTableBlockEntity entitySeeker(Player player) {
        Level level1 = player.level();
        BlockPos pos = player.getOnPos();
        int radius = 4 + (int) player.getAttributes().getValue(Attributes.BLOCK_INTERACTION_RANGE);
        for (int y = -radius; y < radius; y++) {
            for (int x = -radius; x < radius; x++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos pos1 = pos.offset(x, y, z);
                    if (level1.getBlockEntity(pos1) instanceof ArtifactTableBlockEntity workbenchBlock)
                        return workbenchBlock;
                }
            }
        }
        return null;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 22;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 3;

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
                return ItemStack.EMPTY;
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0)
            sourceSlot.set(ItemStack.EMPTY);
        else
            sourceSlot.setChanged();
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, VWBlocks.ARTIFACT_TABLE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}