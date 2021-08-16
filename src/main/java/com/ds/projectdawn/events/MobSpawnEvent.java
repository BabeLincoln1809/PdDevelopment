package com.ds.projectdawn.events;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobSpawnEvent {

    public MobSpawnEvent() {}

    @SubscribeEvent
    public static void axeBladeChance(EntityJoinWorldEvent event)
    {

        Entity entity = event.getEntity();

        Random rand = new Random();

        int chance = rand.nextInt(250)+1;

        if(entity instanceof ZombieEntity && !(entity instanceof ZombifiedPiglinEntity) && !(entity instanceof ZombieVillagerEntity) && !(entity instanceof DrownedEntity) && chance >= 1 && chance <= 5) {
            entity.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemInit.AXEBLADE.get()));
            entity.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(Items.IRON_HELMET));
            entity.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            entity.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
            entity.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
            ((ZombieEntity) entity).getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.28D);
        }

    }

    @SubscribeEvent
    public static void replaceHeldItem(EntityJoinWorldEvent event)
    {
        Entity entity = event.getEntity();

        Random rand = new Random();

        int chance = rand.nextInt(100)+1;

        //if(entity instanceof PiglinEntity) {

          //  PiglinEntity piglinIn = (PiglinEntity) event.getEntity();

            //if(!piglinIn.isChild()) {
              //  if (chance >= 1 && chance <= 50) {
                   // entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(MeleeWeaponInit.PIGLIN_SWORD.get()));
                //} else
                  //  entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.CROSSBOW));
         //   }

        //}

        if(entity instanceof PiglinBruteEntity) {
            if(chance >= 1 && chance <= 50)
                entity.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemInit.PIGLIN_DOOMBLADE.get()));

        }

       // if(entity instanceof ZombifiedPiglinEntity) {

         //       entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(MeleeWeaponInit.PIGLIN_SWORD.get()));

        //}

        //if(entity instanceof VindicatorEntity) {

          //      entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ToolsInit.VINDICATOR_AXE.get()));
        //}

    }

}
