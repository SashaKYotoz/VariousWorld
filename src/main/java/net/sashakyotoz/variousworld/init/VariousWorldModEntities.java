package net.sashakyotoz.variousworld.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.sashakyotoz.variousworld.entity.ZombieOfStonyMagmaEntity;
import net.sashakyotoz.variousworld.entity.ZanyVilerWitchEntity;
import net.sashakyotoz.variousworld.entity.WanderingSpiritSummonedOfSculksEntity;
import net.sashakyotoz.variousworld.entity.WanderingSpiritAbilityShootEntity;
import net.sashakyotoz.variousworld.entity.SpiritofPeacefulWastelandEntity;
import net.sashakyotoz.variousworld.entity.SpiritofDeepCavernEntity;
import net.sashakyotoz.variousworld.entity.SculkSkeletonEntity;
import net.sashakyotoz.variousworld.entity.SculkScytheEntity;
import net.sashakyotoz.variousworld.entity.SculkNecromancerSkeletonEntity;
import net.sashakyotoz.variousworld.entity.NecromancerStaffEntity;
import net.sashakyotoz.variousworld.entity.MultipleEnderPearlEntity;
import net.sashakyotoz.variousworld.entity.LordOfFuriesCrossbowEntity;
import net.sashakyotoz.variousworld.entity.FuryLordEntity;
import net.sashakyotoz.variousworld.entity.ZombieOfVariousBiomesEntity;
import net.sashakyotoz.variousworld.entity.DarkSpiritGlovesEntity;
import net.sashakyotoz.variousworld.entity.DarkSpiritEntity;
import net.sashakyotoz.variousworld.entity.DarkFuryEntity;
import net.sashakyotoz.variousworld.entity.CrystalicSlimeEntity;
import net.sashakyotoz.variousworld.entity.CrystalicBowEntity;
import net.sashakyotoz.variousworld.entity.CrystalWarriorEntity;
import net.sashakyotoz.variousworld.entity.ArmoredskeletonEntity;
import net.sashakyotoz.variousworld.VariousWorldMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VariousWorldModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VariousWorldMod.MODID);
	public static final RegistryObject<EntityType<CrystalicBowEntity>> CRYSTALIC_BOW = register("projectile_crystalic_bow",
			EntityType.Builder.<CrystalicBowEntity>of(CrystalicBowEntity::new, MobCategory.MISC).setCustomClientFactory(CrystalicBowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<LordOfFuriesCrossbowEntity>> LORD_OF_FURIES_CROSSBOW = register("projectile_lord_of_furies_crossbow", EntityType.Builder
			.<LordOfFuriesCrossbowEntity>of(LordOfFuriesCrossbowEntity::new, MobCategory.MISC).setCustomClientFactory(LordOfFuriesCrossbowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<ZombieOfVariousBiomesEntity>> ZOMBIE_OF_VARIOUS_BIOMES = register("zombie_of_various_biomes",
			EntityType.Builder.<ZombieOfVariousBiomesEntity>of(ZombieOfVariousBiomesEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).setCustomClientFactory(ZombieOfVariousBiomesEntity::new).sized(0.8f, 1.8f));
	public static final RegistryObject<EntityType<SculkSkeletonEntity>> SCULK_SKELETON = register("sculk_skeleton", EntityType.Builder.<SculkSkeletonEntity>of(SculkSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.clientTrackingRange(8).setCustomClientFactory(SculkSkeletonEntity::new).fireImmune().sized(0.6f, 2f));
	public static final RegistryObject<EntityType<DarkFuryEntity>> DARK_FURY = register("dark_fury",
			EntityType.Builder.<DarkFuryEntity>of(DarkFuryEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(16).setCustomClientFactory(DarkFuryEntity::new).fireImmune().sized(1f, 0.5f));
	public static final RegistryObject<EntityType<CrystalicSlimeEntity>> CRYSTALIC_SLIME = register("crystalic_slime", EntityType.Builder.<CrystalicSlimeEntity>of(CrystalicSlimeEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.clientTrackingRange(12).setCustomClientFactory(CrystalicSlimeEntity::new).fireImmune().sized(1f, 1f));
	public static final RegistryObject<EntityType<SpiritofPeacefulWastelandEntity>> SPIRITOF_PEACEFUL_WASTELAND = register("spiritof_peaceful_wasteland",
			EntityType.Builder.<SpiritofPeacefulWastelandEntity>of(SpiritofPeacefulWastelandEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8)
					.setCustomClientFactory(SpiritofPeacefulWastelandEntity::new).fireImmune().sized(0.6f, 0.8f));
	public static final RegistryObject<EntityType<SpiritofDeepCavernEntity>> SPIRITOF_DEEP_CAVERN = register("spiritof_deep_cavern", EntityType.Builder.<SpiritofDeepCavernEntity>of(SpiritofDeepCavernEntity::new, MobCategory.AMBIENT)
			.setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).setCustomClientFactory(SpiritofDeepCavernEntity::new).fireImmune().sized(0.5f, 0.8f));
	public static final RegistryObject<EntityType<ArmoredskeletonEntity>> ARMOREDSKELETON = register("armoredskeleton",
			EntityType.Builder.<ArmoredskeletonEntity>of(ArmoredskeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).setCustomClientFactory(ArmoredskeletonEntity::new)
					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<WanderingSpiritSummonedOfSculksEntity>> WANDERING_SPIRIT_SUMMONED_OF_SCULKS = register("wandering_spirit_summoned_of_sculks",
			EntityType.Builder.<WanderingSpiritSummonedOfSculksEntity>of(WanderingSpiritSummonedOfSculksEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8)
					.setCustomClientFactory(WanderingSpiritSummonedOfSculksEntity::new)

					.sized(0.8f, 2f));
	public static final RegistryObject<EntityType<ZombieOfStonyMagmaEntity>> ZOMBIE_OF_STONY_MAGMA = register("zombie_of_stony_magma", EntityType.Builder.<ZombieOfStonyMagmaEntity>of(ZombieOfStonyMagmaEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(ZombieOfStonyMagmaEntity::new).fireImmune().sized(0.7f, 1.8f));
	public static final RegistryObject<EntityType<ZanyVilerWitchEntity>> ZANY_VILER_WITCH = register("zany_viler_witch",
			EntityType.Builder.<ZanyVilerWitchEntity>of(ZanyVilerWitchEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(16).setCustomClientFactory(ZanyVilerWitchEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<CrystalWarriorEntity>> CRYSTAL_WARRIOR = register("crystal_warrior", EntityType.Builder.<CrystalWarriorEntity>of(CrystalWarriorEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(32).setCustomClientFactory(CrystalWarriorEntity::new).fireImmune().sized(1f, 2f));
	public static final RegistryObject<EntityType<DarkSpiritEntity>> DARK_SPIRIT = register("dark_spirit", EntityType.Builder.<DarkSpiritEntity>of(DarkSpiritEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32)
			.setCustomClientFactory(DarkSpiritEntity::new).fireImmune().sized(0.7f, 1.8f));
	public static final RegistryObject<EntityType<SculkNecromancerSkeletonEntity>> SCULK_NECROMANCER_SKELETON = register("sculk_necromancer_skeleton",
			EntityType.Builder.<SculkNecromancerSkeletonEntity>of(SculkNecromancerSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32)
					.setCustomClientFactory(SculkNecromancerSkeletonEntity::new).fireImmune().sized(1f, 2.5f));
	public static final RegistryObject<EntityType<FuryLordEntity>> FURY_LORD = register("fury_lord",
			EntityType.Builder.<FuryLordEntity>of(FuryLordEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setCustomClientFactory(FuryLordEntity::new).fireImmune().sized(2.5f, 2f));
	public static final RegistryObject<EntityType<SculkScytheEntity>> SCULK_SCYTHE_RANGED_ITEM = register("projectile_sculk_scythe_ranged_item",
			EntityType.Builder.<SculkScytheEntity>of(SculkScytheEntity::new, MobCategory.MISC).setCustomClientFactory(SculkScytheEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<WanderingSpiritAbilityShootEntity>> WANDERING_SPIRIT_ABILITY_SHOOT = register("projectile_wandering_spirit_ability_shoot",
			EntityType.Builder.<WanderingSpiritAbilityShootEntity>of(WanderingSpiritAbilityShootEntity::new, MobCategory.MISC).setCustomClientFactory(WanderingSpiritAbilityShootEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.75f, 0.75f));
	public static final RegistryObject<EntityType<NecromancerStaffEntity>> NECROMANCER_STAFF = register("projectile_necromancer_staff", EntityType.Builder.<NecromancerStaffEntity>of(NecromancerStaffEntity::new, MobCategory.MISC)
			.setCustomClientFactory(NecromancerStaffEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<MultipleEnderPearlEntity>> MULTIPLE_ENDER_PEARL = register("projectile_multiple_ender_pearl", EntityType.Builder.<MultipleEnderPearlEntity>of(MultipleEnderPearlEntity::new, MobCategory.MISC)
			.setCustomClientFactory(MultipleEnderPearlEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<DarkSpiritGlovesEntity>> DARK_SPIRIT_GLOVES = register("projectile_dark_spirit_gloves", EntityType.Builder.<DarkSpiritGlovesEntity>of(DarkSpiritGlovesEntity::new, MobCategory.MISC).sized(0.3125F, 0.3125F).clientTrackingRange(8));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ZombieOfVariousBiomesEntity.init();
			SculkSkeletonEntity.init();
			DarkFuryEntity.init();
			CrystalicSlimeEntity.init();
			SpiritofPeacefulWastelandEntity.init();
			SpiritofDeepCavernEntity.init();
			ArmoredskeletonEntity.init();
			WanderingSpiritSummonedOfSculksEntity.init();
			ZombieOfStonyMagmaEntity.init();
			ZanyVilerWitchEntity.init();
			CrystalWarriorEntity.init();
			DarkSpiritEntity.init();
			SculkNecromancerSkeletonEntity.init();
			FuryLordEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ZOMBIE_OF_VARIOUS_BIOMES.get(), ZombieOfVariousBiomesEntity.createAttributes().build());
		event.put(SCULK_SKELETON.get(), SculkSkeletonEntity.createAttributes().build());
		event.put(DARK_FURY.get(), DarkFuryEntity.createAttributes().build());
		event.put(CRYSTALIC_SLIME.get(), CrystalicSlimeEntity.createAttributes().build());
		event.put(SPIRITOF_PEACEFUL_WASTELAND.get(), SpiritofPeacefulWastelandEntity.createAttributes().build());
		event.put(SPIRITOF_DEEP_CAVERN.get(), SpiritofDeepCavernEntity.createAttributes().build());
		event.put(ARMOREDSKELETON.get(), ArmoredskeletonEntity.createAttributes().build());
		event.put(WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), WanderingSpiritSummonedOfSculksEntity.createAttributes().build());
		event.put(ZOMBIE_OF_STONY_MAGMA.get(), ZombieOfStonyMagmaEntity.createAttributes().build());
		event.put(ZANY_VILER_WITCH.get(), ZanyVilerWitchEntity.createAttributes().build());
		event.put(CRYSTAL_WARRIOR.get(), CrystalWarriorEntity.createAttributes().build());
		event.put(DARK_SPIRIT.get(), DarkSpiritEntity.createAttributes().build());
		event.put(SCULK_NECROMANCER_SKELETON.get(), SculkNecromancerSkeletonEntity.createAttributes().build());
		event.put(FURY_LORD.get(), FuryLordEntity.createAttributes().build());
	}
}
