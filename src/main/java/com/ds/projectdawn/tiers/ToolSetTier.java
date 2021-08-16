package com.ds.projectdawn.tiers;

import com.ds.projectdawn.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ToolSetTier implements IItemTier
{
    //NAME(Harvest Level, Max Uses, Efficiency, Attack Damage, Enchantability, Repair Material)
    //All have a base damage of 1 (1 + basedamage + attackdamage)
    //normal attack speed for swords is -2.4f (1.6)

    FIREBRAND(4, 2031, 8.0f, 5.0f, 5, () -> {
        return Ingredient.of(ItemInit.FIREBRAND_INGOT.get());
    }),
    FROSTBRAND(4, 2031, 10.0f, 5.0f, 15, () -> {
        return Ingredient.of(ItemInit.FROSTBRAND_INGOT.get());
    }),
    ENDERITE(4,2456, 12.0f, 11.0f, 14, () -> {
        return Ingredient.of(ItemInit.ENDERITE_CRYSTAL.get());
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    ToolSetTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial)
    {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
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
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
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
