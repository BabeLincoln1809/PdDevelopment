package com.ds.projectdawn.objects.accessories;

import com.ds.projectdawn.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.Random;

public class CurativeAccessoryItem extends AccessoryItem
{
    private final Effect effectType;
    private final boolean isMischievous;

    public CurativeAccessoryItem(Effect effectTypeIn, boolean isMischievousIn, Properties properties) {
        super(properties);

        this.effectType = effectTypeIn;
        this.isMischievous = isMischievousIn;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (entityIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityIn;

            if (this.effectType != null) {
                if(this.isMischievous)
                {
                    player.addEffect(new EffectInstance(this.effectType, 200, 0, false, false, true));
                }
                else {
                    if (player.hasEffect(this.effectType))
                        player.removeEffect(this.effectType);
                }
            }
        }
    }

    public Effect getEffect() { return this.effectType; }
    public boolean getMischievous() { return this.isMischievous; }

}
