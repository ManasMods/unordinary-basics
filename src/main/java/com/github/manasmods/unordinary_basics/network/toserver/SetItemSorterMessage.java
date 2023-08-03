package com.github.manasmods.unordinary_basics.network.toserver;

import com.github.manasmods.unordinary_basics.block.entity.ItemSorterBlockEntity;
import lombok.RequiredArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class SetItemSorterMessage {

    private final BlockPos sorterPos;
    private final Component message;

    public SetItemSorterMessage(FriendlyByteBuf buf) {
        sorterPos = buf.readBlockPos();
        message = buf.readComponent();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(sorterPos);
        buf.writeComponent(message);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player.getLevel().getBlockEntity(sorterPos) instanceof ItemSorterBlockEntity sorter) {
                sorter.setMessage(2,message);
            }

        });
        ctx.get().setPacketHandled(true);
    }
}
