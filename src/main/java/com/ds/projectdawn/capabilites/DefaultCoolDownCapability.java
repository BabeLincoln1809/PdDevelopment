package com.ds.projectdawn.capabilites;

import net.minecraft.util.CooldownTracker;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class DefaultCoolDownCapability implements ICoolDown
{
    CooldownTracker tracker;

    @Override
    public void createCoolDownTracker(CooldownTracker tracker) { this.tracker = tracker; }

    @Override
    public CooldownTracker getCoolDownTracker() { return tracker; }

}
