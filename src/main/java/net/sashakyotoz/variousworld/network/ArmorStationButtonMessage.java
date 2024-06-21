
package net.sashakyotoz.variousworld.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.sashakyotoz.variousworld.init.VariousWorldItems;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArmorStationButtonMessage {
    private final int buttonID, x, y, z;

    public ArmorStationButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public ArmorStationButtonMessage(int buttonID, int x, int y, int z) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void buffer(ArmorStationButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
    }

    public static void handler(ArmorStationButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            handleButtonAction(entity, buttonID, x, y, z);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player player, int buttonID, int x, int y, int z) {
        Level world = player.level();
        if (!world.hasChunkAt(new BlockPos(x, y, z)))
            return;
        if (buttonID == 0)
            buttonHandler(player);
    }

    private static void buttonHandler(Player player) {
        if (player == null)
            return;
        if (player instanceof ServerPlayer serverPlayer && serverPlayer.containerMenu instanceof Supplier<?> supplier && supplier.get() instanceof Map slots && !player.level().isClientSide()) {
            if (((Slot) slots.get(0)).getItem().is(Items.FEATHER) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.SCULK_ARMOR_HELMET.get())
                    && ((Slot) slots.get(2)).getItem().is(Items.NETHER_STAR)) {
                ItemStack stack = new ItemStack(VariousWorldItems.ANGEL_HELMET.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
            if (((Slot) slots.get(0)).getItem().is(Items.FEATHER) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.SCULK_ARMOR_CHESTPLATE.get())
                    && ((Slot) slots.get(2)).getItem().is(Items.NETHER_STAR)) {
                ItemStack stack = new ItemStack(VariousWorldItems.ANGEL_CHESTPLATE.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
            if (((Slot) slots.get(0)).getItem().is(Items.FEATHER) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.SCULK_ARMOR_LEGGINGS.get())
                    && ((Slot) slots.get(2)).getItem().is(Items.NETHER_STAR)) {
                ItemStack stack = new ItemStack(VariousWorldItems.ANGEL_LEGGINGS.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
            if (((Slot) slots.get(0)).getItem().is(Items.FEATHER) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.SCULK_ARMOR_BOOTS.get())
                    && ((Slot) slots.get(2)).getItem().is(Items.NETHER_STAR)) {
                ItemStack stack = new ItemStack(VariousWorldItems.ANGEL_BOOTS.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
            if (((Slot) slots.get(0)).getItem().is(Items.PHANTOM_MEMBRANE) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.DARKNIUM_ARMOR_HELMET.get())
                    && ((Slot) slots.get(2)).getItem().is(VariousWorldItems.FURY_INGOT.get())) {
                ItemStack stack = new ItemStack(VariousWorldItems.FURY_HELMET.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
            if (((Slot) slots.get(0)).getItem().is(Items.PHANTOM_MEMBRANE) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.DARKNIUM_ARMOR_CHESTPLATE.get())
                    && ((Slot) slots.get(2)).getItem().is(VariousWorldItems.FURY_INGOT.get())) {
                ItemStack stack = new ItemStack(VariousWorldItems.FURY_CHESTPLATE.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
            if (((Slot) slots.get(0)).getItem().is(Items.PHANTOM_MEMBRANE) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.DARKNIUM_ARMOR_LEGGINGS.get())
                    && ((Slot) slots.get(2)).getItem().is(VariousWorldItems.FURY_INGOT.get())) {
                ItemStack stack = new ItemStack(VariousWorldItems.FURY_LEGGINGS.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
            if (((Slot) slots.get(0)).getItem().is(Items.PHANTOM_MEMBRANE) && ((Slot) slots.get(1)).getItem().is(VariousWorldItems.DARKNIUM_ARMOR_BOOTS.get())
                    && ((Slot) slots.get(2)).getItem().is(VariousWorldItems.FURY_INGOT.get())) {
                ItemStack stack = new ItemStack(VariousWorldItems.FURY_BOOTS.get());
                stack.setCount(1);
                ((Slot) slots.get(3)).set(stack);
                cleanSlots(serverPlayer, slots);
            }
        }
    }

    private static void cleanSlots(Player player, Map slots) {
        ((Slot) slots.get(0)).remove(1);
        ((Slot) slots.get(1)).remove(1);
        ((Slot) slots.get(2)).remove(1);
        player.containerMenu.broadcastChanges();
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        VariousWorld.addNetworkMessage(ArmorStationButtonMessage.class, ArmorStationButtonMessage::buffer, ArmorStationButtonMessage::new, ArmorStationButtonMessage::handler);
    }
}
