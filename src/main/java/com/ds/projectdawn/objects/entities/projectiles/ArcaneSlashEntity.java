package com.ds.projectdawn.objects.entities.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ArcaneSlashEntity extends ArcaneBoltEntity {

    public ArcaneSlashEntity(EntityType<? extends ArcaneSlashEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setParticle(null);
        this.setLife(10);
    }
    public ArcaneSlashEntity(EntityType<? extends ArcaneSlashEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(null);
        this.setLife(10);
    }
    public ArcaneSlashEntity(EntityType<? extends ArcaneSlashEntity> type, LivingEntity shooterIn, World worldIn) {
        super(type, shooterIn, worldIn);
        this.setParticle(null);
        this.setLife(10);
    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundEvents.PLAYER_SMALL_FALL; }


}
