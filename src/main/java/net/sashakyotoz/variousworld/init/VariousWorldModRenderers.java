package net.sashakyotoz.variousworld.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.sashakyotoz.variousworld.client.renderer.ZombieOfStonyMagmaRenderer;
import net.sashakyotoz.variousworld.client.renderer.ZanyVilerWitchRenderer;
import net.sashakyotoz.variousworld.client.renderer.WanderingSpiritSummonedOfSculksRenderer;
import net.sashakyotoz.variousworld.client.renderer.WanderingSpiritAbilityShootRenderer;
import net.sashakyotoz.variousworld.client.renderer.SpiritofPeacefulWastelandRenderer;
import net.sashakyotoz.variousworld.client.renderer.SpiritofDeepCavernRenderer;
import net.sashakyotoz.variousworld.client.renderer.SculkSkeletonRenderer;
import net.sashakyotoz.variousworld.client.renderer.SculkScytheRangedItemRenderer;
import net.sashakyotoz.variousworld.client.renderer.SculkNecromancerSkeletonRenderer;
import net.sashakyotoz.variousworld.client.renderer.NecromancerStaffRenderer;
import net.sashakyotoz.variousworld.client.renderer.FuryLordRenderer;
import net.sashakyotoz.variousworld.client.renderer.ZombieOfVariousBiomesRenderer;
import net.sashakyotoz.variousworld.client.renderer.DarkSpiritRenderer;
import net.sashakyotoz.variousworld.client.renderer.DarkSpiritGlovesRenderer;
import net.sashakyotoz.variousworld.client.renderer.DarkFuryRenderer;
import net.sashakyotoz.variousworld.client.renderer.CrystalicSlimeRenderer;
import net.sashakyotoz.variousworld.client.renderer.CrystalWarriorRenderer;
import net.sashakyotoz.variousworld.client.renderer.ArmoredskeletonRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VariousWorldModRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(VariousWorldModEntities.CRYSTALIC_BOW.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.LORD_OF_FURIES_CROSSBOW.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.ZOMBIE_OF_VARIOUS_BIOMES.get(), ZombieOfVariousBiomesRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.SCULK_SKELETON.get(), SculkSkeletonRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.DARK_FURY.get(), DarkFuryRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.CRYSTALIC_SLIME.get(), CrystalicSlimeRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.SPIRITOF_PEACEFUL_WASTELAND.get(), SpiritofPeacefulWastelandRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.SPIRITOF_DEEP_CAVERN.get(), SpiritofDeepCavernRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.ARMOREDSKELETON.get(), ArmoredskeletonRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), WanderingSpiritSummonedOfSculksRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.ZOMBIE_OF_STONY_MAGMA.get(), ZombieOfStonyMagmaRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.ZANY_VILER_WITCH.get(), ZanyVilerWitchRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.CRYSTAL_WARRIOR.get(), CrystalWarriorRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.DARK_SPIRIT.get(), DarkSpiritRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.SCULK_NECROMANCER_SKELETON.get(), SculkNecromancerSkeletonRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.FURY_LORD.get(), FuryLordRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.SCULK_SCYTHE_RANGED_ITEM.get(), SculkScytheRangedItemRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.WANDERING_SPIRIT_ABILITY_SHOOT.get(), WanderingSpiritAbilityShootRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.NECROMANCER_STAFF.get(), NecromancerStaffRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.MULTIPLE_ENDER_PEARL.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(VariousWorldModEntities.DARK_SPIRIT_GLOVES.get(), DarkSpiritGlovesRenderer::new);
	}
}
