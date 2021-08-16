package com.ds.projectdawn.objects.accessories;

import com.ds.projectdawn.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class HealingAccessoryItem extends AccessoryItem
{
    private final int chance;
    private final int healthAmountMax;

    public HealingAccessoryItem(int chanceIn, int healthMax,Properties properties) {
        super(properties);

        this.chance = chanceIn;
        this.healthAmountMax = healthMax;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(entityIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entityIn;
            Random rand = new Random();

            if (stack.getItem() == this && player.inventory.countItem(this) == 1) {
                int healChance = rand.nextInt(chance) + 1;

                if (healChance == 1 && player.getHealth() < player.getMaxHealth()) {
                    int heart = rand.nextInt(healthAmountMax) + 1;
                    player.heal(heart);
                }

            }
        }

    }
}
