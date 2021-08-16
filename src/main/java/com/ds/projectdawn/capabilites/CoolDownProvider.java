package com.ds.projectdawn.capabilites;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class CoolDownProvider implements ICapabilitySerializable<CompoundNBT>
{

    private final DefaultCoolDownCapability coolDown = new DefaultCoolDownCapability();
    private final LazyOptional<ICoolDown> coolDownLazy = LazyOptional.of(() -> coolDown);

    public void invalidate() { coolDownLazy.invalidate(); }

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability( Capability<T> cap, Direction side) {
        return coolDownLazy.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if(CoolDownCapability.COOLDOWN_CAPABILITY == null)
            return new CompoundNBT();
        else
            return (CompoundNBT) CoolDownCapability.COOLDOWN_CAPABILITY.writeNBT(coolDown, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if(CoolDownCapability.COOLDOWN_CAPABILITY != null)
            CoolDownCapability.COOLDOWN_CAPABILITY.readNBT(coolDown, null, nbt);
    }
}
