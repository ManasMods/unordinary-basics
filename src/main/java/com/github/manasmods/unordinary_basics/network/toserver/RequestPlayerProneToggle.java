package com.github.manasmods.unordinary_basics.network.toserver;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestPlayerProneToggle {

    public RequestPlayerProneToggle(FriendlyByteBuf buf) {
    }

    public RequestPlayerProneToggle(){
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            if (!player.getLevel().getBlockState(player.getOnPos().above(2).relative(player.getDirection())).getCollisionShape(player.getLevel(),player.getOnPos().above(2).relative(player.getDirection())).isEmpty()
                    && player.getPose() != Pose.SWIMMING
                    && !player.getLevel().getBlockState(player.getOnPos().relative(player.getDirection())).getCollisionShape(player.getLevel(),player.getOnPos().relative(player.getDirection())).isEmpty()) {
                player.setPos(player.getDirection().getStepX() / 2 + player.getX(),player.getY(),player.getDirection().getStepZ() / 2 + player.getZ());
                player.setSwimming(true);
                player.setPose(Pose.SWIMMING);
            }

        });
        ctx.get().setPacketHandled(true);
    }
}
