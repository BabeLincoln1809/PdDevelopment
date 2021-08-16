package com.ds.projectdawn.capabilites;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class CapabilityHelper {

    public CapabilityHelper() {
    }

    @Nullable
    public static ICoolDown getCoolDownCapability(ItemStack item) {
        LazyOptional<ICoolDown> lazyCap = item.getCapability(CoolDownCapability.COOLDOWN_CAPABILITY);
        return lazyCap.isPresent() ? (ICoolDown)lazyCap.orElseThrow(() -> {
            return new IllegalStateException("Couldn't get the combo capability from the Entity!");
        }) : null;
    }
}
