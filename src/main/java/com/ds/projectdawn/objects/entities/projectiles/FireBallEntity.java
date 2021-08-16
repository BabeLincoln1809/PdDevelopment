package com.ds.projectdawn.objects.entities.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FireBallEntity extends ArcaneBoltEntity {

    private int fire;

    public FireBallEntity(EntityType<? extends FireBallEntity> type, World worldIn) {
        super(type, worldIn);
        this.setParticle(ParticleTypes.FLAME);
    }
    public FireBallEntity(EntityType<? extends FireBallEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(ParticleTypes.FLAME);
    }
    public FireBallEntity(EntityType<? extends FireBallEntity> type, LivingEntity shooterIn, World worldIn, int fireIn) {
        super(type, shooterIn, worldIn);
        this.fire = fireIn;
        this.setParticle(ParticleTypes.FLAME);

    }
    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();

        if (owner == null) {
            entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage());
        } else {
            entity.hurt(DamageSource.indirectMagic(this, owner), (float)this.getBaseDamage());
            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
        }
        entity.setSecondsOnFire(this.getFire());
    }
    @Override
    public void tick()
    {
        Vector3d vector3d = this.getBoundingBox().getCenter();

        super.tick();
        if (this.level.isClientSide && this.doesEffect(true)) {
            this.level.addParticle(this.getParticle(), vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
            this.level.addParticle(ParticleTypes.SMOKE, vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);

        }

        if(!this.level.isClientSide && this.tickCount == 45)
        {
            remove();
            this.level.broadcastEntityEvent(this, (byte)3);
        }
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3 && this.doesEffect(true)) {

            Vector3d vector3d = this.getBoundingBox().getCenter();

            for(int i = 0; i < 8; ++i) {

                double d0 = this.random.nextGaussian() * 0.2D;
                double d1 = this.random.nextGaussian() * 0.2D;
                double d2 = this.random.nextGaussian() * 0.2D;


                this.level.addParticle(this.getParticle(), vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
                this.level.addParticle(ParticleTypes.SMOKE, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
                this.level.addParticle(ParticleTypes.LAVA, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
            }
        }
    }

    public boolean doesEffect(boolean doesEffect) { return true; }
    public int getFire() { return fire; }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundEvents.GENERIC_EXTINGUISH_FIRE; }
    @Override
    public boolean isOnFire() { return true; }
    @Override
    public boolean fireImmune() { return false; }
}