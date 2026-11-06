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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.blocks.entities.gui.ArtifactTableMenu;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.init.VWBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArtifactTableBlockEntity extends BaseContainerBlockEntity {

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
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        items = nonNullList;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
        if (slot == 0) {
            currentSetting = null;
            settingName = "";
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        nbt.putInt("table.progress", this.progress);
        nbt.putString("table.setting_name", this.settingName);
        ContainerHelper.saveAllItems(nbt, this.items, registries);
        super.saveAdditional(nbt, registries);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt.contains("menus") ? nbt.getCompound("menus") : nbt, this.items, registries);
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
        Containers.dropContents(this.level, this.worldPosition, this);
    }

    public void tick(Level level, BlockPos pos, BlockState state, ArtifactTableBlockEntity blockEntity) {
        tick++;
        if (ModConfigController.ARTIFACTS_CONFIG_VALUES != null && tick % 20 == 0 && state.getValue(BlockStateProperties.TRIGGERED)) {
            for (ModConfigController.ArtifactSetting setting : ModConfigController.ARTIFACTS_CONFIG_VALUES) {
                if (currentSetting == null) {
                    if (setting.artifact().getId().getPath().equals(settingName)) {
                        currentSetting = setting;
                        break;
                    } else if (settingName.isEmpty() && getItem(0).getItem().equals(setting.artifact().build()))
                        currentSetting = setting;
                }
                if (!setting.artifact().build().equals(Items.AIR) && getItem(0).getItem().equals(setting.artifact().build())
                        && progress <= 0 && !getItem(1).isEmpty() && !getItem(2).isEmpty()) {
                    progress = 320;
                    OnActionsTrigger.spawnParticle(ParticleTypes.EFFECT, level, pos.getX(), pos.getY(), pos.getZ(), 2);
                    level.playLocalSound(pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.5f, 1f, true);
                }
            }
            if (progress > 0 && getItem(2).is(Items.DRAGON_BREATH)) {
                progress--;
                OnActionsTrigger.spawnParticle(ParticleTypes.EFFECT, level, pos.getX(), pos.getY() + 0.5f, pos.getZ(), 2);
                if (progress < 2) {
                    level.playLocalSound(pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.5f, 1f, true);
                    blockEntity.removeItem(1, 1);
                    blockEntity.removeItem(2, 1);
                    progress = 0;
                    return;
                }
                if (currentSetting == null)
                    return;
                int strength = currentSetting.effect_strength();
                int range = currentSetting.effect_range();
                List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(pos.above().getCenter(), pos.above().getCenter()).inflate(1 + range));
                ItemStack potion = getItem(1);
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