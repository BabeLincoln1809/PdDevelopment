package com.ds.projectdawn.objects.entities.projectiles;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class SoulChargeEntity extends FireBallEntity
{

    public SoulChargeEntity(EntityType<? extends SoulChargeEntity> type, World worldIn) {
        super(type, worldIn);
        this.setParticle(ParticleTypes.SOUL);
    }

    public SoulChargeEntity(EntityType<? extends SoulChargeEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public SoulChargeEntity(EntityType<? extends SoulChargeEntity> type, LivingEntity shooter, World worldIn, int fireIn) {
        super(type, shooter, worldIn, fireIn);

    }
    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();

        if (owner == null) {
            entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage());
        } else {
            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity)entity;
                if(living.isInvertedHealAndHarm()) {
                    double bonusDamage = this.getBaseDamage() * .2D;
                    entity.hurt(DamageSource.indirectMagic(this, owner), (float) this.getBaseDamage() + (float) bonusDamage);
                }
            }
            else
                entity.hurt(DamageSource.indirectMagic(this, owner), (float) this.getBaseDamage());
            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
        }
        entity.setSecondsOnFire(this.getFire());
    }

    @Override
    public void onRemovedFromWorld()
    {
        if(!level.isClientSide)
            this.spawnLingeringCloud();

    }

    @Override
    public void tick()
    {
        Vector3d vector3d = this.getBoundingBox().getCenter();

        super.tick();

        if (this.level.isClientSide) {
            this.level.addParticle(this.getParticle(), vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
            this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
            this.level.addParticle(ParticleTypes.SMOKE, vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);

        }

        if(!this.level.isClientSide && this.tickCount == this.getLife())
        {
            remove();
            this.level.broadcastEntityEvent(this, (byte)3);
        }
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {

            Vector3d vector3d = this.getBoundingBox().getCenter();

            for(int i = 0; i < 8; ++i) {

                double d0 = this.random.nextGaussian() * 0.2D;
                double d1 = this.random.nextGaussian() * 0.2D;
                double d2 = this.random.nextGaussian() * 0.2D;


                this.level.addParticle(this.getParticle(), vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
                this.level.addParticle(ParticleTypes.SMOKE, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
                this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
            }
        }
    }

    @Override
    public boolean doesEffect(boolean doesEffect) { return false; }

    @Override
    public boolean isOnFire() {
        return false;
    }

    private void spawnLingeringCloud() {

        AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
        areaeffectcloudentity.setRadius(2.0F);
        areaeffectcloudentity.setRadiusOnUse(-0.5F);
        areaeffectcloudentity.setWaitTime(10);
        areaeffectcloudentity.setOwner((LivingEntity) this.getOwner());
        areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
        areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
        areaeffectcloudentity.setParticle((ParticleTypes.SOUL));

        AreaEffectCloudEntity areaeffectcloudentity1 = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
        areaeffectcloudentity1.setRadius(2.0F);
        areaeffectcloudentity1.setRadiusOnUse(-0.5F);
        areaeffectcloudentity1.setWaitTime(10);
        areaeffectcloudentity1.setOwner((LivingEntity) this.getOwner());
        areaeffectcloudentity1.setDuration(areaeffectcloudentity.getDuration() / 2);
        areaeffectcloudentity1.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
        areaeffectcloudentity1.setParticle((ParticleTypes.SMOKE));

                areaeffectcloudentity.addEffect(new EffectInstance(Effects.WITHER, 225, 0));
                areaeffectcloudentity1.addEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));


            this.level.addFreshEntity(areaeffectcloudentity);
            this.level.addFreshEntity(areaeffectcloudentity1);
        }


}
