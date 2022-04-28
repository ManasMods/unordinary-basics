package com.manasmods.vanilla_plus;

import com.manasmods.vanilla_plus.registry.Vanilla_PlusRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class Vanilla_PlusCommon {

    public void preInit(IEventBus modEventBus) {
        Vanilla_PlusRegistry.register(modEventBus);
    }

    /**
     * This Method is a save way to call client only Methods on Initialization.
     * Do not add any functionality here! Use TensuraClient instead.
     */
    public void clientInit() {}

    public void init(FMLCommonSetupEvent event) {
    }
}