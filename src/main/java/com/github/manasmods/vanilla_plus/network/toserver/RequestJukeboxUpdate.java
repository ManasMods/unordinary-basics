package com.github.manasmods.vanilla_plus.network.toserver;

import com.github.manasmods.vanilla_plus.menu.JukeBoxMenu;
import lombok.RequiredArgsConstructor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class RequestJukeboxUpdate {
    private final boolean playingState;


    public RequestJukeboxUpdate(FriendlyByteBuf buf) {
        this.playingState = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.playingState);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getSender().containerMenu instanceof JukeBoxMenu menu) {
                if (!menu.hasDisc()) return;

                if (this.playingState) {
                    menu.startPlaying();
                } else {
                    menu.stopPlaying();
                }
                menu.update();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
