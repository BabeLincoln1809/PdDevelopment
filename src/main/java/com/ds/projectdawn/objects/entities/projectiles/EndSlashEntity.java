package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.ParticleInit;
import com.ds.projectdawn.init.SoundInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EndSlashEntity extends AbstractArrowEntity {

    private LivingEntity shooter;

    public EndSlashEntity(EntityType<? extends EndSlashEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EndSlashEntity(EntityType<? extends EndSlashEntity> type, double x, double y, double z, World worldIn) {
        this(type, worldIn);
        this.setPos(x, y, z);
    }
    public EndSlashEntity(EntityType<? extends EndSlashEntity> type, LivingEntity shooterIn, World worldIn) {
        this(type, shooterIn.getX(), shooterIn.getEyeY() - (double)0.1F, shooterIn.getZ(), worldIn);
        this.shooter = shooterIn;
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
    }
    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();

        if (owner == null) {
            entity.hurt(DamageSource.thrown(this, this), (float)this.getBaseDamage());
        } else {
            BlockPos pos = this.getOwner().blockPosition();
            if(owner.level.isNight() || owner.level.getBiome(pos).getBiomeCategory() == Biome.Category.THEEND) {
                double bonusDamage = this.getBaseDamage() * .2D;
                entity.hurt(DamageSource.mobAttack((LivingEntity) owner), (float) this.getBaseDamage() + (float) bonusDamage);
            }
            else
                entity.hurt(DamageSource.mobAttack((LivingEntity)owner), (float)this.getBaseDamage());
            ((LivingEntity)owner).setLastHurtMob(entity);
        }
    }
    @Override
    public void tick() {
        super.tick();
        if(!this.level.isClientSide && this.tickCount >= 5) { remove(); }
    }
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if (!this.level.isClientSide)
            this.level.broadcastEntityEvent(this, (byte)3);
    }
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            Vector3d vec = this.getBoundingBox().getCenter();
            for(int i = 0; i < 8; ++i) {
                double d0 = this.random.nextGaussian() * 0.2D;
                this.level.addParticle(ParticleInit.PURPUR_GLINT.get(), this.getX() * d0, this.getY(), this.getZ() * d0, 0, 0, 0);
            }
        }
    }
    @Override
    public Entity getOwner() { return this.shooter; }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundEvents.PLAYER_SMALL_FALL; }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    protected ItemStack getPickupItem() { return ItemStack.EMPTY; }
    @OnlyIn(Dist.CLIENT)
    public float getBrightness() {
        return 15728880;
    }
    @Override
    public boolean isNoGravity() { return true; }
    @Override
    public boolean fireImmune() { return true; }
    @Override
    protected float getWaterInertia() {
        return 1.0F;
    }

}
