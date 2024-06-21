package net.sashakyotoz.variousworld.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VariousWorldItems;

import java.util.HashMap;
import java.util.Map;

public class AdvancementsManager {
	private static final Map<ResourceLocation,ResourceLocation> ADVANCEMENT_MAP = new HashMap<>();
	public static final ResourceLocation WELCOME_TO_VARIOUS_WORLD_ADV = new ResourceLocation(VariousWorld.MODID,"welcome_to_the_various_world");
	public static final ResourceLocation THUNDER_HAMMER_ADV = new ResourceLocation(VariousWorld.MODID,"changedhammer");
	public static final ResourceLocation ARMOR_STATION_ADV = new ResourceLocation(VariousWorld.MODID,"whatastrangeforge");
	public static final ResourceLocation ARTIFACTS_TABLE_ADV = new ResourceLocation(VariousWorld.MODID,"portable_beacon");
	public static final ResourceLocation MUSHROOM_TABLE_ADV = new ResourceLocation(VariousWorld.MODID,"in_searching_of_mushrooms");
	public static final ResourceLocation DISENCHANT_TABLE_ADV = new ResourceLocation(VariousWorld.MODID,"it_isnot_enchanting_table");
	public static final ResourceLocation COMBAT_ALLAY_ADV = new ResourceLocation(VariousWorld.MODID,"combat_allay");
	public static final ResourceLocation MINING_ALLAY_ADV = new ResourceLocation(VariousWorld.MODID,"mining_allay");
	public static final ResourceLocation CRYSTALIC_WARRIOR_ADV = new ResourceLocation(VariousWorld.MODID,"kill_first_titan");
	public static final ResourceLocation DARK_SPIRIT_ADV = new ResourceLocation(VariousWorld.MODID,"defeatthedarkspirit");
	public static final ResourceLocation EASTER_DARK_SPIRIT_ADV = new ResourceLocation(VariousWorld.MODID,"easter_dark_spirit_advancement");
	public static final ResourceLocation SCULK_NECROMANCER_ADV = new ResourceLocation(VariousWorld.MODID,"necromancer_dead");
	public static final ResourceLocation LORD_OF_FURIES_ADV = new ResourceLocation(VariousWorld.MODID,"lord_fury_advancement");
	public static final ResourceLocation CRYSTALIC_SWORD_ADV = new ResourceLocation(VariousWorld.MODID,"createacoolsword");
	public static final ResourceLocation CAVERN_OF_DEEP_ADV = new ResourceLocation(VariousWorld.MODID,"dark_deltas");
	public static final ResourceLocation PEACEFUL_WASTELAND_ADV = new ResourceLocation(VariousWorld.MODID,"enter_peacefulwasteland");
	public static final ResourceLocation CRYSTALIC_FOREST_ADV = new ResourceLocation(VariousWorld.MODID,"forwardin_cristalicforest");
	public static final ResourceLocation MAGMA_CAVERNS_ADV = new ResourceLocation(VariousWorld.MODID,"road_of_stony_magma");
	public static final ResourceLocation SCULK_VALLEY_ADV = new ResourceLocation(VariousWorld.MODID,"sculk_valley_advancement");
	public static final ResourceLocation SHINY_VALLEY_ADV = new ResourceLocation(VariousWorld.MODID,"shiny_valley_advancement");
	public static final ResourceLocation CRYSTAL_SHARD_ADV = new ResourceLocation(VariousWorld.MODID,"the_brightest_radiance");
	public static final ResourceLocation DARKNIUM_INGOT_ADV = new ResourceLocation(VariousWorld.MODID,"darker_material_cant_be_found");
	public static final ResourceLocation FURY_INGOT_ADV = new ResourceLocation(VariousWorld.MODID,"weighs_like_a_feather");
	public static final ResourceLocation LORD_FURY_SCALES_ADV = new ResourceLocation(VariousWorld.MODID,"powerof_slayed_dragon");
	public static final ResourceLocation MORE_COLORS_ADV = new ResourceLocation(VariousWorld.MODID,"more_colors");
	static {
		ADVANCEMENT_MAP.put(WELCOME_TO_VARIOUS_WORLD_ADV,WELCOME_TO_VARIOUS_WORLD_ADV);
		ADVANCEMENT_MAP.put(THUNDER_HAMMER_ADV,THUNDER_HAMMER_ADV);
		ADVANCEMENT_MAP.put(ARMOR_STATION_ADV,ARMOR_STATION_ADV);
		ADVANCEMENT_MAP.put(ARTIFACTS_TABLE_ADV,ARTIFACTS_TABLE_ADV);
		ADVANCEMENT_MAP.put(MUSHROOM_TABLE_ADV,MUSHROOM_TABLE_ADV);
		ADVANCEMENT_MAP.put(DISENCHANT_TABLE_ADV,DISENCHANT_TABLE_ADV);
		ADVANCEMENT_MAP.put(MINING_ALLAY_ADV,MINING_ALLAY_ADV);
		ADVANCEMENT_MAP.put(COMBAT_ALLAY_ADV,COMBAT_ALLAY_ADV);
		ADVANCEMENT_MAP.put(CRYSTALIC_WARRIOR_ADV,CRYSTALIC_WARRIOR_ADV);
		ADVANCEMENT_MAP.put(DARK_SPIRIT_ADV,DARK_SPIRIT_ADV);
		ADVANCEMENT_MAP.put(LORD_OF_FURIES_ADV,LORD_OF_FURIES_ADV);
		ADVANCEMENT_MAP.put(CRYSTALIC_SWORD_ADV,CRYSTALIC_SWORD_ADV);
		ADVANCEMENT_MAP.put(SCULK_NECROMANCER_ADV,SCULK_NECROMANCER_ADV);
		ADVANCEMENT_MAP.put(CAVERN_OF_DEEP_ADV,CAVERN_OF_DEEP_ADV);
		ADVANCEMENT_MAP.put(PEACEFUL_WASTELAND_ADV,PEACEFUL_WASTELAND_ADV);
		ADVANCEMENT_MAP.put(CRYSTALIC_FOREST_ADV,CRYSTALIC_FOREST_ADV);
		ADVANCEMENT_MAP.put(MAGMA_CAVERNS_ADV,MAGMA_CAVERNS_ADV);
		ADVANCEMENT_MAP.put(SCULK_VALLEY_ADV,SCULK_VALLEY_ADV);
		ADVANCEMENT_MAP.put(SHINY_VALLEY_ADV,SHINY_VALLEY_ADV);
		ADVANCEMENT_MAP.put(CRYSTAL_SHARD_ADV,CRYSTAL_SHARD_ADV);
		ADVANCEMENT_MAP.put(DARKNIUM_INGOT_ADV,DARKNIUM_INGOT_ADV);
		ADVANCEMENT_MAP.put(FURY_INGOT_ADV,FURY_INGOT_ADV);
		ADVANCEMENT_MAP.put(LORD_FURY_SCALES_ADV,LORD_FURY_SCALES_ADV);
		ADVANCEMENT_MAP.put(MORE_COLORS_ADV,MORE_COLORS_ADV);
		ADVANCEMENT_MAP.put(EASTER_DARK_SPIRIT_ADV,EASTER_DARK_SPIRIT_ADV);
	}
	public static void addAdvancement(Player player, ResourceLocation location) {
		ResourceLocation tmpLocation = ADVANCEMENT_MAP.get(location);
		if (tmpLocation != null && player instanceof ServerPlayer serverPlayer) {
			awardAdvancement(serverPlayer, tmpLocation);
		}
	}
	private static void awardAdvancement(ServerPlayer player, ResourceLocation advancementLocation) {
		Advancement advancement = player.server.getAdvancements().getAdvancement(advancementLocation);
		AdvancementProgress ap = player.getAdvancements().getOrStartProgress(advancement);
		if (!ap.isDone()) {
			for (String criteria : ap.getRemainingCriteria()) {
				player.getAdvancements().award(advancement, criteria);
			}
		}
	}
	public static void tickCheckingAdvancements(Player player) {
		if (player == null)
			return;
		if (player.getInventory().contains(new ItemStack(VariousWorldItems.DARKNIUM_INGOT.get()))) {
			addAdvancement(player,DARKNIUM_INGOT_ADV);
		}
		if ((player.getInventory().contains(new ItemStack(VariousWorldItems.CRYSTALSHARD.get())))
				|| (player.getInventory().contains(new ItemStack(VariousWorldItems.DARKSHARD.get())))) {
			addAdvancement(player,CRYSTAL_SHARD_ADV);
		}
		if (player.getInventory().contains(new ItemStack(VariousWorldItems.FURY_INGOT.get()))) {
			addAdvancement(player,FURY_INGOT_ADV);
		}
		if (player.getInventory().contains(new ItemStack(VariousWorldItems.LORD_FURY_SCALE.get()))) {
			addAdvancement(player,LORD_FURY_SCALES_ADV);
		}
	}
}
