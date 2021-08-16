package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.ItemInit;
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

public class BlazeBombEntity extends ProjectileItemEntity
{
    public int explosionPower = 1;

    public BlazeBombEntity(EntityType<BlazeBombEntity> type, World worldIn) {
        super(type,worldIn);

    }
    public BlazeBombEntity(LivingEntity entity, World worldIn){
        super(EntityTypeInit.BLAZE_BOMB.get(), entity, worldIn);

    }

    public BlazeBombEntity(double x, double y, double z, World worldIn){
        super(EntityTypeInit.BLAZE_BOMB.get(), x, y, z, worldIn);

    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.BLAZE_BOMB.get().asItem();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        //int i = entity instanceof BlazeEntity ? 3 : 0;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), 5);
        entity.setSecondsOnFire(3);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);

        if(!this.level.isClientSide)
        {
            this.level.broadcastEntityEvent(this, (byte)3);
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
            this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, flag ? Explosion.Mode.NONE : Explosion.Mode.NONE);
            this.remove();
        }
    }

    @Override
    public void onAddedToWorld() {

        if(!this.level.isClientSide)
            this.level.playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), SoundEvents.TNT_PRIMED, SoundCategory.NEUTRAL, 1.0F, 1.0F);

    }

    public void tick()
    {
        Vector3d vector3d = this.getBoundingBox().getCenter();

        super.tick();
        if (this.level.isClientSide) {
            this.level.addParticle(ParticleTypes.SMOKE, vector3d.x, vector3d.y - 0.3D, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
        }

        if(!this.level.isClientSide && this.tickCount == 50) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
            this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), this.explosionPower, flag, flag ? Explosion.Mode.NONE : Explosion.Mode.NONE);
            this.remove();
        }
    }
}
