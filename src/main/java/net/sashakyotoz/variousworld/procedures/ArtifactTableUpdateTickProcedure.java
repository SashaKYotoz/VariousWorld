package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.sashakyotoz.variousworld.init.VariousWorldItems;

import java.util.concurrent.atomic.AtomicReference;

public class ArtifactTableUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world == null) return;
		BlockPos blockPos = BlockPos.containing(x, y, z );
		checkAndApplyEffect(world, blockPos, 0, VariousWorldItems.STRENGH_AMULET.get(), "strength", 28);
		checkAndApplyEffect(world, blockPos, 1, VariousWorldItems.REGENERATION_GEM.get(), "regeneration", 28);
		checkAndApplyEffect(world, blockPos, 3, VariousWorldItems.EXPLORER_NECKLACE.get(), "jump_boost", 32);
		checkAndApplyEffect(world, blockPos, 2, VariousWorldItems.AMETHYST_RING.get(), "resistance", 32);
	}
	private static void checkAndApplyEffect(LevelAccessor accessor, BlockPos pos, int slotId, Item expectedItem, String effect, int range) {
		ItemStack itemStack = getItemStack(accessor, pos, slotId);
		if (itemStack.is( expectedItem) && accessor instanceof ServerLevel serverLevel) {
			applyEffect(serverLevel, pos, effect, range);
		}
	}
	private static ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotId) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null) {
			AtomicReference<ItemStack> reference = new AtomicReference<>(ItemStack.EMPTY);
			blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
				reference.set(capability.getStackInSlot(slotId).copy());
			});
			return reference.get();
		}
		return ItemStack.EMPTY;
	}

	private static void applyEffect(ServerLevel serverLevel, BlockPos pos, String effect, int range) {
		String command = String.format("/effect give @a[distance=..%d] minecraft:%s 30 1", range, effect);
		CommandSourceStack sourceStack = new CommandSourceStack(
				CommandSource.NULL,
				new Vec3(pos.getX(), pos.getY(), pos.getZ()),
				Vec2.ZERO,
				serverLevel,
				4,
				"",
				Component.literal(""),
				serverLevel.getServer(),
				null
		).withSuppressedOutput();
		serverLevel.getServer().getCommands().performPrefixedCommand(sourceStack, command);
	}
}