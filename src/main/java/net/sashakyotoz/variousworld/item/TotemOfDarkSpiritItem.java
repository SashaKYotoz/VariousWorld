
package net.sashakyotoz.variousworld.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;

public class TotemOfDarkSpiritItem extends Item {
	public TotemOfDarkSpiritItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level level, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, level, entity, slot, selected);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		if (!(new Object() {
			public boolean checkGamemode(Entity entity1) {
				if (entity1 instanceof ServerPlayer player) {
					return player.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
				} else if (entity1.level().isClientSide() && entity1 instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity) || new Object() {
			public boolean checkGamemode(Entity entity1) {
				if (entity1 instanceof ServerPlayer player) {
					return player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
				} else if (entity1.level().isClientSide() && entity1 instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR;
				}
				return false;
			}
		}.checkGamemode(entity))) {
			if (entity instanceof Player player && player.getInventory().contains(new ItemStack(VariousWorldModItems.TOTEM_OF_DARK_SPIRIT.get()))
					&& !(player.getItemBySlot(EquipmentSlot.HEAD).is(VariousWorldModItems.ANGEL_HELMET.get())
					&& player.getItemBySlot(EquipmentSlot.CHEST).is(VariousWorldModItems.ANGEL_CHESTPLATE.get())
					&& player.getItemBySlot(EquipmentSlot.LEGS).is(VariousWorldModItems.ANGEL_LEGGINGS.get())
					&& player.getItemBySlot(EquipmentSlot.FEET).is(VariousWorldModItems.ANGEL_BOOTS.get()))) {
				if (level.getBlockState(BlockPos.containing(x, y - 3, z)).canOcclude() || level.getBlockState(BlockPos.containing(x, y - 4, z)).canOcclude() || level.getBlockState(BlockPos.containing(x, y - 5, z)).canOcclude()) {
					player.getAbilities().mayfly = true;
					player.onUpdateAbilities();
					if (Math.random() < 0.125)
						level.addParticle(ParticleTypes.SPIT, x, y, z, 0, 0.5, 0);
				} else {
					player.getAbilities().mayfly = false;
					player.onUpdateAbilities();
				}
			}
		}
	}
}
