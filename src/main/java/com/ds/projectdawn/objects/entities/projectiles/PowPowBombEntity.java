package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.ItemInit;
import com.ds.projectdawn.init.ParticleInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Predicate;

public class PowPowBombEntity extends ProjectileItemEntity
{
    private static final Predicate<Entity> IS_ALIVE_AND_NOT_ITSELF = (entity) -> {
        return entity.isAlive() && !(entity instanceof PowPowBombEntity);
    };

    public PowPowBombEntity(EntityType<PowPowBombEntity> type, World worldIn) {
        super(type,worldIn);

    }
    public PowPowBombEntity(LivingEntity entity, World worldIn){
        super(EntityTypeInit.POWPOW_BOMB.get(), entity, worldIn);

    }
    public PowPowBombEntity(double x, double y, double z, World worldIn){
        super(EntityTypeInit.POWPOW_BOMB.get(), x, y, z, worldIn);

    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        entity.hurt(DamageSource.thrown(this, this.getOwner()), 2);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);

        if(!this.level.isClientSide)
        {
            this.remove();
        }

    }

    @Override
    public void onAddedToWorld() {

        if(!this.level.isClientSide)
            this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.TNT_PRIMED, SoundCategory.NEUTRAL, 1.0F, 1.0F);

    }

    @Override
    public void onRemovedFromWorld() {
        this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        this.explosion();
    }

    private void explosion() {
        for(Entity entity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(3.0D), IS_ALIVE_AND_NOT_ITSELF)) {
            if (entity != this.getOwner()) {
                entity.hurt(DamageSource.thrown(this, this.getOwner()), 6);
                this.launch(entity);
            }
        }

        Vector3d vector3d = this.getBoundingBox().getCenter();

        for(int i = 0; i < 60; ++i) {
            double d0 = this.random.nextGaussian() * 0.25D;
            double d1 = this.random.nextGaussian() * 0.25D;
            double d2 = this.random.nextGaussian() * 0.25D;
            this.level.addParticle(ParticleTypes.POOF, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
            this.level.addParticle(ParticleTypes.ASH, vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
        }
    }

    private void launch(Entity target) {
        double d0 = target.getX() - this.getX();
        double d1 = target.getZ() - this.getZ();
        double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
        target.push(d0 / d2 * 0.35D, 0.2D, d1 / d2 * 0.35D);
    }

    public void tick()
    {
        Vector3d vector3d = this.getBoundingBox().getCenter();

        super.tick();
        if (this.level.isClientSide) {
            this.level.addParticle(ParticleTypes.SMOKE, vector3d.x, vector3d.y - 0.3D, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
        }

        if(!this.level.isClientSide && this.tickCount == 50) {
            this.remove();
        }

    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.POWPOW_BOMB.get().asItem();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
