package com.ds.projectdawn.message;

import com.ds.projectdawn.events.LeftClickEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent.Context;


import java.util.function.Supplier;


public class LeftSwingHandler {

    public LeftSwingHandler() {
    }

    public static void handle(LeftSwingHandler message, Supplier<Context> context) {
        ((Context)context.get()).setPacketHandled(true);
        PlayerEntity player = ((Context)context.get()).getSender();
        if (player != null) {
            LeftClickEvent.onLeftClickSword(player, player.getItemInHand(Hand.MAIN_HAND));
            LeftClickEvent.onLeftClickStaffWand(player, player.getItemInHand(Hand.MAIN_HAND));
        }

    }
}