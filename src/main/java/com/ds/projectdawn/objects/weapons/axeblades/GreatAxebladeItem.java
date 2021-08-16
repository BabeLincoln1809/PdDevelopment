package com.ds.projectdawn.objects.weapons.axeblades;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class GreatAxebladeItem extends AxebladeItem
{
    public GreatAxebladeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, strengthIn, properties);
        setBonusDamage(2);
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
            if (target.isInvertedHealAndHarm()) {
                if (attacker.swingTime == 0.0F)
                    target.hurt(DamageSource.mobAttack(attacker), (float) this.getBonusDamage());
            }
            this.doKnockBack((PlayerEntity)attacker, target);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    @Override
    public void doKnockBack(PlayerEntity player, LivingEntity target) {
        super.doKnockBack(player, target);
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        Vector3d aim = player.getLookAngle();
        if (chance <= 15) {
            target.knockback(this.strength + 1, aim.x * -1, aim.z * -1);
            target.addEffect(new EffectInstance(Effects.WEAKNESS, 250, 0));
            player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }
}
