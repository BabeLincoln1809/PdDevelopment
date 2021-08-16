package com.ds.projectdawn.objects.weapons.swords;

import com.ds.projectdawn.init.EntityTypeInit;
import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.entities.projectiles.EndSlashEntity;
import com.ds.projectdawn.objects.entities.projectiles.SoulChargeEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class EndCataclysmSwordItem extends AbstractProjectileSwordItem
{
    public EndCataclysmSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float projectileDamageIn, float velocityIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, projectileDamageIn, velocityIn, builderIn);
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        BlockPos pos = attacker.blockPosition();
        if(attacker.level.isNight() || attacker.level.getBiome(pos).getBiomeCategory() == Biome.Category.THEEND && attacker.swingTime == 0.0F)
            target.hurt(DamageSource.mobAttack(attacker), 2);
        stack.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    @Override
    public Entity getProjectile(LivingEntity shooter) {
        Vector3d vec3d = shooter.getLookAngle();
        Vector3f vec3f = new Vector3f(vec3d);
        EndSlashEntity endSlash = new EndSlashEntity(EntityTypeInit.END_SLASH.get(), shooter, shooter.level);
        endSlash.setBaseDamage((double) this.getProjectileDamage());
        endSlash.setOwner(shooter);
        endSlash.shoot((double) vec3f.x(), (double) vec3f.y(), (double) vec3f.z(), this.getVelocity(), 1.25F);
        return endSlash;
    }
    @Override
    public String getSwordName() { String swordNameIn = "ENDCATACLYSM SWORD"; return swordNameIn;}
    @Override
    public SoundEvent getCastSound() { return SoundInit.ENDSLASH; }
    @Override
    public boolean getCostsXp() { return false; }
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        tooltip.add((new TranslationTextComponent("+2 Damage against mobs during the night")).withStyle(TextFormatting.GREEN));
    }
}
