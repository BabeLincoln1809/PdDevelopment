package com.ds.projectdawn.loot;

import com.ds.projectdawn.ProjectDawn;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID)
public class LootRegistry
{
    private static ResourceLocation MINESHAFT_CHEST = new ResourceLocation("minecraft", "chests/abandoned_mineshaft");
    private static ResourceLocation DUNGEON_CHEST = new ResourceLocation("minecraft", "chests/simple_dungeon");
    private static ResourceLocation BASTION_BRIDGE = new ResourceLocation("minecraft", "chests/bastion_bridge");
    private static ResourceLocation BASTION_OTHER = new ResourceLocation("minecraft", "chests/bastion_other");
    private static ResourceLocation BASTION_TREASURE = new ResourceLocation("minecraft", "chests/bastion_treasure");

    private static ResourceLocation WITCH = new ResourceLocation("minecraft", "entities/witch");
    private static ResourceLocation CREEPER = new ResourceLocation("minecraft", "entities/creeper");
    private static ResourceLocation RAVAGER = new ResourceLocation("minecraft", "entities/ravager");
    private static ResourceLocation EVOKER = new ResourceLocation("minecraft", "entities/evoker");
    private static ResourceLocation WITHER = new ResourceLocation("minecraft", "entities/wither");
    private static ResourceLocation ENDER_DRAGON = new ResourceLocation("minecraft", "entities/ender_dragon");

    @SubscribeEvent
    public void lootLoad(LootTableLoadEvent event)
    {
        if(event.getName().equals(MINESHAFT_CHEST))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/abandoned_mineshaft_inject"))).build());
        if(event.getName().equals(DUNGEON_CHEST))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/simple_dungeon_inject"))).build());
        if(event.getName().equals(BASTION_TREASURE) || event.getName().equals(BASTION_OTHER) || event.getName().equals(BASTION_BRIDGE))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/generic_bastion_inject"))).build());

        if(event.getName().equals(WITCH))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/witch_inject"))).build());
        if(event.getName().equals(CREEPER))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/creeper_inject"))).build());
        if(event.getName().equals(RAVAGER))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/ravager_inject"))).build());
        if(event.getName().equals(EVOKER))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/evoker_inject"))).build());

        if(event.getName().equals(WITHER))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/wither_inject"))).build());
        if(event.getName().equals(ENDER_DRAGON))
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(ProjectDawn.MOD_ID, "inject/ender_dragon_inject"))).build());

    }
}
