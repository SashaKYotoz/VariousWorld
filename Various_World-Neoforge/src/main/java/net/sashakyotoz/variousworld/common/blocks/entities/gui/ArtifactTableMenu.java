package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
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
@SuppressWarnings("removal")
public class ArtifactTableMenu extends ITableMenu {
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
            blockEntity = findBlockEntity(inv.player, ArtifactTableBlockEntity.class);
        else
            blockEntity = (ArtifactTableBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        IItemHandler handler = this.level.getCapability(ArtifactTableBlockEntity.TABLE_ITEM_HANDLER, this.blockEntity.getBlockPos(), null);
        if (handler != null) {
            this.addSlot(new SlotItemHandler(handler, 0, 56, 48));
            this.addSlot(new SlotItemHandler(handler, 1, 104, 48) {
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

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 22;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, VWBlocks.ARTIFACT_TABLE.get());
    }
}