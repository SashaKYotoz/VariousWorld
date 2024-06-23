package net.sashakyotoz.variousworld.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.client.menus.ArchOfGemsMenu;
import net.sashakyotoz.variousworld.client.menus.DisenchantTableGUIMenu;
import net.sashakyotoz.variousworld.entity.SpiritofPeacefulWastelandEntity;
import net.sashakyotoz.variousworld.init.*;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventManager {
    private static double timer;
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            witheredEnchantmentAction(event.getEntity(), event.getSource().getEntity());
            slimeballSwordAttackWith(event.getEntity(), event.getSource().getEntity());
            amethystSpikesHit(event.getEntity(), event.getSource().getEntity());
        }
    }
    private static double getXVector(double speed, double yaw) {
        return speed * Math.cos((yaw + 90) * (Math.PI / 180));
    }

    private static double getZVector(double speed, double yaw) {
        return speed * Math.sin((yaw + 90) * (Math.PI / 180));
    }
    @SubscribeEvent
    public static void onPlayerGetFallDamage(LivingDamageEvent event){
        if (event.getSource().is(DamageTypes.FALL)){
            if (event.getEntity() instanceof Player player && player.getItemBySlot(EquipmentSlot.HEAD).is(VariousWorldItems.SLIME_ARMOR_HELMET.get())
                    && player.getItemBySlot(EquipmentSlot.CHEST).is(VariousWorldItems.SLIME_ARMOR_CHESTPLATE.get())
                    && player.getItemBySlot(EquipmentSlot.LEGS).is(VariousWorldItems.SLIME_ARMOR_LEGGINGS.get())
                    && player.getItemBySlot(EquipmentSlot.FEET).is(VariousWorldItems.SLIME_ARMOR_BOOTS.get())){
                if (player.fallDistance > 1){
                    float impulseModifier = player.fallDistance * 0.325f - (player.fallDistance > 5 ? 0.325f * (player.fallDistance/2) : 0.125f);
                    if (!player.isShiftKeyDown())
                        player.setDeltaMovement(getXVector(impulseModifier*1.5f,player.getYRot()),impulseModifier,getZVector(impulseModifier*1.5f,player.getYRot()));
                    player.getItemBySlot(EquipmentSlot.FEET).hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    event.setAmount(0);
                    player.fallDistance = 0;
                }
            }
        }
    }
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        onFunctionalBlock(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
    }

    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        spiritPWonClick(event.getTarget(), event.getEntity());
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        itemUpgrading(event.getEntity());
        sculkArmorRepairing(event.getEntity());
    }
    public static boolean isMovingOnLand(LivingEntity entity) {
        return entity.onGround() && entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            itemInHandAbilities(event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
            crystalTransforming(event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
            AdvancementsManager.tickCheckingAdvancements(event.player);
            biomeLimiter(event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
            double x = event.player.getX();
            double y = event.player.getY();
            double z = event.player.getZ();
            if (event.player.containerMenu instanceof DisenchantTableGUIMenu)
                DisenchantTableUpdateTickProcedure.execute(event.player.level(), x,y,z);
            if (event.player.containerMenu instanceof ArchOfGemsMenu)
                ArchOfGemsManagerProcedure.execute(event.player);
        }
    }

    @SubscribeEvent
    public static void onPickup(EntityItemPickupEvent event) {
        totemAnimation(event.getEntity().level(), event.getEntity(), event.getItem().getItem());
    }

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        jumpWithSlimeArmor(event.getEntity());
        jumperEnchantment(event.getEntity());
        chainedAction(event.getEntity());
    }

    private static void biomeLimiter(LevelAccessor accessor, double x, double y, double z, Player player1) {
        if (player1 == null)
            return;
        if (player1 instanceof ServerPlayer player) {
            if (accessor.getBiome(BlockPos.containing(x, y, z)).is(VariousWorldBiomes.SCULK_VALLEY) && (player.level() instanceof ServerLevel
                    && !player.getAdvancements().getOrStartProgress(player.server.getAdvancements().getAdvancement(AdvancementsManager.CRYSTALIC_WARRIOR_ADV)).isDone())) {
                if(!player.isCreative() || !player.isSpectator()){
                    if (!player.level().isClientSide()) {
                        player.displayClientMessage(Component.translatable("hint.various_world.biome.sculk_valley"), true);
                    }
                    if (timer < accessor.dayTime()) {
                        timer = accessor.dayTime() + 50;
                        player1.hurt(player1.damageSources().freeze(), 1);
                    }
                }
            }
        }
    }

    private static void totemAnimation(LevelAccessor accessor, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        if (entity instanceof Player) {
            if (itemstack.is(VariousWorldItems.TOTEM_OF_DARK_SPIRIT.get())) {
                if (accessor.isClientSide())
                    Minecraft.getInstance().gameRenderer.displayItemActivation(itemstack);
            }
        }
    }

    private static void crystalTransforming(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player player) {
            if (player.getInventory().contains(new ItemStack(VariousWorldBlocks.CRYSTAL_BLOCK.get()))) {
                if (world.getLevelData().isThundering()) {
                    if (Math.random() < 0.125) {
                        if (Math.random() < 0.125) {
                            if (world instanceof ServerLevel serverLevel) {
                                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
                                lightningBolt.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -5, 5), y, z + Mth.nextDouble(RandomSource.create(), -5, 5))));
                                serverLevel.addFreshEntity(lightningBolt);
                            }
                            ItemStack itemStack = new ItemStack(VariousWorldBlocks.CRYSTAL_OF_CHARGED_BLOCK.get(), 1);
                            ItemHandlerHelper.giveItemToPlayer(player, itemStack);
                            ItemStack stack = new ItemStack(VariousWorldBlocks.CRYSTAL_BLOCK.get());
                            player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
                        }
                    }
                }
            }
        }
    }

    private static void witheredEnchantmentAction(Entity entity, Entity sourceentity) {
        if (entity == null || sourceentity == null)
            return;
        double witheredStrength = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(VariousWorldEnchantments.WITHERED.get());
        if (witheredStrength > 0) {
            if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide())
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (60 * witheredStrength), (int) witheredStrength));
        }
    }

    private static void chainedAction(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VariousWorldMobEffects.CHAINED_OF_CHAIN.get())) {
            entity.setDeltaMovement(new Vec3(0, (-0.5), 0));
        }
    }

    private static void slimeballSwordAttackWith(Entity entity, Entity sourceentity) {
        if (entity == null || sourceentity == null)
            return;
        double speed;
        double Yaw;
        if (sourceentity instanceof Player player && player.getMainHandItem().getItem() == VariousWorldItems.CRYSTALIC_SLIMEBALL_SWORD.get()) {
            speed = 1.5;
            Yaw = sourceentity.getYRot();
            entity.setDeltaMovement(new Vec3((speed * Math.cos((Yaw + 90) * (Math.PI / 180))), 0.1, (speed * Math.sin((Yaw + 90) * (Math.PI / 180)))));
        }
    }

    private static void onFunctionalBlock(Level level, double x, double y, double z, Player entity) {
        if (entity == null)
            return;
        if (entity instanceof ServerPlayer player) {
            if ((level.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldBlocks.ARTIFACTTABLE.get()) {
                AdvancementsManager.addAdvancement(player, AdvancementsManager.ARTIFACTS_TABLE_ADV);
            }
        } else if ((level.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldBlocks.ARMOR_STATION_BLOCK.get()) {
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.ARMOR_STATION_ADV);
            if (!level.isClientSide())
                level.playSound(null, BlockPos.containing(x, y, z), VariousWorldSounds.ITEM_WAND_SHOOT, SoundSource.PLAYERS, 1, 1);
        } else if ((level.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldBlocks.MYCOLOCYFAROGRAPH.get()) {
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.MUSHROOM_TABLE_ADV);
        } else if ((level.getBlockState(BlockPos.containing(x, y, z))).getBlock() == VariousWorldBlocks.DISENCHANT_TABLE.get()) {
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.DISENCHANT_TABLE_ADV);
            if (!level.isClientSide()) {
                level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1, 1);
            }
        }
    }

    private static void spiritPWonClick(Entity entity, Entity sourceentity) {
        if (entity == null || sourceentity == null)
            return;
        if (entity instanceof SpiritofPeacefulWastelandEntity animal) {
            if (sourceentity instanceof LivingEntity ownedBy && animal.isOwnedBy(ownedBy)) {
                if (sourceentity instanceof ServerPlayer serverPlayer) {
                    AdvancementsManager.addAdvancement(serverPlayer, AdvancementsManager.COMBAT_ALLAY_ADV);
                }
            }
        }
    }

    private static void sculkArmorRepairing(Player player) {
        if (player == null)
            return;
        if (player.getMainHandItem().getItem() == VariousWorldItems.SCULK_SHARD.get()
                && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == VariousWorldItems.SCULK_ARMOR_HELMET.get()
                && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == VariousWorldItems.SCULK_ARMOR_CHESTPLATE.get()
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == VariousWorldItems.SCULK_ARMOR_LEGGINGS.get()
                && player.getItemBySlot(EquipmentSlot.FEET).getItem() == VariousWorldItems.SCULK_ARMOR_BOOTS.get()) {
            player.giveExperiencePoints(-(1));
            ItemStack stack = new ItemStack(VariousWorldItems.SCULK_SHARD.get());
            player.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
            player.getItemBySlot(EquipmentSlot.HEAD).setDamageValue(0);
            player.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(0);
            player.getItemBySlot(EquipmentSlot.LEGS).setDamageValue(0);
            player.getItemBySlot(EquipmentSlot.FEET).setDamageValue(0);
        }
    }
    private static void amethystSpikesHit(Entity entity, Entity sourceentity) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(VariousWorldMobEffects.AMETHYST_SPIKES.get()) && sourceentity != null) {
            sourceentity.hurt(sourceentity.damageSources().generic(),(Objects.requireNonNull(livingEntity.getEffect(VariousWorldMobEffects.AMETHYST_SPIKES.get())).getAmplifier() + 1));
        }
    }
    private static void itemUpgrading(Player entity) {
        if (entity == null)
            return;
        if (entity.getMainHandItem().getItem() == VariousWorldItems.CRYSTALSHARD.get()
                && (entity.getOffhandItem().getItem() == VariousWorldItems.SCULK_SCYTHE.get())) {
            entity.getOffhandItem().getOrCreateTag().putDouble("CustomModelData", 1);
            ItemStack stack = new ItemStack(VariousWorldItems.CRYSTALSHARD.get());
            entity.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.MORE_COLORS_ADV);
        }
        if (entity.getMainHandItem().getItem() == VariousWorldItems.DARKNIUM_INGOT.get()
                && (entity.getOffhandItem().getItem() == VariousWorldItems.CRYSTAL_SWORD.get())) {
            entity.getOffhandItem().getOrCreateTag().putDouble("CustomModelData", 1);
            ItemStack stack = new ItemStack(VariousWorldItems.DARKNIUM_INGOT.get());
            entity.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.MORE_COLORS_ADV);
        }
        if (entity.getMainHandItem().getItem() == VariousWorldItems.SLIME_CRYSTALIC.get()
                && (entity.getOffhandItem().getItem() == VariousWorldItems.LORD_SWORD.get())) {
            entity.getOffhandItem().getOrCreateTag().putDouble("CustomModelData", 1);
            ItemStack stack = new ItemStack(VariousWorldItems.SLIME_CRYSTALIC.get());
            entity.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.MORE_COLORS_ADV);
        }
        if (entity.getMainHandItem().getItem() == VariousWorldItems.SCULK_SHARD.get()
                && (entity.getOffhandItem().getItem() == VariousWorldItems.DARKNIUM_SWORD.get())) {
            entity.getOffhandItem().getOrCreateTag().putDouble("CustomModelData", 1);
            ItemStack stack = new ItemStack(VariousWorldItems.SCULK_SHARD.get());
            entity.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.MORE_COLORS_ADV);
        }
        if (entity.getMainHandItem().getItem() == Items.MAGMA_BLOCK
                && (entity.getOffhandItem().getItem() == VariousWorldItems.NECROMANCER_WAND.get())) {
            entity.getOffhandItem().getOrCreateTag().putDouble("CustomModelData", 1);
            ItemStack stack = new ItemStack(Items.MAGMA_BLOCK);
            entity.getInventory().clearOrCountMatchingItems(p -> stack.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            AdvancementsManager.addAdvancement(entity, AdvancementsManager.MORE_COLORS_ADV);
        }
    }

    private static void itemInHandAbilities(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity))
            return;
        boolean hasSuperVisionCharm = hasSuperVisionCharm(livingEntity);
        CommandSourceStack commandSourceStack = createCommandSourceStack(world, x, y, z);
        if (hasSuperVisionCharm) {
            if (commandSourceStack != null) {
                String command = "/attribute @p minecraft:generic.movement_speed modifier add 3d952521-d8be-4e6d-9906-d1fb22ca3156 vps_binoculars -2 add";
                commandSourceStack.getServer().getCommands().performPrefixedCommand(commandSourceStack, command);
            }
            if (!livingEntity.level().isClientSide() && !livingEntity.hasEffect(MobEffects.NIGHT_VISION)) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0));
            }
        } else {
            if (commandSourceStack != null) {
                String command = "/attribute @p minecraft:generic.movement_speed modifier remove 3d952521-d8be-4e6d-9906-d1fb22ca3156";
                commandSourceStack.getServer().getCommands().performPrefixedCommand(commandSourceStack, command);
            }
            if (commandSourceStack != null) {
                VariousWorld.queueServerWork(10, () -> {
                    String command = "/attribute @p minecraft:generic.movement_speed base set 0.1000005";
                    commandSourceStack.getServer().getCommands().performPrefixedCommand(commandSourceStack, command);
                });
            }
        }
    }

    private static boolean hasSuperVisionCharm(LivingEntity livingEntity) {
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        ItemStack offHandItem = livingEntity.getOffhandItem();
        return mainHandItem.getItem() == VariousWorldItems.SUPER_VISION_CHARM.get() || offHandItem.getItem() == VariousWorldItems.SUPER_VISION_CHARM.get();
    }

    private static CommandSourceStack createCommandSourceStack(LevelAccessor accessor, double x, double y, double z) {
        if (!(accessor instanceof ServerLevel server)) {
            return null;
        }
        return new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, server, 4, "", Component.literal(""), server.getServer(), null).withSuppressedOutput();
    }

    private static void jumpWithSlimeArmor(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.getItemBySlot(EquipmentSlot.HEAD).getItem() == VariousWorldItems.SLIME_ARMOR_HELMET.get()
                    && livingEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() == VariousWorldItems.SLIME_ARMOR_CHESTPLATE.get()
                    && livingEntity.getItemBySlot(EquipmentSlot.LEGS).getItem() == VariousWorldItems.SLIME_ARMOR_LEGGINGS.get()
                    && livingEntity.getItemBySlot(EquipmentSlot.FEET).getItem() == VariousWorldItems.SLIME_ARMOR_BOOTS.get()) {
                entity.setDeltaMovement(new Vec3(0, 0.65, 0));
            }
        }
    }

    private static void jumperEnchantment(Entity entity) {
        if (entity == null)
            return;
        int enchantPower = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getEnchantmentLevel(VariousWorldEnchantments.JUMPER.get());
        double speed;
        double Yaw;
        if (enchantPower == 1) {
            speed = 0.75;
            Yaw = entity.getYRot();
            entity.setDeltaMovement(new Vec3((speed * Math.cos((Yaw + 90) * (Math.PI / 180))), 0.45, (speed * Math.sin((Yaw + 90) * (Math.PI / 180)))));
        } else if (enchantPower == 2) {
            speed = 0.9;
            Yaw = entity.getYRot();
            entity.setDeltaMovement(new Vec3((speed * Math.cos((Yaw + 90) * (Math.PI / 180))), 0.65, (speed * Math.sin((Yaw + 90) * (Math.PI / 180)))));
        }
    }
}