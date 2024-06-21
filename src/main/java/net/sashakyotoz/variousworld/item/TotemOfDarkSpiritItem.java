
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

		if (entity instanceof Player player) {
			if (!isCreativeOrSpectator(player) && hasTotemOfDarkSpirit(player)) {
				if (isWearingAngelArmor(player) || !isValidGroundBelow(level, player)) {
					disableFlying(player);
				} else {
					enableFlying(player);
					spawnSpitParticle(level, player);
				}
			}
		}
	}

	private boolean isCreativeOrSpectator(Player player) {
		if (player.level().isClientSide()) {
			return Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId())
					.getGameMode().isCreative() || Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId())
					.getGameMode() == GameType.SPECTATOR;
		}
		return player.isCreative() || player.isSpectator();
	}

	private boolean hasTotemOfDarkSpirit(Player player) {
		return player.getInventory().contains(new ItemStack(VariousWorldModItems.TOTEM_OF_DARK_SPIRIT.get()));
	}

	private boolean isWearingAngelArmor(Player player) {
		return player.getItemBySlot(EquipmentSlot.HEAD).is(VariousWorldModItems.ANGEL_HELMET.get()) &&
				player.getItemBySlot(EquipmentSlot.CHEST).is(VariousWorldModItems.ANGEL_CHESTPLATE.get()) &&
				player.getItemBySlot(EquipmentSlot.LEGS).is(VariousWorldModItems.ANGEL_LEGGINGS.get()) &&
				player.getItemBySlot(EquipmentSlot.FEET).is(VariousWorldModItems.ANGEL_BOOTS.get());
	}

	private boolean isValidGroundBelow(Level level, Player player) {
		BlockPos pos = player.blockPosition().below();
		return isOccludingBlock(level, pos.below(2)) || isOccludingBlock(level, pos.below(3)) || isOccludingBlock(level, pos.below(4));
	}

	private boolean isOccludingBlock(Level level, BlockPos pos) {
		return level.getBlockState(pos).canOcclude();
	}

	private void enableFlying(Player player) {
		if (!player.getAbilities().mayfly) {
			player.getAbilities().mayfly = true;
			player.onUpdateAbilities();
		}
	}

	private void disableFlying(Player player) {
		if (player.getAbilities().mayfly) {
			player.getAbilities().mayfly = false;
			player.onUpdateAbilities();
		}
	}

	private void spawnSpitParticle(Level level, Player player) {
		if (Math.random() < 0.125)
			level.addParticle(ParticleTypes.SPIT, player.getX(), player.getY(), player.getZ(), 0, 0.5, 0);
	}
}