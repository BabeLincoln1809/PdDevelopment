package com.ds.projectdawn.tiers;

import com.ds.projectdawn.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum MagicWeaponTier implements IItemTier
{
    //Staffs
    ARCANE_WAND(60, 0, 0.0f, 4, () -> {
        return Ingredient.of(ItemInit.ARCANE_SHARD.get());
    }),
    AMETHYSTSTAFF(85, 2, 1.0f, 22, () -> {
        return Ingredient.of(Items.EMERALD);
    }),
    EMERALDSTAFF(150, 3, 2.0f, 22, () -> {
        return Ingredient.of(Items.EMERALD);
    }),
    RUBYSTAFF(245, 4, 3.0f, 14, () -> {
        return Ingredient.of(ItemInit.RUBY.get());
    }),
    DIAMONDSTAFF(567, 5, 4.0f, 10, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),
    RADIANTPILLAR(726, 5, 5.0f, 15, () -> {
        return Ingredient.of(ItemInit.RADIANT_CRYSTAL.get());
    }),
    FROSTSPARK(1236, 6, 6.0f, 18, () -> {
        return Ingredient.of(ItemInit.FROSTBRAND_INGOT.get());
    }),
    FORK(1236, 6, 6.0f, 13, () -> {
        return Ingredient.of(ItemInit.FIREBRAND_INGOT.get());
    }),
    SOULSTAFF(1065, 7, 7.0f, 9, () -> {
        return Ingredient.of(ItemInit.WITHER_BONE.get());
    }),
    SHADOWCRESCENT(2456, 9, 9.0f, 14, () -> {
        return Ingredient.of(ItemInit.WITHER_BONE.get());
    }),

    //Tomes
    MAGICHARP(400, 3, 2.0f, 10, () -> {
        return Ingredient.of(ItemInit.ARCANE_SHARD.get());
    }),
    INFERNOTOME(236, 6, 6.0f, 4, () -> {
        return Ingredient.of(Items.BLAZE_POWDER);
    }),
    EVOKERTOME(456, 7, 3.0f, 8, () -> {
        return Ingredient.of(ItemInit.ARCANE_SHARD.get());
    }),
    RADIANTHARP(650, 8, 6.0f, 14, () -> {
    return Ingredient.of(ItemInit.RADIANT_CRYSTAL.get());
    });

    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    MagicWeaponTier(int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial)
    {
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



