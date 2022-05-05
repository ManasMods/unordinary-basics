package com.github.manasmods.vanilla_plus.registry;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ItemRegistry {
    /**
     * This Method will register all Items to Forge.
     * It is called though the {@link Vanilla_PlusRegistry#register(IEventBus)} Method.
     */
    static void register(DeferredRegister<Item> registry) {
        registerItems(registry);
    }

    /**
     * Method to register tool {@link Item} objects
     */
    private static void registerItems(DeferredRegister<Item> registry) {
    }
}
