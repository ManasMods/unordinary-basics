package com.github.manasmods.unordinary_basics.network;

import com.github.manasmods.unordinary_basics.Unordinary_Basics;
import com.github.manasmods.unordinary_basics.network.toserver.RequestJukeboxUpdate;
import com.github.manasmods.unordinary_basics.network.toserver.RequestMenuChange;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Unordinary_BasicsNetwork {
    private static final String PROTOCOL_VERSION = String.valueOf(1);
    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(Unordinary_Basics.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void registerPackets() {
        int messageId = 1;
        getInstance().registerMessage(messageId++, RequestJukeboxUpdate.class, RequestJukeboxUpdate::toBytes, RequestJukeboxUpdate::new, RequestJukeboxUpdate::handle);
        getInstance().registerMessage(messageId++, RequestMenuChange.class, RequestMenuChange::toBytes, RequestMenuChange::new, RequestMenuChange::handle);
    }

    public static SimpleChannel getInstance() {
        return INSTANCE;
    }
}
