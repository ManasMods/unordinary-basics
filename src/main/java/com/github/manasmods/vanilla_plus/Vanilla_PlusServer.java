package com.github.manasmods.vanilla_plus;

import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.server.ServerLifecycleHooks;

public class Vanilla_PlusServer extends Vanilla_PlusCommon {
    @Override
    public void preInit(IEventBus modEventBus) {
        super.preInit(modEventBus);
    }

    @Override
    public Level getLevelOrOverworld() {
        return ServerLifecycleHooks.getCurrentServer().overworld();
    }
}