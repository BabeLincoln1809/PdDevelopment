package com.ds.projectdawn.events.accessoryevents;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.ItemInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.ArcaneBoltEntity;
import com.ds.projectdawn.objects.entities.projectiles.EmeraldBoltEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UnstableCrystalEvent
{
    Random rand = new Random();

    public UnstableCrystalEvent() {  }

    @SubscribeEvent
    public void unstableCrystalShoot(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof LivingEntity || event.getSource().isProjectile() || event.getSource().isMagic()) {

            if (event.getEntity() instanceof PlayerEntity) {
                int chance = rand.nextInt(100)+1;
                PlayerEntity playerIn = (PlayerEntity) event.getEntity();
                Vector3d aim = playerIn.getLookAngle();

                if (!playerIn.inventory.isEmpty()) {
                    if (playerIn.inventory.contains(new ItemStack(ItemInit.UNSTABLE_CRYSTAL.get()))) {
                        ArcaneBoltEntity bolt = new ArcaneBoltEntity(EntityTypeInit.ARCANE_BOLT.get(), playerIn, playerIn.level);
                        bolt.setLife(25);
                        bolt.setBaseDamage(3);
                        bolt.setOwner(playerIn);
                        bolt.shoot(aim.x, aim.y, aim.z, 1.0f, 1.0f);
                        if(!playerIn.level.isClientSide) {
                            if (chance <= 15) {
                                playerIn.playSound(SoundInit.BASIC_MAGIC_SHOOT, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                                playerIn.getCommandSenderWorld().addFreshEntity(bolt);
                                ProjectDawn.LOGGER.info("UNSTABLE!!!");
                            } else ProjectDawn.LOGGER.info("STABLE!!!");
                        }
                    } else ProjectDawn.LOGGER.info("NOTHING FROM UNSTABLE CRYSTAL");
                }
            }
        }
    }
}

