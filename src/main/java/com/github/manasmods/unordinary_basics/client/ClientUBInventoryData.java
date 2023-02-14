package com.github.manasmods.unordinary_basics.client;

import com.github.manasmods.unordinary_basics.capability.IUBInventoryHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ClientUBInventoryData {
    private static IUBInventoryHandler handler;

    public static void set(IUBInventoryHandler handler){
        ClientUBInventoryData.handler = handler;
    }

    public static IUBInventoryHandler getHandler(){
        return handler;
    }
}
