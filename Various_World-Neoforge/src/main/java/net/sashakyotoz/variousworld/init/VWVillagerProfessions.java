package net.sashakyotoz.variousworld.init;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class VWVillagerProfessions {
//    private static final Map<String, ProfessionPoiType> POI_TYPES = new HashMap<>();
//    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, VariousWorld.MODID);
//    public static final DeferredHolder<VillagerProfession, ?> CRYSTALLOGRAPHER = registerProfession("crystallographer", VWBlocks.ARTIFACTTABLE::get,
//            () -> SoundEvents.AMETHYST_BLOCK_PLACE);
//    public static final DeferredHolder<VillagerProfession, ?> MYCOLOGIST = registerProfession("mycologist", VWBlocks.MYCOLOCYFAROGRAPH::get,
//            () -> SoundEvents.MOOSHROOM_CONVERT);
//
//    private static DeferredHolder<VillagerProfession, ?> registerProfession(String name, Supplier<Block> block, Supplier<SoundEvent> soundEvent) {
//        POI_TYPES.put(name, new ProfessionPoiType(block, null));
//        return PROFESSIONS.register(name, () -> {
//            Predicate<Holder<PoiType>> poiPredicate = poiTypeHolder -> (POI_TYPES.get(name).poiType != null) && (poiTypeHolder.getKey() == POI_TYPES.get(name).poiType.getKey());
//            return new VillagerProfession(VariousWorld.MODID + ":" + name, poiPredicate, poiPredicate, ImmutableSet.of(), ImmutableSet.of(), soundEvent.get());
//        });
//    }
//
//    @SubscribeEvent
//    public static void registerProfessionPointsOfInterest(RegisterEvent event) {
//        event.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE.key(), registerHelper -> {
//            for (Map.Entry<String, ProfessionPoiType> entry : POI_TYPES.entrySet()) {
//                Block block = entry.getValue().block.get();
//                String name = entry.getKey();
//                Optional<Holder<PoiType>> existingCheck = PoiTypes.forState(block.defaultBlockState());
//                if (existingCheck.isPresent()) {
//                    VariousWorld.LOGGER.error("Skipping villager profession {} that uses POI block {} that is already in use by {}", name, block, existingCheck);
//                    continue;
//                }
////				PoiType poiType = new PoiType(ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates()), 1, 1);
////				registerHelper.register(name, poiType);
////				entry.getValue().poiType = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(poiType).get();
//            }
//        });
//    }
//
//    private static class ProfessionPoiType {
//        final Supplier<Block> block;
//        Holder<PoiType> poiType;
//
//        ProfessionPoiType(Supplier<Block> block, Holder<PoiType> poiType) {
//            this.block = block;
//            this.poiType = poiType;
//        }
//    }
}
