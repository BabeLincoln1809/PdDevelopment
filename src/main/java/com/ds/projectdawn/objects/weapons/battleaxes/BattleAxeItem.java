package com.ds.projectdawn.objects.weapons.battleaxes;

import com.ds.projectdawn.init.ItemInit;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Set;

public class BattleAxeItem extends TieredItem implements IVanishable {

    private static final Set<Material> EFFECTIVE_ON_MATERIALS = Sets.newHashSet(Material.WOOD, Material.NETHER_WOOD, Material.PLANT, Material.BAMBOO);

    private final float attackDamage;
    private final float strength;
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public BattleAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Properties builderIn) {
        super(tier, builderIn);
        this.attackDamage = (float)attackDamageIn + tier.getAttackDamageBonus();
        this.strength = strengthIn;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public boolean canAttackBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) { return true; }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return EFFECTIVE_ON_MATERIALS.contains(material) ? 2.0F : super.getDestroySpeed(stack, state);
        }
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.doKnockBack(attacker, target);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    public void doKnockBack(LivingEntity attacker, LivingEntity target) {
        Vector3d aim = attacker.getLookAngle();
        target.knockback(this.getStrength(), aim.x * -1, aim.z * -1);

    }
    public boolean mineBlock(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (state.getDestroySpeed(worldIn, pos) != 0.0F) {
            stack.hurtAndBreak(2, entityLiving, (p_220044_0_) -> {
                p_220044_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }
    public boolean isCorrectToolForDrops(BlockState blockIn) {
        return blockIn.is(Blocks.COBWEB);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return EnchantmentType.WEAPON.canEnchant(this.getItem());
    }

    public float getAttackDamage() { return this.attackDamage; }
    public float getStrength() { return this.strength; }

    @SuppressWarnings("deprecation")
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
        return slot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getDefaultAttributeModifiers(slot);
    }
}