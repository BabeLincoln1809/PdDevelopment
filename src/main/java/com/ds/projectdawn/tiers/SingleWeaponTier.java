package com.ds.projectdawn.tiers;

import com.ds.projectdawn.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum SingleWeaponTier implements IItemTier
{
    //NAME(Harvest Level, Max Uses, Efficiency, Attack Damage, Enchantability, Repair Material)
    //All have a base damage of 1 (1 + basedamage + attackdamage)
    //normal attack speed for swords is -2.4f (1.6)

    //Melee
    SLAMMER(150, 3.0f, 7, () -> {
        return Ingredient.of(Items.SLIME_BALL);
    }),
    ARCANE(235, 5.0f, 22, () -> {
        return Ingredient.of(ItemInit.ARCANE_SHARD.get());
    }),
    DOOMBLADE(235, 5.0f, 18, () -> {
        return Ingredient.of(Items.GOLD_INGOT);
    }),
    SUNBREAKER(820, 7.0f, 10, () -> {
        return Ingredient.of(ItemInit.FIREBRAND_INGOT.get());
    }),
    JAGGEDTOOTH(986, 9.0f, 5, () -> {
        return Ingredient.of(ItemInit.WITHER_BONE.get());
    }),
    STORMLANDER(1322, 13.0f, 7, () -> {
        return Ingredient.of(Items.STONE);
    }),
    DREADNOUGHT(1789, 11.0f, 5, () -> {
        return Ingredient.of(Items.NETHERITE_INGOT);
    }),
    ICEHAVOK(3134, 24.0f, 10, () -> {
        return Ingredient.of(Items.ICE);
    });


    private final int maxUses;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    SingleWeaponTier(int maxUses, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial)
    {
        this.maxUses = maxUses;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

}



