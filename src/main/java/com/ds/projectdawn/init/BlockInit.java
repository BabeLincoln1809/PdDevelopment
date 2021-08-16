package com.ds.projectdawn.init;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.objects.blocks.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit
{

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectDawn.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectDawn.MOD_ID);


    public static void init()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    //Blocks---------------
    public static final RegistryObject<OreBlock> ARCANE_BLOCK = BlockInit.BLOCKS.register("arcane_block", () ->
            new OreBlock(AbstractBlock.Properties.of(Material.STONE).strength(3.0f, 15.0f).sound(SoundType.STONE)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));
    public static final RegistryObject<ClusterBlock> ARCANE_CLUSTER = BlockInit.BLOCKS.register("arcane_cluster", () ->
            new ClusterBlock(3, 6, AbstractBlock.Properties.of(Material.STONE).strength(5.0f, 6.0f).sound(SoundType.GLASS)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));

    public static final RegistryObject<ClusterBlock> RADIANT_CLUSTER = BlockInit.BLOCKS.register("radiant_cluster", () ->
            new ClusterBlock(5, 8, AbstractBlock.Properties.of(Material.STONE).strength(5.0f, 6.0f).sound(SoundType.GLASS)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));

    public static final RegistryObject<RubyOreBlock> RUBY_ORE = BlockInit.BLOCKS.register("ruby_ore", () ->
            new RubyOreBlock(AbstractBlock.Properties.of(Material.STONE).strength(3.0f, 15.0f).sound(SoundType.STONE)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));


    //Special Blocks---------------
    public static final RegistryObject<ArcaneSwordShrineBlock> ARCANE_SWORD_SHRINE_STONE = BlockInit.BLOCKS.register("arcane_sword_shrine_stone", () ->
            new ArcaneSwordShrineBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.25f, 6.0f).sound(SoundType.STONE)));
    public static final RegistryObject<ArcaneSwordShrineBlock> ARCANE_SWORD_SHRINE_GRANITE = BlockInit.BLOCKS.register("arcane_sword_shrine_granite", () ->
            new ArcaneSwordShrineBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.25f, 6.0f).sound(SoundType.STONE)));
    public static final RegistryObject<ArcaneSwordShrineBlock> ARCANE_SWORD_SHRINE_DIORITE = BlockInit.BLOCKS.register("arcane_sword_shrine_diorite", () ->
            new ArcaneSwordShrineBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.25f, 6.0f).sound(SoundType.STONE)));
    public static final RegistryObject<ArcaneSwordShrineBlock> ARCANE_SWORD_SHRINE_ANDESITE = BlockInit.BLOCKS.register("arcane_sword_shrine_andesite", () ->
            new ArcaneSwordShrineBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.25f, 6.0f).sound(SoundType.STONE)));

    //Block Items---------------
    public static final RegistryObject<BlockItem> ARCANE_SWORD_SHRINE_STONE_ITEM = BlockInit.BLOCK_ITEMS.register("arcane_sword_shrine_stone", () ->
            new BlockItem(ARCANE_SWORD_SHRINE_STONE.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));
    public static final RegistryObject<BlockItem> ARCANE_SWORD_SHRINE_GRANITE_ITEM = BlockInit.BLOCK_ITEMS.register("arcane_sword_shrine_granite", () ->
            new BlockItem(ARCANE_SWORD_SHRINE_GRANITE.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));
    public static final RegistryObject<BlockItem> ARCANE_SWORD_SHRINE_DIORITE_ITEM = BlockInit.BLOCK_ITEMS.register("arcane_sword_shrine_diorite", () ->
            new BlockItem(ARCANE_SWORD_SHRINE_DIORITE.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));
    public static final RegistryObject<BlockItem> ARCANE_SWORD_SHRINE_ANDESITE_ITEM = BlockInit.BLOCK_ITEMS.register("arcane_sword_shrine_andesite", () ->
            new BlockItem(ARCANE_SWORD_SHRINE_ANDESITE.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));

    public static final RegistryObject<BlockItem> ARCANE_BLOCK_ITEM = BlockInit.BLOCK_ITEMS.register("arcane_block", () ->
            new BlockItem(ARCANE_BLOCK.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));
    public static final RegistryObject<BlockItem> ARCANE_CLUSTER_ITEM = BlockInit.BLOCK_ITEMS.register("arcane_cluster", () ->
            new BlockItem(ARCANE_CLUSTER.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));

    public static final RegistryObject<BlockItem> RADIANT_CLUSTER_ITEM = BlockInit.BLOCK_ITEMS.register("radiant_cluster", () ->
            new BlockItem(RADIANT_CLUSTER.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));

    public static final RegistryObject<BlockItem> RUBY_ORE_ITEM = BlockInit.BLOCK_ITEMS.register("ruby_ore", () ->
            new BlockItem(RUBY_ORE.get(), new Item.Properties().tab(ProjectDawn.PROJECTDAWN_BLOCKS_TAB)));

}
