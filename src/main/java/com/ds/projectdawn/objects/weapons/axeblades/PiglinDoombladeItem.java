package com.ds.projectdawn.objects.weapons.axeblades;

import com.google.common.collect.Sets;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.HoglinEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class PiglinDoombladeItem extends AxebladeItem
{
    public PiglinDoombladeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, strengthIn, properties);
        setBonusDamage(2);
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        BlockPos pos = attacker.blockPosition();
        if(attacker.level.getBiome(pos).getBiomeCategory() == Biome.Category.NETHER && attacker.swingTime == 0.0F)
            target.hurt(DamageSource.mobAttack(attacker), this.getBonusDamage());
        this.doKnockBack((PlayerEntity)attacker, target);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    @Override
    public void doKnockBack(PlayerEntity player, LivingEntity target) {
        super.doKnockBack(player, target);
        Random rand = new Random();
        Vector3d aim = player.getLookAngle();
        int chance = rand.nextInt(100) + 1;
        if (!player.level.isClientSide) {
            if (chance <= 15) {
                target.push(0, 0.50D, 0);
                player.level.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
            else
                target.knockback(this.getStrength(), aim.x * -1, aim.z * -1);
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("+" + this.getBonusDamage() + " Damage against mobs in the Nether")).withStyle(TextFormatting.GREEN));
    }

}
