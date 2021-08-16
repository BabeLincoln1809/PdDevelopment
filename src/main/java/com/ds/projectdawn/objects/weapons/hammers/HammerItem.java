package com.ds.projectdawn.objects.weapons.hammers;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.SoundInit;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
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
import net.minecraft.item.UseAction;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

import java.util.*;
import java.util.function.Predicate;

public class HammerItem extends TieredItem {
    public static final Predicate<Entity> ENTITY = Entity::isAlive;
    private final float attackDamage;
    private final float strength;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public HammerItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float strengthIn, Properties properties) {
        super(tier, properties);
        this.attackDamage = (float)attackDamageIn + tier.getAttackDamageBonus();
        this.strength = strengthIn;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public boolean hurtEnemy(ItemStack p_77644_1_, LivingEntity target, LivingEntity attacker) {
        this.doKnockBack(attacker, target);
        p_77644_1_.hurtAndBreak(1, attacker, (p_220045_0_) -> {
            p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }
    public void doKnockBack(LivingEntity attacker, LivingEntity target) {
        Vector3d aim = attacker.getLookAngle();
        target.knockback(this.getStrength(), aim.x * -1, aim.z * -1);

    }
    public void releaseUsing(ItemStack stack, World world, LivingEntity livingEntity, int time) {
        Random rand = new Random();
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            int i = this.getUseDuration(stack) - time;

            if (i < 0 || !player.isOnGround()) return;

            if (i >= 10 && player.isOnGround()) {
                if (!world.isClientSide) {
                    this.slam(player);
                    if (!player.isCreative())
                        stack.hurtAndBreak(2, player, (p_220047_1_) -> {
                            p_220047_1_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                }
                player.swing(player.getUsedItemHand());
                world.playSound((PlayerEntity) null, player, this.getSlamSound(), SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                this.spawnParticles(player);
                player.getCooldowns().addCooldown(this, 30);
            }
        }
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return ActionResult.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return ActionResult.consume(itemstack);
        }
    }
    public void slam(LivingEntity user) {
        if (user.isAlive()) {
            Vector3d look = user.getLookAngle();
            double d0 = (look.x() * this.getStrength()) * 1.5D;
            double d1 = (look.z() * this.getStrength()) * 1.5D;
            for (Entity entity : user.level.getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().expandTowards(d0, 1.25D, d1), ENTITY)) {
                if (entity != user) {
                    entity.hurt(DamageSource.mobAttack(user), this.getAttackDamage() / 2);
                    this.launch(entity, user);
                }
            }
        }
    }
    public void spawnParticles(LivingEntity entity) {
        Random offset = new Random();
        Vector3d box = entity.getBoundingBox().getCenter();
        double strength = (double)this.getStrength() * 1.5D;
        Vector3d vec = new Vector3d(entity.getLookAngle().x * (float)strength,entity.getLookAngle().y * 0, entity.getLookAngle().z * (float)strength);

        for (int i = 0; i < 40; ++i) {
            entity.level.addParticle(this.getSlamParticle(), box.x, box.y, box.z, vec.x * offset.nextDouble(), vec.y, vec.z * offset.nextDouble());
        }
        ProjectDawn.LOGGER.info(vec.x + " " + vec.y + " " + vec.z);
    }
    public void launch(Entity target, Entity user) { target.push(0, this.getStrength() * .2D, 0); }

    public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {
        if (state.getDestroySpeed(world, pos) != 0.0F) {
            stack.hurtAndBreak(2, entity, (p_220044_0_) -> {
                p_220044_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return true;
    }

    public boolean isCorrectToolForDrops(BlockState state) {
        return state.is(Blocks.COBWEB);
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && !state.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
        }
    }
    public boolean canAttackBlock(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    public float getAttackDamage() { return this.attackDamage; }
    public float getStrength() { return this.strength; }
    public UseAction getUseAnimation(ItemStack stack) { return UseAction.SPEAR; }
    public int getUseDuration(ItemStack stack) { return 72000; }
    public SoundEvent getSlamSound() { return SoundInit.HAMMER_SLAM; }
    public IParticleData getSlamParticle() { return ParticleTypes.POOF; }

    @SuppressWarnings("deprecation")
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType p_111205_1_) {
        return p_111205_1_ == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_111205_1_);
    }
}
