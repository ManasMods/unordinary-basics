package com.github.manasmods.unordinary_basics.network.toserver;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.menu.UBInventoryMenu;
import com.github.manasmods.unordinary_basics.menu.UBItemInventoryMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public class RequestUBItemInventoryMenu {

    public RequestUBItemInventoryMenu() {

    }

    public RequestUBItemInventoryMenu(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //No checks since opening is always possible
            NetworkHooks.openScreen(ctx.get().getSender(), new SimpleMenuProvider(UBItemInventoryMenu::new, Component.translatable(Unordinary_Basics.MOD_ID + ".inventory.title")));
        });
        ctx.get().setPacketHandled(true);
    }
}
