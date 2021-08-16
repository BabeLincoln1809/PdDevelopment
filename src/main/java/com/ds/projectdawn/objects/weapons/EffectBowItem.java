package com.ds.projectdawn.objects.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EffectBowItem extends PDBowItem
{
    private final double damage;

    private final int effectType;

    private final int effectTime;
    private final int effectStrength;

    public EffectBowItem(double damageIn, int enchantabilityIn, int effectTypeIn, int effectTimeIn, int effectStrengthIn, Properties builder) {
        super(damageIn, enchantabilityIn, builder);

        this.effectType = effectTypeIn;;

        this.effectTime = effectTimeIn;
        this.effectStrength = effectStrengthIn;
        this.damage = damageIn;
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = playerentity.getProjectile(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float velocity = getArrowVelocity(i);
                if (!((double)velocity < 0.1D)) {
                    boolean flag1 = playerentity.abilities.instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.shootFromRotation(playerentity, playerentity.xRot, playerentity.yRot, 0.0F, velocity * 3.0F, 1.0F);
                        abstractarrowentity.setBaseDamage(damage);
                        if (velocity == 1.0F) {
                            abstractarrowentity.setCritArrow(true);
                        }

                        int power = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                        if (power > 0) {
                            abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double)power * 0.5D + 0.5D);
                        }

                        int punch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
                        if (punch > 0) {
                            abstractarrowentity.setKnockback(punch);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0 && effectType != 1 && effectType != 2) {
                            abstractarrowentity.setSecondsOnFire(100);
                        }

                        //Firebrand
                        if (effectType == 1) {
                            abstractarrowentity.setSecondsOnFire(effectTime);
                        }

                        stack.hurtAndBreak(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.broadcastBreakEvent(playerentity.getUsedItemHand());
                        });
                        if (flag1 || playerentity.abilities.instabuild && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addFreshEntity(abstractarrowentity);
                    }

                    worldIn.playSound((PlayerEntity)null, playerentity.getX(), playerentity.getY(), playerentity.getY(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
                    if (!flag1 && !playerentity.abilities.instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.removeItem(itemstack);
                        }
                    }

                    playerentity.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
}
