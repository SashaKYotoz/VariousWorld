package net.sashakyotoz.variousworld.init;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sashakyotoz.variousworld.block.entity.renderer.ArmorStationBlockEntityRenderer;
import net.sashakyotoz.variousworld.block.entity.renderer.DisenchantTableBlockEntityRenderer;
import net.sashakyotoz.variousworld.client.model.*;
import net.sashakyotoz.variousworld.client.particle.*;
import net.sashakyotoz.variousworld.client.renderer.*;
import net.sashakyotoz.variousworld.client.renderer.layers.AmethystSpikesEffectLayer;
import net.sashakyotoz.variousworld.client.renderer.layers.AngelStarWingsLayer;
import net.sashakyotoz.variousworld.client.renderer.layers.ChainedEffectLayer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VariousWorldModClient {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(VariousWorldModEntities.CRYSTALIC_BOW.get(), CrystalicArrowRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.LORD_OF_FURIES_CROSSBOW.get(), CrystalicArrowRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.ZOMBIE_OF_VARIOUS_BIOMES.get(), ZombieOfVariousBiomesRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.SCULK_SKELETON.get(), SculkSkeletonRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.DARK_FURY.get(), DarkFuryRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.CRYSTALIC_SLIME.get(), CrystalicSlimeRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.SPIRITOF_PEACEFUL_WASTELAND.get(), SpiritofPeacefulWastelandRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.SPIRITOF_DEEP_CAVERN.get(), SpiritofDeepCavernRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.ARMORED_SKELETON.get(), ArmoredSkeletonRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.DROMOPHANT.get(), DromophantRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), WanderingSpiritSummonedOfSculksRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.ZOMBIE_OF_STONY_MAGMA.get(), ZombieOfStonyMagmaRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.ZANY_VILER_WITCH.get(), ZanyVilerWitchRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.CRYSTAL_WARRIOR.get(), CrystalWarriorRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.DARK_SPIRIT.get(), DarkSpiritRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.SCULK_NECROMANCER_SKELETON.get(), SculkNecromancerSkeletonRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.FURY_LORD.get(), FuryLordRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.SCULK_SCYTHE_PROJECTILE.get(), SculkScytheProjectileRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.WANDERING_SPIRIT_PROJECTILE.get(), WanderingSpiritProjectileRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.NECROMANCER_STAFF.get(), NecromancerStaffRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.MULTIPLE_ENDER_PEARL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(VariousWorldModEntities.DARK_SPIRIT_GLOVES.get(), DarkSpiritGlovesRenderer::new);
        event.registerBlockEntityRenderer(VariousWorldModBlockEntities.DISENCHANT_TABLE.get(), DisenchantTableBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(VariousWorldModBlockEntities.ARMOR_STATION_BLOCK.get(), ArmorStationBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(VariousWorldModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(VariousWorldModParticleTypes.PEACEFUL_PARTICLE.get(), PeacefulParticleParticle::provider);
        event.registerSpriteSet(VariousWorldModParticleTypes.WANDERING_SPIRIT_PROJECTILE_PARTICLE.get(), WanderingSpiritAbilityShootParticleParticle::provider);
        event.registerSpriteSet(VariousWorldModParticleTypes.LORD_SHOOT_PARTICLE.get(), LordShootParticleParticle::provider);
        event.registerSpriteSet(VariousWorldModParticleTypes.MAGMA_FIREFLIES.get(), MagmaFirefliesParticle::provider);
    }       

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelFury_Lord.LAYER_LOCATION, ModelFury_Lord::createBodyLayer);
        event.registerLayerDefinition(ModelZany_Viler_Witch.LAYER_LOCATION, ModelZany_Viler_Witch::createBodyLayer);
        event.registerLayerDefinition(ModelDark_Spirit_Boom.LAYER_LOCATION, ModelDark_Spirit_Boom::createBodyLayer);
        event.registerLayerDefinition(ModelCrystalic_Slime.LAYER_LOCATION, ModelCrystalic_Slime::createInnerBodyLayer);
        event.registerLayerDefinition(ModelCrystalic_Slime.OUTER_LAYER_LOCATION, ModelCrystalic_Slime::createOuterBodyLayer);
        event.registerLayerDefinition(ModelChained_Projectile.LAYER_LOCATION, ModelChained_Projectile::createBodyLayer);
        event.registerLayerDefinition(ModelLordFuryBottom.LAYER_LOCATION, ModelLordFuryBottom::createBodyLayer);
        event.registerLayerDefinition(ModelVariousArmoredSkeleton.LAYER_LOCATION, ModelVariousArmoredSkeleton::createBodyLayer);
        event.registerLayerDefinition(ModelVariousArmoredSkeleton.OUTER_LAYER_LOCATION, ModelVariousArmoredSkeleton::createBodyLayer);
        event.registerLayerDefinition(ModelCrystalicWarrior.LAYER_LOCATION, ModelCrystalicWarrior::createBodyLayer);
        event.registerLayerDefinition(ModelSculk_Skeleton.LAYER_LOCATION, ModelSculk_Skeleton::createBodyLayer);
        event.registerLayerDefinition(ModelSculk_Necromancer_Skeleton.LAYER_LOCATION, ModelSculk_Necromancer_Skeleton::createBodyLayer);
        event.registerLayerDefinition(ModelSpiritOfTheDark.LAYER_LOCATION, ModelSpiritOfTheDark::createBodyLayer);
        event.registerLayerDefinition(ModelChained.LAYER_LOCATION, ModelChained::createBodyLayer);
        event.registerLayerDefinition(ModelAmethystSpikes.LAYER_LOCATION, ModelAmethystSpikes::createBodyLayer);
        event.registerLayerDefinition(ModelSpirit_of_Peaceful_Wasteland.LAYER_LOCATION, ModelSpirit_of_Peaceful_Wasteland::createBodyLayer);
        event.registerLayerDefinition(ModelZombie_of_Various_Biomes.LAYER_LOCATION, ModelZombie_of_Various_Biomes::createBodyLayer);
        event.registerLayerDefinition(ModelAngelArmorTop.LAYER_LOCATION, ModelAngelArmorTop::createBodyLayer);
        event.registerLayerDefinition(ModelAngelArmorWings.LAYER_LOCATION, ModelAngelArmorWings::createBodyLayer);
        event.registerLayerDefinition(ModelAngelArmorBottom.LAYER_LOCATION, ModelAngelArmorBottom::createBodyLayer);
        event.registerLayerDefinition(ModelDark_Fury.LAYER_LOCATION, ModelDark_Fury::createBodyLayer);
        event.registerLayerDefinition(ModelProjectileCycle.LAYER_LOCATION, ModelProjectileCycle::createBodyLayer);
        event.registerLayerDefinition(ModelFury_Lord_Advanced.LAYER_LOCATION, ModelFury_Lord_Advanced::createBodyLayer);
        event.registerLayerDefinition(ModelSculkArmorTop.LAYER_LOCATION, ModelSculkArmorTop::createBodyLayer);
        event.registerLayerDefinition(ModelSculkArmorBottom.LAYER_LOCATION, ModelSculkArmorBottom::createBodyLayer);
        event.registerLayerDefinition(ModelWanderingSpirit.LAYER_LOCATION, ModelWanderingSpirit::createBodyLayer);
        event.registerLayerDefinition(ModelFuryArmor.LAYER_LOCATION, ModelFuryArmor::createBodyLayer);
        event.registerLayerDefinition(ModelLordFuryTop.LAYER_LOCATION, ModelLordFuryTop::createBodyLayer);
        event.registerLayerDefinition(ModelSpirit_of_Deep_Cavern.LAYER_LOCATION, ModelSpirit_of_Deep_Cavern::createBodyLayer);
        event.registerLayerDefinition(ModelZombie_Stony_Magma.LAYER_LOCATION, ModelZombie_Stony_Magma::createBodyLayer);
        event.registerLayerDefinition(ModelDromophant.LAYER_LOCATION, ModelDromophant::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.AddLayers event) {
        EntityModelSet entityModels = event.getEntityModels();
        event.getSkins().forEach((s) -> {
            LivingEntityRenderer<? extends Player, ? extends EntityModel<? extends Player>> livingEntityRenderer = event.getSkin(s);
            if (livingEntityRenderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new AngelStarWingsLayer<>(playerRenderer, entityModels));
                playerRenderer.addLayer(new AmethystSpikesEffectLayer<>(playerRenderer, entityModels));
                playerRenderer.addLayer(new ChainedEffectLayer<>(playerRenderer, entityModels));
            }
        });
    }
}
