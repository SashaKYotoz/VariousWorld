
package net.sashakyotoz.variousworld.item;

import com.google.common.collect.Lists;
import net.sashakyotoz.variousworld.entity.technical.LordOfFuriesCrossbowEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class LordOfFuriesCrossbowItem extends ProjectileWeaponItem implements Vanishable {
    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;

    public LordOfFuriesCrossbowItem() {
        super(new Item.Properties().durability(768));
    }

    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return ARROW_OR_FIREWORK;
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (isCharged(itemstack)) {
            performShooting(level, player, hand, itemstack, getShootingPower(itemstack), 1.0F);
            setCharged(itemstack, false);
            return InteractionResultHolder.consume(itemstack);
        } else if (!player.getProjectile(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
                player.startUsingItem(hand);
            }
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    private static float getShootingPower(ItemStack p_40946_) {
        return containsChargedProjectile(p_40946_, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
    }

    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int p_40878_) {
        int i = this.getUseDuration(stack) - p_40878_;
        float f = getPowerForTime(i, stack);
        if (f >= 1.0F && !isCharged(stack) && tryLoadProjectiles(entity, stack)) {
            setCharged(stack, true);
            SoundSource soundsource = entity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundsource, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
    }

    private static boolean tryLoadProjectiles(LivingEntity entity, ItemStack stack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MULTISHOT, stack);
        int j = i == 0 ? 1 : 3;
        boolean flag = entity instanceof Player && ((Player) entity).getAbilities().instabuild;
        ItemStack itemstack = entity.getProjectile(stack);
        ItemStack itemstack1 = itemstack.copy();
        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                itemstack = itemstack1.copy();
            }
            if (itemstack.isEmpty() && flag) {
                itemstack = new ItemStack(Items.ARROW);
                itemstack1 = itemstack.copy();
            }
            if (!loadProjectile(entity, stack, itemstack, k > 0, flag)) {
                return false;
            }
        }
        return true;
    }

    private static boolean loadProjectile(LivingEntity entity, ItemStack stack, ItemStack p_40865_, boolean p_40866_, boolean p_40867_) {
        if (p_40865_.isEmpty()) {
            return false;
        } else {
            boolean flag = p_40867_ && p_40865_.getItem() instanceof ArrowItem;
            ItemStack itemstack;
            if (!flag && !p_40867_ && !p_40866_) {
                itemstack = p_40865_.split(1);
                if (p_40865_.isEmpty() && entity instanceof Player) {
                    ((Player) entity).getInventory().removeItem(p_40865_);
                }
            } else {
                itemstack = p_40865_.copy();
            }
            addChargedProjectile(stack, itemstack);
            return true;
        }
    }

    public static boolean isCharged(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null && compoundtag.getBoolean("Charged");
    }

    public static void setCharged(ItemStack stack, boolean p_40886_) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putBoolean("Charged", p_40886_);
    }

    private static void addChargedProjectile(ItemStack stack, ItemStack stack1) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        ListTag listtag;
        if (compoundtag.contains("ChargedProjectiles", 9)) {
            listtag = compoundtag.getList("ChargedProjectiles", 10);
        } else {
            listtag = new ListTag();
        }
        CompoundTag compoundtag1 = new CompoundTag();
        stack1.save(compoundtag1);
        listtag.add(compoundtag1);
        compoundtag.put("ChargedProjectiles", listtag);
    }

    private static List<ItemStack> getChargedProjectiles(ItemStack stack) {
        List<ItemStack> list = Lists.newArrayList();
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag != null && compoundtag.contains("ChargedProjectiles", 9)) {
            ListTag listtag = compoundtag.getList("ChargedProjectiles", 10);
            if (listtag != null) {
                for (int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag1 = listtag.getCompound(i);
                    list.add(ItemStack.of(compoundtag1));
                }
            }
        }
        return list;
    }

    private static void clearChargedProjectiles(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag != null) {
            ListTag listtag = compoundtag.getList("ChargedProjectiles", 9);
            listtag.clear();
            compoundtag.put("ChargedProjectiles", listtag);
        }
    }

    public static boolean containsChargedProjectile(ItemStack stack, Item item) {
        return getChargedProjectiles(stack).stream().anyMatch((stack1) -> stack1.is(item));
    }

    private static void shootProjectile(Level level, LivingEntity entity, InteractionHand hand, ItemStack stack, ItemStack flagStack, float p_40900_, boolean p_40901_, float p_40902_, float p_40903_, float angleAxis) {
        if (!level.isClientSide) {
            boolean flag = flagStack.is(Items.FIREWORK_ROCKET);
            Projectile projectile;
            if (flag) {
                projectile = new FireworkRocketEntity(level, flagStack, entity, entity.getX(), entity.getEyeY() - (double) 0.15F, entity.getZ(), true);
            } else {
                projectile = LordOfFuriesCrossbowEntity.shoot(level, entity, RandomSource.create(), 3.0f, 2.5, 1);
                if (p_40901_ || angleAxis != 0.0F)
                    ((AbstractArrow) projectile).pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
            Vec3 vec31 = entity.getUpVector(1.0F);
            Quaternionf quaternionf = (new Quaternionf()).setAngleAxis(angleAxis * ((float) Math.PI / 180F), vec31.x, vec31.y, vec31.z);
            Vec3 vec3 = entity.getViewVector(1.0F);
            Vector3f vector3f = vec3.toVector3f().rotate(quaternionf);
            projectile.shoot(vector3f.x(), vector3f.y(), vector3f.z(), p_40902_, p_40903_);
            LordOfFuriesCrossbowEntity.shoot(level, entity, RandomSource.create(), 3.0f, 2.5, 1);
            stack.hurtAndBreak(flag ? 3 : 1, entity, (entity1) -> entity1.broadcastBreakEvent(hand));
            level.addFreshEntity(projectile);
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, p_40900_);
        }
    }

    public static void performShooting(Level level, LivingEntity livingEntity, InteractionHand hand, ItemStack stack, float p_40892_, float p_40893_) {
        if (livingEntity instanceof Player player && net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, livingEntity.level(), player, 1, true) < 0)
            return;
        List<ItemStack> list = getChargedProjectiles(stack);
        float[] afloat = getShotPitches(livingEntity.getRandom());
        for (int i = 0; i < list.size(); ++i) {
            ItemStack itemstack = list.get(i);
            boolean flag = livingEntity instanceof Player && ((Player) livingEntity).getAbilities().instabuild;
            if (!itemstack.isEmpty()) {
                if (i == 0) {
                    shootProjectile(level, livingEntity, hand, stack, itemstack, afloat[i], flag, p_40892_, p_40893_, 0.0F);
                } else if (i == 1) {
                    shootProjectile(level, livingEntity, hand, stack, itemstack, afloat[i], flag, p_40892_, p_40893_, -10.0F);
                } else if (i == 2) {
                    shootProjectile(level, livingEntity, hand, stack, itemstack, afloat[i], flag, p_40892_, p_40893_, 10.0F);
                }
            }
        }
        onCrossbowShot(level, livingEntity, stack);
    }

    private static float[] getShotPitches(RandomSource source) {
        boolean flag = source.nextBoolean();
        return new float[]{1.0F, getRandomShotPitch(flag, source), getRandomShotPitch(!flag, source)};
    }

    private static float getRandomShotPitch(boolean p_220026_, RandomSource source) {
        float f = p_220026_ ? 0.63F : 0.43F;
        return 1.0F / (source.nextFloat() * 0.5F + 1.8F) + f;
    }

    private static void onCrossbowShot(Level level, LivingEntity entity, ItemStack stack) {
        if (entity instanceof ServerPlayer serverplayer) {
            if (!level.isClientSide) {
                CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, stack);
            }
            serverplayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }
        clearChargedProjectiles(stack);
    }

    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int i1) {
        if (!level.isClientSide) {
            int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundevent = this.getStartSound(i);
            SoundEvent soundevent1 = i == 0 ? SoundEvents.CROSSBOW_LOADING_MIDDLE : null;
            float f = (float) (stack.getUseDuration() - i1) / (float) getChargeDuration(stack);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }
            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundevent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            if (f >= 0.5F && soundevent1 != null && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundevent1, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    public int getUseDuration(ItemStack stack) {
        return getChargeDuration(stack) + 3;
    }

    public static int getChargeDuration(ItemStack stack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }

    private SoundEvent getStartSound(int i) {
        return switch (i) {
            case 1 -> SoundEvents.CROSSBOW_QUICK_CHARGE_1;
            case 2 -> SoundEvents.CROSSBOW_QUICK_CHARGE_2;
            case 3 -> SoundEvents.CROSSBOW_QUICK_CHARGE_3;
            default -> SoundEvents.CROSSBOW_LOADING_START;
        };
    }

    private static float getPowerForTime(int i, ItemStack stack) {
        float f = (float) i / (float) getChargeDuration(stack);
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        List<ItemStack> list = getChargedProjectiles(stack);
        if (isCharged(stack) && !list.isEmpty()) {
            ItemStack itemstack = list.get(0);
            components.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemstack.getDisplayName()));
            if (flag.isAdvanced() && itemstack.is(Items.FIREWORK_ROCKET)) {
                List<Component> list1 = Lists.newArrayList();
                Items.FIREWORK_ROCKET.appendHoverText(itemstack, level, list1, flag);
                if (!list1.isEmpty()) {
                    list1.replaceAll(p130942 -> Component.literal("  ").append(p130942).withStyle(ChatFormatting.GRAY));
                    components.addAll(list1);
                }
            }
        }
    }

    public boolean useOnRelease(ItemStack stack) {
        return stack.is(this);
    }

    public int getDefaultProjectileRange() {
        return 8;
    }
}