package com.ds.projectdawn.objects.weapons.swords;

import com.ds.projectdawn.events.LeftClickEvent;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class AbstractProjectileSwordItem extends SwordItem
{
    private Entity projectile;
    private String swordName;
    private SoundEvent castProjectile;
    private boolean costsXp;

    public int xpAmount;
    private final float projectileDamage;
    private final float velocity;

    public AbstractProjectileSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float projectileDamageIn, float velocityIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);

        this.projectileDamage = projectileDamageIn;
        this.velocity = velocityIn;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(entityLiving instanceof PlayerEntity)
            LeftClickEvent.onLeftClickSword((PlayerEntity)entityLiving, stack);

        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

    public float getProjectileDamage() {
        return this.projectileDamage - 1;
    }
    public float getVelocity() {
        return this.velocity;
    }

    //public void setCostsXp(boolean costsXpIn) { this.costsXp = costsXpIn; }
    public boolean getCostsXp() { return this.costsXp; }

    //public void setCastSound(SoundEvent soundEventIn) { this.castProjectile = soundEventIn; }
    public SoundEvent getCastSound() { return this.castProjectile; }

    //public void setProjectile(Entity projectileIn){ this.projectile = projectileIn; }
    public Entity getProjectile(LivingEntity shooter) { return this.projectile; }

    //public void setSwordName(String swordNameIn) { this.swordName = swordNameIn; }
    public String getSwordName() { return this.swordName; }
    public int getXpAmount() { return this.xpAmount; }

}
