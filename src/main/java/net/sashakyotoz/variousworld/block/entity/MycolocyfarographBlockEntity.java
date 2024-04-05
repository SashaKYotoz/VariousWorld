package net.sashakyotoz.variousworld.block.entity;

import net.sashakyotoz.variousworld.init.VariousWorldModBlockEntities;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.client.inventory.MycolocyfarographGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class MycolocyfarographBlockEntity extends BlockEntity implements MenuProvider {
	private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 78;

	public MycolocyfarographBlockEntity(BlockPos pos, BlockState state) {
		super(VariousWorldModBlockEntities.MYCOLOCYFAROGRAPH.get(), pos, state);
		this.data = new ContainerData() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> MycolocyfarographBlockEntity.this.progress;
					case 1 -> MycolocyfarographBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> MycolocyfarographBlockEntity.this.progress = value;
					case 1 -> MycolocyfarographBlockEntity.this.maxProgress = value;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}
		};
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("block.various_world.mycolocyfarograph");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		return new MycolocyfarographGUIMenu(id, inventory, this, this.data);
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			return lazyItemHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		nbt.put("inventory", itemHandler.serializeNBT());
		nbt.putInt("mycolocyfarograph.progress", this.progress);
		super.saveAdditional(nbt);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		progress = nbt.getInt("mycolocyfarograph.progress");
	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i, itemHandler.getStackInSlot(i));
		}
		Containers.dropContents(this.level, this.worldPosition, inventory);
	}

	public void tick(Level level, BlockPos pos, BlockState state, MycolocyfarographBlockEntity blockEntity) {
		if (level.isClientSide()) {
			return;
		}
		if (hasRecipe(blockEntity)) {
			blockEntity.progress++;
			setChanged(level, pos, state);
			if (blockEntity.progress >= blockEntity.maxProgress) {
				craftItem(blockEntity);
			}
		} else {
			blockEntity.resetProgress();
			setChanged(level, pos, state);
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static void craftItem(MycolocyfarographBlockEntity pEntity) {
		if (hasRecipe(pEntity)) {
			pEntity.itemHandler.extractItem(1, 1, false);
			pEntity.itemHandler.setStackInSlot(0, new ItemStack(Items.ROTTEN_FLESH, pEntity.itemHandler.getStackInSlot(0).getCount() + 1));
			pEntity.itemHandler.setStackInSlot(2, new ItemStack(ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(new ResourceLocation("various_world:mushroom_drop"))).getRandomElement(RandomSource.create()).get(), pEntity.itemHandler.getStackInSlot(2).getCount() + 1));
			pEntity.resetProgress();
		}
	}

	private static boolean hasRecipe(MycolocyfarographBlockEntity entity) {
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem() == VariousWorldModItems.MYCENA_FROM_CAVERN_OF_DEEP_FOOD.get() ||entity.itemHandler.getStackInSlot(1).getItem() == VariousWorldModItems.CRYSHROOM.get() || entity.itemHandler.getStackInSlot(1).getItem() == Items.BROWN_MUSHROOM || entity.itemHandler.getStackInSlot(1).getItem() == Items.RED_MUSHROOM;
		return hasItemInFirstSlot && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, new ItemStack(ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(new ResourceLocation("various_world:mushroom_drop"))).getRandomElement(RandomSource.create()).get()));
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
		return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
	}
}
