package com.ds.projectdawn.objects.weapons.swords;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.ArcaneSlashEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArcaneSwordItem extends AbstractProjectileSwordItem
{
    public ArcaneSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float projectileDamageIn, float velocityIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, projectileDamageIn, velocityIn, builderIn);

    }
    @Override
    public Entity getProjectile(LivingEntity shooter) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        ArcaneSlashEntity arcaneSlash = new ArcaneSlashEntity(EntityTypeInit.ARCANE_SLASH.get(), shooter, shooter.level);
        arcaneSlash.setBaseDamage((double) this.getProjectileDamage());
        arcaneSlash.setOwner(shooter);
        arcaneSlash.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), 1.50F);
        return arcaneSlash;
    }
    @Override
    public String getSwordName() { String swordNameIn = "ARCANE SWORD"; return swordNameIn;}
    @Override
    public SoundEvent getCastSound() { return SoundInit.ARCANE_SLASH; }
    @Override
    public boolean getCostsXp() { return true; }
    @Override
    public int getXpAmount() { return 8; }
}
