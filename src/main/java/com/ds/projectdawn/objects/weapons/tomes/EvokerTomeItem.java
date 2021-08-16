package com.ds.projectdawn.objects.weapons.tomes;

import com.ds.projectdawn.init.SoundInit;
import com.ds.projectdawn.objects.accessories.ManaReducerAccessory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EvokerFangsEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Random;

public class EvokerTomeItem extends AbstractTomeItem
{
    public EvokerTomeItem(IItemTier tierIn, float damageIn, int cooldownIn, Properties properties) {
        super(tierIn, damageIn, cooldownIn, properties);
        this.setXpAmount(6);
        this.setTomeName("EVOKER TOME");
        this.setSpellSound(SoundEvents.EVOKER_CAST_SPELL);
        this.setEmptySound(SoundInit.BASIC_EMPTY_SPELLCAST);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        Vector3d aim = playerIn.getLookAngle();
        Random rand = new Random();

        if (playerIn.totalExperience > 0 || playerIn.abilities.instabuild) {
            double d0 = Math.min(playerIn.getY(), playerIn.getY());
            double d1 = Math.max(playerIn.getY(), playerIn.getY()) + 1.0D;
            float f = (float) MathHelper.atan2(playerIn.getZ() - playerIn.getZ(), playerIn.getX() - playerIn.getX());

            EvokerFangsEntity fangs = new EvokerFangsEntity(worldIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), this.getMagicDamage(), 0, playerIn);

            if (!worldIn.isClientSide) {
                if (playerIn.isCrouching()) {
                    for (int i = 0; i < 5; ++i) {
                        float f1 = f + (float) i * (float) Math.PI * 0.4F;
                        this.spawnFangs(playerIn.getX() + (double) MathHelper.cos(f1) * 1.5D, playerIn.getZ() + (double) MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0, playerIn);
                    }
                } else
                    for (int l = 0; l < 8; ++l) {
                        double d2 = 1.25D * (double) (l + 1);
                        int j = 1 * l;
                        this.spawnFangs(playerIn.getX() + aim.x * d2, playerIn.getZ() + aim.z * d2, d0, d1, f, j, playerIn);
                    }
                worldIn.playSound((PlayerEntity) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), this.getSpellSound(), SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
            }

            if (!playerIn.abilities.instabuild && playerIn.totalExperience > 0) {
                double xpCost = ((double) this.getXpAmount() / 2) + 1;
                playerIn.giveExperiencePoints((int) xpCost * -1);

                stack.hurtAndBreak(1, playerIn, (p_220045_0_) -> {
                    p_220045_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
                });
            }
        }
        else
            if(!worldIn.isClientSide)
            worldIn.playSound((PlayerEntity) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), this.getEmptySound(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        playerIn.getCooldowns().addCooldown(this, this.getCooldown());

        return ActionResult.consume(stack);
    }

    private void spawnFangs(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_, PlayerEntity playerIn) {
        BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = playerIn.level.getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(playerIn.level, blockpos1, Direction.UP)) {
                if (!playerIn.level.isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = playerIn.level.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(playerIn.level, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while(blockpos.getY() >= MathHelper.floor(p_190876_5_) - 1);

        if (flag) {
            playerIn.level.addFreshEntity(new EvokerFangsEntity(playerIn.level, p_190876_1_, (double)blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, playerIn));
        }

    }

}
