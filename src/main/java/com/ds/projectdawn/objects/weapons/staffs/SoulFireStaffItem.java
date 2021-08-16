package com.ds.projectdawn.objects.weapons.staffs;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.SoulChargeEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class SoulFireStaffItem extends AbstractStaffItem
{
    public SoulFireStaffItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tierIn, attackDamageIn, attackSpeedIn, properties);
        this.setXpAmount(25);
        this.setVelocity(1.75F);
        this.setInnacuracy(1.75F);
        this.setStaffName("SOULFIRE STAFF");
        this.setSpellSound(SoundEvents.FIRECHARGE_USE);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }
    @Override
    public Entity getProjectile(LivingEntity shooter, ItemStack stack) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        SoulChargeEntity soulCharge = new SoulChargeEntity(EntityTypeInit.SOULCHARGE.get(), shooter, shooter.level, 10);
        soulCharge.setLife(50);
        soulCharge.setBaseDamage((double) this.getMagicDamage());
        soulCharge.setOwner(shooter);
        soulCharge.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), this.getInnacuracy());
        return soulCharge;
    }
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("+2 Damage against undead mobs")).withStyle(TextFormatting.GREEN));
    }
}
