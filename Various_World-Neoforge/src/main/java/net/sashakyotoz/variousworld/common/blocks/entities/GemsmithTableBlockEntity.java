package net.sashakyotoz.variousworld.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.GemsmithTableMenu;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithRecipeInput;
import net.sashakyotoz.variousworld.common.blocks.entities.recipes.GemsmithTransformRecipe;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.common.items.data.CrystalData;
import net.sashakyotoz.variousworld.common.items.data.SupplyCrystalData;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GemsmithTableBlockEntity extends BaseContainerBlockEntity {

    public static final BlockCapability<IItemHandler, Void> TABLE_ITEM_HANDLER =
            BlockCapability.createVoid(
                    VariousWorld.createVWLocation("table_item_handler"),
                    IItemHandler.class);
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
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
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos pos, BlockState state, GemsmithTableBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            GemsmithFurnaceBlockEntity furnace = getFurnace(level, pos);
            if (!hasRecipe() && furnace != null && blockEntity.fuel > 0 && furnace.fuel == 0)
                blockEntity.fuel = 0;
            if (hasRecipe() && furnace != null) {
                blockEntity.fuel = furnace.fuel;
                if (blockEntity.fuel > 0)
                    blockEntity.progress++;
                else if (blockEntity.progress > 0)
                    blockEntity.progress--;
                setChanged(level, pos, state);
                if (blockEntity.progress >= blockEntity.maxProgress)
                    craftItem(blockEntity);
            } else {
                blockEntity.resetProgress();
                setChanged(level, pos, state);
            }
        }
    }

    private void craftItem(GemsmithTableBlockEntity pEntity) {
        Optional<RecipeHolder<GemsmithTransformRecipe>> matchedRecipe = getCurrentRecipe();
        if (matchedRecipe.isPresent()) {
            ItemStack result = releaseResultStack(matchedRecipe.get(), pEntity);
            for (int i = 0; i < 2; i++)
                pEntity.removeItem(i, 1);
            pEntity.setItem(2, result);
            pEntity.resetProgress();
        }
    }

    private ItemStack releaseResultStack(RecipeHolder<GemsmithTransformRecipe> recipe, GemsmithTableBlockEntity blockEntity) {
        List<ModConfigController.GemsmithingSetting> setting = ModConfigController.CRYSTALING_CONFIG_VALUES;
        if (recipe.value().tool.getItems()[0].getItem() instanceof TieredItem && setting != null) {
            ItemStack itemstack = recipe.value().tool.getItems()[0].copy();
            ItemStack supplyGemStack = VWItems.SUPPLY_CRYSTAL.toStack();
            String toolName;
            for (ModConfigController.GemsmithingSetting crystalingSetting : setting) {
                if (recipe.value().gem.getItems()[0].is(crystalingSetting.item().build())) {
                    toolName = OnActionsTrigger.getToolName(itemstack);
                    supplyGemStack.set(VWMiscRegistries.SUPPLY_CRYSTAL_DATA.get(), new SupplyCrystalData(
                            recipe.value().gem.getItems()[0],
                            toolName
                    ));
                    itemstack.set(VWMiscRegistries.CRYSTAL_DATA.get(), new CrystalData(
                            supplyGemStack,
                            crystalingSetting.durability()
                    ));
                    var attributeKey = BuiltInRegistries.ATTRIBUTE.getKey(crystalingSetting.attribute());
                    if (!itemstack.has(DataComponents.ATTRIBUTE_MODIFIERS) || attributeKey == null)
                        return itemstack;
                    List<ItemAttributeModifiers.Entry> modifiers = new ArrayList<>(
                            itemstack.get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers()
                    );
                    var registry = blockEntity.getLevel().registryAccess().lookupOrThrow(Registries.ATTRIBUTE);
                    var resourceKey = ResourceKey.create(Registries.ATTRIBUTE, attributeKey);
                    Optional<Holder.Reference<Attribute>> attributeHolderOpt = registry.get(resourceKey);
                    if (attributeHolderOpt.isEmpty())
                        return itemstack;
                    Holder.Reference<Attribute> attributeHolder = attributeHolderOpt.get();
                    if (modifiers.stream().anyMatch(entry -> entry.attribute().is(attributeKey))) {
                        modifiers = modifiers.stream().map(entry -> {
                            if (entry.attribute().is(attributeKey)) {
                                var updatedModifier = new AttributeModifier(
                                        entry.modifier().id(),
                                        entry.modifier().amount() + crystalingSetting.modify_value(),
                                        entry.modifier().operation()
                                );
                                return new ItemAttributeModifiers.Entry(entry.attribute(), updatedModifier, entry.slot());
                            }
                            return entry;
                        }).collect(Collectors.toList());
                    } else {
                        var newModifier = new AttributeModifier(
                                VariousWorld.createVWLocation("tool.modify_attribute" + crystalingSetting.prefix()),
                                crystalingSetting.modify_value(),
                                AttributeModifier.Operation.ADD_VALUE
                        );
                        modifiers.add(new ItemAttributeModifiers.Entry(attributeHolder, newModifier, EquipmentSlotGroup.MAINHAND));
                    }

                    itemstack.set(
                            DataComponents.ATTRIBUTE_MODIFIERS,
                            new ItemAttributeModifiers(modifiers, itemstack.get(DataComponents.ATTRIBUTE_MODIFIERS).showInTooltip())
                    );

                }
            }
            return itemstack;
        } else return recipe.value().tool.getItems()[0];
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
        ArrayList<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            stacks.add(i, this.itemHandler.getStackInSlot(i));
        }
        GemsmithRecipeInput inventory = new GemsmithRecipeInput(stacks.get(0), stacks.get(1));
        return this.getLevel().getRecipeManager().getRecipeFor(GemsmithTransformRecipe.Type.INSTANCE, inventory, this.getLevel());
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(2).isEmpty() || this.itemHandler.getStackInSlot(2).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(2).getCount() + count <= this.itemHandler.getStackInSlot(2).getMaxStackSize();
    }

    @Override
    public int getContainerSize() {
        return 3;
    }
}