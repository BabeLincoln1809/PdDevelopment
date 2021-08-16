package com.ds.projectdawn.objects.weapons.staffs;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.EmeraldBoltEntity;
import com.ds.projectdawn.objects.entities.projectiles.RadiantBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class RadiantPillarItem extends AbstractStaffItem
{
    public RadiantPillarItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tierIn, attackDamageIn, attackSpeedIn, properties);
        this.setXpAmount(20);
        this.setVelocity(1.75F);
        this.setInnacuracy(1.25F);
        this.setStaffName("RADIANT STAFF");
        this.setSpellSound(SoundInit.BASIC_MAGIC_SHOOT);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter, ItemStack stack) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        RadiantBoltEntity radiantBolt = new RadiantBoltEntity(EntityTypeInit.RADIANT_BOLT.get(), shooter, shooter.level);
        radiantBolt.setLife(50);
        radiantBolt.setBaseDamage((double) this.getMagicDamage());
        radiantBolt.setOwner(shooter);
        radiantBolt.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return radiantBolt;
    }
}
