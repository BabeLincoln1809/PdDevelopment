package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.ParticleInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class RubyBoltEntity extends ArcaneBoltEntity
{
    public RubyBoltEntity(EntityType<? extends RubyBoltEntity> type, World worldIn) {
        super(type, worldIn);
        this.setParticle(ParticleInit.RUBY_GLINT.get());
    }
    public RubyBoltEntity(EntityType<? extends RubyBoltEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(ParticleInit.RUBY_GLINT.get());
    }
    public RubyBoltEntity(EntityType<? extends RubyBoltEntity> type, LivingEntity shooterIn, World worldIn) {
        super(type, shooterIn, worldIn);
        this.setParticle(ParticleInit.RUBY_GLINT.get());
    }
}
