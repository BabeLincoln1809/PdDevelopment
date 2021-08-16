package com.ds.projectdawn.objects.weapons.staffs;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.ParticleInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.AmethystBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class AmethystStaffItem extends AbstractStaffItem
{
    public AmethystStaffItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tierIn, attackDamageIn, attackSpeedIn, properties);
        this.setXpAmount(8);
        this.setVelocity(0.75F);
        this.setInnacuracy(2.25F);
        this.setStaffName("AMETHYST STAFF");
        this.setSpellSound(SoundInit.BASIC_MAGIC_SHOOT);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter, ItemStack stack) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        AmethystBoltEntity amethystBolt = new AmethystBoltEntity(EntityTypeInit.AMETHYST_BOLT.get(), shooter, shooter.level);
        amethystBolt.setLife(30);
        amethystBolt.setBaseDamage((double) this.getMagicDamage());
        amethystBolt.setOwner(shooter);
        amethystBolt.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return amethystBolt;
    }
}
