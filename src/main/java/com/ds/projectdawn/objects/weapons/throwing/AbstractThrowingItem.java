package com.ds.projectdawn.objects.weapons.throwing;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AbstractThrowingItem extends Item
{
    public float throwingDamage;
    public double velocity;

    public AbstractThrowingItem(float throwingDamageIn, double velocityIn, Properties properties) {
        super(properties);

        this.throwingDamage = throwingDamageIn;
        this.velocity = velocityIn;
    }

    public float getThrowingDamage() { return this.throwingDamage; }

    public double getVelocity() { return this.velocity; }

    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }
}
