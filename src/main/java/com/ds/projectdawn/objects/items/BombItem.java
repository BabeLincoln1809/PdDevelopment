package com.ds.projectdawn.objects.items;

import com.ds.projectdawn.init.ItemInit;
import com.ds.projectdawn.objects.entities.projectiles.BlazeBombEntity;
import com.ds.projectdawn.objects.entities.projectiles.PowPowBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BombItem extends Item
{
    public boolean hasDurability;
    public int cooldown;

    public BombItem(int cooldownIn, boolean hasDurabilityIn, Properties properties) {
        super(properties);

        this.hasDurability = hasDurabilityIn;
        this.cooldown = cooldownIn;

    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        Vector3d aim = playerIn.getLookAngle();

        worldIn.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if(!worldIn.isClientSide)
        {
            if(itemstack.getItem().asItem() == ItemInit.POWPOW_BOMB.get())
            {
                PowPowBombEntity powpowbomb = new PowPowBombEntity(playerIn, worldIn);
                powpowbomb.setItem(itemstack);
                powpowbomb.shoot(aim.x, aim.y, aim.z, 0.75f, 1.0f);
                worldIn.addFreshEntity(powpowbomb);
            }
            if(itemstack.getItem().asItem() == ItemInit.BLAZE_BOMB.get())
            {
                BlazeBombEntity blazebomb = new BlazeBombEntity(playerIn, worldIn);
                blazebomb.setItem(itemstack);
                blazebomb.shoot(aim.x, aim.y, aim.z, 0.75f, 1.0f);
                worldIn.addFreshEntity(blazebomb);
            }
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));

        playerIn.getCooldowns().addCooldown(this, this.cooldown);


        if(!playerIn.isCreative()) {
            if (this.hasDurability) {
                itemstack.hurtAndBreak(2, playerIn, (p_220044_0_) -> {
                    p_220044_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
                });
            } else
                itemstack.shrink(1);
        }

        return ActionResult.success(itemstack);

    }

    @Override
    public boolean isRepairable(ItemStack stack) {

        if(this.hasDurability)
            return super.isRepairable(stack);
        else
            return false;
    }

}