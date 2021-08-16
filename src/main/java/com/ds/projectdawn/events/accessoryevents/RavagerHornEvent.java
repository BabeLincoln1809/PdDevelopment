package com.ds.projectdawn.events.accessoryevents;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RavagerHornEvent
{
    private static final Predicate<Entity> entity = Entity::isAlive;

        Random rand = new Random();

    public RavagerHornEvent() {  }

    @SubscribeEvent
    public void ravagerHornAttack(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof LivingEntity || event.getSource().isProjectile() || event.getSource().isMagic()) {
            if (event.getEntity() instanceof PlayerEntity) {
                int chance = rand.nextInt(100) + 1;
                PlayerEntity playerIn = (PlayerEntity) event.getEntity();
                if (!playerIn.inventory.isEmpty()) {
                    if (playerIn.inventory.contains(new ItemStack(ItemInit.RAVAGER_HORN.get()))) {
                        if (chance <= 30 && event.getSource().getDirectEntity() != playerIn && !event.getSource().isBypassInvul()) {
                            if (!playerIn.level.isClientSide) {
                                this.roar(playerIn);
                                playerIn.level.playSound((PlayerEntity) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.RAVAGER_ROAR, SoundCategory.NEUTRAL, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                            }
                            this.spawnParticles(playerIn);
                            ProjectDawn.LOGGER.info("ROAR!!!");
                        } else ProjectDawn.LOGGER.info("meow :)");
                    } else ProjectDawn.LOGGER.info("NOTHING FROM MALICE");
                }
            }
        }
    }
    private void roar(LivingEntity user) {
        if (user.isAlive()) {
            for(Entity entity : user.level.getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().inflate(4.0D), entity)) {
                if(entity != user) {
                    entity.hurt(DamageSource.mobAttack(user), 0.5F);
                    this.launch(entity, user);
                }
            }
        }
    }
    private void spawnParticles(LivingEntity user) {
        Vector3d vector3d = user.getBoundingBox().getCenter();
        for (int i = 0; i < 40; ++i) {
            double d0 = this.rand.nextGaussian() * 0.2D;
            double d1 = this.rand.nextGaussian() * 0.2D;
            double d2 = this.rand.nextGaussian() * 0.2D;
            user.level.addParticle(ParticleTypes.POOF, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
        }
    }

    private void launch(Entity target, Entity user) {
        double d0 = target.getX() - user.getX();
        double d1 = target.getZ() - user.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        target.push(d0 / d2 * 0.5D, 0.2D, d1 / d2 * 0.5D);
    }
}
