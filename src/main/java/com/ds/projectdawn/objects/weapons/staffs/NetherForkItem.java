package com.ds.projectdawn.objects.weapons.staffs;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.InfernoBallEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class NetherForkItem extends AbstractStaffItem
{
    public NetherForkItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tierIn, attackDamageIn, attackSpeedIn, properties);
        this.setXpAmount(22);
        this.setVelocity(1.50F);
        this.setInnacuracy(1.25F);
        this.setStaffName("NETHER FORK");
        this.setSpellSound(SoundEvents.FIRECHARGE_USE);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter, ItemStack stack) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        InfernoBallEntity infernoBall = new InfernoBallEntity(EntityTypeInit.INFERNO_BALL.get(), shooter, shooter.level, 7);
        infernoBall.setLife(55);
        infernoBall.setBaseDamage((double) this.getMagicDamage());
        infernoBall.setOwner(shooter);
        infernoBall.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return infernoBall;
    }
}
