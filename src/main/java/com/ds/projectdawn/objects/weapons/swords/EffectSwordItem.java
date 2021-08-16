package com.ds.projectdawn.objects.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class EffectSwordItem extends SwordItem
{
    private final Effect effect;
    private final int duration;
    private final int amplifier;

    public EffectSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Effect effectIn, int durationIn, int amplifierIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);

        this.effect = effectIn;
        this.duration = durationIn;
        this.amplifier = amplifierIn;

    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if(this.effect != null)
        {
            target.addEffect(new EffectInstance(this.effect, this.duration, this.amplifier));
        }
        else
            target.setSecondsOnFire(this.duration);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    public Effect getEffect() { return this.effect;}

}
