package com.ds.projectdawn.objects.weapons.axeblades;

import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Random;
import java.util.Set;

public class AxebladeItem extends SwordItem
{
    private static final Set<Material> EFFECTIVE_ON_MATERIALS = Sets.newHashSet(Material.WOOD, Material.NETHER_WOOD, Material.PLANT, Material.GRASS, Material.BAMBOO);

    public float strength;
    public int bonusDamage;

    public AxebladeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);

        this.strength = strengthIn;
    }
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) { return true; }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return EFFECTIVE_ON_MATERIALS.contains(material) ? 2.0F : super.getDestroySpeed(stack, state);
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        this.doKnockBack((PlayerEntity)attacker, target);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    public void doKnockBack(PlayerEntity player, LivingEntity target)
    {
        Vector3d aim = player.getLookAngle();
        target.knockback(this.getStrength(), aim.x * -1, aim.z * -1);
    }

    public void setBonusDamage(int bonusDamageIn) { this.bonusDamage = bonusDamageIn; }
    public int getBonusDamage() { return this.bonusDamage;}
    public float getStrength() { return this.strength; }

}
