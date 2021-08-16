package com.ds.projectdawn.objects.weapons.staffs;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.DiamondBoltEntity;
import com.ds.projectdawn.objects.entities.projectiles.EmeraldBoltEntity;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Iterator;

public class DiamondStaffItem extends AbstractStaffItem
{
    public DiamondStaffItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tierIn, attackDamageIn, attackSpeedIn, properties);
        this.setXpAmount(17);
        this.setVelocity(1.50F);
        this.setInnacuracy(1.50F);
        this.setStaffName("DIAMOND STAFF");
        this.setSpellSound(SoundInit.BASIC_MAGIC_SHOOT);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter, ItemStack stack) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        DiamondBoltEntity diamondBolt = new DiamondBoltEntity(EntityTypeInit.DIAMOND_BOLT.get(), shooter, shooter.level);
        diamondBolt.setLife(45);
        diamondBolt.setBaseDamage((double) this.getMagicDamage());
        diamondBolt.setOwner(shooter);
        diamondBolt.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return diamondBolt;
    }

}
