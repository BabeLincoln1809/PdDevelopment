package com.ds.projectdawn.objects.weapons.staffs;

import com.ds.projectdawn.events.LeftClickEvent;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractStaffItem extends TieredItem implements IVanishable {
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;
    private final float attackDamage;

    private Entity projectile;
    private String staffName;
    private SoundEvent castSpell;
    private SoundEvent castSpellEmpty;
    private int xpAmount = 1;
    private float velocity;
    private float innacuracy = 1.0F;

    public AbstractStaffItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tierIn, properties);
        this.attackDamage = (float)attackDamageIn + tierIn.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof PlayerEntity)
            LeftClickEvent.onLeftClickStaffWand((PlayerEntity) entityLiving, stack);

        return super.finishUsingItem(stack, worldIn, entityLiving);
    }
    @Override
    public boolean canAttackBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    @Override
    public boolean mineBlock(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (state.getDestroySpeed(worldIn, pos) != 0.0F) {
            stack.hurtAndBreak(2, entityLiving, (entity) -> {
                entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }
    public float getMagicDamage() { return this.attackDamage - 1; }
    public void setXpAmount(int xpIn) { this.xpAmount = xpIn; }
    public int getXpAmount() { return this.xpAmount; }
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
    public Entity getProjectile(LivingEntity shooter, ItemStack stack) { return this.projectile; }
    public void setStaffName(String nameIn) { this.staffName = nameIn; }
    public String getStaffName() { return this.staffName; }

    @SuppressWarnings("deprecation")
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
        return slot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getDefaultAttributeModifiers(slot);
    }
}


