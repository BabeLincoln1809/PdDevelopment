package com.ds.projectdawn.objects.accessories;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AccessoryItem extends Item
{
    public AccessoryItem(Properties properties) {
        super(properties);
    }
    @Override
    public int getItemStackLimit(ItemStack stack) { return 1; }
}
