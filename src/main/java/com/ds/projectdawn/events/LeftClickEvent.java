package com.ds.projectdawn.events;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.*;
import com.ds.projectdawn.message.LeftSwingMessage;
import com.ds.projectdawn.objects.weapons.staffs.AbstractStaffItem;
import com.ds.projectdawn.objects.weapons.swords.AbstractProjectileSwordItem;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LeftClickEvent {

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.getWorld().isClientSide) {
            ProjectDawn.sendMSGToServer(new LeftSwingMessage());
        }
    }

    public static void onLeftClickSword(PlayerEntity player, ItemStack stack) {
        Random rand = new Random();

        if (stack.getItem() instanceof AbstractProjectileSwordItem) {

            float f2 = player.getAttackStrengthScale(0.5F);
            boolean flag = f2 > 0.9F;

            AbstractProjectileSwordItem sword = (AbstractProjectileSwordItem)stack.getItem();

            if (flag) {
                if (sword.getCostsXp()) {
                    if (player.totalExperience > 0 || player.isCreative()) {
                        player.level.addFreshEntity(sword.getProjectile(player));
                        player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), sword.getCastSound(), SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                    }
                } else {
                    player.level.addFreshEntity(sword.getProjectile(player));
                    player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), sword.getCastSound(), SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                }


                if (!player.isCreative()) {
                    if (sword.getCostsXp()) {
                        if (player.totalExperience > 0) {
                            double xpCost = ((double) sword.getXpAmount() / 2) + 1;
                            player.giveExperiencePoints((int) xpCost * -1);
                            stack.hurtAndBreak(1, player, (p_220044_0_) -> {
                                p_220044_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
                            });
                        }
                    } else
                        stack.hurtAndBreak(1, player, (p_220044_0_) -> {
                            p_220044_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
                        });
                }
                //ProjectDawn.LOGGER.info(sword.getSwordName());
            }
        }
    }

    public static void onLeftClickStaffWand(PlayerEntity player, ItemStack stack) {
        Vector3d vec3d = player.getEyePosition(1.0F);
        Random rand = new Random();
        Float y = (float) vec3d.y;

        if (stack.getItem() instanceof AbstractStaffItem) {

            float f2 = player.getAttackStrengthScale(0.5F);
            boolean flag = f2 > 0.9F;

            AbstractStaffItem staff = (AbstractStaffItem)stack.getItem();

            if (flag) {

                if (player.totalExperience > 0 || player.isCreative()) {

                    player.level.addFreshEntity(staff.getProjectile(player, stack));
                    //ProjectDawn.LOGGER.info(staff.getStaffName());

                    if (!player.isCreative() && player.totalExperience > 0) {
                        double xpCost = ((double) staff.getXpAmount() / 2) + 1;
                        player.giveExperiencePoints((int) xpCost * -1);

                        stack.hurtAndBreak(1, player, (p_220044_0_) -> {
                            p_220044_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
                        });
                    }
                    player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), staff.getSpellSound(), SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));


                } else
                    player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), staff.getEmptySound(), SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));

            }
        }
    }
}

