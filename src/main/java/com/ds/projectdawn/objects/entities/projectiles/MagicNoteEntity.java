package com.ds.projectdawn.objects.entities.projectiles;

import com.ds.projectdawn.init.ParticleInit;
import com.ds.projectdawn.init.SoundInit;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.command.arguments.ParticleArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class MagicNoteEntity extends ArcaneBoltEntity {

    public MagicNoteEntity(EntityType<? extends ArcaneBoltEntity> type, World worldIn) {
        super(type, worldIn);
        this.setParticle(ParticleTypes.NOTE);
    }
    public MagicNoteEntity(EntityType<? extends ArcaneBoltEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
        this.setParticle(ParticleTypes.NOTE);
    }

    public MagicNoteEntity(EntityType<? extends ArcaneBoltEntity> type, LivingEntity shooterIn, World worldIn) {
        super(type, shooterIn, worldIn);
        this.setParticle(ParticleTypes.NOTE);
    }
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        this.level.playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), SoundInit.MAGICHARP_USE, SoundCategory.PLAYERS, 1.0F / (random.nextFloat() * 0.4F + 0.8F), 1.0F / (random.nextFloat() * 0.4F + 0.8F));
    }
    @Override
    public boolean canBeCollidedWith() { return false; }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return null; }
}