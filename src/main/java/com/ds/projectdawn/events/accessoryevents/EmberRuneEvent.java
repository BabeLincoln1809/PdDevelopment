package com.ds.projectdawn.events.accessoryevents;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EmberRuneEvent
{
    public EmberRuneEvent() {  }

    @SubscribeEvent
    public void emberRuneAttack(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) event.getSource().getDirectEntity();
            if (!playerIn.inventory.isEmpty()) {
                boolean flag = playerIn.inventory.contains(new ItemStack(ItemInit.FIRE_GAUNTLETS.get()));
                if (playerIn.inventory.contains(new ItemStack(ItemInit.EMBER_RUNE.get()))) {
                    if (event.getEntity() instanceof LivingEntity) {
                        if(!event.getSource().isMagic() && !event.getSource().isProjectile() && !flag) {
                            event.getEntity().setSecondsOnFire(4);
                            ProjectDawn.LOGGER.info("FIRE! FIRE!!!");
                        } else ProjectDawn.LOGGER.info("NOTHING FROM EMBER RUNE");
                    }
                }
            }
        }
    }

}
