package com.ds.projectdawn.objects.weapons.tomes;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.AmethystBoltEntity;
import com.ds.projectdawn.objects.entities.projectiles.MagicNoteEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class MagicHarpItem extends AbstractTomeItem {
    int ticks;
    public MagicHarpItem(IItemTier tierIn, float damageIn, int cooldownIn, Properties properties) {
        super(tierIn, damageIn, cooldownIn, properties);
        this.setXpAmount(1);
        this.setVelocity(0.50F);
        this.setInnacuracy(3.50F);
        this.setTomeName("MAGIC HARP");
        this.setSpellSound(SoundInit.MAGICHARP_USE);
        this.setEmptySound(SoundInit.MAGICHARP_USE);
    }
    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) entityLiving;
            if(!playerIn.isCrouching())
                if(playerIn.totalExperience > 0 || playerIn.isCreative())
                    playerIn.getCooldowns().addCooldown(this, this.cooldown);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
        }
    }
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        playerIn.startUsingItem(handIn);
        return ActionResult.pass(itemstack);
    }
    @Override
    public void onUsingTick(ItemStack stack, LivingEntity livingEntity, int count) {
        if(livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            Vector3d aim = player.getLookAngle();
            float y = (float) aim.y();

            if(!player.level.isClientSide) {
                if (!player.isCrouching()) {
                    if(player.totalExperience > 0 || player.isCreative()) {
                        player.level.addFreshEntity(this.getProjectile(player));
                        if (!player.isCreative() && player.totalExperience > 0) {
                            double xpCost = ((double) this.getXpAmount() / 2) + 1;
                            player.giveExperiencePoints((int) xpCost * -1);

                            stack.hurtAndBreak(1, player, (p_220044_0_) -> {
                                p_220044_0_.broadcastBreakEvent(player.getUsedItemHand());
                            });
                        }
                        ticks++;
                        if (ticks >= 10) {
                            player.getCooldowns().addCooldown(this, 30);
                            player.stopUsingItem();
                            ticks = 0;
                        }
                        player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), this.getSpellSound(), SoundCategory.PLAYERS, 1.0F, y - -1.25F);
                    }
                } else
                    player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), this.getEmptySound(), SoundCategory.PLAYERS, 1.0F, y - -1.25F);
            }
        }

    }

    @Override
    public Entity getProjectile(LivingEntity shooter) {
        Vector3d aim = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(aim);

        MagicNoteEntity magicNote = new MagicNoteEntity(EntityTypeInit.MAGIC_NOTE.get(), shooter, shooter.level);
        magicNote.setLife(30);
        magicNote.setBaseDamage((double) this.getMagicDamage());
        magicNote.setOwner(shooter);
        magicNote.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return magicNote;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }
}
