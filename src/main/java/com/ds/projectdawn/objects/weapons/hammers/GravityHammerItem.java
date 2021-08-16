package com.ds.projectdawn.objects.weapons.hammers;

import net.minecraft.client.particle.Particle;
import net.minecraft.item.IItemTier;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;

public class GravityHammerItem extends EffectHammerItem {
    public GravityHammerItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Effect effectIn, int durationIn, int amplifierIn, boolean hitEffectIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, strengthIn, effectIn, durationIn, amplifierIn, hitEffectIn, properties);
    }

    @Override
    public boolean showPotionParticles() { return false; }
    @Override
    public IParticleData getSlamParticle() { return ParticleTypes.DRAGON_BREATH; }

}
