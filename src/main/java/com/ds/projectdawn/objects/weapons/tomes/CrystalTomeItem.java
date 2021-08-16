package com.ds.projectdawn.objects.weapons.tomes;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.ParticleInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.AmethystBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class CrystalTomeItem extends AbstractTomeItem
{
    public CrystalTomeItem(IItemTier tierIn, float damageIn, int cooldownIn, Properties properties) {
        super(tierIn, damageIn, cooldownIn, properties);
        this.setXpAmount(4);
        this.setVelocity(0.50F);
        this.setInnacuracy(2.50F);
        this.setTomeName("CRYSTAL TOME");
        this.setSpellSound(SoundInit.BASIC_MAGIC_SHOOT);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        AmethystBoltEntity amethystBolt = new AmethystBoltEntity(EntityTypeInit.AMETHYST_BOLT.get(), shooter, shooter.level);
        amethystBolt.setParticle(ParticleInit.AMETHYST_GLINT.get());
        amethystBolt.setBaseDamage((double) this.getMagicDamage());
        amethystBolt.setOwner(shooter);
        amethystBolt.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return amethystBolt;
    }
}
