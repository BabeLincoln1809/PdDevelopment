package com.ds.projectdawn.message;

import com.ds.projectdawn.events.LeftClickEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class LeftSwingMessage {

    public LeftSwingMessage() {
}

    public static LeftSwingMessage read(PacketBuffer buf) {
        return new LeftSwingMessage();
    }

    public static void write(LeftSwingMessage message, PacketBuffer buf) {
    }

    public static class Handler {
        public Handler() {
        }

        public static void handle(LeftSwingMessage message, Supplier<NetworkEvent.Context> context) {
            ((NetworkEvent.Context)context.get()).setPacketHandled(true);
            PlayerEntity player = ((NetworkEvent.Context)context.get()).getSender();
            if (player != null) {
                LeftClickEvent.onLeftClickSword(player, player.getItemInHand(Hand.MAIN_HAND));
                LeftClickEvent.onLeftClickStaffWand(player, player.getItemInHand(Hand.MAIN_HAND));
            }

        }
    }
}