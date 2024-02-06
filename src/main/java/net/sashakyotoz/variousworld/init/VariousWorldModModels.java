
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sashakyotoz.variousworld.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.sashakyotoz.variousworld.client.model.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class VariousWorldModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelFury_Lord.LAYER_LOCATION, ModelFury_Lord::createBodyLayer);
		event.registerLayerDefinition(ModelZany_Viler_Witch.LAYER_LOCATION, ModelZany_Viler_Witch::createBodyLayer);
		event.registerLayerDefinition(ModelDark_Spirit_Boom.LAYER_LOCATION, ModelDark_Spirit_Boom::createBodyLayer);
		event.registerLayerDefinition(ModelCrystalic_Slime.LAYER_LOCATION, ModelCrystalic_Slime::createInnerBodyLayer);
		event.registerLayerDefinition(ModelCrystalic_Slime.OUTER_LAYER_LOCATION, ModelCrystalic_Slime::createOuterBodyLayer);
		event.registerLayerDefinition(ModelChained_Projectile.LAYER_LOCATION, ModelChained_Projectile::createBodyLayer);
		event.registerLayerDefinition(Modellord_fury_bottom.LAYER_LOCATION, Modellord_fury_bottom::createBodyLayer);
		event.registerLayerDefinition(ModelVarious_Armored_Skeleton.LAYER_LOCATION, ModelVarious_Armored_Skeleton::createBodyLayer);
		event.registerLayerDefinition(Modelcrystalic_warrior.LAYER_LOCATION, Modelcrystalic_warrior::createBodyLayer);
		event.registerLayerDefinition(ModelSculk_Skeleton.LAYER_LOCATION, ModelSculk_Skeleton::createBodyLayer);
		event.registerLayerDefinition(ModelLord_Fury_Armor.LAYER_LOCATION, ModelLord_Fury_Armor::createBodyLayer);
		event.registerLayerDefinition(ModelSculk_Necromancer_Skeleton.LAYER_LOCATION, ModelSculk_Necromancer_Skeleton::createBodyLayer);
		event.registerLayerDefinition(ModelSpirit_of_the_Dark.LAYER_LOCATION, ModelSpirit_of_the_Dark::createBodyLayer);
		event.registerLayerDefinition(ModelChained.LAYER_LOCATION, ModelChained::createBodyLayer);
		event.registerLayerDefinition(ModelAmethystSpikes.LAYER_LOCATION, ModelAmethystSpikes::createBodyLayer);
		event.registerLayerDefinition(ModelSpirit_of_Peaceful_Wasteland.LAYER_LOCATION, ModelSpirit_of_Peaceful_Wasteland::createBodyLayer);
		event.registerLayerDefinition(ModelZombie_of_Various_Biomes.LAYER_LOCATION, ModelZombie_of_Various_Biomes::createBodyLayer);
		event.registerLayerDefinition(ModelAngelArmorTop.LAYER_LOCATION, ModelAngelArmorTop::createBodyLayer);
		event.registerLayerDefinition(ModelAngel_Armor_Wings.LAYER_LOCATION, ModelAngel_Armor_Wings::createBodyLayer);
		event.registerLayerDefinition(ModelAngelArmorBottom.LAYER_LOCATION, ModelAngelArmorBottom::createBodyLayer);
		event.registerLayerDefinition(ModelDark_Fury.LAYER_LOCATION, ModelDark_Fury::createBodyLayer);
		event.registerLayerDefinition(ModelMagic_Ball.LAYER_LOCATION, ModelMagic_Ball::createBodyLayer);
		event.registerLayerDefinition(ModelFury_Lord_Advanced.LAYER_LOCATION, ModelFury_Lord_Advanced::createBodyLayer);
		event.registerLayerDefinition(ModelSculkArmorTop.LAYER_LOCATION, ModelSculkArmorTop::createBodyLayer);
		event.registerLayerDefinition(ModelSculkArmorBottom.LAYER_LOCATION, ModelSculkArmorBottom::createBodyLayer);
		event.registerLayerDefinition(ModelWandering_Spirit.LAYER_LOCATION, ModelWandering_Spirit::createBodyLayer);
		event.registerLayerDefinition(ModelFury_Armor.LAYER_LOCATION, ModelFury_Armor::createBodyLayer);
		event.registerLayerDefinition(Modellord_fury_top.LAYER_LOCATION, Modellord_fury_top::createBodyLayer);
		event.registerLayerDefinition(ModelSpirit_of_Deep_Cavern.LAYER_LOCATION, ModelSpirit_of_Deep_Cavern::createBodyLayer);
		event.registerLayerDefinition(ModelZombie_Stony_Magma.LAYER_LOCATION, ModelZombie_Stony_Magma::createBodyLayer);
	}
}
