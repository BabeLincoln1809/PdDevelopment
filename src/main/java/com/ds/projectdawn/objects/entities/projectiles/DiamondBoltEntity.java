package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.ParticleInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class DiamondBoltEntity extends ArcaneBoltEntity
{
    public DiamondBoltEntity(EntityType<? extends DiamondBoltEntity> type, World worldIn) {
        super(type, worldIn);
        this.setParticle(ParticleInit.DIAMOND_GLINT.get());
    }
    public DiamondBoltEntity(EntityType<? extends DiamondBoltEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(ParticleInit.DIAMOND_GLINT.get());
    }
    public DiamondBoltEntity(EntityType<? extends DiamondBoltEntity> type, LivingEntity shooterIn, World worldIn) {
        super(type, shooterIn, worldIn);
        this.setParticle(ParticleInit.DIAMOND_GLINT.get());
    }

}
