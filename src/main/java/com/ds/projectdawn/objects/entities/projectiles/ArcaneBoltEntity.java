package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.ParticleInit;
import com.ds.projectdawn.init.SoundInit;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.arguments.ParticleArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class ArcaneBoltEntity extends AbstractArrowEntity {

    private LivingEntity shooter;
    private IParticleData particle;
    private int life;

    public ArcaneBoltEntity(EntityType<? extends ArcaneBoltEntity> type, World worldIn) {
        super(type,worldIn);
        this.setParticle(ParticleInit.ARCANE_GLINT.get());
    }
    public ArcaneBoltEntity(EntityType<? extends ArcaneBoltEntity> type, double x, double y, double z, World worldIn) {
        this(type, worldIn);
        this.setPos(x, y, z);
        this.setParticle(ParticleInit.ARCANE_GLINT.get());

    }
    public ArcaneBoltEntity(EntityType<? extends ArcaneBoltEntity> type, LivingEntity shooterIn, World worldIn) {
        this(type, shooterIn.getX(), shooterIn.getEyeY() - (double)0.1F, shooterIn.getZ(), worldIn);
        this.shooter = shooterIn;
        this.setParticle(ParticleInit.ARCANE_GLINT.get());
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
    }
    public void tick()
    {
        super.tick();
        Vector3d vector3d = this.getBoundingBox().getCenter();
        if (this.level.isClientSide) {
                if(this.getParticle() != null)
                    this.level.addAlwaysVisibleParticle(this.getParticle(), vector3d.x, vector3d.y, vector3d.z, this.random.nextGaussian() * 0.05D, -this.getMotionDirection().getStepY() * 0.5D, this.random.nextGaussian() * 0.05D);
        }
        if(this.getLife() == 0)
            this.life = 45;
        boolean flag = this.tickCount >= this.getLife();
        if(!this.level.isClientSide && flag) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
    }
    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (!this.level.isClientSide)
        {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
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
            Vector3d vector3d = this.getBoundingBox().getCenter();

            for(int i = 0; i < 8; ++i) {

                double d0 = this.random.nextGaussian() * 0.2D;
                double d1 = this.random.nextGaussian() * 0.2D;
                double d2 = this.random.nextGaussian() * 0.2D;

                if(this.getParticle() != null)
                    this.level.addAlwaysVisibleParticle(this.getParticle(), vector3d.x, vector3d.y, vector3d.z, d0, d1, d2);
            }
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        nbt.putString("Particle", Registry.PARTICLE_TYPE.getKey(this.getParticle().getType()).toString());
        nbt.putShort("life", (short)this.life);
    }
    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        this.life = nbt.getShort("life");
        if (nbt.contains("Particle", 8)) {
            try {
                this.setParticle(ParticleArgument.readParticle(new StringReader(nbt.getString("Particle"))));
            } catch (CommandSyntaxException commandsyntaxexception) {
                LOGGER.warn("Couldn't load custom particle {}", nbt.getString("Particle"), commandsyntaxexception);
            }
        }
    }
    @Override
    public Entity getOwner() { return this.shooter; }
    public void setLife(int lifeIn) { this.life = lifeIn; }
    public int getLife() { return this.life; }
    public void setParticle(IParticleData particleIn) {
        this.particle = particleIn;
    }
    public IParticleData getParticle() { return this.particle; }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    @Override
    protected ItemStack getPickupItem() { return ItemStack.EMPTY; }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundInit.BASIC_MAGIC_HIT; }
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
