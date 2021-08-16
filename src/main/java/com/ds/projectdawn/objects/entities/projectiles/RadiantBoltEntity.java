package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.ParticleInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class RadiantBoltEntity extends ArcaneBoltEntity {

    boolean canMultiShoot = true;

    public RadiantBoltEntity(EntityType<? extends RadiantBoltEntity> type, World worldIn) {
        super(type, worldIn);
        this.setParticle(ParticleInit.DIAMOND_GLINT.get());
    }
    public RadiantBoltEntity(EntityType<? extends RadiantBoltEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(ParticleInit.DIAMOND_GLINT.get());
    }
    public RadiantBoltEntity(EntityType<? extends RadiantBoltEntity> type, LivingEntity shooterIn, World worldIn) {
        super(type, shooterIn, worldIn);
        this.setParticle(ParticleInit.DIAMOND_GLINT.get());
    }
    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);

        if(result.getType() == RayTraceResult.Type.ENTITY)
        {
            if(this.canMultiShoot)
                this.specialAttack();
        }

        if (!this.level.isClientSide)
        {
            this.remove();
            this.level.broadcastEntityEvent(this, (byte)3);
        }
    }
    public void specialAttack()
    {
        if(this.getOwner() != null) {
            Vector3d vec3d = this.getOwner().getLookAngle();
            double facingRotation = Math.atan2(vec3d.z, vec3d.x);
            double increase = 6 / 3 - 1;
            for (int i = 0; i <= 3; i++) {
                double rot = facingRotation - increase * i;
                RadiantBoltEntity radiantBolt = new RadiantBoltEntity(EntityTypeInit.RADIANT_BOLT.get(), (LivingEntity)this.getOwner(), this.getOwner().level);
                radiantBolt.canMultiShoot = false;
                radiantBolt.setBaseDamage(this.getBaseDamage() / 2);
                radiantBolt.setOwner(this.getOwner());
                radiantBolt.shoot(rot, rot, rot, 1.45F, 1.0F);
                this.getOwner().level.addFreshEntity(radiantBolt);
            }
        }
    }

}