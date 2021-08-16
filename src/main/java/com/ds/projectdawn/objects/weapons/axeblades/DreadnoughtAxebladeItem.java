package com.ds.projectdawn.objects.weapons.axeblades;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Random;

public class DreadnoughtAxebladeItem extends AxebladeItem
{
    public DreadnoughtAxebladeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, strengthIn, properties);
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World worldIn = attacker.level;
        Vector3d aim = attacker.getLookAngle();

        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;

        if (chance <= 25) {
            target.addEffect(new EffectInstance(Effects.WITHER, 250, 0));

            if (!worldIn.isClientSide) {
                worldIn.playSound((PlayerEntity) null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                target.knockback(strength + 1.0F, aim.x * -1, aim.z * -1);
            }
        }
        this.doKnockBack((PlayerEntity)attacker, target);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
}
