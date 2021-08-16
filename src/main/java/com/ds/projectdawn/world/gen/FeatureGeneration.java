package com.ds.projectdawn.world.gen;

import com.ds.projectdawn.init.BlockInit;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureGeneration {

    public static ConfiguredFeature<?, ?> ORE_RUBY = register("ore_ruby", Feature.EMERALD_ORE.configured(new ReplaceBlockConfig(States.STONE, BlockInit.RUBY_ORE.get().defaultBlockState())).decorated(Placement.EMERALD_ORE.configured(IPlacementConfig.NONE)));
    public static ConfiguredFeature<?, ?> ARCANE_CRYSTAL = register("arcane_crystal", Feature.RANDOM_PATCH.configured(Configs.ARCANE_CRYSTAL_CONFIG).decorated(Placements.ARCANE_CRYSTAL_PLACEMENT).count(2));
    public static ConfiguredFeature<?, ?> ARCANE_SHRINE_STONE = register("arcane_shrine_stone", Feature.RANDOM_PATCH.configured(Configs.ARCANE_SHRINE_STONE_CONFIG).decorated(Placements.ARCANE_SHRINE_PLACEMENT).count(1));
    public static ConfiguredFeature<?, ?> ARCANE_SHRINE_GRANITE = register("arcane_shrine_granite", Feature.RANDOM_PATCH.configured(Configs.ARCANE_SHRINE_GRANITE_CONFIG).decorated(Placements.ARCANE_SHRINE_PLACEMENT).count(1));
    public static ConfiguredFeature<?, ?> ARCANE_SHRINE_DIORITE = register("arcane_shrine_diorite", Feature.RANDOM_PATCH.configured(Configs.ARCANE_SHRINE_DIORITE_CONFIG).decorated(Placements.ARCANE_SHRINE_PLACEMENT).count(1));
    public static ConfiguredFeature<?, ?> ARCANE_SHRINE_ANDESITE = register("arcane_shrine_andesite", Feature.RANDOM_PATCH.configured(Configs.ARCANE_SHRINE_ANDESITE_CONFIG).decorated(Placements.ARCANE_SHRINE_PLACEMENT).count(1));





    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    public static void buildOverWorldFeatures(BiomeGenerationSettings.Builder builder)
    {
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ARCANE_CRYSTAL);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ARCANE_SHRINE_STONE);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ARCANE_SHRINE_GRANITE);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ARCANE_SHRINE_DIORITE);
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ARCANE_SHRINE_ANDESITE);
    }

    public static void generateOverworldFeatures(final BiomeLoadingEvent event) {
        buildOverWorldFeatures(event.getGeneration());
    }

    public static void buildRubyOre(BiomeGenerationSettings.Builder builder)
    {
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_RUBY);
    }

    public static void generateRubyOre(final BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.Category.DESERT))
            buildRubyOre(event.getGeneration());
    }

    public static final class States
    {
        protected static final BlockState STONE = Blocks.STONE.defaultBlockState();
        protected static final BlockState ANDESITE = Blocks.ANDESITE.defaultBlockState();
        protected static final BlockState DIORITE = Blocks.DIORITE.defaultBlockState();
        protected static final BlockState GRANITE = Blocks.GRANITE.defaultBlockState();
        protected static final BlockState BLACKSTONE = Blocks.BLACKSTONE.defaultBlockState();
        protected static final BlockState OBSIDIAN = Blocks.OBSIDIAN.defaultBlockState();
        protected static final BlockState NETHERRACK = Blocks.NETHERRACK.defaultBlockState();
        protected static final BlockState AIR = Blocks.AIR.defaultBlockState();
    }

    public static final class Configs
    {
        public static final BlockClusterFeatureConfig ARCANE_CRYSTAL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ARCANE_CLUSTER.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(16).whitelist(ImmutableSet.of(States.STONE.getBlock(),
                States.ANDESITE.getBlock(),
                States.DIORITE.getBlock(),
                States.GRANITE.getBlock())).build();

        public static final BlockClusterFeatureConfig ARCANE_SHRINE_STONE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ARCANE_SWORD_SHRINE_STONE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1)
                .whitelist(ImmutableSet.of(States.STONE.getBlock())).build();
        public static final BlockClusterFeatureConfig ARCANE_SHRINE_GRANITE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ARCANE_SWORD_SHRINE_GRANITE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1)
                .whitelist(ImmutableSet.of(States.GRANITE.getBlock())).build();
        public static final BlockClusterFeatureConfig ARCANE_SHRINE_DIORITE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ARCANE_SWORD_SHRINE_DIORITE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1)
                .whitelist(ImmutableSet.of(States.DIORITE.getBlock())).build();
        public static final BlockClusterFeatureConfig ARCANE_SHRINE_ANDESITE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.ARCANE_SWORD_SHRINE_ANDESITE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(1)
                .whitelist(ImmutableSet.of(States.ANDESITE.getBlock())).build();

    }

    public static final class Placements
    {
        public static final ConfiguredPlacement<TopSolidRangeConfig> ARCANE_CRYSTAL_PLACEMENT = Placement.RANGE.configured(new TopSolidRangeConfig(32, 32, 80));
        public static final ConfiguredPlacement<TopSolidRangeConfig> ARCANE_SHRINE_PLACEMENT = Placement.RANGE.configured(new TopSolidRangeConfig(32, 32, 80));
    }

    public static final class FillerBlockType {
        public static final RuleTest OBSIDIAN = new BlockMatchRuleTest(Blocks.OBSIDIAN);
    }

}

