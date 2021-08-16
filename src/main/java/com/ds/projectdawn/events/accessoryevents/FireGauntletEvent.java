package com.ds.projectdawn.events.accessoryevents;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FireGauntletEvent {
    protected static final UUID BONUS_ATTACK_DAMAGE_UUID = UUID.fromString("FA233E1C-4180-4F38-B01B-96EA6097278D");

    public FireGauntletEvent() { }

    @SubscribeEvent
    public void fireGauntletAttack(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) event.getSource().getEntity();
            Vector3d aim = playerIn.getLookAngle();
            if (!playerIn.inventory.isEmpty()) {
                if (playerIn.inventory.contains(new ItemStack(ItemInit.FIRE_GAUNTLETS.get()))) {
                    if (event.getEntity() instanceof LivingEntity) {
                        if (!event.getSource().isMagic() && !event.getSource().isProjectile()) {
                            event.getEntity().setSecondsOnFire(8);
                            ((LivingEntity) event.getEntity()).knockback(0.65F, aim.x * -1, aim.z * -1);
                            ProjectDawn.LOGGER.info("FIRE GAUNTLET HIT! FIRE GAUNTLET HIT!!!");
                        } else ProjectDawn.LOGGER.info("NOTHING FROM FIRE GAUNTLET");
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void addBonusDamage(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity playerIn = (PlayerEntity)event.getEntityLiving();

            if(playerIn.inventory.contains(new ItemStack(ItemInit.FIRE_GAUNTLETS.get())))
                playerIn.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0D);
            else
                playerIn.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        }
    }

}

