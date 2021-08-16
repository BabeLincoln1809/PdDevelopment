package com.ds.projectdawn.objects.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.Random;

public class EnderLordItem extends SwordItem
{
    public EnderLordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties p_i48460_4_) {
        super(tier, attackDamageIn, attackSpeedIn, p_i48460_4_);
    }

    Random rand = new Random();

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        World world = (World)attacker.level;

        int x = rand.nextInt(10)-5;
        int y = rand.nextInt(5)+1;
        int z = rand.nextInt(10)-5;

        double posX = target.getX();
        double posY = target.getY();
        double posZ = target.getZ();

        int chance = rand.nextInt(100)+1;

        if(!world.isClientSide)
        {
            target.teleportTo(posX + x,posY + y,posZ + z);
            world.playSound((PlayerEntity) null, target.getX(), target.getY(), target.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 1.0F, 1.0F);

            for(int i = 0; i < 32; ++i) {
                attacker.level.addParticle(ParticleTypes.PORTAL, target.getX(), target.getY() + this.rand.nextDouble() * 2.0D, target.getZ(), this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
            }

        }

        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

}
