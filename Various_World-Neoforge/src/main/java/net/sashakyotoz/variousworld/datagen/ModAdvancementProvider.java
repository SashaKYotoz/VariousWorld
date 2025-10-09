package net.sashakyotoz.variousworld.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.init.VWBiomes;
import net.sashakyotoz.variousworld.init.VWBlocks;
import net.sashakyotoz.variousworld.init.VWItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    private static final class ModAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            Advancement.Builder builder = Advancement.Builder.advancement();
            AdvancementHolder advancement = addBiomes(builder.display(VWBlocks.BLUE_JACARANDA_SAPLING.toStack(),
                                    Component.translatable("advancement.various_world.across_the_world"), Component.translatable("advancement.various_world.across_the_world.desc"),
                                    VariousWorld.createVWLocation("textures/block/blue_jacaranda_leaves.png"), AdvancementType.TASK, true, true, true)
                            .requirements(AdvancementRequirements.anyOf(List.of("various_world:blue_jacaranda_meadow", "various_world:crystalline_forest", "various_world:reclaimite_caves")))
                            .rewards(AdvancementRewards.Builder.experience(10)),
                    provider, List.of(VWBiomes.BLUE_JACARANDA_MEADOW, VWBiomes.CRYSTALLINE_FOREST, VWBiomes.RECLAIMITE_CAVES)).save(consumer, VariousWorld.createVWLocation("across_the_world"), existingFileHelper);
            builder = Advancement.Builder.advancement();
            addBiomes(builder.display(VWBlocks.SODALITE_WART.toStack(),
                                    Component.translatable("advancement.various_world.master_of_various_world"), Component.translatable("advancement.various_world.master_of_various_world.desc"), null, AdvancementType.CHALLENGE, true, true, false)
                            .parent(advancement)
                            .addCriterion("sodalite_shard", InventoryChangeTrigger.TriggerInstance.hasItems(VWItems.SODALITE_SHARD.get()))
                            .addCriterion("crystalline_slime_ball", InventoryChangeTrigger.TriggerInstance.hasItems(VWItems.CRYSTALLINE_SLIME_BALL.get()))
                            .addCriterion("reclaimite_shard", InventoryChangeTrigger.TriggerInstance.hasItems(VWItems.RECLAIMITE_SHARD.get()))
                            .addCriterion("jacaranda_petals", InventoryChangeTrigger.TriggerInstance.hasItems(VWBlocks.BLUE_JACARANDA_PETALS.get()))
                            .requirements(AdvancementRequirements.allOf(
                                    List.of("various_world:blue_jacaranda_meadow", "various_world:crystalline_forest", "various_world:reclaimite_caves", "sodalite_shard", "crystalline_slime_ball", "reclaimite_shard", "jacaranda_petals")))
                            .rewards(AdvancementRewards.Builder.experience(30)),
                    provider, List.of(VWBiomes.BLUE_JACARANDA_MEADOW, VWBiomes.CRYSTALLINE_FOREST, VWBiomes.RECLAIMITE_CAVES)).save(consumer, VariousWorld.createVWLocation("master_of_various_world"), existingFileHelper);
            builder = Advancement.Builder.advancement();
            builder.display(VWBlocks.ARTIFACT_TABLE.toStack(),
                            Component.translatable("advancement.various_world.tabled"), Component.translatable("advancement.various_world.tabled.desc"), null, AdvancementType.GOAL, true, true, false)
                    .parent(advancement)
                    .addCriterion("disassembly_table", InventoryChangeTrigger.TriggerInstance.hasItems(VWBlocks.DISASSEMBLY_TABLE.get()))
                    .addCriterion("artifact_table", InventoryChangeTrigger.TriggerInstance.hasItems(VWBlocks.ARTIFACT_TABLE.get()))
                    .addCriterion("gemsmith_table", InventoryChangeTrigger.TriggerInstance.hasItems(VWBlocks.GEMSMITH_TABLE.get()))
                    .requirements(AdvancementRequirements.allOf(List.of("disassembly_table","artifact_table","gemsmith_table"))).save(consumer, VariousWorld.createVWLocation("tabled"), existingFileHelper);
        }

        private Advancement.Builder addBiomes(Advancement.Builder builder, HolderLookup.Provider levelRegistry, List<ResourceKey<Biome>> biomes) {
            HolderGetter<Biome> holdergetter = levelRegistry.lookupOrThrow(Registries.BIOME);
            for (ResourceKey<Biome> resourcekey : biomes)
                builder.addCriterion(resourcekey.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(holdergetter.getOrThrow(resourcekey))));
            return builder;
        }
    }
}