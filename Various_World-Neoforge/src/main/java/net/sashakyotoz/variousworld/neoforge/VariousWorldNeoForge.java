package net.sashakyotoz.variousworld.neoforge;

import net.lcc.sollib.api.common.registry.data.entity.SpawnRestrictions;
import net.lcc.sollib.api.common.registry.holder.EntityHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.common.OnActionsTrigger;
import net.sashakyotoz.variousworld.common.VWCauldronInteractions;
import net.sashakyotoz.variousworld.common.config.ModConfigController;
import net.sashakyotoz.variousworld.init.*;

@Mod(VariousWorld.MOD_ID)
public class VariousWorldNeoForge {
    public VariousWorldNeoForge(IEventBus bus, ModContainer container) {
        VariousWorld.init();
        bus.addListener(VariousWorldNeoForge::commonSetup);
        bus.addListener(VariousWorldNeoForge::registerCapabilities);
        bus.addListener(VariousWorldNeoForge::registerSpawnPlacements);
        bus.addListener(VariousWorldNeoForge::modifyDefaultComponents);
        NeoForge.EVENT_BUS.register(NeoForgeGameEvents.class);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModConfigController.init();
            VWCauldronInteractions.register();
            VWVillagers.putTypeToBiome(VWBiomes.CRYSTALLINE_FOREST, VWVillagers.CRYSTALLINE.get());
            FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
            pot.addPlant(VWBlocks.SODALITE_WART.getID(), VWBlocks.POTTED_SODALITE_WART);
            pot.addPlant(VWBlocks.CRYSTALIC_OAK_SAPLING.getID(), VWBlocks.POTTED_CRYSTALIC_OAK_SAPLING);
            pot.addPlant(VWBlocks.BLUE_JACARANDA_SAPLING.getID(), VWBlocks.POTTED_BLUE_JACARANDA_SAPLING);
        });
    }

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, VWBlocks.GEMSMITH_TABLE_BE.get(), (table, side) -> new InvWrapper(table));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, VWBlocks.ARTIFACT_TABLE_BE.get(), (table, side) -> new InvWrapper(table));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, VWBlocks.DISASSEMBLY_TABLE_BE.get(), (table, side) -> new InvWrapper(table));
    }

    private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        VWRegistryHelper.forEach(EntityHolder.class, holder -> {
            if (holder.hasSpawnRestrictions())
                registerSpawnPlacement(event, holder);
        });
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event, EntityHolder holder) {
        SpawnRestrictions restrictions = holder.getSpawnRestrictions();
        event.register((EntityType) holder.get(), restrictions.location(), restrictions.heightmap(), restrictions.predicate(),
                RegisterSpawnPlacementsEvent.Operation.AND);
    }

    private static void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        event.modifyMatching(item -> item instanceof TieredItem,
                builder -> builder.set(VWMiscRegistries.CRYSTAL_DATA.get(), OnActionsTrigger.defaultCrystalData()));
    }
}
