package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

public class FrostSparkEntity extends ArcaneBoltEntity {

    private int strength;
    private int duration;

    public FrostSparkEntity(EntityType<? extends FrostSparkEntity> type, World worldIn) {
        super(type,worldIn);
        this.setParticle(ParticleTypes.POOF);
    }
    public FrostSparkEntity(EntityType<? extends FrostSparkEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(ParticleTypes.POOF);
    }

    public FrostSparkEntity(EntityType<? extends FrostSparkEntity> type, LivingEntity shooterIn, World worldIn, int durationIn, int strengthIn) {
        super(type, shooterIn, worldIn);
        this.setParticle(ParticleTypes.POOF);
    }
    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();
        boolean flag = entity instanceof SnowGolemEntity || entity instanceof PolarBearEntity || entity instanceof StrayEntity;
        boolean flag1 = entity.fireImmune();
        double bonusDamage = this.getBaseDamage() * .2D;

        if (owner == null) {
            if(flag)
                entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage() - (float) bonusDamage);
            else if(flag1)
                entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage() + (float) bonusDamage);
            else
                entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage());
        } else {
            if(flag)
                entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage() - (float) bonusDamage);
            else if(flag1)
                entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage() + (float) bonusDamage);
            else
                entity.hurt(DamageSource.indirectMagic(this, this), (float)this.getBaseDamage());

            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
        }
        if(entity instanceof LivingEntity)
        {
            LivingEntity living = (LivingEntity)entity;
            living.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 250, 0));
        }
    }
    @Override
    protected void onHitBlock(BlockRayTraceResult result) {
        super.onHitBlock(result);
        
            if (!this.level.isClientSide) {

                BlockPos blockpos = result.getBlockPos();
                BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
                float f = (float) Math.min(16, 2);
                BlockPos.Mutable mutable = new BlockPos.Mutable();

                for (BlockPos pos : BlockPos.betweenClosed(blockpos.offset((double) (-f), 0.0D, (double) (-f)), blockpos.offset((double) f, -1.0D, (double) f))) {
                    if (pos.closerThan(this.position(), (double) f)) {
                        mutable.set(pos.getX(), pos.getY() + 1, pos.getZ());
                        BlockState blockstate1 = level.getBlockState(mutable);
                        if (blockstate1.isAir(level, mutable)) {
                            BlockState blockstate2 = level.getBlockState(pos);
                            boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(FlowingFluidBlock.LEVEL) == 0;
                            if (blockstate2.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(level, pos) && level.isUnobstructed(blockstate, pos, ISelectionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(this, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos), net.minecraft.util.Direction.UP)) {
                                level.setBlockAndUpdate(pos, blockstate);
                                level.getBlockTicks().scheduleTick(pos, Blocks.FROSTED_ICE, MathHelper.nextInt(this.random, 60, 120));
                                this.remove();
                                this.level.broadcastEntityEvent(this, (byte)3);
                            }
                        }
                    }
                }
            }
    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundInit.ICE_HIT; }
}