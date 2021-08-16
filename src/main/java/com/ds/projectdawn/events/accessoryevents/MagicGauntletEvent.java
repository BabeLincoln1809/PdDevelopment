package com.ds.projectdawn.events.accessoryevents;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MagicGauntletEvent
{
    public MagicGauntletEvent() {  }

    @SubscribeEvent
    public void magicGauntletAttack(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) event.getSource().getDirectEntity();
            Vector3d aim = playerIn.getLookAngle();
            if (!playerIn.inventory.isEmpty()) {
                boolean flag = playerIn.inventory.contains(new ItemStack(ItemInit.FIRE_GAUNTLETS.get()));
                if (playerIn.inventory.contains(new ItemStack(ItemInit.MAGIC_GAUNTLETS.get()))) {
                    if (event.getEntity() instanceof LivingEntity) {
                        if(!event.getSource().isMagic() && !event.getSource().isProjectile() && !flag) {
                            ((LivingEntity) event.getEntity()).knockback(0.55F, aim.x * -1, aim.z * -1);
                            ProjectDawn.LOGGER.info("MAGIC GAUNTLET HIT! MAGIC GAUNTLET HIT!!!");
                        } else ProjectDawn.LOGGER.info("NOTHING FROM MAGIC GAUNTLET");
                    }
                }
            }
        }
    }

}
