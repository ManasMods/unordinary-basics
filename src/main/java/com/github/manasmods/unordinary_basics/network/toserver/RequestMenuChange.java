package com.github.manasmods.unordinary_basics.network.toserver;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import lombok.RequiredArgsConstructor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class RequestMenuChange {
    private final TargetMenu targetMenu;

    public RequestMenuChange(FriendlyByteBuf buf) {
        targetMenu = buf.readEnum(TargetMenu.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(targetMenu);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            switch (targetMenu) {
                case UB_FLETCHING -> Unordinary_Basics.getInstance().getApotheosisIntegration().ifPresent(apotheosisIntegration -> {
                    apotheosisIntegration.openUnordinaryBasicsFletchingMenu(player);
                });
                case APOTHEOSIS_FLETCHING -> Unordinary_Basics.getInstance().getApotheosisIntegration().ifPresent(apotheosisIntegration -> {
                    apotheosisIntegration.openApotheosisFletchingMenu(player);
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }


    public enum TargetMenu {
        UB_FLETCHING, APOTHEOSIS_FLETCHING
    }
}
