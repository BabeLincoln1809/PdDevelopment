package com.ds.projectdawn.objects.weapons;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PDShieldItem extends TieredItem
{

    public PDShieldItem(IItemTier tierIn, Properties builder) {
        super(tierIn, builder);

        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        playerIn.startUsingItem(handIn);
        return ActionResult.consume(itemstack);
    }

    @Override
    public boolean isShield(ItemStack stack, LivingEntity entity) { return true; }

}
