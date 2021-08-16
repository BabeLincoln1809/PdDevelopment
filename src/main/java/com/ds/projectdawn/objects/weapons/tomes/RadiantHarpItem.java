package com.ds.projectdawn.objects.weapons.tomes;

import com.ds.projectdawn.init.SoundInit;
import net.minecraft.item.IItemTier;

public class RadiantHarpItem extends MagicHarpItem {
    public RadiantHarpItem(IItemTier tierIn, float damageIn, int cooldownIn, Properties properties) {
        super(tierIn, damageIn, cooldownIn, properties);
        this.setTomeName("RADIANT HARP");
        this.setSpellSound(SoundInit.RADIANTHARP_USE);
        this.setEmptySound(SoundInit.RADIANTHARP_USE);
    }
}
