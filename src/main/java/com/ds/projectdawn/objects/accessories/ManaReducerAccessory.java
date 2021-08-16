package com.ds.projectdawn.objects.accessories;

import net.minecraft.item.Item;

public class ManaReducerAccessory extends Item
{
    public int reductionAmount;

    public ManaReducerAccessory(int reductionAmountIn, Properties properties)
    {
        super(properties);

        this.reductionAmount = reductionAmountIn;
    }

    public int getReductionAmount() { return this.reductionAmount;};

}
