package com.ds.projectdawn.objects.weapons.swords;

import com.ds.projectdawn.ProjectDawn;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import java.util.Random;

public class LifeStealingSwordItem extends SwordItem {

    private final float chance;

    public LifeStealingSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, int chanceIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
        this.chance = chanceIn;
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.stealHealth(target, attacker);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    public void stealHealth(LivingEntity target, LivingEntity attacker){
        Random rand = new Random();
        double i = attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double maxHearts = (i - rand.nextInt((int)i/2 )) * 0.5D;
        int j = rand.nextInt(100)+1;
        if(this.getChance() >= j)
            attacker.heal((float)maxHearts);
    }

    public float getChance() { return this.chance; }
}
