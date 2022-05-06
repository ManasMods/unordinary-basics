package com.github.manasmods.vanilla_plus.network;

import com.github.manasmods.vanilla_plus.Vanilla_Plus;
import com.github.manasmods.vanilla_plus.network.toserver.RequestJukeboxUpdate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class Vanilla_PlusNetwork {
    private static final String PROTOCOL_VERSION = String.valueOf(1);
    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(Vanilla_Plus.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void registerPackets() {
        int messageId = 1;
        getInstance().registerMessage(messageId++, RequestJukeboxUpdate.class, RequestJukeboxUpdate::toBytes, RequestJukeboxUpdate::new, RequestJukeboxUpdate::handle);
    }

    public static SimpleChannel getInstance() {
        return INSTANCE;
    }
}
