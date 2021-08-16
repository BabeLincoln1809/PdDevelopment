package com.ds.projectdawn.capabilites;

import net.minecraft.util.SoundEvent;

import javax.swing.text.html.parser.Entity;

public interface IStaff {
    void createEntity(Entity entity);
    Entity getEntity();
    void castSound(SoundEvent castSound);
    SoundEvent getCastSound();
    void emptySound(SoundEvent emptySound);
    SoundEvent getEmptySound();
}
