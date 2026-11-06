package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
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
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.common.blocks.entities.GemsmithFurnaceBlockEntity;
import net.sashakyotoz.variousworld.common.blocks.entities.GemsmithTableBlockEntity;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GemsmithTableMenu extends ITableMenu {
    public static final int TOOL_SLOT = 36;
    public static final int OUTPUT_SLOT = 38;
    public GemsmithTableBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    private final SimpleContainer preview = new SimpleContainer(1);

    public GemsmithTableMenu(int id, Inventory inv, BlockPos pos) {
        this(id, inv, inv.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public GemsmithTableMenu(int id, Inventory inv, @Nullable BlockEntity entity, ContainerData data) {
        super(VWMiscRegistries.GEMSMITH_TABLE.get(), id);
        if (entity instanceof GemsmithTableBlockEntity table)
            blockEntity = table;
        else
            blockEntity = findBlockEntity(inv.player, GemsmithTableBlockEntity.class);
        checkContainerSize(blockEntity, 3);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addSlot(new Slot(blockEntity, 0, 43, 48) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return super.mayPlace(stack) && stack.getItem() instanceof TieredItem;
            }
        });
        this.addSlot(new Slot(blockEntity, 1, 61, 48));
        this.addSlot(new Slot(blockEntity, 2, 119, 48) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(this.preview, 0, 0, 0) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(@NotNull Player player) {
                return false;
            }

            @Override
            public boolean isActive() {
                return false;
            }
        });
        addDataSlots(data);
    }

    @Override
    public void broadcastChanges() {
        if (!this.level.isClientSide() && this.blockEntity != null)
            this.preview.setItem(0, computePreview());
        super.broadcastChanges();
    }

    private ItemStack computePreview() {
        ItemStack tool = this.blockEntity.getItem(0);
        ItemStack gem = this.blockEntity.getItem(1);
        if (tool.isEmpty() || gem.isEmpty() || !this.blockEntity.hasRecipe())
            return ItemStack.EMPTY;
        ItemStack result = GemsmithingHelper.applyGem(tool, gem);
        return result == tool ? ItemStack.EMPTY : result;
    }

    public ItemStack getPreview() {
        return this.preview.getItem(0);
    }

    public ItemStack getTool() {
        return this.slots.get(TOOL_SLOT).getItem();
    }

    public boolean isOutputEmpty() {
        return this.slots.get(OUTPUT_SLOT).getItem().isEmpty();
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
        return Mth.clamp(this.data.get(2) / (float) GemsmithFurnaceBlockEntity.MAX_FUEL, 0.0F, 1.0F);
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