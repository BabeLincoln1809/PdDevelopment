package com.ds.projectdawn.effects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class DrainedEffect extends Effect
{

    public DrainedEffect() {
        super(EffectType.HARMFUL, 10425141);
    }

    public void performEffect(LivingEntity entity, int amplifier) {

        if(entity instanceof LivingEntity) {
            entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(entity.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) * -0.1F);
            entity.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(entity.getAttributeBaseValue(Attributes.ATTACK_SPEED) * -0.1F);
        }


    }


    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {

        if (source == null) {
            entityLivingBaseIn.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(entityLivingBaseIn.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) * -0.1F);
            entityLivingBaseIn.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(entityLivingBaseIn.getAttributeBaseValue(Attributes.ATTACK_SPEED) * -0.1F);
        }
    }

    public boolean isReady(int duration, int amplifier) {
        return duration % 100 == 0;
    }
}

