package com.ds.projectdawn.capabilites;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CoolDownCapability
{

    @CapabilityInject(ICoolDown.class)
    public static Capability<ICoolDown> COOLDOWN_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(ICoolDown.class, new Storage(), DefaultCoolDownCapability::new);
    }

    public static class Storage implements Capability.IStorage<ICoolDown>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<ICoolDown> capability, ICoolDown instance, Direction side) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("cooldown", instance.getCoolDownTracker().toString());
            return nbt;
        }

        @Override
        public void readNBT(Capability<ICoolDown> capability, ICoolDown instance, Direction side, INBT nbt) {
            String coolDown = ((CompoundNBT) nbt).getString("cooldown");
            if(instance.getCoolDownTracker().toString().equals(coolDown))
                instance.createCoolDownTracker(instance.getCoolDownTracker());
        }
    }

}
