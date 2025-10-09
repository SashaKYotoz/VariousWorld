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
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.DisassemblyTableMenu;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DisassemblyTableBlockEntity extends BaseContainerBlockEntity {
    public static final BlockCapability<IItemHandler, Void> TABLE_ITEM_HANDLER =
            BlockCapability.createVoid(
                    VariousWorld.createVWLocation("table_item_handler"),
                    IItemHandler.class);
    public final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public NonNullList<ItemStack> items;
    protected final ContainerData data;
    public int progress = 0;
    private int maxProgress = 60;
    private int fuel = 0;
    private int tick = 0;
    public int clientTick = 0;

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
        if (items.isEmpty())
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                items.set(i, itemHandler.getStackInSlot(i));
            }
        return items;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
        itemHandler.setStackInSlot(slot, stack);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        itemHandler.extractItem(slot, amount, false);
        return super.removeItem(slot, amount);
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, nonNullList.get(i));
        }
        items = nonNullList;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        nbt.put("menus", itemHandler.serializeNBT(registries));
        nbt.putInt("table.progress", this.progress);
        ContainerHelper.saveAllItems(nbt, this.items, registries);
        nbt.putInt("table.fuel", this.fuel);
        super.saveAdditional(nbt, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, nbt.getCompound("menus"));
        ContainerHelper.loadAllItems(nbt, this.items, registries);
        for (int i = 0; i < items.size(); i++) {
            itemHandler.setStackInSlot(i, items.get(i));
        }
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
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos pos, BlockState state, DisassemblyTableBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            tick++;
            if (tick % 20 == 0) {
                if (blockEntity.fuel <= 50 && blockEntity.getItem(0).is(VWItems.RECLAIMITE_SHARD)) {
                    blockEntity.removeItem(0, 1);
                    blockEntity.fuel += 10;
                }
                if (hasRecipe(level, blockEntity)) {
                    blockEntity.fuel--;
                    if (blockEntity.fuel > 0)
                        blockEntity.progress++;
                    else if (blockEntity.progress > 0)
                        blockEntity.progress--;
                    setChanged(level, pos, state);
                    if (blockEntity.progress >= blockEntity.maxProgress)
                        craftItem(level, blockEntity);
                } else {
                    blockEntity.resetProgress();
                    setChanged(level, pos, state);
                }
            }
        } else {
            clientTick++;
            if (hasRecipe(level, blockEntity) && clientTick % 20 == 0) {
                level.playLocalSound(pos, progress < 55 ? SoundEvents.WATER_AMBIENT : SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.4f, 0.4f, true);
                level.addParticle(ParticleTypes.WHITE_ASH, pos.getX() + 0.5f, pos.getY() + 0.8f, pos.getZ() + 0.5f, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void craftItem(Level level, DisassemblyTableBlockEntity blockEntity) {
        ItemStack inputStack = blockEntity.itemHandler.getStackInSlot(3);
        Recipe<?>[] recipes = getRecipesFor(inputStack, level);
        Recipe<?> recipe = Arrays.stream(recipes).filter(recipe1 -> recipe1.getIngredients().size() > 1).findFirst().orElse(recipes[0]);
        if (recipe instanceof ShapedRecipe recipe1 && isRecipeValid(recipe1)) {
            blockEntity.removeItem(3, 1);
            blockEntity.setItem(1, recipe1.getIngredients().getFirst().getItems()[0].copy());
            if (Optional.ofNullable(recipe1.getIngredients().getLast().getItems()[0]).isPresent())
                blockEntity.setItem(2, recipe1.getIngredients().getLast().getItems()[0]);
        }
        if (recipe instanceof ShapelessRecipe recipe1 && isRecipeValid(recipe1)) {
            blockEntity.removeItem(3, 1);
            blockEntity.setItem(1, recipe1.getIngredients().getFirst().getItems()[0].copy());
            if (recipe.getIngredients().size() > 1)
                blockEntity.setItem(2, recipe1.getIngredients().getLast().getItems()[0]);
        }
        if (level instanceof ServerLevel serverLevel)
            serverLevel.sendBlockUpdated(blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity.getBlockState(), 3);
        resetProgress();
    }

    private boolean isRecipeValid(Recipe<?> recipe) {
        return recipe.getIngredients().getFirst().getItems().length > 0 && recipe.getIngredients().getLast().getItems().length > 0;
    }

    public boolean hasRecipe(Level level, DisassemblyTableBlockEntity blockEntity) {
        return canInsertItemIntoOutputSlots() && !Arrays.stream(getRecipesFor(blockEntity.itemHandler.getStackInSlot(3), level)).toList().isEmpty();
    }

    private Recipe<?>[] getRecipesFor(ItemStack inputStack, Level level) {
        List<Recipe<?>> recipes = new ArrayList<>();
        if (!inputStack.isEmpty()) {
            for (RecipeHolder<?> recipe : level.getRecipeManager().getRecipes()) {
                if (isRecipeSupported(recipe.value()) &&
                        !recipe.value().isIncomplete() &&
                        recipe.value().canCraftInDimensions(3, 3) &&
                        !recipe.value().getIngredients().isEmpty() &&
                        recipe.value().getIngredients().getFirst().getItems().length > 0 &&
                        matches(inputStack, recipe.value().getResultItem(level.registryAccess()))) {
                    recipes.add(recipe.value());
                }
            }
        }
        return recipes.toArray(new Recipe<?>[0]);
    }

    private static boolean isRecipeSupported(Recipe<?> recipe) {
        return recipe instanceof CraftingRecipe;
    }

    private static boolean matches(ItemStack input, ItemStack output) {
        return input.is(output.getItem()) && input.getCount() >= output.getCount();
    }

    private boolean canInsertItemIntoOutputSlots() {
        return this.itemHandler.getStackInSlot(1).isEmpty() && this.itemHandler.getStackInSlot(2).isEmpty();
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
