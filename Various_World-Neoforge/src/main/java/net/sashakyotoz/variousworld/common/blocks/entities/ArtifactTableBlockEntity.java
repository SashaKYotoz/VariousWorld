package net.sashakyotoz.variousworld.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.ArtifactTableMenu;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.init.VWBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArtifactTableBlockEntity extends BaseContainerBlockEntity {

    public static final BlockCapability<IItemHandler, Void> TABLE_ITEM_HANDLER =
            BlockCapability.createVoid(
                    VariousWorld.createVWLocation("artifact_table_item_handler"),
                    IItemHandler.class);
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public NonNullList<ItemStack> items;
    protected final ContainerData data;
    private int tick = 0;
    public int progress = 0;
    private int maxProgress = 320;
    private ModConfigController.ArtifactSetting currentSetting;
    private String settingName = "";


    public ArtifactTableBlockEntity(BlockPos pos, BlockState state) {
        super(VWBlocks.ARTIFACT_TABLE_BE.get(), pos, state);
        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ArtifactTableBlockEntity.this.progress;
                    case 1 -> ArtifactTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ArtifactTableBlockEntity.this.progress = value;
                    case 1 -> ArtifactTableBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.various_world.artifact_table");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        if (items.isEmpty())
            for (int i = 0; i < itemHandler.getSlots(); i++)
                items.set(i, itemHandler.getStackInSlot(i));
        return items;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
        itemHandler.setStackInSlot(slot, stack);
        if (slot == 0 && ModConfigController.ARTIFACTS_CONFIG_VALUES != null) {
            for (ModConfigController.ArtifactSetting setting : ModConfigController.ARTIFACTS_CONFIG_VALUES) {
                if (currentSetting != null && !stack.getItem().equals(currentSetting.artifact().build())) {
                    currentSetting = setting;
                    break;
                } else {
                    settingName = "";
                    currentSetting = null;
                }
            }
        }
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
        nbt.putString("table.setting_name", this.settingName);
        ContainerHelper.saveAllItems(nbt, this.items, registries);
        super.saveAdditional(nbt, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        itemHandler.deserializeNBT(registries, nbt.getCompound("menus"));
        ContainerHelper.loadAllItems(nbt, this.items, registries);
        for (int i = 0; i < items.size(); i++)
            itemHandler.setStackInSlot(i, items.get(i));
        progress = nbt.getInt("table.progress");
        settingName = nbt.getString("table.setting_name");
        super.loadAdditional(nbt, registries);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ArtifactTableMenu(i, inventory, this, this.data);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ArtifactTableMenu(i, inventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos pos, BlockState state, ArtifactTableBlockEntity blockEntity) {
        tick++;
        if (ModConfigController.ARTIFACTS_CONFIG_VALUES != null && tick % 20 == 0 && state.getValue(BlockStateProperties.TRIGGERED)) {
            for (ModConfigController.ArtifactSetting setting : ModConfigController.ARTIFACTS_CONFIG_VALUES) {
                if (currentSetting == null) {
                    if (setting.artifact().getId().getPath().equals(settingName)) {
                        currentSetting = setting;
                        break;
                    } else if (settingName.isEmpty() && itemHandler.getStackInSlot(0).getItem().equals(setting.artifact().build()))
                        currentSetting = setting;
                }
                if (!setting.artifact().build().equals(Items.AIR) && itemHandler.getStackInSlot(0).getItem().equals(setting.artifact().build())
                        && progress <= 0 && !itemHandler.getStackInSlot(1).isEmpty() && !itemHandler.getStackInSlot(2).isEmpty()) {
                    progress = 320;
                    OnActionsTrigger.spawnParticle(ParticleTypes.EFFECT, level, pos.getX(), pos.getY(), pos.getZ(), 2);
                    level.playLocalSound(pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.5f, 1f, true);
                }
            }
            if (progress > 0 && itemHandler.getStackInSlot(2).is(Items.DRAGON_BREATH)) {
                progress--;
                OnActionsTrigger.spawnParticle(ParticleTypes.EFFECT, level, pos.getX(), pos.getY() + 0.5f, pos.getZ(), 2);
                if (progress < 2) {
                    level.playLocalSound(pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.5f, 1f, true);
                    blockEntity.removeItem(1, 1);
                    blockEntity.removeItem(2, 1);
                    progress = 0;
                    return;
                }
                int strength = currentSetting.effect_strength();
                int range = currentSetting.effect_range();
                List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(pos.above().getCenter(), pos.above().getCenter()).inflate(1 + range));
                ItemStack potion = itemHandler.getStackInSlot(1);
                for (Player player : players) {
                    if (potion.has(DataComponents.POTION_CONTENTS)) {
                        PotionContents potions = potion.get(DataComponents.POTION_CONTENTS);
                        if (potions.potion().isPresent()
                                && !player.hasEffect(potions.potion().get().value().getEffects().getFirst().getEffect())
                                && !level.isClientSide()) {
                            player.addEffect(new MobEffectInstance(
                                    potions.potion().get().value().getEffects().getFirst().getEffect(),
                                    potions.potion().get().value().getEffects().getFirst().getDuration() / 2,
                                    adjustedAmplifier(potions.potion().get().value().getEffects().getFirst().getAmplifier(), strength)
                            ));
                        }
                    }
                }
            }
        }
    }

    private int adjustedAmplifier(int baseAmplifier, int strength) {
        int raw = baseAmplifier + strength;
        return Math.max(0, raw);
    }

    @Override
    public int getContainerSize() {
        return 3;
    }
}