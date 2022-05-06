package com.github.manasmods.vanilla_plus;

import com.github.manasmods.vanilla_plus.handler.HoeHandler;
import com.github.manasmods.vanilla_plus.network.Vanilla_PlusNetwork;
import com.github.manasmods.vanilla_plus.registry.Vanilla_PlusRegistry;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract class Vanilla_PlusCommon {

    public void preInit(IEventBus modEventBus) {
        Vanilla_PlusRegistry.register(modEventBus);
        HoeHandler.register();
    }

    /**
     * This Method is a save way to call client only Methods on Initialization.
     * Do not add any functionality here! Use TensuraClient instead.
     */
    public void clientInit(FMLClientSetupEvent event) {}

    public void init(FMLCommonSetupEvent event) {
        event.enqueueWork(Vanilla_PlusNetwork::registerPackets);
    }

    public abstract Level getLevelOrOverworld();
}