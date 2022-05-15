package com.github.manasmods.unordinary_basics;

import com.github.manasmods.unordinary_basics.handler.HoeHandler;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.registry.Unordinary_BasicsRegistry;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract class Unordinary_BasicsCommon {

    public void preInit(IEventBus modEventBus) {
        Unordinary_BasicsRegistry.register(modEventBus);
        HoeHandler.register();
    }

    /**
     * This Method is a save way to call client only Methods on Initialization.
     * Do not add any functionality here! Use TensuraClient instead.
     */
    public void clientInit(FMLClientSetupEvent event) {}

    public void init(FMLCommonSetupEvent event) {
        event.enqueueWork(Unordinary_BasicsNetwork::registerPackets);
    }

    public abstract Level getLevelOrOverworld();
}