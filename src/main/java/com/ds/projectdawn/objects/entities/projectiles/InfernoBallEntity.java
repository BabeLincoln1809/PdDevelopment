package com.ds.projectdawn.objects.entities.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.function.Predicate;

public class InfernoBallEntity extends FireBallEntity {
    private static final Predicate<Entity> IS_ALIVE_AND_NOT_ITSELF = (entity) -> {
        return entity.isAlive() && !(entity instanceof InfernoBallEntity);
    };

    public InfernoBallEntity(EntityType<? extends InfernoBallEntity> type, World worldIn) {
        super(type, worldIn);
    }
    public InfernoBallEntity(EntityType<? extends InfernoBallEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }
    public InfernoBallEntity(EntityType<? extends InfernoBallEntity> type, LivingEntity shooter, World worldIn, int fireIn) {
        super(type, shooter, worldIn, fireIn);
    }
    @Override
    public void onRemovedFromWorld() { this.fireExplosion(); }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();
        double bonusDamage = this.getBaseDamage() * .2D;

        if (owner == null) {
            entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage());
            entity.setSecondsOnFire(this.getFire());
        } else {
            BlockPos pos = this.getOwner().blockPosition();
            if(owner.level.getBiome(pos).getBiomeCategory() == Biome.Category.NETHER)
                entity.hurt(DamageSource.indirectMagic(this, owner), (float) this.getBaseDamage() + (float) bonusDamage);
            else
                entity.hurt(DamageSource.indirectMagic(this, owner), (float) this.getBaseDamage());
            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
            entity.setSecondsOnFire(this.getFire());
        }

    }

    private void fireExplosion() {
            for(Entity entity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(2.5D), IS_ALIVE_AND_NOT_ITSELF)) {
                Entity owner = this.getOwner();
                double i = this.getBaseDamage() * .5D;

                if (owner == null) {
                    entity.hurt(DamageSource.indirectMagic(this, this), (float) i);
                } else {
                    entity.hurt(DamageSource.indirectMagic(this, owner), (float) i);
                    if (owner instanceof LivingEntity) {
                        ((LivingEntity)owner).setLastHurtMob(entity);
                    }
                }
                    entity.setSecondsOnFire(3);
                    this.launch(entity);
            }

            Vector3d vector3d = this.getBoundingBox().getCenter();

            for(int i = 0; i < 40; ++i) {
                double d0 = this.random.nextGaussian() * 0.2D;
                double d1 = this.random.nextGaussian() * 0.2D;
                double d2 = this.random.nextGaussian() * 0.2D;
                this.level.addParticle(ParticleTypes.FLAME, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
                this.level.addParticle(ParticleTypes.SMOKE, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
                this.level.addParticle(ParticleTypes.LAVA, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
            }
    }

    private void launch(Entity target) {
        double d0 = target.getX() - this.getX();
        double d1 = target.getZ() - this.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        target.push(d0 / d2 * 0.35D, 0.2D, d1 / d2 * 0.35D);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundEvents.GENERIC_EXPLODE; }

}

