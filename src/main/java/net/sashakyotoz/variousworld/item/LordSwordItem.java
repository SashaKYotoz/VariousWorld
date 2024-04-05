
package net.sashakyotoz.variousworld.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.procedures.LordSwordRightClickedInAirProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.jarjar.nio.util.Lazy;

import java.util.UUID;

public class LordSwordItem extends SwordItem {
    public static final UUID REACH_MOD = UUID.fromString("dccd59ec-6391-436d-9e00-47f2e6005e20");
    public static float reach = 2;
    public static Lazy<? extends Multimap<Attribute, AttributeModifier>> ATTRIBUTE_LAZY_MAP = Lazy.of(() -> {
        Multimap<Attribute, AttributeModifier> map;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 11.5f, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.4, AttributeModifier.Operation.ADDITION));
        if (ForgeMod.ENTITY_REACH.isPresent()) {
            builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(REACH_MOD, "Weapon modifier", reach, AttributeModifier.Operation.ADDITION));
        }
        map = builder.build();
        return map;
    });

    public LordSwordItem() {
        super(new Tier() {
            public int getUses() {
                return 2000;
            }

            public float getSpeed() {
                return 12f;
            }

            public float getAttackDamageBonus() {
                return 11.5f;
            }

            public int getLevel() {
                return 4;
            }

            public int getEnchantmentValue() {
                return 40;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(VariousWorldModItems.LORD_FURY_SCALES_DUST.get()));
            }
        }, 3, -2.4f, new Item.Properties());
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? ATTRIBUTE_LAZY_MAP.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        double reachSqr = reach * reach;
        Level world = entity.level();
        Vec3 viewVec = entity.getViewVector(1.0F);
        Vec3 eyeVec = entity.getEyePosition(1.0F);
        Vec3 targetVec = eyeVec.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);
        AABB viewBB = entity.getBoundingBox().expandTowards(viewVec.scale(reach)).inflate(4.0D, 4.0D, 4.0D);
        EntityHitResult result = ProjectileUtil.getEntityHitResult(world, entity, eyeVec, targetVec, viewBB, EntitySelector.NO_CREATIVE_OR_SPECTATOR, 4f);
        if (result == null || !(result.getEntity() instanceof LivingEntity))
            return false;
        LivingEntity target = (LivingEntity) result.getEntity();
        double distanceToTargetSqr = entity.distanceToSqr(target);
        boolean hitResult = (result != null ? target : null) != null;
        if (hitResult) {
            if (entity instanceof Player) {
                if (reachSqr >= distanceToTargetSqr) {
                    target.hurt(new DamageSource(target.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK)), 1);
                }
            }
        }
        return super.onEntitySwing(stack, entity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        LordSwordRightClickedInAirProcedure.execute(world, entity, ar.getObject());
        return ar;
    }
}
