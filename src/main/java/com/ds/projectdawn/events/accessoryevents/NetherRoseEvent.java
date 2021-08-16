package com.ds.projectdawn.events.accessoryevents;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.init.ItemInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class NetherRoseEvent {

    public NetherRoseEvent() {  }

    @SubscribeEvent
    public void netherRoseUse(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) event.getEntityLiving();
            if (!playerIn.inventory.isEmpty()) {
                if (playerIn.inventory.contains(new ItemStack(ItemInit.ARCANE_SHARD.get()))) {
                    if(!playerIn.isInLava() && !playerIn.isOnFire() && !playerIn.displayFireAnimation() && !playerIn.fireImmune() && !playerIn.isEyeInFluid(FluidTags.LAVA) && !(playerIn.getFeetBlockState().is(Blocks.LAVA)) && !(playerIn.getFeetBlockState().is(Blocks.MAGMA_BLOCK)))
                        playerIn.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200, 0, false, false, true));
                }
            }
        }
    }
}
