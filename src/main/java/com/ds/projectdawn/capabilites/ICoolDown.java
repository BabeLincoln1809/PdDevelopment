package com.ds.projectdawn.capabilites;

import net.minecraft.util.CooldownTracker;

public interface ICoolDown
{
    void createCoolDownTracker(CooldownTracker tracker);
    CooldownTracker getCoolDownTracker();

}
