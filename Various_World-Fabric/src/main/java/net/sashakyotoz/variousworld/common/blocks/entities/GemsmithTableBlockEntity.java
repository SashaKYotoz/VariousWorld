package net.sashakyotoz.variousworld.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableMenu;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithRecipeInput;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;
import net.sashakyotoz.variousworld.init.VWBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GemsmithTableBlockEntity extends BaseContainerBlockEntity {

    public NonNullList<ItemStack> items;
    protected final ContainerData data;
    public int progress = 0;
    private int maxProgress = 300;
    private int fuel = 0;


    public GemsmithTableBlockEntity(BlockPos pos, BlockState state) {
        super(VWBlocks.GEMSMITH_TABLE_BE.get(), pos, state);
        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemsmithTableBlockEntity.this.progress;
                    case 1 -> GemsmithTableBlockEntity.this.maxProgress;
                    case 2 -> GemsmithTableBlockEntity.this.fuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemsmithTableBlockEntity.this.progress = value;
                    case 1 -> GemsmithTableBlockEntity.this.maxProgress = value;
                    case 2 -> GemsmithTableBlockEntity.this.fuel = value;
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
        return Component.translatable("block.various_world.gemsmith_table");
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
        super.saveAdditional(nbt, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt.contains("menus") ? nbt.getCompound("menus") : nbt, this.items, registries);
        progress = nbt.getInt("table.progress");
        super.loadAdditional(nbt, registries);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new GemsmithTableMenu(i, inventory, this, this.data);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new GemsmithTableMenu(i, inventory, this, this.data);
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this);
    }

    public void tick(Level level, BlockPos pos, BlockState state, GemsmithTableBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            GemsmithFurnaceBlockEntity furnace = getFurnace(level, pos);
            blockEntity.fuel = furnace != null ? furnace.fuel : 0;
            if (hasRecipe() && furnace != null) {
                if (blockEntity.fuel > 0)
                    blockEntity.progress++;
                else if (blockEntity.progress > 0)
                    blockEntity.progress--;
                setChanged(level, pos, state);
                if (blockEntity.progress >= blockEntity.maxProgress)
                    craftItem(blockEntity);
            } else if (blockEntity.progress != 0) {
                blockEntity.resetProgress();
                setChanged(level, pos, state);
            }
        }
    }

    private void craftItem(GemsmithTableBlockEntity pEntity) {
        Optional<RecipeHolder<GemsmithTransformRecipe>> matchedRecipe = getCurrentRecipe();
        if (matchedRecipe.isPresent()) {
            ItemStack result = GemsmithingHelper.applyGem(pEntity.getItem(0), pEntity.getItem(1));
            for (int i = 0; i < 2; i++)
                pEntity.removeItem(i, 1);
            pEntity.setItem(2, result);
            pEntity.resetProgress();
        }
    }

    private GemsmithFurnaceBlockEntity getFurnace(Level level, BlockPos pos) {
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                if (level.getBlockEntity(pos.offset(x, 0, z)) instanceof GemsmithFurnaceBlockEntity entity
                        && !(level.getBlockEntity(pos.offset(x, 0, z)
                        .relative(level.getBlockState(pos.offset(x, 0, z)).getValue(BlockStateProperties.FACING))) instanceof GemsmithFurnaceBlockEntity))
                    return entity;
            }
        }
        return null;
    }

    public boolean hasRecipe() {
        Optional<RecipeHolder<GemsmithTransformRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty())
            return false;
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<RecipeHolder<GemsmithTransformRecipe>> getCurrentRecipe() {
        GemsmithRecipeInput inventory = new GemsmithRecipeInput(this.getItem(0), this.getItem(1));
        return this.getLevel().getRecipeManager().getRecipeFor(GemsmithTransformRecipe.Type.INSTANCE, inventory, this.getLevel());
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getItem(2).isEmpty() || this.getItem(2).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.getItem(2).getCount() + count <= this.getItem(2).getMaxStackSize();
    }

    @Override
    public int getContainerSize() {
        return 3;
    }
}