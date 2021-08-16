package com.ds.projectdawn.objects.weapons.hammers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Random;

public class EffectHammerItem extends HammerItem {
    private final Effect effect;
    private final int duration;
    private final int amplifier;
    private final boolean hitEffect;

    public EffectHammerItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Effect effectIn, int durationIn, int amplifierIn, boolean hitEffectIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, strengthIn, properties);

        this.effect = effectIn;
        this.duration = durationIn;
        this.amplifier = amplifierIn;
        this.hitEffect = hitEffectIn;
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(this.hitEffect) {
            if (this.effect != null) {
                target.addEffect(new EffectInstance(this.effect, this.duration, this.amplifier, this.showPotionParticles(), this.showPotionParticles(), true));
            } else
                target.setSecondsOnFire(this.duration);
        }
        this.doKnockBack(attacker, target);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    @Override
    public void slam(LivingEntity user) {
        if (user.isAlive()) {
            Vector3d look = user.getLookAngle();
            double d0 = (look.x() * this.getStrength()) * 10;
            double d1 = (look.z() * this.getStrength()) * 10;
            for (Entity entity : user.level.getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().expandTowards(d0, 1.25D, d1), ENTITY)) {
                if (entity != user) {
                    if(entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity)entity;
                        if (this.effect != null) {
                            livingEntity.addEffect(new EffectInstance(this.effect, this.duration, this.amplifier, this.showPotionParticles(), this.showPotionParticles(), true));
                        } else
                            livingEntity.setSecondsOnFire(this.duration);
                    }
                    entity.hurt(DamageSource.mobAttack(user), this.getAttackDamage() / 2);
                    this.launch(entity, user);
                }
            }
        }
    }
    @Override
    public void spawnParticles(LivingEntity entity) {
        Random offset = new Random();
        Vector3d vector3d = entity.getBoundingBox().getCenter();
        Vector3d look = entity.getLookAngle();
        for (int i = 0; i < 40; ++i) {
            double d0 = (look.x() * this.getStrength()) * 1.5D;
            double d1 = (look.z() * this.getStrength()) * 1.5D;
            entity.level.addParticle(this.getSlamParticle(), vector3d.x, vector3d.y, vector3d.z, d0 * offset.nextDouble(), 0, d1 * offset.nextDouble());
            if(this.getOptionalSlamParticle() != null)
                entity.level.addParticle(this.getOptionalSlamParticle(), vector3d.x, vector3d.y, vector3d.z, d0 * offset.nextDouble(), 0, d1 * offset.nextDouble());
        }
    }
    public boolean showPotionParticles() { return true; }
    public IParticleData getOptionalSlamParticle() { return null;}
    public Effect getEffect() { return this.effect;}

}
