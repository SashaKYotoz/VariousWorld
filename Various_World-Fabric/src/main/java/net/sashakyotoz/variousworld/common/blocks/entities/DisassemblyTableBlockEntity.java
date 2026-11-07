package net.sashakyotoz.variousworld.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.DisassemblyTableMenu;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DisassemblyTableBlockEntity extends BaseContainerBlockEntity {
    public NonNullList<ItemStack> items;
    protected final ContainerData data;
    public int progress = 0;
    private int maxProgress = 30;
    private int fuel = 0;
    private int tick = 0;
    public int clientTick = 0;
    @Nullable
    private Item cachedInput;
    @Nullable
    private Disassembly cachedDisassembly;

    private record Disassembly(int inputCount, ItemStack primary, ItemStack secondary) {
    }

    public DisassemblyTableBlockEntity(BlockPos pos, BlockState blockState) {
        super(VWBlocks.DISASSEMBLY_TABLE_BE.get(), pos, blockState);
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DisassemblyTableBlockEntity.this.progress;
                    case 1 -> DisassemblyTableBlockEntity.this.maxProgress;
                    case 2 -> DisassemblyTableBlockEntity.this.fuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DisassemblyTableBlockEntity.this.progress = value;
                    case 1 -> DisassemblyTableBlockEntity.this.maxProgress = value;
                    case 2 -> DisassemblyTableBlockEntity.this.fuel = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.various_world.disassembly_table");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        items = nonNullList;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        nbt.putInt("table.progress", this.progress);
        ContainerHelper.saveAllItems(nbt, this.items, registries);
        nbt.putInt("table.fuel", this.fuel);
        super.saveAdditional(nbt, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt.contains("menus") ? nbt.getCompound("menus") : nbt, this.items, registries);
        progress = nbt.getInt("table.progress");
        fuel = nbt.getInt("table.fuel");
        super.loadAdditional(nbt, registries);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new DisassemblyTableMenu(i, inventory, this, this.data);
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new DisassemblyTableMenu(i, inventory, this, this.data);
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this);
    }

    public void tick(Level level, BlockPos pos, BlockState state, DisassemblyTableBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            tick++;
            if (tick % 20 != 0)
                return;
            if (blockEntity.fuel <= 50 && blockEntity.getItem(0).is(VWItems.RECLAIMITE_SHARD.get())) {
                blockEntity.removeItem(0, 1);
                blockEntity.fuel += 10;
                setChanged(level, pos, state);
            }
            Disassembly disassembly = blockEntity.resolve(level);
            if (disassembly != null) {
                if (blockEntity.fuel > 0) {
                    blockEntity.fuel--;
                    blockEntity.progress++;
                } else if (blockEntity.progress > 0)
                    blockEntity.progress--;
                setChanged(level, pos, state);
                if (blockEntity.progress >= blockEntity.maxProgress)
                    blockEntity.craftItem(level, disassembly);
            } else if (blockEntity.progress != 0) {
                blockEntity.resetProgress();
                setChanged(level, pos, state);
            }
        } else {
            clientTick++;
            if (clientTick % 20 == 0 && hasRecipe(level, blockEntity)) {
                level.playLocalSound(pos, progress < 55 ? SoundEvents.WATER_AMBIENT : SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.4f, 0.4f, true);
                level.addParticle(ParticleTypes.WHITE_ASH, pos.getX() + 0.5f, pos.getY() + 0.8f, pos.getZ() + 0.5f, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void craftItem(Level level, Disassembly disassembly) {
        this.removeItem(3, disassembly.inputCount());
        this.setItem(1, disassembly.primary().copy());
        if (!disassembly.secondary().isEmpty())
            this.setItem(2, disassembly.secondary().copy());
        if (level instanceof ServerLevel serverLevel)
            serverLevel.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        this.resetProgress();
    }

    public boolean hasRecipe(Level level, DisassemblyTableBlockEntity blockEntity) {
        return blockEntity.resolve(level) != null;
    }

    @Nullable
    private Disassembly resolve(Level level) {
        ItemStack input = this.getItem(3);
        if (input.isEmpty() || input.isDamaged() || !canInsertItemIntoOutputSlots())
            return null;
        if (input.getItem() != this.cachedInput) {
            this.cachedInput = input.getItem();
            this.cachedDisassembly = findDisassembly(level, input.getItem());
        }
        if (this.cachedDisassembly == null || input.getCount() < this.cachedDisassembly.inputCount())
            return null;
        return this.cachedDisassembly;
    }

    @Nullable
    private static Disassembly findDisassembly(Level level, Item item) {
        Disassembly fallback = null;
        for (RecipeHolder<?> holder : level.getRecipeManager().getRecipes()) {
            Recipe<?> recipe = holder.value();
            if (!(recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe) || recipe.isIncomplete() || !recipe.canCraftInDimensions(3, 3))
                continue;
            ItemStack result = recipe.getResultItem(level.registryAccess());
            if (result.isEmpty() || !result.is(item))
                continue;
            List<ItemStack> parts = new ArrayList<>();
            for (Ingredient ingredient : recipe.getIngredients()) {
                ItemStack[] options = ingredient.getItems();
                if (options.length > 0 && !options[0].isEmpty())
                    parts.add(options[0]);
            }
            if (parts.isEmpty())
                continue;
            Disassembly disassembly = new Disassembly(result.getCount(), parts.getFirst().copyWithCount(1),
                    parts.size() > 1 ? parts.getLast().copyWithCount(1) : ItemStack.EMPTY);
            if (parts.size() > 1)
                return disassembly;
            if (fallback == null)
                fallback = disassembly;
        }
        return fallback;
    }

    private boolean canInsertItemIntoOutputSlots() {
        return this.getItem(1).isEmpty() && this.getItem(2).isEmpty();
    }

    private void resetProgress() {
        this.progress = 0;
    }
}