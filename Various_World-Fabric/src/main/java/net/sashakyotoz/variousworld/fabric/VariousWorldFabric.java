package net.sashakyotoz.variousworld.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.GemsmithingHelper;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.VWCauldronInteractions;
import net.sashakyotoz.variousworld.common.VWTrades;
import net.sashakyotoz.variousworld.common.entities.boat.VWBoatDispenseItemBehavior;
import net.sashakyotoz.variousworld.common.world.features.ModPlacedFeatures;
import net.sashakyotoz.variousworld.init.VWBiomes;
import net.sashakyotoz.variousworld.init.VWMiscRegistries;
import net.sashakyotoz.variousworld.init.VWVillagers;
import net.sashakyotoz.variousworld.init.holder.VWHolders;
import net.sashakyotoz.variousworld.mixin.fabric.PoiTypesInvoker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VariousWorldFabric implements ModInitializer {
    private static boolean tradesRegistered;

    @Override
    public void onInitialize() {
        VariousWorld.init();
        if (FabricBootstrapState.registriesBootstrapped())
            SolLibFabricFallback.registerCommon();

        bindPoiStates(VWVillagers.GEMSMITHER_POI);
        bindPoiStates(VWVillagers.ARTIFACTOLOGIST_POI);
        VWCauldronInteractions.register();
        VWVillagers.putTypeToBiome(VWBiomes.CRYSTALLINE_FOREST, VWVillagers.CRYSTALLINE.get());
        VWBoatDispenseItemBehavior.register();

        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.SODALITE_GEODE);

        DefaultItemComponentEvents.MODIFY.register(context -> context.modify(GemsmithingHelper::isGemmable,
                (builder, item) -> builder.set(VWMiscRegistries.CRYSTAL_DATA.get(), OnActionsTrigger.defaultCrystalData())));

        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (!player.isSpectator())
                OnActionsTrigger.onUseBlock(player, level, hitResult.getBlockPos(), player.getItemInHand(hand));
            return InteractionResult.PASS;
        });

        ServerLifecycleEvents.SERVER_STARTING.register(VariousWorldFabric::onServerStarting);
    }

    private static void onServerStarting(MinecraftServer server) {
        registerTrades();
        OnActionsTrigger.injectOverworldBiomes(server);
    }

    private static synchronized void registerTrades() {
        if (tradesRegistered)
            return;
        tradesRegistered = true;
        for (VillagerProfession profession : List.of(VWVillagers.GEMSMITHER.get(), VWVillagers.ARTIFACTOLOGIST.get())) {
            for (int level = 1; level <= 5; level++) {
                int tradeLevel = level;
                TradeOfferHelper.registerVillagerOffers(profession, tradeLevel, (factories, rebalanced) ->
                        VWTrades.fill(profession, (listingLevel, listing) -> {
                            if (listingLevel == tradeLevel)
                                factories.add(listing);
                        }));
            }
        }
    }

    private static void bindPoiStates(VWHolders.PoiTypeHolder holder) {
        Holder<PoiType> poi = holder.getAsHolder();
        Set<BlockState> unbound = new HashSet<>();
        for (BlockState state : holder.get().matchingStates()) {
            if (PoiTypes.forState(state).isEmpty())
                unbound.add(state);
        }
        if (!unbound.isEmpty())
            PoiTypesInvoker.variousworld$registerBlockStates(poi, unbound);
    }
}
