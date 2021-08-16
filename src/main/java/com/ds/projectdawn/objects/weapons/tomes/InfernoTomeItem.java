package com.ds.projectdawn.objects.weapons.tomes;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.DiamondBoltEntity;
import com.ds.projectdawn.objects.entities.projectiles.FireBallEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.Random;

public class InfernoTomeItem extends AbstractTomeItem
{
    public InfernoTomeItem(IItemTier tierIn, float damageIn, int cooldownIn, Properties properties) {
        super(tierIn, damageIn, cooldownIn, properties);
        this.setXpAmount(6);
        this.setVelocity(1.5F);
        this.setInnacuracy(2.0F);
        this.setTomeName("INFERNO TOME");
        this.setSpellSound(SoundEvents.FIRECHARGE_USE);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        FireBallEntity fireBall = new FireBallEntity(EntityTypeInit.FIRE_BALL.get(), shooter, shooter.level,5);
        fireBall.setBaseDamage((double) this.getMagicDamage());
        fireBall.setOwner(shooter);
        fireBall.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return fireBall;
    }

}
