package com.ds.projectdawn.objects.items;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.objects.entities.projectiles.DiamondBoltEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.Random;

public class InstrumentItem extends Item
{
    private SoundEvent instrument;

    public InstrumentItem(SoundEvent instrumentIn, Properties properties) {
        super(properties);

        this.instrument = instrumentIn;
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) entityLiving;

            playerIn.awardStat(Stats.ITEM_USED.get(this));

        }

    }
    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        playerIn.startUsingItem(handIn);
            return ActionResult.pass(itemstack);
    }
    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {

        World worldIn = player.level;

        Vector3d aim = player.getLookAngle();
        float y = (float) aim.y();

        if (!worldIn.isClientSide)
            worldIn.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), this.getInstrument(), SoundCategory.NEUTRAL, 1.0F, y - -1.25F);

    }

    protected SoundEvent getInstrument() { return instrument; }
}
