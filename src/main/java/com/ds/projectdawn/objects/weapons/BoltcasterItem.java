package com.ds.projectdawn.objects.weapons;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.capabilites.CapabilityHelper;
import com.ds.projectdawn.capabilites.CoolDownCapability;
import com.ds.projectdawn.capabilites.CoolDownProvider;
import com.ds.projectdawn.capabilites.ICoolDown;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class BoltcasterItem extends ShootableItem implements IVanishable {

    private final double damage;
    private final int velocity;

    public BoltcasterItem(double damageIn,int velocityIn, Properties builder) {
        super(builder);
        this.damage = damageIn;
        this.velocity = velocityIn;
    }
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        boolean flag = playerIn.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
        ItemStack arrowstack = playerIn.getProjectile(stack);

        if (!arrowstack.isEmpty() || flag) {
            if (arrowstack.isEmpty()) {
                arrowstack = new ItemStack(Items.ARROW);
            }
        }

        float f = getArrowVelocity(10 + this.velocity);

        boolean flag1 = playerIn.abilities.instabuild || (arrowstack.getItem() instanceof ArrowItem && ((ArrowItem) arrowstack.getItem()).isInfinite(arrowstack, stack, playerIn));

        boolean flag2 = !playerIn.getProjectile(stack).isEmpty();

        if (!worldIn.isClientSide) {
            if (!playerIn.abilities.instabuild && !flag2) {
                worldIn.playSound((PlayerEntity) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.DISPENSER_FAIL, SoundCategory.NEUTRAL, 1.0F, 1.5F);
            } else {

                ArrowItem arrowitem = (ArrowItem) (arrowstack.getItem() instanceof ArrowItem ? arrowstack.getItem() : Items.ARROW);
                AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, arrowstack, playerIn);
                abstractarrowentity = customArrow(abstractarrowentity);
                abstractarrowentity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, f * 3.0F, 1.0F);
                abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + this.damage);
                if (f == 1.0F) {
                    abstractarrowentity.setCritArrow(true);
                }

                int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                if (j > 0) {
                    abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double) j * 0.5D + 0.5D);
                }

                int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
                if (k > 0) {
                    abstractarrowentity.setKnockback(k);
                }

                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0) {
                    abstractarrowentity.setSecondsOnFire(100);
                }

                stack.hurtAndBreak(1, playerIn, (p_220009_1_) -> {
                    p_220009_1_.broadcastBreakEvent(playerIn.getUsedItemHand());
                });
                if (flag1 || playerIn.abilities.instabuild && (arrowstack.getItem() == Items.SPECTRAL_ARROW || arrowstack.getItem() == Items.TIPPED_ARROW)) {
                    abstractarrowentity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                }

                worldIn.playSound((PlayerEntity) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.5F);
                worldIn.addFreshEntity(abstractarrowentity);
            }

            if (!flag1 && !playerIn.abilities.instabuild) {
                arrowstack.shrink(1);
                if (arrowstack.isEmpty()) {
                    playerIn.inventory.removeItem(arrowstack);
                }
            }
        }

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(stack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;


        if (!playerIn.abilities.instabuild && !flag2) {
            return ActionResult.fail(stack);
        } else {
            playerIn.startUsingItem(handIn);
            return ActionResult.consume(stack);
        }
    }

    public static float getArrowVelocity(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }
    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        return arrow;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() { return 8; }


}
