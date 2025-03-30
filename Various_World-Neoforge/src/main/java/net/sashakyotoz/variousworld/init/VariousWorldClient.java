package net.sashakyotoz.variousworld.init;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.block.AnthuriumSproutedOfMagmaBlock;
import net.sashakyotoz.variousworld.block.CrystalLikeBlock;
import net.sashakyotoz.variousworld.block.SmallSculkBushBlock;
import net.sashakyotoz.variousworld.block.entity.renderer.ArmorStationBlockEntityRenderer;
import net.sashakyotoz.variousworld.block.entity.renderer.DisenchantTableBlockEntityRenderer;
import net.sashakyotoz.variousworld.client.model.*;
import net.sashakyotoz.variousworld.client.particle.*;
import net.sashakyotoz.variousworld.client.renderer.*;
import net.sashakyotoz.variousworld.client.renderer.layers.AmethystSpikesEffectLayer;
import net.sashakyotoz.variousworld.client.renderer.layers.AngelStarWingsLayer;
import net.sashakyotoz.variousworld.client.renderer.layers.ChainedEffectLayer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VariousWorldClient {
    private static final ModelLayerLocation CRYSTALIC_OAK_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MODID,"boat/crystalic_oak"),"main");
    private static final ModelLayerLocation CRYSTALIC_OAK_CHEST_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MODID,"chest_boat/crystalic_oak"),"main");
    private static final ModelLayerLocation MAGNOLIA_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MODID,"boat/magnolia"),"main");
    private static final ModelLayerLocation MAGNOLIA_CHEST_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MODID,"chest_boat/magnolia"),"main");
    private static final ModelLayerLocation SCULK_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MODID,"boat/sculk"),"main");
    private static final ModelLayerLocation SCULK_CHEST_BOAT_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VariousWorld.MODID,"chest_boat/sculk"),"main");
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(VWEntities.CRYSTALIC_BOW.get(), CrystalicArrowRenderer::new);
        event.registerEntityRenderer(VWEntities.LORD_OF_FURIES_CROSSBOW.get(), CrystalicArrowRenderer::new);
        event.registerEntityRenderer(VWEntities.ZOMBIE_OF_VARIOUS_BIOMES.get(), ZombieOfVariousBiomesRenderer::new);
        event.registerEntityRenderer(VWEntities.SCULK_SKELETON.get(), SculkSkeletonRenderer::new);
        event.registerEntityRenderer(VWEntities.DARK_FURY.get(), DarkFuryRenderer::new);
        event.registerEntityRenderer(VWEntities.CRYSTALIC_SLIME.get(), CrystalicSlimeRenderer::new);
        event.registerEntityRenderer(VWEntities.SPIRITOF_PEACEFUL_WASTELAND.get(), SpiritofPeacefulWastelandRenderer::new);
        event.registerEntityRenderer(VWEntities.SPIRITOF_DEEP_CAVERN.get(), SpiritofDeepCavernRenderer::new);
        event.registerEntityRenderer(VWEntities.ARMORED_SKELETON.get(), ArmoredSkeletonRenderer::new);
        event.registerEntityRenderer(VWEntities.DROMOPHANT.get(), DromophantRenderer::new);
        event.registerEntityRenderer(VWEntities.WANDERING_SPIRIT_SUMMONED_OF_SCULKS.get(), WanderingSpiritSummonedOfSculksRenderer::new);
        event.registerEntityRenderer(VWEntities.ZOMBIE_OF_STONY_MAGMA.get(), ZombieOfStonyMagmaRenderer::new);
        event.registerEntityRenderer(VWEntities.ZANY_VILER_WITCH.get(), ZanyVilerWitchRenderer::new);
        event.registerEntityRenderer(VWEntities.CRYSTAL_WARRIOR.get(), CrystalWarriorRenderer::new);
        event.registerEntityRenderer(VWEntities.DARK_SPIRIT.get(), DarkSpiritRenderer::new);
        event.registerEntityRenderer(VWEntities.SCULK_NECROMANCER_SKELETON.get(), SculkNecromancerSkeletonRenderer::new);
        event.registerEntityRenderer(VWEntities.FURY_LORD.get(), FuryLordRenderer::new);
        event.registerEntityRenderer(VWEntities.SCULK_SCYTHE_PROJECTILE.get(), SculkScytheProjectileRenderer::new);
        event.registerEntityRenderer(VWEntities.WANDERING_SPIRIT_PROJECTILE.get(), WanderingSpiritProjectileRenderer::new);
        event.registerEntityRenderer(VWEntities.NECROMANCER_STAFF.get(), NecromancerStaffRenderer::new);
        event.registerEntityRenderer(VWEntities.MULTIPLE_ENDER_PEARL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(VWEntities.DARK_SPIRIT_GLOVES.get(), DarkSpiritGlovesRenderer::new);
        //boats' renderer
        event.registerEntityRenderer(VWEntities.MOD_BOAT.get(), context->new ModBoatRenderer(context,false));
        event.registerEntityRenderer(VWEntities.MOD_CHEST_BOAT.get(), context->new ModBoatRenderer(context,true));
        //block entities' renderer
        event.registerBlockEntityRenderer(VWBlockEntities.DISENCHANT_TABLE.get(), DisenchantTableBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(VWBlockEntities.ARMOR_STATION_BLOCK.get(), ArmorStationBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(VWBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(VWBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
    @SubscribeEvent
    public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
        CrystalLikeBlock.blockColorLoad(event);
        SmallSculkBushBlock.blockColorLoad(event);
        AnthuriumSproutedOfMagmaBlock.blockColorLoad(event);
    }

    @SubscribeEvent
    public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
        AnthuriumSproutedOfMagmaBlock.itemColorLoad(event);
    }
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(VWMiscRegistries.PEACEFUL_PARTICLE.get(), PeacefulParticleParticle::provider);
        event.registerSpriteSet(VWMiscRegistries.WANDERING_SPIRIT_PROJECTILE_PARTICLE.get(), WanderingSpiritAbilityShootParticleParticle::provider);
        event.registerSpriteSet(VWMiscRegistries.LORD_SHOOT_PARTICLE.get(), LordShootParticleParticle::provider);
        event.registerSpriteSet(VWMiscRegistries.MAGMA_FIREFLIES.get(), MagmaFirefliesParticle::provider);
    }       

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelFuryLord.LAYER_LOCATION, ModelFuryLord::createBodyLayer);
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
        event.registerLayerDefinition(ModelSculkNecromancerSkeleton.LAYER_LOCATION, ModelSculkNecromancerSkeleton::createBodyLayer);
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
        event.registerLayerDefinition(ModelFuryLordAdvanced.LAYER_LOCATION, ModelFuryLordAdvanced::createBodyLayer);
        event.registerLayerDefinition(ModelSculkArmorTop.LAYER_LOCATION, ModelSculkArmorTop::createBodyLayer);
        event.registerLayerDefinition(ModelSculkArmorBottom.LAYER_LOCATION, ModelSculkArmorBottom::createBodyLayer);
        event.registerLayerDefinition(ModelWanderingSpirit.LAYER_LOCATION, ModelWanderingSpirit::createBodyLayer);
        event.registerLayerDefinition(ModelFuryArmor.LAYER_LOCATION, ModelFuryArmor::createBodyLayer);
        event.registerLayerDefinition(ModelLordFuryTop.LAYER_LOCATION, ModelLordFuryTop::createBodyLayer);
        event.registerLayerDefinition(ModelSpirit_of_Deep_Cavern.LAYER_LOCATION, ModelSpirit_of_Deep_Cavern::createBodyLayer);
        event.registerLayerDefinition(ModelZombie_Stony_Magma.LAYER_LOCATION, ModelZombie_Stony_Magma::createBodyLayer);
        event.registerLayerDefinition(ModelDromophant.LAYER_LOCATION, ModelDromophant::createBodyLayer);
        event.registerLayerDefinition(CRYSTALIC_OAK_BOAT_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(CRYSTALIC_OAK_CHEST_BOAT_LOCATION, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(MAGNOLIA_BOAT_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(MAGNOLIA_CHEST_BOAT_LOCATION, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(SCULK_BOAT_LOCATION, BoatModel::createBodyModel);
        event.registerLayerDefinition(SCULK_CHEST_BOAT_LOCATION, ChestBoatModel::createBodyModel);
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
