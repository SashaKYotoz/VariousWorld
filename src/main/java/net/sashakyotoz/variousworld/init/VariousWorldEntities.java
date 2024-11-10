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

import net.sashakyotoz.variousworld.entity.*;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.entity.boats.ModBoatEntity;
import net.sashakyotoz.variousworld.entity.boats.ModChestBoatEntity;
import net.sashakyotoz.variousworld.entity.technical.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VariousWorldEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VariousWorld.MODID);
	public static final RegistryObject<EntityType<CrystalicArrowEntity>> CRYSTALIC_BOW = register("projectile_crystalic_bow",
			EntityType.Builder.<CrystalicArrowEntity>of(CrystalicArrowEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<LordOfFuriesCrossbowEntity>> LORD_OF_FURIES_CROSSBOW = register("projectile_lord_of_furies_crossbow", EntityType.Builder
			.<LordOfFuriesCrossbowEntity>of(LordOfFuriesCrossbowEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<ZombieOfVariousBiomesEntity>> ZOMBIE_OF_VARIOUS_BIOMES = register("zombie_of_various_biomes",
			EntityType.Builder.of(ZombieOfVariousBiomesEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).sized(0.8f, 1.8f));
	public static final RegistryObject<EntityType<SculkSkeletonEntity>> SCULK_SKELETON = register("sculk_skeleton", EntityType.Builder.of(SculkSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.clientTrackingRange(8).fireImmune().sized(0.6f, 2f));
	public static final RegistryObject<EntityType<DarkFuryEntity>> DARK_FURY = register("dark_fury",
			EntityType.Builder.of(DarkFuryEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(16).fireImmune().sized(1f, 0.5f));
	public static final RegistryObject<EntityType<CrystalicSlimeEntity>> CRYSTALIC_SLIME = register("crystalic_slime", EntityType.Builder.of(CrystalicSlimeEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.clientTrackingRange(12).fireImmune().sized(1f, 1f));
	public static final RegistryObject<EntityType<SpiritofPeacefulWastelandEntity>> SPIRITOF_PEACEFUL_WASTELAND = register("spiritof_peaceful_wasteland",
			EntityType.Builder.of(SpiritofPeacefulWastelandEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8)
					.fireImmune().sized(0.6f, 0.8f));
	public static final RegistryObject<EntityType<SpiritOfDeepCavernEntity>> SPIRITOF_DEEP_CAVERN = register("spiritof_deep_cavern", EntityType.Builder.of(SpiritOfDeepCavernEntity::new, MobCategory.AMBIENT)
			.setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).fireImmune().sized(0.5f, 0.8f));
	public static final RegistryObject<EntityType<ArmoredSkeletonEntity>> ARMORED_SKELETON = register("armoredskeleton",
			EntityType.Builder.of(ArmoredSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<WanderingSpiritOfSculksEntity>> WANDERING_SPIRIT_SUMMONED_OF_SCULKS = register("wandering_spirit_summoned_of_sculks",
			EntityType.Builder.of(WanderingSpiritOfSculksEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8)
					.sized(1f, 2f));
	public static final RegistryObject<EntityType<ZombieOfStonyMagmaEntity>> ZOMBIE_OF_STONY_MAGMA = register("zombie_of_stony_magma", EntityType.Builder.of(ZombieOfStonyMagmaEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).fireImmune().sized(0.7f, 1.8f));
	public static final RegistryObject<EntityType<ZanyVilerWitchEntity>> ZANY_VILER_WITCH = register("zany_viler_witch",
			EntityType.Builder.of(ZanyVilerWitchEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<DromophantEntity>> DROMOPHANT = register("dromophant",
			EntityType.Builder.of(DromophantEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).clientTrackingRange(8).sized(1.5f, 2f));
	public static final RegistryObject<EntityType<CrystalWarriorEntity>> CRYSTAL_WARRIOR = register("crystal_warrior", EntityType.Builder.of(CrystalWarriorEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(32).fireImmune().sized(1f, 2f));
	public static final RegistryObject<EntityType<DarkSpiritEntity>> DARK_SPIRIT = register("dark_spirit", EntityType.Builder.of(DarkSpiritEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32)
			.fireImmune().sized(0.7f, 1.8f));
	public static final RegistryObject<EntityType<SculkNecromancerSkeletonEntity>> SCULK_NECROMANCER_SKELETON = register("sculk_necromancer_skeleton",
			EntityType.Builder.of(SculkNecromancerSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32)
					.fireImmune().sized(1f, 2.5f));
	public static final RegistryObject<EntityType<FuryLordEntity>> FURY_LORD = register("fury_lord",
			EntityType.Builder.of(FuryLordEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).fireImmune().sized(2.5f, 2f));
	public static final RegistryObject<EntityType<SculkScytheEntity>> SCULK_SCYTHE_PROJECTILE = register("projectile_sculk_scythe_ranged_item",
			EntityType.Builder.<SculkScytheEntity>of(SculkScytheEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<WanderingSpiritProjectileEntity>> WANDERING_SPIRIT_PROJECTILE = register("projectile_wandering_spirit_ability_shoot",
			EntityType.Builder.<WanderingSpiritProjectileEntity>of(WanderingSpiritProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(1f, 1f));
	public static final RegistryObject<EntityType<NecromancerStaffEntity>> NECROMANCER_STAFF = register("projectile_necromancer_staff", EntityType.Builder.<NecromancerStaffEntity>of(NecromancerStaffEntity::new, MobCategory.MISC)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<MultipleEnderPearlEntity>> MULTIPLE_ENDER_PEARL = register("projectile_multiple_ender_pearl", EntityType.Builder.<MultipleEnderPearlEntity>of(MultipleEnderPearlEntity::new, MobCategory.MISC)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<DarkSpiritGlovesEntity>> DARK_SPIRIT_GLOVES = register("projectile_dark_spirit_gloves", EntityType.Builder.<DarkSpiritGlovesEntity>of(DarkSpiritGlovesEntity::new, MobCategory.MISC).sized(0.325F, 0.325F).clientTrackingRange(8));
	public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =register("mod_boat",EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new,MobCategory.MISC).sized(1.375f, 0.5625f));
	public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =register("mod_chest_boat",EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new,MobCategory.MISC).sized(1.375f, 0.5625f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return ENTITIES.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ZombieOfVariousBiomesEntity.init();
			SculkSkeletonEntity.init();
			DarkFuryEntity.init();
			CrystalicSlimeEntity.init();
			SpiritofPeacefulWastelandEntity.init();
			SpiritOfDeepCavernEntity.init();
			ArmoredSkeletonEntity.init();
			WanderingSpiritOfSculksEntity.init();
			ZombieOfStonyMagmaEntity.init();
			DromophantEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ZOMBIE_OF_VARIOUS_BIOMES.get(), ZombieOfVariousBiomesEntity.createAttributes().build());
		event.put(SCULK_SKELETON.get(), SculkSkeletonEntity.createAttributes().build());
		event.put(DARK_FURY.get(), DarkFuryEntity.createAttributes().build());
		event.put(CRYSTALIC_SLIME.get(), CrystalicSlimeEntity.createAttributes().build());
		event.put(SPIRITOF_PEACEFUL_WASTELAND.get(), SpiritofPeacefulWastelandEntity.createAttributes().build());
		event.put(SPIRITOF_DEEP_CAVERN.get(), SpiritOfDeepCavernEntity.createAttributes().build());
		event.put(ARMORED_SKELETON.get(), ArmoredSkeletonEntity.createAttributes().build());
		event.put(WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), WanderingSpiritOfSculksEntity.createAttributes().build());
		event.put(ZOMBIE_OF_STONY_MAGMA.get(), ZombieOfStonyMagmaEntity.createAttributes().build());
		event.put(ZANY_VILER_WITCH.get(), ZanyVilerWitchEntity.createAttributes().build());
		event.put(CRYSTAL_WARRIOR.get(), CrystalWarriorEntity.createAttributes().build());
		event.put(DARK_SPIRIT.get(), DarkSpiritEntity.createAttributes().build());
		event.put(SCULK_NECROMANCER_SKELETON.get(), SculkNecromancerSkeletonEntity.createAttributes().build());
		event.put(FURY_LORD.get(), FuryLordEntity.createAttributes().build());
		event.put(DROMOPHANT.get(), DromophantEntity.createAttributes().build());
	}
}