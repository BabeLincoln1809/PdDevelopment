package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.SoundInit;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Predicate;

public class VoidChargeEntity extends ArcaneBoltEntity {
    private static final Predicate<Entity> IS_ALIVE_AND_NOT_ITSELF = (entity) -> {
        return entity.isAlive() && !(entity instanceof VoidChargeEntity);
    };

    public VoidChargeEntity(EntityType<? extends VoidChargeEntity> type, World worldIn) {
        super(type,worldIn);
        this.setParticle(ParticleTypes.DRAGON_BREATH);
    }
    public VoidChargeEntity(EntityType<? extends VoidChargeEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(ParticleTypes.DRAGON_BREATH);
    }
    public VoidChargeEntity(EntityType<? extends VoidChargeEntity> type, LivingEntity shooterIn, World worldIn) {
        super(type, shooterIn, worldIn);
        this.setParticle(ParticleTypes.DRAGON_BREATH);
    }
    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();

        if (owner == null) {
            entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage());
        } else {
            BlockPos pos = this.getOwner().blockPosition();
            if(owner.level.isNight() || owner.level.getBiome(pos).getBiomeCategory() == Biome.Category.THEEND) {
                double bonusDamage = this.getBaseDamage() * .2D;
                entity.hurt(DamageSource.indirectMagic(this, owner), (float) this.getBaseDamage() + (float) bonusDamage);
            }
            else
                entity.hurt(DamageSource.indirectMagic(this, owner), (float)this.getBaseDamage());
            ((LivingEntity)owner).setLastHurtMob(entity);
        }
    }
    @Override
    public void tick()
    {
        Vector3d vector3d = this.getBoundingBox().getCenter();

        super.tick();
        if (this.level.isClientSide) {
            if(this.getParticle() != null) {
                this.level.addParticle(this.getParticle(), vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getMotionDirection().getStepY() * 0.5D, this.random.nextGaussian() * 0.05D);
                this.level.addParticle(ParticleTypes.DRAGON_BREATH, vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
            }
        }

        if(!this.level.isClientSide && this.tickCount == this.getLife())
        {
            remove();
            this.level.broadcastEntityEvent(this, (byte)3);
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {

            Vector3d vector3d = this.getBoundingBox().getCenter();

            for(int i = 0; i < 8; ++i) {

                double d0 = this.random.nextGaussian() * 0.2D;
                double d1 = this.random.nextGaussian() * 0.2D;
                double d2 = this.random.nextGaussian() * 0.2D;


                this.level.addParticle(ParticleTypes.DRAGON_BREATH, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
                this.level.addParticle(ParticleTypes.PORTAL, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
            }
        }
    }
    @Override
    public void onRemovedFromWorld() { this.voidExplosion(); this.spawnVoidCloud();}

    private void voidExplosion() {
        for(Entity entity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(2.5D), IS_ALIVE_AND_NOT_ITSELF)) {
            Entity owner = this.getOwner();
            double d = this.getBaseDamage() * .5D;

            if (owner == null) {
                entity.hurt(DamageSource.indirectMagic(this, this), (float) d);
            } else {
                entity.hurt(DamageSource.indirectMagic(this, owner), (float) d);
                if (owner instanceof LivingEntity) {
                    ((LivingEntity) owner).setLastHurtMob(entity);
                }
            }
            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            this.pull(entity);
            this.launch(entity);

            for (int i = 0; i < 32; ++i) {
                entity.level.addParticle(ParticleTypes.PORTAL, entity.getX(), entity.getY() + this.random.nextDouble() * 2.0D, entity.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
            }
        }

        Vector3d vector3d = this.getBoundingBox().getCenter();

        for(int i = 0; i < 40; ++i) {
            double d0 = this.random.nextGaussian() * 0.2D;
            double d1 = this.random.nextGaussian() * 0.2D;
            double d2 = this.random.nextGaussian() * 0.2D;
            this.level.addParticle(ParticleTypes.PORTAL, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
            this.level.addParticle(ParticleTypes.DRAGON_BREATH, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
        }
    }

    private void pull(Entity target) {
        if (target instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)target;
            LivingEntity owner = (LivingEntity)this.getOwner();
            boolean flag = living instanceof EnderDragonEntity;
            boolean flag1 = living == owner;
            if(!flag || !flag1) {
                net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(living, this.getX(), this.getY(), this.getZ(), 4);
                if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) {
                    if (living.isPassenger()) {
                        living.stopRiding();
                    }
                    living.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                }
            }
        }
    }
    private void launch(Entity target) {
        double d0 = target.getX() - this.getX();
        double d1 = target.getZ() - this.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        target.push(d0 / d2 * 0.35D, 0.2D, d1 / d2 * 0.35D);
    }

    private void spawnVoidCloud() {

        AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
        areaeffectcloudentity.setRadius(2.0F);
        areaeffectcloudentity.setRadiusOnUse(-0.5F);
        areaeffectcloudentity.setWaitTime(10);
        areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
        areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
        areaeffectcloudentity.setOwner((LivingEntity)this.getOwner());
        areaeffectcloudentity.setParticle((ParticleTypes.DRAGON_BREATH));
        areaeffectcloudentity.addEffect(new EffectInstance(Effects.HARM, 1, 1));

        this.level.addFreshEntity(areaeffectcloudentity);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundInit.VOIDCHARGE_HIT; }

}