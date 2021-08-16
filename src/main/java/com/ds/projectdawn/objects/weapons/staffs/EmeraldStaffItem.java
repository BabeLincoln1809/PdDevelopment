package com.ds.projectdawn.objects.weapons.staffs;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.EmeraldBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class EmeraldStaffItem extends AbstractStaffItem
{
    public EmeraldStaffItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tierIn, attackDamageIn, attackSpeedIn, properties);
        this.setXpAmount(12);
        this.setVelocity(1.0F);
        this.setInnacuracy(2.0F);
        this.setStaffName("EMERALD STAFF");
        this.setSpellSound(SoundInit.BASIC_MAGIC_SHOOT);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter, ItemStack stack) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        EmeraldBoltEntity emeraldBolt = new EmeraldBoltEntity(EntityTypeInit.EMERALD_BOLT.get(), shooter, shooter.level);
        emeraldBolt.setLife(35);
        emeraldBolt.setBaseDamage((double) this.getMagicDamage());
        emeraldBolt.setOwner(shooter);
        emeraldBolt.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return emeraldBolt;
    }
}
