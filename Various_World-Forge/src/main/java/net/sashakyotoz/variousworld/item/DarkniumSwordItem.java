
package net.sashakyotoz.variousworld.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;

public class DarkniumSwordItem extends SwordItem {
	public DarkniumSwordItem() {
		super(ModTiers.DARKNIUM, 3, -2.4f, new Item.Properties());
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity attacker) {
		if (attacker.getMainHandItem().getOrCreateTag().getDouble("CustomModelData") == 1) {
			attacker.level().addParticle(VWMiscRegistries.WANDERING_SPIRIT_PROJECTILE_PARTICLE.get(), attacker.getX(), attacker.getY(), attacker.getZ(), 0, 1.5, 0);
			if (Math.random() < 0.5) {
					entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)) {
						@Override
						public Component getLocalizedDeathMessage(LivingEntity living) {
							return Component.translatable("death.attack." + "Absorption by sculk");
						}
					}, 1);
				if (entity.level() instanceof ServerLevel serverLevel)
					serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, attacker.getX(), attacker.getY(), attacker.getZ(), 1));
			}
		}
		return super.hurtEnemy(itemstack, entity, attacker);
	}
}
