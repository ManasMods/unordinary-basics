package com.github.manasmods.unordinary_basics.client;

import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;

public class ClientUBInventoryData {
    private static IUBInventoryHandler handler;

    public static void set(IUBInventoryHandler handler){
        ClientUBInventoryData.handler = handler;
    }

    public static IUBInventoryHandler getHandler(){
        return handler;
    }
}
