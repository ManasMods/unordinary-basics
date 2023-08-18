package com.github.manasmods.unordinary_basics.network;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.network.toclient.UBInventoryClientSync;
import com.github.manasmods.unordinary_basics.network.toserver.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Unordinary_BasicsNetwork {
    private static final String PROTOCOL_VERSION = String.valueOf(5);
    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(Unordinary_Basics.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void registerPackets() {
        int messageId = 0;
        INSTANCE.registerMessage(++messageId, RequestJukeboxUpdate.class, RequestJukeboxUpdate::toBytes, RequestJukeboxUpdate::new, RequestJukeboxUpdate::handle);
        INSTANCE.registerMessage(++messageId, RequestMenuChange.class, RequestMenuChange::toBytes, RequestMenuChange::new, RequestMenuChange::handle);
        INSTANCE.registerMessage(++messageId, RequestUBInventoryMenu.class, RequestUBInventoryMenu::toBytes, RequestUBInventoryMenu::new, RequestUBInventoryMenu::handle);
        INSTANCE.registerMessage(++messageId, UBInventoryClientSync.class, UBInventoryClientSync::toBytes, UBInventoryClientSync::new, UBInventoryClientSync::handle);
        INSTANCE.registerMessage(++messageId, SetItemSorterMessage.class, SetItemSorterMessage::toBytes, SetItemSorterMessage::new, SetItemSorterMessage::handle);
        INSTANCE.registerMessage(++messageId, RequestUBItemInventoryMenu.class, RequestUBItemInventoryMenu::toBytes, RequestUBItemInventoryMenu::new, RequestUBItemInventoryMenu::handle);
    }

    public static SimpleChannel getInstance() {
        return INSTANCE;
    }
}
