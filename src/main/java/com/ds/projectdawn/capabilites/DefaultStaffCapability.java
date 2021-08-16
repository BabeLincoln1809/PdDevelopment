package com.ds.projectdawn.capabilites;

import net.minecraft.util.SoundEvent;

import javax.swing.text.html.parser.Entity;

public class DefaultStaffCapability implements IStaff {

    Entity projectile;
    SoundEvent cast;
    SoundEvent empty;

    @Override
    public void createEntity(Entity entity) { this.projectile = entity; }

    @Override
    public Entity getEntity() { return this.projectile; }

    @Override
    public void castSound(SoundEvent castSound) { this.cast = castSound; }

    @Override
    public SoundEvent getCastSound() { return this.cast; }

    @Override
    public void emptySound(SoundEvent emptySound) { this.empty = emptySound; }

    @Override
    public SoundEvent getEmptySound() { return this.empty; }
}
