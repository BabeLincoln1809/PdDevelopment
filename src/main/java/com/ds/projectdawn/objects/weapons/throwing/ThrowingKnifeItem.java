package com.ds.projectdawn.objects.weapons.throwing;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Random;

public class ThrowingKnifeItem extends AbstractThrowingItem
{
    public ThrowingKnifeItem(float throwingDamageIn, double velocityIn, Properties properties) {
        super(throwingDamageIn, velocityIn, properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        Vector3d aim = playerIn.getLookAngle();
        Random rand = new Random();


        worldIn.playSound((PlayerEntity) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));

        if(!playerIn.abilities.instabuild)
            stack.shrink(1);

        return ActionResult.success(stack);
    }
}
