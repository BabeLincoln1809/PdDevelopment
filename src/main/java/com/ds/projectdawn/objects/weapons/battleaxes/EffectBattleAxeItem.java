package com.ds.projectdawn.objects.weapons.battleaxes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class EffectBattleAxeItem extends BattleAxeItem
{
    private final Effect effect;
    private final int duration;
    private final int amplifier;

    public EffectBattleAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Effect effectIn, int durationIn, int amplifierIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, strengthIn, builderIn);

        this.effect = effectIn;
        this.duration = durationIn;
        this.amplifier = amplifierIn;
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World worldIn = attacker.level;

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
