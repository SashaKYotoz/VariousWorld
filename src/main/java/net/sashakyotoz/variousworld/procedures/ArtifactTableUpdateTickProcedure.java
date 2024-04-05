package net.sashakyotoz.variousworld.procedures;

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

import net.sashakyotoz.variousworld.network.VariousWorldModVariables;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

import java.util.concurrent.atomic.AtomicReference;

public class ArtifactTableUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> reference = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> reference.set(capability.getStackInSlot(slotid).copy()));
				return reference.get();
			}
		}.getItemStack(world,
				BlockPos.containing(x + VariousWorldModVariables.MapVariables.get(world).WitheredTime, y + VariousWorldModVariables.MapVariables.get(world).WitheredTime, z + VariousWorldModVariables.MapVariables.get(world).WitheredTime), 0))
				.getItem() == VariousWorldModItems.STRENGH_AMULET.get()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands()
						.performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((x + VariousWorldModVariables.MapVariables.get(world).WitheredTime), (y + VariousWorldModVariables.MapVariables.get(world).WitheredTime),
												(z + VariousWorldModVariables.MapVariables.get(world).WitheredTime)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"/effect give @a[distance=..25] minecraft:strength 30 1");
		}
		if ((new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> reference = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> reference.set(capability.getStackInSlot(slotid).copy()));
				return reference.get();
			}
		}.getItemStack(world,
				BlockPos.containing(x + VariousWorldModVariables.MapVariables.get(world).WitheredTime, y + VariousWorldModVariables.MapVariables.get(world).WitheredTime, z + VariousWorldModVariables.MapVariables.get(world).WitheredTime), 1))
				.getItem() == VariousWorldModItems.REGENERATION_GEM.get()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands()
						.performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((x + VariousWorldModVariables.MapVariables.get(world).WitheredTime), (y + VariousWorldModVariables.MapVariables.get(world).WitheredTime),
												(z + VariousWorldModVariables.MapVariables.get(world).WitheredTime)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"/effect give @a[distance=..30] minecraft:regeneration 30 1");
		}
		if ((new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> reference = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> reference.set(capability.getStackInSlot(slotid).copy()));
				return reference.get();
			}
		}.getItemStack(world,
				BlockPos.containing(x + VariousWorldModVariables.MapVariables.get(world).WitheredTime, y + VariousWorldModVariables.MapVariables.get(world).WitheredTime, z + VariousWorldModVariables.MapVariables.get(world).WitheredTime), 3))
				.getItem() == VariousWorldModItems.EXPLORER_NECKLACE.get()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands()
						.performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((x + VariousWorldModVariables.MapVariables.get(world).WitheredTime), (y + VariousWorldModVariables.MapVariables.get(world).WitheredTime),
												(z + VariousWorldModVariables.MapVariables.get(world).WitheredTime)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"/effect give @a[distance=..30] minecraft:jump_boost 30 1");
		}
		if ((new Object() {
			public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
				AtomicReference<ItemStack> reference = new AtomicReference<>(ItemStack.EMPTY);
				BlockEntity _ent = world.getBlockEntity(pos);
				if (_ent != null)
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> reference.set(capability.getStackInSlot(slotid).copy()));
				return reference.get();
			}
		}.getItemStack(world,
				BlockPos.containing(x + VariousWorldModVariables.MapVariables.get(world).WitheredTime, y + VariousWorldModVariables.MapVariables.get(world).WitheredTime, z + VariousWorldModVariables.MapVariables.get(world).WitheredTime), 2))
				.getItem() == VariousWorldModItems.AMETHYST_RING.get()) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands()
						.performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((x + VariousWorldModVariables.MapVariables.get(world).WitheredTime), (y + VariousWorldModVariables.MapVariables.get(world).WitheredTime),
												(z + VariousWorldModVariables.MapVariables.get(world).WitheredTime)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"/effect give @a[distance=..30] minecraft:resistance 30 1");
		}
	}
}
