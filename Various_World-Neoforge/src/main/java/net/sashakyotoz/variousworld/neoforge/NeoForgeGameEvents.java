package net.sashakyotoz.variousworld.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.VWTrades;

public class NeoForgeGameEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        OnActionsTrigger.onUseBlock(event.getEntity(), event.getLevel(), event.getPos(), event.getItemStack());
    }

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        OnActionsTrigger.injectOverworldBiomes(event.getServer());
    }

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        VWTrades.fill(event.getType(), (level, listing) -> event.getTrades().get(level.intValue()).add(listing));
    }
}