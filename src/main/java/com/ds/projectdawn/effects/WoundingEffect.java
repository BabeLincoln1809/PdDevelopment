package com.ds.projectdawn.effects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;
import java.util.Random;

public class WoundingEffect extends Effect {

    public WoundingEffect() { super(EffectType.HARMFUL, 10425141); }
    @Override
    public void applyEffectTick(LivingEntity entity, int duration) {
        Random rand = new Random();
        entity.hurt(DamageSource.GENERIC, rand.nextInt(2)+1);
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) { return duration % 100 == 0; }
}

