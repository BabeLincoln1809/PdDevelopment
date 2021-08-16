package com.ds.projectdawn.objects.weapons.tomes;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class AbstractTomeItem extends TieredItem
{
    private Entity projectile;
    private String tomeName;
    private SoundEvent castSpell;
    private SoundEvent castSpellEmpty;

    private int xpAmount = 1;
    private final float damage;
    private float velocity;
    private float innacuracy = 1.0F;
    public int cooldown;

    public AbstractTomeItem(IItemTier tierIn, float damageIn, int cooldownIn, Properties properties) {
        super(tierIn, properties);

        this.damage = damageIn + tierIn.getAttackDamageBonus();
        this.cooldown = cooldownIn;
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    public boolean mineBlock(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (state.getDestroySpeed(worldIn, pos) != 0.0F) {
            stack.hurtAndBreak(2, entityLiving, (entity) -> {
                entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return true;
    }
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        Vector3d aim = playerIn.getLookAngle();
        Random rand = new Random();

        if (playerIn.totalExperience > 0 || playerIn.isCreative()) {


            if (!worldIn.isClientSide) {
                worldIn.addFreshEntity(this.getProjectile(playerIn));
                //ProjectDawn.LOGGER.info(this.getTomeName());
                playerIn.level.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), this.getSpellSound(), SoundCategory.NEUTRAL, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
            }

            if (!playerIn.isCreative() && playerIn.totalExperience > 0) {
                double xpCost = ((double) this.getXpAmount() / 2) + 1;
                playerIn.giveExperiencePoints((int) xpCost * -1);

                stack.hurtAndBreak(1, playerIn, (p_220045_0_) -> {
                    p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
                });
            }
        }
        else
        if(!worldIn.isClientSide)
            playerIn.level.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), this.getEmptySound(), SoundCategory.NEUTRAL, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));

        playerIn.getCooldowns().addCooldown(this, this.getCooldown());

        return ActionResult.consume(stack);
    }

    public float getMagicDamage() {
        return this.damage - 1;
    }
    public int getCooldown() { return this.cooldown; }
    public void setVelocity(float velocityIn) { this.velocity = velocityIn; }
    public float getVelocity() {
        return this.velocity;
    }
    public void setInnacuracy(float innacuracyIn) { this.innacuracy = innacuracyIn; }
    public float getInnacuracy() {
        return this.innacuracy;
    }
    public void setSpellSound(SoundEvent spellSoundIn) { this.castSpell = spellSoundIn; }
    public SoundEvent getSpellSound() { return this.castSpell; }
    public void setEmptySound(SoundEvent emptySoundIn) { this.castSpellEmpty = emptySoundIn; }
    public SoundEvent getEmptySound() { return this.castSpellEmpty; }
    public Entity getProjectile(LivingEntity shooter) { return this.projectile; }
    public void setXpAmount(int xpIn) { this.xpAmount = xpIn; }
    public int getXpAmount() { return this.xpAmount; }
    public void setTomeName(String nameIn) { this.tomeName = nameIn; }
    public String getTomeName() { return this.tomeName; }

}
